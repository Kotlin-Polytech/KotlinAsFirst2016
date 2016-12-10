@file:Suppress("UNUSED_PARAMETER")

package lesson7.task2

import lesson7.task1.Cell
import lesson7.task1.Matrix
import lesson7.task1.createMatrix

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
    val matrix = createMatrix(height, width, 0)
    var row = 0
    var column = 0
    var symbolRow = 0 //Направление движения по вертикали
    var symbolColumn = 1 //Направление движения по горизонтали
    var leftBorder = 0 //Левая граница
    var rightBorder = width - 1 //Правая граница
    var topBorder = 0 //Верхняя граница
    var bottomBorder = height - 1//Нижняя граница

    for (elem in 0..height * width - 1) {
        matrix[row, column] = elem + 1

        if (column == rightBorder && row == topBorder) {
            if (topBorder != bottomBorder - 1) { //Не меняем положение верхней границы, если она сольется с нижней
                topBorder += symbolColumn //Опускаем верхнюю границу
            }
            symbolColumn = 0 //Прекращаем движение по горизонтали
            symbolRow = 1 //Начинаем двигаться по вертикали вниз
        }
        if (row == bottomBorder && column == rightBorder) {
            if (rightBorder != leftBorder + 1) { //Не меняем положение правой границы, если она сольется с левой
                rightBorder -= symbolRow //Сдвигаем правую границу
            }
            symbolRow = 0 //Прекращаем движение по вертикали
            symbolColumn = -1 //Начинаем двигаться по горизонтали влево
        }
        if (column == leftBorder && row == bottomBorder) {
            if (bottomBorder != topBorder + 1) { //Не меняем положение нижней границы, если она сольется с верхней
                bottomBorder += symbolColumn //Поднимаем нижнюю границу
            }
            symbolColumn = 0 //Прекращаем движение по горизонтали
            symbolRow = -1 //Начинаем двигаться по вертикали вверх
        }
        if (row == topBorder && column == leftBorder) {
            if (leftBorder != rightBorder - 1) { //Не меняем положение левой границы, если она сольется с правой
                leftBorder -= symbolRow //Сдвигаем левую границу
            }
            symbolRow = 0 //Прекращаем движение по вертикали
            symbolColumn = 1 //Начинаем двигаться по горизонтали влево
        }
        column += symbolColumn
        row += symbolRow
    }
    return matrix
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
    //Функция для заполнения заданного прямоугольника заданными значениями
    fun aggregate(a: Cell, b: Cell, value: Int, matrix: Matrix<Int>) {
        for (i in a.row..b.row) {
            for (j in a.column..b.column) {
                matrix[i, j] = value
            }
        }
    }

    val matrix = createMatrix(height, width, 1)
    var rowTop = 1
    var rowBottom = height - 2
    var columnLeft = 1
    var columnRight = width - 2
    //Определяем значения от 2 до высоты пополам
    for (n in 2..(Math.round(height / 2.0)).toInt()) {
        //Вызываем функцию для заполнения очередного прямоугольника заданными значениями
        aggregate(Cell(rowTop, columnLeft), Cell(rowBottom, columnRight), n, matrix)
        rowTop++
        rowBottom--
        columnLeft++
        columnRight--
    }
    return matrix
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
    val matrix = createMatrix(height, width, 1)
    var rowTop = 0 //Границе сверху
    var rowBottom = 0 //Границы снизу
    var columnLeft = 0 //Границы слева
    var columnRight = 0 //Граница справа
    var n = 1 //Значения, которые буду добавляться в ячейки матрицы
    do {
        var j = columnRight //Столбец равен правой границе
        for (i in rowTop..rowBottom) { //Заполняем ячейки в цикле от верхней границы до нижней
            matrix[i, j] = n
            n++
            if (j > columnLeft) j-- //Смещаем столбец влево
            //В цикле заполнеям диагональ матрицы от правой верхней границы до левой нижней
        }
        if (rowBottom == height - 1) columnLeft++ //Наращиваем левую границу, если мы заполнили диагональ до нижней границы
        if (columnRight == width - 1) rowTop++ //Наращиваем верхнюю границу, если мы занолнили диагональ до правой границы
        if (columnRight < width - 1) columnRight++ //Наращиваем правую груницу, если еще не дошли до правого края
        if (rowBottom < height - 1) rowBottom++ //Наращиваем нижнюю границу, если еще не дошли до низа
    } while (n <= height * width)
    return matrix
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
    val newMatrix = createMatrix(matrix.height, matrix.width, matrix[0, 0]) //Создаем матрицу
    for (i in 0..matrix.width - 1) {
        var list: List<E> = listOf() //Создаем список
        for (j in matrix.height - 1 downTo 0) {
            list += matrix[j, i] //Добавляем в список элементы матрицы снизу вверху (колонку)
        }
        for (j in 0..matrix.width - 1) {
            newMatrix[i, j] = list[j] //Добавляем колонку как строчку в новую матрицу
        }
    }
    return newMatrix
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
    if (matrix !is Matrix<Int>) return false

    for (i in 0..matrix.width - 1) {
        var listRows = listOf<Int>() //Создаем список для значение по строкам
        var listColumns = listOf<Int>() //Создаем список для значений по столбцам
        for (j in 0..matrix.width - 1) {
            listColumns += matrix[j, i] //Заполняем список значениями столбца
            listRows += matrix[i, j] //Заполняем списо значениями строки
        }
        for (n in 1..matrix.width) {
            //Проверяем одновременно два списка на наличие в них необходимых чисел
            if (n !in listColumns || n !in listRows) return false
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
    if (matrix.width == matrix.height && matrix.width == 1) return createMatrix(1, 1, 0) //Проверяем разамер матрицы (не равен ли он 1х1)
    val matrixNew = createMatrix(matrix.height, matrix.width, 0) //Создаем матрицу
    for (row in 0..matrix.height - 1) {
        for (column in 0..matrix.width - 1) {
            var sum = 0 //Переменная для записи суммы соседей
            if (row > 0) { //Проверяем могут ли быть соседи сверху
                if (column > 0) sum += matrix[row - 1, column - 1] //Сосед слева сверху
                if (column < matrix.width - 1) sum += matrix[row - 1, column + 1] //Сосед справа сверху
                sum += matrix[row - 1, column] //Сосед сверху
            }
            if (row < matrix.height - 1) { //Проверяем могут ли быть соседи снизу
                if (column > 0) sum += matrix[row + 1, column - 1] //Сосед справа снизу
                if (column < matrix.width - 1) sum += matrix[row + 1, column + 1] //Сосед слева снизу
                sum += matrix[row + 1, column] //Сосед снизу
            }
            if (column < matrix.width - 1) sum += matrix[row, column + 1] //Сосед справа
            if (column > 0) sum += matrix[row, column - 1] //Сосед слевв
            matrixNew[row, column] = sum
        }
    }
    return matrixNew
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
    var resultListRows = listOf<Int>()
    var resultListColumns = listOf<Int>()
    for (i in 0..matrix.height - 1) {
        var listRows = listOf<Int>() //Строки
        for (j in 0..matrix.width - 1) {
            listRows += matrix[i, j] //Добавляем очередную ячейку в строку
        }
        if (1 !in listRows) resultListRows += i //Проверяем строку на наличие кирпичей
    }
    for (i in 0..matrix.width - 1) {
        var listColumns = listOf<Int>() //Столбцы
        for (j in 0..matrix.height - 1) {
            listColumns += matrix[j, i] //Добавляем очередную ячейку в столбец
        }
        if (1 !in listColumns) resultListColumns += i //Проверяем столбец на наличие кирпичей
    }
    return Holes(resultListRows, resultListColumns)
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
    //Возможна иная реализация, если запоминать все предыдущие суммы и заисывать их промежуточную матрицу,
    //потом использовать эти значения (стоящие слева-наверху/слева/наверху относительно очередного элемента,
    //сумму для которого мы ищем) не считая заново сумму элементов, которые стоят в левом верхнем прямоугольники?
    val newMatrix = createMatrix(matrix.height, matrix.width, 0)
    for (i in 0..matrix.height - 1) {
        for (j in 0..matrix.width - 1) {
            var sum = 0
            for (k in 0..i) {
                for (l in 0..j) {
                    sum += matrix[k, l]
                }
            }
            newMatrix[i, j] = sum
        }
    }
    return newMatrix
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
    for (i in 0..key.height - 1) {
        for (j in 0..key.width - 1) {
            if (key[i, j] !in 0..1) return Triple(false, 0, 0)
        }
    }
    for (i in 0..lock.height - 1) {
        for (j in 0..lock.width - 1) {
            if (lock[i, j] !in 0..1) return Triple(false, 0, 0)
        }
    }
    //Задаем верхнюю, нижнюю, левую и правую границы
    //Границы задают прямоугольник, в который может поместиться ключ
    var topBorder = -1
    var bottomBorder = key.height - 2
    var leftBorder = -1
    var rightBorder = key.width - 2
    for (i in 0..key.width - 1) {
        if (rightBorder < lock.width - 1) {
            //Смещаемся вправо при каждой итерации
            leftBorder += 1
            rightBorder += 1
            topBorder = 0 //Обнуляем верхнюю границу
            bottomBorder = key.height - 1 //Обнуляем нижнюю границу
        }
        do { //Проверяем подходит ли ключ смещаясь вниз при каждой итерации
            var switch = true //Переключатель для остановки цикла, если ключ не подходит
            for (row in topBorder..bottomBorder) {
                if (!switch) break //Выходим из цикла, если ключ не подходит
                for (column in leftBorder..rightBorder) {
                    if (!switch) break //Выходим из цикла, если ключ не подходит
                    //Проверяем каждый элемент ключа и области в замке, ограниченной границами
                    if (lock[row, column] == key[row - topBorder, column - leftBorder]) switch = false
                }
            }
            topBorder += 1
            bottomBorder += 1
            if (switch) return Triple(true, topBorder - 1, leftBorder) //Здесь смещения равны верхней и левой границе
        } while (bottomBorder < lock.height - 2) //Выполняем пока нижняя граница не будет равна высоте - 1
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
    val matrix = createMatrix(this.height, this.width, 0)
    for (i in 0..this.height - 1) {
        for (j in 0..this.width - 1) {
            matrix[i, j] = this[i, j] * (-1)
        }
    }
    return matrix
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
    val matrix = createMatrix(this.height, other.width, 0)
    for (row in 0..this.height - 1) {
        for (i in 0..other.width - 1) {
            var sum = 0
            for (column in 0..this.width - 1) {
                sum += this[row, column] * other[column, i]
            }
            matrix[row, i] = sum
        }
    }
    return matrix
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
fun fifteenGameMoves(matrix: Matrix<Int>, moves: List<Int>): Matrix<Int> = TODO()

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
fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> = TODO()
