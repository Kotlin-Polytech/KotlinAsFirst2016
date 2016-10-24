@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.minDivisor
import lesson1.task1.takeDigit

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
    return if (v.isEmpty()) 0.0
    else Math.sqrt(v.map { it * it }.sum())
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isNotEmpty()) list.average() else 0.0

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val temp = list.average()
        for (i in 0..list.size-1) {
            list[i] -= temp
        }
        return list
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
    return if (a.size != b.size) Double.NaN
    else if (a.isEmpty() || b.isEmpty()) 0.0
    else a.zip(b, { elementOfa, elementOfb -> elementOfa * elementOfb }).sum()
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
    var result: Double = 0.0
    if (p.isEmpty()) return result
    else {
        for (i in p.reversed()) {
            result = result * x + i
        }
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
    if (list.isEmpty() || list.size == 1) return list
    else {
        for (i in 1..list.size-1) {
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
    return if (n < 1) listOf()
    else {
        var newN: Int = n
        var result: List<Int> = listOf()
        while (newN != 1) {
            result = result + listOf(minDivisor(newN))
            newN = newN / minDivisor(newN)
        }
        result
    }
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
    var result: List<Int> = listOf()
    var mutN: Int = n
    do {
        result = listOf(mutN%base)+ result
        mutN/=base
    } while (mutN != 0)
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
fun <T> List<T>.indexOf(value: T): Int {
    for (i in 0..this.size - 1) {
        if (this[i] == value) return i
    }
    return -1
}

val convertTable: List<Char> =
        listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
                'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z')

fun convertToString(n: Int, base: Int): String {
    var result: String = ""
    for (i in convert(n, base)) result += convertTable[i].toString()
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
    var result: Int = 0
    for (i in digits) {
        result = result * base + i
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
    var result: Int = 0
    for (i in str.toList()) {
        result = result * base + convertTable.indexOf(i)
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
val table: Map<Int,String> =
        mapOf(  1000 to "M",
                900 to "CM",
                500 to "D",
                400 to "CD",
                100 to "C",
                90 to "XC",
                50 to "L",
                40 to "XL",
                10 to "X",
                9 to "IX",
                5 to "V",
                4 to "IV",
                1 to "I")

fun roman(n: Int): String {
    var result: String = ""
    var mutN: Int = n
    for (i in table) {
        while (mutN >= i.key) {
            result += i.value
            mutN -= i.key
        }
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
val hundreds: Map<Int, String> =
        mapOf(  1 to "сто",
                2 to "двести",
                3 to "триста",
                4 to "четыреста",
                5 to "пятьсот",
                6 to "шестьсот",
                7 to "семьсот",
                8 to "восемьсот",
                9 to "девятьсот")

val dozens: Map<Int, String> =
        mapOf(  2 to "двадцать",
                3 to "тридцать",
                4 to "сорок",
                5 to "пятьдесят",
                6 to "шестьдесят",
                7 to "семьдесят",
                8 to "восемьдесят",
                9 to "девяносто")

val teens: Map<Int, String> =
        mapOf(  10 to "десять",
                11 to "одиннадцать",
                12 to "двенадцать",
                13 to "тринадцать",
                14 to "четырнадцать",
                15 to "пятнадцать",
                16 to "шестнадцать",
                17 to "семнадцать",
                18 to "восемнадцать",
                19 to "девятнадцать")

val masculineUnits: Map<Int, String> =
        mapOf(  1 to "один",
                2 to "два",
                3 to "три",
                4 to "четыре",
                5 to "пять",
                6 to "шесть",
                7 to "семь",
                8 to "восемь",
                9 to "девять")

val feminineUnits: Map<Int, String> =
        mapOf(  1 to "одна",
                2 to "две",
                3 to "три",
                4 to "четыре",
                5 to "пять",
                6 to "шесть",
                7 to "семь",
                8 to "восемь",
                9 to "девять")

fun tripletProcessing(triplet: Int,
                      unitsTable: Map<Int, String>,
                      diclensionOfTripletWord: List<String> = listOf()): List<String> {
    var result: MutableList<String> = mutableListOf()

    var temp: Int = triplet / 100
    if (temp != 0) result.add(hundreds[temp] ?: "")

    temp = takeDigit(triplet, 2)
    if (temp == 1) result.add(teens[triplet % 100] ?: "")
    else if (temp != 1) {
        if (dozens[temp] != null) result.add(dozens[temp] ?: "")
        temp = triplet % 10
        if (temp != 0) result.add(unitsTable[temp] ?: "")
    }

    if (diclensionOfTripletWord.isNotEmpty()) {
        if (diclensionOfTripletWord.size == 3) {
            if (triplet == 1) result = mutableListOf(diclensionOfTripletWord[0])
            else {
                when {
                    triplet % 100 in 10..19 || triplet % 10 in 5..9 || triplet / 100 != 0
                    -> result.add(diclensionOfTripletWord[2])
                    triplet % 10 == 1
                    -> result.add(diclensionOfTripletWord[0])
                    triplet % 10 in 2..4
                    -> result.add(diclensionOfTripletWord[1])
                }
            }
        }
    }

    return result
}

fun russian(n: Int): String {
    return (tripletProcessing(n / 1000, feminineUnits, listOf("тысяча", "тысячи", "тысяч"))
            + tripletProcessing(n % 1000, masculineUnits)).joinToString(" ")
}