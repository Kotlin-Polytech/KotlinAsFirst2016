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
    if (height <= 0) throw IllegalArgumentException("Wrong height: $height.")
    if (width <= 0) throw IllegalArgumentException("Wrong width: $width.")
    return MatrixImpl(height, width, e)
}


/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int,
                    override val width: Int,
                    e: E) : Matrix<E> {
    private var matrix: List<MutableList<E>> = listOf()

    init {
        for (i in 0..height - 1) {
            val temp: MutableList<E> = mutableListOf()
            for (j in 0..width - 1) {
                temp.add(e)
            }
            matrix += listOf(temp)
        }
    }

    override fun get(row: Int, column: Int): E = matrix[row][column]

    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        matrix[row][column] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MatrixImpl<*>) return false
        else if (other.height != this.height || other.width != this.width) return false
        else {
            for (i in 0..height - 1) {
                for (j in 0..width - 1) {
                    if (other[i,j] != this[i,j]) return false
                }
            }
            return true
        }
    }

    override fun toString(): String {
        return "[$matrix]"
    }

    override fun hashCode(): Int{
        var result = height
        result = 31 * result + width
        result = 31 * result + matrix.hashCode()
        return result
    }
}

