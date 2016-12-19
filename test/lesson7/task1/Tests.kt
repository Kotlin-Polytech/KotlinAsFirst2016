package lesson7.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Easy")
    fun createMatrix() {
        val matrix = createMatrix(4, 6, 0.0)
        assertEquals(4, matrix.height)
        assertEquals(6, matrix.width)
    }

    @Test
    @Tag("Normal")
    fun getSetInt() {
        val matrix = createMatrix(3, 2, 0)
        var value = 0
        for (row in 0..matrix.height - 1) {
            for (column in 0..matrix.width - 1) {
                matrix[row, column] = value++
            }
        }
        value = 0
        for (row in 0..matrix.height - 1) {
            for (column in 0..matrix.width - 1) {
                assertEquals(value++, matrix[Cell(row, column)])
            }
        }
    }

    @Test
    @Tag("Normal")
    fun getSetString() {
        val matrix = createMatrix(2, 2, "")
        val strings = listOf("alpha", "beta", "gamma", "omega")
        var index = 0
        for (row in 0..matrix.height - 1) {
            for (column in 0..matrix.width - 1) {
                matrix[Cell(row, column)] = strings[index++]
            }
        }
        index = 0
        for (row in 0..matrix.height - 1) {
            for (column in 0..matrix.width - 1) {
                assertEquals(strings[index++], matrix[row, column])
            }
        }
    }

    @Test
    fun equals() {
        assertEquals(true, createMatrix(1, 1, listOf(listOf(3))) == createMatrix(1, 1, listOf(listOf(3))))
        assertEquals(true, createMatrix(2, 2, listOf(listOf(1, 2), listOf(2, 2))) == createMatrix(2, 2, listOf(listOf(1, 2), listOf(2, 2))))
        assertEquals(true, createMatrix(2, 2, listOf(listOf(1, 1), listOf(2, 2))) == createMatrix(1, 1, listOf(listOf(1, 2), listOf(2, 2))))
    }
}