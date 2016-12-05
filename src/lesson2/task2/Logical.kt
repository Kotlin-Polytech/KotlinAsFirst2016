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
fun isNumberHappy(number: Int): Boolean {
    val digit1 = number % 10
    val digit2 = (number % 100) / 10
    val digit3 = (number / 100) % 10
    val digit4 = number / 1000
    return (digit1 + digit2 == digit3 + digit4)
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    val qtLine = (x1 == x2) || (y1 == y2)
    val qtDiag = (Math.abs(x1 - x2) == (Math.abs(y1 - y2)))
    return(qtLine || qtDiag)
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean {
    val x = sqr(x2 - x1)
    val y = sqr(y2 - y1)
    return(Math.sqrt(x + y) + r1 <= r2)
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
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val bp1 = (a <= r) && (b <= s)
    val bp2 = (a <= r) && (c <= s)
    val bp3 = (b <= r) && (a <= s)
    val bp4 = (b <= r) && (c <= s)
    val bp5 = (c <= r) && (a <= s)
    val bp6 = (c <= r) && (b <= s)
    return (bp1 || bp2 || bp3 || bp4 || bp5 || bp6)
}
