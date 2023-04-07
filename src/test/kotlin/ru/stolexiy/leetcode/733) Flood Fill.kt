package ru.stolexiy.leetcode

import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertTrue

class FloodFillTest {

    @Test
    internal fun test1() {
        val sourceImage: Array<IntArray> = arrayOf(
            intArrayOf(1,1,1),
            intArrayOf(1,1,0),
            intArrayOf(1,0,1)
        )
        val expectedImage: Array<IntArray> = arrayOf(
            intArrayOf(2,2,2),
            intArrayOf(2,2,0),
            intArrayOf(2,0,1)
        )
        val color = 2
        val sourcePixel = 1 to 1
        val actualImage = floodFill(sourceImage, sourcePixel.first, sourcePixel.second, color)
        assertTrue(expectedImage.contentDeepEquals(actualImage),
            "Failed with source image: \n" +
                sourceImage.toString() + ",\n" +
                "source pixel = $sourcePixel,\n" +
                "color = $color.\n" +
                "Expected: \n" +
                expectedImage.toString() + ",\n" +
                "but actual: \n" +
                actualImage.toString()
        )
    }
}