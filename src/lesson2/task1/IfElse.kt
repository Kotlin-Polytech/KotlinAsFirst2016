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
    return when {
        (age % 10 >= 5) && (age % 10 <= 9) -> "$age лет"
        age % 10 == 0 -> "$age лет"
        (age % 100 >= 10) && (age % 100 <= 19) -> "$age лет"
        (age % 10 >= 2) && (age % 10 <= 4) -> "$age года"
        else -> "$age год"
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
    val halfallway=(t1*v1+t2*v2+t3*v3)/2
    val FirstPartWay=t1*v1
    val SecondPartWay=t1*v1+t2*v2
    if (halfallway < (FirstPartWay)) return ((halfallway) / v1)  //есди первый отрезок
    else if ((halfallway) < (SecondPartWay)) return (((halfallway - FirstPartWay) / v2) + t1) //если второй отрезок
    else return ((halfallway - SecondPartWay) / v3 + t1 + t2) //если третий отрезок


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
    val firstrook:Boolean=(kingX==rookX1)|| (kingY==rookY1)
    val Secondrook:Boolean=(kingX == rookX2) || (kingY == rookY2)
    if ((firstrook) && (Secondrook)) return 3 //от 2
    else if (firstrook) return 1
    else if (Secondrook) return 2
    else return 0
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
    val rook:Boolean=(kingX==rookX)|| (kingY==rookY)
    val bishop:Boolean=(Math.abs(kingX - bishopX)) == (Math.abs(kingY - bishopY))
    if (rook && bishop) return 3
    if (rook) return 1
    else if (bishop) return 2
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
    return when{
        c <= a && d >= b-> b-a
        c <= a && d >= a && d <= b -> d-a
        c >= a && c <= b && d >= b-> b-c
        c >= a && c <= b && d <= b-> d-c
        else-> -1
    }
}
