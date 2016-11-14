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
    var c=0
    var nn=Math.abs(n)
    do{
        nn/=10
        c++
    } while(nn>0)
    return c
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var a = 1
    var b = 1
    var box: Int
    for (i in 1..n - 2) {
        box = b
        b += a
        a = box
    }
    return b
}
/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun nod(mm: Int, nn: Int): Int{
    var m = mm
    var n = nn
    while (m!=0 && n!=0) {
        if (m > n)
            m %= n
        else
            n %= m
    }
    return (m+n)
}

fun lcm(m: Int, n: Int): Int = m*n/nod(m,n)

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int{
    for(d in 2..Math.sqrt(n.toDouble()).toInt()){
        if(n % d == 0)
            return d
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int{
    for(i in n/2 downTo Math.sqrt(n.toDouble()).toInt()){
        if(n % i ==0)
            return i
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
fun isCoPrime(m: Int, n: Int): Boolean{
    if(nod(m,n)>1)
        return false
    else
        return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean{
    var k=Math.sqrt(m.toDouble()).toInt()
    while (k*k <= n) {
        if (k * k <= n && k * k >= m)
            return true
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
    var it: Double
    var sin_x: Double
    var i=3.0
    val xx= x % (2*Math.PI)
    sin_x = xx
    it = xx
    while (Math.abs(it)>eps) {
        it = it * xx * xx / i / (i - 1) * (-1)
        i+=2
        sin_x+=it
    }
    return sin_x
}

/**
 * Простая
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var it: Double
    var cos_x: Double
    var i = 2.0
    cos_x = 1.0
    it = 1.0
    val xx = x % (2 *Math.PI)
    while (Math.abs(it) > eps) {
        it = it * xx * xx / i / (i - 1) * (-1)
        i += 2
        cos_x += it
    }
    return cos_x
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int{
    var nn = n
    var r = 0
    while (nn>0){
        r = r * 10 + nn % 10
        nn/=10
    }
    return r
}
 
/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean{
    if (n<10)
        return true
    val a = mutableListOf<Int>()
    var nn = n
    while (nn != 0){
        a.add(nn % 10)
        nn/=10
    }
    for (i in 0..(a.size /2)){
        if (a[i] != a[a.size-i-1])
            return false
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean{
    var nn = n
    while(nn>9) {
        if ((nn % 100) % 11 != 0)
            return true
        nn/=10
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
fun squareSequenceDigit(n: Int): Int{
    var c =0
    var ch=1
    var square = 1
    while(c!=n){
        square = ch *ch
        c+= digitNumber(square)
        while(n<c) {
            square/= 10
            c--
        }
        ch++
    }
    return square % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int{
    var c =0
    var ch=1
    var nfib = 1
    while(c!=n){
        nfib = fib(ch)
        c+= digitNumber(nfib)
        while(n<c) {
            nfib/= 10
            c--
        }
        ch++
    }
    return nfib % 10
}
