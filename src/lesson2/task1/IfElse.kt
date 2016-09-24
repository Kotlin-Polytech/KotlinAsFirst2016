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
    if  ((age%10 == 1) && (age != 11) && (age != 111)) return ("$age год")
    else if ((age%10 in 2..4) && (age !in 12..14) && (age !in 112..114)) return ("$age года")
    else return ("$age лет")
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

    if (t1<0 || v1<0 || t2<0 || v2<0 || t3<0 || v3<0) return Double.NaN
    val s1 = t1*v1
    val s2 = t2*v2
    val s3 = t3*v3
    val halfS = (s1 + s2 + s3)/2
    if (halfS == 0.0) return Double.NaN
    return when {
        halfS <= s1 -> halfS/v1
        halfS <= s1+s2 && v2 != 0.0 -> t1 + (halfS - s1)/v2
        halfS > s1+s2 && v3 != 0.0 -> t1 + t2 + (halfS - s1 - s2)/v3
        else -> Double.NaN
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
    var danger1 = false //нет угрозы первой ладьи
    var danger2 = false //нет угрозы второй ладьи
    if (rookX1 == kingX || rookY1 == kingY) danger1 = true // есть угроза от первой ладьи
    if (rookX2 == kingX || rookY2 == kingY) danger2 = true //есть угроза от второй ладьи
    if (rookX1 == rookX2) { //если две ладьи и король стоят в один ряд(по вертикали поля)
        //если между королем и второй ладьей стоит первая ладья, то нет угрозы от второй
        if (rookY1 in min(rookY2, kingY)..max(rookY2, kingY)) danger2= false
        //если между королем и первой ладьей стоит вторая ладья, то нет угрозы от первой
        else if (rookY2 in min(rookY1, kingY)..max(rookY1, kingY)) danger1= false
    }
    else if (rookY1 == rookY2) { //если две ладьи и король стоят в один ряд (по горизонтали)
        //если между королем и второй ладьей стоит первая ладья, то нет угрозы от второй
        if (rookX1 in min(rookX2, kingX)..max(rookX2, kingX)) danger2= false
        //если между королем и первой ладьей стоит вторая ладья, то нет угрозы от первой
        else if (rookX2 in min(rookX1, kingX)..max(rookX1, kingX)) danger1= false
    }
    return when {
        danger1 == true && danger2 == true -> 3 //угроза от обеих ладей
        danger1 == true -> 1 //угроза только от первой
        danger2 == true -> 2 //угроза только от второй
        else -> 0 //угрозы нет вообще
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

    var dangerbishop = false //нет угрозы слона
    var dangerrook = false //нет угрозы ладьи
    //если катеты равны по длине, то король и слон лежат на одной диагонали
    // => король под угрозой слона
    if (abs(bishopX - kingX) == abs(bishopY - kingY))
    {
        dangerbishop = true
        //если между слоном и королем стоит ладья
        if (abs(rookX - kingX) == abs(rookY - kingY))
        {
            if (rookX in min(bishopX, kingX)..max(bishopX, kingX) &&
                    rookY in min(bishopY, kingY)..max(bishopY, kingY))
                dangerbishop = false
        }
    }
    ///////////////////////////////////
    //угроза от ладьи
    if (rookX == kingX || rookY == kingY)
    {
        dangerrook = true
        //если между ладьей и королем стоит слон
        if (bishopY == kingY && bishopX in min(kingX,rookX)..max(kingX,rookX))
                    dangerrook = false
        else if (bishopX == kingX &&
                bishopY in min(kingY,rookY)..max(kingY,rookY))
                    dangerrook = false
    }
    /////////////////
    return when {
        dangerbishop == true && dangerrook == true -> 3 //угроза и от слона, и от ладьи
        dangerbishop == true -> 2 //угроза только от слона
        dangerrook == true -> 1 //угроза только от ладьи
        else -> 0 //нет угрозы
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
fun triangleKind(a: Double, b: Double, c: Double): Int
{
        //проверим, существует ли треугольник
    //если сумма двух сторон больше третьей, то треугольник существует
    if ((a + b > c) && (a + c > b) && (c + b > a))
    {
        if ((a*a + b*b < c*c) || (a*a + c*c < b*b) || (b*b + c*c < a*a)) return 2
        else if ((a*a + b*b == c*c) || (a*a + c*c == b*b) || (b*b + c*c == a*a)) return 1
        else return 0
    }
    else return -1 //если треугольник не существует
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
    val lenght = min(b,d) - max(a,c)
    return when {
        lenght >= 0 -> lenght
        else -> -1
    }
}

