package ru.stolexiy

fun containsDuplicate(nums: IntArray): Boolean {
    return nums.distinct().size != nums.size
}

fun containsDuplicateHash(nums: IntArray): Boolean {
    val map = HashSet<Int>()
    nums.forEach {
        if (map.contains(it))
            return true
        else
            map += it
    }
    return false
}

fun main() {
    val intArr = readln().split(",").map(String::toInt).toIntArray()
    assert(containsDuplicate(intArr) == containsDuplicateHash(intArr))
}