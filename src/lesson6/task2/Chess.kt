@file:Suppress("UNUSED_PARAMETER")
package lesson6.task2

import java.util.*

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
        val letters = "abcdefgh"
        if (inside() == true) {
            return letters[column - 1] + row.toString()
        }
        return ""
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
    val letters = "abcdefgh"
    when {
        (notation.length != 2) -> throw IllegalArgumentException()
        (notation[0] !in letters) -> throw IllegalArgumentException()
    }
    val column = letters.indexOf(notation[0]) + 1
    try {
        val row = (notation[1] - '0').toInt()
        return Square(column, row)
    } catch (e: NumberFormatException) {
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
    if ((start.column !in 1..8) or (start.row !in 1..8)) throw IllegalArgumentException()
    if ((end.column !in 1..8) or (end.row !in 1..8)) throw IllegalArgumentException()
    if (start == end) {
        return 0
    } else {
        if ((start.column != end.column) and (start.row != end.row)) return 2
        else return 1
    }
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
    when {
        (rookMoveNumber(start, end) == 0) -> return listOf(start)
        (rookMoveNumber(start, end) == 1) -> return listOf(start, end)
    }
    return listOf(start, Square(start.column, end.row), end)
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
    when {
        ((start.column !in 1..8) or (start.row !in 1..8)) -> throw IllegalArgumentException()
        ((end.column !in 1..8) or (end.row !in 1..8)) -> throw IllegalArgumentException()
        (start == end) -> return 0
    }
    fun moveNumber(start: Square, end: Square): Int {
        if (Math.abs(start.column - end.column) == Math.abs(start.row - end.row)) return 1
        return 2
    }
    if (((start.column % 2 == 0) and (start.row % 2 == 0)) or ((start.column % 2 != 0) and (start.row % 2 != 0))) {
        if (((end.column % 2 == 0) and (end.row % 2 == 0)) or ((end.column % 2 != 0) and (end.row % 2 != 0))) {
            return moveNumber(start, end)
        }
    }
    if (((start.column % 2 != 0) and (start.row % 2 == 0)) or ((start.column % 2 == 0) and (start.row % 2 != 0))) {
        if (((end.column % 2 != 0) and (end.row % 2 == 0)) or ((end.column % 2 == 0) and (end.row % 2 != 0))) {
            return moveNumber(start, end)
        }
    }
    return -1
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
    when {
        (bishopMoveNumber(start, end) == -1) -> return listOf()
        (bishopMoveNumber(start, end) == 0) -> return listOf(start)
        (bishopMoveNumber(start, end) == 1) -> return listOf(start, end)
    }

    var nextStep = start

    if (end.row >= start.row) {
        while (Math.abs(nextStep.column - end.column) != Math.abs(nextStep.row - end.row)) {
            nextStep = Square(nextStep.column + 1, nextStep.row + 1)
        }
        if (nextStep.inside()) return listOf(start, nextStep, end)
        nextStep = start
        while (Math.abs(nextStep.column - end.column) != Math.abs(nextStep.row - end.row)) {
            nextStep = Square(nextStep.column - 1, nextStep.row + 1)
        }
        return listOf(start, nextStep, end)
    }

    while (Math.abs(nextStep.column - end.column) != Math.abs(nextStep.row - end.row)) {
        nextStep = Square(nextStep.column + 1, nextStep.row - 1)
    }
    if (nextStep.inside()) return listOf(start, nextStep, end)
    nextStep = start
    while (Math.abs(nextStep.column - end.column) != Math.abs(nextStep.row - end.row)) {
        nextStep = Square(nextStep.column - 1, nextStep.row - 1)
    }

    return listOf(start, nextStep, end)
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
    when {
        (!start.inside() or !end.inside()) -> throw IllegalArgumentException()
        (start == end) -> return 0
    }
    var moveCol = 0
    var moveRow = 0
    var nextMove = start
    while (nextMove != end) {
        when {
            (nextMove.column > end.column) -> {
                nextMove = Square(nextMove.column - 1, nextMove.row)
                moveCol ++
            }
            (nextMove.column < end.column) -> {
                nextMove = Square(nextMove.column + 1, nextMove.row)
                moveCol ++
            }
            (nextMove.row > end.row) -> {
                nextMove = Square(nextMove.column, nextMove.row - 1)
                moveRow ++
            }
            (nextMove.row < end.row) -> {
                nextMove = Square(nextMove.column, nextMove.row + 1)
                moveRow ++
            }
        }
    }
    return Math.max(moveCol, moveRow)
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
    when {
        (kingMoveNumber(start, end) == 0) -> return listOf(start)
        (kingMoveNumber(start, end) == 1) -> return listOf(start, end)
    }
    val path = mutableListOf(start)
    var moveCol = 0
    var moveRow = 0
    var nextMove = start
    while (nextMove != end) {
        if (nextMove.column > end.column) {
            nextMove = Square(nextMove.column - 1, nextMove.row)
            moveCol ++
        }
        if (nextMove.column < end.column) {
            nextMove = Square(nextMove.column + 1, nextMove.row)
            moveCol ++
        }
        if (nextMove.row > end.row) {
            nextMove = Square(nextMove.column, nextMove.row - 1)
            moveRow ++
        }
        if (nextMove.row < end.row) {
            nextMove = Square(nextMove.column, nextMove.row + 1)
            moveRow ++
        }
        path.add(nextMove)
    }
    return path
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
fun knightMoveNumber(start: Square, end: Square): Int = TODO()

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
fun knightTrajectory(start: Square, end: Square): List<Square> = TODO()
