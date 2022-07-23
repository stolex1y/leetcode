package ru.stolexiy

import java.lang.Integer.max
import kotlin.collections.*

fun maxAreaOfIsland(grid: Array<IntArray>): Int {
    if (grid.isEmpty())
        return 0
    val gridMod = MutableList(grid.size) {
        mutableListOf<Cell>()
    }
    grid.forEachIndexed { r, column ->
        column.forEachIndexed { c, i ->
            gridMod[r].add(c, Cell(i, false, r to c, gridMod))
        }
    }
    var maxSquare = 0
    gridMod.forEach { column ->
        column.forEach { cell ->
            if (!cell.isWater && cell.isNotVisiting)
                maxSquare = max(maxSquare, calcIslandSquare(cell))
            cell.visit()
        }
    }
    return maxSquare
}

private data class Cell(
    val type: Int,
    var isVisited: Boolean,
    val coordinates: Pair<Int, Int>,
    var grid: List<List<Cell>>
) {
    val isWater
        get() = type == 0

    val isNotVisiting
        get() = !isVisited

    fun visit() {
        isVisited = true
    }
}

private fun ArrayDeque<Cell>.addNeighbours(grid: List<List<Cell>>, cell: Cell) {
    Direction.values().forEach {
        val coordinates = cell.coordinates + it.dir
        val neighbour = grid.getOrNull(coordinates.first)?.getOrNull(coordinates.second)
        if (neighbour != null && !neighbour.isWater && neighbour.isNotVisiting)
            addFirst(neighbour)
    }
}

private fun calcIslandSquare(start: Cell): Int {
    if (start.isWater)
        return 0
    val queue = ArrayDeque<Cell>()
    queue += start
    var square = 0
    while (queue.isNotEmpty()) {
        val currentCell = queue.removeFirst()
        if (currentCell.isVisited)
            continue
        currentCell.visit()
        square++
        queue.addNeighbours(start.grid, currentCell)
    }
    return square
}