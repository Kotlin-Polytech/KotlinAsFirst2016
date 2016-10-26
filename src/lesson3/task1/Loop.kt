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
    var count = 1
    var number = n / 10
    while (number > 0) {
        count++
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
fun fib(n: Int): Int {
    var a = 1
    var b = 0
    var m = 0
    for (i in 1 .. n) {
        m = a + b
        a = b
        b = m
    }
    return (m)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val min = Math.min(n , m)
    var nod = 1
    for (i in 2..min){
        if  (((m % i) == 0) && ((n % i) == 0)) nod = i
    }
    return (m * n / nod)
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
    for (i in n / 2 downTo 1) {
        if (n % i == 0)
            return (i)
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
fun isCoPrime(m: Int, n: Int): Boolean {
    val min = Math.min(n , m)
    var nod = 1
    for (i in 2..min) {
        if (((m % i) == 0) && ((n % i) == 0)) nod = i
    }
        if (nod == 1) return true
        else return false
}


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val lowerLimit = Math.sqrt (m.toDouble()). toInt()
    val upperLimit = Math.sqrt (n.toDouble()). toInt()
    for (k in lowerLimit..upperLimit) {
        val sqrK = k * k
        if ((m <= sqrK) && (sqrK <= n)) return true
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
    var a = 1
    var sin = x
    while (Math.pow(x,a * 2.0 + 1) / factorial(a * 2 + 1) > eps ) {
        if ((a % 2) == 0) sin += Math.pow(x, a * 2.0 + 1) / factorial(a * 2 + 1)
        else sin -= Math.pow(x, a * 2.0 + 1) / factorial(a * 2 + 1)
        a++
    }
    return (sin)
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
    var cos = 1.0
    while (Math.pow(x, a * 2.0) / factorial(a * 2) > eps) {
        if ((a % 2) == 0) cos += Math.pow(x, a * 2.0) / factorial(a * 2)
        else cos -= Math.pow(x, a * 2.0) / factorial(a * 2)
        a++
    }
    return (cos)
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var m = 0
    var number = n
    while (number > 0) {
        m = m * 10 + number % 10
        number /= 10
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
    if (revert(n) == n) return true
    else return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n
    val mod = n % 10
    for (i in digitNumber(n) downTo 1) {
        if (number % 10 != mod) {
            return true
        }
        number /= 10
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
fun squareSequenceDigit(n: Int): Int {
    var i = 1
    var leftBound = 0
    var digit = 0
    while ( leftBound < n) {
        val sqrI = i*i
        val rightBound = leftBound + digitNumber(sqrI)
        if (n <= rightBound)
            digit = sqrI.toString()[n-leftBound-1] - '0'
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
        val fib = fib (i)
        val rightBound = leftBound + digitNumber(fib)
        if (n <= rightBound)
            digit = fib.toString()[n - leftBound - 1] - '0'
        i++
        leftBound = rightBound
    }
    return digit
}
