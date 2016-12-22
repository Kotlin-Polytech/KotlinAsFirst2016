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
    val ten = age % 10
    val oneHundred = age % 100
    return when {
        (ten == 1) && (oneHundred != 11) -> "$age год"
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
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s12 = s1 + s2
    val sAll = (s1 + s2 + t3 * v3) / 2
    //В прошлом сообщении, Вы написали: "Вот эту проверку лучше, наоборот, вытащить наружу (так как она проверяет входные данные на допустимость) в виде отдельного if с return"
    if (sAll > 0) {
        return when {
            (sAll <= s1) -> sAll / v1
            (sAll <= s12) -> (t1 + (sAll - s1) / v2)
            (sAll > s12) -> (t1 + t2 + (sAll - s12) / v3)
            else -> 0.0
        }
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
    val rook1Danger = (rookX1 == kingX) || (rookY1 == kingY)
    val rook2Danger = (rookX2 == kingX) || (rookY2 == kingY)
    return when {
        (rook1Danger && rook2Danger) -> 3
        (!rook2Danger && rook1Danger) -> 1
        (!rook1Danger && rook2Danger) -> 2
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
    val bishopDanger = ((kingX - kingY) == (bishopX - bishopY)) || ((bishopX - kingX) == (bishopY - kingY)) || (bishopX - kingX == -(bishopY - kingY))
    val rookDanger = (rookX == kingX) || (rookY == kingY)
    return when {
        bishopDanger && rookDanger -> 3
        bishopDanger && !rookDanger -> 2
        !bishopDanger && rookDanger -> 1
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
    when {((a >= b) && (a >= c)) -> {
        max = a
        min1 = b
        min2 = c

    }
        ((b >= c) && (a <= b)) -> {
            max = b
            min1 = a
            min2 = c
        }
        else -> {
            max = c
            min1 = a
            min2 = b
        }
    }
    val cosMax = (min1 * min1 + min2 * min2 - max * max) / 2 * min1 * min2
    return when { (min1 + min2 > max) -> when {
        (cosMax > 0.0) -> 0
        (cosMax == 0.0) -> 1
        else -> 2
    }
        else -> -1
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
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    (a > d) || ((a < d) && (c > b)) -> -1
    (d <= b) && (c < a) -> (d - a)
    (d <= b) && (c >= a) -> (d - c)
    (d > b) && (c < a) -> b - a
    (d > b) && (c >= a) -> b - c
    else -> 0
}


