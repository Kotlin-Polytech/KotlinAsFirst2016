@file:Suppress("UNUSED_PARAMETER")

package lesson6.task2

import lesson6.task1.Point
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

    fun bishopOnLine(other: Square): Boolean = Math.abs(column - other.column) == Math.abs(row - other.row)

    fun kingOnLine(other: Square): Boolean = ((column == other.column) || (row == other.row))

    /**
     * Простая
     *
     * Возвращает строковую нотацию для клетки.
     * В нотации, колонки обозначаются латинскими буквами от a до h, а ряды -- цифрами от 1 до 8.
     * Для клетки не в пределах доски вернуть пустую строку
     */
    fun notation(): String {
        var result = ""
        result += when (column) {
            1 -> 'a'
            2 -> 'b'
            3 -> 'c'
            4 -> 'd'
            5 -> 'e'
            6 -> 'f'
            7 -> 'g'
            8 -> 'h'
            else -> return ""
        }
        if (row in 1..8) result += row.toString()
        else return ""

        return result
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
    try {
        val x = when (notation[0]) {
            'a' -> 1
            'b' -> 2
            'c' -> 3
            'd' -> 4
            'e' -> 5
            'f' -> 6
            'g' -> 7
            'h' -> 8
            else -> throw IllegalArgumentException("")
        }
        if (notation[1] in '1'..'8') {
            val y = notation[1].toInt() - 48
            return Square(x, y)
        } else throw IllegalArgumentException("")
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("")
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
    if ((start.inside()) && (end.inside())) {
        //if ((start.column !in 1..8) && (end.column !in 1..8) && (end.row !in 1..8) && (start.row !in 1..8)) throw IllegalArgumentException()
        if (start == end) return 0
        else if ((start.column == end.column) || (end.row == start.row)) return 1
        else return 2
    } else throw IllegalArgumentException()
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
    if (start == end) return listOf(start)
    else if ((start.column == end.column) || (end.row == start.row)) return listOf(start, end)
    else return listOf(start, Square(start.column, end.row), end)
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
    else if (start == end) return 0
    else if (Math.abs(start.column - end.column) == Math.abs(start.row - end.row)) return 1
    else if ((Math.abs(start.column - end.column) + Math.abs(start.row - end.row)) % 2 == 0) return 2
    else return -1
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

fun bishopWalk(start: Square, end: Square, quarter: Int): List<Square> {
    var x = start.column
    var y = start.row
    while ((x in 1..8) && (y in 1..8)) {
        if (Square(x, y).bishopOnLine(end)) {
            if (end == Square(x, y)) return listOf(start, end)
            else return listOf(start, Square(x, y), end)
        }
        if (quarter == 1 || quarter == 4) x += 1
        else x -= 1
        if (quarter == 1 || quarter == 2) y += 1
        else y -= 1
    }
    return emptyList()
}

fun bishopTrajectory(start: Square, end: Square): List<Square> {
    if ((!start.inside()) || (!end.inside())) return emptyList()
    if ((Math.abs(start.column - end.column) + Math.abs(start.row - end.row)) % 2 != 0) return emptyList()
    if (start == end) return listOf(start)
    if (start.bishopOnLine(end)) return listOf(start, end)
    var result= listOf<Square>()

    if(bishopWalk(start, end, 1).isNotEmpty()) result= bishopWalk(start, end, 1) //проходим по четвертям
    if(bishopWalk(start, end, 2).isNotEmpty()) result= bishopWalk(start, end, 2)
    if(bishopWalk(start, end, 3).isNotEmpty()) result= bishopWalk(start, end, 3)
    if(bishopWalk(start, end, 4).isNotEmpty()) result= bishopWalk(start, end, 4)

    return result
}

/**
 * Средняя
 *
 * Определить число ходов, за которое шахматный король пройдёт из клетки start в клетку end.
 * Шахматный король одним ходом может переместиться из клетки, в которой стоит,
 * на любую соседнюю по вертикали, горизонтали или диагонали.
 * Ниже точками выделены возможные ходы короля, а крестиками -- невозможные:
 *
 * xxxxxx
 * x...xx
 * x.K.xx
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
    if ((!start.inside()) || (!end.inside())) throw IllegalArgumentException()
    if (start == end) return 0
    return (Math.abs(start.column - end.column) + Math.abs(start.row - end.row) - Math.min(Math.abs(start.column - end.column), Math.abs(start.row - end.row)))
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
    val listOfMoves = mutableListOf<Square>()
    if (start == end) return listOf(start)
    listOfMoves.add(start)

    if (start.kingOnLine(end)) {                                        // если на одной линии
        if ((start.column == end.column) && (start.row < end.row)) {    //верх // модуль можно убрать
            for (i in 1..(end.row - start.row)) {
                listOfMoves.add(Square(start.column, start.row + i))
            }
        }
        if ((start.column == end.column) && (start.row > end.row)) {  // низ
            for (i in 1..(start.row - end.row)) {
                listOfMoves.add(Square(start.column, end.row - i))
            }
        }
        if ((start.row == end.row) && (start.column < end.column)) { //право
            for (i in 1..(end.column - start.column)) {
                listOfMoves.add(Square(start.column + i, start.row))
            }
        }
        if ((start.row == end.row) && (start.column > end.column)) {  //лево
            for (i in 1..(start.column - end.column)) {
                listOfMoves.add(Square(start.column - i, start.row))
            }
        }
        return listOfMoves
    }

    for (i in 1..Math.min(Math.abs(start.column - end.column), Math.abs(start.row - end.row))) {   //идём по диагонали
        if ((start.column < end.column) && (start.row < end.row)) {
            listOfMoves.add(Square(start.column + i, start.row + i))   //1 четверть
        }
        if ((start.column > end.column) && (start.row < end.row)) {     //2 четверть
            listOfMoves.add(Square(start.column - i, start.row + i))
        }
        if ((start.column < end.column) && (start.row > end.row)) {   //4 четверть
            listOfMoves.add(Square(start.column + i, start.row - i))
        }
        if ((start.column > end.column) && (start.row > end.row)) {    //3 четверть
            listOfMoves.add(Square(start.column - i, start.row - i))
        }
    }

    if (listOfMoves[listOfMoves.size - 1] == end) return listOfMoves
    else {
        val square = listOfMoves[listOfMoves.size - 1]
        if (square.row < end.row) {                                     //верх
            for (i in 1..end.row - square.row) {
                listOfMoves.add(Square(end.column, square.row + i))
            }
        }
        if (square.row > end.row) {
            for (i in 1..square.row - end.row) {                             //низ
                listOfMoves.add(Square(end.column, square.row - 1))
            }
        }
        if (square.column < end.column) {
            for (i in 1..end.column - square.column) {                       //право
                listOfMoves.add(Square(square.column + i, end.row))
            }
        }
        if (square.column > end.column) {
            for (i in 1..square.column - end.column) {                         //лево
                listOfMoves.add(Square(square.column - 1, end.row))
            }
        }
        return listOfMoves
    }
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
