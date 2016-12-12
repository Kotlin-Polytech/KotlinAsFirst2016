@file:Suppress("UNUSED_PARAMETER")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.pow
import lesson3.task1.digitNumber
import org.jetbrains.annotations.Mutable

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
    var absv = 0.0
    for (element in v) {
        absv = absv + sqr(element)
    }
    return Math.sqrt(absv)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val sr = list.sum() / list.size
    for (i in 0..list.size - 1) {
        list[i] -= sr
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
    var scalar = 0.0
    for (i in 0..a.size - 1) {
        scalar += a[i] * b[i]
    }
    return scalar
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
    var px = 0.0
    var i = 0.0
    for (element in p) {
        px += element * Math.pow(x, i)
        i++
    }
    return px
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
    var allSum = 0.0
    var pastSum = 0.0
    for (i in 0..list.size - 1) {
        pastSum = allSum
        allSum += list[i]
        list[i] = list[i] + pastSum
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
    var primeIndex = 2
    var number = n
    val result = mutableListOf<Int>()
    while (number > 1) {
        if (number % primeIndex == 0) {
            result.add(primeIndex)
            number = number / primeIndex
        } else primeIndex++
    }
    return result
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
    val result = mutableListOf<Int>()
    if (number == 0) result.add(0)
    while (number > 0) {
        result.add(0, number % base)
        number = number / base
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
    val letters = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
    return convert(n, base).map { letters[it] }.joinToString(separator = "")
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var number = 0
    var i = digits.size - 1
    for (element in digits) {
        number += element * pow(base, i)
        i--
    }
    return number
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
    val letters = mapOf<Char, Int>('0' to 0, '1' to 1, '2' to 2, '3' to 3, '4' to 4, '5' to 5, '6' to 6, '7' to 7, '8' to 8, '9' to 9, 'a' to 10,
            'b' to 11, 'c' to 12, 'd' to 13, 'e' to 14, 'f' to 15, 'g' to 16, 'h' to 17, 'i' to 18, 'j' to 19, 'k' to 20,
            'l' to 21, 'm' to 22, 'n' to 23, 'o' to 24, 'p' to 25, 'q' to 26, 'r' to 27, 's' to 28, 't' to 29, 'u' to 30,
            'v' to 31, 'w' to 32, 'x' to 33, 'y' to 34, 'z' to 35)
    var number = 0
    var i = str.length - 1
    for (char in str) {
        number += letters[char]!! * pow(base, i)
        i--
    }
    val a = str.toList()
    return number
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
    val romans = mapOf<Int, String>(1 to "I", 2 to "II", 3 to "III", 4 to "IV", 5 to "V", 6 to "VI", 7 to "VII", 8 to "VIII", 9 to "IX", 10 to "X",
            20 to "XX", 30 to "XXX", 40 to "XL", 50 to "L", 60 to "LX", 70 to "LXX", 80 to "LXXX", 90 to "XC", 100 to "C",
            200 to "CC", 300 to "CCC", 400 to "CD", 500 to "D", 600 to "DC", 700 to "DCC", 800 to "DCCC", 900 to "CM")
    val resultPart2 = mutableListOf<String>()
    val resultPart1 = mutableListOf<String>()
    val nPart1 = n / 1000
    var nPart2 = n % 1000
    var i = 10
    while (nPart2 > 0) {
        if (nPart2 % i == 0) {
            i *= 10
            continue
        }
        resultPart2.add(0, romans[nPart2 % i]!!)
        nPart2 -= nPart2 % i
    }
    if (nPart1 == 0) return resultPart2.joinToString(separator = "")
    for (j in 1..nPart1) resultPart1.add("M")
    return (resultPart1 + resultPart2).joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val words = mapOf<Int, String>(1 to "один", 2 to "два", 3 to "три", 4 to "четыре", 5 to "пять", 6 to "шесть", 7 to "семь", 8 to "восемь", 9 to "девять",
            10 to "десять", 11 to "одиннадцать", 12 to "двенадцать", 13 to "тринадцать", 14 to "четырнадцать", 15 to "пятнадцать", 16 to "шестнадцать", 17 to "семнадцать", 18 to "восемнадцать", 19 to "девятнадцать",
            20 to "двадцать", 30 to "тридцать", 40 to "сорок", 50 to "пятьдесят", 60 to "шестьдесят", 70 to "семьдесят", 80 to "восемьдесят", 90 to "девяносто",
            100 to "сто", 200 to "двести", 300 to "триста", 400 to "четыреста", 500 to "пятьсот", 600 to "шестьсот", 700 to "семьсот", 800 to "восемьсот", 900 to "девятьсот")
    val resultPart2 = mutableListOf<String>()
    val resultPart1 = mutableListOf<String>()
    var nPart1 = n / 1000
    var nPart2 = n % 1000
    var i = 10
    while (nPart2 > 0) {
        if (nPart2 % i == 0) {
            i *= 10
            continue
        }
        if ((i == 10) && (nPart2 % 100 in 11..19)) i = 100
        resultPart2.add(0, words[nPart2 % i]!!)
        nPart2 -= nPart2 % i
    }
    if (nPart1 > 0) {
        i = 10
        while (nPart1 > 0) {
            if (nPart1 % i == 0) {
                i *= 10
                continue
            }
            if ((i == 10) && (nPart1 % 100 in 11..19)) i = 100
            resultPart1.add(0, words[nPart1 % i]!!)
            nPart1 -= nPart1 % i
        }
        nPart1 = n / 1000
        when {
            (nPart1 % 10 == 1) && (nPart1 % 100 != 11) -> {
                resultPart1.removeAt(resultPart1.size - 1)
                resultPart1.add("одна тысяча")
            }
            (nPart1 % 10 == 2) && (nPart1 % 100 != 12) -> {
                resultPart1.removeAt(resultPart1.size - 1)
                resultPart1.add("две тысячи")
            }
            (nPart1 % 10 == 3) && (nPart1 % 100 != 13) -> {
                resultPart1.removeAt(resultPart1.size - 1)
                resultPart1.add("три тысячи")
            }
            (nPart1 % 10 == 4) && (nPart1 % 100 != 14) -> {
                resultPart1.removeAt(resultPart1.size - 1)
                resultPart1.add("четыре тысячи")
            }
            else -> {
                resultPart1.add("тысяч")
            }
        }
    }
    return (resultPart1 + resultPart2).joinToString(separator = " ")
}


/* Про купюры*/
fun moneyCounter(sum: Double, coins: String): String {
    var price = sum
    val result = mutableListOf<String>()
    try {
        val listCoins = coins.split(", ")
        for (i in 0..listCoins.size-1){
            val element = listCoins[i].toDouble()
            val k = (price / element).toInt()
            if (k != 0) {
                if (element % 1 == 0.0) {
                    result.add("$k x ${element.toInt()}")
                }
                else result.add("$k x $element")
                price = price - element*k
            }
        }
    }
    catch (e:IllegalArgumentException){
        throw e
    }
    return result.joinToString(separator = ", ")
}