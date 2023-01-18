package ru.stolexiy

private fun deleteDuplicates(head: ListNode?): ListNode? {
    var newHead: ListNode? = null
    var prev: ListNode? = null
    var cur = head
    while (cur != null) {
        if (cur.`val` == cur.next?.`val`) {
            val removedValue = cur.`val`
            while (cur?.`val` == removedValue) {
                val newNext = cur.next
                prev?.next = newNext
                cur.next = null
                cur = newNext
            }
        } else {
            prev = cur
            cur = cur.next
            if (newHead == null) {
                newHead = prev
            }
        }
    }
    return newHead
}