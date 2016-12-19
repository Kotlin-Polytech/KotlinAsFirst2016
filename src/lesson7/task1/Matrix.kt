@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

import org.jetbrains.annotations.Mutable

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

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
    if (height > 0 && width > 0) return MatrixImpl(height, width, e)
    else throw IllegalArgumentException("Height or wight is less than 0")
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E> : Matrix<E> {

    private val cellsValue: MutableMap<Cell, E> = mutableMapOf()

    override val height: Int

    override val width: Int

    constructor(height: Int, width: Int, e: E) {
        this.height = height
        this.width = width
        for (i in 0..height - 1) {
            for (j in 0..width - 1) {
                cellsValue.put(Cell(i, j), e)
            }
        }
    }

    fun inside (cell: Cell) : Boolean = cell.row in 0..height - 1 && cell.column in 0..width - 1
    fun inside (row: Int, column: Int) : Boolean = row in 0..height - 1 && column in 0.. width - 1


    override fun get(row: Int, column: Int): E  =
        if (inside(row, column)) {
            cellsValue[Cell(row, column)]!!
        }
        else throw IllegalArgumentException("Cell doesn't exist")


    override fun get(cell: Cell): E  = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        if (inside(row, column)) {
            cellsValue.put(Cell(row, column), value)
        }
        else throw IllegalArgumentException("Cell doesn't exist")
    }

    override fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)

    override fun equals(other: Any?) = other is MatrixImpl<*> && other.cellsValue == this.cellsValue

    override fun toString(): String {
        val string : StringBuilder = StringBuilder()
        for (i in 0..height - 1) {
            for (j in 0..width - 1) {
                string.append("\t${this[i, j]}")
            }
            string.appendln()
        }
        return string.toString()
    }
}

