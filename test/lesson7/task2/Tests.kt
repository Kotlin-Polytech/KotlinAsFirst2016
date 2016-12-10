package lesson7.task2

import lesson7.task1.Matrix
import lesson7.task1.createMatrix
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    private fun <E> createMatrix(height: Int, width: Int, values: List<List<E>>): Matrix<E> {
        val matrix = createMatrix<E>(height, width, values[0][0])
        for (row in 0..height - 1) {
            for (column in 0..width - 1) {
                matrix[row, column] = values[row][column]
            }
        }
        return matrix
    }

    @Test
    @Tag("Example")
    fun transpose() {
        assertEquals(createMatrix(1, 1, listOf(listOf(1))), transpose(createMatrix(1, 1, listOf(listOf(1)))))
        assertEquals(createMatrix(3, 4, listOf(listOf(1, 4, 6, 3), listOf(2, 5, 5, 2), listOf(3, 6, 4, 1))),
                transpose(createMatrix(4, 3, listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(6, 5, 4), listOf(3, 2, 1)))))
    }

    @Test
    @Tag("Example")
    fun plus() {
        assertEquals(createMatrix(1, 1, listOf(listOf(3))),
                createMatrix(1, 1, listOf(listOf(1))) + createMatrix(1, 1, listOf(listOf(2))))
        assertEquals(createMatrix(2, 2, listOf(listOf(2, 5), listOf(8, 11))),
                createMatrix(2, 2, listOf(listOf(1, 2), listOf(3, 4))) + createMatrix(2, 2, listOf(listOf(1, 3), listOf(5, 7))))
    }

    @Test
    @Tag("Hard")
    fun generateSpiral() {
        assertEquals(createMatrix(1, 1, listOf(listOf(1))), generateSpiral(1, 1))
        assertEquals(createMatrix(2, 2,
                listOf(
                        listOf(1, 2),
                        listOf(4, 3)
                )), generateSpiral(2, 2))
        assertEquals(createMatrix(3, 4,
                listOf(
                        listOf(1, 2, 3, 4),
                        listOf(10, 11, 12, 5),
                        listOf(9, 8, 7, 6)
                )), generateSpiral(3, 4))
        assertEquals(createMatrix(23, 9,
                listOf(
                        listOf(1, 2, 3, 4, 5, 6, 7, 8, 9),
                        listOf(60, 61, 62, 63, 64, 65, 66, 67, 10),
                        listOf(59, 112, 113, 114, 115, 116, 117, 68, 11),
                        listOf(58, 111, 156, 157, 158, 159, 118, 69, 12),
                        listOf(57, 110, 155, 192, 193, 160, 119, 70, 13),
                        listOf(56, 109, 154, 191, 194, 161, 120, 71, 14),
                        listOf(55, 108, 153, 190, 195, 162, 121, 72, 15),
                        listOf(54, 107, 152, 189, 196, 163, 122, 73, 16),
                        listOf(53, 106, 151, 188, 197, 164, 123, 74, 17),
                        listOf(52, 105, 150, 187, 198, 165, 124, 75, 18),
                        listOf(51, 104, 149, 186, 199, 166, 125, 76, 19),
                        listOf(50, 103, 148, 185, 200, 167, 126, 77, 20),
                        listOf(49, 102, 147, 184, 201, 168, 127, 78, 21),
                        listOf(48, 101, 146, 183, 202, 169, 128, 79, 22),
                        listOf(47, 100, 145, 182, 203, 170, 129, 80, 23),
                        listOf(46, 99, 144, 181, 204, 171, 130, 81, 24),
                        listOf(45, 98, 143, 180, 205, 172, 131, 82, 25),
                        listOf(44, 97, 142, 179, 206, 173, 132, 83, 26),
                        listOf(43, 96, 141, 178, 207, 174, 133, 84, 27),
                        listOf(42, 95, 140, 177, 176, 175, 134, 85, 28),
                        listOf(41, 94, 139, 138, 137, 136, 135, 86, 29),
                        listOf(40, 93, 92, 91, 90, 89, 88, 87, 30),
                        listOf(39, 38, 37, 36, 35, 34, 33, 32, 31)
                )), generateSpiral(23, 9))
    }

    @Test
    @Tag("Hard")
    fun generateRectangles() {
        assertEquals(createMatrix(1, 1, listOf(listOf(1))), generateRectangles(1, 1))
        assertEquals(createMatrix(2, 2,
                listOf(
                        listOf(1, 1),
                        listOf(1, 1)
                )), generateRectangles(2, 2))
        assertEquals(createMatrix(4, 3,
                listOf(
                        listOf(1, 1, 1),
                        listOf(1, 2, 1),
                        listOf(1, 2, 1),
                        listOf(1, 1, 1)
                )), generateRectangles(4, 3))
        assertEquals(createMatrix(5, 6,
                listOf(
                        listOf(1, 1, 1, 1, 1, 1),
                        listOf(1, 2, 2, 2, 2, 1),
                        listOf(1, 2, 3, 3, 2, 1),
                        listOf(1, 2, 2, 2, 2, 1),
                        listOf(1, 1, 1, 1, 1, 1)
                )), generateRectangles(5, 6))

    }

    @Test
    @Tag("Hard")
    fun generateSnake() {
        assertEquals(createMatrix(1, 1, listOf(listOf(1))), generateSnake(1, 1))
        assertEquals(createMatrix(2, 2,
                listOf(
                        listOf(1, 2),
                        listOf(3, 4)
                )), generateSnake(2, 2))
        assertEquals(createMatrix(4, 2,
                listOf(
                        listOf(1, 2),
                        listOf(3, 4),
                        listOf(5, 6),
                        listOf(7, 8)
                )), generateSnake(4, 2))
        assertEquals(createMatrix(5, 4,
                listOf(
                        listOf(1, 2, 4, 7),
                        listOf(3, 5, 8, 11),
                        listOf(6, 9, 12, 15),
                        listOf(10, 13, 16, 18),
                        listOf(14, 17, 19, 20)
                )), generateSnake(5, 4))

    }

    @Test
    @Tag("Normal")
    fun rotate() {
        val m = createMatrix(1, 1, listOf(listOf(("single"))))
        assertEquals(m, rotate(m))
        assertEquals(createMatrix(2, 2, listOf(listOf("alpha", "beta"),
                listOf("gamma", "delta"))),
                rotate(createMatrix(2, 2, listOf(listOf("beta", "delta"),
                        listOf("alpha", "gamma")))))
        assertEquals(createMatrix(3, 3, listOf(listOf(7, 4, 1), listOf(8, 5, 2), listOf(9, 6, 3))),
                rotate(createMatrix(3, 3, listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)))))
    }

    @Test
    @Tag("Hard")
    fun isLatinSquare() {
        assertTrue(isLatinSquare(createMatrix(1, 1, listOf(listOf(1)))))
        assertFalse(isLatinSquare(createMatrix(1, 1, listOf(listOf(2)))))
        assertTrue(isLatinSquare(createMatrix(2, 2, listOf(listOf(1, 2), listOf(2, 1)))))
        assertFalse(isLatinSquare(createMatrix(2, 2, listOf(listOf(1, 2), listOf(1, 2)))))
        assertTrue(isLatinSquare(createMatrix(3, 3, listOf(listOf(2, 3, 1), listOf(1, 2, 3), listOf(3, 1, 2)))))
        assertFalse(isLatinSquare(createMatrix(3, 3, listOf(listOf(2, 3, 1), listOf(1, 2, 3), listOf(3, 1, 4)))))
        assertFalse(isLatinSquare(createMatrix(3, 3, listOf(listOf(2, 3, 1), listOf(1, 2, 3), listOf(1, 3, 2)))))
        assertFalse(isLatinSquare(createMatrix(3, 3, listOf(listOf(2, 3, 0), listOf(1, 2, 3), listOf(3, 1, 2)))))
    }

    @Test
    @Tag("Normal")
    fun sumNeighbours() {
        assertEquals(createMatrix(1, 1, listOf(listOf(0))), sumNeighbours(createMatrix(1, 1, listOf(listOf(42)))))
        assertEquals(createMatrix(2, 2, listOf(listOf(9, 8), listOf(7, 6))),
                sumNeighbours(createMatrix(2, 2, listOf(listOf(1, 2), listOf(3, 4)))))
        assertEquals(createMatrix(4, 3, listOf(listOf(11, 19, 13), listOf(19, 31, 19),
                listOf(19, 31, 19), listOf(13, 19, 11))),
                sumNeighbours(createMatrix(4, 3, listOf(listOf(1, 2, 3), listOf(4, 5, 6),
                        listOf(6, 5, 4), listOf(3, 2, 1)))))
    }

    @Test
    @Tag("Normal")
    fun findHoles() {
        assertEquals(Holes(listOf(), listOf()), findHoles(createMatrix(1, 1, listOf(listOf(1)))))
        assertEquals(Holes(listOf(0), listOf(0)), findHoles(createMatrix(1, 1, listOf(listOf(0)))))
        assertEquals(Holes(listOf(), listOf()), findHoles(createMatrix(2, 2, listOf(listOf(0, 1), listOf(1, 0)))))
        assertEquals(Holes(listOf(), listOf(0)), findHoles(createMatrix(2, 2, listOf(listOf(0, 1), listOf(0, 1)))))
        assertEquals(Holes(listOf(4), listOf(1, 3)),
                findHoles(createMatrix(5, 4, listOf(listOf(1, 0, 1, 0), listOf(0, 0, 1, 0), listOf(1, 0, 0, 0),
                        listOf(0, 0, 1, 0), listOf(0, 0, 0, 0)))))
        assertEquals(Holes(listOf(0, 1, 2, 3, 4, 5, 6, 7), listOf(0, 1, 2, 3, 4, 5)),
                findHoles(createMatrix(8, 6, listOf(listOf(0, 0, 0, 0, 0, 0), listOf(0, 0, 0, 0, 0, 0), listOf(0, 0, 0, 0, 0, 0), listOf(0, 0, 0, 0, 0, 0), listOf(0, 0, 0, 0, 0, 0), listOf(0, 0, 0, 0, 0, 0), listOf(0, 0, 0, 0, 0, 0), listOf(0, 0, 0, 0, 0, 0)))))
    }

    @Test
    @Tag("Normal")
    fun sumSubMatrix() {
        assertEquals(createMatrix(1, 1, listOf(listOf(0))), sumSubMatrix(createMatrix(1, 1, listOf(listOf(0)))))
        assertEquals(createMatrix(1, 5, listOf(listOf(1, 3, 6, 8, 9))),
                sumSubMatrix(createMatrix(1, 5, listOf(listOf(1, 2, 3, 2, 1)))))
        assertEquals(createMatrix(2, 2, listOf(listOf(2, 6), listOf(3, 10))),
                sumSubMatrix(createMatrix(2, 2, listOf(listOf(2, 4), listOf(1, 3)))))
        assertEquals(createMatrix(3, 3, listOf(listOf(1, 3, 6), listOf(5, 12, 21), listOf(12, 27, 45))),
                sumSubMatrix(createMatrix(3, 3, listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9)))))
    }

    @Test
    @Tag("Easy")
    fun unaryMinus() {
        assertEquals(createMatrix(1, 1, listOf(listOf(3))), -createMatrix(1, 1, listOf(listOf(-3))))
        assertEquals(createMatrix(2, 2, listOf(listOf(-1, -2), listOf(-3, -4))),
                -createMatrix(2, 2, listOf(listOf(1, 2), listOf(3, 4))))
        assertEquals(createMatrix(1, 3, listOf(listOf(-4, -6, -8))), -createMatrix(1, 3, listOf(listOf(4, 6, 8))))
    }

    @Test
    @Tag("Normal")
    fun times() {
        assertEquals(createMatrix(1, 1, listOf(listOf(2))),
                createMatrix(1, 1, listOf(listOf(1))) * createMatrix(1, 1, listOf(listOf(2))))
        assertEquals(createMatrix(1, 1, listOf(listOf(19))),
                createMatrix(1, 3, listOf(listOf(2, 3, 5))) * createMatrix(3, 1, listOf(listOf(4), listOf(2), listOf(1))))
        assertEquals(createMatrix(2, 2, listOf(listOf(23, -2), listOf(8, -18))),
                createMatrix(2, 3, listOf(listOf(3, 5, 1), listOf(2, -2, 4))) *
                        createMatrix(3, 2, listOf(listOf(4, 1), listOf(2, 0), listOf(1, -5))))
    }

    @Test
    @Tag("Hard")
    fun canOpenLock() {
        assertFalse(canOpenLock(
                key = createMatrix(1, 1, listOf(listOf(1))),
                lock = createMatrix(1, 1, listOf(listOf(1)))).first)
        assertEquals(Triple(true, 0, 0), canOpenLock(
                key = createMatrix(1, 1, listOf(listOf(1))),
                lock = createMatrix(1, 1, listOf(listOf(0)))))
        assertEquals(Triple(true, 0, 1), canOpenLock(
                key = createMatrix(1, 1, listOf(listOf(0))),
                lock = createMatrix(2, 2, listOf(listOf(0,1), listOf(0,0)))))
        assertEquals(Triple(true, 0, 1), canOpenLock(
                key = createMatrix(2, 2, listOf(listOf(1, 0), listOf(0, 1))),
                lock = createMatrix(3, 3, listOf(listOf(1, 0, 1), listOf(0, 1, 0), listOf(1, 1, 1)))))
        assertFalse(canOpenLock(
                key = createMatrix(2, 2, listOf(listOf(1, 1), listOf(1, 0))),
                lock = createMatrix(3, 3, listOf(listOf(1, 0, 1), listOf(0, 1, 0), listOf(1, 1, 1)))).first)
        assertEquals(Triple(true, 0, 0), canOpenLock(
                key = createMatrix(8, 13, listOf(
                        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
                        listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1))),
                lock = createMatrix(10, 20, listOf(
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
                        listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                        ))))

    }

    @Test
    @Tag("Hard")
    fun fifteenGameMoves() {
        val start = createMatrix(4, 4, listOf(listOf(1, 2, 3, 4), listOf(5, 6, 7, 8),
                listOf(9, 10, 11, 12), listOf(13, 14, 15, 0)))
        assertEquals(start, fifteenGameMoves(start, listOf()))
        assertEquals(start, fifteenGameMoves(createMatrix(4, 4, listOf(listOf(1, 2, 3, 4), listOf(5, 6, 7, 8),
                listOf(9, 10, 11, 12), listOf(0, 13, 14, 15))),
                listOf(13, 14, 15)))
        assertEquals(start, fifteenGameMoves(createMatrix(4, 4, listOf(listOf(1, 2, 3, 0), listOf(5, 6, 7, 4),
                listOf(9, 10, 11, 8), listOf(13, 14, 15, 12))),
                listOf(4, 8, 12)))
        assertEquals(createMatrix(4, 4, listOf(listOf(5, 7, 9, 1), listOf(2, 12, 14, 15),
                listOf(0, 4, 13, 6), listOf(3, 10, 11, 8))),
                fifteenGameMoves(createMatrix(4, 4, listOf(listOf(5, 7, 9, 1), listOf(2, 12, 14, 15),
                        listOf(3, 4, 6, 8), listOf(10, 11, 13, 0))),
                        listOf(8, 6, 13, 11, 10, 3)))
        try {
            fifteenGameMoves(start, listOf(1))
            assert(false) { "Exception expected" }
        } catch (e: IllegalStateException) {
        } catch (e: Throwable) {
            assert(false) { "IllegalStateException expected" }
        }
    }

    private fun <T> Matrix<T>.copy(): Matrix<T> {
        val result = lesson7.task1.createMatrix(height, width, this[0, 0])
        for (row in 0..height - 1) {
            for (column in 0..width - 1) {
                result[row, column] = this[row, column]
            }
        }
        return result
    }

    private fun Matrix<Int>.assertSolution() {
        val copy = copy()
        val moves = fifteenGameSolution(this)
        val start1 = createMatrix(4, 4, listOf(listOf(1, 2, 3, 4), listOf(5, 6, 7, 8),
                listOf(9, 10, 11, 12), listOf(13, 14, 15, 0)))
        val start2 = createMatrix(4, 4, listOf(listOf(1, 2, 3, 4), listOf(5, 6, 7, 8),
                listOf(9, 10, 11, 12), listOf(13, 15, 14, 0)))
        val result = fifteenGameMoves(copy, moves)
        assertTrue(result == start1 || result == start2) { "Result position is not a solution position: $result" }
    }

    @Test
    @Tag("Impossible")
    fun fifteenGameSolution() {
        createMatrix(4, 4, listOf(listOf(1, 2, 3, 4), listOf(5, 6, 7, 8),
                listOf(9, 10, 11, 12), listOf(13, 14, 15, 0))).assertSolution()
        createMatrix(4, 4, listOf(listOf(1, 2, 3, 4), listOf(5, 6, 7, 8),
                listOf(9, 10, 11, 12), listOf(13, 15, 14, 0))).assertSolution()
        createMatrix(4, 4, listOf(listOf(5, 7, 9, 2), listOf(1, 12, 14, 15),
                listOf(3, 4, 6, 8), listOf(10, 11, 13, 0))).assertSolution()
        createMatrix(4, 4, listOf(listOf(0, 1, 2, 3), listOf(4, 5, 6, 7),
                listOf(8, 9, 10, 11), listOf(12, 13, 14, 15))).assertSolution()
    }
}