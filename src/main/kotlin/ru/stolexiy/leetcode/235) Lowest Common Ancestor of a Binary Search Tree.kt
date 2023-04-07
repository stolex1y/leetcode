package ru.stolexiy.leetcode

import ru.stolexiy.TreeNode
import java.util.*

private fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    return if (root == null || p == null || q == null)
        null
    else if (root.`val` < p.`val` && root.`val` < q.`val`)
        lowestCommonAncestor(root.right, p, q)
    else if (root.`val` > p.`val` && root.`val` > q.`val`)
        lowestCommonAncestor(root.left, p, q)
    else
        root
}

private fun lowestCommonAncestor2(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || p == null || q == null)
        return null
    var cur = root
    while (cur != null && cur.`val`.compareTo(p.`val`) * cur.`val`.compareTo(q.`val`) > 0) {
        cur = if (cur.`val` > p.`val`)
            cur.left
        else
            cur.right
    }
    return cur
}

private fun lowestCommonAncestor3(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || p == null || q == null)
        return null
    val pQueue = searchNode(root, p.`val`)
    val qQueue = searchNode(root, q.`val`)
    var ancestor: TreeNode? = null
    while (
        pQueue.isNotEmpty() &&
        qQueue.isNotEmpty() &&
        pQueue.first() == qQueue.first()
    ) {
        ancestor = pQueue.poll()
        qQueue.poll()
    }
    return ancestor
}

private fun searchNode(root: TreeNode?, target: Int): Deque<TreeNode> {
    val queue = LinkedList<TreeNode>()
    var cur = root
    while (cur != null && cur.`val` != target) {
        queue.add(cur)
        cur = if (target < cur.`val`)
            cur.left
        else
            cur.right
    }
    return if (cur?.`val` == target) {
        queue.apply { add(cur) }
    } else {
        LinkedList<TreeNode>()
    }
}