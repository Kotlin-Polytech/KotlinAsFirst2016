@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return "$age " + when {
        (age % 100 in 11..19) || (age % 10 == 0) || (age % 10 in 5..9)  -> "лет"
        age % 10 == 1                                                   -> "год"
        age % 10 in 2..4                                                -> "года"
        else                                                            -> "incorrect data"
    }
}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val s: Double = v1 * t1 + v2 * t2 + v3 * t3
    if (v1 * t1 >= s / 2) return (s / 2) / (v1)
    else if (v2 * t2 >= (s / 2) - v1 * t1) return ((s / 2) - v1 * t1) / v2 + t1
    else return ((s / 2) - v1 * t1 - v2 * t2) / v3 + t1 + t2
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 */
fun rookIsAttack(rookX: Int, rookY: Int,
                 figureX: Int, figureY: Int): Boolean {
    return (rookX == figureX) || (rookY == figureY)
}

fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    return when {
        (!rookIsAttack(rookX1, rookY1, kingX, kingY)) &&
                (!rookIsAttack(rookX2, rookY2, kingX, kingY))   -> 0
        (rookIsAttack(rookX1, rookY1, kingX, kingY)) &&
                (!rookIsAttack(rookX2, rookY2, kingX, kingY))   -> 1
        (!rookIsAttack(rookX1, rookY1, kingX, kingY)) &&
                (rookIsAttack(rookX2, rookY2, kingX, kingY))    -> 2
        (rookIsAttack(rookX1, rookY1, kingX, kingY)) &&
                (rookIsAttack(rookX2, rookY2, kingX, kingY))    -> 3
        else                                                    -> -1
    }

}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 */
fun bishopIsAttack(bishopX: Int, bishopY: Int,
                   figureX: Int, figureY: Int): Boolean {
    return abs(figureX - bishopX) == abs(figureY - bishopY)
}

fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    return if ((!rookIsAttack(rookX, rookY, kingX, kingY)) &&
            (!bishopIsAttack(bishopX, bishopY, kingX, kingY))) 0
    else if ((rookIsAttack(rookX, rookY, kingX, kingY)) &&
            (!bishopIsAttack(bishopX, bishopY, kingX, kingY))) 1
    else if ((!rookIsAttack(rookX, rookY, kingX, kingY)) &&
            (bishopIsAttack(bishopX, bishopY, kingX, kingY))) 2
    else 3
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    return when {
        ((a + b <= c) || (a + c <= b) || (b + c <= a))                                      -> -1
        ((a * a + b * b < c * c) || (a * a + c * c < b * b) || (b * b + c * c < a * a))     -> 2
        ((a * a + b * b == c * c) || (a * a + c * c == b * b) || (b * b + c * c == a * a))  -> 1
        ((a * a + b * b > c * c) || (a * a + c * c > b * b) || (b * b + c * c > a * a))     -> 0
        else                                                                                -> -1
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    val AB: Int = abs(a - b)
    val AC: Int = abs(a - c)
    val AD: Int = abs(a - d)
    val BC: Int = abs(b - c)
    val BD: Int = abs(b - d)
    val CD: Int = abs(c - d)
    val maxSegment: Int = max(max(max(AB, AC), max(AD, BC)), max(BD, CD))
    val intersection = (AB + CD) - maxSegment
    return if (intersection >= 0) intersection else -1
}