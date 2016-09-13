@file:Suppress("UNUSED_PARAMETER")
package lesson2.task2

import lesson1.task1.sqr

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean
{
    var s1 : Int
    var s2 : Int
    
    s1 = ( ( number - number % 1000 ) / 1000 ) + ( ( number % 1000 - number % 100 ) / 100 )
    s2 = ( ( number % 100 - number % 10 ) / 10 ) + ( number % 10 )
    
    if ( s1 == s2 ) return true
    else return false
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean
{
    if
    (
        x1 == x2 ||
        y1 == y2 ||
        Math.abs ( x1 - x2 ) == Math.abs ( y1 - y2 )
    ) return true
    else return false
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double, x2: Double, y2: Double, r2: Double): Boolean
{   // Если расстояние между центрами + радиус первого меньше радиуса второго
    if ( Math.sqrt ( ( x2 - x1 ) * ( x2 - x1 ) + ( y2 - y1 ) * ( y2 - y1 ) ) + r1 <= r2 ) return true
    else return false
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean
{
    if
    (
		( a <= r && b <= s ) || ( b <= r && a <= s ) ||
		( b <= r && c <= s ) || ( c <= r && b <= s ) ||
		( c <= r && a <= s ) || ( a <= r && c <= s )
    ) return true
    else return false
}
