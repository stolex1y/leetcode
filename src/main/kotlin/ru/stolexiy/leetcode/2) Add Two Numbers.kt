package ru.stolexiy.leetcode

import ru.stolexiy.ListNode

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    var resultHead: ListNode? = null
    var curResult: ListNode? = null
    var cur1 = l1
    var cur2 = l2
    var carryFlag = 0
    while (cur1 != null || cur2 != null || carryFlag > 0) {
        val sum = calcSum(cur1, cur2, carryFlag)
        carryFlag = sum / 10
        if (curResult == null) {
            resultHead = ListNode(sum % 10)
            curResult = resultHead
        } else {
            curResult.next = ListNode(sum % 10)
            curResult = curResult.next
        }
        cur1 = cur1?.next
        cur2 = cur2?.next
    }
    return resultHead
}

private fun calcSum(l1: ListNode?, l2: ListNode?, carry: Int) =
    (l1?.`val` ?: 0) + (l2?.`val` ?: 0) + carry