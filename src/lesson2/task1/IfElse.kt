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
    if (age % 100 in 5..20) return "$age лет"
    else if (age % 10 == 1) return "$age год"
    else if (age % 10 in 2..4) return "$age года"
    else return "$age лет"

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
    val s0 = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    val s1 = t1 * v1
    val s2 = t2 * v2
    if (s0 < s1) return s0 / v1
    else if (s0 in s1..(s1 + s2)) return t1 + (s0 - s1) / v2
    else return t1 + t2 + (s0 - s1 - s2) / v3

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
    return when {((kingX == rookX1 || kingX == rookX2) && (kingY == rookY1 || kingY == rookY2)) -> 3
        ((kingX == rookX1 || kingY == rookY1) && (kingX != rookX2 && kingY != rookY2)) -> 1
        ((kingX == rookX2 || kingY == rookY2) && (kingX != rookX1 && kingY != rookY1)) -> 2
        else -> 0
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
    return when {((kingX == rookX || kingY == rookY) && (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY))) -> 3
        ((kingX == rookX || kingY == rookY) && (Math.abs(kingX - bishopX) != Math.abs(kingY - bishopY))) -> 1
        (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY) && (kingX != rookX && kingY != rookY)) -> 2
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
    return when { (a + b < c || a + c < b || b + c < a) -> -1
        (a * a == b * b + c * c || b * b == a * a + c * c || c * c == b * b + a * a) -> 1
        (a * a > b * b + c * c || b * b > c * c + a * a || c * c > b * b + a * a) -> 2
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
    if (b >= c && a <= c && b <= d) return b - c
    else if (a <= d && c <= a && b >= d) return d - a
    else if (a >= c && b <= d) return b - a
    else if (a <= c && b >= d) return d - c
    else return -1
}