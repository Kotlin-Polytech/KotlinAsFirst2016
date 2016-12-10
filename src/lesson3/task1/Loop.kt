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
    var iDig = 1
    var newN = n
    do {
        newN /= 10
        if (newN != 0) iDig++
    } while (newN != 0)
    return iDig
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
fun nod(m: Int, n: Int): Int = when {
    m == 0 -> n
    n == 0 -> m
    m >= n -> nod(m % n, n)
    n > m -> nod(n % m, m)
    else -> 0
}

fun lcm(m: Int, n: Int): Int {
    return (m * n) / nod(m, n)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n / 2) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n / 2 downTo 2) {
        if (n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int) = nod(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */

fun squareBetweenExists(m: Int, n: Int): Boolean {
    val newM = sqrt(m.toDouble())
    val newN = sqrt(n.toDouble())
    val difference = newN.toInt() - newM.toInt()
    if (m == n) {
        val roundM = round(newM).toInt()
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
    var y = 1.0
    var newX = x
    if (abs(x) > 2 * PI) newX = x % (2 * PI)
    var z = newX

    val moduleEps = abs(eps)
    var result: Double
    do {
        result = pow((-1.0), y) * pow(newX, 2 * y + 1) / factorial((2 * y + 1).toInt())
        z += result
        y += 1
    } while (abs(result) >= moduleEps)
    return z
}


/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var y = 1.0
    var z = 1.0
    val moduleEps = abs(eps)
    var result: Double
    var newX = x
    if (abs(x) > 2 * PI) newX = x % (2 * PI)
    do {
        result = pow((-1.0), y) * pow(newX, 2 * y) / factorial((2 * y).toInt())
        z += result
        y += 1
    } while (abs(result) >= moduleEps)
    return z
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var newN = n
    var revertN = 0
    do {
        revertN = (revertN * 10) + newN % 10
        newN /= 10
    } while (newN != 0)
    return revertN
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int) = revert(n) == n

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var newN = n
    do {
        if (newN / 10 == 0) break
        if (newN % 10 != (newN / 10) % 10) return true
        newN /= 10
    } while (newN != 0)
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
    var i = 1
    var x: Int //переменная для подсчета квадратов
    var y = 0//переменная для подсчета номера цифры
    do {
        x = i * i
        y += digitNumber(x) //Считаем сколько цифр содержится в числе "х", вызывая метод из данного класса. Увеличиваем "y" на кол-во цифр в новом числе
        if (y >= n) break
        i++
    } while (true)
    for (j in 1..y - n) {
        x /= 10
    }
    return x % 10
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
    var x: Int //переменная для подсчета факториалов
    var y = 0//переменная для подсчета номера цифры
    do {
        x = fib(i)
        y += digitNumber(x) //Считаем сколько цифр содержится в числе "х", вызывая метод из данного класса. Увеличиваем "y" на кол-во цифр в новом числе
        if (y >= n) break
        i++
    } while (true)
    for (j in 1..y - n) {
        x /= 10
    }
    return x % 10
}

