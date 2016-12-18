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
    if (age > 20 && age % 10 == 1 && age != 111 || age == 1)
        return "$age год"
    if (age > 20 && age % 10 > 4 && age % 10 <= 9 || age % 10 == 0 || age >= 5 && age <= 19 || age >= 111 && age <= 119)
        return "$age лет"
    if (age > 20 && age % 10 > 1 && age % 10 < 5 || age >= 2 && age <= 4)
        return "$age года"
    else return "-1" // -1 - это ошибка
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
    var time = 0.0
    val s1 = v1 * t1
    val s2 = v2 * t2
    val s3 = v3 * t3
    val halfWay = (s1 + s2 + s3) / 2
    if (halfWay <= s1) time = halfWay / v1
    else
        if (halfWay > s1 && halfWay < s1 + s2) time = t1 + (halfWay - s1) / v2
        else
            time = t1 + t2 + (halfWay - s1 - s2) / v3
    return time

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
                       rookX2: Int, rookY2: Int): Int = TODO()

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
                          bishopX: Int, bishopY: Int): Int = TODO()

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var minSide1 = 0.0
    var minSide2 = 0.0
    if (a + b > c && a + c > b && b + c > a) {
        val maxSide = Math.max(a, Math.max(b, c))
        if (maxSide == a) {
            minSide1 = b
            minSide2 = c
        }
        if (maxSide == b) {
            minSide1 = a
            minSide2 = c
        }
        if (maxSide == c) {
            minSide1 = a
            minSide2 = b
        }
        return when {
            maxSide * maxSide == minSide1 * minSide1 + minSide2 * minSide2 -> 1
            maxSide * maxSide < minSide1 * minSide1 + minSide2 * minSide2 -> 0
            else -> 2
        }
    }
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
    return when {
        c in a..b && d !in a..b -> b - c
        c in a..b && d in a..b -> d - c
        a in c..d && b in c..d -> b - a
        a in c..d && b !in c..d -> d - a
        a in c..d && a > 0 && a - c > d - a -> d - a
        a in c..d && a > 0 && a - c < d - a -> a - c
        a in c..d && a < 0 && a - c > d - a -> a - c
        a in c..d && a < 0 && a - c < d - a -> d - a
        else -> -1
    }
}



