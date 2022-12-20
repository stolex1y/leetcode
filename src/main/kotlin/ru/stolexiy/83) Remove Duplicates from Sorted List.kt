package ru.stolexiy

fun deleteDuplicates(head: ListNode?): ListNode? {
    var currNode = head
    var prevNode: ListNode? = null
    while (currNode != null) {
        if (prevNode != null && prevNode.`val` == currNode.`val`) {
            prevNode.next = currNode.next
        } else {
            prevNode = currNode
        }
        currNode = currNode.next
    }
    return head
}

fun main() {
    val list = ListNode.generateLinkedList(listOf(1, 1, 2, 3, 3))
    println(deleteDuplicates(list)?.toList()?.joinToString(" ") ?: "")
}