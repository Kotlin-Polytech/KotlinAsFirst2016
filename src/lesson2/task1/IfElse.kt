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
fun ageDescription(age: Int): String {
    val a = age % 10
    when {
        a >= 5 || a == 0 || age in 11..14 || age % 100 in 11..14 -> return ("$age лет")
        a == 1 -> return ("$age год")
        else -> return ("$age года")
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
    val halfWay = (s1 + s2 + s3) / 2
    if (s1 >= halfWay) return halfWay / v1
    else if (s1 + s2 >= halfWay) return t1 + (halfWay - s1) / v2
    else return t1 + t2 + (halfWay - s1 - s2) / v3

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
    return when{
        (kingX != rookX1) && (kingX != rookX2) && (kingY != rookY1) && (kingY != rookY2) -> 0
        ((kingX == rookX1) && (kingY == rookY2)) || ((kingX == rookX2) && (kingY == rookY1)) -> 3
        (kingX == rookX1) || (kingY == rookY1) -> 1
        else -> 2
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
        ((kingX == rookX) && (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY))) ||
                ((kingY == rookY) && (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY))) -> 3
        (kingX == rookX) || (kingY == rookY) -> 1
        else -> 2
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
    val sqrA = a * a
    val sqrB = b * b
    val sqrC = c * c
    return when {
        ((a + b) < c) || ((a + c) < b) || ((b + c) < a) -> -1
        ((sqrA + sqrB) == sqrC)|| ((sqrA + sqrC) == sqrB) || ((sqrB + sqrC) == sqrA) -> 1
        ((sqrA + sqrB) < sqrC) || ((sqrA + sqrC) < sqrB) || ((sqrB + sqrC) < sqrA) -> 2
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
    return when {
        (c > b) || (a > d) -> -1
        ((c >= a) && (c <= b) && (b <= d)) -> b - c
        ((a >= c) && (d >= a) && (d <= b)) -> d - a
        (c >= a) && (c <= b) && (d <= b) -> d - c
        else -> b - a
    }
}