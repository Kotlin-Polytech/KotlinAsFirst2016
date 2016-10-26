@file:Suppress("UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER")

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
    var number = n
    var result = 0
    if (n == 0) return 1
    if (n<0) number = -n
    while (number > 0) {
        result++
        number /= 10
    }
    return result
}

/* Простая
*
* Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
* Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
*/
fun fib(n: Int): Int {
    var prev = 0
    var result = 1
    for (i in 2..n) {
        result += prev
        prev = result - prev
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var a = 0
    var b = 0
    var c = 0
    if (m > n) {
        a = m
        b = n
    } else {
        a = n
        b = m
    }
    while (a % b > 0) {
        c = a
        a = b
        b = c % b
    }
    return n * m / b
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var div = 2
    while (n % div != 0) {
        div++
    }
    return div
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var div = n - 1
    while (n % div != 0) {
        div--
    }
    return div
}


/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var a = 1
    var b = 1
    if (m > n) {
        a = m
        b = n
    } else {
        a = n
        b = m
    }
    for (div in 2..b) {
        if ((a % div == 0) && (b % div == 0)) return false
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
    for (k in 0..n) {
        if ((k * k >= m) && (k * k <= n)) return true
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
    var result = x
    var num = x
    var step = 3.0
    while (Math.abs(num) >= eps) {
        num = -num * x * x / (step * (step - 1))
        result += num
        step += 2
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
    var num = 1.0
    var step = 2.0
    while (Math.abs(num) > eps) {
        num = -num * x * x / (step * (step - 1))
        result += num
        step += 2
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
    var number = n
    var buf = 0
    while (number > 0) {
        buf *= 10
        buf += number % 10
        number /= 10
    }
    return buf
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    if (revert(n) == n) return true
    return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    while (number > 9) {
        if (number % 10 == (number % 100) / 10)
            number /= 10
        else return true
    }
    if (number != n % 10) return true
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
    var line = "1"
    var count = 2
    var prevLength = 0
    while (prevLength + line.length < n) {
        prevLength += line.length
        line = (count * count).toString()
        count++
    }
    return (line[n - prevLength - 1] - '0').toInt()
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var line = "1"
    var count = 2
    var prevLength = 0
    while (prevLength + line.length < n) {
        prevLength += line.length
        line = fib(count).toString()
        count++
    }
    return (line[n - prevLength - 1] - '0').toInt()
}

