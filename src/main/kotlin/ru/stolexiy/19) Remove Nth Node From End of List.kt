package ru.stolexiy

fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    if (head == null || n == 0)
        return head

    var preRemove: ListNode? = null
    var count = 0
    var currentNode = head
    while (currentNode != null) {
        count++
        if (count == n + 1)
            preRemove = head
        else if (count > n + 1)
            preRemove = preRemove?.next
        currentNode = currentNode.next
    }
    if (n == count)
        return head.next
    else
        preRemove?.next = preRemove?.next?.next
    return head
}