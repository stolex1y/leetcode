package ru.stolexiy.leetcode

import java.util.*

private fun simplifyPath(path: String): String {
    val dirs = LinkedList<String>()
    var cur = 1
    var dirStart = 1
    while (cur <= path.length) {
        if (cur == path.length || path[cur] == '/') {
            val dir = path.substring(dirStart until cur)
            when (dir) {
                "", "." -> {}
                ".." -> {
                    if (dirs.size > 0)
                        dirs.removeLast()
                }
                else -> dirs.addLast(dir)
            }
            dirStart = cur + 1
        }
        cur++
    }
    return buildPath(dirs)
}

private fun buildPath(dirs: Queue<String>): String {
    val sb = StringBuilder()
    if (dirs.size == 0) {
        sb.append("/")
    } else {
        dirs.forEach { dir ->
            sb.append("/")
            sb.append(dir)
        }
    }
    return sb.toString()
}