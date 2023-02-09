package ru.stolexiy

import java.util.*
import kotlin.math.abs
import kotlin.math.max

private object Solution110 {
    fun isBalanced(root: TreeNode?): Boolean {
        return if (root == null)
            true
        else {
            val leftHeight = root.left?.getHeight() ?: 0
            val rightHeight = root.right?.getHeight() ?: 0
            abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right)
        }
    }

    private fun TreeNode.getHeight(): Int {
        return max(this.left?.getHeight() ?: 0, this.right?.getHeight() ?: 0) + 1
    }

    fun isBalancedIterative(root: TreeNode?): Boolean {
        val heights = getHeightsIterative(root)
        val stack = Stack<TreeNode?>().apply { push(root) }
        while (stack.isNotEmpty()) {
            stack.pop()?.let { node ->
                val leftH = node.left?.let { heights[it] } ?: 0
                val rightH = node.right?.let { heights[it] } ?: 0
                if (abs(leftH - rightH) > 1)
                    return false
                stack.push(node.left)
                stack.push(node.right)
            }
        }
        return true
    }

    private fun getHeightsIterative(root: TreeNode?): Map<TreeNode, Int> {
        val heights = mutableMapOf<TreeNode, Int>()
        val stack = Stack<TreeNode?>().apply { push(root) }
        while (stack.isNotEmpty()) {
            stack.peek()?.let { node ->
                val left = node.left
                val right = node.right

                val leftHeight = if (left == null)
                    0
                else
                    heights[left]

                val rightHeight = if (right == null)
                    0
                else
                    heights[right]

                if (leftHeight != null && rightHeight != null) {
                    stack.pop()
                    heights[node] = max(leftHeight, rightHeight) + 1
                } else {
                    if (leftHeight == null)
                        stack.push(left)
                    if (rightHeight == null)
                        stack.push(right)
                }
            } ?: run {
                stack.pop()
            }
        }
        return heights
    }
}