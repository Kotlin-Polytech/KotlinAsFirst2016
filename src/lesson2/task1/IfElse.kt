@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant

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
    (age % 10 == 0) -> "$age лет"
    ((age % 10 == 1) && (age % 100 != 11)) -> "$age год"
    ((age % 10 in 2..4) && (age % 100 !in 12..14)) -> "$age года"
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
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val S_half = (s1 + s2 + s3) / 2
    if (S_half <= s1) return S_half / v1
    else if ((S_half > s1) && (S_half <= (s1 + s2))) return t1 + ((S_half - s1) / v2)
    else if ((S_half > (s1 + s2))) return t1 + t2 + ((S_half - s1 - s2) / v3)
    else return 0.0

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
                       rookX2: Int, rookY2: Int): Int =
        if (((kingX == rookX1) || (kingY == rookY1)) && ((kingX !== rookX2) && kingY !== rookY2)) 1
        else if (((kingX == rookX2) || (kingY == rookY2)) && ((kingX !== rookX1) && kingY !== rookY1)) 2
        else if (((kingX == rookX2) || (kingY == rookY2)) && ((kingX == rookX1) || kingY == rookY1)) 3
        else if (((kingX !== rookX2) && (kingY !== rookY2)) && ((kingX !== rookX1) && (kingY !== rookY1))) 0
        else 9

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
                          bishopX: Int, bishopY: Int): Int =
        if ((kingX !== rookX) && (kingY !== rookY) && (Math.abs(kingX - bishopX) !== Math.abs(kingY - bishopY))) 0
        else if (((kingX == rookX) || (kingY == rookY)) && (Math.abs(kingX - bishopX) !== Math.abs(kingY - bishopY))) 1
        else if (((kingX !== rookX) && (kingY !== rookY)) && (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY))) 2
        else if (((kingX == rookX) || (kingY == rookY)) && (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY))) 3
        else 0

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun sqr(x: Double): Double = x * x

fun triangleKind(a: Double, b: Double, c: Double): Int {
    if ((a == b) && (b == c) && (c == a)) return 0
    if ((a > b) && (a > c)) {
        if (b + c <= a) return -1
        else if (sqr(b) + sqr(c) > sqr(a)) return 0
        else if (sqr(b) + sqr(c) < sqr(a)) return 2
        else if (sqr(b) + sqr(c) == sqr(a)) return 1
    }
    if ((b > a) && (b > c)) {
        if ((a + c <= b)) return -1
        else if (sqr(a) + sqr(c) == sqr(b)) return 1
        else if (sqr(a) + sqr(c) < sqr(b)) return 2
        else if (sqr(a) + sqr(c) > sqr(b)) return 0
    }
    if ((c > a) && (c > b)) {
        if (a + b <= c) return -1
        else if (sqr(a) + sqr(b) == sqr(c)) return 1
        else if (sqr(a) + sqr(b) < sqr(c)) return 2
        else if (sqr(a) + sqr(b) > sqr(c)) return 0
    }
    return 0
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
        if (((b < c) && (a < c)) || ((c < a) && (d < a))) -1
        else if ((a <= c) && (d <= b)) d - c
        else if ((c <= a) && (b <= d)) b - a
        else if ((a <= c) && (c <= b) && (b <= d)) b - c
        else if ((c <= a) && (a <= d) && (d <= b)) d - a
        else 0