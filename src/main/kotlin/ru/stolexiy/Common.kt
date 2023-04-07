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

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + other.first, this.second + other.second)
}

class BinarySearchTreeNode(var `val`: Int) {
    var left: BinarySearchTreeNode? = null
    var right: BinarySearchTreeNode? = null
    companion object {
        fun generateTree(values: IntArray, equalsToLeft: Boolean = true): BinarySearchTreeNode {
            require(values.isNotEmpty())
            val root = BinarySearchTreeNode(values.first())
            values.drop(1).forEach { root.insert(it, equalsToLeft) }
            return root
        }
    }

    fun insert(value: Int, equalsToLeft: Boolean) {
        var cur: BinarySearchTreeNode? = this
        var p: BinarySearchTreeNode = this
        while (cur != null) {
            p = cur
            cur = if (value < cur.`val` || (value == cur.`val` && equalsToLeft))
                cur.left
            else
                cur.right
        }
        if (value < p.`val` || (value == p.`val` && equalsToLeft))
            p.left = BinarySearchTreeNode(value)
        else
            p.right = BinarySearchTreeNode(value)
    }

    fun inorderTraversal(): List<Int> {
        return mutableListOf<Int>().apply { inorderTraversal(this@BinarySearchTreeNode, this@apply) }
    }

    private fun inorderTraversal(node: BinarySearchTreeNode?, list: MutableList<Int>) {
        if (node != null) {
            inorderTraversal(node.left, list)
            list += node.`val`
            inorderTraversal(node.right, list)
        }
    }
}

class TreeNode(var `val`: Int) {
    var parent: TreeNode? = null
    var left: TreeNode? = null
    var right: TreeNode? = null

    companion object {
        fun generateTree(vararg values: Int?): TreeNode {
            require(values.isNotEmpty() && values.first() != null)
            val root = TreeNode(values.first()!!)
            val queue = ArrayDeque<TreeNode>()
            queue.add(root)
            for (i in 1 until values.size step 2) {
                val current = queue.removeFirst()
                val left = values.getOrNull(i)
                val right = values.getOrNull(i + 1)
                left?.let {
                    TreeNode(left).let { node ->
                        node.parent = current
                        current.left = node
                        queue.add(node)
                    }
                }
                right?.let {
                    TreeNode(right).let { node ->
                        node.parent = current
                        current.right = node
                        queue.add(node)
                    }
                }
            }
            return root
        }

        fun generateTree(values: IntArray): TreeNode {
            return generateTree(*values.toTypedArray())
        }

    }

    fun toIntArray(): IntArray {
        return toArray().mapNotNull { it }.toIntArray()
    }

    fun toArray(): Array<Int?> {
        val result = mutableListOf<Int?>()
        val queue = ArrayDeque<TreeNode?>()
        queue.add(this)
        while (queue.isNotEmpty()) {
            val curr = queue.removeFirst()
            result.add(curr?.`val`)
            curr?.let {
                queue.add(it.left)
                queue.add(it.right)
            }
        }
        return result.toTypedArray()
    }

    override fun toString(): String {
        return toArray().joinToString(",", "[", "]")
    }
}

class Node(var `val`: Int) {
    var left: Node? = null
    var right: Node? = null
    var next: Node? = null

    companion object {
        fun generateTree(values: IntArray): Node? {
            if (values.isEmpty())
                return null
            val root = Node(values[0])
            val queue = ArrayDeque<Node>()
            queue.add(root)
            for (i in 1 until values.size step 2) {
                val current = queue.removeFirst()
                val left = values[i]
                val right = values[i + 1]
                current.left = Node(left).also {
                    queue.add(it)
                }
                current.right = Node(right).also {
                    queue.add(it)
                }
            }
            return root
        }
    }

    fun decomposeToLayers(): IntArray {
        val result = mutableListOf<Int>()
        val layer = mutableListOf<Int>()
        var currentNode: Node? = this
        while (currentNode != null) {
            layer += currentNode.`val`
            currentNode = currentNode.next
        }
        result += layer
        if (left != null) {
            result += -1
            result += left!!.decomposeToLayers().toList()
        } else if (right != null) {
            result += -1
            result += right!!.decomposeToLayers().toList()
        }
        return result.toIntArray()
    }
}

fun Node.toNodeArray(): Array<Node?> {
    val result = mutableListOf<Node?>()
    val queue = ArrayDeque<Node?>()
    queue.add(this)
    while (queue.isNotEmpty()) {
        val curr = queue.removeFirst()
        result.add(curr)
        curr?.let { it ->
            it.left?.let {
                queue.add(it)
            }
            it.right?.let {
                queue.add(it)
            }
        }
    }
    return result.toTypedArray()
}

class MaxHeap<T>(val capacity: Int) {
    private val array = Array(capacity) { Pair<Int, T?>(0, null) }
    var size: Int = 0
        private set

    constructor(array: Array<Pair<Int, T?>>) : this(array.size) {
        array.forEachIndexed { index, i -> this.array[index] = i }
        this.size = array.size
        for (i in this.array.size / 2 - 1 downTo 0)
            heapify(i)
    }

    fun extractMax(): T? {
        if (size == 0)
            return null
        val max = array.first()
        array[0] = array[size - 1]
        array[size - 1] = 0 to null
        size--
        heapify(0)
        return max.second
    }

    private fun heapify(i: Int) {
        if (i >= this.size) return
        val left = left(i)
        val right = right(i)
        val cur = array[i]
        var maxIdx = i
        if (left < this.size && array[left].first > array[maxIdx].first)
            maxIdx = left
        if (right < this.size && array[right].first > array[maxIdx].first)
            maxIdx = right
        if (maxIdx == i)
            return
        else {
            array[i] = array[maxIdx]
            array[maxIdx] = cur
            heapify(maxIdx)
        }
    }

    private fun left(i: Int) = (i + 1) * 2 - 1
    private fun right(i: Int) = (i + 1) * 2
    private fun parent(i: Int) = (i + 1) / 2 - 1
}

