@file:Suppress("UNUSED_PARAMETER")

package lesson6.task2

import lesson6.task1.*
import lesson6.task3.Graph
import java.lang.Math.*

val alf = "abcdefgh"

/**
 * Клетка шахматной доски. Шахматная доска квадратная и имеет 8 х 8 клеток.
 * Поэтому, обе координаты клетки (горизонталь row, вертикаль column) могут находиться в пределах от 1 до 8.
 * Горизонтали нумеруются снизу вверх, вертикали слева направо.
 */
data class Square(val column: Int, val row: Int) {
    /**
    //     * Пример
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
        if (column !in 1..8 || row !in 1..8) return ""
        return alf[column - 1] + "$row"
    }

    override fun toString(): String {
        return "$column$row"
    }
}

fun isCorrect(vararg squares: Square) {
    for (square in squares) {
        if (square.column !in 1..8 || square.row !in 1..8) throw IllegalArgumentException()
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
    if (notation.length != 2 || notation[0] !in 'a'..'h' || notation[1] !in '1'..'8') throw IllegalArgumentException()
    val column = notation[0] - 'a' + 1
    val row = notation[1] - '0'
    return Square(column, row)
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
    isCorrect(start, end)
    if (start == end) return 0
    if (start.column == end.column || start.row == end.row) return 1
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
    val moveNumber = rookMoveNumber(start, end)
    if (moveNumber == 0) return listOf(start)
    if (moveNumber == 1) return listOf(start, end)
    val center = Square(start.column, end.row)
    return listOf(start, center, end)
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
    isCorrect(start, end)
    val startIsEven = (start.column + start.row) % 2 == 0
    val endIsEven = (end.column + end.row) % 2 == 0
    if (startIsEven != endIsEven) return -1
    if (start == end) return 0
    val condition = abs(start.column - end.column) == abs(start.row - end.row)
    if (condition) return 1
    return 2
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
    val moveNumber = bishopMoveNumber(start, end)
    if (moveNumber == 0) return listOf(start)
    if (moveNumber == 1) return listOf(start, end)
    if (moveNumber == -1) return listOf()
    // Используем функцию crossPoint из Lesson6.task1
    var result = Line(Point(start.column.toDouble(), start.row.toDouble()), PI / 4)
            .crossPoint(Line(Point(end.column.toDouble(), end.row.toDouble()), 3 * PI / 4))
    if (result.x !in 1..8 || result.y !in 1..8) {
        result = Line(Point(start.column.toDouble(), start.row.toDouble()), 3 * PI / 4)
                .crossPoint(Line(Point(end.column.toDouble(), end.row.toDouble()), PI / 4))
    }
    val centerSquare = Square(round(result.x).toInt(), round(result.y).toInt())
    return listOf(start, centerSquare, end)
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
    isCorrect(start, end)
    val rowDistance = abs(start.row - end.row)
    val columnDistance = abs(start.column - end.column)

    val result = when {
        rowDistance >= columnDistance -> rowDistance
        else -> columnDistance
    }
    return result
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
    isCorrect(start, end)
    val result = mutableListOf(start)
    fun symbol(a: Int, b: Int): Int {
        return when {
            a < b -> 1
            a > b -> -1
            else -> 0
        }
    }

    var symbolColumn = symbol(start.column, end.column)
    var symbolRow = symbol(start.row, end.row)
    var centerSquare = Square(start.column, start.row)
    if (symbolColumn == 0 && symbolRow == 0) return result
    do {
        centerSquare = Square(centerSquare.column + symbolColumn, centerSquare.row + symbolRow)
        result += centerSquare
        if (centerSquare.column == end.column) symbolColumn = 0
        if (centerSquare.row == end.row) symbolRow = 0
    } while ((centerSquare.column != end.column) || (centerSquare.row != end.row))
    return result
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

//СОЗДАЕМ ГРАФ С ХОДАМИ КОНЯ
fun chessGraph(): Graph {
    val chessGraph = Graph()
    //СОЗДАЕМ ШАХМАТНУЮ ДОСКУ
    for (row in 8 downTo 1) {
        for (column in 1..8) {
            chessGraph.addVertex("$column$row")
        }
    }
    //СОЕДИНЯЕМ ВСЕ КЛЕТКИ ШАХМАТНОЙ ДОСКИ С ВОЗМОЖНЫМИ ХОДАМИ КОНЯ
    for (row in 8 downTo 1) {
        for (column in 1..8) {

            val rowOne = row - 1
            val rowTwo = row - 2
            //ДЛЯ СТРОК ОТ 3 ДО 8
            if (row > 2) {
                //УЧИТЫВАЕМ ГРАНИЦЫ СПРАВА
                if (column < 8) {
                    val column2 = column + 1
                    chessGraph.connect("$column$row", "$column2$rowTwo")
                    if (column < 7) {
                        val column3 = column + 2
                        chessGraph.connect("$column$row", "$column3$rowOne")
                    }
                }
                //УЧИТЫВАЕМ ГРАНИЦЫ СЛЕВА
                if (column > 1) {
                    val column4 = column - 1
                    chessGraph.connect("$column$row", "$column4$rowTwo")
                    if (column > 2) {
                        val column5 = column - 2
                        chessGraph.connect("$column$row", "$column5$rowOne")
                    }
                }
            }
            //ДЛЯ ВТОРОГО РЯДА
            if (row == 2) {
                if (column < 7) {
                    val column3 = column + 2
                    chessGraph.connect("$column$row", "$column3$rowOne")
                }
                if (column > 2) {
                    val column5 = column - 2
                    chessGraph.connect("$column$row", "$column5$rowOne")
                }
            }
        }
    }
    return chessGraph
}

fun knightMoveNumber(start: Square, end: Square): Int {
    if (start.row !in 1..8 || end.column !in 1..8) throw IllegalArgumentException()
    val graph = chessGraph()
    return graph.bfs(start.toString(), end.toString())
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

