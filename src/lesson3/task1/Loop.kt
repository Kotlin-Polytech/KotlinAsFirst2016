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
    if (n == 0) return 1
    else {
        var k = 0
        var m = n
        while (m != 0) {
            m /= 10
            k++
        }
        return k
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var m1 = 1
    var m2 = 1
    var l = 0
    if (n == 1) return 1
    if (n == 2) return 1
    for (i in 3..n) {
        l = m1 + m2
        m1 = m2
        m2 = l
    }
    return l
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = 0
    if ((m == 0) || (n == 0)) return 0
    for (i in 2..n * m) {
        if (((i % n) == 0) && ((i % m) == 0)) {
            k = i
            return k
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
    for (i in 2..n)
        if ((n % i) == 0) return i
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n - 1 downTo 1)
        if ((n % i) == 0) return i
    return n
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..m) {
        if (((m % i) == 0) and ((n % i) == 0)) return false
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
    for (result in m..n) {
        val sqrt = Math.sqrt(result.toDouble())
        if (sqrt % 1 == 0.0)
            if (sqrt * sqrt in m..n) return true
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
    val x1 = x % (2 * Math.PI)
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
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var i = 0
    val x1 = x % (2 * Math.PI)
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
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var answer = 0
    var m = n
    while (m > 0) {
        answer = answer * 10 + m % 10
        m /= 10
    }
    return answer
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var a = 0
    var m = n
    while (m > 0) {
        a = a * 10 + m % 10
        m /= 10
    }
    if (a == n) return true else return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var m = n
    val k = n % 10
    while (m > 0)
        if (k == m % 10) m /= 10 else return true
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
    var sq = 4
    if (n in 1..3) return n * n
    var i = 3
    while (i < n) {
        var m = sq * sq
        while (m > 0) {
            m /= 10
            i++
        }
        sq++
    }
    sq--
    sq *= sq
    while (i > n) {
        i--
        sq /= 10
    }
    return sq % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun quantity(n: Int): Int {
    var r: Int = 0
    var nn: Int = n
    while (nn > 0) {
        nn /= 10
        r++
    }
    return r
}

fun fibSequenceDigit(n: Int): Int {
    var i = 0
    var number = 0
    while (number < n) {
        i++
        number += quantity(fib(i))
    }
    var resalt = fib(i)
    for (k in n..number - 1) {
        resalt /= 10
    }
    return (resalt % 10)
}