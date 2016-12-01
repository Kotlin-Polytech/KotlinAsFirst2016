@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1



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
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> = MatrixImpl(height, width, e)

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E> : Matrix<E> {

    override val height: Int
    override val width: Int

    private val cells: MutableList<MutableList<E>>

    constructor(height: Int, width: Int, e: E) {
        if (height > 0 && width > 0) {
            this.height = height
            this.width = width
        } else
            throw IllegalArgumentException("Index is below 0")

        //Можно проще создать двумерный массив? Если да, то как?
        this.cells = (1..height).map{(1..width).map { e }.toMutableList()}.toMutableList()

    }

    private fun check(row: Int, column: Int) {
        if (height !in 0..this.height && width !in 0..this.width)
            throw IllegalArgumentException("Index out of bounds: [$row;$column]")
    }

    override fun get(row: Int, column: Int): E  {
        check(row,column)
        return this.cells[row][column]
    }

    override fun get(cell: Cell): E  = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        check(row, column)
        this.cells[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }



    override fun equals(other: Any?) = other is MatrixImpl<*> && other.height == this.height &&
        other.width == this.width && other.cells == this.cells

    override fun hashCode(): Int {
        val primal = 7
        var result = 1
        result = result * primal + height
        result = result * primal + width
        for (i in 0..height - 1)
            for (j in 0..width - 1)
                result += (cells[i][j]?.hashCode() ?: 0) * ((i + 1) * width + j + 1)
        return result
    }

    override fun toString(): String {
        val str: StringBuilder = StringBuilder()
        str.appendln()
        cells.forEachIndexed { i, list ->
            str.append("Row $i: ")
            list.forEach {
                str.append("\t$it")
            }
            str.appendln()
        }
        return str.toString()
    }
}

