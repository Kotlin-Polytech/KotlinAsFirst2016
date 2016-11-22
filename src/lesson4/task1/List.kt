@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant

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
fun abs(v: List<Double>): Double = TODO()

fun intPower(a: Int, b: Int): Int {
    var basement = 1
    if (b > 0) for (i in 1..b) basement *= a
    return basement
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.size != 0) list.sum() / list.size else return 0.0
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0..(list.size - 1)) {
        list[i] -= mean
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
    var c = 0.0
    for (i in 0..a.size - 1) {
        c += a[i] * b[i]
    }
    return c
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
    var k = 1.0
    var result = 0.0
    for (i in 0..p.size - 1) {
        result += p[i] * k
        k *= x
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
fun factorize(n: Int): List<Int> = TODO()

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = TODO()

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
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    var weight = 1
    for (i in digits.size - 1 downTo 0) {
        result += digits[i] * weight
        weight *= base
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
fun decimalFromString(str: String, base: Int): Int = TODO()

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
    val digitsInDigits = mutableListOf<Int>()
    val digitsInWords = mutableListOf<String>()
    for (i in 0..6) digitsInWords += ""
    var digitsOfn = n
    while (digitsOfn > 0) {
        val digit = digitsOfn % 10
        digitsInDigits += digit
        digitsOfn /= 10
    }
    if (digitsInDigits.size > 3) {
        if (digitsInDigits[3] == 1) digitsInWords[3] = "тысяча "
        if ((digitsInDigits[3] > 1) && (digitsInDigits[3] < 5)) digitsInWords[3] = "тысячи "
        if ((digitsInDigits[3] >= 5) || (digitsInDigits[3] == 0)) digitsInWords[3] = "тысяч "
    }

    if (digitsInDigits.size > 2) {//перевод сотен - начало
        if (digitsInDigits[2] == 1) digitsInWords[4] = "сто "
        if (digitsInDigits[2] == 2) digitsInWords[4] = "двести "
        if (digitsInDigits[2] == 3) digitsInWords[4] = "триста "
        if (digitsInDigits[2] == 4) digitsInWords[4] = "четыреста "
        if (digitsInDigits[2] == 5) digitsInWords[4] = "пятьсот "
        if (digitsInDigits[2] == 6) digitsInWords[4] = "шестьсот "
        if (digitsInDigits[2] == 7) digitsInWords[4] = "семьсот "
        if (digitsInDigits[2] == 8) digitsInWords[4] = "восемьсот "
        if (digitsInDigits[2] == 9) digitsInWords[4] = "девятьсот "
        if (digitsInDigits[2] == 0) digitsInWords[4] = ""
        //перевод сотен - конец
    }

    //перевод десятков и единиц - начало
    //перевод для случаев, когда десятков два или больше, или вовсе нет - начало
    //перевод едениц - начало
    if (digitsInDigits[0] == 1) digitsInWords[6] = "один"
    if (digitsInDigits[0] == 2) digitsInWords[6] = "два"
    if (digitsInDigits[0] == 3) digitsInWords[6] = "три"
    if (digitsInDigits[0] == 4) digitsInWords[6] = "четыре"
    if (digitsInDigits[0] == 5) digitsInWords[6] = "пять"
    if (digitsInDigits[0] == 6) digitsInWords[6] = "шесть"
    if (digitsInDigits[0] == 7) digitsInWords[6] = "семь"
    if (digitsInDigits[0] == 8) digitsInWords[6] = "восемь"
    if (digitsInDigits[0] == 9) digitsInWords[6] = "девять"
    if (digitsInDigits[0] == 0) digitsInWords[6] = ""
    //перевод единиц - конец
    if (digitsInDigits.size > 1) {//перевод десятков - начало
        if (digitsInDigits[1] == 2) digitsInWords[5] = "двадцать "
        if (digitsInDigits[1] == 3) digitsInWords[5] = "тридцать "
        if (digitsInDigits[1] == 4) digitsInWords[5] = "сорок "
        if (digitsInDigits[1] == 5) digitsInWords[5] = "пятьдесят "
        if (digitsInDigits[1] == 6) digitsInWords[5] = "шестьдесят "
        if (digitsInDigits[1] == 7) digitsInWords[5] = "семьдесят "
        if (digitsInDigits[1] == 8) digitsInWords[5] = "восемьдесят "
        if (digitsInDigits[1] == 9) digitsInWords[5] = "девяносто "
        if (digitsInDigits[1] == 0) digitsInWords[5] = ""
        //перевод десятков - конец
    }
    //конец
    if ((digitsInDigits.size > 1) && (digitsInDigits[1] == 1)) { //перевод для случаев, когда число десятков равно 1 - начало
        if (digitsInDigits[0] == 0) {
            digitsInWords[5] = "десять"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 1) {
            digitsInWords[5] = "одиннадцать"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 2) {
            digitsInWords[5] = "двенадцать"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 3) {
            digitsInWords[5] = "тринадцать"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 4) {
            digitsInWords[5] = "четырнадцать"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 5) {
            digitsInWords[5] = "пятнадцать"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 6) {
            digitsInWords[5] = "шестнадцать"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 7) {
            digitsInWords[5] = "семнадцать"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 8) {
            digitsInWords[5] = "восемнадцать"
            digitsInWords[6] = ""
        }
        if (digitsInDigits[0] == 9) {
            digitsInWords[5] = "девятнадцать"
            digitsInWords[6] = ""
        }
    } //конец
    //перевод десятков и единиц - конец

    //место для сотен, десятков и единиц тысяч
    if (digitsInDigits.size > 5) {//перевод сотен тысяч - начало
        if (digitsInDigits[5] == 1) digitsInWords[0] = "сто "
        if (digitsInDigits[5] == 2) digitsInWords[0] = "двести "
        if (digitsInDigits[5] == 3) digitsInWords[0] = "триста "
        if (digitsInDigits[5] == 4) digitsInWords[0] = "четыреста "
        if (digitsInDigits[5] == 5) digitsInWords[0] = "пятьсот "
        if (digitsInDigits[5] == 6) digitsInWords[0] = "шестьсот "
        if (digitsInDigits[5] == 7) digitsInWords[0] = "семьсот "
        if (digitsInDigits[5] == 8) digitsInWords[0] = "восемьсот "
        if (digitsInDigits[5] == 9) digitsInWords[0] = "девятьсот "
        if (digitsInDigits[5] == 0) digitsInWords[0] = ""
        //перевод сотен тысяч - конец
    }

    //перевод десятков и единиц тысяч - начало
    if (digitsInDigits.size > 3) {
        //перевод для случаев, когда десятков два или больше, или вовсе нет - начало
        //перевод едениц тысяч - начало
        if (digitsInDigits[3] == 1) digitsInWords[2] = "одна "
        if (digitsInDigits[3] == 2) digitsInWords[2] = "две "
        if (digitsInDigits[3] == 3) digitsInWords[2] = "три "
        if (digitsInDigits[3] == 4) digitsInWords[2] = "четыре "
        if (digitsInDigits[3] == 5) digitsInWords[2] = "пять "
        if (digitsInDigits[3] == 6) digitsInWords[2] = "шесть "
        if (digitsInDigits[3] == 7) digitsInWords[2] = "семь "
        if (digitsInDigits[3] == 8) digitsInWords[2] = "восемь "
        if (digitsInDigits[3] == 9) digitsInWords[2] = "девять "
        if (digitsInDigits[3] == 0) digitsInWords[2] = ""
        //перевод единиц тысяч - конец
        if (digitsInDigits.size > 4) {//перевод десятков тысяч - начало
            if (digitsInDigits[4] == 2) digitsInWords[1] = "двадцать "
            if (digitsInDigits[4] == 3) digitsInWords[1] = "тридцать "
            if (digitsInDigits[4] == 4) digitsInWords[1] = "сорок "
            if (digitsInDigits[4] == 5) digitsInWords[1] = "пятьдесят "
            if (digitsInDigits[4] == 6) digitsInWords[1] = "шестьдесят "
            if (digitsInDigits[4] == 7) digitsInWords[1] = "семьдесят "
            if (digitsInDigits[4] == 8) digitsInWords[1] = "восемьдесят "
            if (digitsInDigits[4] == 9) digitsInWords[1] = "девяносто "
            if (digitsInDigits[4] == 0) digitsInWords[1] = ""
            //перевод десятков тысяч - конец
        }
        //конец
        if ((digitsInDigits.size > 4) && (digitsInDigits[4] == 1)) { //перевод для случаев, когда число десятков тысяч равно 1 - начало
            if (digitsInDigits[3] == 0) {
                digitsInWords[1] = "десять "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 1) {
                digitsInWords[1] = "одиннадцать "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 2) {
                digitsInWords[1] = "двенадцать "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 3) {
                digitsInWords[1] = "тринадцать "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 4) {
                digitsInWords[1] = "четырнадцать "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 5) {
                digitsInWords[1] = "пятнадцать "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 6) {
                digitsInWords[1] = "шестнадцать "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 7) {
                digitsInWords[1] = "семнадцать "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 8) {
                digitsInWords[1] = "восемнадцать "
                digitsInWords[2] = ""
            }
            if (digitsInDigits[3] == 9) {
                digitsInWords[1] = "девятнадцать "
                digitsInWords[2] = ""
            }
        }
    }
    return digitsInWords.joinToString("", "", "", -1)
}