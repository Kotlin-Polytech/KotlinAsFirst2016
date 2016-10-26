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
    if (n < 10 && n > -10) return 1
    var count = 0
    var m = n
    while (m != 0) {
        m /= 10
        count++
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    val sq = sqrt(5.0)
    val doubleN = n.toDouble()
    val result: Double
    if (n > 10) {
        result = (1 / sq * (pow((1 + sq) / 2, doubleN)))
    } else {
        result = (1 / sq * (pow((1 + sq) / 2, doubleN) - pow((1 - sq) / 2, doubleN)))
    }
    return round(result).toInt()
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k: Int
    if (n > m) k = n else k = m
    for (i in k..Int.MAX_VALUE) {
        if (i % m == 0 && i % n == 0) {
            k = i
            break
        }
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var nok = n
    for (i in n downTo 2) {
        if (n % i == 0) nok = i
    }
    return nok
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var nok = 1
    for (i in 1..n - 1) {
        if (n % i == 0) nok = i
    }
    return nok
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var a = m
    var b = n
    while (b != 0) {
        val c = a % b
        a = b
        b = c
    }
    return a == 1
}

/**
 * Простая
 *
 * Для з
 * аданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val a = sqrt(m.toDouble())
    val b = sqrt(n.toDouble())
    val difference = b.toInt() - a.toInt()
    if (m == n) {
        val roundM = round(a).toInt()
        if (roundM * roundM == m) return true
    } else if (difference >= 1) return true
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
    var a = 1
    var k = 2
    var s = 0.0
    var e = x
    var z = x
    if (Math.abs(x) > 2 * Math.PI) z = x % (2 * Math.PI)
    while (Math.abs(e) >= Math.abs(eps)) {
        e = Math.pow(z, a.toDouble()) / factorial(a)
        if (k % 2 == 0) s += e else s -= e
        a += 2
        k += 1
    }
    return s
}

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var a = 2
    var k = 1
    var s = 1.0
    var e = x
    var z = x
    if (Math.abs(x) > 2 * Math.PI) z = x % (2 * Math.PI)
    while (Math.abs(e) >= Math.abs(eps)) {
        e = Math.pow(z, a.toDouble()) / factorial(a)
        if (k % 2 == 0) s += e else s -= e
        a += 2
        k += 1
    }
    return s
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var k = n
    var rev = 0
    while (k > 0) {
        rev *= 10
        rev += k % 10
        k /= 10
    }
    return rev
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    return n == revert(n)
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var k = n
    if (k in 0..10) return false else {
        while (k >= 10) {
            if (k % 10 != (k / 10) % 10) return true else k /= 10
        }
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
fun squareSequenceDigit(n: Int): Int {
    var l = "1"
    var c = 2
    var k = 0
    while (k + l.length < n) {
        k += l.length
        l = (c * c).toString()
        c += 1
    }
    return (l[n - k - 1] - '0').toInt()
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var i = 1
    var x: Int
    var y = 0
    do {
        x = fib(i)
        y += digitNumber(x)
        if (y >= n) break
        i++
    } while (true)
    for (j in 1..y - n) {
        x /= 10
    }
    return x % 10
}
