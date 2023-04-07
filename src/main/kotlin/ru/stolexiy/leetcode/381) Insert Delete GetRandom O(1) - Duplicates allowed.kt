package ru.stolexiy.leetcode

private class RandomizedCollection {

    private val map = mutableMapOf<Int, MutableSet<Int>>()
    private val list = mutableListOf<Int>()
    private var size = 0

    fun insert(`val`: Int): Boolean {
        val ret = !map.containsKey(`val`)
        if (list.size == size) {
            list.add(`val`)
        } else {
            list[size] = `val`
        }
        map.getOrPut(`val`) { mutableSetOf() }.add(size)
        size++
        return ret
    }

    fun remove(`val`: Int): Boolean {
        if (!map.containsKey(`val`))
            return false
        val indices = map[`val`]!!
        if (indices.size == 1)
            map.remove(`val`)
        val idx = indices.first()
        indices.remove(idx)
        if (idx < size - 1) {
            val last = list[size - 1]
            list[idx] = last
            map[last]!!.let {
                it.add(idx)
                it.remove(size - 1)
            }
        }
        size--
        return true
    }

    fun getRandom(): Int {
        return list[(Math.random() * size).toInt()]
    }

}

fun main() {
    val collection = RandomizedCollection().apply {
        insert(1)
        insert(1)
        insert(2)
        insert(1)
        insert(2)
        insert(2)
        remove(1)
        remove(2)
        remove(2)
        remove(2)
    }
    println(collection.getRandom())
}