@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.pow

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
    var s = 0.0
    for (e in v) {
        s += sqr(e)
    }
    return Math.sqrt(s)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    var sum = 0.0
    for (i in list) {
        sum += i
    }
    if (list.size != 0) return sum / list.size
    else return 0.0
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val sr = mean(list)
    for (i in 0..list.size - 1) {
        list[i] = list[i] - sr
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
    var scalarProduct = 0.0
    for (i in 0..a.size - 1) {
        scalarProduct += a[i] * b[i]
    }
    return scalarProduct
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
    var res = 0.0
    for (i in 0..p.size - 1) {
        res = res + p[i] * Math.pow(x, i.toDouble())
    }
    return res
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
    var sum = 0.0
    for (i in 0..list.size - 1) {
        sum += list[i]
        list[i] = sum
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
    val listOfMultipliers = mutableListOf<Int>()
    var q = n
    var d = 2
    while (q > 1) {
        if (q % d == 0) {
            listOfMultipliers.add(d)
            q /= d
        } else d++
    }
    return listOfMultipliers
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
    val l = mutableListOf<Int>()
    var q = n
    if (n < base) {
        l.add(0, n)
    } else {
        while (q > 0) {
            val a = q % base
            l.add(0, a)
            q /= base
        }
    }
    return l
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
    val a = convert(n, base)
    var result = ""
    if (base < 1) {
        result = n.toString()
    } else {
        for (el in a) {
            if (el > 9) {
                result += ('a' + el - 10)
            } else result += el.toString()
        }
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
fun decimalFromString(str: String, base: Int): Int {
    val l = str.length
    var res = 0
    var con = pow(base, l - 1)
    for (q in str) {
        if (q in '0'..'9') {
            res += (q - '0') * con
        } else if (q in 'a'..'z') {
            res += ((q - 'a') + 10) * con
        }
        con /= base
    }
    return res
}


/**
 * Сложная
 *
 * Перевести натуральное число n в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()/*{
    var romanDigits = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M")
    var arabicDigits = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000)
    var a = n.toString()
    var z = n
    var result = ""
    if (z in arabicDigits) {
        result = romanDigits[arabicDigits.indexOf(z)]
    } else {

    }
    return result
}*/

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val a = n.toString().length
    var s = ""
    var s1 = ""
    var r = ""
    var r1 = ""
    val unit = n % 10
    when (unit) {
        1 -> s = "один"
        2 -> s = "два"
        3 -> s = "три"
        4 -> s = "четыре"
        5 -> s = "пять"
        6 -> s = "шесть"
        7 -> s = "семь"
        8 -> s = "восемь"
        9 -> s = "девять"
        0 -> s = ""
    }
    val hundr = (n / 10) % 10
    when (hundr) {
        1 -> {
            when (unit) {
                2 -> s1 = s.substring(0, s.length - 1) + "енадцать"
                4, 5, 6, 7, 8, 9 -> s1 = s.substring(0, s.length - 1) + "надцать"
                else -> s1 = s + "надцать"
            }
        }
        2 -> s1 = "двадцать " + s
        3 -> s1 = "тридцать " + s
        4 -> s1 = "сорок " + s
        5 -> s1 = "пятьдесят " + s
        6 -> s1 = "шестьдесят " + s
        7 -> s1 = "семьдесят " + s
        8 -> s1 = "восемьдесят " + s
        9 -> s1 = "девяносто " + s
        0 -> s1 = "" + s
    }
    val thous = (n / 100) % 10
    when (thous) {
        1 -> s1 = "сто " + s1
        2 -> s1 = "двести " + s1
        3 -> s1 = "триста " + s1
        4 -> s1 = "четыреста " + s1
        5 -> s1 = "пятьсот " + s1
        6 -> s1 = "шестьсот " + s1
        7 -> s1 = "семьсот " + s1
        8 -> s1 = "восемьсот " + s1
        9 -> s1 = "девятьсот " + s1
    }
    if (a > 3) {
        val unit1 = (n / 1000) % 10
        val hundr1 = (n / 10000) % 10
        if ((unit == 0) && (hundr == 0) && (thous == 0)) {
            s1 = "тысяч" + s1
        } else {
            if (hundr1 != 1) {
                when (unit1) {
                    1 -> s1 = "тысяча " + s1
                    2, 3, 4 -> s1 = "тысячи " + s1
                    else -> s1 = "тысяч " + s1
                }
            } else s1 = "тысяч " + s1
        }
        when (unit1) {
            1 -> r = "одна "
            2 -> r = "две "
            3 -> r = "три "
            4 -> r = "четыре "
            5 -> r = "пять "
            6 -> r = "шесть "
            7 -> r = "семь "
            8 -> r = "восемь "
            9 -> r = "девять "
            0 -> r = ""
        }
        when (hundr1) {
            1 -> {
                when (unit1) {
                    4, 5, 6, 7, 8, 9 -> r1 = r.substring(0, r.length - 2) + "надцать "
                    2, 3 -> r1 = r.substring(0, r.length - 1) + "надцать "
                    else -> r1 = r.substring(0, r.length - 3) + "иннадцать "
                }
            }
            2 -> r1 = "двадцать " + r
            3 -> r1 = "тридцать " + r
            4 -> r1 = "сорок " + r
            5 -> r1 = "пятьдесят " + r
            6 -> r1 = "шестьдесят " + r
            7 -> r1 = "семьдесят " + r
            8 -> r1 = "восемьдесят " + r
            9 -> r1 = "девяносто " + r
            0 -> r1 = "" + r
        }
        val thous1 = n / 100000
        when (thous1) {
            1 -> r1 = "сто " + r1
            2 -> r1 = "двести " + r1
            3 -> r1 = "триста " + r1
            4 -> r1 = "четыреста " + r1
            5 -> r1 = "пятьсот " + r1
            6 -> r1 = "шестьсот " + r1
            7 -> r1 = "семьсот " + r1
            8 -> r1 = "восемьсот " + r1
            9 -> r1 = "девятьсот " + r1
        }

    }
    return r1 + s1
}