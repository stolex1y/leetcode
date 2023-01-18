package ru.stolexiy

import java.util.ArrayDeque

private typealias GridNode = Pair<Int, Int>

private fun numIslands(grid: Array<CharArray>): Int {
    if (grid.isEmpty() || grid.first().isEmpty())
        return 0
    val notVisited = mutableSetOf<GridNode>()
    grid.forEachIndexed { ri, r ->
        r.forEachIndexed { ci, ch ->
            if (ch == '1')
                notVisited.add(ri to ci)
        }
    }
    var result = 0
    while (notVisited.isNotEmpty()) {
        val cur = notVisited.first()
        bfs(cur, grid, notVisited)
        result++
    }
    return result
}

private fun bfs(start: GridNode, grid: Array<CharArray>, notVisited: MutableSet<GridNode>) {
    val queue = ArrayDeque<GridNode>()
    queue.push(start)
    while (queue.isNotEmpty()) {
        val cur = queue.pop()
        for (dir in GridDirection.values()) {
            getNeighbour(cur, grid, dir)?.let {
                if (notVisited.contains(it))
                    queue.push(it)
            }
        }
        notVisited.remove(cur)
    }
}

private fun getNeighbour(node: GridNode, grid: Array<CharArray>, dir: GridDirection): GridNode? {
    val neighbour = getNodeByDir(node, dir)
    if (grid.getOrNull(neighbour.first)?.getOrNull(neighbour.second) == '1')
        return neighbour
    else
        return null
}

private fun getNodeByDir(node: GridNode, dir: GridDirection): GridNode {
    return GridNode(node.first + dir.dy, node.second + dir.dx)
}

private enum class GridDirection(val dy: Int, val dx: Int) {
    LEFT(0, -1),
    RIGHT(0, 1),
    UP(1, 0),
    DOWN(-1, 0);
}