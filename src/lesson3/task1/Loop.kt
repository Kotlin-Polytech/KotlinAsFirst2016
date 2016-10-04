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
    var k = 0
    var x = n
    if (x != 0) {
        while (x != 0) {
            x /= 10
            k += 1
        }
        return k
    } else {
        return 1
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var last = 1
    var next = 1
    var box = 0
    for (i in 3..n) {
        box = next
        next = last + next
        last = box

    }
    return next

}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun NOD2(m: Int, n: Int): Int{
    var a= n
    var b = m
    var shift = 0
    if (a == 0)
        return b
    if (b == 0)
        return a
    shift = 0
    while (a or b and 1 == 0) {
        shift++
        a = a shr 1
        b = b shr 1

    }
    while (a and 1 == 0)
        a = a shr 1
    do {
        while (b and 1 == 0)
            b = b shr 1
        if (a > b) {
            val t = b
            b = a
            a = t
        }
        b -= a
    } while (b !== 0)
    return a shl shift
}



fun lcm(m: Int, n: Int): Int {

    return (m * n) / NOD2(m, n)

}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var i = 2
    while (n % i != 0) i ++
    return i
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var i = n - 1
    while (n % i != 0) i --
    return i
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = NOD2(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun sqr(g: Double) = g * g

fun squareBetweenExists(m: Int, n: Int): Boolean = m <= sqr(floor(sqrt(n.toDouble()))) && sqr(floor(sqrt(n.toDouble())))  <= n

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var k = 0
    var sin: Double = x
    var number: Double = x
    while (Math.abs(number) * 1000 > eps) {
        k += 1
        number = Math.pow(x, k.toDouble() * 2 + 1) / factorial(k * 2 + 1)
        if (k % 2 == 1) {
            sin -= number
        } else {
            sin += number
        }
    }
    return sin
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
    var cos: Double = 1.0
    var number: Double = x
    while (Math.abs(number) * 1000 > eps) {
        k += 1
        number = Math.pow(x, k.toDouble() * 2) / factorial(k * 2)
        if (k % 2 == 1) {
            cos -= number
        } else cos += number
    }
    return cos
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var revert = 0
    var s = n
    while (s > 0) {
        revert = revert * 10 + s % 10
        s /= 10
    }
    return revert
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
    val maincipher = n % 10
    var s = n
    while (s > 0) {
        if (s % 10 != maincipher) {
            return true
        }
        else {
            s /= 10
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
fun count(n: Int): Int {
    var k = 0
    var s = n
    while (s > 0) {
        s /= 10
        k ++
    }
    return k
}


fun squareSequenceDigit(n: Int): Int {
    var numberi = 0
    var number = 0
    var result = 0
    while (number < n) {
        numberi ++
        number += count(numberi * numberi)
    }
    result = numberi * numberi
    for (numberi in n..number - 1) {
        result /= 10
    }
    return (result % 10)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */


fun fibSequenceDigit(n: Int): Int {
    var numberi = 0
    var number = 0
    var result = 0
    while (number < n) {
        numberi ++
        number += count(fib(numberi))
    }
    result = fib(numberi)
    for (i in n..number - 1) {
        result /= 10
    }
    return (result % 10)
}
