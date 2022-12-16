package ru.stolexiy

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null)
        return list2
    else if (list2 == null)
        return list1

    var curL1Node = list1
    var curL2Node = list2
    val head: ListNode
    if (list1.`val` <= list2.`val`) {
        head = list1
        curL1Node = curL1Node.next
    } else {
        head = list2
        curL2Node = curL2Node.next
    }

    var curNode = head
    while (curL1Node != null && curL2Node != null) {
        if (curL1Node.`val` <= curL2Node.`val`) {
            curNode.next = curL1Node
            curNode = curL1Node
            curL1Node = curL1Node.next
        } else {
            curNode.next = curL2Node
            curNode = curL2Node
            curL2Node = curL2Node.next
        }
    }
    if (curL1Node == null) {
        curNode.next = curL2Node
    } else
        curNode.next = curL1Node
    return head
}

fun mergeTwoListsRecursive(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null)
        return list2
    else if (list2 == null)
        return list1
    return if (list1.`val` < list2.`val`) {
        list1.next = mergeTwoLists(list1.next, list2)
        list1
    } else {
        list2.next = mergeTwoLists(list1, list2.next)
        list2
    }
}

fun main() {
    val list1 = ListNode.generateLinkedList(intArrayOf(1, 2, 4).asIterable())
    val list2 = ListNode.generateLinkedList(intArrayOf(1, 3, 4).asIterable())
    val result = mergeTwoListsRecursive(list1, list2)
    println(result?.toList() ?: "")
}