package ru.stolexiy.leetcode

import ru.stolexiy.TreeNode

private fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
    return mutableListOf<MutableList<Int>>()
        .apply { levelOrderBottom(root, 0, this) }
        .reversed()
}

private fun levelOrderBottom(root: TreeNode?, pathLen: Int, levels: MutableList<MutableList<Int>>) {
    if (root == null)
        return

    if (levels.size < pathLen + 1) {
        levels.add(mutableListOf())
    }
    levels[pathLen].add(root.`val`)
    levelOrderBottom(root.left, pathLen + 1, levels)
    levelOrderBottom(root.right, pathLen + 1, levels)
}