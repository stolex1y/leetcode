package ru.stolexiy.leetcode

import ru.stolexiy.BinarySearchTreeNode

private fun isValidBST(root: BinarySearchTreeNode?): Boolean {
    if (root == null)
        return true
    val nodeList = inorderTraversal(root)
    var prev = nodeList.first()
    for (i in 1 until nodeList.size) {
        if (nodeList[i] < prev)
            return false
        else
            prev = nodeList[i]
    }
    return true
}

private fun inorderTraversal(root: BinarySearchTreeNode?): List<Int> {
    return mutableListOf<Int>().apply { inorderTraversal(root, this) }
}

private fun inorderTraversal(node: BinarySearchTreeNode?, list: MutableList<Int>) {
    if (node != null) {
        inorderTraversal(node.left, list)
        list += node.`val`
        inorderTraversal(node.right, list)
    }
}

private fun isValidBST2(root: BinarySearchTreeNode?) = isValidBST2(root, null, null)

private fun isValidBST2(root: BinarySearchTreeNode?, max: Int?, min: Int?): Boolean {
    root?.let {
        return if ((max != null && it.`val` >= max) || (min != null && it.`val` <= min))
            false
        else
            isValidBST2(it.left, it.`val`, min) && isValidBST2(it.right, max, it.`val`)
    } ?: return true
}

fun main() {
    val tree = BinarySearchTreeNode.generateTree(intArrayOf(2, 2, 2))
    tree.right = BinarySearchTreeNode(2)
    println(isValidBST2(tree))
}