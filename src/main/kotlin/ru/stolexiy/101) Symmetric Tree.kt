package ru.stolexiy

import java.util.LinkedList

private fun isSymmetric(root: TreeNode?): Boolean {
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
    val tree = TreeNode.generateTree(1,2,2,null,3,null,3)
    println(isSymmetric(tree))
}