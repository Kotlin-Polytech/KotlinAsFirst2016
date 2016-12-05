@file:Suppress("UNUSED_PARAMETER", "UNREACHABLE_CODE")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr

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
    val m = age / 10
    when (age) {
        in 5..20, in 105..120              -> return ("$age лет")
        10 * m + 1                         -> return ("$age год")
        10 * m + 2, 10 * m + 3, 10 * m + 4 -> return ("$age года")
        else                               -> return ("$age лет")
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
    val s1 = v1 * t1
    val s2 = v2 * t2
    val s3 = v3 * t3
    val s = (s1 + s2 + s3) / 2.0
    when {
        s <= s1                   -> return (s / v1)
        (s > s1) && (s < s1 + s2) -> return (t1 + (s - s1) / v2)
        else                      -> return (t1 + t2 + (s - s1 - s2) / v3)
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
    val rt1 = (kingX == rookX1) || (kingY == rookY1)
    val rt2 = (kingX == rookX2) || (kingY == rookY2)
    when {
        rt1 && rt2  -> return 3
        !rt1 && rt2 -> return 2
        rt1 && !rt2 -> return 1
        else        -> return 0
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
    val rt = (kingX == rookX) || (kingY == rookY)
    val bt = (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY))
    when {
        rt && bt  -> return 3
        !rt && bt -> return 2
        rt && !bt -> return 1
        else      -> return 0
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
    if ((a >= b + c) || (b >= a + c) || (c >= a + b)) return -1
    else {
        val alpha = Math.acos((sqr(b) + sqr(c) - sqr(a)) / (2.0 * b * c))
        val beta = Math.acos((sqr(a) + sqr(c) - sqr(b)) / (2.0 * a * c))
        val gamma = Math.PI - (alpha + beta)
        val maxAngle = Math.max(Math.max(alpha, beta), Math.max(beta, gamma))
        when {
            (maxAngle > 0.0) && (maxAngle < Math.PI / 2.0)     -> return 0
            maxAngle == Math.PI / 2.0                          -> return 1
            (maxAngle > Math.PI / 2.0) && (maxAngle < Math.PI) -> return 2
            else                                               -> return -1
        }
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
    (c in a..b) && (d > b) -> b - c
    (c in a..b) && (d <= b) -> d - c
    (c <= a) && (b <= d) -> b - a
    (c <= a) && (d in a..b) -> d - a
    else -> -1
}