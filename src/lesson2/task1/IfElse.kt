@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
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
    val a = age % 10;
    if (a == 0 || a >= 5 || age in 105..120 || (age in 5..20)) {
        return "$age лет"
    } else if (a > 1 && a < 5) {
        return "$age года"
    } else {
        return "$age год"
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
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val s = (s1 + s2 + s3) / 2
    if (s <= s1) {
        return s / v1
    } else if (s <= s1 + s2) {
        return t1 + (s - s1) / v2
    } else {
        return t1 + t2 + (s - s1 - s2) / v3
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
    var result = 0
    val threatFrom1 = (kingX == rookX1 || kingY == rookY1)
    val threatFrom2 = (kingX == rookX2 || kingY == rookY2)
    if (threatFrom1) {
        result = 1
    }
    if (threatFrom2) {
        result = 2
    }
    if (threatFrom1 && threatFrom2) {
        result = 3
    }
    return result
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
    var result = 0
    val threatFromRook = (kingX == rookX || kingY == rookY)
    val threatFromBishop = (abs(kingX - bishopX) == abs(kingY - bishopY))
    if (threatFromRook) {
        result = 1
    }
    if (threatFromBishop) {
        result = 2
    }
    if (threatFromRook && threatFromBishop) {
        result = 3
    }
    return result
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
    if (a + b > c && b + c > a && a + c > b) {
        if (sqr(a) + sqr(b) == sqr(c) || sqr(b) + sqr(c) == sqr(a) || sqr(a) + sqr(c) == sqr(b)) return 1
        else if (sqr(a) + sqr(b) < sqr(c) || sqr(b) + sqr(c) < sqr(a) || sqr(a) + sqr(c) < sqr(b)) return 2
        else return 0
    } else return -1
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
    if (b in c..d && a in c..d) {
        return (b - a)
    } else if (d in a..b && c in a..b) {
        return d - c
    } else if (c in a..b) {
        return b - c
    } else if (a in c..d) {
        return d - a
    } else if (b == c || d == a) {
        return 0
    } else return -1
}
