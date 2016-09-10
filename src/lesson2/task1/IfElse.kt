@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
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
    if ((age>4 && age<21) || age%10 == 0 || age%10 > 4){
        return age.toString() + " лет"
    } else if(age%10 == 1) {
        return age.toString() + " год"
    } else {
        return age.toString() + " года"
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
    var path: Double = t1*v1 + t2*v2 + t3*v3
    if (t1*v1 >= path/2)
        return path/2/v1
    else if (t1*v1+t2*v2 >= path/2)
        return t1 + (path/2-t1*v1)/v2
    else
        return t1 + t2 + (path/2 - t1*v1 - t2*v2)/v3
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
    var result: Int = 0;
    if (rookX1==kingX || rookY1==kingY)
        result = 1
    if (rookX2==kingX || rookY2==kingY)
        result = 2
    if((rookX2==kingX || rookY2==kingY) && (rookX1==kingX || rookY1==kingY))
        result = 3
    return result
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
    var result: Int = 0
    if (kingX == rookX || kingY == rookY)
        result = 1
    if(abs(kingX-bishopX) == abs(kingY-bishopY))
        result = 2
    if((kingX == rookX || kingY == rookY) && (abs(kingX-bishopX) == abs(kingY-bishopY)))
        result = 3
    return result
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
    var arr: DoubleArray = doubleArrayOf(a,b,c)
    arr.sort()
    if (arr[2] >= arr[1] + arr[0])
        return -1
    else if (arr[2] < sqrt(sqr(arr[1])+ sqr(arr[0])))
        return 0
    else if (arr[2] == sqrt(sqr(arr[1])+ sqr(arr[0])))
        return 1
    else
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
    if (c <= a && d >= a && d <= b)
        return d-a
    else if (c <= a && d >= b)
        return b-a
    else if (c >= a && d <= b)
        return d-c
    else if (c <= b && c >= a && d >= b)
        return  b-c
    else
        return -1
}