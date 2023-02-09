package ru.stolexiy

import java.util.*

private object Solution113 {
    fun pathSum(root: TreeNode?, targetSum: Int): List<List<Int>> {
        return mutableListOf<List<Int>>().apply { pathSum(node = root, targetSum = targetSum, paths = this) }
    }

    fun pathSum(node: TreeNode?, curSum: Int = 0, targetSum: Int, path: LinkedList<Int> = LinkedList(), paths: MutableList<List<Int>>) {
        node?.let { node ->
            val newSum = curSum + node.`val`
            path.addLast(node.`val`)
            if (newSum == targetSum && node.isLeaf()) {
                paths.add(mutableListOf<Int>().apply { addAll(path) })
            } else {
                pathSum(node.left, curSum + node.`val`, targetSum, path, paths)
                pathSum(node.right, curSum + node.`val`, targetSum, path, paths)
            }
            path.removeLast()
        }
    }

    private fun TreeNode.isLeaf() = left == null && right == null
}