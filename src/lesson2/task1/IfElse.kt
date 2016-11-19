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
    val a: Int = age % 10
    if (age % 100 in 5..19)
        return "$age лет"
    if (a in 5..9)
        return "$age лет"
    if (a == 0)
        return "$age лет"
    if (a == 1)
        return "$age год"
    if (a in 2..4)
        return "$age года"
    return "invalid input"
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
    if ((((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2) < (t1 * v1)) return ((((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2) / v1)
    else if ((((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2) < ((t1 * v1) + (t2 * v2))) return (((((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2 - (t1 * v1)) / v2) + t1)
    else return ((((t1 * v1) + (t2 * v2) + (t3 * v3)) / 2 - (t1 * v1 + t2 * v2)) / v3 + t1 + t2)


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
    if (((kingX == rookX1) || (kingY == rookY1)) && ((kingX == rookX2) || (kingY == rookY2))) return 3
    else if ((kingX == rookX1) || (kingY == rookY1)) return 1
    else if ((kingX == rookX2) || (kingY == rookY2)) return 2
    else return 0
    var danger1 = 0
    var danger2 = 0
    if ((kingX == rookX1) || (kingY == rookY1)) danger1 = 1
    if ((kingX == rookX2) || (kingY == rookY2)) danger2 = 2
    return danger1 + danger2
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
    if (((kingX == rookX) || (kingY == rookY)) && (((Math.abs(kingX - bishopX)) == (Math.abs(kingY - bishopY))))) return 3
    if ((kingX == rookX) || (kingY == rookY)) return 1
    else if ((Math.abs(kingX - bishopX)) == (Math.abs(kingY - bishopY))) return 2
    else return 0
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
    if ((a + b > c) && (a + c > b) && (c + b > a)) {
        if ((a * a + b * b < c * c) || (a * a + c * c < b * b) || (b * b + c * c < a * a)) return 2
        else if ((a * a + b * b == c * c) || (a * a + c * c == b * b) || (b * b + c * c == a * a)) return 1
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
    var q: Int = 0
    if (c <= a && d >= b) q = (b - a) else
        if (c <= a && d >= a && d <= b) q = (d - a) else
            if (c >= a && c <= b && d >= b) q = (b - c) else
                if (c >= a && c <= b && d <= b) q = (d - c) else q = -1
    return q
}


