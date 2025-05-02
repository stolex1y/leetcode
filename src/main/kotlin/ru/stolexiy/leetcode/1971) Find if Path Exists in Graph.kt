package ru.stolexiy.leetcode

fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    if (source == destination)
        return true
    val adjList = Array<MutableList<Int>>(n) { mutableListOf() }
    for (edge in edges) {
        adjList[edge.first()].add(edge.last())
        adjList[edge.last()].add(edge.first())
    }
    val visiting = BooleanArray(n) { false }
    val queue = ArrayDeque<Int>()
    queue.add(source)
    while (queue.isNotEmpty()) {
        val v = queue.removeFirst()
        visiting[v] = true
        for (adjV in adjList[v]) {
            if (adjV == destination)
                return true
            if (!visiting[adjV])
                queue.addFirst(adjV)
        }
    }
    return false
}

fun validPathDisjointSet(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    val parent = IntArray(n) { it }
    for (edge in edges) {
        union(parent, edge[0], edge[1])
    }
    return areConnected(parent, source, destination)
}

private fun areConnected(parent: IntArray, x: Int, y: Int) = find(parent, x) == find(parent, y)

private fun union(parent: IntArray, x: Int, y: Int) {
    if (x != y) {
        val parentX = find(parent, x)
        val parentY = find(parent, y)
        parent[parentX] = parentY
    }
}

private fun find(parent: IntArray, x: Int): Int {
    var i = parent[x]
    while (parent[i] != i)
        i = parent[i]
    return i
}

fun main() {
    val edges = arrayOf(
        intArrayOf(0,1),
        intArrayOf(0,2),
        intArrayOf(3,5),
        intArrayOf(5,4),
        intArrayOf(4,3),

    )
    for (i in 0..5) {
        for (y in 0..5) {
            println(validPathDisjointSet(6, edges, i, y))
        }
    }
}