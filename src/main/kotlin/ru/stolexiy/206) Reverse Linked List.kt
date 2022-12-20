package ru.stolexiy

fun reverseList(head: ListNode?): ListNode? {
    var prevNode = head
    var node = head?.next
    head?.next = null
    while (node != null) {
        val nextNode = node.next
        node.next = prevNode
        prevNode = node
        node = nextNode
    }
    return prevNode
}