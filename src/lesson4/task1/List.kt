@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.*
import lesson3.task1.*
/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        if (y < 0) listOf()
        else if (y == 0.0) listOf(0.0)
        else {
            val root = Math.sqrt(y)
            // Результат!
            listOf(-root, root)
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        if (b == 0.0) return listOf()
        else return sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0..list.size - 1) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it* it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var result = 0.0
    for (i in v )
        result += i * i
    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double{
    var result = 0.0

    if (list.isEmpty()){
        return result
    }
    else{
        for (i in list)
            result += i
        return result / list.size
    }
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty() == true){
        return list
    }
    else{
        val middle = mean(list)
        for (i in 0..list.size - 1)
        {
            list[i] -= middle
        }
        return list
    }
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double{
    var result = 0.0
    var i = 0
    while (i < a.size)
    {
        result += a[i] * b[i]
        i++
    }
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
*/
fun polynom(p: List<Double>, x: Double): Double{
    var result = 0.0
    if (p.isEmpty() == true){
        return result
    }
    else{
        for (i in 0..p.size - 1)
            result += p[i] * pow(x, i.toDouble())
        return result
    }
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()){
        return list
    }
    else{
        for (i in 1..list.size - 1){
            list[i] += list[i - 1]
        }
        return list
    }
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val result = mutableListOf<Int>()
    var digit = n
    var primeNumber = 2
    while (digit != 1){
        if (isPrime(primeNumber)){
            while ((digit % primeNumber) == 0){
                result.add(primeNumber)
                digit /= primeNumber
            }
        }
        primeNumber++
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String =
        factorize(n).joinToString(separator = "*")



/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var value = n
    var digit =  value / base
    if (value < base)
        result.add(value)
    else
    {
        while (value >= base) {
            result.add(value - digit * base)
            value = digit
            digit = value / base
        }
        result.add(value)
    }
    return result.asReversed()
}

/**
 * Сложная
 *
 * Перевести заданное натуральное число n в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var result = ""
    val letter = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    val digit = listOf(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
            26, 27, 28, 29, 30, 31, 32, 33, 34, 35)
    val value = convert(n, base)
    for (i in 0..value.size - 1 step 1){
        if (value[i] > 9)
            result += letter[digit.indexOf(value[i])]
        else
            result += value[i].toString()
    }
    return result
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var count = 0
    for (i in digits.asReversed())
    {
        result += i * powInt(base , count)
        count++
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int
{
    var result = 0
    val letter = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z')
    val digit = listOf(10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
            26, 27, 28, 29, 30, 31, 32, 33, 34, 35)
    var count = str.length - 1
    for (i in str)
    {
        if (i in letter)
            result += digit[letter.indexOf(i)] * powInt(base, count)
        else
            result += (i.toInt() - '0'.toInt()) * powInt(base, count)
        count--
    }
    return result
}

/**
 * Сложная
 *
 * Перевести натуральное число n в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */


fun roman(n: Int): String {
    var result = mutableListOf<Char>()
    val parts = mutableListOf<String>()
    var value = n
    for (i in 1..value / 1000 step 1)
        result.add('M')
    value %= 1000
    when (value / 100) {
        in 1..3 -> {
            for (i in 1..value / 100 step 1)
                result.add('C')
        }
        4 -> {
            result.add('C')
            result.add('D')

        }
        5 -> result.add('D')
        in 6..8 -> {
            result.add('D')
            for (i in 1..(value / 100 - 5) step 1)
                result.add('C')
        }
        9 -> {
            result.add('C')
            result.add('M')
        }
    }
    value %= 100
    when (value / 10) {
        in 1..3 -> {
            for (i in 1..value / 10 step 1)
                result.add('X')
        }
        4 -> {
            result.add('X')
            result.add('L')

        }
        5 -> result.add('L')
        in 6..8 -> {
            result.add('L')
            for (i in 1..(value / 10 - 5) step 1)
                result.add('X')
        }
        9 -> {
            result.add('X')
            result.add('C')
        }
    }
    value %= 10
    when (value) {
        in 1..3 -> {
            for (i in 1..value step 1)
                result.add('I')
        }
        4 -> {
            result.add('I')
            result.add('V')

        }
        5 -> result.add('V')
        in 6..8 -> {
            result.add('V')
            for (i in 1..(value - 5) step 1)
                result.add('I')
        }
        9 -> {
            result.add('I')
            result.add('X')
        }
    }
    return result.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun toRussian (n: Int, y: Int): String
{
    val digit = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    var result = ""
    var count = y
    var value = n
    if (count == 3)
    {
        val number = value / powInt(10, count - 1)
        when (number)
        {
            1 -> result += "сто "
            2 -> result += "двести "
            in 3..4 -> result += digit[number - 1] + "ста "
            in 5..9 -> result += digit[number - 1] + "сот "
        }
        value %=  powInt(10, count -1)
        count -= 1
    }
    if (count == 2)
    {
        val number = value / powInt(10, count - 1)
        when (number)
        {
            1 ->
            {
                val temp = value % powInt(10, count - 1)
                when (temp)
                {
                    0 -> result += "десять"
                    1 -> result += digit[temp - 1] + "надцать"
                    2 -> result += digit[temp - 1].substring(0, digit[temp - 1].length - 1 ) + "енадцать"
                    3 -> result += digit[temp - 1] + "надцать"
                    in 4..9 -> result += digit[temp - 1].substring(0, digit[temp - 1].length - 1 ) + "надцать"
                }
                count -= 1
            }
            in 2..3 -> result += digit[number - 1] + "дцать "
            4 -> result += "сорок "
            in 5..8 -> result += digit[number - 1] + "десят "
            9 -> result += "девяносто "
        }
        value %=  powInt(10, count - 1)
        count -= 1
    }
    if (count == 1)
    {
        val number = value / powInt(10, count - 1)
        when (number)
        {
            in 1..9 -> result += digit[number - 1]
        }
        value %=  powInt(10, count - 1)
        count -= 1
    }
    return result
}
fun russian(n: Int): String
{
    var result = ""
    val value = n
    val left = value / 1000
    val right = value % 1000
    val countLeft = countNumber(left)
    val countRight = countNumber(right)
    if (left != 0)
    {
        if ((left % 100 ) / 10 != 1)
            when (left % 10)
            {
                0 -> result += toRussian(left, countLeft) + "тысяч "
                1 -> result += toRussian(left, countLeft).dropLast(4) + "одна тысяча "
                2 -> result += toRussian(left, countLeft).dropLast(3) + "две тысячи "
                in 3..4 -> result += toRussian(left, countLeft) + " тысячи "
                in 5..9 -> result += toRussian(left, countLeft) + " тысяч "
            }
        else
            result += toRussian(left, countLeft) + " тысяч "
    }
    result += toRussian(right, countRight)
    return result.trim()
}
