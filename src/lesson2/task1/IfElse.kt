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
fun ageDescription(age: Int): String {
    return when {
        age % 10 == 1 && age / 10 != 1 && age / 10 != 11 -> "$age год"
        age % 10 in 1..4 && age / 10 != 1 && age / 10 != 11 -> "$age года"
        else -> "$age лет"
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
    val FirstRook = (kingX == rookX1 || kingY == rookY1)
    val SecondRook = (kingX == rookX2 || kingY == rookY2)
    return when {
        !FirstRook && !SecondRook -> 0
        FirstRook && !SecondRook -> 1
        !FirstRook && SecondRook -> 2
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
    val BishopThreaten = (abs(kingX - bishopX) == abs(kingY - bishopY))
    val RookThreaten = (kingX == rookX) || (kingY == rookY)
    return when {
        RookThreaten && !BishopThreaten -> 1
        !RookThreaten && BishopThreaten -> 2
        RookThreaten && BishopThreaten -> 3
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
fun MaxofThree(a: Double, b: Double, c: Double): Double {
    var max = 0.0
    if (a > b) {max = a} else {max = b}
    if (c > max) {max = c}
    return max
}

fun MinofThree(a: Double, b: Double, c: Double): Double{
    var min= 0.0
    if (a < b) {min = a} else {min = b}
    if (c < min) {min = c}
    return min
}

fun MediumofThree(a: Double, b: Double, c: Double): Double = a + b + c - MinofThree(a,b,c) - MaxofThree(a,b,c)

fun quad(x: Double) = x * x

fun triangleKind(a: Double, b: Double, c: Double): Int {
    val min = MinofThree(a,b,c)
    val medium =  MediumofThree(a,b,c)
    val max = MaxofThree(a,b,c)
    return when {
        max >= min + medium -> -1
        quad(min)  + quad(medium) == quad(max) -> 1
        quad(min) + quad(medium)< quad(max) -> 2
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
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if (d >= b) {
        return when {
            c > b -> -1
            (b >= c && a <= c) -> b - c
            else -> b - a
        }
    } else {
        return when {
            a > d -> -1
            d >= a && c <= a -> d - a
            else -> d - c
        }
    }
}


