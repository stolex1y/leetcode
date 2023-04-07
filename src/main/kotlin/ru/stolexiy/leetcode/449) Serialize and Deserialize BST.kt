package ru.stolexiy.leetcode

import ru.stolexiy.TreeNode
import java.util.LinkedList

private class Codec() {
    fun preorderTreeWalk(root: TreeNode?, nodeList: MutableList<Int>) {
        if (root == null)
            return
        nodeList.add(root.`val`)
        preorderTreeWalk(root.left, nodeList)
        preorderTreeWalk(root.right, nodeList)
    }

    fun insert(root: TreeNode?, value: Int): TreeNode {
        if (root == null)
            return TreeNode(value)
        if (value <= root.`val`)
            root.left = insert(root.left, value)
        else
            root.right = insert(root.right, value)
        return root
    }

    // Encodes a tree to a single string.
    fun serialize(root: TreeNode?): String {
        val nodeList = mutableListOf<Int>()
        preorderTreeWalk(root, nodeList)
        return nodeList.joinToString(";")
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {
        val nodeList = data.split(";").mapNotNull { it.toIntOrNull() }
        if (nodeList.isEmpty())
            return null
        val root = TreeNode(nodeList.first())
        for (i in 1 until nodeList.size) {
            insert(root, nodeList[i])
        }
        return root
    }

    fun deserializeLinear(data: String): TreeNode? {
        val nodeList = LinkedList(data.split(";").mapNotNull { it.toIntOrNull() })
        return deserializeLinear(nodeList)
    }

    fun deserializeLinear(list: LinkedList<Int>, min: Int = Int.MIN_VALUE, max: Int = Int.MAX_VALUE): TreeNode? {
        if (list.isEmpty())
            return null
        val value = list.peek().toInt()
        if (value < min || value > max)
            return null
        list.poll()
        val root = TreeNode(value)
        root.left = deserializeLinear(list, min, value)
        root.right = deserializeLinear(list, value, max)
        return root
    }
}

fun main() {
    val root = TreeNode.generateTree(intArrayOf(2, 1, 3))
    val codec = Codec()
    val serialized = codec.serialize(root)
    println("Serialized: ${serialized}. " +
            "Deserialized: ${codec.deserializeLinear(serialized)
                ?.toIntArray()
                ?.filter { it != -1 }
                ?.joinToString(";") 
                ?: ""
            }")
}