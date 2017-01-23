@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1
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
fun digitNumber(n: Int): Int {
    var count=0
    var number=n
    if (number==0) return 1
    else {
        while (number > 0) {
            number /= 10
            count += 1
        }
        return count
    }
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var beforePrevious = 1
    var previous = 0
    var num = 0
    for (i in 1..n) {
        num = beforePrevious + previous
        beforePrevious = previous
        previous = num
    }
    return num
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun nod(m: Int, n: Int): Int {
    var a = m
    var b = n
    while (b != 0) {
        val tmp = a % b
        a = b
        b = tmp
    }
    return a
}

fun lcm(m: Int, n: Int): Int = n * m / nod(m, n)





/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt())
        if (n%i==0) return i
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n/2 downTo sqrt(n.toDouble()).toInt())
        if (n%i==0) return i
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = nod(m,n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var i=ceil(sqrt(m.toDouble()))
    i*=i
    return i>=m && i<=n
}

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var i = 1
    var sin = x
    while (pow(x,(1+i*2.0)) / factorial(1+i*2) > eps) {
        if (i%2 == 1)
            sin -= pow(x,(1+i*2.0)) / factorial(1+i*2)
        else
            sin += pow(x,(1+i*2.0)) / factorial(1+i*2)
        i++
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
    var i = 1
    var cos = 1.0
    while (pow(x,(i*2.0)) / factorial(i*2) > eps) {
        if (i%2 == 1)
            cos -= (pow(x,(i*2.0)) / factorial(i*2))
        else
            cos += (pow(x,(i*2.0)) / factorial(i*2))
        i++
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
    var new = 0
    var number = n
    while (number > 0) {
        new *= 10
        new += number%10
        number /= 10
    }
    return new
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    return n.toString() == n.toString().reversed()
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = abs(n)
    var last = num%10
    while (num > 0){
        if (last != num%10) {
            return true
        }
        last = num%10
        num /= 10
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
    var pointer = 0
    var num = 0
    while (pointer < n){
        val a = i*i
        val currentLength = pointer + digitNumber(a)
        if (currentLength >= n)
            num = (a).toString()[n-pointer-1] - '0'
        pointer = currentLength
        i++
    }
    return num
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
    var pointer = 0
    var num = 0
    while (pointer < n){
        val fib = fib(i)
        val currentLength = pointer + digitNumber(fib)
        if (currentLength >= n)
            num = fib.toString()[n-pointer-1] - '0'
        pointer = currentLength
        i++
    }
    return num
}

