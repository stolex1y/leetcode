package ru.stolexiy

fun removeElements(list: ListNode?, `val`: Int): ListNode? {
    var head = list
    var curNode = head
    var prevNode = head
    while (curNode != null) {
        if (curNode.`val` == `val`) {
            if (head == curNode)
                head = curNode.next
            else
                prevNode?.next = curNode.next
        } else {
            prevNode = curNode
        }
        curNode = curNode.next
    }
    return head
}

fun main() {
    val sourceList = listOf(1, 2, 2, 1)
    val source = ListNode.generateLinkedList(sourceList)
    val targetDelete = 2
    val expectedList = sourceList.filter { it !=  targetDelete}
    val result = removeElements(source, targetDelete)?.toList() ?: emptyList()
    println("expected: ${expectedList.joinToString(" ")}, actually: ${result.joinToString(" ")}")
}