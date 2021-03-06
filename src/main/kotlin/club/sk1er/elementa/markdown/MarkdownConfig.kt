package club.sk1er.elementa.markdown

import java.awt.Color

data class MarkdownConfig @JvmOverloads constructor(
    val headerConfig: HeaderConfig = HeaderConfig(),
    val listConfig: ListConfig = ListConfig(),
    val textConfig: TextConfig = TextConfig(),
    val blockquoteConfig: BlockquoteConfig = BlockquoteConfig(),
    val inlineCodeConfig: InlineCodeConfig = InlineCodeConfig(),
    val codeblockConfig: CodeblockConfig = CodeblockConfig(),
    val urlConfig: URLConfig = URLConfig()
)

data class HeaderConfig @JvmOverloads constructor(
    val fontColor: Color = Color.WHITE,
    val level1: HeaderLevelConfig = HeaderLevelConfig(fontColor, 2.0f, 12f, 6f, hasDivider = true),
    val level2: HeaderLevelConfig = HeaderLevelConfig(fontColor, 1.66f, 10f, 5f, hasDivider = true),
    val level3: HeaderLevelConfig = HeaderLevelConfig(fontColor, 1.33f, 8f, 4f),
    val level4: HeaderLevelConfig = HeaderLevelConfig(fontColor, 1.0f, 6f, 3f),
    val level5: HeaderLevelConfig = HeaderLevelConfig(fontColor, 0.7f, 4f, 2f),
    val level6: HeaderLevelConfig = HeaderLevelConfig(Color(155, 155, 155), 0.7f, 4f, 2f)
)

data class HeaderLevelConfig @JvmOverloads constructor(
    val fontColor: Color,
    val textScale: Float,
    val spaceBefore: Float,
    val spaceAfter: Float,
    val hasDivider: Boolean = false,
    val dividerColor: Color = Color(80, 80, 80),
    val dividerWidth: Float = 2f
)

data class ListConfig @JvmOverloads constructor(
    val fontColor: Color = Color.WHITE,
    val indentation: Float = 10f,
    val elementSpacing: Float = 5f,
    val spaceBeforeText: Float = 4f,
    val spaceBeforeList: Float = 5f,
    val spaceAfterList: Float = 5f
)

data class TextConfig @JvmOverloads constructor(
    val color: Color = Color.WHITE,
    val shadow: Boolean = true,
    val spaceBetweenLines: Float = 4f
)

data class InlineCodeConfig @JvmOverloads constructor(
    val fontColor: Color = Color.WHITE,
    val backgroundColor: Color = Color(60, 60, 60, 255),
    val outlineColor: Color = Color(140, 140, 140, 255),
    val outlineWidth: Float = 0.5f,
    val cornerRadius: Float = 3f,
    val steps: Int = 10,
    val leftPadding: Float = 2f,
    val topPadding: Float = 2f,
    val rightPadding: Float = 2f,
    val bottomPadding: Float = 2f
)

data class CodeblockConfig @JvmOverloads constructor(
    val fontColor: Color = Color.WHITE,
    val backgroundColor: Color = Color(40, 40, 40, 255),
    val outlineColor: Color = Color(120, 120, 120, 255),
    val outlineWidth: Float = 0.5f,
    val cornerRadius: Float = 3f,
    val leftPadding: Float = 5f,
    val topPadding: Float = 5f,
    val rightPadding: Float = 5f,
    val bottomPadding: Float = 5f,
    val topMargin: Float = 10f,
    val bottomMargin: Float = 10f
)

data class URLConfig @JvmOverloads constructor(
    val fontColor: Color = Color(6, 217, 210),
    val showBarOnHover: Boolean = true,
    val barColor: Color = Color(6, 217, 210),
    val barWidth: Float = 1f,
    val spaceBeforeBar: Float = 1f
)

data class BlockquoteConfig @JvmOverloads constructor(
    val spaceBeforeDivider: Float = 3f,
    val spaceAfterDivider: Float = 6f,
    val spaceBeforeBlockquote: Float = 7f,
    val spaceAfterBlockquote: Float = 7f,
    val spaceBetweenLines: Float = 2f,
    val dividerColor: Color = Color(80, 80, 80),
    val dividerWidth: Float = 2f
)
