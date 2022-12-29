package ru.stolexiy

import java.util.*


private class RandomizedSet() {

    private val map = mutableMapOf<Int, Int>()
    private val list = mutableListOf<Int>()
    private var size = 0

    fun insert(`val`: Int): Boolean {
        if (map.containsKey(`val`))
            return false
        if (list.size == size) {
            list.add(`val`)
        } else {
            list[size] = `val`
        }
        map[`val`] = size
        size++
        return true
    }

    fun remove(`val`: Int): Boolean {
        if (!map.containsKey(`val`))
            return false
        val idx = map[`val`]!!
        map.remove(`val`)
        if (idx < size - 1) {
            val last = list[size - 1]
            list[idx] = last
            map[last] = idx
        }
        size--
        return true
    }

    fun getRandom(): Int {
        return list[(Math.random() * size).toInt()]
    }


}

fun main() {
    val set = RandomizedSet()
    set.apply {
        insert(3)
        insert(-2)
        remove(2)
        insert(1)
        insert(-3)
        remove(-2)
        remove(3)
        insert(-1)
        remove(-3)
        insert(-2)
    }
    println(set.insert(-3))
}