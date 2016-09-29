@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        if (n == m) 1 else if (n < 10) 0
        else digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var a = 0
    var b = abs(n)
    if (b == 0) return 1
    else {
        do {
            b = b / 10
            a++
        } while (b != 0)
        return a
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1) // 1,6180339887
 */
fun fib(n: Int): Int {
    val a = sqrt(5.0)
    val b: Double
    if (n > 10) {
        b = (1 / a * (pow((1 + a) / 2, n.toDouble())))
    } else {
        b = (1 / a * (pow((1 + a) / 2, n.toDouble()) - pow((1 - a) / 2, n.toDouble())))
    }
    return round(b).toInt()
}
    /**
     * Простая
     *
     * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
     * минимальное число k, которое делится и на m и на n без остатка
     */
    fun lcm(m: Int, n: Int): Int {
        var a = if (m > n) m else n
        val b = if (m < n) m else n
        var k = (a * b) / 2
        val s = if (m > n) m else n
        if (((a % 2) == 0) && ((b % 2) == 0)) ((a * b) / 2)
        else {
            if ((a % b) == 0) (k == b) else
                while ((a % b) != 0)
                    a += s
            k = a
        }
        return k
    }

    /**
     * Простая
     *
     * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
     */
    fun minDivisor(n: Int): Int = TODO()

    /**
     * Простая
     *
     * Для заданного числа n > 1 найти максимальный делитель, меньший n
     */
    fun maxDivisor(n: Int): Int = TODO()

    /**
     * Простая
     *
     * Определить, являются ли два заданных числа m и n взаимно простыми.
     * Взаимно простые числа не имеют общих делителей, кроме 1.
     * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
     */
    fun isCoPrime(m: Int, n: Int): Boolean = TODO()

    /**
     * Простая
     *
     * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
     * то есть, существует ли такое целое k, что m <= k*k <= n.
     * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
     */
    fun squareBetweenExists(m: Int, n: Int): Boolean = TODO()

    /**
     * Простая
     *
     * Для заданного x рассчитать с заданной точностью eps
     * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
     * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
     */
    fun sin(x: Double, eps: Double): Double = TODO()

    /**
     * Простая
     *
     * Для заданного x рассчитать с заданной точностью eps
     * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
     * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
     */
    fun cos(x: Double, eps: Double): Double = TODO()

    /**
     * Средняя
     *
     * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
     * Не использовать строки при решении задачи.
     */
    fun revert(n: Int): Int = TODO()

    /**
     * Средняя
     *
     * Проверить, является ли заданное число n палиндромом:
     * первая цифра равна последней, вторая -- предпоследней и так далее.
     * 15751 -- палиндром, 3653 -- нет.
     */
    fun isPalindrome(n: Int): Boolean = TODO()

    /**
     * Средняя
     *
     * Для заданного числа n определить, содержит ли оно различающиеся цифры.
     * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
     */
    fun hasDifferentDigits(n: Int): Boolean = TODO()

    /**
     * Сложная
     *
     * Найти n-ю цифру последовательности из квадратов целых чисел:
     * 149162536496481100121144...
     * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
     */
    fun squareSequenceDigit(n: Int): Int = TODO()

    /**
     * Сложная
     *
     * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
     * 1123581321345589144...
     * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
     */
    fun fibSequenceDigit(n: Int): Int = TODO()
