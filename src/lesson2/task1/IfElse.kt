@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
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
fun ageDescription(age: Int): String {
    return when {
        ((age % 10 == 0) || (age / 10 == 1) || (age / 10 == 11) || (age % 10 in 5..9)) -> "$age лет"
        (age % 10 == 1) -> "$age год"
        else -> "$age года"
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
    val halfS = (s1 + s2 + s3) / 2
    return when {
        halfS <= s1 -> halfS / v1
        halfS <= s1 + s2 -> (halfS - s1) / v2 + t1
        else -> (halfS - s1 - s2) / v3 + t1 + t2

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
                       rookX2: Int, rookY2: Int): Int = TODO()

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
    val bishopThreaten = (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY))
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
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if (((a + b) <= c) || ((b + c) <= a) || ((a + c) <= b)) return -1
    else {
        val cosa = (b * b + c * c - a * a) / (2 * b * c)
        val cosb = (a * a + c * c - b * b) / (2 * a * c)
        val cosc = (a * a + b * b - c * c) / (2 * a * b)
        if ((Math.abs(cosa) < 0.00001) || (Math.abs(cosb) < 0.00001) || (Math.abs(cosc) < 0.00001)) return 1 else
            if ((cosa < 0) || (cosb < 0) || (cosc < 0)) return 2
            else return 0
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
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int =
        when {
            ((c >= a) && (c <= b)) && (d <= b) -> d - c
            ((a >= c) && (c <= d)) && (b <= d) -> b - a
            !(((b >= c) && (b <= d)) || ((c >= a) && (c <= b)) || ((d >= a) && (d <= b)) || ((a >= c) && (a <= d))) -> -1
            (b - c) <= (d - a) -> b - c
            else -> d - a
        }
