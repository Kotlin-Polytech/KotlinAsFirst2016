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
    if ((height <= 0) || (width <= 0)) throw IllegalArgumentException()
    return MatrixImpl(height,width,e)
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */

class MatrixImpl<E> (override val height: Int, override val width: Int, e : E) : Matrix<E> {

    private val list = mutableListOf<E>()

    init {
        for (i in 0..height*width - 1){
            list.add(e)
        }
    }

    override fun get(row: Int, column: Int): E = list[row*width + column]

    override fun get(cell: Cell): E = list[cell.row*width + cell.column]

    override fun set(row: Int, column: Int, value: E) {
        list[row*width + column] = value
    }

    override fun set(cell: Cell, value: E) {
        list[cell.row*width + cell.column] = value
    }

    override fun equals(other: Any?) =
            other is MatrixImpl<*> && list == other.list &&
                    height == other.height &&
                    width == other.width

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (row in 0..height - 1) {
            sb.append("[")
            if (row < height - 1) sb.append(",", " ")
            for (column in 0..width - 1) {
                sb.append(this[row, column])
                if (column + 1 < width) sb.append(",", " ")
            }
            sb.append("]")
        }
        sb.append("]")
        return "$sb"
    }
        override fun hashCode(): Int {
            var result = 5
            result = result * 31 + height
            result = result * 31 + width
            result = 31 * result + list.hashCode()
            return result
    }
}



