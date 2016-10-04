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
    var n1 = n
    var count = 0
    if (n1 == 0) return 1
    while (n1 != 0) {
        n1 /= 10
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
    var a = 1
    var b = 1
    var c = 0
    if (n in 1..2) return 1
    else {
        for (i in 3..n) {
            c = a + b
            a = b
            b = c
        }
    }
    return c
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */

//переделать
fun lcm(m: Int, n: Int): Int {
    /*var k = 0
    val l = m * n
    for (i in 1..l) {
        if (i % m == 0 && i % n == 0) {
            k = i
            break
        }
    }
    return k
    */
    //2 способ алгортим Евклида
    var m1 = m
    var n1 = n
    while (m1 != n1) {
        if (m1 > n1) m1 -= n1
        else n1 -= m1
    }
    return (m * n) / m1
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */

//переделал
fun minDivisor(n: Int): Int {
    var k = 0
    for (i in 2..n) {
        if (n % i == 0) {
            k = i
            break
        }
    }
    return k
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
//переделал
fun maxDivisor(n: Int): Int {
    var k = 0
    for (i in n - 1 downTo 1) {
        if (n % i == 0) {
            k = i
            break
        }
    }
    return k
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */

fun isCoPrime(m: Int, n: Int): Boolean {
    for (i in 2..Math.min(m, n)) {
        if ((m % i == 0) && (n % i == 0)) {
            return false
        }
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
    for (i in m..n) {
        if (i % Math.sqrt(i.toDouble()) == 0.0) {
            return true
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
    var count = 1
    var a = x
    var A = x
    while (Math.abs(a) >= Math.abs(eps)) {
        count++
        a = -a * x * x / ((2 * count - 1) * (2 * count - 2))
        A += a
    }
    return A
}

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double = TODO()

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var n1 = n
    var m = 0
    while (n1 != 0) {
        m = m * 10 + n1 % 10
        n1 /= 10
    }
    return m
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */

fun isPalindrome(n: Int): Boolean {
    var n1 = n
    var count = 0
    while (n1 != 0) {
        count = count * 10 + n1 % 10
        n1 /= 10
    }
    return count == n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var b = n
    var m = 0
    var a = 0
    if (n < 10) return false
    else {
        m = b
        while (b > 0) {
            a = b % 10
            b /= 10
            m = b
            while (m > 0) {
                if (a == m % 10) m /= 10
                else return true
            }
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
fun squareSequenceDigit(n: Int): Int = TODO()

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var n1 = n
    var c = 0
    var a = 1
    var b = 1
    var m = 10
    var l = 1
    var count = 2
    var count2 = 0
    if (n in 1..2) return 1
    while (count < n1) {
        c = a + b
        a = b
        b = c
        while (c > m) {
            m *= 10
            l++
        }
        count += l
    }
    if (count != n) {
        count2 = count - n
        for (i in 1..count2) {
            c /= 10
        }
    }
    return c % 10
}