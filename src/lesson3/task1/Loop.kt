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
    var digit = 0
    var numb= Math.abs(n.toLong())
    if (numb == 0L) return 1
    while (numb > 0L) {
        numb /= 10
        digit += 1
    }
    return digit
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var numb1 = 1
    var numb2 = 1
    var numb3 = 0
    if (n == 1 || n == 2) return 1
    for (i in 3..n) {
        numb3 = numb1 + numb2
        numb1 = numb2
        numb2 = numb3
    }
    return numb3
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k = Math.max(m, n)
    while (k%m != 0 || k%n != 0) {
        k++
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var del = 2
    while (n%del != 0) {
        del++
    }
    return del
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var del = n - 1
    while (n%del != 0) {
        del--
    }
    return del
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var del = 2 //на что делим в цикле
    val minMN = Math.min(m, n)
    while (del <= minMN) {
        if (m%del == 0 && n%del == 0) return false
        del++
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
    var k : Int = Math.sqrt(m.toDouble()).toInt()
    while (k*k <= n) {
        if (k*k in m..n) return true
        k++
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
    var sign = 1
    var cur = x%(Math.PI*2)
    var sinx = 0.0
    var n = 1
        while (true) {
            cur = sign  / factorial(n)  * Math.pow(x,n.toDouble())
            if (Math.abs(cur) < eps)
                break
            sign = -sign
            sinx += cur
            n += 2
        }
    return sinx
}

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var sign = -1
    var cur = x%(Math.PI*2)
    var cosx = 1.0
    var n = 2
    while (true) {
        cur = sign / factorial(n) * Math.pow(x,n.toDouble())
        if (Math.abs(cur) < eps)
            break
        sign = -sign
        cosx += cur
        n += 2
    }
    return cosx
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var rev = 0
    var numb = n
    while (numb > 0) {
        rev = rev*10 + (numb%10)
        numb /= 10
    }
    return rev
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    val revertN = revert(n)
    return revertN == n
}


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var numb = n
    var prew = n%10
    var cur = 0
    while (numb > 0) {
        cur = numb%10
        if (prew != cur) return true
        numb /= 10
        prew = cur
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

    var call = 0 //общая длина всех "проверенных" цифр
    var ccur = 0 //длина текущего числа
    var cur = 0 //текущее число
    var i = 1 //номер текущего числа
    var ab = 0 //какая цифра(по счету) из текущего числа (при заходе числа за границу n)
//понадобится
    var k = 0 //последняя цифра текущего числа
    while (true) {
        cur = fib(i) //приравниваем текущему число число последовательности Фиб. с
//номером i
        ccur = digitNumber(cur) //считаем длину текущего числа(сколько в нем цифр?)
        call += ccur //увеличиваем длину обработанной пос-ти на длину текущего числа
        if (call == n) return cur % 10 //если длина обраб. пос-ти равна n, то
// возвращаем последнюю цифру текущего числа
        if (call > n) { //если обработанная длина "вышла за" n
            ab = n - (call - ccur) //какая цифра данного числа под номером n?
            while (cur > 0) { //поиск нужной цифры текущего числа
                k = cur % 10
                if (ccur == ab) return k
                cur /= 10
                ccur--
            }
        }
        i++
    }
}

