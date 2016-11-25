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
    if (n == 0) return (1)
    else {
        var number = n
        var count = 0
        while (number != 0) {
            number = number / 10
            count = count + 1
        }
        return (count)
    }
}



/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var a = 1
    var b = 0
    var t = 0
    for (i in 1..n) {
        t = a + b
        a = b
        b = t
    }
    return (t)
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = Math.min(m, n)
    var nod = 1
    for (i in 2..k) {
        if (((m % i) == 0) && ((n % i) == 0)) nod = i
    }
    return ((m * n) / nod)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n) {
        if (n % i == 0)
            return (i)
    }
    return (n)
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var divisor = 1
    for (i in 2..n - 1) {
        if (n % i == 0) divisor = i
    }
    return (divisor)
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var k = Math.min(m, n)
    var nod = 1
    for (i in 2..k) {
        if (((m % i) == 0) && ((n % i) == 0)) nod = i
    }
    return nod == 1
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean =
        (m == n)||(((Math.sqrt(1.0 * n)).toInt() - (Math.sqrt(1.0 * m)).toInt()) >= 1)

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var a = 0
    val b = x % (2 * Math.PI)
    var sin = b
    var c = b
    while (Math.abs(c) >= eps) {
        a++
        c = Math.pow(b, a * 2.0 + 1) / factorial(a * 2 + 1)
        if ((a % 2) == 1) sin -= c
        else sin += c
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
    var a = 1
    val b = x % (2 * Math.PI)
    var cos = 1.0
    var increase = 1.0
    var comp: Double
    do {
        increase *= b * b
        if (a % 2 != 0) comp = -1 * increase / factorial(a * 2)
        else comp = increase / factorial(a * 2)
        cos += comp
        a++
    } while (Math.abs(comp) > Math.abs(eps))
    return (cos)
}



/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var m = n
    var result = 0
    for (i in digitNumber(n) - 1 downTo 0) {
        result *= 10
        result += m % 10
        m /= 10
    }
    return (result.toInt())
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean = n.toString().reversed() == n.toString()

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val digit = n % 10
    var num = n
    for (i in digitNumber(n) downTo 1) {
        if (num % 10 != digit) {
            return (true)
        }
        num /= 10
    }
    return (false)
}



/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * 12345678901234567890123
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var i = 1
    var leftBound = 0
    var digit = 0
    while (leftBound < n) {
        val sqr = i * i
        val rightBound = leftBound + digitNumber(sqr)
        if (n <= rightBound)
            digit = sqr.toString()[n - leftBound - 1] - '0'
        i++
        leftBound = rightBound
    }
    return digit

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
    var leftBound = 0
    var digit = 0
    while (leftBound < n) {
        val fib = fib(i)
        val rightBound = leftBound + digitNumber(fib)
        if (n <= rightBound)
            digit = fib.toString()[n - leftBound - 1] - '0'
        i++
        leftBound = rightBound
    }
    return digit
}

