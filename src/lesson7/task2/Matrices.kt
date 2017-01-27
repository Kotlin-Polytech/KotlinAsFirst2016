@file:Suppress("UNUSED_PARAMETER")

package lesson7.task2

import lesson7.task1.*
import java.lang.Math.*

// Все задачи в этом файле требуют наличия реализации интерфейса "Матрица" в Matrix.kt

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
    val result = createMatrix(height, width, 0)
    var cursor = Cell(0, 0)
    result[cursor] = 1
    var j = 1
    for (i in 0..(min(height, width) / 2) + 1) {
        while (cursor.column + 2 <= width && result[Cell(cursor.row, cursor.column + 1)] == 0) {
            j++
            cursor = Cell(cursor.row, cursor.column + 1)
            result[cursor] = j
        }
        while (cursor.row + 2 <= height && result[Cell(cursor.row + 1, cursor.column)] == 0) {
            j++
            cursor = Cell(cursor.row + 1, cursor.column)
            result[cursor] = j
        }
        while (cursor.column >= 1 && result[Cell(cursor.row, cursor.column - 1)] == 0) {
            j++
            cursor = Cell(cursor.row, cursor.column - 1)
            result[cursor] = j
        }
        while (cursor.row >= 1 && result[Cell(cursor.row - 1, cursor.column)] == 0) {
            j++
            cursor = Cell(cursor.row - 1, cursor.column)
            result[cursor] = j
        }
    }
    return result
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
    val result = createMatrix(height, width, 0)
    var cursor = Cell(0, 0)
    result[cursor] = 1
    var j = 1
    for (i in 0..(min(height, width) / 2) + 1) {
        while (cursor.column + 2 <= width && result[Cell(cursor.row, cursor.column + 1)] == 0) {
            cursor = Cell(cursor.row, cursor.column + 1)
            result[cursor] = j
        }
        while (cursor.row + 2 <= height && result[Cell(cursor.row + 1, cursor.column)] == 0) {
            cursor = Cell(cursor.row + 1, cursor.column)
            result[cursor] = j
        }
        while (cursor.column >= 1 && result[Cell(cursor.row, cursor.column - 1)] == 0) {
            cursor = Cell(cursor.row, cursor.column - 1)
            result[cursor] = j
        }
        while (cursor.row >= 1 && result[Cell(cursor.row - 1, cursor.column)] == 0) {
            cursor = Cell(cursor.row - 1, cursor.column)
            result[cursor] = j
        }
        j++
    }
    return result
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
fun generateSnake(height: Int, width: Int): Matrix<Int> {
    val result = createMatrix(height, width, 0)
    var cursor = Cell(0, 0)
    var temp = cursor
    result[cursor] = 1
    var j = 2
    while (cursor.column + 2 <= width) {
        for (i in 1..width - 1) {
            cursor = Cell(cursor.row, cursor.column + 1)
            temp = cursor
            while (temp.row <= height - 1 && temp.column >= 0) {
                result[temp] = j
                j++
                temp = Cell(temp.row + 1, temp.column - 1)
            }
        }
    }
    while (cursor.row + 2 <= height) {
        for (i in 1..height - 1) {
            cursor = Cell(cursor.row + 1, cursor.column)
            temp = cursor
            while (temp.row <= height - 1 && temp.column >= 0) {
                result[temp] = j
                j++
                temp = Cell(temp.row + 1, temp.column - 1)
            }
        }
    }
    return result
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
    if (matrix.height != matrix.width) throw IllegalArgumentException()
    val result = createMatrix(matrix.width, matrix.height, matrix[0, 0])
    for (i in 0..matrix.height - 1)
        for (j in 0..matrix.width - 1)
            result[i, j] = matrix[matrix.height - 1 - j, i]
    return result
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
    if (matrix.height != matrix.width) return false
    val n = matrix.height
    for (i in 0..n - 1)
        for (j in 0..n - 1) if (matrix[i, j] !in 1..n) return false
    for (i in 0..n - 1) {
        val set = mutableSetOf<Int>()
        for (j in 0..n - 1) {
            if (matrix[i, j] in set) return false
            set.add(matrix[i, j])
        }
    }
    for (i in 0..n - 1) {
        val set = mutableSetOf<Int>()
        for (j in 0..n - 1) {
            if (matrix[j, i] in set) return false
            set.add(matrix[j, i])
        }
    }
    return true
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
    val nullMatrix = createMatrix(matrix.height + 2, matrix.width + 2, 0)
    val result = createMatrix(matrix.height, matrix.width, 0)
    if (matrix.height * matrix.width == 1) return result
    for (i in 0..matrix.height - 1)
        for (j in 0..matrix.width - 1) nullMatrix[i + 1, j + 1] = matrix[i, j]
    for (i in 1..matrix.height)
        for (j in 1..matrix.width) result[i - 1, j - 1] = nullMatrix[i + 1, j + 1] + nullMatrix[i + 1, j] + nullMatrix[i, j + 1] +
                nullMatrix[i - 1, j - 1] + nullMatrix[i - 1, j + 1] + nullMatrix[i + 1, j - 1] + nullMatrix[i - 1, j] + nullMatrix[i, j - 1]
    return result
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
fun findHoles(matrix: Matrix<Int>): Holes {
    val set = mutableSetOf<Int>()
    val rows = mutableListOf<Int>()
    val colums = mutableListOf<Int>()
    for (i in 0..matrix.height - 1)
        for (j in 0..matrix.width - 1) {
            if (matrix[i, j] != 0) break
            if (j == matrix.width - 1) rows.add(i)
        }
    for (j in 0..matrix.width - 1)
        for (i in 0..matrix.height - 1) {
            if (matrix[i, j] != 0) break
            if (i == matrix.height - 1) colums.add(j)
        }
    return Holes(rows, colums)
}


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
    val result = createMatrix(matrix.height, matrix.width, 0)
    for (row in 0..matrix.height - 1)
        for (column in 0..matrix.width - 1) {
            for (i in 0..row)
                for (j in 0..column)
                    result[row, column] += matrix[i, j]
        }
    return result
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
    if (lock.height < key.height || lock.width < key.width) return Triple(false, 0, 0)
    for (i in 0..lock.height - 1)
        for (j in 0..lock.width - 1)
            if (lock[i, j] !in 0..1) return Triple(false, 0, 0)
    for (i in 0..key.height - 1)
        for (j in 0..key.width - 1)
            if (key[i, j] !in 0..1) return Triple(false, 0, 0)

    for (row in 0..lock.height - key.height)
        for (column in 0..lock.width - key.width) {
            var temp = true
            keyLoop@ for (i in 0..key.height - 1)
                for (j in 0..key.width - 1) {
                    temp = key[i, j] != lock[row + i, column + j]
                    if (!temp) break@keyLoop
                }
            if (temp) return Triple(true, row, column)
        }
    return Triple(false, 0, 0)
}

/**
 * Простая
 *
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> {
    val result = this
    for (i in 0..result.height - 1)
        for (j in 0..result.width - 1)
            result[i, j] = -this[i, j]
    return result
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
    if (this.width != other.height) throw IllegalArgumentException()
    val result = createMatrix(this.height, other.width, 0)
    for (row in 0..this.height - 1)
        for (column in 0..other.width - 1) {
            for (i in 0..this.width - 1)
                result[row, column] += (this[row, i] * other[i, column])
        }
    return result
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
    var cursor = Cell(row = 0, column = 0)
    for (i in 0..matrix.height - 1)
        for (j in 0..matrix.width - 1) {
            if (matrix[i, j] !in 0..matrix.height * matrix.width - 1) throw IllegalStateException()
            if (matrix[i, j] == 0) cursor = Cell(i, j)
        }
    for (move in moves) {
        stepLoop@ for (i in -1..1) {
            for (j in -1..1) {
                if (cursor.row + i != -1 && cursor.row + i != matrix.height && cursor.column + j != -1 && cursor.column + j != matrix.width
                   && move == matrix[cursor.row + i, cursor.column + j] && abs(i + j) != 2 && abs(i + j) != 0) {
                    matrix[cursor] = move
                    cursor = Cell(cursor.row + i, cursor.column + j)
                    matrix[cursor] = 0
                    break@stepLoop
                }
            }
            if (i == 1) throw IllegalStateException()
        }
    }
    return matrix
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

fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> {
    val result = mutableListOf<Int>()
    /**
    поиск в матрице числа и вывод полложения Cell
     */

    fun Int.search(): Cell {
        for (i in 0..3)
            for (j in 0..3)
                if (matrix[i, j] == this) return Cell(i, j)
        throw IllegalArgumentException("fun search")
    }

    /**
    переход клетки 0 в другую кдетку и запись хода клетки в result
    в дальнейшем нет необходимости использовать переменную result
    1  2  3  4             1  2  3  4
    10 7  8  11            10 7  8  11
    12 14 13 6     --->    12 14 13 6
    5  0 9  15             0  5  9  15
     */
    fun Cell.moving(other: Cell) {   //this необходимо присвоить кдетку 0 всегда
        if (matrix[this] != 0) throw IllegalArgumentException("ошибка в использовании fun moving")
        matrix[this] = matrix[other]
        matrix[other] = 0
        result.add(matrix[this])
    }

    /**
     * осуществление одного шага клетки, возврат нуля для следующего шага
     * 1  2  3  4             1  2  3  4
     * 10 7  8  11            10 7  8  11
     * 12 14 13 6     --->    14 13 15 6
     * 5  0  15 9             12 5  0  9
     * если клетка находится в нижнем ряду нужно реализовать переход нуля сверху, при движении по горизонтали,
     * иначе снизу
     * при движении по вертикали нужно реализовать переход нуля слева
     */

    fun Cell.inccorect(): Boolean = this.row !in 0..3 || this.column !in 0..3

    fun step(start: Cell, finish: Cell) {  //finish необходимо присвоить клетку 0 всегда
        if (start.inccorect() || finish.inccorect()) return
        if (matrix[finish] != 0) {
            println( "$matrix error")
            throw IllegalArgumentException("ошибка в использовании fun step (0)")
        }
        if (Math.abs(start.row - finish.row) !in 0..1 || Math.abs(finish.column - start.column) !in 0..1)
            throw IllegalArgumentException("ошибка в использовании fun step")
        if (start == finish) return
        val label = if (start.row == 3) 1 else -1
        when {
            start.row == finish.row -> {
                finish.moving(start)
                start.moving(Cell(start.row - label, start.column))
                Cell(start.row - label, start.column).moving(Cell(finish.row - label, finish.column))
                if (Cell(finish.row - label, finish.column + 1).inccorect()) return
                Cell(finish.row - label, finish.column).moving(Cell(finish.row - label, finish.column + 1))
                Cell(finish.row - label, finish.column + 1).moving(Cell(finish.row, finish.column + 1))
            }
            start.column == finish.column -> {
                finish.moving(start)
                start.moving(Cell(start.row, start.column - 1))
                Cell(start.row, start.column - 1).moving(Cell(finish.row, finish.column - 1))
                if (Cell(finish.row - 1, finish.column - 1).inccorect()) return
                Cell(finish.row, finish.column - 1).moving(Cell(finish.row - 1, finish.column - 1))
                Cell(finish.row - 1, finish.column - 1).moving(Cell(finish.row - 1, finish.column))
            }
            else -> throw IllegalArgumentException("ошибка в использовании fun step")
        }
    }

    fun stepLeft(start: Cell, finish: Cell) {  //finish необходимо присвоить клетку 0 всегда
        if (start.inccorect() || finish.inccorect()) return
        if (matrix[finish] != 0) throw IllegalArgumentException("ошибка в использовании fun step (0)")
        if (Math.abs(start.row - finish.row) !in 0..1 || Math.abs(finish.column - start.column) !in 0..1)
            throw IllegalArgumentException("ошибка в использовании fun step")
        if (start == finish) return
        val label = if (start.row == 3) 1 else -1
        val labelLeft = -1
        finish.moving(start)
        start.moving(Cell(start.row - label, start.column))
        Cell(start.row - label, start.column).moving(Cell(finish.row - label, finish.column))
        if (Cell(finish.row - label, finish.column + labelLeft).inccorect()) return
        Cell(finish.row - label, finish.column).moving(Cell(finish.row - label, finish.column + labelLeft))
        Cell(finish.row - label, finish.column + labelLeft).moving(Cell(finish.row, finish.column + labelLeft))
    }

    /**
     * осуществление перемещения нуля из точки this в точку other, при использовании функции необходимо учитывать, что
     * все точки на пути нуля будут изменять своё положение, перемещени нуля должно сначала идти по вертикали,
     * а потом по горизонтали
     */

    fun Cell.nullMoving(finish: Cell) { //this - клетка с 0
        if (this == finish) return
        if (matrix[this] != 0) throw IllegalArgumentException("ошибка в использовании fun nullMoving")
        var temp = this
        while (temp.row != finish.row) {
            temp.moving(Cell(temp.row + finish.row.compareTo(temp.row), temp.column))
            temp = Cell(temp.row + finish.row.compareTo(temp.row), temp.column)
        }
        while (temp.column != finish.column) {
            temp.moving(Cell(temp.row, temp.column + finish.column.compareTo(temp.column)))
            temp = Cell(temp.row, temp.column + finish.column.compareTo(temp.column))
        }
    }

    /**
     * необходимо осуществить перемещение точки из положения this в положение other
     * чтобы не задевать уже выстроенные точки нужно перемещать точку сначала в самую правую колонку, далее вверх,
     * затем влево
     * необходимо внимательно смотреть за положением нуля
     */

    fun Cell.way(other: Cell) {  //переделать
        val number = matrix[this]
        if (matrix[other] == number) return
        0.search().nullMoving(Cell(3, 3))
        var temp = number.search()

        if (temp.row == other.row && other.column < temp.column){
            Cell(3, 3).nullMoving(Cell(3, temp.column - 1))
            Cell(3, temp.column - 1).nullMoving(Cell(temp.row, temp.column - 1))
            while (matrix[other] != number){
                stepLeft(temp, Cell(temp.row, temp.column - 1))
                temp = Cell(temp.row, temp.column - 1)
            }
            return
        }

        if (temp.column != 3) {
            Cell(3, 3).nullMoving(Cell(temp.row, temp.column + 1))
            for (i in 0..3) {
                step(temp, Cell(temp.row, temp.column + 1))
                temp = Cell(temp.row, temp.column + 1)
            }
            temp = 0.search()
            if (number.search().row < temp.row) {
                temp.moving(Cell(temp.row, temp.column - 1))
                if (temp.row > 1) Cell(temp.row, temp.column - 1).nullMoving(Cell(temp.row - 2, temp.column))
                else Cell(temp.row, temp.column - 1).moving(Cell(temp.row - 1, temp.column - 1))
            } else if (temp == Cell(0, 2)) {
                Cell(0, 2).nullMoving(Cell(1, 3))
            }
        }else {
                Cell(3, 3).moving(Cell(3, 2))
                if (this.row != 0) Cell(3, 2).nullMoving(Cell(temp.row - 1, temp.column))
                else Cell(3, 2).nullMoving(Cell(temp.row, temp.column - 1))
            }

        if (this.row != other.row) {
            temp = number.search()
            while (temp.row != other.row) {
                step(temp, Cell(temp.row - 1, temp.column))
                temp = Cell(temp.row - 1, temp.column)
            }
        } else temp = number.search()
        if (temp.row != 0) {
            Cell(temp.row - 1, 3).moving(Cell(temp.row - 1, 2))
            Cell(temp.row - 1, 2).moving(Cell(temp.row, 2))
        }

        temp = number.search()

        if (matrix[other] == number) return

        for (i in 0..3) {
            stepLeft(temp, Cell(temp.row, temp.column - 1))
            temp = Cell(temp.row, temp.column - 1)
            if (matrix[other] == number) return
        }
    }

    /**
     * осуществление перемещений цифр внутри маленького квадрата, до попадания цифры condition в cell.
     * для корректной работы нуль должени находиться внутри квадрата.
     * квадрат строится вправо вниз от cell и имеет стороны a = 2
     */
    fun miniLoop(cell: Cell, condition: Int) {
        0.search().nullMoving(Cell(cell.row, cell.column + 1))
        while (matrix[cell] != condition) {
            Cell(cell.row, cell.column + 1).moving(cell)
            cell.moving(Cell(cell.row + 1, cell.column))
            Cell(cell.row + 1, cell.column).moving(Cell(cell.row + 1, cell.column + 1))
            Cell(cell.row + 1, cell.column + 1).moving(Cell(cell.row, cell.column + 1))
        }
    }
    /**
     * основная часть функции
     * необходимо переместить клетки по своим местам согласно алгоритму решения задачи пятнашки
     * необходимо уделить большее внимание функции way и её использованию, так как все цифры перемещаются в разные
     * положения, а также не должны задевать при перемещении уже выстроенные цифры
     */
    if (matrix[Cell(0, 0)] != 1) 1.search().way(Cell(0, 0))

    if (matrix[Cell(0, 1)] != 2) 2.search().way(Cell(0, 1))

    if (matrix[Cell(0, 2)] != 3 && matrix[Cell(0, 3)] != 4) {
        if (matrix[Cell(0, 3)] != 3) 3.search().way(Cell(0, 3))
        4.search().way(Cell(0, 3))
    }

    if (matrix[1, 0] != 5) 5.search().way(Cell(1, 0))

    if (matrix[Cell(1, 1)] != 6) {
        6.search().way(Cell(1, 1))
    }

    if (matrix[Cell(1, 2)] != 7 && matrix[Cell(1, 3)] != 8) {
        if (matrix[Cell(1, 3)] != 7) 7.search().way(Cell(1, 3))
        if (matrix[Cell(2, 3)] != 8) 8.search().way(Cell(2, 3))

        0.search().nullMoving(Cell(0.search().row, 2))
        0.search().nullMoving(Cell(1, 2))
        Cell(1, 2).moving(Cell(1, 3))
        Cell(1, 3).moving(Cell(2, 3))
    }

    /***************************************************************************************************************
     *
     */

    if (matrix[Cell(2, 0)] != 9 && matrix[Cell(3, 0)] != 13) {
        if (matrix[Cell(2, 3)] != 9) 9.search().way(Cell(2, 3))
        0.search().nullMoving(Cell(3, 2))
        if (matrix[Cell(3, 3)] == 13) {
            Cell(3, 2).moving(Cell(3, 3))
            Cell(3, 3).moving(Cell(2, 3))
            Cell(2, 3).moving(Cell(2, 2))
            miniLoop(Cell(2, 1), 13)
            miniLoop(Cell(2, 2), 9)
        } else {
            if (13.search().column == 0) {
                0.search().nullMoving(Cell(13.search().row, 1))
                Cell(13.search().row, 1).moving(13.search())
                if (13.search().row == 3) {
                    Cell(3, 0).nullMoving(Cell(2, 0))
                    Cell(2, 0).nullMoving(Cell(2, 1))
                } else Cell(2, 0).nullMoving(Cell(3, 1))
            }
            miniLoop(Cell(2, 1), 13)
            Cell(2, 2).moving(Cell(2, 3))
        }
        Cell(2, 3).nullMoving(Cell(3, 0))
        Cell(3, 0).moving(Cell(2, 0))
        Cell(2, 0).nullMoving(Cell(2, 2))
        Cell(2, 2).nullMoving(Cell(3, 0))
        Cell(3, 0).moving(Cell(2, 0))
        Cell(2, 0).moving(Cell(2, 1))
    }


    if (matrix[Cell(2, 1)] != 10 || matrix[Cell(3, 1)] != 14) {
        if (matrix[Cell(2, 3)] != 10) 10.search().way(Cell(2, 3)) ///клон
        0.search().nullMoving(Cell(3, 2))
        if (matrix[Cell(3, 3)] == 14) {
            Cell(3, 2).moving(Cell(3, 3))
            Cell(3, 3).moving(Cell(2, 3))
            Cell(2, 3).moving(Cell(2, 2))
            miniLoop(Cell(2, 1), 14)
            miniLoop(Cell(2, 2), 10)
        } else {
            miniLoop(Cell(2, 1), 14)
            Cell(2, 2).moving(Cell(2, 3))
        }
        Cell(2, 3).nullMoving(Cell(3, 1))
        Cell(3, 1).moving(Cell(2, 1))
        Cell(2, 1).moving(Cell(2, 2))
    }
    miniLoop(Cell(2, 2), 11)
    Cell(2, 3).moving(Cell(3, 3))
    if (matrix[2, 3] != 12) {
        if (matrix[Cell(2, 1)] != 10 || matrix[Cell(3, 1)] != 15) {    ///клон
            if (matrix[Cell(2, 3)] != 10) 10.search().way(Cell(2, 3)) ///клон
            0.search().nullMoving(Cell(3, 2))
            if (matrix[Cell(3, 3)] == 15) {
                Cell(3, 2).moving(Cell(3, 3))
                Cell(3, 3).moving(Cell(2, 3))
                Cell(2, 3).moving(Cell(2, 2))
                miniLoop(Cell(2, 1), 15)
                miniLoop(Cell(2, 2), 10)
            } else {
                miniLoop(Cell(2, 1), 15)
                Cell(2, 2).moving(Cell(2, 3))
            }
            Cell(2, 3).nullMoving(Cell(3, 1))
            Cell(3, 1).moving(Cell(2, 1))
            Cell(2, 1).moving(Cell(2, 2))
        }
        miniLoop(Cell(2, 2), 11)
        Cell(2, 3).moving(Cell(3, 3))
    }
    return result
}



