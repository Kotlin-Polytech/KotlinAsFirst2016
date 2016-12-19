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
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (height <= 0 || width <= 0) throw IllegalArgumentException()
    val result = MatrixImpl<E>(height, width, e)
    return result
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E> : Matrix<E> {
    val cells = mutableMapOf<Cell,E>()
    override val height: Int

    override val width: Int
    constructor(height: Int, width: Int, e: E){
        this.height = height
        this.width =  width
        for (i in 0..height - 1){
            for (j in 0..width - 1){
                cells.put(Cell(i,j),e)
            }
        }
    }
    fun checher(cell: Cell): Boolean = cell.row in 0..height - 1 && cell.column in 0..width - 1
    override fun get(row: Int, column: Int): E {
        if (!checher(Cell(row, column))) throw IllegalArgumentException()
        return cells[Cell(row, column)]!!
    }

    override fun get(cell: Cell): E  = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        if (!checher(Cell(row, column))) throw IllegalArgumentException()
        cells.put(Cell(row,column), value)
    }

    override fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)

    override fun equals(other: Any?) = other is MatrixImpl<*> && cells == other.cells

    override fun toString(): String {
        val stringbuild = StringBuilder()
        for (i in 0..height - 1) {
            for (j in 0..width - 1) {
                stringbuild.append("\t${cells[Cell(i,j)]}")
            }
            stringbuild.appendln()
        }
        return stringbuild.toString()
    }
}

