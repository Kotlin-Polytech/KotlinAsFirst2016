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
    return when {
        (age % 10 == 1) -> when { (age % 100 == 11) -> "$age лет" else -> "$age год"}
        (age%100 in 12..14) -> "$age лет"
        (age%10 in 2..4) -> "$age года"
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
                   t3: Double, v3: Double): Double  {
    val sAll = (t1*v1+t2*v2+t3*v3)/2
    val s1 = t1*v1
    val s2 = t2*v2
    if (sAll>0) {
        if (sAll < s1) {
            val tHalf = sAll / v1
            return (tHalf)
        } else {
            if (sAll < s1 + s2) {
                val sBalance = sAll - s1
                val tBalance = sBalance / v2
                val tHalf = t1 + tBalance
                return (tHalf)
            } else {
                val sBalance = sAll - s1 - s2
                val tBalance = sBalance / v3
                val tHalf = t1 + t2 + tBalance
                return (tHalf)
            }
        }
    } else{
        val tHalf = 0.0
        return (tHalf)
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
    val rook1Danger: Boolean =(rookX1 == kingX)or(rookY1 == kingY)
    val rook2Danger: Boolean =(rookX2 == kingX)or(rookY2 == kingY)
    if ((rook1Danger == true)or(rook2Danger == true)){
        if ((rook1Danger == true)and(rook2Danger == true))
            return(3)
        else {
            if (rook1Danger == true)
                return(1)
            else return(2)
        }
    } else return(0)
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
    val bishopDanger: Boolean = ((kingX-kingY) == (bishopX-bishopY))or((bishopX-kingX) == (bishopY-kingY))or(bishopX-kingX == -(bishopY-kingY))
    val rookDanger: Boolean =(rookX == kingX)or (rookY == kingY)
    if (bishopDanger == true){
        if(rookDanger == true)
            return(3)
        else return(2)

    } else{
        if(rookDanger == true)
            return(1)
        else return(0)
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
    if ((a+b>c)and(b+c>a)and(a+c>b)){
        if
        ((a*a+b*b == c*c)or(b*b+c*c == a*a)or(c*c+a*a == b*b)){
            return(1)
        } else {
            val cosA=(b*b+c*c-a*a)/2*b*c
            val cosB=(a*a+c*c-b*b)/2*a*c
            val cosC=(b*b+a*a-c*c)/2*b*a
            if((cosA<0)or(cosB<0)or(cosC<0)){
                return(2)
            } else return(0)
        }
    } else return(-1)
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
    if (a > d) return (-1)
    else {
        if (c > b) return (-1)
        else {
            if (d < b) {
                if (c < a) return (d - a)
                else return (d - c)
            } else {
                if (c < a) return (b - a)
                else return (b - c)
            }
        }
    }
}