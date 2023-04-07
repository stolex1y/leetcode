package ru.stolexiy.leetcode

import org.junit.jupiter.api.Test
import ru.stolexiy.Node
import kotlin.test.assertContentEquals

class PopulatingNextRightPointersInEachNodeTest {

    @Test
    internal fun test1() {
        val testTree = Node.generateTree((0..14).toList().toIntArray())
        val expected = intArrayOf(0,-1,1,2,-1,3,4,5,6,-1,7,8,9,10,11,12,13,14)
        val actual = connect(testTree)?.decomposeToLayers()
        assertContentEquals(expected, actual)
    }

    @Test
    internal fun test2() {
        val testTree = Node.generateTree(intArrayOf())
        val expected = null
        val actual = connect(testTree)?.decomposeToLayers()
        assertContentEquals(expected, actual)
    }
}