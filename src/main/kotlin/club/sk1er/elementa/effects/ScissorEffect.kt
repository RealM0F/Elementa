package club.sk1er.elementa.effects

import club.sk1er.elementa.UIComponent
import club.sk1er.elementa.components.Window
import org.lwjgl.opengl.GL11.*
import kotlin.math.max
import kotlin.math.min

/**
 * Enables GL Scissoring to restrict all drawing done by
 * the component where this is enabled to be only inside of that component's bounds.
 *
 * By proxy, this restricts all of said component's children drawing to inside of the same bounds.
 *
 * [scissorIntersection] will try to combine this scissor with all of it's parents scissors (if any).
 */
class ScissorEffect @JvmOverloads constructor(
    private val customBoundingBox: UIComponent? = null,
    private val scissorIntersection: Boolean = true
) : Effect {
    private var oldState: ScissorState? = null

    override fun beforeDraw(component: UIComponent) {
        val boundingBox = customBoundingBox ?: component
        val res = Window.of(component).scaledResolution

        if (currentScissorState == null) {
            glEnable(GL_SCISSOR_TEST)
        }

        oldState = currentScissorState
        val state = oldState

        var x = boundingBox.getLeft().toInt() * res.scaleFactor
        var y = (res.scaledHeight * res.scaleFactor) - (boundingBox.getBottom().toInt() * res.scaleFactor)
        var width = boundingBox.getWidth().toInt() * res.scaleFactor
        var height = boundingBox.getHeight().toInt() * res.scaleFactor

        val x2 = x + width
        val y2 = y + height

        if (state != null && scissorIntersection) {
            val oldX = state.x
            val oldY = state.y
            val oldX2 = state.x + state.width
            val oldY2 = state.y + state.height

            x = max(x, oldX)
            y = max(y, oldY)
            width = min(x2, oldX2) - x
            height = min(y2, oldY2) - y
        }

        glScissor(x, y, width.coerceAtLeast(0), height.coerceAtLeast(0))

        currentScissorState = ScissorState(x, y, width.coerceAtLeast(0), height.coerceAtLeast(0))
    }

    override fun beforeChildrenDraw(component: UIComponent) {
        // do nothing
    }

    override fun afterDraw(component: UIComponent) {
        val state = oldState

        if (state != null) {
            glScissor(state.x, state.y, state.width, state.height)

            oldState = null
        } else {
            glDisable(GL_SCISSOR_TEST)
        }

        currentScissorState = state
    }

    data class ScissorState(val x: Int, val y: Int, val width: Int, val height: Int)

    companion object {
        var currentScissorState: ScissorState? = null
    }
}