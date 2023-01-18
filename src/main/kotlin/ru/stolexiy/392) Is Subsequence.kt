package ru.stolexiy

private fun isSubsequence(s: String, t: String): Boolean {
    if (s == "")
        return true
    var cur = 0
    for (i in t.indices) {
        if (s[cur] == t[i]) {
            cur++
            if (cur >= s.length)
                return true
        }
    }
    return false
}