package ru.stolexiy

class ListNode(var `val`: Int) {

    companion object {
        fun generateLinkedList(items: Iterable<Int>): ListNode? {
            var head: ListNode? = null
            var curr: ListNode? = null
            items.forEachIndexed { index, value ->
                if (index == 0) {
                    head = ListNode(value)
                    curr = head
                } else {
                    curr?.next = ListNode(value)
                    curr = curr?.next
                }
            }
            return head
        }
    }

    var next: ListNode? = null

    fun toList(): List<Int> {
        val list = mutableListOf<Int>()
        var currNode: ListNode? = this
        while (currNode != null) {
            list += currNode.`val`
            currNode = currNode.next
        }
        return list
    }
}

fun Array<IntArray>.toString() = joinToString(separator = "\n") {
    it.joinToString(" ")
}

enum class Direction(val dir: Pair<Int, Int>) {
    LEFT(0 to -1),
    RIGHT(0 to 1),
    UP(-1 to 0),
    DOWN(1 to 0)
}

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + other.first, this.second + other.second)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    companion object {
        fun generateTree(values: IntArray): TreeNode {
            require(values.isNotEmpty())
            val root = TreeNode(values[0])
            val queue = ArrayDeque<TreeNode>()
            queue.add(root)
            for (i in 1 until values.size step 2) {
                val current = queue.removeFirst()
                val left = values.getOrElse(i) { -1 }
                val right = values.getOrElse(i + 1) { -1 }
                if (left != -1) {
                    current.left = TreeNode(left)
                    queue.add(current.left!!)
                }
                if (right != -1) {
                    current.right = TreeNode(right)
                    queue.add(current.right!!)
                }
            }
            return root
        }
    }

    fun toIntArray(): IntArray {
        val result = mutableListOf<Int>()
        val queue = ArrayDeque<TreeNode?>()
        queue.add(this)
        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            result.add(curr?.`val` ?: -1)
            curr?.let {
                queue.add(it.left)
                queue.add(it.right)
            }
        }
        return result.toIntArray()
    }
}

