@file:Suppress("UNUSED_PARAMETER")
package lesson7.task2

import lesson7.task1.Cell
import lesson7.task1.Matrix
import lesson7.task1.createMatrix
import java.lang.Math.*
import java.util.*

// Все задачи в этом файле требуют наличия реализации интерфейса "Матрица" в Matrix.kt

//Extensions of Cell data class
// По-нормальноу это должен быть статический параметр класса, но са класс Cell я менять не могу
val NOT_EXISTS = Cell(-1, -1)

fun Cell.near(other: Cell): Boolean {
    if (this == other) throw IllegalArgumentException("Cells are the same")
    else return ((this.row - other.row) == 0 && abs(this.column - other.column) == 1) ||
            (abs(this.row - other.row) == 1 && this.column - other.column == 0)
}

fun createFilledMatrix(height: Int, width: Int, values: List<List<Int>>): Matrix<Int> {
    val matrix = createMatrix(height, width, values[0][0])
    for (row in 0..height - 1) {
        for (column in 0..width - 1) {
            matrix[row, column] = values[row][column]
        }
    }
    return matrix
}

//Extensions of Matrix class
fun <E> Matrix<E>.getRow(row: Int): List<E> =
        if (row in 0..height - 1) (0..width - 1).map { this[row, it] } else throw IllegalArgumentException("Index out of bounds: $row")

fun <E> Matrix<E>.getColumn(column: Int): List<E> =
        if (column in 0..width - 1) (0..height - 1).map { this[it, column] } else throw IllegalArgumentException("Index out of bounds: $column")

fun <E> Matrix<E>.contains(row: Int, column: Int): Boolean = row in 0..this.height - 1 && column in 0..this.width - 1

fun <E> Matrix<E>.contains(cell: Cell): Boolean = this.contains(cell.row, cell.column)

fun <E> Matrix<E>.subMatrix(height: Int, width: Int, heightShift: Int, widthShift: Int): Matrix<E> {
    if (this.height < height + heightShift || this.width < width + widthShift)
        throw IllegalArgumentException("Sub Matrix is out of bounds of original")
    else {
        val m = createMatrix(height, width, this[0,0])
        for (i in 0..height - 1)
            for (j in 0..width - 1)
                m[i,j] = this[i + heightShift, j + widthShift]
        return m
    }
}

fun <E> Matrix<E>.indexOf(element: E): Cell {
    for (i in 0..height - 1)
        for (j in 0..width - 1)
            if (this[i,j] == element)
                return Cell(i, j)
    return NOT_EXISTS
}

fun <E> Matrix<E>.swap(first: Cell, second: Cell) {
    if (this.contains(first) && this.contains(second)) {
        val tmp = this[first]
        this[first] = this[second]
        this[second] = tmp
    } else throw IllegalArgumentException("No suck Cells in Matrix")
}
fun <E> Matrix<E>.findMoves(cell: Cell): List<Cell> {
    val list: MutableList<Cell> = mutableListOf()
    (-1..0).forEach {
        if (this.contains(cell.row + it, cell.column + it + 1)) list.add(Cell(cell.row + it, cell.column + it + 1))
        if (this.contains(cell.row + it + 1, cell.column + it)) list.add(Cell(cell.row + it + 1, cell.column + it))
    }
    return list
}
fun <E> Matrix<E>.copyN(): Matrix<E> {
    val m = createMatrix(height, width, this[0,0])
    for (i in 0..height - 1)
        for (j in 0..width - 1)
            m[i,j] = this[i,j]
    return m
}
/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 * При транспонировании строки матрицы становятся столбцами и наоборот:
 *
 * 1 2 3      1 4 6 3
 * 4 5 6  ==> 2 5 5 2
 * 6 5 4      3 6 4 1
 * 3 2 1
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0..matrix.width - 1) {
        for (j in 0..matrix.height - 1) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}

/**
 * Пример
 *
 * Сложить две заданные матрицы друг с другом.
 * Складывать можно только матрицы совпадающего размера -- в противном случае бросить IllegalArgumentException.
 * При сложении попарно складываются соответствующие элементы матриц
 */
operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    if (width != other.width || height != other.height) throw IllegalArgumentException()
    if (width < 1 || height < 1) return this
    val result = createMatrix(height, width, this[0, 0])
    for (i in 0..height - 1) {
        for (j in 0..width - 1) {
            result[i, j] = this[i, j] + other[i, j]
        }
    }
    return result
}

/**
 * Сложная
 *
 * Заполнить матрицу заданной высоты height и ширины width
 * натуральными числами от 1 до m*n по спирали,
 * начинающейся в левом верхнем углу и закрученной по часовой стрелке.
 *
 * Пример для height = 3, width = 4:
 *  1  2  3  4
 * 10 11 12  5
 *  9  8  7  6
 */
fun generateSpiral(height: Int, width: Int): Matrix<Int> {
    val m = createMatrix(height, width, 0)
    //Создаем лист с максимальными значениями каждого "кольца", считаем по формуле, а потом проходимся по нему функцией,
    //аналогичной accumulate (для матрицы 4х4 получим значения 0, 12, 16)
    val squareEndValue: MutableList<Int> = mutableListOf(0)
    (1..ceil(min(width, height) / 2.0).toInt()).forEach {
        val space = 2 * ((width + height) - (2 + 4 * (it - 1)))
        squareEndValue.add((if (space != 0) space else 1) + if (it > 1) squareEndValue[it-1] else 0)
    }
    //Проходимся по каждому "row" матрицы и считаем значения
    for (i in 0..height-1) {
        for (j in 0..width-1) {
            //Значения обратные i,j соответсвенно
            val revertI = height-i-1
            val revertJ = width-j-1
            //Находим, в каком "кольце" находится ячейка и от этого рассчиываем ее значение
            val inside = min(if (i >= (height/2.0)) revertI else i, if (j >= (width/2.0)) revertJ else j)
            //Правая верхняя часть матрицы
            if (i == inside || revertJ == inside) {
                m[i,j] = squareEndValue[inside] + ((width - revertJ + height - revertI) - (2 * inside) - 1)
            //Левая нижняя часть матрицы
            } else {
                m[i,j] = squareEndValue[inside+1] - (width - revertJ + height - revertI - (2 * inside) - 3)
            }

        }
    }
    return m
}

/**
 * Сложная
 *
 * Заполнить матрицу заданной высоты height и ширины width следующим образом.
 * Элементам, находящимся на периферии (по периметру матрицы), присвоить значение 1;
 * периметру оставшейся подматрицы – значение 2 и так далее до заполнения всей матрицы.
 *
 * Пример для height = 5, width = 6:
 *  1  1  1  1  1  1
 *  1  2  2  2  2  1
 *  1  2  3  3  2  1
 *  1  2  2  2  2  1
 *  1  1  1  1  1  1
 */
fun generateRectangles(height: Int, width: Int): Matrix<Int> {
    val m = createMatrix(height, width, 0)
    for (i in 0..height-1) {
        for (j in 0..width-1) {
            val inside = min(if (i >= (height/2.0)) height-i-1 else i, if (j >= (width/2.0)) width-j-1 else j)
            m[i,j] = inside+1
        }
    }
    return m
}

/**
 * Сложная
 *
 * Заполнить матрицу заданной высоты height и ширины width диагональной змейкой:
 * в левый верхний угол 1, во вторую от угла диагональ 2 и 3 сверху вниз, в третью 4-6 сверху вниз и так далее.
 *
 * Пример для height = 5, width = 4:
 *  1  2  4  7
 *  3  5  8 11
 *  6  9 12 15
 * 10 13 16 18
 * 14 17 19 20
 */
fun generateSnake(height: Int, width: Int): Matrix<Int>  {
    val m = createMatrix(height, width, 0)
    for (i in 0..height - 1)
        for (j in 0..width - 1)
            m[i,j] = 1 + if (i > 0 && (j < width - 1 || j == 0)) m[i - 1,j + min(width - 1,1)]
                    else if (j > 0) m[i,j - 1] + min(j - 1, height - 1 - i) else 0
    return m
}

/**
 * Средняя
 *
 * Содержимое квадратной матрицы matrix (с произвольным содержимым) повернуть на 90 градусов по часовой стрелке.
 * Если height != width, бросить IllegalArgumentException.
 *
 * Пример:    Станет:
 * 1 2 3      7 4 1
 * 4 5 6      8 5 2
 * 7 8 9      9 6 3
 */
fun <E> rotate(matrix: Matrix<E>): Matrix<E> {
    if (matrix.height != matrix.width) throw IllegalAccessException("Height and width isn't equals")
    val m = createMatrix(matrix.width, matrix.height, matrix[0,0])
    for (i in 0..matrix.height - 1) {
        for (j in 0..matrix.width - 1)
            m[i, j] = matrix[matrix.width-j-1, i]
    }
    return m
}

/**
 * Сложная
 *
 * Проверить, является ли квадратная целочисленная матрица matrix латинским квадратом.
 * Латинским квадратом называется матрица размером n x n,
 * каждая строка и каждый столбец которой содержат все числа от 1 до n.
 * Если height != width, вернуть false.
 *
 * Пример латинского квадрата 3х3:
 * 2 3 1
 * 1 2 3
 * 3 1 2
 */
fun isLatinSquare(matrix: Matrix<Int>): Boolean {
    if (matrix.width == matrix.height) {
        val list = (1..matrix.width).toList()
        (0..matrix.height - 1).forEach {
            if (matrix.getRow(it).sorted() != list || matrix.getColumn(it).sorted() != list) return false
        }
        return true
    } else return false
}
/**
 * Средняя
 *
 * В матрице matrix каждый элемент заменить суммой непосредственно примыкающих к нему
 * элементов по вертикали, горизонтали и диагоналям.
 *
 * Пример для матрицы 4 x 3: (11=2+4+5, 19=1+3+4+5+6, ...)
 * 1 2 3       11 19 13
 * 4 5 6  ===> 19 31 19
 * 6 5 4       19 31 19
 * 3 2 1       13 19 11
 *
 * Поскольку в матрице 1 х 1 примыкающие элементы отсутствуют,
 * для неё следует вернуть как результат нулевую матрицу:
 *
 * 42 ===> 0
 */
fun sumNeighbours(matrix: Matrix<Int>): Matrix<Int> {
    val m = createMatrix(matrix.height, matrix.width, 0)
    for (i in 0..matrix.height - 1)
        for (j in 0..matrix.width - 1) {
                m[i,j] = -matrix[i,j]
                for (k in -1..1)
                    for (l in -1..1)
                        if (m.contains(i + k, j + l))
                            m[i,j] += matrix[i + k, j + l]
            }
    return m
}

/**
 * Средняя
 *
 * Целочисленная матрица matrix состоит из "дырок" (на их месте стоит 0) и "кирпичей" (на их месте стоит 1).
 * Найти в этой матрице все ряды и колонки, целиком состоящие из "дырок".
 * Результат вернуть в виде Holes(rows = список дырчатых рядов, columns = список дырчатых колонок).
 * Ряды и колонки нумеруются с нуля. Любой из спискоов rows / columns может оказаться пустым.
 *
 * Пример для матрицы 5 х 4:
 * 1 0 1 0
 * 0 0 1 0
 * 1 0 0 0 ==> результат: Holes(rows = listOf(4), columns = listOf(1, 3)): 4-й ряд, 1-я и 3-я колонки
 * 0 0 1 0
 * 0 0 0 0
 */
fun findHoles(matrix: Matrix<Int>): Holes =
    Holes((0..matrix.height - 1).filter { matrix.getRow(it).sum() == 0 }, (0..matrix.width - 1).filter { matrix.getColumn(it).sum() == 0 })


/**
 * Класс для описания местонахождения "дырок" в матрице
 */
data class Holes(val rows: List<Int>, val columns: List<Int>)

/**
 * Средняя
 *
 * В целочисленной матрице matrix каждый элемент заменить суммой элементов подматрицы,
 * расположенной в левом верхнем углу матрицы matrix и ограниченной справа-снизу данным элементом.
 *
 * Пример для матрицы 3 х 3:
 *
 * 1  2  3      1  3  6
 * 4  5  6  =>  5 12 21
 * 7  8  9     12 27 45
 *
 * К примеру, центральный элемент 12 = 1 + 2 + 4 + 5, элемент в левом нижнем углу 12 = 1 + 4 + 7 и так далее.
 */
fun sumSubMatrix(matrix: Matrix<Int>): Matrix<Int> {
    val m = createMatrix(matrix.height, matrix.width, 0)
    for (i in 0..matrix.height - 1)
        for (j in 0..matrix.width - 1) {
            m[i,j] = 0
            for (k in -i..0)
                for (l in -j..0)
                    if (m.contains(i + k, j + l))
                        m[i,j] += matrix[i + k, j + l]
        }
    return m
}

/**
 * Сложная
 *
 * Даны мозаичные изображения замочной скважины и ключа. Пройдет ли ключ в скважину?
 * То есть даны две матрицы key и lock, key.height <= lock.height, key.width <= lock.width, состоящие из нулей и единиц.
 *
 * Проверить, можно ли наложить матрицу key на матрицу lock (без поворота, разрешается только сдвиг) так,
 * чтобы каждой единице в матрице lock (штырь) соответствовал ноль в матрице key (прорезь),
 * а каждому нулю в матрице lock (дырка) соответствовала, наоборот, единица в матрице key (штырь).
 * Ключ при сдвиге не может выходить за пределы замка.
 *
 * Пример: ключ подойдёт, если его сдвинуть на 1 по ширине
 * lock    key
 * 1 0 1   1 0
 * 0 1 0   0 1
 * 1 1 1
 *
 * Вернуть тройку (Triple) -- (да/нет, требуемый сдвиг по высоте, требуемый сдвиг по ширине).
 * Если наложение невозможно, то первый элемент тройки "нет" и сдвиги могут быть любыми.
 */
fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> {
    if (key.height > lock.height || key.width > lock.width)
        return Triple(false, 0, 0)
    else {
        val unlocked = createMatrix(key.height, key.width, 1)
        for (i in 0..lock.height - key.height)
            for (j in 0..lock.width - key.width)
                if (lock.subMatrix(key.height, key.width, i, j) + key == unlocked)
                    return Triple(true, i, j)
        return Triple(false, 0, 0)
    }
}

/**
 * Простая
 *
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> {
    for (i in 0..height - 1)
        for (j in 0..width - 1)
            this[i,j] *= -1
    return this
}

/**
 * Средняя
 *
 * Перемножить две заданные матрицы друг с другом.
 * Матрицы можно умножать, только если ширина первой матрицы совпадает с высотой второй матрицы.
 * В противном случае бросить IllegalArgumentException.
 * Подробно про порядок умножения см. статью Википедии "Умножение матриц".
 */
operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> {
    if (this.width != other.height) throw IllegalArgumentException("Matrixes can't be multiplied")
    val matrixes = if (this.width > other.width) Pair(this, other) else Pair(other, this)
    val min = matrixes.first.height
    val m = createMatrix(min, min, 0)
    for (i in 0..min - 1)
        for (j in 0..min - 1)
            m[i, j] = matrixes.first.getRow(i).zip(matrixes.second.getColumn(j), { R, T -> R * T }).sum()
    return m
}

/**
 * Сложная
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  1
 *  2 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой. Цель игры -- упорядочить фишки на игровом поле.
 *
 * В списке moves задана последовательность ходов, например [8, 6, 13, 11, 10, 3].
 * Ход задаётся номером фишки, которая передвигается на пустое место (то есть, меняется местами с нулём).
 * Фишка должна примыкать к пустому месту по горизонтали или вертикали, иначе ход не будет возможным.
 * Все номера должны быть в пределах от 1 до 15.
 * Определить финальную позицию после выполнения всех ходов и вернуть её.
 * Если какой-либо ход является невозможным или список содержит неверные номера,
 * бросить IllegalStateException.
 *
 * В данном случае должно получиться
 * 5  7  9  1
 * 2 12 14 15
 * 0  4 13  6
 * 3 10 11  8
 */
fun fifteenGameMoves(matrix: Matrix<Int>, moves: List<Int>): Matrix<Int> {
    val m = matrix
    var zeroCell: Cell = m.indexOf(0)
    moves.forEach {
        val moveCell = m.indexOf(it)
        if (moveCell != NOT_EXISTS && moveCell.near(zeroCell)) {
            m.swap(zeroCell, moveCell)
            zeroCell = moveCell
        } else {
            throw IllegalStateException("Such turn is impossible or number isn't exists")
        }
    }
    return m
}

/**
 * Очень сложная
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  2
 *  1 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой.
 *
 * Цель игры -- упорядочить фишки на игровом поле, приведя позицию к одному из следующих двух состояний:
 *
 *  1  2  3  4          1  2  3  4
 *  5  6  7  8   ИЛИ    5  6  7  8
 *  9 10 11 12          9 10 11 12
 * 13 14 15  0         13 15 14  0
 *
 * Можно математически доказать, что РОВНО ОДНО из этих двух состояний достижимо из любой исходной позиции.
 *
 * Вернуть решение -- список ходов, приводящих исходную позицию к одной из двух упорядоченных.
 * Каждый ход -- это перемена мест фишки с заданным номером с пустой клеткой (0),
 * при этом заданная фишка должна по горизонтали или по вертикали примыкать к пустой клетке (но НЕ по диагонали).
 * К примеру, ход 13 в исходной позиции меняет местами 13 и 0, а ход 11 в той же позиции невозможен.
 *
 * Одно из решений исходной позиции:
 *
 * [8, 6, 14, 12, 4, 11, 13, 14, 12, 4,
 * 7, 5, 1, 3, 11, 7, 3, 11, 7, 12, 6,
 * 15, 4, 9, 2, 4, 9, 3, 5, 2, 3, 9,
 * 15, 8, 14, 13, 12, 7, 11, 5, 7, 6,
 * 9, 15, 8, 14, 13, 9, 15, 7, 6, 12,
 * 9, 13, 14, 15, 12, 11, 10, 9, 13, 14,
 * 15, 12, 11, 10, 9, 13, 14, 15]
 *
 * Перед решением этой задачи НЕОБХОДИМО решить предыдущую
 */
val solution1 = createFilledMatrix(4,4, listOf(listOf(1, 2, 3, 4),
        listOf(5, 6, 7, 8),
        listOf(9, 10, 11, 12),
        listOf(13, 14, 15, 0)))
val solution2 = createFilledMatrix(4,4, listOf(listOf(1, 2, 3, 4),
        listOf(5, 6, 7, 8),
        listOf(9, 10, 11, 12),
        listOf(13, 15, 14, 0)))

class State(val matrix: Matrix<Int>, val moved: Int?)  {

    object companion {
        var START = createMatrix(4,4,0)
            set(value) {
                field = value
                solutionChoose()
            }
        var SOLUTION = createMatrix(4,4,0)
        val ZERO_MATRIX = createMatrix(4,4,0)

        private fun solutionChoose() {
            var N = 0
            val zeroRow = START.indexOf(0).row + 1
            val list = mutableListOf<Int>()
            for (i in 0..3)
                list += START.getRow(i)
            for (i in 0..15) {
                if (list[i] == 0) continue
                for (j in i + 1..15) {
                    if (list[i] > list[j] && list[j] != 0)
                        N++
                }
            }
            N += zeroRow
            SOLUTION = if (N % 2 == 0) solution1 else solution2
            println("Solution found: $SOLUTION")
        }
    }

    fun getAllMoves(): List<State> {
        val moves = mutableListOf<State>()
        val zeroPos = this.matrix.indexOf(0)
        for(move in this.matrix.findMoves(zeroPos)) {
            val step = this.matrix.copyN()
            step.swap(zeroPos, move)
            moves.add(State(step, this.matrix[move]))
        }
        return moves
    }

    fun getCellsMoves(): List<Cell> = this.matrix.findMoves(this.matrix.indexOf(0))

    fun solved(): Boolean = this.matrix == State.companion.SOLUTION

    fun estimate(): Int {
        var manhattan = 0
        for (row in 0..3) {
            for (column in 0..3) {
                val value = State.companion.SOLUTION.indexOf(matrix[row, column])
                manhattan += abs(row - value.row) + abs(column - value.column)
            }
        }
        return manhattan + linearConflict()
    }

    fun linearConflict(): Int {
        var conflicts = 0
        for (row in 0..3) {
            for (column in 1..3) {
                for (cell in 0..column - 1) {
                    val K = matrix[row, column]
                    val J = matrix[row, cell]
                    val TileKRow = K / 4 == row || (K / 4 == row + 1 && K % 4 == 0) //Goal-позиция тайла K в этом ряду?
                    val TileJRow = J / 4 == row || (J / 4 == row + 1 && J % 4 == 0)
                    if (TileJRow && TileKRow && J > K)
                        conflicts += 2
                }
            }
        }
        return conflicts
    }

}

val visited = HashMap<Matrix<Int>, Int>()
val moves = mutableListOf<Int>()
var finished = 0

fun limitedSearch(state: State, limit: Int, path: Int): Boolean {
    var found = false
    for (move in state.getCellsMoves()) {
        val zero = state.matrix.indexOf(0)
        state.matrix.swap(zero, move)
        if (state.solved()) {
            println("Found")
            found = true
        } else {

            if (!visited.contains(state.matrix.copyN())) {
                if (state.estimate() + path + 1 > limit) {
                    visited.put(state.matrix.copyN(), state.matrix[zero])
                    finished++
                }
                else {
                    visited.put(state.matrix.copyN(), state.matrix[zero])
                    val solution = limitedSearch(state, limit, path + 1)
                    if (solution)
                        found = true
                }
            }
        }
        state.matrix.swap(zero, move)
        if (found) {
            moves.add(state.matrix[move])
            return true
        }
    }
    finished++
    return false
}

fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> {

    State.companion.START = matrix

    var count = 0

    val state = State(matrix, null)
    var limit = state.estimate()
    var solved = false

    if (state.matrix == State.companion.SOLUTION)
        return listOf()

    while (!solved) {

        visited.put(state.matrix.copyN(), 0)
        val solution = limitedSearch(state, limit, 0)
        if (solution) {
            solved = true
            return moves.reversed()
        }
        if (count % 2 == 0)
            println("$count searches are complete, pathes = $finished, current depth = $limit, visited = ${visited.size}")
        limit += 2
        visited.clear()
        moves.clear()
        count++
    }


    // Попытка A* + поиск в шиину
    /*
    val visited = HashMap<State, State>()
    val depth = HashMap<State, Int>()
    val comparator = Comparator<State>() {R,T -> (depth[R] ?: 0 + R.estimate()) - (depth[T] ?: 0 + T.estimate())}
    val queue = PriorityQueue<State>(10000,comparator)
    val start = State(matrix, null)
    visited.put(start, State(State.companion.ZERO_MATRIX, null))
    depth.put(start, 0)
    queue.add(start)

    var count = 0
    while (queue.isNotEmpty()) {
        val currentState = queue.remove()

        if(count % 10000 == 0) {
            println("Considered $count positions. Queue = ${queue.size}")
        }

        if (currentState.solved()) {
            println("Solution found on $count iteration")
            val steps = mutableListOf<Int>()
            var backtrace = currentState
            while (backtrace.moved != null) {
                steps.add(backtrace.moved ?: -1)
                backtrace = visited[backtrace]
            }
            println(steps)
            return steps.reversed()
        }
        for (move in currentState.getAllMoves()) {
            if (!visited.containsKey(move)) {
                visited.put(move, currentState)
                depth.put(move, (depth[currentState] ?: 0) + 1)
                queue.add(move)
            }
        }
        count++
    }
    */

    return listOf()
}
