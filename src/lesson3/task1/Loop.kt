@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import lesson1.task1.sqr

fun powInt(x: Int, y: Int): Int {
    var z = 1
    for (i in 1..y) z *= x
    return z
}

fun gcd(a: Int, b: Int): Int {
    var m = Math.max(a, b)
    var n = Math.min(a, b)
    var r: Int
    while (m % n > 0) {
        r = m
        m = n
        n = r % n
    }
    return n
}

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
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
    for (m in 2..n/2) {
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
    var number = Math.abs(n)
    var count = 0
    do {
        count++
        number /= 10
    } while (number > 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 0
    var fib2 = 1
    for (i in 2..n) {
        fib2 += fib1
        fib1 = fib2 - fib1
    }
    return fib2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = Math.abs(m * n) / gcd(m, n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var d = 1
    for (i in 2..n) {
        d++
        if (n % d == 0) break
    }
    return d
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var d = n
    for (i in n - 1 downTo 1) {
        d--
        if (n % d == 0) break
    }
    return d
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = (gcd(m, n) == 1)

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k = 0
    while (k * k <= m) {
        if (m == 0) return true
        else {
            k++
            if (k * k in m..n) return true
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
fun sin(x: Double, eps: Double): Double {
    var k = 1
    var i = 1
    var sinX = 0.0
    val y = x % (Math.PI * 2.0)
    do {
        val z = Math.pow(y, k.toDouble()) / factorial(k)
        if (i % 2 == 1) sinX += z
        else sinX -= z
        k += 2
        i++
    } while (Math.abs(Math.pow(y, k.toDouble()) / factorial(k)) > eps)
    return sinX
}

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var k = 0
    var i = 1
    var cosX = 0.0
    val y = x % (Math.PI * 2.0)
    do {
        val z = Math.pow(y, k.toDouble()) / factorial(k)
        if (i % 2 == 1) cosX += z
        else cosX -= z
        k += 2
        i++
    } while (Math.abs(Math.pow(y, k.toDouble()) / factorial(k)) > eps)
    return cosX
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var m = n
    var number = 0
    while (m > 0) {
        number = number * 10 + m % 10
        m /= 10
    }
    return number
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = (revert(n) == n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var m = n
    do {
        if (m < 10) break
        if (m % 10 != (m / 10) % 10) return true
        m /= 10
    } while (m != 0)
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var c = 0
    var k = 0
    while (c < n) {
        k++
        c += digitNumber(k * k)
    }
    if (c == n) return (k * k) % 10
    else {
        val ex = powInt(10, c - n)
        return ((k * k) / ex) % 10
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var c = 0
    var k = 0
    while (c < n) {
        k++
        c += digitNumber(fib(k))
    }
    if (c == n) return (fib(k)) % 10
    else {
        val ex = powInt(10, c - n)
        return (fib(k) / ex) % 10
    }
}
