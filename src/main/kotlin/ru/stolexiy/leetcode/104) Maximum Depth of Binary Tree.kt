package ru.stolexiy.leetcode

import ru.stolexiy.TreeNode
import java.util.*

private fun maxDepth(root: TreeNode?): Int {
    if (root == null)
        return 0
    val stack = Stack<Pair<TreeNode?, Int>>()
    var max = 0
    stack.push(root to 1)
    while (stack.isNotEmpty()) {
        val cur = stack.pop()
        cur.first?.let { curNode ->
            val curLevel = cur.second
            if (curLevel > max)
                max = curLevel
            stack.push(curNode.right to curLevel + 1)
            stack.push(curNode.left to curLevel + 1)
        }
    }
    return max
}