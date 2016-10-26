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
    var digits = 0
    var number = n
    if (n == 0) return 1
    else {
        while (number > 0) {
            digits += 1
            number /= 10
        }
        return digits
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var a = 1 // число с номером n-2
    var b = 1 // число с номером n-1
    var c = 0 // искомое число с номером n
    if ( n <= 2 ) return 1
    else {
        for (i in 1..n - 2) {
            c = a + b
            a = b
            b = c
        }
        return c
    }
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = Math.max(n, m)
    val equalToK = k
    while ( k % m != 0 || k % n != 0 ) {
        k += equalToK
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var i = 2
    while (n % i != 0) {
        i++
    }
    return i
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var i = n - 1
    while (n % i != 0) {
        i--
    }
    return i
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val min = Math.min(m, n)
    for (i in 2..min) {
        if (m % i == 0 && n % i == 0)
            return false
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
    val lowerLim = Math.sqrt(m.toDouble()).toInt()
    val upperLim = Math.sqrt(n.toDouble()).toInt()
    for (k in lowerLim..upperLim) {
        if (m <= k * k && k * k <= n) return true
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
    var currentExp = x % (2 * Math.PI) // текущее значение x^(какой-то степении) в членах ф-ции
    var currentSin = currentExp // текущее значение sin (x)
    var currentAdding = currentExp // значение очередного члена ряда
    var num = 1 // номер члена ф-ции (для + и -)
    var digit = 3 // для факториала
    while (Math.abs(currentAdding) >= eps) {
        currentExp *= x * x
        if (num % 2 != 0) currentSin -= currentExp / factorial(digit)
        else currentSin += currentExp / factorial(digit)
        currentAdding = currentExp / factorial(digit)
        num += 1
        digit += 2
    }
    return currentSin
}

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var currentExp = 1.0 // текущее значение x^(какой-то степении) в членах ф-ции
    var currentCos = currentExp // текущее значение cos (x)
    var currentAdding = currentExp // значение текущего члена ряда
    var num = 1 // номер члена ф-ции (для + и -)
    var digit = 2 // для факториала
    while (Math.abs(currentAdding) >= eps) {
        currentExp *= x * x
        if (num % 2 != 0) currentCos -= currentExp / factorial(digit)
        else currentCos += currentExp / factorial(digit)
        currentAdding = currentExp / factorial(digit)
        num += 1
        digit += 2
    }
    return currentCos
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var a = 0
    var n1 = n
    while (n1 > 0) {
        a = a * 10 + n1 % 10
        n1 /= 10
    }
    return a
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
    val lastDigit = n % 10
    var n1 = n
    for (i in 1..digitNumber(n)) {
        if (lastDigit != n1 % 10) return true
        n1 /= 10
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
    var digit = 0 // номер текущей цифры в последовательности
    var number = 0 // целое число, крадрат которого вычисляется
    var result = 0 // n-ая цифра
    var revertedSquare: Int // квадрат, записанный в обратном порядке
    while (digit < n) {
        number++
        revertedSquare = revert(number * number)
        for (i in 1..digitNumber(number * number)) {
            result = revertedSquare % 10
            digit ++
            if (digit == n) return result
            revertedSquare /= 10
        }
    }
    return result
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var digit = 0 // номер искомой цифры в последовательности
    var number = 0 // целое число, fib которого вычисляется
    var result = 0 // искомая цифра
    var revertedFib: Int
    while (digit < n) {
        number++
        revertedFib = revert(fib(number))
        for (i in 1..digitNumber(fib(number))) {
            result = revertedFib % 10
            digit ++
            if (digit == n) return result
            revertedFib /= 10
        }
    }
    return result
}
