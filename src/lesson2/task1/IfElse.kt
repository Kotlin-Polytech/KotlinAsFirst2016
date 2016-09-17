@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr

/**
 * Пример
 *
 * Найти навименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
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
    val one = age % 10
    val two = age % 100
    if (one == 1 && two != 11) return "$age год"
    if (one >= 2 && one <= 4 && (two < 10 || two >= 20)) return "$age года"
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
                   t3: Double, v3: Double): Double = TODO()

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
    if ((kingX == rookX1 || kingY == rookY1) && (kingX != rookX2 && kingX != rookY2)) return 1
    if ((kingX != rookX1 && kingY != rookY1) && (kingX == rookX2 || kingX == rookY2)) return 2
    if (kingX != rookX1 && kingX != rookY1 && kingX != rookX2 && kingX !=rookY2) return 0 else return 3
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
    if (((Math.abs(kingX - bishopX)) != (Math.abs(kingY - bishopY)))) {
        if (kingX != rookX && kingY != rookY) return 0 else
            if (kingX == rookX || kingY == rookY) return 1
    }
    if ((Math.abs(kingX - bishopX)) == (Math.abs(kingY - bishopY))) {
        if (kingX != rookX && kingY != rookY) return 2 else
            if (kingX == rookX || kingY == rookY) return 3
    }
    return 0
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
    if (a + b <= c || a + c <= b || b + c <= a) return -1
    if (sqr(c) == sqr(a) - sqr(b)) return 1
    if (sqr(c) < sqr(a) + sqr(b)) return 2
    if (sqr(c) < sqr(a) + sqr(b)) return 0
    return -1
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
    if (b < c && b < d) return -1
    if (a > c && a > d) return -1
    if (b == c || a == d) return 0
    if (c < b && b < d && c > a && a < d) {
        val long = b - c
        return long
    }
    if (a < c && d < b) {
        val long = d - c
        return long
    }
    if (c < a && a < d && d < b) {
        val long = d - a
        return long
    }
    if (a == c && d == b) {
        val long = d - c
        return long
    }
    if (a == d) return 0
    if (c < a && b < d && a < d) {
        val long = b - a
        return long
    }
    if (a == c) {
        if (b < d) {
            val long = b - a
            return long
        } else if (b > d) {
            val long = d - c
            return long
        }
    }
    if (b == d) {
        if (a < c) {
            val long = d - c
            return long
        } else if (a > c) {
            val long = b - a
            return long
        }
    }
    return 0
}
