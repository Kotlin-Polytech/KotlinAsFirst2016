@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import java.lang.Math.*


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
fun squares(list: List<Int>) = list.map { it * it }

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
    for (i in 0..v.size - 1) {
        result += (v[i] * v[i])
    }
    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var result = 0.0
    for (i in 0..list.size - 1) {
        result += list[i]
    }
    return if (list.isEmpty()) result else result / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var result = list
    val mean = mean(list)
    if (list.isNotEmpty()) {
        for (i in 0..list.size - 1) {
            result[i] = list[i] - mean
        }
    }
    return result
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var result = 0.0
    if (a.isEmpty()) return 0.0
    if (b.isEmpty()) return 0.0
    for (i in 0..a.size - 1) {
        result += a[i]*b[i]
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
fun polynom(p: List<Double>, x: Double): Double {
    var result = 0.0
    if (p.isEmpty()) return 0.0
    for (i in 0..p.size - 1) {
        result += p[i] * pow(x, i.toDouble())
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> = TODO() /*{
    var sum = 0.0
    var result = list
    for (i in 0..result.size - 1) {
        list[i] = result[i] + sum
        sum += result[i]
    }
    return result
} */

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var result = mutableListOf<Int>()
    var foo = n
    if (n == 2) result.add(2) && return result
    while (foo > 2) {
        for (i in 2..n) {
            if ((foo % i) == 0) {
                result.add(i)
                foo /= i
            }
        }
    }
    return result.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    return factorize(n).joinToString(separator = "*", prefix = "", postfix = "")
}

/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная
 *
 * Перевести заданное натуральное число n в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная
 *
 * Перевести натуральное число n в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var ones = ""
    if (n % 10 == 0) ones = ""
    if (n % 10 == 1) ones = "I"
    if (n % 10 == 2) ones = "II"
    if (n % 10 == 3) ones = "III"
    if (n % 10 == 4) ones = "IV"
    if (n % 10 == 5) ones = "V"
    if (n % 10 == 6) ones = "VI"
    if (n % 10 == 7) ones = "VII"
    if (n % 10 == 8) ones = "VIII"
    if (n % 10 == 9) ones = "IX"
    var tens = ""
    if (n % 100 < 10) tens = ""
    if ((n % 100 >= 10) && (20 > n % 100)) tens = "X"
    if ((n % 100 >= 20) && (30 > n % 100)) tens = "XX"
    if ((n % 100 >= 30) && (40 > n % 100)) tens = "XXX"
    if ((n % 100 >= 40) && (50 > n % 100)) tens = "XL"
    if ((n % 100 >= 50) && (60 > n % 100)) tens = "L"
    if ((n % 100 >= 60) && (70 > n % 100)) tens = "LX"
    if ((n % 100 >= 70) && (80 > n % 100)) tens = "LXX"
    if ((n % 100 >= 80) && (90 > n % 100)) tens = "LXXX"
    if ((n % 100 >= 90) && (100 > n % 100)) tens = "XC"
    var hundreds = ""
    if (n % 1000 < 100) hundreds = ""
    if ((n % 1000 >= 100) && (200 > n % 1000)) hundreds = "C"
    if ((n % 1000 >= 200) && (300 > n % 1000)) hundreds = "CC"
    if ((n % 1000 >= 300) && (400 > n % 1000)) hundreds = "CCC"
    if ((n % 1000 >= 400) && (500 > n % 1000)) hundreds = "CD"
    if ((n % 1000 >= 500) && (600 > n % 1000)) hundreds = "D"
    if ((n % 1000 >= 600) && (700 > n % 1000)) hundreds = "DC"
    if ((n % 1000 >= 700) && (800 > n % 1000)) hundreds = "DCC"
    if ((n % 1000 >= 800) && (900 > n % 1000)) hundreds = "DCCC"
    if ((n % 1000 >= 900) && (1000 > n % 1000)) hundreds = "CM"
    var thousands = ""
    if (n % 10000 < 1000) thousands = ""
    var count = 0
    if (n >= 1000) {
        count = (n / 1000)
        while (count > 0) {
            thousands += "M"
            count --
        }
    }
    var result = (thousands + hundreds + tens + ones)
    return result
}


/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russianOne(n: Int): String {
    var one = ""
    if (n % 10 == 0) one = ""
    if (n % 10 == 1) one = "один"
    if (n % 10 == 2) one = "два"
    if (n % 10 == 3) one = "три"
    if (n % 10 == 4) one = "четыре"
    if (n % 10 == 5) one = "пять"
    if (n % 10 == 6) one = "шесть"
    if (n % 10 == 7) one = "семь"
    if (n % 10 == 8) one = "восемь"
    if (n % 10 == 9) one = "девять"
    return one
}

fun russianOnesForTeens(n: Int): String {
    var ones = ""
    if (n % 10 == 0) ones = ""
    if (n % 10 == 1) ones = "один"
    if (n % 10 == 2) ones = "две"
    if (n % 10 == 3) ones = "три"
    if (n % 10 == 4) ones = "четыр"
    if (n % 10 == 5) ones = "пят"
    if (n % 10 == 6) ones = "шест"
    if (n % 10 == 7) ones = "сем"
    if (n % 10 == 8) ones = "восем"
    if (n % 10 == 9) ones = "девят"
    return ones
}

fun russianOnesForThsds(n: Int): String {
    var onee = ""
    if (n % 10 == 0) onee = ""
    if (n % 10 == 1) onee = "одна"
    if (n % 10 == 2) onee = "две"
    if (n % 10 == 3) onee = "три"
    if (n % 10 == 4) onee = "четыре"
    if (n % 10 == 5) onee = "пять"
    if (n % 10 == 6) onee = "шесть"
    if (n % 10 == 7) onee = "семь"
    if (n % 10 == 8) onee = "восемь"
    if (n % 10 == 9) onee = "девять"
    return onee
}

fun russianTeens(n: Int): String {
    if (n % 10 == 0) return "десять"
    val result = russianOnesForTeens(n % 10)
    return result + "надцать"
}

fun russianTens(n: Int): String {
    var result = ""
    val foo = n / 10
    if (foo == 0) result = russianOne(n)
    if (foo == 1) result = russianTeens(n)
    if (foo == 2) result = "двадцать"
    if (foo == 3) result = "тридцать"
    if (foo == 4) result = "сорок"
    if (foo == 5) result = "пятьдесят"
    if (foo == 6) result = "шестьдесят"
    if (foo == 7) result = "семьдесят"
    if (foo == 8) result = "восемьдесят"
    if (foo == 9) result = "девяносто"
    return if (foo > 1) result + " " + russianOne(n % 10) else return result
}

fun russianTensForThsds(n: Int): String {
    var result = ""
    val foo = n / 10
    if (foo == 0) result = russianOne(n)
    if (foo == 1) result = russianTeens(n)
    if (foo == 2) result = "двадцать"
    if (foo == 3) result = "тридцать"
    if (foo == 4) result = "сорок"
    if (foo == 5) result = "пятьдесят"
    if (foo == 6) result = "шестьдесят"
    if (foo == 7) result = "семьдесят"
    if (foo == 8) result = "восемьдесят"
    if (foo == 9) result = "девяносто"
    return if (foo > 1) result + " " + russianOnesForThsds(n % 10) else return result
}

fun russianHundreds(n: Int): String {
    var result = ""
    val foo = n / 100
    if (foo == 1) result = "сто"
    if (foo == 2) result = "двести"
    if (foo == 3) result = "триста"
    if (foo == 4) result = "четыреста"
    if (foo == 5) result = "пятьсот"
    if (foo == 6) result = "шестьсот"
    if (foo == 7) result = "семьсот"
    if (foo == 8) result = "восемьсот"
    if (foo == 9) result = "девятьсот"
    return if ((n % 100) == 0) return result else return result + " " + russianTens(n % 100)
}

fun russianHundredsForThsds(n: Int): String {
    var result = ""
    val foo = n / 100
    if (foo == 1) result = "сто"
    if (foo == 2) result = "двести"
    if (foo == 3) result = "триста"
    if (foo == 4) result = "четыреста"
    if (foo == 5) result = "пятьсот"
    if (foo == 6) result = "шестьсот"
    if (foo == 7) result = "семьсот"
    if (foo == 8) result = "восемьсот"
    if (foo == 9) result = "девятьсот"
    return result
}

fun russianThousands(n: Int): String {
    var result = ""
    val foo = (n)
    if (foo < 10) result = russianOnesForThsds(n)
    if ((foo > 10) && (foo < 20)) result = russianTeens(n)
    if (foo >= 100) result = russianHundredsForThsds(n)
    if (foo >= 100 && foo % 100 < 10) result += " " + russianOnesForThsds(n % 10)
    if (foo % 100 >= 10) {
        if (result.isNotEmpty()) result += " " + russianTensForThsds(n % 100) else result += russianTensForThsds(n % 100)
    }
    return if ((foo % 10) == 0 || (foo % 10) in 5..9) result + " тысяч" else if ((foo % 10) == 1) return result + " тысяча" else if ((foo % 100 > 10 ) && (foo % 100 < 20)) return result + " тысяч" else return result + " тысячи"
}

fun russian(n: Int): String {
    if (n >= 1000) return rmFirstAndLastSpaces(russianThousands(n / 1000) + " " + russianHundreds(n % 1000)).replace("  ", " ")
    if (n >= 100) return rmFirstAndLastSpaces(russianHundreds(n)).replace("  ", " ")
    if (n < 100) return rmFirstAndLastSpaces(russianTens(n)).replace("  ", " ")
    if (n < 10) return rmFirstAndLastSpaces(russianOne(n)).replace("  ", " ")
    return "Invalid input"
}

fun rmFirstAndLastSpaces(n: String): String {
    if (n.last() == ' ') return n.substring(0..n.length - 2)
    if (n.first() == ' ') return n.substring(1..n.length - 1)
    return n
}
