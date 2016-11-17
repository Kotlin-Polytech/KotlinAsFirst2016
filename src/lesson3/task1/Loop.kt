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
    var count = 0
    var number = n
    if (number == 0) count = 1 else
        while (Math.abs(number) > 0) {
            count += 1
            number /= 10
        }

    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = when {
    n == 1 -> 1
    n == 2 -> 1
    else -> fib(n - 1) + fib(n - 2)
}

/**
 * Функция для нахождения НОД чисел m и n по алгоритму Евклида
 */
fun gcd(m: Int, n: Int): Int {
    var digit1 = m
    var digit2 = n
    while (digit1 != 0 && digit2 != 0)
        if (digit1 > digit2) digit1 %= digit2
        else digit2 %= digit1
    return digit1 + digit2
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = m * n / gcd(m, n)

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
fun isCoPrime(m: Int, n: Int): Boolean = gcd(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (i in m..n) {
        return (Math.sqrt(i.toDouble()) == Math.sqrt(i.toDouble()).toInt().toDouble())
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
//И sin, и cos фейлятся на тестах с точностью 1.0Е-10, хотя условие по идее выполняется
//Как это можно исправить?
fun sin(x: Double, eps: Double): Double {
    var result = 0.0
    var currentMember = eps
    var count = 1
    while (Math.abs(currentMember) >= eps) {
        if ((count + 1) % 4 == 0) currentMember = -Math.pow(x, count.toDouble()) / factorial(count)
        else currentMember = Math.pow(x, count.toDouble()) / factorial(count)
        result += currentMember
        count += 2
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
    return sin(Math.PI / 2 + x, eps)
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var number = 0
    var enteredNumber = n
    while (enteredNumber > 0) {
        number *= 10
        number += enteredNumber % 10
        enteredNumber /= 10
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
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n < 10) return false
    val digit = n % 10
    var number = n / 10
    while (number > 0) {
        if (digit != number % 10) return true
        else {
            number /= 10
        }
    }
    return false
}

fun powInt(a: Int, b: Int): Int {
    if (b == 0) return 1
    var result = a
    for (i in 1..b - 1) {
        result *= a
    }
    return result
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var currentDigit = 1
    var count = 1
    while (count < n) {
        currentDigit += 1
        count += digitNumber(currentDigit * currentDigit)
    }
    currentDigit = (currentDigit * currentDigit) / powInt(10, (count - n))
    return currentDigit % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
//Kotlin-Polytech-Bot фейлит один из тестов, ибо тот не проходится за 10 сек.
//Через добавленный AssertEquals для значений зафейленного теста прога проходит за 21 сек.
//Как можно ускорить работу?
//UPD:Всё, вроде придумал
fun fibSequenceDigit(n: Int): Int {
    var count = 1
    var fib1 = 0
    var fib2 = fib1
    var currentFib = 1

    while (count < n) {
        fib1 = fib2
        fib2 = currentFib
        currentFib = fib1 + fib2
        count += digitNumber(currentFib)
    }
    currentFib /= powInt(10, (count - n))
    return currentFib % 10
}