@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AdditionalSupertypes

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
        result += v[i] * v[i]
    }
    return Math.sqrt(result)

}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var result = 0.0
    for (i in list) {
        result += i
    }
    if (list.size == 0) return 0.0
    else return result / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val listMean = mean(list)
        for (i in 0..list.size - 1) {
            list[i] -= listMean
        }
        return list
    } else return list
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
    var degree = 0.0
    for (i in p) {
        result += i * Math.pow(x, degree)
        degree++
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
    val result = mutableListOf<Int>()
    var number = n
    for (i in 2..number) {
        while (number % i == 0) {
            number /= i
            result.add(result.size, i)
        }
    }
    if (number != 1) result.add(result.size, number)
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    var number = n
    val result = mutableListOf<Int>()
    for (i in 2..n) {
        while (number % i == 0) {
            result.add(i)
            number /= i
        }
    }
    //if (number != 1) result + number
    return result.joinToString(separator = "*")
}


/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    if (n < base) result.add(n)
    else {

        var number = n
        while (number / base >= base) {
            result.add(0, number % base)
            number /= base
        }
        result.add(0, number % base)
        result.add(0, number / base)

    }
    return result
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
    val numbers = convert(n, base)
    var result = ""
    for (i in numbers) {
        result += if (i > 9) 'a' + (i - 10)
        else i
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
    for (i in 0..digits.size - 1) {
        result += (digits[digits.size - 1 - i] * (Math.pow(base.toDouble(), i.toDouble())).toInt())
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
fun sqr(n: Int, m: Int): Int {
    var result = 1
    for (i in 1..m) result *= n
    return result
}

fun decimalFromString(str: String, base: Int): Int {
    var result = 0

    for (i in 0..str.length - 1) {
        val number = str[str.length - 1 - i]
        result += when (number) {
            'a' -> (sqr(base, i) * 10)
            'b' -> (sqr(base, i) * 11)
            'c' -> (sqr(base, i) * 12)
            'd' -> (sqr(base, i) * 13)
            'e' -> (sqr(base, i) * 14)
            'f' -> (sqr(base, i) * 15)
            'g' -> (sqr(base, i) * 16)
            'h' -> (sqr(base, i) * 17)
            'i' -> (sqr(base, i) * 18)
            'j' -> (sqr(base, i) * 19)
            'k' -> (sqr(base, i) * 20)
            'l' -> (sqr(base, i) * 21)
            'm' -> (sqr(base, i) * 22)
            'n' -> (sqr(base, i) * 23)
            'o' -> (sqr(base, i) * 24)
            'p' -> (sqr(base, i) * 25)
            'q' -> (sqr(base, i) * 26)
            'r' -> (sqr(base, i) * 27)
            's' -> (sqr(base, i) * 28)
            't' -> (sqr(base, i) * 29)
            'u' -> (sqr(base, i) * 30)
            'v' -> (sqr(base, i) * 31)
            'w' -> (sqr(base, i) * 32)
            'x' -> (sqr(base, i) * 33)
            'y' -> (sqr(base, i) * 34)
            'z' -> (sqr(base, i) * 35)
            else -> (sqr(base, i) * (number - 48).toInt())
        }
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
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var a = when (n / 100000) {
        1 -> "сто "
        2 -> "двести "
        3 -> "триста "
        4 -> "четыреста "
        5 -> "пятьсот "
        6 -> "шестьсот "
        7 -> "семьсот "
        8 -> "восемьсот "
        9 -> "девятьсот "
        else -> ""
    }
    a += when (n / 10000 % 10) {
        2 -> "двадцать "
        3 -> "тридцать "
        4 -> "сорок "
        5 -> "пятьдесят "
        6 -> "шестьдесят "
        7 -> "семьдесят "
        8 -> "восемьдесят "
        9 -> "девяносто "
        else -> ""
    }
    a += when (n / 1000 % 100) {
        10 -> "десять "
        11 -> "одиннадцать тысяч"
        12 -> "двенадцать тысяч"
        13 -> "тринадцать тысяч"
        14 -> "четырнадцать тысяч"
        15 -> "пятнадцать тысяч"
        16 -> "шестнадцать тысяч"
        17 -> "семнадцать тысяч"
        18 -> "восемнадцать тысяч"
        19 -> "девятнадцать тысяч"
        else -> ""
    }
    if (n / 10000 % 10 != 1)
        a += when (n / 1000 % 10) {
            1 -> "одна тысяча"
            2 -> "две тысячи"
            3 -> "три тысячи"
            4 -> "четыре тысячи"
            5 -> "пять тысяч"
            6 -> "шесть тысяч" //20954
            7 -> "семь тысяч"
            8 -> "восемь тысяч"
            9 -> "девять тысяч"
            else -> ""
        }
    if ((n / 10000 != 0) && (n / 1000 % 10 == 0)) a += "тысяч"

    if ((n % 1000 / 100 != 0) && (n / 1000 != 0)) a += " "
    a += when (n % 1000 / 100) {
        1 -> "сто"
        2 -> "двести"
        3 -> "триста"
        4 -> "четыреста"
        5 -> "пятьсот"
        6 -> "шестьсот"
        7 -> "семьсот"
        8 -> "восемьсот"
        9 -> "девятьсот"
        else -> ""
    }
    if ((n % 100 / 10 != 0) && (n / 100 != 0)) a += " "
    a += when (n % 100) {
        10 -> "десять"
        11 -> "одиннадцать"
        12 -> "двенадцать"
        13 -> "тринадцать"
        14 -> "четырнадцать"
        15 -> "пятнадцать"
        16 -> "шестнадцать"
        17 -> "семнадцать"
        18 -> "восемнадцать"
        19 -> "девятнадцать"
        else -> ""
    }

    a += when (n % 100 / 10) {
        2 -> "двадцать"
        3 -> "тридцать"
        4 -> "сорок"
        5 -> "пятьдесят"
        6 -> "шестьдесят"
        7 -> "семьдесят"
        8 -> "восемьдесят"
        9 -> "девяносто"
        else -> ""
    }
    if ((n % 10 != 0) && (n / 10 != 0) && (n % 100 / 10 != 1)) a += " "
    if (n % 100 / 10 != 1)
        a += when (n % 10) {
            1 -> "один"
            2 -> "два"
            3 -> "три"
            4 -> "четыре"
            5 -> "пять"
            6 -> "шесть"
            7 -> "семь"
            8 -> "восемь"
            9 -> "девять"
            else -> ""
        }
    return a
}