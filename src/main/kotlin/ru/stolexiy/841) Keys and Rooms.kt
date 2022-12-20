package ru.stolexiy

fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
    var visited = 0
    val visits = BooleanArray(rooms.size) { false }
    val queue = ArrayDeque<Int>()
    queue += 0
    while (queue.isNotEmpty()) {
        val room = queue.removeFirst()
        if (visits[room])
            continue
        visits[room] = true
        visited++
        rooms[room].forEach {
            queue.addLast(it)
        }
    }
    return visited == rooms.size
}

fun main() {
    val rooms = listOf(
        intArrayOf(1),
        intArrayOf(2),
        intArrayOf(3),
        intArrayOf()
    ).map { it.toList() }
    println(canVisitAllRooms(rooms))
}
