@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
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
fun ageDescription(age: Int) :String {
    if (age in 11..19) return "$age лет"
    val n = age % 10
    if (n == 1) return "$age год"
    if (n in 2..4) return "$age года"
    else return "$age лет"
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
    val S1 = t1*v1
    val S2 = t2*v2
    val S3 = t3*v3
    val S = (S1+S2+S3) / 2
    if (S <= S1) return S/v1
    if ((S > S1) && (S <= S1+S2)) return (S-S1)/v2 + t1
    else return (S-S1-S2) / v3 + t1 + t2
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
    if (kingX != rookX1 && kingY != rookY1 && kingX != rookX2 && kingY != rookY2) return 0
    if (kingX == rookX1 || kingY == rookY1 && kingX != rookX2 && kingY != rookY2) return 1
    if (kingX != rookX1 && kingY != rookY1 && kingX == rookX2 || kingY == rookY2) return 2
    else return 3
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
    if (kingX != rookX && kingY != rookY && Math.abs(bishopX - kingX) != Math.abs(bishopY - kingY)) return 0
    if (kingX == rookX || kingY == rookY && Math.abs(bishopX - kingX) != Math.abs(bishopY - kingY)) return 1
    if (kingX != rookX && kingY != rookY && Math.abs(bishopX - kingX) == Math.abs(bishopY - kingY)) return 2
    else return 3
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
    if (a+b>c && a+c>b && b+c>a) {
        val H = if (a>b && a>c) a else if (b>a && b>c) b else c
        val L = Math.min(a,b)
        val K = Math.min(L,c)
        if (sqr(H) <sqr(L)+sqr(K)) return 0
        if (sqr(H)==sqr(L)+sqr(K)) return 1
        else return 2
   }
    else return -1
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
    if ((b<d && b==c) || (b>d && a==d) || (a==b || c==d) || (a==c && b==d)) return 0
    if (c in a..b && b in c..d) return b-c
    if (a in c..b && b in a..d) return b-a
    if (a in c..d && d in a..b) return d-a
    if (c in a..d && d in c..b) return d-c
    else return -1
}