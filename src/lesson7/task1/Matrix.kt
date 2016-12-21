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
    if ((height < 1) || (width < 1)) {
        throw IllegalArgumentException()
    }
    val result = MatrixImpl(height, width, e)
    for (i in 0..height-1){
        for (j in 0..width-1){
            result[i, j] = e
        }
    }
    return result
}

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {
    private val matrixMap = mutableMapOf<Cell, E>()

    override fun get(row: Int, column: Int): E  {
        val getResult = matrixMap[Cell(row, column)]
        if (getResult == null) {
            throw IllegalArgumentException()
        }
        else {
            return getResult
        }
    }

    override fun get(cell: Cell) = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        matrixMap[Cell(row, column)] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) =
            other is MatrixImpl<*> &&
            height == other.height &&
            width == other.width &&
            matrixMap == other.matrixMap

    override fun hashCode(): Int{
        var result = height
        result = 31 * result + width
        result = 31 * result + matrixMap.hashCode()
        return result
    }

    override fun toString(): String {
        val resultString = StringBuilder()
        resultString.append("[")
        for (row in 0..height - 1) {
            resultString.append("[")
            for (column in 0..width - 1) {
                resultString.append(this[row, column])
                resultString.append(", ")
            }
            resultString.append("], ")
        }
        resultString.append("]")
        return resultString.toString()
    }
}

