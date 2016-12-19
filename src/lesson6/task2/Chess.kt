@file:Suppress("UNUSED_PARAMETER")

package lesson6.task2
import java.lang.Math.*
import lesson4.task1.abs
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
    fun notation(): String =
            when {
                inside() -> ('a' + column - 1).toString() + "$row"
                else -> ""
            }
}


/**
 * Простая
 *
 * Создаёт клетку по строковой нотации.
 * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
 * Если нотация некорректна, бросить IllegalArgumentException
 */
fun square(notation: String): Square =
        when {
            notation.length != 2 || notation[0] !in 'a'..'h' || notation[1] !in '1'..'8' ->
                throw IllegalArgumentException("IllegalArgumentException")
            else -> Square((notation[0] - 'a').toInt() + 1, notation[1].toString().toInt())
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
    if (start.inside() && end.inside()) {
        return when {
            (start.column == end.column).xor(start.row == end.row) -> 1
            (start.column == end.column).or(start.row == end.row) -> 0
            else -> 2
        }
    } else throw IllegalArgumentException("IllegalArgumentException")
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
fun rookTrajectory(start: Square, end: Square): List<Square> =
        when (rookMoveNumber(start, end)) {
            2 -> listOf(start, Square(start.column, end.row), end)
            1 -> listOf(start, end)
            else -> listOf(start)
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
    if (start.inside() && end.inside()) {
      return when {
            start == end -> 0
            Math.abs(end.column - start.column) == Math.abs(end.row - start.row) -> 1
            (Math.abs(end.column - start.column) + Math.abs(end.row - start.row)) % 2 == 0 -> 2
            (Math.abs(end.column - start.column) + Math.abs(end.row - start.row)) % 2 == 1 -> -1
            else -> throw IllegalArgumentException("IllegalArgumentException")
        }
    } else throw IllegalArgumentException("IllegalArgumentException")
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
        if ((Math.abs(end.column - start.column) + Math.abs(end.row - start.row)) % 2 == 1) {
        return listOf()
    } else {
       return when (bishopMoveNumber(start, end)) {
           -1 -> listOf()
           0 -> listOf(start)
           1 -> listOf(start, end)
            else -> listOf(start, when {
                (start.column + end.column + end.row - start.row) / 2 in 0..8 ->
                    Square((start.column + end.column + end.row - start.row) / 2,
                           (start.column + end.column + end.row - start.row) / 2
                                   - start.column + start.row)
                else -> Square((start.column - end.column + end.row - start.row) / 2,
                               (start.column + end.column + end.row - start.row) / 2
                                       - start.column + start.row)
            }
                    , end)
        }
    }
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
    if (!start.inside() || !end.inside()) {
        throw IllegalArgumentException("IllegalArgumentException")
    } else {
        return Math.max(Math.abs(start.column - end.column),
                Math.abs(start.row - end.row))
    }
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
    val moveList = mutableListOf<Square>(start)
    val distanceColumn = end.column - start.column >= 0
    val distanceRow = end.row - start.row >= 0
    var kingX = start.column
    var kingY = start.row
    while (kingX != end.column && kingY != end.row) {
        when {
            distanceColumn && distanceRow -> {
                kingX += 1
                kingY += 1
            }
            distanceColumn && !distanceRow -> {
                kingX += 1
                kingY -= 1
            }
            !distanceColumn && distanceRow -> {
                kingX -= 1
                kingY += 1
            }
            else -> {
                kingX -= 1
                kingY -= 1
            }
        }
        moveList.add(Square(kingX, kingY))
    }
    if (kingX == kingY){
        return moveList
    }
    if (kingY == end.row){
        while (kingX != end.column){
            when {
                distanceColumn -> kingX += 1
                else -> kingX -= 1
            }
            moveList.add(Square(kingX, kingY))
        }
    } else {
        while (kingY != end.row){
            when{
                distanceRow -> kingY += 1
                else -> kingY -= 1
            }
            moveList.add(Square(kingX, kingY))
        }
    }
    return moveList
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
fun sqr(x: Int) = x * x
//перебор шахматной доски
fun knightMoveNumber(start: Square, end: Square): Int {
    if (start.inside() && end.inside()) {
        return when {
            start == end -> 0
            sqr(end.row - start.row) + sqr(end.column - start.column) == 5 -> 1
            sqr(end.row - start.row) + sqr(end.column - start.column) == 4 -> 2
            sqr(end.row - start.row) + sqr(end.column - start.column) == 16 -> 2
            sqr(end.row - start.row) + sqr(end.column - start.column) == 20 -> 2
            sqr(end.row - start.row) + sqr(end.column- start.column) == 2 &&
                    (start == Square(1,1) ||  start == Square(1,8) ||
                    start == Square(8,1) ||  start == Square(8,8) ||
                    end == Square(1,1) ||  end == Square(1,8) ||
                    end == Square(8,1) ||  end == Square(8,8)) -> 4
            sqr(end.row - start.row) + sqr(end.column- start.column) == 2 -> 2
            sqr(end.row - start.row) + sqr(end.column - start.column) == 10 -> 2
            sqr(end.row - start.row) + sqr(end.column - start.column) == 18 -> 2
            sqr(end.row - start.row) + sqr(end.column - start.column) == 1 -> 3
            sqr(end.row - start.row) + sqr(end.column - start.column) == 9 -> 3
            sqr(end.row - start.row) + sqr(end.column - start.column) == 25 -> 3
            sqr(end.row - start.row) + sqr(end.column - start.column) == 13 -> 3
            sqr(end.row - start.row) + sqr(end.column- start.column) == 17 -> 3
            sqr(end.row - start.row) + sqr(end.column - start.column) == 25 -> 3
            sqr(end.row - start.row) + sqr(end.column - start.column) == 29 -> 3
            sqr(end.row - start.row) + sqr(end.column - start.column) == 37 -> 3
            sqr(end.row - start.row) + sqr(end.column - start.column) == 41 -> 3
            sqr(end.row - start.row) + sqr(end.column- start.column) == 45 -> 3
            sqr(end.row - start.row) + sqr(end.column - start.column) == 36 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 8 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 26 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 32 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 34 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 40 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 50 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 58 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 52 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 72 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 74 -> 4
            sqr(end.row - start.row) + sqr(end.column - start.column) == 49 -> 5
            sqr(end.row - start.row) + sqr(end.column - start.column) == 53 -> 5
            sqr(end.row - start.row) + sqr(end.column - start.column) == 61 -> 5
            sqr(end.row - start.row) + sqr(end.column - start.column) == 65 -> 5
            sqr(end.row - start.row) + sqr(end.column - start.column) == 85 -> 5
            else -> 6
        }
    } else throw IllegalArgumentException("IllegalArgumentException")
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
fun knightTrajectory(start: Square, end: Square): List<Square> = TODO()
