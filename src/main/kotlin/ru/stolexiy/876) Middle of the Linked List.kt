package ru.stolexiy

internal fun middleNode(head: ListNode?): ListNode? {
    if (head == null)
        return null
    var currNode: ListNode? = head
    var mid: ListNode = head
    var count = 1
    while (currNode != null) {
        if ((count++) % 2 == 0)
            mid = mid.next!!
        currNode = currNode.next
    }
    return mid
}

internal fun twoPointers(head: ListNode?): ListNode? {
    var slow = head
    var fast = head
    while (fast?.next != null) {
        slow = slow?.next
        fast = fast.next?.next
    }
    return slow
}

// 1 2 3 4 5 6 7
// !1
// 1 !2
// 1 !2 3
// 1 2 !3 4
// 1 2 !3 4 5
// 1 2 3 !4 5 6
// 1 2 3 !4 5 6 7