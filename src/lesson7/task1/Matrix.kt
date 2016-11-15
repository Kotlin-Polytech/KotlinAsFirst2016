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
fun <E> createMatrix(height: Int, width: Int, e: E): MatrixImpl<E> {
    if (height <= 0 || width <= 0) throw IllegalArgumentException()
    return MatrixImpl(height, width, e)
}


/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private val list = mutableListOf<E>()

    init {
        for (i in 0..height * width - 1) {
            list.add(e)
        }
    }

    fun check(row: Int, column: Int) {
        if (row < 0 || column < 0 || row >= height || column >= width)
            throw IllegalArgumentException("Input error - bad index")
    }

    override fun get(row: Int, column: Int): E {
        check(row, column)
        return list[width * row + column]
    }

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        check(row, column)
        list[width * row + column] = value
    }

    override fun set(cell: Cell, value: E) = set(cell.row, cell.column, value)

    override fun equals(other: Any?) = other is MatrixImpl<*> &&
            height == other.height && width == other.width && list.equals(other.list)

    override fun hashCode(): Int {
        var result = 13
        result = result * 31 + height
        result = result * 31 + width
        return result
    }

    override fun toString(): String {
        val str = StringBuilder()
        for (i in 0..height-1) {
            for (j in 0..width-1) {
                str.append ("\t" + this[i,j])
            }
            str.appendln()
        }
        return str.toString()
    }
}

fun main(args: Array<String>) {
    val a = createMatrix(4, 4, 13)
    println(a)
}


