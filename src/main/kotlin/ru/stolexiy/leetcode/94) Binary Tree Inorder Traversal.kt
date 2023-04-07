package ru.stolexiy.leetcode

import ru.stolexiy.TreeNode

private fun inorderTraversal(root: TreeNode?): List<Int> {
    return mutableListOf<Int>().apply { inorderTraversal(root, this) }
}

private fun inorderTraversal(node: TreeNode?, list: MutableList<Int>) {
    if (node != null) {
        inorderTraversal(node.left, list)
        list += node.`val`
        inorderTraversal(node.right, list)
    }
}