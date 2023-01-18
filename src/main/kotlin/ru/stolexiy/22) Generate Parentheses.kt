package ru.stolexiy

private fun generateParenthesis(n: Int): List<String> {
    return mutableListOf<String>().apply { generateParenthesis(n = n, list = this) }
}

private fun generateParenthesis(n: Int, opened: Int = 0, closed: Int = 0, str: String = "", list: MutableList<String>) {
    if (opened == n && closed == n) {
        list += str
        return
    }

    if (opened < n) {
        generateParenthesis(n, opened + 1, closed, "$str(", list)
    }
    if (closed < opened) {
        generateParenthesis(n, opened, closed + 1, "$str)", list)
    }
}

