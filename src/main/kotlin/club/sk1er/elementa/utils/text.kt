package club.sk1er.elementa.utils

import club.sk1er.elementa.dsl.width
import club.sk1er.mods.core.universal.UniversalGraphicsHandler

val spaceWidth = ' '.width()
fun getStringSplitToWidth(text: String, maxLineWidth: Float): List<String> {
    val maxLineWidthSpace = maxLineWidth - spaceWidth
    val lineList = mutableListOf<String>()
    val currLine = StringBuilder()
    var currLineWidth = 0f
    var textPos = 0

    fun pushLine(newLineWidth: Float = 0f) {
        lineList.add(currLine.toString())
        currLine.clear()
        currLineWidth = newLineWidth
    }

    while (textPos < text.length) {
        val builder = StringBuilder()

        while (textPos < text.length && text[textPos] != ' ') {
            builder.append(text[textPos])
            textPos++
        }

        val word = builder.toString()
        val wordWidth = word.width()

        if (currLineWidth + wordWidth > maxLineWidthSpace) {
            if (wordWidth > maxLineWidthSpace) {
                // Split up the word into it's own lines
                if (currLineWidth > 0)
                    pushLine()

                for (char in word.toCharArray()) {
                    currLineWidth += char.width()
                    if (currLineWidth > maxLineWidthSpace)
                        pushLine(char.width())
                    currLine.append(char)
                }
            } else {
                pushLine(wordWidth)
                currLine.append(word)
            }

            // Check if we have a space, and if so, append it to the new line
            if (textPos < text.length) {
                if (currLineWidth + spaceWidth > maxLineWidthSpace)
                    pushLine()
                currLine.append(' ')
                currLineWidth += spaceWidth
                textPos++
            }
        } else {
            currLine.append(word)
            currLineWidth += wordWidth

            // Check if we have a space, and if so, append it to a line
            if (textPos < text.length) {
                textPos++
                currLine.append(' ')
                currLineWidth += spaceWidth
            }
        }
    }

    lineList.add(currLine.toString())

    return lineList
}

fun sizeStringToWidth(string: String, width: Float): Int {
    val i = string.length
    var j = 0f
    var k = 0
    var l = -1

    var flag = false
    while (k < i) {
        val c0: Char = string[k]

        when (c0) {
            '\n' -> k--
            ' ' -> {
                l = k
                j += UniversalGraphicsHandler.getCharWidth(c0)

                if (flag) j++
            }
            '\u00a7' -> if (k < i - 1) {
                k++
                val c1 = string[k]
                if (c1.toInt() != 108 && c1.toInt() != 76) {
                    if (c1.toInt() == 114 || c1.toInt() == 82 || isFormatColor(c1.toInt())) {
                        flag = false
                    }
                } else {
                    flag = true
                }
            }
            else -> {
                j += UniversalGraphicsHandler.getCharWidth(c0)

                if (flag) j++
            }
        }

        if (c0.toInt() == 10) {
            k++
            l = k
            break
        }

        if (j > width) break

        k++
    }

    return if (k != i && l != -1 && l < k) l else k
}

fun isFormatColor(char: Int) = char in 48..57 || char in 97..102 || char in 65..70