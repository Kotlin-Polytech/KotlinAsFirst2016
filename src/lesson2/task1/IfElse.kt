@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.maxDivisor
import lesson4.task1.abs

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
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    when {
        ((age in 1..10) || (age in 101..111)) && ((age % 10) == 1) -> "$age год"
        ((age in 2..10) || (age in 102..111)) && ((age % 10) in 2..4) -> "$age года"
        ((age in 11..20) || (age in 111..120))  -> "$age лет"
        ((age in 21..100) || (age in 120..200)) && ((age % 10) in 2..4) -> "$age года"
        ((age in 21..200) || (age in 120..200)) && ((age % 10) == 1) -> "$age год"
        else -> "$age лет"
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

    val way1 = t1 * v1
    val way2 = t2 * v2
    val way3 = t3 * v3
    val halfway = (way1 + way2 + way3) / 2
    return when {
        (halfway - way1 > 0.0) && (halfway - way1 - way2 < 0.0) -> ((halfway - way1) / v2) + t1
        (halfway - way1 > 0.0) && (halfway - way1 - way2 > 0.0) -> (t1 + t2 + (halfway - way1 - way2) / v3)
        (halfway - way1 < 0.0) -> (halfway / v1)
        else -> 90.0
    }
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
        (kingX == rookX1) && (kingX != rookX2) && (kingY != rookY2) -> 1
        (kingX == rookX2) && (kingX != rookX1) && (kingY != rookY1) -> 2
        (kingY == rookY1) && (kingY != rookY2) && (kingX != rookX2) -> 1
        (kingY == rookY2) && (kingY != rookY1) && (kingY != rookY1) -> 2
        (kingX != rookX1) && (kingX != rookX2) && (kingY != rookY2) && (kingY != rookY1) ->0
        else -> 3
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
        (kingX != rookX) && (kingY != rookY) && (Math.abs(kingX - bishopX) != Math.abs(kingY - bishopY)) -> 0
        (kingX == rookX) && (Math.abs(kingX - bishopX) != Math.abs(kingY - bishopY)) -> 1
        (kingY == rookY) && (Math.abs(kingX - bishopX) != Math.abs(kingY - bishopY)) -> 1
        (kingX != rookX) && (kingY != rookY) && (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY)) -> 2
        (kingX == rookX) &&  (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY)) -> 3
        (kingY == rookY) && (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY)) -> 3
        else -> -1
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
    return when {
        ((a >= b) && (b >= c) || (a >= c) && (c >= b)) && (sqr(a) == sqr(b) + sqr(c)) -> 1
        ((b >= a) && (a >= c) || (b >=c) && (c >= a)) && (sqr(b) == sqr(a) + sqr(c)) -> 1
        ((c >= b) && (b >= a) || (c >= a) && (a >= b)) && (sqr(c) == sqr(a) + sqr(b)) -> 1
        ((a >= b) && (b >= c) || (a >= c) && (c >= b)) && (sqr(a) < sqr(b) + sqr(c))  -> 0
        ((b >= a) && (a >= c) || (b >= c) && (c >= a)) && (sqr(b) < sqr(a) + sqr(c)) -> 0
        ((c >= b) && (b >= a) || (c >= a) && (a >= b)) && (sqr(c) < sqr(a) + sqr(b)) -> 0
        ((a >= b) && (b >= c) || (a >= c) && (c >= b)) && (sqr(a) > sqr(b) + sqr(c)) && (a < b + c) -> 2
        ((b >= a) && (a >= c) || (b >= c) && (c >= a)) && (sqr(b) > sqr(a) + sqr(c)) && (b < a + c)  -> 2
        ((c >= b) && (b >= a) || (c >= a) && (a >= b)) && (sqr(c) > sqr(a) + sqr(b)) && (c < b + a)-> 2
        else -> -1
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
    return when{

        (a < c) && (c < b) && (b < d) -> b - c
        (a > c) && (a < d) && (d > b)-> b - a
        (c < a) && (a < d) && (d < b)-> d - a
        (a < c) && (d < b) -> d - c
        (a == b) && (a == c) && (c == d)-> 0
        (a == b) && (c < b) && (d > b)-> 0
        (c == d) && (a < c) && (b > c)-> 0
        (b == c)-> 0
        (a == d)-> 0
        else -> -1

    }
}