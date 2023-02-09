package ru.stolexiy

import java.util.*

private object Solution100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return if ((p == null).xor(q == null))
            false
        else if (p == null)
            true
        else
            p.`val` == q?.`val` && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }

    fun isSameTree2(p: TreeNode?, q: TreeNode?): Boolean {
        val queue1 = LinkedList<TreeNode?>().apply { add(p) }
        val queue2 = LinkedList<TreeNode?>().apply { add(q) }
        while (queue1.isNotEmpty() && queue2.isNotEmpty()) {
            val cur1 = queue1.removeFirst()
            val cur2 = queue2.removeFirst()
            if (cur1?.`val` != cur2?.`val`)
                return false
            cur1?.let { queue1.addLast(it.left); queue1.addLast(it.right) }
            cur2?.let { queue2.addLast(it.left); queue2.addLast(it.right) }
        }
        return queue1.isEmpty() && queue2.isEmpty()
    }
}