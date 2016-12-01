@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.abs
import java.lang.Math.max

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
        ((age > 1 && age < 5) || (((age > 20 && age < 110) || (age > 120 && age < 200)) && (age % 10) < 5 && (age % 10) > 1)) -> "$age года"
        ((age == 1) || (((age > 20 && age < 110) || (age > 120 && age < 200)) && (age % 10) == 1)) -> "$age год"
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
val s=(t1*v1+t2*v2+t3*v3)/2
return when {
    (s < v1 * t1) -> (s / v1)
    (s < (v1 * t1 + v2 * t2)) -> (((s - v1 * t1) / v2) + t1)
    else -> (((s - v1 * t1 - v2 * t2) / v3) + t1 + t2)

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
    var a=0
    if(kingX==rookX1 || kingY==rookY1) a=1
    if(kingX==rookX2 || kingY==rookY2)if(a==1) a=3 else a=2
    return a
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
    var a = 0

    if (abs(kingX-bishopX)==abs(kingY-bishopY)) a=2
    if(kingX==rookX || kingY==rookY)if(a==2) a=3
    else a=1

return a
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
    var p = -1
    if(a+b<=c||b+c<=a||a+c<=b) return p
    var cosy:Double = 0.0
    var d = max(max(a, b), c)
    if (d == c) cosy = ((a * a + b * b - c * c))
    if (d == b) cosy = ((a * a + c * c - b * b) )
    if(d==a) cosy = ((c * c + b * b - a * a) )
    if (cosy > 0) p = 0
    if (cosy == 0.0) p = 1
    if (cosy < 0) p = 2
    return p

}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int{
    var p=-1
    if(c>b ||a>d) return p else
    if (a >= c && b >= d) p=(d - a) else
    if(c >= a && d >= b) p= (b - c) else
    if(c >= a && d <= b) p= (d - c)else
    if(a >= c && b <= d) p= (b - a)
    return p
}