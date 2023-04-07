package ru.stolexiy.leetcode

import ru.stolexiy.TreeNode
import java.util.LinkedList

private fun isSymmetric(root: TreeNode?): Boolean {
    return isSymmetric(root, root)
}

private fun isSymmetric(first: TreeNode?, second: TreeNode?): Boolean {
    if (first != null && second != null) {
        return if (first === second)
            return isSymmetric(first.left, first.right)
        else if (first.`val` != second.`val`)
            false
        else
            isSymmetric(first.left, second.right) && isSymmetric(first.right, second.left)
    } else {
        return first == null && second == null
    }
}

private fun isSymmetric2(root: TreeNode?): Boolean {
    val curLevel = LinkedList<TreeNode?>()
    val nextLevel = LinkedList<TreeNode?>()
    curLevel.add(root)
    while (curLevel.isNotEmpty()) {
        val cur = curLevel.removeFirst()
        cur?.let {
            nextLevel.add(it.left)
            nextLevel.add(it.right)
        }
        if (curLevel.isEmpty()) {
            if (nextLevel.size % 2 != 0)
                return false
            for (node in nextLevel)
                curLevel.addLast(node)
            while (nextLevel.isNotEmpty()) {
                val first = nextLevel.removeFirst()
                val last = nextLevel.removeLast()
                if (first?.`val` != last?.`val`)
                    return false
            }
        }
    }
    return true
}

fun main() {
    val tree = TreeNode.generateTree(1, 2, 2, null, 3, null, 3)
    println(isSymmetric(tree))
}