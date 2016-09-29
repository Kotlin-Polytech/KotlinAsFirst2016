@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1
import lesson1.task1.sqr
import java.lang.Math.*
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
fun digitNumber(n: Int): Int = if (n / 10 == 0) 1 else 1 + digitNumber(n / 10)

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = if (n < 3) 1 else fib(n-1) + fib (n-2)

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int = TODO()
/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {

    var result = 1
    if (n % 2 == 0)
        result = 2
    else
        for (i in 3 .. n step 2 )
            if (n % i == 0)
            {
                result = i
                break
            }

    return result
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int  {

    var result = 0
    for (i in 1 .. n step 1)
        if ((n % i == 0) && (i < n))
        {
            result = i

        }
    return result
}



/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var result = false
    for (i in 2..min(m,n) step 1)
        if ((m % i == 0) && (n % i == 0))
        {
            result = false
            break
        }
        else
            result = true
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean{
    var result = false
    var left = sqrt(m.toDouble()).toInt()
    var right = sqrt(n.toDouble()).toInt()

    for (i in left..right  step 1 )
        if ((sqr(i.toDouble()).toInt()<= n)&&((sqr(i.toDouble()).toInt() >= m)))
        {
            result = true
        }
    return result
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
    var i = 0
    while (pow(x,2.0*i +1.0)/factorial(2*i + 1) > eps)
    {
        result += pow(-1.0,i.toDouble())* pow(x,2.0*i +1.0)/factorial(2*i + 1)
        i++
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

    var result = 0.0
    var i = 0
    while (pow(x, 2.0 * i) / factorial(2 * i) > eps) {
        result += pow(-1.0, i.toDouble()) * pow(x, 2.0 * i) / factorial(2 * i)
        i++
    }

    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
// функция для revert
fun countNumber (x: Int): Int = if (x < 10) 1 else countNumber (x % 10) +  countNumber (x / 10)

fun revert(n: Int): Int {
    var result = 0.0
    var temp = n
    for (i in countNumber(temp) downTo 1){
        result += (pow(10.0,(i-1).toDouble())*(temp % 10))
        temp /= 10
    }
    return result.toInt()
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean  = if (revert (n) == n) true else false


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var result = false
    var value = n % 10
    var temp = n / 10
    while (temp > 0)
    {

        if (temp % 10 != value )
        {
            result = true
            break

        }
        else
        {
            result = false

        }
        temp /= 10

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
    var k = 0
    var value = 0
    var result = 0
    var i = 1
    var j = 0

    var logic = false

    while (logic != true)
    {
        value = i * i
        j = countNumber(value)
        k += j
        if (k >= n )
        {
            k -= j
            value = revert(i*i)
            while (k != n)
            {
                result = value % 10
                value /= 10
                k++
            }
            logic = true
        }
        i++
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
    var k = 0
    var value = 0
    var result = 0
    var i = 1
    var temp = 0
    var j = 0

    var logic = false

    while (logic != true)
    {
        value = fib(i)
        j = countNumber(value)
        k += j
        if (k >= n )
        {
            k -= j
            value = fib(i)
            temp = revert(value)
            while (k != n)
            {
                result = temp % 10
                temp /= 10
                k++
            }
            logic = true
        }
        i++
    }
    return result
}



