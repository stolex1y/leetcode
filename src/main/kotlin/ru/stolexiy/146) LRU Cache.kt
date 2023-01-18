class LRUCache(private val capacity: Int) {

    private val cache = mutableMapOf<Int, Int>()
    private val lruPos = mutableMapOf<Int, LinkedList.ListNode>()
    private val lruList = LinkedList()

    fun get(key: Int): Int {
        updateUsageKey(key)
        return cache.getOrElse(key) { -1 }
    }

    private fun updateUsageKey(key: Int) {
        if (cache.contains(key)) {
            val node = lruPos[key]!!
            lruList.remove(node)
            lruList.addLast(node)
        }
    }

    private fun removeLru() {
        lruList.head?.let { lruNode ->
            lruList.remove(lruNode)
            lruPos.remove(lruNode.value)
            cache.remove(lruNode.value)
        }
    }

    fun put(key: Int, value: Int) {
        if (!cache.contains(key)) {
            if (cache.size == capacity) {
                removeLru()
            }
            lruPos[key] = lruList.addLast(key)
        } else {
            updateUsageKey(key)
        }
        cache[key] = value
    }

}

class LinkedList() {
    var head: ListNode? = null
    var tail: ListNode? = null

    fun addFirst(node: ListNode): ListNode {
        if (head != null) {
            head?.prev = node
            node.next = head
        } else {
            tail = node
        }
        head = node
        return node
    }

    fun addFirst(value: Int): ListNode {
        return addFirst(ListNode(value))
    }

    fun addLast(node: ListNode): ListNode {
        if (tail != null) {
            tail?.next = node
            node.prev = tail
        } else {
            head = node
        }
        tail = node
        return node
    }

    fun addLast(value: Int): ListNode {
        return addLast(ListNode(value))
    }

    fun remove(removing: ListNode) {
        val nextNode = removing.next
        removing.prev?.next = removing.next
        removing.next?.prev = removing.prev
        if (head == removing)
            head = removing.next
        if (tail == removing)
            tail = removing.prev
        removing.next = null
        removing.prev = null
    }

    fun add(currentNode: ListNode, nextNode: ListNode) {
        if (currentNode == tail) {
            addLast(nextNode)
            return
        }
        nextNode.prev = currentNode
        nextNode.next = currentNode.next
        currentNode.next = nextNode
    }

    fun add(currentNode: ListNode, nextValue: Int) {
        val nextNode = ListNode(nextValue)
        add(currentNode, nextNode)
    }

    class ListNode(var value: Int) {
        var prev: ListNode? = null
        var next: ListNode? = null
    }
}
