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
    if (height !in 1..Int.MAX_VALUE || width !in 1..Int.MAX_VALUE) throw IllegalArgumentException("Attempting to create matrix with invalid parameters")
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
        for (i in 0..height*width - 1) list.add(e)
    }
    override fun get(row: Int, column: Int): E  {
        if (row > height || column > width || row < 0 || column < 0) throw IllegalArgumentException("height or row addressing out of bounds")
        return list[width*(row) + column]
    }

    override fun get(cell: Cell): E  {
        if (cell.row > height || cell.column > width || cell.row < 0 || cell.column < 0) throw IllegalArgumentException("cell height or row addressing out of bounds")
        return list[width*cell.row + cell.column]
    }

    override fun set(row: Int, column: Int, value: E) {
        if (row > height || column > width || row < 0 || column < 0) throw IllegalArgumentException("attempting to set value at out of bounds address")
        list[width*row + column] = value
    }

    override fun set(cell: Cell, value: E) {
        if (cell.row > height || cell.column > width || cell.row < 0 || cell.column < 0) throw IllegalArgumentException("attempting to set cell at out of bounds address")
        list[width*cell.row + cell.column] = value
    }

    override fun equals(other: Any?): Boolean {
        return (other is MatrixImpl<*> && height == other.height && width == other.width && list == other.list)
    }

    override fun toString(): String {
        return list.toString()
    }

    override fun hashCode(): Int {
        var result = height
        result = 31 * result + width
        result = 31 * result + list.hashCode()
        return result
    }
}

