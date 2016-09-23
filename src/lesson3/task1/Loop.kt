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
fun digitNumber(n: Int): Int =
        if (n / 10 == 0 || n == 0) 1 else digitNumber(n / 10) + 1

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var fib1 = 1
    var fib2 = 0
    var fib = 1
    for (i in 1..n-1) {
        fib = fib1 + fib2
        fib2 = fib1
        fib1 = fib
    }
    return fib
}


/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val gcd = gcd(m, n)
    return m * n / gcd


}

fun gcd(m: Int, n: Int): Int {
    val max = Math.max(m, n)
    val min = Math.min(m, n)

    if (max % min == 0) return min else return gcd(min, max % min)
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
    var i = n / 2
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
fun isCoPrime(m: Int, n: Int): Boolean = TODO()

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = TODO()

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
fun revert(n: Int): Int {
    var i = digitNumber(n)
    var cn = n
    var rn = 0
    while (i != 0) {
        rn += cn % 10 * pow(10, i - 1)
        cn /= 10
        i--
    }

    return rn
}

fun revertLong(n: Int): Long {
    var i = digitNumber(n)
    var cn = n
    var rn = 0L
    while (i != 0) {
        rn += cn % 10 * pow(10, i - 1).toLong()
        cn /= 10
        i--
    }

    return rn
}


/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    val revert = revertLong(n)
    if (revert == n.toLong()) return true else return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n % 10 != n / 10 % 10 && n / 10 != 0) return true
    else if (n / 10 == 0) return false
    else return hasDifferentDigits(n / 10)
}

fun pow (n: Int, p: Int): Int {
    var np = n
    if (p == 0) return 1
    for (i in 2..p) np *= n
    return np
}

fun main(args: Array<String>) {
    println(pow(2,10))
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var sequence = 0
    var i = 0
    while (n > sequence) {
        i++
        sequence += digitNumber(i * i)
    }
    val overage = sequence - n
    val digit = i * i
    return digit / pow(10, overage) % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
/*  var sequence = 0
  var i = 0
  while (n > sequence) {
      i++
      sequence += digitNumber(fib(i))
  }
  val overage = sequence - n;
  val digit = fib(i)
  return (digit / Math.pow(10.0, overage.toDouble()) % 10).toInt()
*/
    val fibList = mutableListOf<Int>()
    var fib = 1
    var fib1 = 0
    var fib2 = 1


    while (n > fibList.size) {
        if (fib < 10) fibList.add(fib)
        else {
            val fixDigit = digitNumber(fib)
            for (i in 1..fixDigit) {
                fibList.add(fib / pow(10, fixDigit - i) % 10)
            }
        }
        fib = fib1 + fib2
        fib1 = fib2
        fib2 = fib
    }
    return fibList[n - 1]
}
