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
 *
 */
val UNITS1_LIST = listOf<String>("", "I", "II", "III", "VI", "V", "IV", "IIV", "IIIV", "XI")
val DOZENS1_LIST = listOf<String>("", "X", "XX", "XXX", "LX", "L", "XL", "XXL", "XXXL", "CX")
val HUNDREDS1_LIST = listOf<String>("", "C", "CC", "CCC", "DC", "D", "CD", "CCD", "CCCD", "MC")
fun roman(n: Int): String {
    var result: String = ""
    var result2: String = ""
    var n2 = n
    for (i in 1..n / 1000) {
        result += "M"
        n2 -= 1000  //убирает из числа все тысячи, вместо них выводит M//
    }
    val n3 = n2.toString().reversed() //в n3 лежит изначально введенное число без тысяч, переведенное в строку и перевернутое//
    if (n3.length >= 1) result2 += UNITS1_LIST[n3[0].toString().toInt()] //если число без тысяч != 0, то берем последнюю цифру, записываем римскими/
    if (n3.length >= 2) result2 += DOZENS1_LIST[n3[1].toString().toInt()]//если в числе без тысяч есть десятки, то зайдет сюда и запишет римскими//
    if (n3.length >= 3) result2 += HUNDREDS1_LIST[n3[2].toString().toInt()]//если есть и сотни, то тоже запишет римскими//
    return result + result2.reversed()//так как чтобы точно быть уверенными, что десятки, единицы или сотни есть, мы переворачивали число, то при выводе необходимо перевернуть его обратно//
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
val UNITS_LIST = listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
val THOUSANDS_LIST = listOf("одна", "две")
val DOSENS1_LIST = listOf("десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать")
val DOSENS_LIST = listOf("двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
val HUNDREDS_LIST = listOf("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
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
        (thousands100 == 1) -> result += HUNDREDS_LIST[0]
        (thousands100 == 2) -> result += HUNDREDS_LIST[1]
        (thousands100 == 3) -> result += HUNDREDS_LIST[2]
        (thousands100 == 4) -> result += HUNDREDS_LIST[3]
        (thousands100 == 5) -> result += HUNDREDS_LIST[4]
        (thousands100 == 6) -> result += HUNDREDS_LIST[5]
        (thousands100 == 7) -> result += HUNDREDS_LIST[6]
        (thousands100 == 8) -> result += HUNDREDS_LIST[7]
        (thousands100 == 9) -> result += HUNDREDS_LIST[8]
    }
    if ((thousands10 != 0) && (thousands100 != 0)) result += " "
    when {
        ((thousands10 == 1) && (thousands == 0)) -> result += DOSENS1_LIST[0]
        ((thousands10 == 1) && (thousands == 1)) -> result += DOSENS1_LIST[1]
        ((thousands10 == 1) && (thousands == 2)) -> result += DOSENS1_LIST[2]
        ((thousands10 == 1) && (thousands == 3)) -> result += DOSENS1_LIST[3]
        ((thousands10 == 1) && (thousands == 4)) -> result += DOSENS1_LIST[4]
        ((thousands10 == 1) && (thousands == 5)) -> result += DOSENS1_LIST[5]
        ((thousands10 == 1) && (thousands == 6)) -> result += DOSENS1_LIST[6]
        ((thousands10 == 1) && (thousands == 7)) -> result += DOSENS1_LIST[7]
        ((thousands10 == 1) && (thousands == 8)) -> result += DOSENS1_LIST[8]
        ((thousands10 == 1) && (thousands == 9)) -> result += DOSENS1_LIST[9]
        (thousands10 == 2) -> result += DOSENS_LIST[0]
        (thousands10 == 3) -> result += DOSENS_LIST[1]
        (thousands10 == 4) -> result += DOSENS_LIST[2]
        (thousands10 == 5) -> result += DOSENS_LIST[3]
        (thousands10 == 6) -> result += DOSENS_LIST[4]
        (thousands10 == 7) -> result += DOSENS_LIST[5]
        (thousands10 == 8) -> result += DOSENS_LIST[6]
        (thousands10 == 9) -> result += DOSENS_LIST[7]
    }
    if ((thousands != 0) && (thousands10 != 1) && ((thousands100 != 0) || (thousands10 != 0))) result += " "
    when {
        ((thousands10 != 1) && (thousands == 1)) -> result += THOUSANDS_LIST[0]
        ((thousands10 != 1) && (thousands == 2)) -> result += THOUSANDS_LIST[1]
        ((thousands10 != 1) && (thousands == 3)) -> result += UNITS_LIST[2]
        ((thousands10 != 1) && (thousands == 4)) -> result += UNITS_LIST[3]
        ((thousands10 != 1) && (thousands == 5)) -> result += UNITS_LIST[4]
        ((thousands10 != 1) && (thousands == 6)) -> result += UNITS_LIST[5]
        ((thousands10 != 1) && (thousands == 7)) -> result += UNITS_LIST[6]
        ((thousands10 != 1) && (thousands == 8)) -> result += UNITS_LIST[7]
        ((thousands10 != 1) && (thousands == 9)) -> result += UNITS_LIST[8]
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
        (hundreds == 1) -> result += HUNDREDS_LIST[0]
        (hundreds == 2) -> result += HUNDREDS_LIST[1]
        (hundreds == 3) -> result += HUNDREDS_LIST[2]
        (hundreds == 4) -> result += HUNDREDS_LIST[3]
        (hundreds == 5) -> result += HUNDREDS_LIST[4]
        (hundreds == 6) -> result += HUNDREDS_LIST[5]
        (hundreds == 7) -> result += HUNDREDS_LIST[6]
        (hundreds == 8) -> result += HUNDREDS_LIST[7]
        (hundreds == 9) -> result += HUNDREDS_LIST[8]
    }
    if ((dozens != 0) && ((hundreds != 0) || (thousands != 0) || (thousands100 != 0) || (thousands10 != 0))) result += " "
    when {
        ((dozens == 1) && (units == 0)) -> result += DOSENS1_LIST[0]
        ((dozens == 1) && (units == 1)) -> result += DOSENS1_LIST[1]
        ((dozens == 1) && (units == 2)) -> result += DOSENS1_LIST[2]
        ((dozens == 1) && (units == 3)) -> result += DOSENS1_LIST[3]
        ((dozens == 1) && (units == 4)) -> result += DOSENS1_LIST[4]
        ((dozens == 1) && (units == 5)) -> result += DOSENS1_LIST[5]
        ((dozens == 1) && (units == 6)) -> result += DOSENS1_LIST[6]
        ((dozens == 1) && (units == 7)) -> result += DOSENS1_LIST[7]
        ((dozens == 1) && (units == 8)) -> result += DOSENS1_LIST[8]
        ((dozens == 1) && (units == 9)) -> result += DOSENS1_LIST[9]
        (dozens == 2) -> result += DOSENS_LIST[0]
        (dozens == 3) -> result += DOSENS_LIST[1]
        (dozens == 4) -> result += DOSENS_LIST[2]
        (dozens == 5) -> result += DOSENS_LIST[3]
        (dozens == 6) -> result += DOSENS_LIST[4]
        (dozens == 7) -> result += DOSENS_LIST[5]
        (dozens == 8) -> result += DOSENS_LIST[6]
        (dozens == 9) -> result += DOSENS_LIST[7]
    }
    if ((units != 0) && (dozens != 1) && ((dozens != 0) || (hundreds != 0) || (thousands != 0) || (thousands100 != 0) || (thousands10 != 0))) result += " "
    when {
        ((dozens != 1) && (units == 1)) -> result += UNITS_LIST[0]
        ((dozens != 1) && (units == 2)) -> result += UNITS_LIST[1]
        ((dozens != 1) && (units == 3)) -> result += UNITS_LIST[2]
        ((dozens != 1) && (units == 4)) -> result += UNITS_LIST[3]
        ((dozens != 1) && (units == 5)) -> result += UNITS_LIST[4]
        ((dozens != 1) && (units == 6)) -> result += UNITS_LIST[5]
        ((dozens != 1) && (units == 7)) -> result += UNITS_LIST[6]
        ((dozens != 1) && (units == 8)) -> result += UNITS_LIST[7]
        ((dozens != 1) && (units == 9)) -> result += UNITS_LIST[8]
    }

    return result
}






