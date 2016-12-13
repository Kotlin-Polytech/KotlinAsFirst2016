@file:Suppress("UNUSED_PARAMETER")

package lesson6.task2

/**
 * Клетка шахматной доски. Шахматная доска квадратная и имеет 8 х 8 клеток.
 * Поэтому, обе координаты клетки (горизонталь row, вертикаль column) могут находиться в пределах от 1 до 8.
 * Горизонтали нумеруются снизу вверх, вертикали слева направо.
 */
data class Square(val column: Int, val row: Int) {
    /**
     * Пример
     *
     * Возвращает true, если клетка находится в пределах доски
     */
    fun inside(): Boolean = column in 1..8 && row in 1..8

    /**
     * Простая
     *
     * Возвращает строковую нотацию для клетки.
     * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
     * Для клетки не в пределах доски вернуть пустую строку
     */
    fun notation(): String {
        if (inside()) {
            return ('a'.toInt() + column - 1).toChar() + row.toString()
        } else {
            return ""
        }
    }
}

/**
 * Простая
 *
 * Создаёт клетку по строковой нотации.
 * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
 * Если нотация некорректна, бросить IllegalArgumentException
 */
fun square(notation: String): Square {
    if (notation.length == 2) {
        if (notation[0] !in 'a'..'h' || notation[1] !in '1'..'8') throw IllegalArgumentException()
        return Square(notation[0].toInt() - 'a'.toInt() + 1, notation[1].toInt() - '1'.toInt() + 1)
    } else {
        throw IllegalArgumentException()
    }
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматная ладья пройдёт из клетки start в клетку end.
 * Шахматная ладья может за один ход переместиться на любую другую клетку
 * по вертикали или горизонтали.
 * Ниже точками выделены возможные ходы ладьи, а крестиками -- невозможные:
 *
 * xx.xxххх
 * xх.хxххх
 * ..Л.....
 * xх.хxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 * xx.xxххх
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: rookMoveNumber(Square(3, 1), Square(6, 3)) = 2
 * Ладья может пройти через клетку (3, 3) или через клетку (6, 1) к клетке (6, 3).
 */
fun rookMoveNumber(start: Square, end: Square): Int {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()

    val c1 = start.column
    val c2 = end.column
    val r1 = start.row
    val r2 = end.row

    if (r1 == r2 || c1 == c2) {
        if (r1 == r2 && c1 == c2)
            return 0
        else
            return 1
    } else
        return 2
}

/**
 * Средняя
 *
 * Вернуть список из клеток, по которым шахматная ладья может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов ладьи см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: rookTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможен ещё один вариант)
 *          rookTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(3, 3), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          rookTrajectory(Square(3, 5), Square(8, 5)) = listOf(Square(3, 5), Square(8, 5))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun rookTrajectory(start: Square, end: Square): List<Square> {
    val list = mutableListOf<Square>()
    list.add(Square(start.column, start.row))

    if (start.column != end.column) list.add(Square(end.column, start.row))

    if (start.row != end.row)
        if (list.size == 2) list.add(Square(end.column, end.row))
        else list.add(Square(start.column, end.row))

    return list
}

/**
 * Простая
 *
 * Определить число ходов, за которое шахматный слон пройдёт из клетки start в клетку end.
 * Шахматный слон может за один ход переместиться на любую другую клетку по диагонали.
 * Ниже точками выделены возможные ходы слона, а крестиками -- невозможные:
 *
 * .xxx.ххх
 * x.x.xххх
 * xxСxxxxx
 * x.x.xххх
 * .xxx.ххх
 * xxxxx.хх
 * xxxxxх.х
 * xxxxxхх.
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если клетка end недостижима для слона, вернуть -1.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Примеры: bishopMoveNumber(Square(3, 1), Square(6, 3)) = -1; bishopMoveNumber(Square(3, 1), Square(3, 7)) = 2.
 * Слон может пройти через клетку (6, 4) к клетке (3, 7).
 */
fun bishopMoveNumber(start: Square, end: Square): Int {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    if (Math.abs(start.column - end.column) % 2 != Math.abs(start.row - end.row) % 2) return -1
    if (start == end) return 0
    if (start.column - start.row == end.column - end.row ||
            9 - start.column - start.row == 9 - end.column - end.row) return 1
    else return 2
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный слон может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов слона см. предыдущую задачу.
 *
 * Если клетка end недостижима для слона, вернуть пустой список.
 *
 * Если клетка достижима:
 * - список всегда включает в себя клетку start
 * - клетка end включается, если она не совпадает со start.
 * - между ними должны находиться промежуточные клетки, по порядку от start до end.
 *
 * Примеры: bishopTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          bishopTrajectory(Square(3, 1), Square(3, 7)) = listOf(Square(3, 1), Square(6, 4), Square(3, 7))
 *          bishopTrajectory(Square(1, 3), Square(6, 8)) = listOf(Square(1, 3), Square(6, 8))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun bishopTrajectory(start: Square, end: Square): List<Square> {
    val list = mutableListOf<Square>()
    if (Math.abs(start.column - end.column) % 2 != Math.abs(start.row - end.row) % 2) return list
    list.add(Square(start.column, start.row))

    if (start == end) return list

    if (start.column - start.row != end.column - end.row) {
        loop@ for (i in 1..8) for (j in 1..8)
            if (    (i - j == start.column - start.row && 9 - i - j == 9 - end.column - end.row ||
                    i - j == end.column - end.row && 9 - i - j == 9 - start.column - start.row) &&
                    i != start.column && j != start.row && i != end.column && j != end.row) {
                list.add(Square(i, j))
                break@loop
            }
    }

    list.add(Square(end.column, end.row))
    return list
}

/**
 * Средняя
 *
 * Определить число ходов, за которое шахматный король пройдёт из клетки start в клетку end.
 * Шахматный король одним ходом может переместиться из клетки, в которой стоит,
 * на любую соседнюю по вертикали, горизонтали или диагонали.
 * Ниже точками выделены возможные ходы короля, а крестиками -- невозможные:
 *
 * xxxxx
 * x...x
 * x.K.x
 * x...x
 * xxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: kingMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Король может последовательно пройти через клетки (4, 2) и (5, 2) к клетке (6, 3).
 */
fun kingMoveNumber(start: Square, end: Square): Int {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    val difCol = Math.abs(start.column - end.column)
    val difRow = Math.abs(start.row - end.row)
    return Math.max(difCol, difRow)
}

/**
 * Сложная
 *
 * Вернуть список из клеток, по которым шахматный король может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов короля см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры: kingTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 *          (здесь возможны другие варианты)
 *          kingTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(4, 2), Square(5, 2), Square(6, 3))
 *          (здесь возможен единственный вариант)
 *          kingTrajectory(Square(3, 5), Square(6, 2)) = listOf(Square(3, 5), Square(4, 4), Square(5, 3), Square(6, 2))
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun kingTrajectory(start: Square, end: Square): List<Square> {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    val list = mutableListOf<Square>()
    list.add(Square(start.column, start.row))

    val signX: Int
    val signY: Int
    if (end.column - start.column < 0) signX = -1 else signX = 1
    if (end.row - start.row < 0) signY = -1 else signY = 1

    var currentColumn = start.column
    var currentRow = start.row

    while (currentColumn != end.column || currentRow != end.row) {
        if (end.column != currentColumn) currentColumn += signX
        if (end.row != currentRow) currentRow += signY
        list.add(Square(currentColumn, currentRow))
    }

    return list
}

/**
 * Сложная
 *
 * Определить число ходов, за которое шахматный конь пройдёт из клетки start в клетку end.
 * Шахматный конь одним ходом вначале передвигается ровно на 2 клетки по горизонтали или вертикали,
 * а затем ещё на 1 клетку под прямым углом, образуя букву "Г".
 * Ниже точками выделены возможные ходы коня, а крестиками -- невозможные:
 *
 * .xxx.xxx
 * xxKxxxxx
 * .xxx.xxx
 * x.x.xxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 * xxxxxxxx
 *
 * Если клетки start и end совпадают, вернуть 0.
 * Если любая из клеток некорректна, бросить IllegalArgumentException().
 *
 * Пример: knightMoveNumber(Square(3, 1), Square(6, 3)) = 3.
 * Конь может последовательно пройти через клетки (5, 2) и (4, 4) к клетке (6, 3).
 */
fun knightMoveNumber(start: Square, end: Square): Int {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    if (start == end) return 0

    return knightTrajectory(start, end).size-1
}

/**
 * Очень сложная
 *
 * Вернуть список из клеток, по которым шахматный конь может быстрее всего попасть из клетки start в клетку end.
 * Описание ходов коня см. предыдущую задачу.
 * Список всегда включает в себя клетку start. Клетка end включается, если она не совпадает со start.
 * Между ними должны находиться промежуточные клетки, по порядку от start до end.
 * Примеры:
 *
 * knightTrajectory(Square(3, 3), Square(3, 3)) = listOf(Square(3, 3))
 * здесь возможны другие варианты)
 * knightTrajectory(Square(3, 1), Square(6, 3)) = listOf(Square(3, 1), Square(5, 2), Square(4, 4), Square(6, 3))
 * (здесь возможен единственный вариант)
 * knightTrajectory(Square(3, 5), Square(5, 6)) = listOf(Square(3, 5), Square(5, 6))
 * (здесь опять возможны другие варианты)
 * knightTrajectory(Square(7, 7), Square(8, 8)) =
 *     listOf(Square(7, 7), Square(5, 8), Square(4, 6), Square(6, 7), Square(8, 8))
 *
 * Если возможно несколько вариантов самой быстрой траектории, вернуть любой из них.
 */
fun knightTrajectory(start: Square, end: Square): List<Square> {
    if (!start.inside() || !end.inside()) throw IllegalArgumentException()
    val resultList = mutableListOf<Square>()
    val allList = mutableListOf<Square>()
    var prevList = mutableListOf<Square>()

    class Turn(val prev: Square, val next: Square)
    val turnsList = mutableListOf<Turn>()

    allList.add(start)
    prevList.add(start)

    val movesList = listOf(Pair(-2, -1), Pair(-2, 1), Pair(-1, -2), Pair(-1, 2), Pair(1, -2), Pair(1, 2), Pair(2, -1), Pair(2, 1))

    var turnNum = 0
    var endSqFound = false

    while (!endSqFound) {
        val nextList = mutableListOf<Square>()
        prevList.forEach { element ->
            val curX = element.column
            val curY = element.row

            for (move in movesList) {
                val curSq = Square(curX + move.first, curY + move.second)
                if (curSq == end) endSqFound = true
                if (curSq.inside() && curSq !in allList) {
                    turnsList.add(Turn(Square(curX, curY), curSq))
                    nextList.add(curSq)
                    allList.add(curSq)
                }
            }
        }
        prevList = nextList
        turnNum++
    }

    /**
     * На каждой итерации цикла while перебираются все возможные ходы коня, не считая тех,
     * при которых он попадает на те клетки, в которых он когда-то был, так как это
     * было бы уже не кратчайшим путём. Поэтому, при нахождении пути с конца в начало
     * будет пройден единственный путь, который и будет минимальный.
     */

    var curSq = end
    while (curSq != start) {
        for (element in turnsList) {
            if (element.next == curSq) {
                resultList.add(curSq)
                curSq = element.prev
            }
        }
    }

    resultList.add(start)
    resultList.reverse()

    return resultList
}
