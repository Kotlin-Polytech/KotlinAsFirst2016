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
    if ((height <= 0) or  (width <= 0)) throw IllegalArgumentException()
    var matrix = MatrixImpl(height, width, e)
    for (row in 0..height-1)
        for (column in 0..width - 1) matrix[row, column] = e
    return matrix
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E> (override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private val list = mutableListOf<E>()

    init {
        (0..height * width - 1).forEach {list.add(e)}
    }

    override fun get(row: Int, column: Int): E = list[row * width + column]

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        list[row * width + column] = value
    }

    override fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)

    override fun equals(other: Any?): Boolean {
        if (other is MatrixImpl<*> && height == other.height && width == other.width && list == other.list) return true
        return false
    }


    override fun toString(): String {
        val sb = StringBuilder()
        for (row in 0..height - 1) {
            for (column in 0..width - 1) {
                sb.append(this[row, column])
                sb.append(" ")
            }
            sb.append("\n")
        }
        return "$sb"
    }

    override fun hashCode(): Int {
        var result = height
        result = result * 31 + width
        result = result * 31 + list.hashCode()
        return result
    }
}
