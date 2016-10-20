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
    val ten = age % 10
    val oneHundred = age % 100
    return when {
        (ten == 1) ->
            when { (oneHundred == 11) -> "$age лет"
                else -> "$age год"
            }
        (oneHundred in 12..14) -> "$age лет"
        (ten in 2..4) -> "$age года"
        else -> "$age лет"
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
    val sAll = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s12 = s1 + s2
    return when {
        (sAll > 0) ->
            when {
                (sAll <= s1) -> sAll / v1
                (sAll > s1) and (sAll <= s12) -> (t1 + (sAll - s1) / v2)
                (sAll > s12) -> (t1 + t2 + (sAll - s12) / v3)
                else -> 0.0
            }
        else -> 0.0
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
    val rook1Danger = (rookX1 == kingX) or (rookY1 == kingY)
    val rook2Danger = (rookX2 == kingX) or (rookY2 == kingY)
    return when {((rook1Danger == true) or (rook2Danger == true)) ->
        when {((rook1Danger == true) and (rook2Danger == true)) -> 3
            (((rook1Danger == false) or (rook2Danger == false)) and (rook1Danger == true)) -> 1
            (((rook1Danger == false) or (rook2Danger == false)) and (rook1Danger == false)) -> 2
            else -> 0
        }
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
    val bishopDanger = ((kingX - kingY) == (bishopX - bishopY)) or ((bishopX - kingX) == (bishopY - kingY)) or (bishopX - kingX == -(bishopY - kingY))
    val rookDanger = (rookX == kingX) or (rookY == kingY)
    return when {(bishopDanger == true) and (rookDanger == true) -> 3
        (bishopDanger == true) and (rookDanger == false) -> 2
        (bishopDanger == false) and (rookDanger == true) -> 1
        (bishopDanger == false) and (rookDanger == false) -> 0
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
fun triangleKind(a: Double, b: Double, c: Double)
        : Int {
    var max = 0.0
    var min1 = 0.0
    var min2 = 0.0
    if ((a >= b) && (a >= c)) {
        max = a
        min1 = b
        min2 = c

    } else {
        if ((b >= c) && (a <= b)) {
            max = b
            min1 = a
            min2 = c
        } else {
            max = c
            min1 = a
            min2 = b
        }
    }
    if (min1 + min2 > max) {
        val cosMax = (min1 * min1 + min2 * min2 - max * max) / 2 * min1 * min2
        if (cosMax >= 0.0) {
            if (cosMax == 0.0) return 1
            else return 0
        } else return 2
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
    return when {
        (a > d) or ((a < d) and (c > b)) -> -1
        else ->
            when {
                (d <= b) ->
                    when { (c < a) -> (d - a)
                        else -> (d - c)
                    }
                (d > b) ->
                    when { (c < a) -> b - a
                        else -> b - c
                    }
                else -> 0
            }
    }

}

