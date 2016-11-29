@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)


/**
 *  Matrix
 *
 *
 *         7 # # # # # # # #
 *         6 # # # # # # # #
 *  row    5 # # # # # # # #
 * (0..    4 # # # # # # # #
 * height) 3 # # # # # # # #
 *         2 # # # # # # # #
 *         1 # # # # # # # #
 *         0 # # # # # # # #
 *           0 1 2 3 4 5 6 7
 *           column (0..width)
 */

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if(height <= 0 || width <= 0) throw IllegalArgumentException()
    return MatrixImpl(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private var storage = mutableListOf<MutableList<E>>()

    init {
        for (i in 0..height - 1) {
            val tempStorage = mutableListOf<E>()
            for (j in 0..width - 1) {
                tempStorage.add(e)
            }
            storage.add(tempStorage)
        }
        println()
    }

    override fun get(row: Int, column: Int): E  = storage[row][column]

    override fun get(cell: Cell): E  = storage[cell.row][cell.column]

    override fun set(row: Int, column: Int, value: E) {
        if (row !in 0..height - 1 || column !in 0..width - 1) return
        storage[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        if (cell.row !in 0..height - 1 || cell.column !in 0..width - 1) return
        storage[cell.row][cell.column] = value
    }

    override fun equals(other: Any?): Boolean {
        if (
              other !is MatrixImpl<*> ||
              height != other.height ||
              width != other.width
        ) return false

        for (i in 0..height - 1) {
            for (j in 0..width - 1) {
               if (storage[i][j] != other.storage[i][j]) return false
            }
        }
        return true
    }

    override fun hashCode(): Int {
        var res = 9
        for (elem in storage) {
            res = res * 23 + elem.hashCode()
        }
        return res
    }

    override fun toString(): String {
        val stringStorage = StringBuilder()

        stringStorage.append("Matrix($height * $width) (\n")
        for (i in 0..height - 1) {
            stringStorage.append("    ")
            for (j in 0..width - 1) {
                stringStorage.append("[${storage[i][j]}]")
                if (j != width - 1) stringStorage.append(",")
            }
            stringStorage.append("\n")
        }
        stringStorage.append(")")

        return stringStorage.toString()
    }
}

