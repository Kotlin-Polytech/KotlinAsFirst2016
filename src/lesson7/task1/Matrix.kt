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
    if ((width <= 0) || (height <= 0)) throw IllegalArgumentException()
    else return MatrixImpl<E>(height, width, e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int,
                    override val width: Int,
                    e: E) : Matrix<E> {

    private val map = mutableMapOf<Cell, E>()
    init {
        for (i in 0..height - 1) {
            for (z in 0..width - 1) {
                map[Cell(i, z)] = e
            }
        }
    }


    val Cell.exist: Boolean
        get() = ((this.row <= height) && (this.column <= width))
    fun exist(row: Int, column: Int) = ((row <= height) && (column <= width))

    override fun get(row: Int, column: Int): E {
        if (Cell(row, column).exist)
            return map[Cell(row, column)] ?: throw IllegalArgumentException()
        else throw IllegalArgumentException()
    }

    override fun get(cell: Cell): E {
        if (cell.exist)
            return map[cell] ?: throw IllegalArgumentException()
        else throw IllegalArgumentException()
    }

    override fun set(cell: Cell, value: E) {
        if (cell.exist)
            map[cell] = value
        else throw IllegalArgumentException()
    }

    override fun set(row: Int, column: Int, value: E) {
        if (Cell(row, column).exist)
            map[Cell(row, column)] = value
        else throw IllegalArgumentException()
    }

    override fun equals(other: Any?): Boolean = other is MatrixImpl<*> && height == other.height && width == other.width

    override fun hashCode(): Int {
        var result = 5
        result = result * 31 + width
        result = result * 31 + height
        for (i in map)
            result += i.hashCode()
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (row in 0..height - 1) {
            sb.append("[")
            for (column in 0..width - 1) {
                    sb.append(map[Cell(row, column)])
                    if (column != width-1 ) sb.append(",")
            }
            sb.append("]")
        }
        sb.append("]")
        return "$sb"
    }

    /*
    override val height: Int = TODO()

    override val width: Int = TODO()

    override fun get(row: Int, column: Int): E = TODO()

    override fun get(cell: Cell): E = TODO()

    override fun set(row: Int, column: Int, value: E) {
        TODO()
    }

    override fun set(cell: Cell, value: E) {
        TODO()
    }

    override fun equals(other: Any?) = TODO()

    override fun toString(): String = TODO()
    */
}

