package ru.stolexiy

import java.util.*

private fun hasPathSum(root: TreeNode?, targetSum: Int): Boolean {
    if (root == null)
        return false
    val stack = Stack<Pair<TreeNode?, Int>>()
    stack.push(root to root.`val`)
    while (stack.isNotEmpty()) {
        val cur = stack.pop()
        cur.first?.let { curNode ->
            val curSum = cur.second
            if (curNode.left == null && curNode.right == null && curSum == targetSum)
                return true
            curNode.right?.let {
                stack.push(it to it.`val` + curSum)
            }
            curNode.left?.let {
                stack.push(it to it.`val` + curSum)
            }
        }
    }
    return false
}