@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr

fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

fun main(args: Array<String>) {
    println(isNumberHappy(1634))
}

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val third = number % 1000 % 100 % 10
    val fourth = (number % 1000 % 100 - third) / 10
    val secondHalf = number % 1000 % 100
    val secondHalfSum = third + fourth
    val firstHalf = (number - secondHalf) / 100
    val second = firstHalf % 10
    val first = (firstHalf - second) / 10
    val firstHalfSum = first + second

    if (firstHalfSum == secondHalfSum) return true

    return false
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    if (x1 == x2 || y1 == y2) return true
    if (x1 - y1 == x2 - y2 || x1 + y1 == x2 + y2) return true
    return false
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean = Math.sqrt(sqr(x1 - x2) + sqr(y1 - y2)) + r1 <= r2

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
    val min1 = Math.min(a, Math.min(b, c))
    var min2 = 1
    when (min1) {
        a -> min2 = Math.min(b, c)
        b -> min2 = Math.min(a, c)
        c -> min2 = Math.min(a, b)
    }

    if (min1 <= r && min2 <= s || min2 <= r && min1 <= s) return true else return false

}
