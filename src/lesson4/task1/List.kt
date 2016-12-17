@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import lesson3.task1.pow
import lesson3.task1.minDivisor

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
    for (element in v) {
        result += element * element
    }
    return Math.sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0
    else return list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val m = mean(list)
    for (i in 0..list.size - 1) {
        list[i] -= m
    }
    return list
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
    for (i in 0..a.size - 1) {
        result += a[i] * b[i]
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
    for ((index, element) in p.withIndex()) {
        result += element * Math.pow(x, index.toDouble())
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
    var result = listOf<Int>()
    var number = n
    while (number > 1) {
        val mind = minDivisor(number)
        result += mind
        number /= mind
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int) = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n == 0) return listOf(0)
    else {
        var number = n
        var result = listOf<Int>()
        while (number > 0) {
            result += number % base
            number /= base
        }
        return result.reversed()
    }
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
    val list = convert(n, base)
    var result = listOf<String>()
    for (element in list) {
        if (element <= 9) result += element.toString()
        else result += ('a' - 10 + element).toString()
    }
    return result.joinToString(separator = "")
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
    for ((index, element) in digits.withIndex()) {
        result += element * pow(base, digits.size - index - 1)
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
fun decimalFromString(str: String, base: Int): Int {
    var list = listOf<Int>()
    for (char in str) {
        if (char in '0'..'9') list += char - '0'
        else list += char + 10 - 'a'
    }
    return decimal(list, base)
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
    val map = mapOf(1 to "I", 4 to "IV", 5 to "V", 9 to "IX", 10 to "X", 40 to "XL", 50 to "L",
            90 to "XC", 100 to "C", 400 to "CD", 500 to "D", 900 to "CM", 1000 to "M")
    var result = listOf<String>()
    var number = n
    while (number > 0) {
        val i = map.entries.findLast { it.key <= number }
        if (i != null) {
            number -= i.key
            result += i.value
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
fun numberBelowThousandToRussian(list: List<Int>, accompaniedByFeminineNoun: Boolean): List<String> {
    val units = listOf("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val unitsOfFeminine = listOf("", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val from11to19 = listOf("", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
            "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
    val decades = listOf("", "десять", "двадцать", "тридцать", "сорок",
            "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
    val hundreds = listOf("", "сто", "двести", "триста", "четыреста",
            "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    val numberOfHundreds = list.getOrNull(list.size - 3)
    val numberOfDecades = list.getOrNull(list.size - 2)
    val numberOfUnits = list.lastOrNull()
    var result = listOf(hundreds[numberOfHundreds ?: 0])
    if (numberOfDecades == 1 && numberOfUnits != 0) result += from11to19[numberOfUnits ?: 0]
    else {
        result += decades[numberOfDecades ?: 0]
        result += if (accompaniedByFeminineNoun)
            unitsOfFeminine[numberOfUnits ?: 0] else units[numberOfUnits ?: 0]
    }
    return result
}

fun russian(n: Int): String {
    val thousands = listOf("тысяча", "тысячи", "тысяч")
    val listOfNumbers = convert(n, 10)
    val listBelowThousand = listOfNumbers.takeLast(3)
    var result = numberBelowThousandToRussian(listBelowThousand, false)
    val listOfThousands = listOfNumbers.dropLast(3)
    val numberOfDecadesOfThousands = listOfThousands.getOrNull(listOfThousands.size - 2)
    val numberOfThousands = listOfThousands.lastOrNull()
    when {
        numberOfDecadesOfThousands == 1 -> result = listOf(thousands[2]) + result
        numberOfThousands == 1 -> result = listOf(thousands[0]) + result
        numberOfThousands in 2..4 -> result = listOf(thousands[1]) + result
        listOfThousands.isNotEmpty() -> result = listOf(thousands[2]) + result
    }
    result = numberBelowThousandToRussian(listOfThousands, true) + result
    return result.filter { it != "" }.joinToString(separator = " ")
}