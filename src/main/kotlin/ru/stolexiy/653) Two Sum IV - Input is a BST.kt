package ru.stolexiy

import java.util.*

private fun findTarget(root: TreeNode?, k: Int): Boolean {
    if (root == null)
        return false
    val hashMap = mutableSetOf<Int>()
    val stack = Stack<TreeNode>()
    stack.push(root)
    while (stack.isNotEmpty()) {
        val curNode = stack.pop()
        val target = k - curNode.`val`
        if (hashMap.contains(target))
            return true
        hashMap.add(curNode.`val`)
        curNode.left?.let { stack.push(it) }
        curNode.right?.let { stack.push(it) }
    }
    return false
}

private class BstIterator(root: TreeNode, val isForward: Boolean = true) {
    val stack = Stack<TreeNode>()

    init {
        stack += root
        var cur = if (isForward)
            root.left
        else
            root.right
        while (cur != null) {
            stack += cur
            cur = if (isForward)
                cur.left
            else
                cur.right
        }
    }

    fun hasNext() = stack.isNotEmpty()

    fun peek(): TreeNode {
        require(hasNext())
        return stack.peek()
    }

    fun pop(): TreeNode {
        require(hasNext())
        val popped = stack.pop()
        var cur = if (isForward)
            popped.right
        else
            popped.left
        while (cur != null) {
            stack += cur
            cur = if (isForward)
                cur.left
            else
                cur.right
        }
        return popped
    }
}

private fun findTarget2(root: TreeNode?, k: Int): Boolean {
    if (root == null)
        return false
    val forwardIterator = BstIterator(root, isForward = true)
    val backwardIterator = BstIterator(root, isForward = false)
    var i = forwardIterator.peek().`val`
    var j = backwardIterator.peek().`val`
    while (i < j) {
        if (i + j == k)
            return true
        else if (i + j > k)
            backwardIterator.pop()
        else
            forwardIterator.pop()
        i = forwardIterator.peek().`val`
        j = backwardIterator.peek().`val`
    }
    return false
}

fun main() {
    val root = TreeNode.generateTree(2,1,3)
    println(findTarget2(root, 3))
}