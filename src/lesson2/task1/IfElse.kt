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
    if (age % 10 == 1 && age % 100 != 11) return "$age год"
    else if (age % 10 in 1..4 && age % 100 !in 10..19) return "$age года"
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
    val s1 = v1 * t1
    val s2 = v2 * t2
    val s3 = v3 * t3
    val s = (s1 + s2 + s3) / 2.0
    if (s <= s1) return (s / v1)
    else if (s <= s1 + s2) return (t1 + (s - s1) / v2)
    else return (t1 + t2 + (s - s1 - s2) / v3)
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

    /*  if (((kingX==rookX1)||(kingY==rookY1)) && ((kingX==rookX2)||(kingY==rookY2))) return 3
      else if ((kingX==rookX1)||(kingY==rookY1))return 1
      else if ((kingX==rookX2)||(kingY==rookY2))return 2
      else return 0
      */
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
    var dangerRook = 0
    var dangerBishop = 0
    if ((kingX == rookX) || (kingY == rookY)) dangerRook = 1
    if (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY)) dangerBishop = 2
    return dangerRook + dangerBishop
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
    var max = a
    var side1 = b
    var side2 = c
    if (b >= a && b >= c) {
        max = b
        side1 = a
        side2 = c
    } else if (c >= a && c >= b) {
        max = c
        side1 = a
        side2 = b
    }
    return when {
        (max > side1 + side2) -> -1
        (side1 * side1 + side2 * side2 == max * max) -> 1
        (side1 * side1 + side2 * side2 <= max * max) -> 2
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
    /* if (c <= b && c >= a && d >= b) return (b - c)
     else if (c <= a && d <= b && d >= a) return (d - a)
     else if (d <= b && d >= a && c <= b && c >= a) return (d - c)
     else if (b <= d && b >= c && a <= d && a >= c) return (b - a)
     else return -1 */
    return when {
        (c <= b && c >= a && d >= b) -> (b - c)
        (c <= a && d <= b && d >= a) -> (d - a)
        (d <= b && d >= a && c <= b && c >= a) -> (d - c)
        (b <= d && b >= c && a <= d && a >= c) -> (b - a)
        else -> -1
    }
}