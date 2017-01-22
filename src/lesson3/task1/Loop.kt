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
    var i = 0
    var x = Math.abs(n)
    if (x == 0) return 1
    else while (x > 0) {
        x /= 10
        i++
    }
    return i
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var n1 = 2
    var x1 = 1
    var x2 = 1
    var x = 1
    if (n == 1 || n == 2) {
        return x2
    } else while (n1 != n) {
        x = x1 + x2
        x1 = x2
        x2 = x
        n1++
    }
    return x
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var n1 = n
    var m1 = m
    while (m1 != n1) {
        if (m1 > n1) {
            m1 -= n1
        }
        if (m1 < n1) {
            n1 -= m1
        }
    }
    val k = m * n / n1
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var x = 1
    var t = 1
    while (t != 0) {
        t = n % (x + 1)
        x++
    }

    return x
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var x = n - 1
    var t = 1
    while (t != 0) {
        t = n % x
        x--
    }
    x++
    return x
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = m * n / lcm(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k = 1
    while (k * k <= n) {
        if (k * k >= m) return true
        k++
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
    var f = 0
    var sin: Double = x
    var number: Double = x
    while (Math.abs(number) > eps) {
        f++
        number = Math.pow(x, f * 2.0 + 1) / factorial(f * 2 + 1)
        if (f % 2 == 1) sin = sin - number
        else sin = sin + number

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
    var f = 0
    var cos: Double = 1.0
    var number: Double = x
    while (Math.abs(number) > eps) {
        f++
        number = Math.pow(x, f * 2.0) / factorial(f * 2)
        if (f % 2 == 1) cos = cos - number
        else cos = cos + number

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
    var number = n
    var results = 0
    while (number != 0) {
        results = (results + number % 10) * 10
        number = number / 10
    }
    results = results / 10
    return results

}

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
fun squareSequenceDigit(n: Int): Int {
    var x = 1
    var m = 1
    var t: Int
    var l = n
    var count = 1
    while (l > 0) {
        m = x * x
        t = m
        while (t >= 10) {
            count++
            t /= 10
        }
        l -= count
        count = 1
        x++
    }
    l = Math.abs(l)
    m = m / Math.pow(10.0, l * 1.0).toInt() % 10
    return m
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var x = 1.0
    var m = 1
    var t: Int
    var l = n
    var count = 1
    while (l > 0) {
        m = ((Math.sqrt(5.0) / 5) * (Math.pow((1 + Math.sqrt(5.0)) / 2, x) - Math.pow((1 - Math.sqrt(5.0)) / 2, x))).toInt()
        t = m
        while (t >= 10) {
            count++
            t /= 10
        }
        l -= count
        count = 1
        x++
    }
    l = Math.abs(l)
    m = m / Math.pow(10.0, l * 1.0).toInt() % 10
    return m
}
