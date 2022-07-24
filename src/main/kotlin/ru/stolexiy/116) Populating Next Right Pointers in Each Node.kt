package ru.stolexiy

import kotlin.math.pow

fun connect(root: Node?) = connectBfsIterative(root)

fun connectBfsToArray(root: Node?): Node? {
    val nodeArray = root?.toNodeArray() ?: arrayOf()
    var layer = 0
    var onLayerOst = 1
    nodeArray.forEachIndexed { index, node ->
        onLayerOst--
        if (onLayerOst != 0)
            node?.next = nodeArray.getOrNull(index + 1)
        else
            onLayerOst = (2.0).pow(++layer).toInt()
    }
    return root
}

fun connectBfsRecursive(root: Node?): Node? {
    if (root == null)
        return null
    root.left?.next = root.right
    root.right?.next = root.next?.left
    connectBfsRecursive(root.next)
    connectBfsRecursive(root.left)
    return root
}

fun connectDfsRecursive(root: Node?): Node? {
    if (root == null)
        return null
    root.left?.next = root.right
    root.right?.next = root.next?.left
    connectDfsRecursive(root.left)
    connectDfsRecursive(root.right)
    return root
}

fun connectBfsIterative(root: Node?): Node? {
    var layerStart = root
    while (layerStart != null) {
        var currentNode = layerStart
        while (currentNode != null) {
            currentNode.left?.next = currentNode.right
            currentNode.right?.next = currentNode.next?.left
            currentNode = currentNode.next
        }
        layerStart = layerStart.left
    }
    return root
}