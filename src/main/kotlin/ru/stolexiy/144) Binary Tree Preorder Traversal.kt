package ru.stolexiy

import java.util.*

private fun preorderTraversal(root: TreeNode?): List<Int> {
    val list = mutableListOf<Int>()
    val stack = Stack<TreeNode?>()
    root?.let {
        list += it.`val`
        stack += it.right
        stack += it.left
    }
    while (stack.isNotEmpty()) {
        val cur = stack.pop()
        if (cur != null) {
            list += cur.`val`
            stack += cur.right
            stack += cur.left
        }
    }
    return list
}

private fun preorderTraversalRecursive(root: TreeNode?): List<Int> {
    return mutableListOf<Int>().apply { preorderTraversal(root, this) }
}

private fun preorderTraversal(node: TreeNode?, list: MutableList<Int>) {
    node?.let {
        list += it.`val`
        preorderTraversal(it.left, list)
        preorderTraversal(it.right, list)
    }
}