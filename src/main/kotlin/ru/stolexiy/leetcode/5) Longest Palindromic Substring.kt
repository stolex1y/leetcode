package ru.stolexiy.leetcode

private fun longestPalindrome(s: String): String {
    if (s.isEmpty())
        return ""
    var maxLen = 1
    var max = 0 to 0
    for (i in s.indices) {
        val len1 = maxPalindrome(s, i, i)
        val len2 = maxPalindrome(s, i, i + 1)
        if (len2 > maxLen && len2 > len1) {
            maxLen = len2
            max = i - (len2 - 1) / 2 to i + 1 + (len2 - 1) / 2
        } else if (len1 > maxLen && len1 > len2) {
            maxLen = len1
            max = i - len1 / 2 to i + len1 / 2
        }
    }
    return s.substring(max.first, max.second + 1)
}

private fun maxPalindrome(s: String, center1: Int, center2: Int): Int {
    var left = center1
    var right = center2
    while (left >= 0 && right < s.length && s[left] == s[right]) {
        left--
        right++
    }
    return right - left - 1
}


private fun longestPalindrome2(s: String): String {
    if (s.isEmpty())
        return ""
    val isPalindrome = MutableList(2) { i -> MutableList(s.length) { i == 1 } }
    var maxLen = 1
    var max = 0 to 0
    for (len in 2 .. s.length) {
        for (start in 0 .. s.length - len) {
            val end = start + len - 1
            isPalindrome[len % 2][start] = if (len == 2)
                s[start] == s[end]
            else if (s[start] == s[end])
                isPalindrome[len % 2][start + 1]
            else
                false

            if (isPalindrome[len % 2][start] && len > maxLen) {
                maxLen = len
                max = start to end
            }
        }
    }
    return s.substring(max.first, max.second + 1)
}

private fun longestPalindrome3(s: String): String {
    if (s.isEmpty())
        return ""
    val isPalindrome = MutableList(s.length) { i ->
        MutableList(s.length) { y ->
            i == y
        }
    }
    var maxLen = 1
    var max = 0 to 0
    for (len in 2 .. s.length) {
        for (start in 0 .. s.length - len) {
            val end = start + len - 1
            isPalindrome[start][end] = if (len == 2)
                s[start] == s[end]
            else if (s[start] == s[end])
                isPalindrome[start + 1][end - 1]
            else
                false

            if (isPalindrome[start][end] && len > maxLen) {
                maxLen = len
                max = start to end
            }
        }
    }
    return s.substring(max.first, max.second + 1)
}

fun main() {
    val str = "babadadabab"
    println(longestPalindrome(str))
}