package ru.stolexiy

private fun isPalindromeOnPlace(head: ListNode?): Boolean {
    val leftHalf = head
    var first = head
    var second = head
    var i = 0
    while (second != null) {
        if (i % 2 == 0) {
            second = second.next
        } else {
            second = second.next
            first = first?.next
        }
        i++
    }
    val rightHalf = if (i % 2 == 0)
        first
    else
        first?.next
    val reversedRightHalf = reverse(rightHalf)
    return isEquals(leftHalf, reversedRightHalf)
}

private fun isEquals(first: ListNode?, second: ListNode?): Boolean {
    var cur1 = first
    var cur2 = second
    while (cur1 != null && cur2 != null) {
        if (cur1.`val` != cur2.`val`)
            return false
        cur1 = cur1.next
        cur2 = cur2.next
    }
    return true
}

private fun reverse(head: ListNode?): ListNode? {
    var cur = head?.next
    var prev = head
    prev?.next = null
    while (cur != null) {
        val next = cur.next
        cur.next = prev
        prev = cur
        cur = next
    }
    return prev
}
