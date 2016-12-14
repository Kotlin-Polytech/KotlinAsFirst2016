@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

fun ten (n: Int) : Int {
    if (n == 0) return 1
    var answ : Int = 1
    for (i in 1..n) {
       answ *= 10
    }
    return answ
}
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
    var copy : Int = n
    if (n < 0) copy *= -1
    var lenght: Int = 0
    for (i in 1..9999) {
        lenght += 1
        copy /= 10
        if (copy < 1) break
        else continue
    }
    return lenght
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int =
if (n <=2) 1
else  fib(n-2) + fib(n-1)

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var sum : Int = 1
    for (k in (2..n*m)) {
        sum += 1
        if ((k % n == 0) && (k % m == 0)) break
        else continue
    }
    return sum
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var sum : Int = 1
    for (k in(2..n)) {
        sum +=1
        if (n % k == 0) break
        else continue
    }
    return sum
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var sum : Int = n
    for (k in n-1 downTo 1) {
        sum -= 1
        if (n % k == 0) break
        else continue
    }
    return sum
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var Arnold : Int = 0
    for (catdog in 2..n) {
        if ((m % catdog == 0) && (n % catdog == 0)) Arnold += 1
    }
    if (Arnold == 0) return true
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
    if ((m == 0) || (n == 0)) return true
    for (sqrPants in 1..m) {
         if ((sqrPants * sqrPants >= m) && (sqrPants * sqrPants <= n)) return true
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
    var copy :Int = n
    var lenght : Int = 1
    var answ : Int = 0
    for (i in 1..9999) {
        lenght +=1
        copy /= 10
        if (copy <= 1) break
        else continue
    }
    copy = n
    for (i in 1..lenght) {
        answ = answ * 10 + copy % 10
        copy /= 10
        if (copy < 1) break
        else continue
    }
    return answ
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    val copy: Int = n
    val lenght: Int = digitNumber(n)
    if (lenght == 2) return true
    if (lenght % 2 == 0)
        if (revert(copy / ten(lenght / 2)) != copy % ten(lenght/ 2)) return false
        else return true
    else
        if (revert(copy / ten((lenght + 1) / 2)) != copy % ten((lenght - 1) / 2)) return false
        else return true
}
/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n<10 ) return false
    var copy : Int = n
    var smthng : Int = 0
    val pudge : Int = n % 10
    var lenght: Int = 0
    for (i in 1..9999) {
        lenght += 1
        copy /= 10
        if (copy <= 1) break
        else continue
    }
    copy = n
    for (i in 1..999) {
        if (copy % 10 == pudge) smthng +=1
        copy /= 10
        if (copy < 1) break
        else continue
    }
    if (smthng == lenght) return false
    else return true
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    if (n == 1) return 1
    var string : Int = 0
    var answ : Int = 0
    var copy : Int = n
    for (i in 1..n) {
        answ += digitNumber(string)
        if (answ > n) break
        string = i * i
        copy -= digitNumber(i * i)
    }
    answ = string
    if (copy == 0) return string % 10
    else {
        for (i in 1..99999) {
            copy += 1
            answ /= 10
            if (copy == 0) break
        }
        return answ % 10
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int = TODO()