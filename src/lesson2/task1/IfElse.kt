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
fun ageDescription(age: Int): String = when {
        age % 10 == 1 && age / 10 != 1 && age / 10 != 11 -> "$age год"
        age % 10 in 2..4 && age / 10 != 1 && age / 10 != 11 -> "$age года"
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
    val ss1 = v1 * t1
    val ss2 = v2 * t2
    val ss3 = v3 * t3
    val s2 = (ss1 + ss2 + ss3) / 2
    return when {
        s2 <= ss1 -> s2 / v1
        s2 <= ss1 + ss2 -> (s2 - ss1) / v2 + t1
        else -> (s2 - ss1 - ss2) / v3 + t1 + t2
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
    val firstRook = (kingX == rookX1 || kingY == rookY1)
    val secondRook = (kingX == rookX2 || kingY == rookY2)
    return when {
        !firstRook && !secondRook -> 0
        firstRook && !secondRook -> 1
        !firstRook && secondRook -> 2
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
    val bishopThreaten = (abs(kingX - bishopX) == abs(kingY - bishopY))
    val rookThreaten = (kingX == rookX) || (kingY == rookY)
    return when {
        rookThreaten && !bishopThreaten -> 1
        !rookThreaten && bishopThreaten -> 2
        rookThreaten && bishopThreaten -> 3
        else -> 0

    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 *
 */
fun maxOfThree(a: Double, b: Double, c: Double): Double {
    var max = 0.0
    if (a > b) max = a else max = b
    if (c > max) max = c
    return max
}

fun minOfThree(a: Double, b: Double, c: Double): Double{
    var min= 0.0
    if (a < b) min = a else min = b
    if (c < min) min = c
    return min
}

fun mediumOfThree(a: Double, b: Double, c: Double): Double =
        a + b + c - minOfThree(a ,b, c) - maxOfThree(a, b, c)

fun sqr(x: Double) = x * x

fun triangleKind(a: Double, b: Double, c: Double): Int {
    val min = minOfThree(a, b, c)
    val medium =  mediumOfThree(a, b, c)
    val max = maxOfThree(a, b, c)
    return when {
        max >= min + medium -> -1
        sqr(min) + sqr(medium) == sqr(max) -> 1
        sqr(min) + sqr(medium) < sqr(max) -> 2
        else -> 0
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
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    Math.min(b, d) - Math.max(a, c) >= 0 -> Math.min(b, d) - Math.max(a, c)
    else -> -1
}


