@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import lesson1.task1.numberRevert
import lesson1.task1.sqr
import lesson1.task1.takeDigit


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
    var count: Int = 0
    var mutableNumber: Int = n
    do {
        mutableNumber /= 10
        count++
    } while (mutableNumber != 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var result: Int = 1
    var last: Int = 1
    if (n in 1..2) result = 1
    else if (n > 2) {
        for (i in 3..n) {
            val temp = result
            result += last
            last = temp
        }
    } else result = -1
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun gcd(m: Int, n: Int): Int {
    var mutableM: Int = m
    var mutableN: Int = n
    var r: Int = mutableM % mutableN
    while (r != 0) {
        mutableM = mutableN
        mutableN = r
        r = mutableM % mutableN
    }
    return mutableN
}

fun lcm(m: Int, n: Int): Int {
    val temp: Int = gcd(m, n)
    return m * n / temp
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n <= 1) return -1
    else {
        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return i
        }
        return n
    }
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = if (gcd(m, n) == 1) true else false

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in Math.sqrt(m.toDouble()).toInt()..Math.sqrt(n.toDouble()).toInt() + 1) {
        if (i * i in m..n) return true
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
    var element: Double = x
    var result: Double = 0.0
    var n: Int = 1
    while (Math.abs(element) >= eps) {
        result += element
        val degree: Double = (2 * n + 1).toDouble()
        element = (Math.pow(-1.0, n.toDouble()) * Math.pow(x, degree)) / (factorial(degree.toInt()))
        n++
    }
    return result
}


/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var element: Double = 1.0
    var result: Double = 0.0
    var n: Int = 1
    while (Math.abs(element) >= eps) {
        result += element
        val degree: Double = (2 * n).toDouble()
        element = (Math.pow(-1.0, (n).toDouble()) * Math.pow(x, degree)) / (factorial(degree.toInt()))
        n++
    }
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int = numberRevert(n)

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = if (n == revert(n)) true else false

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n in -9..9) return false
    else if(n%10==0) return true
    else if (n % 10 == 9) {
        return if (n + 1 == Math.pow(10.0, digitNumber(n).toDouble()).toInt()) false else true
    } else {
        return if ((n / (n % 10)) * 9 + 1 == Math.pow(10.0, digitNumber(n).toDouble()).toInt()) false
        else true
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var count: Int = 0
    var number: Int = 1
    var temp: Int = 0
    while (n > count) {
        temp = sqr(number.toDouble()).toInt()
        count += digitNumber(temp)
        number++
    }
    return takeDigit(temp, count - n + 1)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var count: Int = 0
    var number: Int = 1
    var temp: Int = 0
    while (n > count) {
        temp = fib(number)
        count += digitNumber(temp)
        number++
    }
    return takeDigit(temp, count - n + 1)
}
