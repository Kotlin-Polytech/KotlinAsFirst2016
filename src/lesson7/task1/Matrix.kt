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


    val Cell.exists: Boolean
        get() = ((this.row <= height-1) && (this.column <= width-1))

    override fun get(row: Int, column: Int): E {
            return get(Cell(row, column))
    }

    override fun get(cell: Cell): E {
        return map[cell]!!
    }

    override fun set(cell: Cell, value: E) {
            map[cell] = value
    }

    override fun set(row: Int, column: Int, value: E) {
        set(Cell(row,column), value)
    }

    override fun equals(other: Any?): Boolean = other is MatrixImpl<*> && height == other.height && width == other.width && map == other.map

    override fun hashCode(): Int {
        var result = 5
        result = result * 31 + width
        result = result * 31 + height
        result += map.hashCode()
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

