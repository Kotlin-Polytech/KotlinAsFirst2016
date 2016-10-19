@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant
import java.util.function.IntToDoubleFunction

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
    val result = mutableListOf<Double>()
    for (i in 0..v.size - 1) {
        val element = v[i] * v[i]
        result.add(element)
    }
    val sum = result.sum()
    return Math.sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    val count = list.size
    val sum = list.sum()
    if (sum != 0.0) return sum / count else return 0.0
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val count = list.size
    val sum = list.sum()
    val arithmeticaverage = sum / count
    if (sum != 0.0)
        for (i in 0..count - 1) {
            list[i] = list[i] - arithmeticaverage
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
    val result = mutableListOf<Double>()
    if ((a.sum() != 0.0) && (b.sum() != 0.0)) {
        for (i in 0..a.size - 1) {
            val element = a[i] * b[i]
            result.add(element)
        }
        return result.sum()
    } else return 0.0

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
    val result = mutableListOf<Double>()
    if (p.isNotEmpty()) {
        for (i in 0..p.size - 1) {
            val i1 = (i.toDouble())
            val element = Math.pow(x, i1) * p[i]
            result.add(element)
        }
        return result.sum()
    } else return 0.0
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

    var length = 0.0
    for (i: Int in 0..list.size - 1) {
        length += list[i]
        list[i] = length
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
    var nn = n
    for (i in 2..n) {
        while (nn % i == 0) {
            nn = nn / i
            result.add(i)
        }
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString("*")

/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val result = mutableListOf<Int>()
    var i = 0
    var nn = n
    if (nn >= base) {
        while (nn >= base) {
            i = nn % base
            nn = nn / base
            result.add(i)
        }
        result.add(nn)
    } else
        if (nn >= 0) {
            result.add(nn)
        }
    return result.reversed()
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
    var result1: String = ""
    var result = listOf<Int>()
    result = convert(n, base)
    for (i in 0..result.size - 1) {
        if (result[i] > 9) {
            result1 += (result[i] + 87).toChar()
        } else
            result1 += result [i]

    }
    return result1
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
    var numbers = digits.reversed()
    for (i in 0..digits.size - 1) {
        val ss = (numbers[i] * Math.pow(base.toDouble(), i.toDouble())).toInt()
        result = result + ss
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
    var list: List<Int>
    list = listOf()
    var string1 = str
    for (i in 0..string1.length - 1)
        if (string1[i] in '0'..'9') list += ((string1[i]).toInt() - 48)
        else list += ((string1[i]).toInt() - 87)
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
    var result: String = ""
    var nn = n
    var thousands = 0
    var hundreds = 0
    var dozens = 0
    var units = 0

    while (nn >= 1) {
        if (nn > 1000) {
            thousands = nn / 1000
            nn = nn % 1000


        }
        if ((nn < 1000) && (nn >= 100)) {
            while (nn >= 100) {
                hundreds = nn / 100
                nn = nn % 100
            }
        }
        if ((nn < 100) && (nn >= 10)) {
            while (nn >= 10) {
                dozens = nn / 10
                nn = nn % 10

            }
        }
        if (nn < 10) {
            units = nn
            nn = 0

        }


    }
    while (thousands > 0) {
        result += ("M")
        thousands --
    }
    when {
        (hundreds == 1) -> result += ("C")
        (hundreds == 2) -> result += ("CC")
        (hundreds == 3) -> result += ("CCC")
        (hundreds == 4) -> result += ("CD")
        (hundreds == 5) -> result += ("D")
        (hundreds == 6) -> result += ("DC")
        (hundreds == 7) -> result += ("DCC")
        (hundreds == 8) -> result += ("DCCC")
        (hundreds == 9) -> result += ("CM")
    }
    when {
        (dozens == 1) -> result += ("X")
        (dozens == 2) -> result += ("XX")
        (dozens == 3) -> result += ("XXX")
        (dozens == 4) -> result += ("XL")
        (dozens == 5) -> result += ("L")
        (dozens == 6) -> result += ("LX")
        (dozens == 7) -> result += ("LXX")
        (dozens == 8) -> result += ("LXXX")
        (dozens == 9) -> result += ("XC")
    }
    when {
        (units == 1) -> result += ("I")
        (units == 2) -> result += ("II")
        (units == 3) -> result += ("III")
        (units == 4) -> result += ("IV")
        (units == 5) -> result += ("V")
        (units == 6) -> result += ("VI")
        (units == 7) -> result += ("VII")
        (units == 8) -> result += ("VIII")
        (units == 9) -> result += ("IX")
    }
    return result
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var result: String = ""
    var thousands100 = 0
    var thousands10 = 0
    var thousands = 0
    var hundreds = 0
    var dozens = 0
    var units = 0
    var nn = n
    while (nn > -1) {
        if (nn > 100000) {
            thousands100 = nn / 100000
            nn = nn % 100000
        }
        if ((nn < 100000) && (nn >= 10000)) {
            thousands10 = nn / 10000
            nn = nn % 10000
        }
        if ((nn < 10000) && (nn >= 1000)) {
            thousands = nn / 1000
            nn = nn % 1000
        }
        if ((nn < 1000) && (nn >= 100)) {
            hundreds = nn / 100
            nn = nn % 100
        }
        if ((nn < 100) && (nn >= 10)) {
            dozens = nn / 10
            nn = nn % 10

        }
        if (nn < 10)
            units = nn
            nn = -1


    }
    when {
        (thousands100 == 1) -> result += "сто"
        (thousands100 == 2) -> result += "двести"
        (thousands100 == 3) -> result += "триста"
        (thousands100 == 4) -> result += "четыреста"
        (thousands100 == 5) -> result += "пятьсот"
        (thousands100 == 6) -> result += "шестьсот"
        (thousands100 == 7) -> result += "семьсот"
        (thousands100 == 8) -> result += "восемьсот"
        (thousands100 == 9) -> result += "девятьсот"
    }
    if ((thousands10 != 0) && (thousands100 != 0)) result += " "
    when {
        ((thousands10 == 1) && (thousands == 0)) -> result += "десять"
        ((thousands10 == 1) && (thousands == 1)) -> result += "одиннадцать"
        ((thousands10 == 1) && (thousands == 2)) -> result += "двенадцать"
        ((thousands10 == 1) && (thousands == 3)) -> result += "тринадцать"
        ((thousands10 == 1) && (thousands == 4)) -> result += "четырнадцать"
        ((thousands10 == 1) && (thousands == 5)) -> result += "пятнадцать"
        ((thousands10 == 1) && (thousands == 6)) -> result += "шестнадцать"
        ((thousands10 == 1) && (thousands == 7)) -> result += "семнадцать"
        ((thousands10 == 1) && (thousands == 8)) -> result += "восемнадцать"
        ((thousands10 == 1) && (thousands == 9)) -> result += "девятнадцать"
        (thousands10 == 2) -> result += "двадцать"
        (thousands10 == 3) -> result += "тридцать"
        (thousands10 == 4) -> result += "сорок"
        (thousands10 == 5) -> result += "пятьдесят"
        (thousands10 == 6) -> result += "шестьдесят"
        (thousands10 == 7) -> result += "семьдесят"
        (thousands10 == 8) -> result += "восемьдесят"
        (thousands10 == 9) -> result += "девяносто"
    }
    if ((thousands != 0) && (thousands10 != 1) && ((thousands100 != 0) || (thousands10 != 0))) result += " "
    when {
        ((thousands10 != 1) && (thousands == 1)) -> result += "одна"
        ((thousands10 != 1) && (thousands == 2)) -> result += "две"
        ((thousands10 != 1) && (thousands == 3)) -> result += "три"
        ((thousands10 != 1) && (thousands == 4)) -> result += "четыре"
        ((thousands10 != 1) && (thousands == 5)) -> result += "пять"
        ((thousands10 != 1) && (thousands == 6)) -> result += "шесть"
        ((thousands10 != 1) && (thousands == 7)) -> result += "семь"
        ((thousands10 != 1) && (thousands == 8)) -> result += "восемь"
        ((thousands10 != 1) && (thousands == 9)) -> result += "девять"
    }
    if ((thousands100 != 0) || (thousands10 != 0) || (thousands != 0)) {
        if (thousands == 1) {
            result += " тысяча"
        } else
            if (((thousands == 2) || (thousands == 3) || (thousands == 4)) && (thousands10 != 1)) {
                result += " тысячи"
            } else
                result += " тысяч"
    }
    if ((hundreds != 0) && ((thousands != 0) || (thousands100 != 0) || (thousands10 != 0))) result += " "
    when {
        (hundreds == 1) -> result += "сто"
        (hundreds == 2) -> result += "двести"
        (hundreds == 3) -> result += "триста"
        (hundreds == 4) -> result += "четыреста"
        (hundreds == 5) -> result += "пятьсот"
        (hundreds == 6) -> result += "шестьсот"
        (hundreds == 7) -> result += "семьсот"
        (hundreds == 8) -> result += "восемьсот"
        (hundreds == 9) -> result += "девятьсот"
    }
    if ((dozens != 0) && ((hundreds != 0) || (thousands != 0) || (thousands100 != 0) || (thousands10 != 0))) result += " "
    when {
        ((dozens == 1) && (units == 0)) -> result += "десять"
        ((dozens == 1) && (units == 1)) -> result += "одиннадцать"
        ((dozens == 1) && (units == 2)) -> result += "двенадцать"
        ((dozens == 1) && (units == 3)) -> result += "тринадцать"
        ((dozens == 1) && (units == 4)) -> result += "четырнадцать"
        ((dozens == 1) && (units == 5)) -> result += "пятнадцать"
        ((dozens == 1) && (units == 6)) -> result += "шестнадцать"
        ((dozens == 1) && (units == 7)) -> result += "семнадцать"
        ((dozens == 1) && (units == 8)) -> result += "восемнадцать"
        ((dozens == 1) && (units == 9)) -> result += "девятнадцать"
        (dozens == 2) -> result += "двадцать"
        (dozens == 3) -> result += "тридцать"
        (dozens == 4) -> result += "сорок"
        (dozens == 5) -> result += "пятьдесят"
        (dozens == 6) -> result += "шестьдесят"
        (dozens == 7) -> result += "семьдесят"
        (dozens == 8) -> result += "восемьдесят"
        (dozens == 9) -> result += "девяносто"
    }
    if ((units != 0) && (dozens != 1) && ((dozens != 0) || (hundreds != 0) || (thousands != 0) || (thousands100 != 0) || (thousands10 != 0))) result += " "
    when {
        ((dozens != 1) && (units == 1)) -> result += "один"
        ((dozens != 1) && (units == 2)) -> result += "два"
        ((dozens != 1) && (units == 3)) -> result += "три"
        ((dozens != 1) && (units == 4)) -> result += "четыре"
        ((dozens != 1) && (units == 5)) -> result += "пять"
        ((dozens != 1) && (units == 6)) -> result += "шесть"
        ((dozens != 1) && (units == 7)) -> result += "семь"
        ((dozens != 1) && (units == 8)) -> result += "восемь"
        ((dozens != 1) && (units == 9)) -> result += "девять"
    }

    return result
}






