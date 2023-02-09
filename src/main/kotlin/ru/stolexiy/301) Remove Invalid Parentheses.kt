package ru.stolexiy

private object Solution301 {
    fun removeInvalidParentheses(s: String): List<String> {
        val (openToRemove, closeToRemove) = needToRemove(s)
        return mutableSetOf<String>().apply { removeInvalidParentheses(s, openToRemove = openToRemove, closeToRemove = closeToRemove, resultSet = this) }.toList()
    }

    fun removeInvalidParentheses(
        s: String,
        start: Int = 0,
        temp: StringBuilder = StringBuilder(),
        openToRemove: Int,
        closeToRemove: Int,
        openCount: Int = 0,
        closeCount: Int = 0,
        resultSet: MutableSet<String>
    ) {
        val startTempLength = temp.length
        var _openCount = openCount
        var _closeCount = closeCount
        for (i in start until s.length) {
            val ch = s[i]
            when (ch) {
                '(' -> {
                    if (openToRemove > 0) {
                        removeInvalidParentheses(s, i + 1, temp, openToRemove - 1, closeToRemove, _openCount, _closeCount, resultSet)
                    }
                    _openCount++
                }
                ')' -> {
                    if (closeToRemove > 0) {
                        removeInvalidParentheses(s, i + 1, temp, openToRemove, closeToRemove - 1, _openCount, _closeCount, resultSet)
                    }
                    _closeCount++
                }
            }
            if (_closeCount > _openCount) {
                temp.delete(startTempLength, temp.length)
                return
            }
            temp.append(ch)
        }
        if (openToRemove == 0 && closeToRemove == 0 && _openCount == _closeCount) {
            temp.toString().run(resultSet::add)
        }
        temp.delete(startTempLength, temp.length)
    }

    private fun needToRemove(s: String): Pair<Int, Int> {
        var removeOpen = 0
        var removeClose = 0
        s.forEach {
            if (it == '(')
                removeOpen++
            else if (it == ')') {
                if (removeOpen > 0)
                    removeOpen--
                else
                    removeClose++
            }
        }
        return removeOpen to removeClose
    }
}