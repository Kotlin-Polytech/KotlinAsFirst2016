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
    if (n == 0) return 1
    else {
        var counter = 0
        var ns = n
        while (ns != 0) {
            ns /= 10
            counter++
        }
        return counter
    }
}


/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n in 1..2) return 1
    var fibnach1 = 1
    var fibnach2 = 1
    var fibnach3 = 0
    for (i in 3..n) {
        fibnach3 = fibnach2 + fibnach1
        fibnach1 = fibnach2
        fibnach2 = fibnach3
    }
    return fibnach3
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun gcd(a: Int, b: Int): Int {
    if (a != 0) return gcd(b % a, a)
    else return b
}

fun lcm(m: Int, n: Int): Int =
        m * n / gcd(m, n)


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var check = 1
    for (i in 2..n) {
        if (n % i == 0) {
            check = i
            break
        }
    }
    return check
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var check = n
    for (i in n - 1 downTo 1) {
        if (n % i == 0) {
            check = i
            break
        }
    }
    return check
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean =
        gcd(m, n) == 1


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (result in m..n) {
        val sqrtResult = Math.sqrt(result.toDouble())
        if (sqrtResult % 1 == 0.0) {
            if (sqrtResult * sqrtResult in m..n) return true
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
    var counter = 0
    val x1=x % (2 * Math.PI)
    var number = x1
    var sinus = x1
    while (Math.abs(number) > eps) {
        counter++
        number = Math.pow(x1, counter * 2.0 + 1) / factorial(counter * 2 + 1)
        if (counter % 2 == 1) sinus -= number
        else sinus += number
    }
    return sinus
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var t = ""
    var n1 = n

    do {
        t += (n1 % 10).toString()
        n1 /= 10
    } while (n1 >= 1)
    return t.toInt()
}

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var i = 0
    val x1=x % (2 * Math.PI)
    var cosus = 1.0
    var number = x1
    while (Math.abs(number) > eps) {
        i++
        number = Math.pow(x1, i * 2.0) / factorial(i * 2)
        if (i % 2 == 1) cosus -= number
        else cosus += number

    }
    return cosus % (2 * Math.PI)
}


/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */

fun isPalindrome(n: Int): Boolean {
    if (n in -9..9) return true
    val firstNumber = n % 10
    val secondNumber = n % 100 / 10
    var nForWhile = 1
    val counter = digitNumber(n)
    for (i in 1..counter) {
        nForWhile *= 10
    }
    val revertNumFirst = n / (nForWhile / 10)
    val revertNumSecond = n / (nForWhile / 100) % 10
    if ((firstNumber == revertNumFirst) && (secondNumber == revertNumSecond)) return true
    return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean =
        if ((n.toString().filter { it == n.toString()[0] }) != (n.toString())) true else false

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var i = 0
    var number = 0
    var resalt = 0
    while (number < n) {
        i++
        number += digitNumber(i * i)
    }
    resalt = i * i
    for (i in n..number - 1) {
        resalt = resalt / 10
    }
    return (resalt % 10)
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */


fun fibSequenceDigit(n: Int): Int {
    var i = 0
    var number = 0
    var result = 0
    while (number < n) {
        i++
        number += digitNumber(fib(i))
    }
    result = fib(i)
    for (i in n..number - 1) {
        result /= 10
    }
    return (result % 10)
}