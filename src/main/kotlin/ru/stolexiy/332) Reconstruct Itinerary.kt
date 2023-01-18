package ru.stolexiy

import java.util.*

private fun findItinerary(tickets: List<List<String>>): List<String> {
    val adjLists = mutableMapOf<String, LinkedList<String>>()
    tickets.forEach { ticket ->
        adjLists.getOrPut(ticket.first()) { LinkedList() }
            .add(ticket.last())
    }
    adjLists.values.forEach { it.sort() }
    val resultPath = LinkedList<String>()
    dfs("JFK", adjLists, resultPath)
    return resultPath
}

private fun dfs(start: String, adjLists: Map<String, Deque<String>>, path: Deque<String>) {
    adjLists[start]?.let { adjList ->
        while (adjList.isNotEmpty()) {
            adjList.removeFirst().let { dfs(it, adjLists, path) }
        }
    }
    path.addFirst(start)
}