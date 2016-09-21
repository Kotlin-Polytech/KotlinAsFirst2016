@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

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
    var count = 0
    var number = n
    do {
        number /= 10
        count += 1

    } while (number != 0)
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var a = 1
    var b = 1
    var result = 1
    if ((n == 1) || (n == 2)) return 1
    else {
        for (i in 1..n - 2) {
            result = a + b
            a = b
            b = result
        }
        return result
    }

    // return fib(n-1)+fib(n-2)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val prod = m * n
    var m1 = m
    var n1 = n
    while (m1 != n1) {
        if (m1 > n1) {
            m1 -= n1
        } else {
            n1 -= m1
        }
    }
    return prod / m1
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var del = 2
    while (n % del != 0) {
        del += 1
    }
    return del
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var del = n - 1
    while (n % del != 0) {
        del -= 1
    }
    return del
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var min = 0
    if (m < n) min = m else min = n
    for (i in 2..min) {
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
    var x = 0
    while (x * x < m) x++
    return ((x * x >= m) && (x * x <= n))
}


/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var result = 0.0
    var count = 1
    var n = 1
    while (Math.pow(x, n.toDouble()) / factorial(n) >= eps) {
        if (count % 2 != 0) {
            result += (Math.pow(x, n.toDouble()) / factorial(n))
            count++
            n += 2
        } else if (count % 2 == 0) {
            result -= (Math.pow(x, n.toDouble()) / factorial(n))
            count++
            n += 2
        }
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
    var result = 1.0
    var n = 2
    var count = 2
    while (Math.pow(x, n.toDouble()) / factorial(n) >= eps) {
        if (count % 2 == 0) {
            result -= (Math.pow(x, n.toDouble()) / factorial(n))
            n += 2
            count++
        } else if (count % 2 != 0) {
            result += (Math.pow(x, n.toDouble()) / factorial(n))
            n += 2
            count++
        }
    }
    return result
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var result = 0
    var number = n
    while (number > 0) {
        result = (result * 10) + number % 10
        number /= 10
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
fun isPalindrome(n: Int): Boolean {
    var count: Int = 0
    var number = n
    do {
        number /= 10
        count++
    } while (number != 0)
    if (count == 1) return false
    else {
        var rightMod = 10
        var rightDiv = 1
        var leftMod = 1
        var leftDiv = 1
        for (i in 1..count) leftMod *= 10
        for (i in 1..count - 1) leftDiv *= 10
        for (i in 1..count / 2) {
            if (n / rightDiv % rightMod != n % leftMod / leftDiv) return false
            else {
                rightMod *= 10
                rightDiv *= 10
                leftMod /= 10
                leftDiv /= 10
            }
        }
        return true
    }
    return true
}


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val x = n % 10
    var number = n
    //var result=false
    do {
        if (x != number % 10) {
            return true
            break
        }
        number /= 10
    } while (number != 0)
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
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
