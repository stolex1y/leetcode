package ru.stolexiy.leetcode

private fun maxDistToClosest(seats: IntArray): Int {
    var max = -1
    var count = 0
    var lastPerson = -1
    for (i in seats.indices) {
        if (seats[i] == 1) {
            val temp = if (lastPerson == -1)
                count
            else
                (count + 1) / 2
            if (temp > max) {
                max = temp
            }
            count = 0
            lastPerson = i
        } else {
            count++
        }
    }
    if (count > max) {
        max = count
    }
    return max
}

