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
fun fib(n: Int): Int {
    var result = 0
    var next = 1
    for (i in 1..n  step 1){
        val prev = result
        result += next
        next = prev
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */


fun gcd(m: Int, n: Int): Int{
    var digitOne = m
    var digitTwo = n
    while (digitOne != digitTwo)
        if (digitOne > digitTwo)
            digitOne -= digitTwo
        else
            digitTwo -= digitOne
    return digitOne
}
fun lcm(m: Int, n: Int): Int {
    val nod = gcd(m,n)
    val result = m * n / nod

    return result
}
/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {

    var result = n
    if (n % 2 == 0)
        result = 2
    else
        for (i in 3 .. n / 2 step 2 )
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

    var result = n
    for (i in 1 .. n / 2 step 1)
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
fun isCoPrime(m: Int, n: Int): Boolean = gcd(m,n) == 1


/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */

fun squareBetweenExists(m: Int, n: Int): Boolean {
    var res = false
    for (i in 0..n )
        if ((i*i >= m)  && (i*i <= n))
            res = true
    return res
}





/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double = TODO()




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
// функция для revert
fun countNumber (x: Int): Int = if (x < 10) 1 else 1 +  countNumber (x / 10)

fun powInt (x: Int , y: Int): Int {
    var result = 1
    for (i in 1..y step 1)
        result *= x
    return result
}
fun revert(n: Int): Int {
    var result = 0
    var temp = n
    for (i in countNumber(temp) downTo 1){
        result += (powInt(10,(i-1))*(temp % 10))
        temp /= 10
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
fun isPalindrome(n: Int): Boolean = TODO()


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var result = false
    val value = n % 10
    var temp = n / 10
    while (temp > 0)
    {
        if (temp % 10 != value )
            return  true
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
    var k = 1 // тк 1  число равно 1
    var result = 0
    var logic = false
    var i = 2
    var nextDigit = 1
    while (logic != true)
    {
        if (k < n)
        {
            nextDigit = i*i
            k += countNumber(nextDigit)
            i++
        }
        if (k >= n)
        {
            if (k == n)
                result = nextDigit % 10
            else
                while (k >= n)
                {
                    result = nextDigit % 10
                    nextDigit /= 10
                    k--
                }
            logic = true
        }
    }
    return result
}



/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584  4181  6795 10946  17741 28687 47428 75115 122543
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var k = 2 // тк 1 и 2 числа равны  1
    var result = 0
    var fibon = 1 //очередное число ряда
    var next= 1 //следущее число
    var logic = false
    if (n < 3)
        result = 1
    else
        while (logic != true)
        {
            if (k < n)
            {
                val prev = fibon
                fibon += next
                next = prev
                k += countNumber(fibon)
            }
            if (k >= n)
            {
                if (k == n)
                    result = fibon % 10
                else
                    while (k >= n)
                    {
                        result = fibon % 10
                        fibon /= 10
                        k--
                    }
                logic = true
            }
        }
    return result
}



