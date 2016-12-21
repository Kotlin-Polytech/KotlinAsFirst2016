@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.minDivisor

val reference = "0123456789abcdefghijklmnopqrstuvwxyz"


fun pow (x: Int, i: Int): Int {
    var number = 1
    var ink = x
    var power = i
    while (power != 0) {
        if (power % 2 == 1)
            number *= ink
        ink *= ink
        power = power.shr(1)

    }
    return number
}

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
    var sum = 0.0
    for (element in v) {
        val sqrEl = element * element
        sum += sqrEl
    }
    return Math.sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var averageSum = 0.0
    var sum = 0.0
    for (element in list) {
        sum += element
        averageSum = sum / list.size
    }
    return averageSum
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0..list.size - 1) {
        val element = list[i]
        list[i] = element - mean
    }
    return  list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var production = 0.0
    for (element in 0..a.size - 1)  {
        production += a[element] * b[element]
    }
    return production
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
    var polynomial = 0.0
    for (i in 0..p.size - 1) {
        polynomial += p[i] * Math.pow(x, i.toDouble())
    }
    return polynomial
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
    for (i in 1..list.size - 1) {
        list[i] += list[i - 1]
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var number = n
    while (number > 1) {
        val minDivisor = minDivisor(number)
        number /= minDivisor
        list.add(minDivisor)
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val list = mutableListOf<Int>()
    if (number == 0) return listOf(0)
    while (number != 0) {
        val result = number % base
        number /= base
        list.add(result)
    }
    return list.reversed()
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
    var number = n
    val str :StringBuilder = StringBuilder()
    if (number == 0) return "0"
    while (number != 0) {
        str.append(reference[number % base])
        number /= base
    }
    return str.toString().reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var number = 0.0
    for (i in 0..digits.reversed().size - 1) {
        number += digits.reversed()[i] * pow(base, i)
    }
    return number.toInt()
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
fun decimalFromString(str: String, base: Int): Int =
    str.reversed().mapIndexed { i, c -> reference.indexOf(c) * pow(base,i) }.sum()


/**
 * Сложная
 *
 * Перевести натуральное число n в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String  {
    val map = sortedMapOf(1 to  "I", 4 to "IV", 5 to "V", 9 to "IX", 10 to "X", 40 to "XL", 50 to "L", 90 to "XC", 100 to "C",
    400 to "CD", 500 to "D", 900 to "CM", 1000 to "M")
    val string: StringBuilder = StringBuilder()
    var number = n
    while (number > 0) {
        val key = map.keys.last()
        if (number >= key) {
            string.append(map[key]) //добавляет в строку значение по ключу
            number -= key
        } else {
            map.remove(key) //убирает последний ключ в массиве
        }
    }
    return string.toString()
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun units (n: Int) : String {
    if (n in 11..19) return ""
    return when (n % 10) {
        9 -> "девять"
        8 -> "восемь"
        7 -> "семь"
        6 -> "шесть"
        5 -> "пять"
        4 -> "четыре"
        3 -> "три"
        2 -> "два"
        1 -> "один"
        else -> ""
    }
}
fun tens (n: Int) : String =
    when (n) {
        in 90..99 ->  "девяносто"
        in 80..89 -> "восемьдесят"
        in 70..79 -> "семьдесят"
        in 60..69 -> "шестьдесят"
        in 50..59 -> "пятьдесят"
        in 40..49 -> "сорок"
        in 30..39 -> "тридцать"
        in 20..29 -> "двадцать"
        19 -> "девятнадцать"
        18 -> "восемнадцать"
        17 -> "семнадцать"
        16 -> "шестнадцать"
        15 -> "пятнадцать"
        14 -> "четырнадцать"
        13 -> "тринадцать"
        12 -> "двенадцать"
        11 -> "одиннадцать"
        10 -> "десять"
        else -> ""
    }

fun hundreds (n: Int) : String =
    when (n) {
        in 900..999 -> "девятьсот"
        in 800..899 -> "восемьсот"
        in 700..799 -> "семьсот"
        in 600..699 -> "шестьсот"
        in 500..599 -> "пятьсот"
        in 400..499 -> "четыреста"
        in 300..399 -> "триста"
        in 200..299 -> "двести"
        in 100..199 -> "сто"
        else -> ""
    }

fun russian(n: Int): String {
    val thousands = n / 1000
    val hundreds = n % 1000
    val list = mutableListOf<String>()
    list += hundreds(thousands)
    list += tens(thousands % 100)
    if (n > 1000) {
        when {
            (thousands % 10 == 1) -> list.add("одна")
            (thousands % 10 == 2) -> list.add("две")
            else -> list += units(thousands % 100)
        }
        list += when {
            (thousands % 100 in 5..20 || thousands % 10 == 0 || thousands % 10 in 5..9) -> "тысяч"
            (thousands % 10 in 2..4) -> "тысячи"
            (thousands % 10 == 1) -> "тысяча"
            else -> ""
        }
    }
    list += hundreds(hundreds)
    list += tens(hundreds % 100)
    list += units(hundreds % 100)
    return list.filter { it != "" }.joinToString(separator = " ")
}