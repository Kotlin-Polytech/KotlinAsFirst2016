@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.*

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
    val endsOnWhatDouble = (age % 10)
    val endsOnWhatTriple = ((age % 100) % 10)
    if (age == 0) {
        return "$age лет"
    } else if ((age > 0) && (age < 5)) {
        return "$age года"
    } else if ((age > 4) && (age < 21)) {
        return "$age лет"
    } else if ((age < 100) && (endsOnWhatDouble == 1)) {
        return "$age год"
        } else if ((age > 99) && (endsOnWhatTriple == 1)) {
            return "$age год"
    } else if ((age < 100) && (endsOnWhatDouble < 5)) {
        return "$age года"
    } else if ((age < 100) && (endsOnWhatDouble > 4) || (endsOnWhatDouble == 0)) {
        return "$age лет"
    } else if ((age > 99) && (endsOnWhatTriple < 5)) {
        return "$age года"
    } else if ((age > 99) && (endsOnWhatTriple > 4) || (endsOnWhatTriple == 0)) {
        return "$age лет"
    } else return "Error"
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
    val halfWay = (t1 * v1 + t2 * v2 + t3 * v3) / 2.0
    if ((t1 * v1) >= halfWay) {
        return (halfWay / v1)
    } else if ((t1 * v1 + t2 * v2) >= halfWay) {
        return (t1 + ((halfWay - t1 * v1) / v2))
    } else if ((t1 * v1 + t2 * v2 + t3 * v3) >= halfWay) {
        return (t1 + t2 + ((halfWay - t1 * v1 - t2 * v2) / v3))
    } else return 0.0
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
    var threatLevel = 0
    if (kingX == rookX1) {
        threatLevel ++
    }
    if (kingX == rookX2) {
        threatLevel = threatLevel + 2
    }
    if (kingY == rookY1) {
        threatLevel ++
    }
    if (kingY == rookY2) {
        threatLevel = threatLevel + 2
    }
    return threatLevel
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
fun rookOrBishopThreatens(kingX: Int, kingY: Int, rookX: Int, rookY: Int, bishopX: Int, bishopY: Int): Int = TODO()

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var whichIsHypotenuse = 0
    if ((a > b) && (a > c)) {
        whichIsHypotenuse ++
    }
    if ((b > a) && (b > c)) {
        whichIsHypotenuse = whichIsHypotenuse + 2
    }
    if ((c > a) && (c > b)) {
        whichIsHypotenuse = whichIsHypotenuse + 3
    }
    if ((a == b) || (b == c) || (a == c)) return 0
    if (whichIsHypotenuse == 1) {
        if (a == sqrt(b * b + c * c)) return 1
    }
    if (whichIsHypotenuse == 2) {
        if (b == sqrt(a * a + c * c)) return 1
    }
    if (whichIsHypotenuse == 3) {
        if (c == sqrt(a* a + b * b)) return 1
    }
    if ((a > (b + c)) || (b > (a + c)) || (c > (a + b))) return -1
    return 2
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
    if ((a == b) && ((a > c) || (a == c)) && ((a < d) || (a == d))) return 0
    if ((a == b) && ((b > c) || (b == c)) && ((b < d) || (b == d))) return 0
    if ((a < c) && (b == c)) return 0
    if ((c < a) && (d == a)) return 0
    if ((c < b) && (a < c) && (b < d)) return (b - c)
    if ((a < c) && (c < d) && (d < b)) return (d - c)
    if ((a == c) && (b == d)) return (b - a)
    if ((c < a) && (a < b) && (b < d)) return (b - a)
    if ((c < a) && (a < d) && (d < b)) return (d - a)
    return -1
}