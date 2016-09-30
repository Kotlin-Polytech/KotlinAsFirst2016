@file:Suppress("UNUSED_PARAMETER")
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
fun ageDescription(age: Int): String
{
    if (((age%10)==1) && (age != 11) ){return "$age год" }
    if ((5<=(age%10) && (age%10)<=9) || ((age%10)==0) || ((11<=age) && (age<=14))){return "$age лет"}
    if (((2<=(age%10)) && ((age%10)<=4))) {return "$age года"}
    return ""
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
         var semiLengthS = (t1*v1+t2*v2+t3*v3)/2
            if (semiLengthS < t1*v1)
                 {
                     var semiT = semiLengthS/v1
                     return semiT
                 }
            if (semiLengthS == t1*v1) return t1
            if ((semiLengthS > t1*v1) && (semiLengthS < t1*v1+t2*v2))
            {
                var semiT = ((semiLengthS - t1*v1) / v2)+t1
                return semiT
            }
            if (semiLengthS == (t2*v2+t1*v1)) return t2+t1
            if (semiLengthS > t2*v2)
            {
                var semiT = ((semiLengthS - t1*v1 - t2*v2) / v3)+t1+t2
                return semiT
            }
            return 0.0
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
    if (((rookX1 == kingX) || (rookY1 == kingY)) && ((rookX2 == kingX) || (rookY2 == kingY))) {return 3}
    if (((rookX1 != kingX) && (rookY1 != kingY)) && ((rookX2 == kingX) || (rookY2 == kingY))) {return 2}
    if (((rookX1 == kingX) || (rookY1 == kingY)) && ((rookX2 != kingX) && (rookY2 != kingY))) {return 1}
    if (((rookX1 != kingX) && (rookY1 != kingY)) && ((rookX2 != kingX) && (rookY2 != kingY))) {return 0}
    return 0
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
    if (((rookX != kingX) && (rookY != kingY)) &&
            (( Math.abs(bishopY-bishopX) != Math.abs(kingY-kingX)) && (kingX+kingY != bishopX+bishopY)) ) { return 0}
    if (((rookX == kingX) || (rookY == kingY)) &&
            (( Math.abs(bishopY-bishopX) != Math.abs(kingY-kingX)) && (kingX+kingY != bishopX+bishopY)) ) { return 1}
    if (((rookX != kingX) && (rookY != kingY)) &&
            (( Math.abs(bishopY-bishopX) == Math.abs(kingY-kingX)) || (kingX+kingY == bishopX+bishopY)) ) { return 2}
    if (((rookX == kingX) || (rookY == kingY)) &&
            (( Math.abs(bishopY-bishopX) == Math.abs(kingY-kingX)) || (kingX+kingY == bishopX+bishopY)) ) { return 3}
    return 0
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
    if ((a+b>c) and (a+c>b) and (b+c>a))
    {
        if ((a>b) and (a>c))
        {
            if (a*a<b*b+c*c) return 0
            if (a*a==b*b+c*c) return 1
            if (a*a>b*b+c*c) return 2
        }
        if ((b>a) and (b>c))
        {
            if (b*b<a*a+c*c) return 0
            if (b*b==a*a+c*c) return 1
            if (b*b>a*a+c*c) return 2
        }
        if ((c>b) and (c>a))
        {
            if (c*c<b*b+a*a) return 0
            if (c*c==b*b+a*a) return 1
            if (c*c>b*b+a*a) return 2
        }
        if ((a==b) and (a==c)) return 0
        if ((a==b) and (a>c)) return 0
        if ((a<b) and (b==c)) return 0
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
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int
{
    var lengthSegment:Int =0
    if (c>b) return -1
    if ((d>=b) and (c>=a)) { lengthSegment=b-c}
    if ((c>=a) and (b>=d)) { lengthSegment=d-c}
    if (a>d) return -1
    if ((b>=d) and (a>=c)) {lengthSegment=d-a}
    if ((d>=b) and (a>=c)) {lengthSegment=b-a}
    if ((c==b) || (d==a)) {lengthSegment=0}
  return lengthSegment
}