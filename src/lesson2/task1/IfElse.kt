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
    val n1 = age % 10
    val n2 = age / 10 % 10
    return when {
        ((n1 == 1) && (n2 == 1)) -> ("$age лет")
        ((n1 == 1) && (n2 != 1)) -> ("$age год")
        ((n1 in 2..4) && (n2 == 1)) -> ("$age лет")
        ((n1 in 2..4) && (n2 != 1)) -> ("$age года")
        else -> ("$age лет")
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
    val halfway = (s1 + s2 + s3) / 2
    return when {
        (s1 >= halfway) -> (halfway / v1)
        ((s1 + s2) > halfway) -> (t1 + (halfway - s1) / v2)
        ((s1 + s2) == halfway) -> (t1 + t2)
        else -> (t1 + t2 + (halfway - s1 - s2) / v3)
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
        (((kingX == rookX1) || (kingY == rookY1)) && ((kingX == rookX2) || (kingY == rookY2))) -> 3
        (((kingX == rookX1) || (kingY == rookY1)) && ((kingX != rookX2) && kingY != rookY2)) -> 1
        (((kingX == rookX2) || (kingY == rookY2)) && ((kingX != rookX1) && kingY != rookY1)) -> 2
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
    val condX = Math.abs(kingX - bishopX)
    val condY = Math.abs(kingY - bishopY)
    return when {
        (((kingX == rookX) || (kingY == rookY)) && (condX == condY)) -> 3
        (((kingX == rookX) || (kingY == rookY)) && (condX != condY)) -> 1
        (((kingX != rookX) && (kingY != rookY)) && (condX == condY)) -> 2
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
    if ((a + b > c) && (a + c > b) && (b + c > a)) {
        return when {
            ((a * a + b * b < c * c) || (c * c + b * b < a * a) || (c * c + a * a < b * b)) -> 2
            ((a * a + b * b == c * c) || (c * c + b * b == a * a) || (c * c + a * a == b * b)) -> 1
            else -> 0
        }
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
    val m = Math.max(a, c)
    val n = Math.min(b, d)
    if (n >= m) return (n - m)
    else return -1
}

