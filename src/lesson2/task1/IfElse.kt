@file:Suppress("UNUSED_PARAMETER", "UNUSED_VALUE")
package lesson2.task1
import lesson1.task1.discriminant
import lesson1.task1.sqr

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
fun ageDescription(age: Int): String  {
    return if ((age in (5..20)) || (age in (105..120)) || (age % 10 > 4) || (age % 10 == 0)) "$age лет"
    else {
        if ((age % 10 < 5) && (age % 10 > 1)) "$age года"
        else "$age год"
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
    val time : Double
    val halfWay : Double = (t1 * v1 + t2 * v2 + t3 * v3) / 2.0
    if (halfWay / (t1 * v1) < 1){
        time = halfWay / v1
    } else
        if (halfWay /(t1 * v1 + t2 * v2) < 1) {
            time = (halfWay - v1 * t1) / v2 + t1
        } else {
            time = (halfWay - (v1 * t1 + v2 * t2)) / v3 + t1 + t2
        }
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
                       rookX2: Int, rookY2: Int): Int {
    var attack: Int
    if ((kingX == rookX1) || (kingY == rookY1)) attack = 1
    else attack = 0
    if ((kingX == rookX2) || (kingY == rookY2)) {
        attack += 2
    }
    return attack
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
    var attack : Int
    if ( (kingX == rookX) || (kingY == rookY) ) attack = 1
    else attack = 0
    if ((kingX - bishopX) * (kingX - bishopX) == (kingY - bishopY) * (kingY - bishopY)) {
        attack += 2
    }
    return attack
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуь -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if ((sqr(a) == (sqr(b) + sqr(c))) || (sqr(b) == (sqr(a) + sqr(c))) || (sqr(c) == (sqr(b) + sqr(a)))) return 1
    if ((a > b + c) || (b > a + c) || (c > b + a)) return -1
    if ((((sqr(b) + sqr(c) - sqr(a)) / (2*b*c)) > 0) && (((sqr(a) + sqr(c) - sqr(b)) / (2*a*c)) > 0) && (((sqr(b) + sqr(a) - sqr(c)) / (2 * b * a)) > 0)) return 0
    else return 2
}


/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = TODO()