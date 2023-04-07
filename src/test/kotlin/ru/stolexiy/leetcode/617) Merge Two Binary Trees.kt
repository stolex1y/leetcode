package ru.stolexiy.leetcode

import org.junit.jupiter.api.Test
import ru.stolexiy.TreeNode
import kotlin.test.assertContentEquals

class MergeTwoBinaryTreesTest {

    @Test
    internal fun test1() {
        val arrayTree1 = intArrayOf(1,3,2,5,-1,-1,-1,-1,-1)
        val arrayTree2 = intArrayOf(2,1,3,-1,4,-1,7,-1,-1,-1,-1)
        val testTree1 = TreeNode.generateTree(arrayTree1)
        val testTree2 = TreeNode.generateTree(arrayTree2)
        val expected = intArrayOf(3,4,5,5,4,-1,7,-1,-1,-1,-1,-1,-1)
        assertContentEquals(expected, mergeTrees(testTree1, testTree2)?.toIntArray())
    }

    @Test
    internal fun test2() {
        val arrayTree1 = intArrayOf(1)
        val arrayTree2 = intArrayOf(1,2)
        val testTree1 = TreeNode.generateTree(arrayTree1)
        val testTree2 = TreeNode.generateTree(arrayTree2)
        val expected = intArrayOf(2,2,-1,-1,-1)
        assertContentEquals(expected, mergeTrees(testTree1, testTree2)?.toIntArray())
    }
}