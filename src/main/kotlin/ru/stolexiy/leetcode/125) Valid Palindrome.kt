package ru.stolexiy.leetcode

private fun isPalindrome(s: String): Boolean {
    var left = findNextAlphanum(s, start = -1)
    var right = findPrevAlphanum(s, start = s.length)
    while (left < right && s[left].equals(s[right], ignoreCase = true)) {
        left = findNextAlphanum(s, left)
        right = findPrevAlphanum(s, right)
    }
    return left >= right
}

private fun findNextAlphanum(s: String, start: Int): Int {
    var i = start + 1
    while (i < s.length && !s[i].isLetter() && !s[i].isDigit()) { i++ }
    return i
}

private fun findPrevAlphanum(s: String, start: Int): Int {
    var i = start - 1
    while (i >= 0 && !s[i].isLetter() && !s[i].isDigit()) { i-- }
    return i
}