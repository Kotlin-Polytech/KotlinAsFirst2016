@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr

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
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
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
    var n1 = if (n == 0) 1 else Math.abs(n)
    var number = 0
    while (n1 > 0) {
        n1 /= 10
        number += 1
    }
    return number
}
 //
/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = if (n <= 2) 1 else fib(n - 1) + fib(n - 2)

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    if (m == n) return m
    var m1 = Math.min(m, n)
    val list = mutableListOf<Int>()
    val list1 = mutableListOf<Int>()
    while (m1 != 1) {
        for (j in 1..Math.min(m, n)) {
            if (j > m1) break
            if (m1 % j == 0) {
                list += j
                m1 /= j
                if (m1 == 1) break
            }
        }
    }
    var n1 = Math.max(m, n)
    for (element in list) {
        if (n1 % element == 0) n1 /= element
    }
    while (n1 != 1) {
        for (j in 1..Math.max(m, n)) {
            if (j > n1) break
            if (n1 % j == 0) {
                list1 += j
                n1 /= j
                if (n1 == 1) break
            }
        }
    }
    var result = 1
    for (element in list) result *= element
    for (element in list1) result *= element
    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var result = 1
    var i = 1
    while (i != 0) {
        result += 1
        i = n % result
    }
    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var x = 1
    var result = 1
    for (i in 2..n / 2) {
        x = n % i
        if (x == 0) result = n / i
        if (result != 1) break
    }

    return result
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..m) {
        if ((m % i == 0) && (n % i == 0)) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    if (m * m >= n) {
        for (i in m downTo 0) {
            if ((i * i <= n) && (i * i >= m)) return true
        }
    }
    var i = 1
    if (m * m < n) {
        for (i in m..n / i) {
            if (i * i <= n) return true
        }
    }
    return false
}

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

fun step(n: Int, i: Int): Int {
    var result = 1
    for (j in 1..i) result *= 10
    return result
}

fun revert(n: Int): Int {
    var n1 = n
    var result = 0
    var j = 0
    while (n1 != 0) {
        n1 /= 10
        j++
    }
    n1 = n
    for (i in 1..j) {
        result += n1 % 10 * step(10, j - i)
        n1 /= 10
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n / 10 == 0) return false
    var n1 = n
    while (n1 / 10 != 0) {
        if ((n1 / 10) % 10 != n1 % 10) return true
        n1 /= 10
    }
    return false
}

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
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
