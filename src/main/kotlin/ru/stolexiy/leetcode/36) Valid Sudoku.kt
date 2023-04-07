package ru.stolexiy.leetcode

import java.util.*


fun isValidSudoku(board: Array<CharArray>): Boolean {
    val set = mutableSetOf<Char>()
    board.forEach { row ->
        set.clear()
        row.forEach { ch ->
            if (ch != '.') {
                if (set.contains(ch))
                    return false
                else
                    set += ch
            }
        }
    }

    for (columnIdx in board.indices) {
        set.clear()
        board.forEach { row ->
            val ch = row[columnIdx]
            if (ch != '.') {
                if (set.contains(ch))
                    return false
                else
                    set += ch
            }
        }
    }

    for (i in board.indices step 3) {
        set.clear()
        for (j in board.first().indices step 3) {
            set.clear()
            for (k in 0 until 9) {
                val ch = board[i + k / 3][j + k % 3]
                if (ch != '.') {
                    if (set.contains(ch))
                        return false
                    else
                        set += ch
                }
            }
        }
    }
    return true

}

fun isValidSudokuStructured(board: Array<CharArray>): Boolean {
    val set = mutableSetOf<SudokuElement>()
    board.forEachIndexed { rowIdx, row ->
        row.forEachIndexed { colIdx, ch ->
            if (ch != '.') {
                val rowElement = SudokuElement(Field.ROW, rowIdx, ch)
                val colElement = SudokuElement(Field.COLUMN, colIdx, ch)
                val sqrElement = SudokuElement(Field.SQUARE, rowIdx / 3 * 3 + colIdx / 3, ch)
                if (set.contains(rowElement) || set.contains(colElement) || set.contains(sqrElement)) {
                    return false
                } else {
                    set += rowElement
                    set += colElement
                    set += sqrElement
                }
            }
        }
    }
    return true
}

data class SudokuElement(val field: Field, val index: Int, val ch: Char)

enum class Field {
    ROW,
    COLUMN,
    SQUARE
}

fun main() {
    val sudokuBoard = mutableListOf<CharArray>()
    Scanner(System.`in`).use {
        while (it.hasNextLine()) {
            val row = it.nextLine().split(",").map(String::first).toCharArray()
            sudokuBoard += row
        }
    }
    println(isValidSudoku(sudokuBoard.toTypedArray()))
}