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
class MatrixImpl<E>// convert'нул в primary constructor по подсказке IDE
(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    val cells = mutableMapOf<Cell, E>()

    fun checker(cell: Cell): Boolean = cell.row in 0..height - 1 && cell.column in 0..width - 1
    override fun get(row: Int, column: Int): E {
        if (!checker(Cell(row, column))) throw IllegalArgumentException()
        return cells[Cell(row, column)]!!
    }

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        if (!checker(Cell(row, column))) throw IllegalArgumentException()
        cells.put(Cell(row, column), value)
    }

    override fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)

    override fun equals(other: Any?) = other is MatrixImpl<*> && cells == other.cells

    override fun toString(): String {
        val builder = StringBuilder()
        for (i in 0..height - 1) {
            for (j in 0..width - 1) {
                builder.append("\t${cells[Cell(i, j)]}")
            }
            builder.appendln()
        }
        return builder.toString()
    }

    override fun hashCode(): Int {
        var result = cells.hashCode()
        result = 31 * result + height
        result = 31 * result + width
        return result
    }

    init {
        for (i in 0..height - 1) {
            for (j in 0..width - 1) {
                cells.put(Cell(i, j), e)
            }
        }
    }
}

