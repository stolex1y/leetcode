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