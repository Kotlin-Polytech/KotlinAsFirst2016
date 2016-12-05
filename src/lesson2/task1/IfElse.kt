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
fun ageDescription(age: Int): String
{
    var Str = "$age"
    if (age%100 > 10 && age%100 < 20) Str += " лет"
    else {
            Str += when (age % 10) {
            0 -> " лет"
            1 -> " год"
            2 -> " года"
            3 -> " года"
            4 -> " года"
            else -> " лет"
        }
    }
    return Str
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
                   t3: Double, v3: Double): Double
{
    val halfDistance = (t1*v1 + t2*v2 + t3*v3)/2.0
    if (halfDistance - t1*v1 < 0)  return halfDistance/v1
    else if (halfDistance - (t1*v1 + t2*v2) < 0) return (halfDistance - t1*v1)/v2 + t1
    else return (halfDistance - t1*v1 - t2*v2)/v3 + t1 + t2
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
                       rookX2: Int, rookY2: Int): Int
{
    var num = 0
    if (kingX == rookX1 || kingY == rookY1) num+=1
    if (kingX == rookX2 || kingY == rookY2) num+=2
    return num
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
                          bishopX: Int, bishopY: Int): Int
{
    var num = 0
    if (kingX == rookX || kingY == rookY) num+=1
    if (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY)) num+=2
    return num
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
    if (a+b-c <= 0 || a+c-b <= 0 || b+c-a <= 0) return -1

    val cosA = ( - a*a +  c*c + b*b)/(2.0*c*b)
    val cosB = ( - b*b +  a*a + c*c)/(2.0*a*c)
    val cosC = ( - c*c +  a*a + b*b)/(2.0*a*b)

    if (cosA == 0.0 || cosB == 0.0 || cosC == 0.0)
    {
        return 1
    }
    else if (cosA < 0 || cosB < 0 || cosC < 0)
    {
        return 2
    }
    else
    {
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
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int
{
    if (b < c || d < a) return -1
    if (a > c)
    {
        return if (b > d) d - a else b - a
    }
    else
    {
        return if (b > d) d - c else b - c
    }
}