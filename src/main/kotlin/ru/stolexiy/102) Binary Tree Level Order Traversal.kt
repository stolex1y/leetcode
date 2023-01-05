package ru.stolexiy

import java.util.*

private fun levelOrder(root: TreeNode?): List<List<Int>> {
    if (root == null)
        return listOf()
    val queue1 = LinkedList<TreeNode?>()
    val queue2 = LinkedList<TreeNode?>()
    var level = 0
    val result = mutableListOf<MutableList<Int>>()
    queue1 += root
    while (queue1.isNotEmpty() || queue2.isNotEmpty()) {
        val cur = if (level % 2 == 0) {
            if (queue1.isEmpty()) {
                level++
                continue
            }
            queue1.pop()?.apply {
                queue2 += this.left
                queue2 += this.right
            }
        } else {
            if (queue2.isEmpty()) {
                level++
                continue
            }
            queue2.pop()?.apply {
                queue1 += this.left
                queue1 += this.right
            }
        }
        cur?.let {
            if (result.size <= level)
                result.add(mutableListOf())
            result[level].add(it.`val`)
        }
    }
    return result
}

private fun levelOrder2(root: TreeNode?): List<List<Int>> {
    return mutableListOf<MutableList<Int>>().apply { levelOrder(root to 0, this) }
}

private fun levelOrder(pair: Pair<TreeNode?, Int>, levelList: MutableList<MutableList<Int>>) {
    pair.first?.let { root ->
        val level = pair.second
        if (levelList.size <= level) {
            levelList.add(mutableListOf())
        }
        levelList[level].add(root.`val`)
        levelOrder(root.left to level + 1, levelList)
        levelOrder(root.right to level + 1, levelList)
    }
}
