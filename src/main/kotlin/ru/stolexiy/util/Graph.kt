package ru.stolexiy.util

import java.lang.IllegalArgumentException
import java.util.*

object GraphUtils {
    data class Graph(
        val nodes: List<Node>,
        val adjLists: List<List<Edge>>
    ) {
        class Node(
            var id: Int,
        ) {
            var discoveryTime: Int = 0
            var finishTime: Int = 0
            var prev: Node? = null
        }
        
        data class Edge(
            val node1: Node,
            val node2: Node,
            val length: Int,
        ) {
            fun getOtherNode(node: Node): Node {
                return when (node) {
                    node1 -> node2
                    node2 -> node1
                    else -> throw IllegalArgumentException()
                }
            }
        }
    }

    fun makeAdjacencyLists(citiesCount: Int, edges: List<Graph.Edge>): List<List<Graph.Edge>> {
        val adjLists = List<MutableList<Graph.Edge>>(citiesCount) { mutableListOf() }
        edges.forEach { edge ->
            adjLists[edge.node1.id].add(edge)
            adjLists[edge.node2.id].add(edge)
        }
        return adjLists
    }


    fun findStronglyConnectedComponents(cities: List<Graph.Node>, adjLists: List<List<Graph.Edge>>): List<Set<Graph.Node>> {
        val lastFinishedIsFirst = dfs(cities, adjLists)
        dfs(lastFinishedIsFirst, adjLists)
        val states = mutableListOf<Set<Graph.Node>>()
        cities.filter { it.prev == null }
            .map { root ->
                val state = mutableSetOf<Graph.Node>().apply {
                    add(root)
                }
                val queue: Queue<Graph.Node> = LinkedList<Graph.Node>().apply {
                    add(root)
                }
                while (queue.isNotEmpty()) {
                    val current = queue.poll()
                    adjLists[current.id].map {
                        it.getOtherNode(current)
                    }.forEach { adjacent ->
                        if (!state.contains(adjacent)) {
                            state.add(adjacent)
                            queue.add(adjacent)
                        }
                    }
                }
                states.add(state)
            }
        return states
    }

    /**
     * @return nodes sorted by finish time in descending order (for SCC)
     */
    fun dfs(cities: List<Graph.Node>, adjLists: List<List<Graph.Edge>>): List<Graph.Node> {
        cities.forEach {
            it.prev = null
        }
        val visits = BooleanArray(cities.size) { false }
        var time = 0
        val sortedByFinishTime = mutableListOf<Graph.Node>()
        cities.forEach { node ->
            if (!visits[node.id]) {
                visits[node.id] = true
                val backstack = Stack<Graph.Node>()
                val queue: Queue<Graph.Node> = LinkedList<Graph.Node>().apply {
                    push(node)
                }
                while (queue.isNotEmpty()) {
                    val current = queue.poll()
                    backstack.push(current)
                    current.discoveryTime = ++time
                    adjLists[current.id].forEach { adjacentEdge ->
                        val adjacent = adjacentEdge.getOtherNode(current)
                        if (!visits[adjacent.id]) {
                            queue.add(adjacent)
                            adjacent.prev = current
                            visits[adjacent.id] = true
                        }
                    }
                }
                while (backstack.isNotEmpty()) {
                    val last = backstack.pop()
                    last.finishTime = ++time
                    sortedByFinishTime.add(last)
                }
            }
        }
        return sortedByFinishTime
    }

}