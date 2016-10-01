@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double ,b: Double ,c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a ,b ,c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1 ,y2)       // 5
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

    if ((age % 10 == 1) && (age / 10 != 1) && (age / 10 != 11)) {
        return "$age год"
    }
    else {
        if ((age % 10 > 1) && (age % 10 < 5) && (age / 10 != 1) && (age / 10 != 11)) {
            return "$age года"
        }
        else {
            return "$age лет"
        }
    }

}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double ,v1: Double ,
                   t2: Double ,v2: Double ,
                   t3: Double ,v3: Double): Double {

    var ss1: Double = v1 * t1
    var ss2: Double = v2 * t2
    var ss3: Double = v3 * t3
    val s2 = (((ss1) + (ss2) + (ss3)) / 2)
    if (s2 <= ss1) {
        return s2 / v1
    }
    else {
        if (s2 <= (ss1) + (ss2)) {
            return ((s2 - (ss1)) / v2) + t1
        }
        else {
            return ((s2 - (ss1) - (ss2)) / v3) + t1 + t2
        }
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
fun whichRookThreatens(kingX: Int ,kingY: Int ,
                       rookX1: Int ,rookY1: Int ,
                       rookX2: Int ,rookY2: Int): Int {
    var BRookx1: Boolean = kingX == rookX1
    var BRookx2: Boolean = kingX == rookX2
    var BRooky1: Boolean = kingY == rookY1
    var BRooky2: Boolean = kingY == rookY2
    if (BRookx1 == false && BRookx2 == false && BRooky1 == false && BRooky2 == false) {
        return 0
    }
    else {
        if ((BRookx1 == true && BRookx2 == false && BRooky2 == false) || (BRooky1 == true && BRookx2 == false && BRooky2 == false)) {
            return 1
        }
        else {
            if ((BRookx1 == false && BRookx2 == true && BRooky1 == false) || (BRooky2 == true && BRookx1 == false && BRooky1 == false)) {
                return 2
            }
            else {
                return 3
            }
        }
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
fun rookOrBishopThreatens(kingX: Int ,kingY: Int ,
                          rookX: Int ,rookY: Int ,
                          bishopX: Int ,bishopY: Int): Int {
    var BBishop: Boolean = abs(kingX - bishopX) == abs(kingY - bishopY)
    var BRookx: Boolean = kingX == rookX
    var BRooky: Boolean = kingY == rookY
    if (BBishop == true && BRookx == false && BRooky == false) {
        return 2
    }
    else {
        if (BBishop == false && (BRookx == true || BRooky == true)) {
            return 1
        }
        else {
            if (BBishop == true && (BRookx == true || BRooky == true)) {
                return 3
            }
            else {
                return 0
            }
        }
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
fun triangleKind(a: Double ,b: Double ,c: Double): Int {
    var min: Double
    val nor: Double
    var max: Double

    if (a > b) {
        max = a
    }
    else {
        max = b
    }
    if (c > max) {
        max = c
    }
    if (a > b) {
        min = b
    }
    else {
        min = a
    }
    if (c < min) {
        min = c
    }
    nor = a + b + c - max - min
    if (max >= min + nor) {
        return -1
    }
    else
        if (min * min + nor * nor == max * max) {
            return 1
        }
        else
            if ((min * min + nor * nor < max * max)) {
                return 2
            }
            else {
                return 0
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
fun segmentLength(a: Int ,b: Int ,c: Int ,d: Int): Int {
    if (d >= b) {
        if (c > b) {
            return -1
        }
        else {
            if (b >= c && a <= c) {
                return b - c
            }
            else {
                return b - a
            }
        }
    }
    else {
        if (a > d) {
            return -1
        }
        else {
            if (d >= a && c <= a) {
                return d - a
            }
            else {
                return d - c
            }
        }
    }
}