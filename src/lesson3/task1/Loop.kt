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
var result: Int = 0
var nn: Int = n
    if (nn == 0)return 1 else
    while (nn >= 1) {
        nn = nn/10
        result++
    }
    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var f1 = 0
    var f2 = 1
    var f3 = 1
    for (m in 1..(n - 1)) {
        f3 = f2
        f2 += f1
        f1 = f3
    }
    return f2
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */

    fun nod(x: Int, y: Int): Int = if (x != 0) nod(y % x, x) else y;
    fun lcm(m: Int, n: Int): Int = m / nod(m, n) * n



/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
        var i: Int = 2
        while (n % i != 0) i++
        return i

    }


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var count: Int = 1
    for (i in 2..(n - 1))
        if (n % i == 0)count = i
    return count


}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var indicator: Int = 0
    if (m < n){
        for (i in 2..m)
            if ((m % i == 0) && (n % i == 0))indicator = 1
    else {
                for (i in 2..n)
                    if ((m % i == 0) && (n % i == 0))indicator = 1    }
    }
if (indicator == 0) return true else return false
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun sqr(x: Double) = x * x
fun squareBetweenExists(m: Int, n: Int): Boolean =
        if (m <= sqr(Math.sqrt(n.toDouble()).toInt().toDouble()) && sqr(Math.sqrt(n.toDouble()).toInt().toDouble()) <= n) true
            else false



/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var i = 0
    var sin1: Double = x
    var num: Double = x
    while (Math.abs(num) * 1000 > eps) {
        i++
        num = Math.pow(x, i * 2.0 + 1) / factorial(i * 2 + 1)
        if (i % 2 == 1) sin1 -= num
        else sin1 += num

    }
    return sin1
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
var cosus: Double = 1.0
var number: Double = x
while (Math.abs(number) * 1000 > eps) {
    i++
    number = Math.pow(x, i * 2.0) / factorial(i * 2)
    if (i % 2 == 1) cosus -= number
    else cosus += number

}
return cosus
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var result = 0
    var nn = n
    while (nn > 0) {
        result = result * 10 + nn % 10
        nn /= 10
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
        var result = 0
var nn = n
while (nn > 0) {
    result = result * 10 + nn % 10
    nn /= 10
}
if (result == n) return true else return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var nn1 = n % 10
    var nn = n
    var indicator = true
    while (nn > 0) {
        if (nn % 10 != nn1) indicator = false
        nn /= 10
    }
    if (indicator == false) return true else return false
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun quantity(n: Int): Int = n.toString().length

fun squareSequenceDigit(n: Int): Int {
    var i = 0
    var number = 0
    var result = 0
    while (number < n) {
        i++
        number += quantity(i * i)
    }
    result = i * i
    (n..number - 1).forEach { i -> result /= 10 }
    return (result % 10)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */


fun fibSequenceDigit(n: Int): Int { n.toString().length
    var i = 0
    var number = 0
    var result = 0
    while (number < n) {
        i++
        number += quantity(fib (i))
    }
    result = fib (i)
    (n..number - 1).forEach {i -> result /= 10}
    return (result % 10)
}
