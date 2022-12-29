package ru.stolexiy

import java.util.PriorityQueue
import kotlin.math.sign

fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val k = lists.size
    val maxHeap = PriorityQueue<Pair<ListNode, Int>>() {
            p1, p2 -> p1.first.`val`.compareTo(p2.first.`val`)
    }
    val currNodes = Array(k) { i -> lists[i] }
    var notNullLists = 0
    for (i in 0 until k) {
        currNodes[i]?.let { currNode ->
            maxHeap.add(currNode to i)
            notNullLists++
        }
    }
    var resultHead: ListNode? = null
    var resultCurr: ListNode? = null
    while (notNullLists > 0) {
        val (maxNode, listIdx) = maxHeap.remove()
        if (resultHead == null) {
            resultHead = maxNode
            resultCurr = resultHead
        } else {
            resultCurr?.next = maxNode
            resultCurr = resultCurr?.next
        }
        currNodes[listIdx] = maxNode.next
        if (currNodes[listIdx] == null)
            notNullLists--
        else
            maxHeap.add(currNodes[listIdx]!! to listIdx)
    }
    return resultHead
}

fun main() {
    val lists = arrayOf(
        ListNode.generateLinkedList(listOf(1,4,5)),
        ListNode.generateLinkedList(listOf(1,3,4)),
        ListNode.generateLinkedList(listOf(2,6)),
    )
    println(mergeKLists(lists)?.toList()?.joinToString(",", "[", "]") ?: "[]")
}