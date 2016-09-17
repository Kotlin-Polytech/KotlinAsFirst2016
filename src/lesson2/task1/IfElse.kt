@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 * t
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
    return if (((age >= 11) and (age <= 20)) or ((age >= 111) and (age <= 120))) "$age лет"
    else when {
        (age % 10 == 1) -> "$age год"
        (age % 10 == 0) or (age % 10 > 4) -> "$age лет"
        (age % 10 < 5) and (age % 10 != 1) and (age % 10 != 0) -> "$age года"
        else -> ""
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
    return if (((t1 * v1 + t2 * v2 + t3 * v3) / 2) <= t1 * v1) ((t1 * v1 + t2 * v2 + t3 * v3) / 2) / v1 else if (((t1 * v1 + t2 * v2 + t3 * v3) / 2 > t1 * v1) and ((t1 * v1 + t2 * v2 + t3 * v3) / 2 <= t1 * v1 + t2 * v2)) t1 + (((t1 * v1 + t2 * v2 + t3 * v3) / 2) - t1 * v1) / v2 else t1 + t2 + (((t1 * v1 + t2 * v2 + t3 * v3) / 2) - t1 * v1 - t2 * v2) / v3
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    return when {
        (kingX != rookX1) and (kingX != rookX2) and (kingY != rookY1) and (kingY != rookY2) -> 0
        ((kingX == rookX1) or (kingY == rookY1)) and (((kingX != rookX2) and (kingY != rookY2))) -> 1
        ((kingX == rookX2) or (kingY == rookY2)) and (((kingX != rookX1) and (kingY != rookY1))) -> 2
        ((kingX == rookX2) or (kingX == rookX1)) and (((kingY != rookY1) or (kingY != rookY2))) -> 3
        else -> 61
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
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    return when {
        ((kingX != rookX) and (kingY != rookY)) and (abs(bishopX - kingX) != abs(bishopY - kingY)) -> 0
        ((kingX == rookX) or (kingY == rookY)) and (abs(bishopX - kingX) != abs(bishopY - kingY)) -> 1
        (abs(bishopX - kingX) == abs(bishopY - kingY)) and ((kingX != rookX) and (kingY != rookY)) -> 2
        (abs(bishopX - kingX) == abs(bishopY - kingY)) and ((kingX == rookX) or (kingY == rookY)) -> 3
        else -> 4
    }
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
    return if ((a > b) and (a > c)) when {
        (((b * b + c * c - a * a) / (2 * b * c)) > 0) and (((b * b + c * c - a * a) / (2 * b * c)) >= -1) and (((b * b + c * c - a * a) / (2 * b * c)) <= 1) -> 0
        (((b * b + c * c - a * a) / (2 * b * c)) == 0.0) and (((b * b + c * c - a * a) / (2 * b * c)) >= -1) and (((b * b + c * c - a * a) / (2 * b * c)) <= 1) -> 1
        (((b * b + c * c - a * a) / (2 * b * c)) < 0) and (((b * b + c * c - a * a) / (2 * b * c)) >= -1) and (((b * b + c * c - a * a) / (2 * b * c)) <= 1) -> 2
        else -> -1
    } else if ((b > a) and ( b > c)) when {
        (((- b * b + c * c + a * a) / (2 * a * c)) > 0) and (((- b * b + c * c + a * a) / (2 * a * c)) >= -1) and (((- b * b + c * c + a * a) / (2 * a * c)) <= 1) -> 0
        (((- b * b + c * c + a * a) / (2 * a * c)) == 0.0) and (((- b * b + c *c + a * a) / (2 * a * c)) >= -1) and (((- b * b + c * c + a * a) / (2 * a * c)) <= 1) -> 1
        (((- b * b + c * c + a * a) / (2 * a * c)) < 0) and (((- b * b + c * c + a * a) / (2 * a * c)) >= -1) and (((- b * b + c * c + a * a) / (2 * a * c)) <= 1) -> 2
        else -> -1
    } else if ((c > a) and ( c > b)) when {
        ((( b * b - c * c + a * a) / (2 * a * b)) > 0) and ((( b * b - c * c + a * a) / (2 * a * b)) >= -1) and ((( b * b - c * c + a * a) / (2 * a * b)) <= 1) -> 0
        ((( b * b - c * c + a * a) / (2 * a * b)) == 0.0) and ((( b * b - c *c + a * a) / (2 * a * b)) >= -1) and ((( b * b - c * c + a * a) / (2 * a * b)) <= 1) -> 1
        ((( b * b - c * c + a * a) / (2 * a * b)) < 0) and ((( b * b - c * c + a * a) / (2 * a * b)) >= -1) and ((( b * b - c * c + a * a) / (2 * a * b)) <= 1) -> 2
        else -> -1
    } else if (a == b) when {
        ((( b * b - c * c + a * a) / (2 * a * b)) > 0) and ((( b * b - c * c + a * a) / (2 * a * b)) >= -1) and ((( b * b - c * c + a * a) / (2 * a * b)) <= 1) -> 0
        ((( b * b - c * c + a * a) / (2 * a * b)) == 0.0) and ((( b * b - c *c + a * a) / (2 * a * b)) >= -1) and ((( b * b - c * c + a * a) / (2 * a * b)) <= 1) -> 1
        ((( b * b - c * c + a * a) / (2 * a * b)) < 0) and ((( b * b - c * c + a * a) / (2 * a * b)) >= -1) and ((( b * b - c * c + a * a) / (2 * a * b)) <= 1) -> 2
        else -> -1
    } else if (b == c) when {
        (((b * b + c * c - a * a) / (2 * b * c)) > 0) and (((b * b + c * c - a * a) / (2 * b * c)) >= -1) and (((b * b + c * c - a * a) / (2 * b * c)) <= 1) -> 0
        (((b * b + c * c - a * a) / (2 * b * c)) == 0.0) and (((b * b + c * c - a * a) / (2 * b * c)) >= -1) and (((b * b + c * c - a * a) / (2 * b * c)) <= 1) -> 1
        (((b * b + c * c - a * a) / (2 * b * c)) < 0) and (((b * b + c * c - a * a) / (2 * b * c)) >= -1) and (((b * b + c * c - a * a) / (2 * b * c)) <= 1) -> 2
        else -> -1
    } else if (a == c) when {
        (((- b * b + c * c + a * a) / (2 * a * c)) > 0) and (((- b * b + c * c + a * a) / (2 * a * c)) >= -1) and (((- b * b + c * c + a * a) / (2 * a * c)) <= 1) -> 0
        (((- b * b + c * c + a * a) / (2 * a * c)) == 0.0) and (((- b * b + c *c + a * a) / (2 * a * c)) >= -1) and (((- b * b + c * c + a * a) / (2 * a * c)) <= 1) -> 1
        (((- b * b + c * c + a * a) / (2 * a * c)) < 0) and (((- b * b + c * c + a * a) / (2 * a * c)) >= -1) and (((- b * b + c * c + a * a) / (2 * a * c)) <= 1) -> 2
        else -> -1
    } else 8
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
    return when {
        (c > b) or (a > d) -> -1
        (d >= b) and ( c >= a) -> b - c
        (c >= a) and (b >= d) -> d - c
        (a >= c) and (d >= b) -> b - a
        (a >= c) and (b >= d) -> d - a
        else -> 8

    }
}