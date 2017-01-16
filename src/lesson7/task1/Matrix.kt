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
    operator fun get(cell: Cell): E = get(cell.row, cell.column)

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (width <= 0 || height <= 0) throw IllegalArgumentException()
    return MatrixImpl(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E> (override val height: Int, override val width: Int, e: E): Matrix<E> {

    private val list = mutableListOf<E>()

    init {
        for (i in 0..height * width - 1) list.add(e)
    }

    override fun get(row: Int, column: Int): E = list[width * row + column]

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        list[width * row + column] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> &&
            height == other.height && width == other.width && list.equals(other.list)

    override fun toString(): String {
        val Sb = StringBuilder()
        Sb.append("[")
        for (row in 0..height - 1) {
            Sb.append("[")
            for (column in 0..width - 1) Sb.append("\t" + this[row, column])
            Sb.append("\t]")
        }
        Sb.append("]")
        return "$Sb"
    }

    override fun hashCode(): Int{
        var result = height
        result = 31 * result + width
        result = 31 * result + list.hashCode()
        return result
    }
}

