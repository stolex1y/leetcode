package ru.stolexiy.leetcode

import ru.stolexiy.TreeNode
import java.util.*

fun mergeTreesRecursive(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    if (root1 == null && root2 == null)
        return null
    val root = TreeNode((root1?.`val` ?: 0) + (root2?.`val` ?: 0))
    root.left = mergeTrees(root1?.left, root2?.left)
    root.right = mergeTrees(root1?.right, root2?.right)
    return root
}

fun mergeTrees(root1: TreeNode?, root2: TreeNode?): TreeNode? {
    if (root1 == null && root2 == null)
        return null
    val queue1 = LinkedList<TreeNode?>()
    val queue2 = LinkedList<TreeNode?>()
    val queue3 = LinkedList<TreeNode>()
    queue1.add(root1)
    queue2.add(root2)
    val root = TreeNode(0).also { queue3.add(it) }
    while (queue1.isNotEmpty()) {
        val node1 = queue1.removeFirst()
        val node2 = queue2.removeFirst()
        val result = queue3.removeFirst()
        result.`val` = (node1?.`val` ?: 0) + (node2?.`val` ?: 0)
        if (node1?.left != null || node2?.left != null) {
            queue1.add(node1?.left)
            queue2.add(node2?.left)
            result.left = TreeNode(0).also {
                queue3.add(it)
            }
        }
        if (node1?.right != null || node2?.right != null) {
            queue1.add(node1?.right)
            queue2.add(node2?.right)
            result.right = TreeNode(0).also {
                queue3.add(it)
            }
        }
    }
    return root
}
