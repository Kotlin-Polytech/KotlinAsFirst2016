@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
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
    val size = v.size
    var sum = 0.0
    if (v.isNotEmpty()) {
        for (i in 0..(size - 1)) {
            sum += sqr(v[i])
        }
        return Math.sqrt(sum)
    } else return 0.0
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    val sp = list.sum()
    val n = list.size
    if (n > 0) {
        return (sp / n)
    } else return 0.0
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isNotEmpty()) {
        val sred = mean(list)
        for (i in 0..(list.size - 1)) {
            list[i] -= sred
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
    val size = a.size
    var sum = 0.0
    if ((a.isNotEmpty()) && (b.isNotEmpty())) {
        for (i in 0..(size - 1)) {
            sum += a[i] * b[i]
        }
        return sum
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
    val size = p.size
    var sum = 0.0
    if (p.isNotEmpty()) {
        for (i in 0..(size - 1)) {
            if (i == 0) sum = sum + p[i]
            else sum += p[i] * Math.pow(x, i.toDouble())
        }
        return sum
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
    val size = list.size
    var sum = 0.0
    if (list.isNotEmpty()) {
        for (i in 0..(size - 1)) {
            list[i] = list[i] + sum
            sum = list[i]
        }
        return list
    } else return list
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
        val minimum = minDivisor(number)
        list.add(minimum)
        number /= minimum
    }
    return list.sorted()
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
    var list = mutableListOf<Int>()
    while (number >= base) {
        list.add(number % base)
        number /= base
    }
    list.add(number)
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
    val number = convert(n, base)
    var result = ""
    for (i in number) {
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
fun mpow(a: Int, b: Int): Int {
    var num = 1
    for (n in 0..b) {
        if (n == 0) num = 1
        else num *= a
    }
    return num
}

fun decimal(digits: List<Int>, base: Int): Int {
    return digits.reversed().mapIndexed { i, d -> d * mpow(base, i) }.sum()
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
fun sqr(a: Int, b: Int): Int {
    var result = 1
    for (i in 1..b) result *= a
    return result
}

fun decimalFromString(str: String, base: Int): Int {
    var result = 0
    for (i in 0..str.length - 1) {
        val number = str[str.length - 1 - i]
        result += when (number) {
            'a' -> (lesson4.task1.sqr(base, i) * 10)
            'b' -> (lesson4.task1.sqr(base, i) * 11)
            'c' -> (lesson4.task1.sqr(base, i) * 12)
            'd' -> (lesson4.task1.sqr(base, i) * 13)
            'e' -> (lesson4.task1.sqr(base, i) * 14)
            'f' -> (lesson4.task1.sqr(base, i) * 15)
            'g' -> (lesson4.task1.sqr(base, i) * 16)
            'h' -> (lesson4.task1.sqr(base, i) * 17)
            'i' -> (lesson4.task1.sqr(base, i) * 18)
            'j' -> (lesson4.task1.sqr(base, i) * 19)
            'k' -> (lesson4.task1.sqr(base, i) * 20)
            'l' -> (lesson4.task1.sqr(base, i) * 21)
            'm' -> (lesson4.task1.sqr(base, i) * 22)
            'n' -> (lesson4.task1.sqr(base, i) * 23)
            'o' -> (lesson4.task1.sqr(base, i) * 24)
            'p' -> (lesson4.task1.sqr(base, i) * 25)
            'q' -> (lesson4.task1.sqr(base, i) * 26)
            'r' -> (lesson4.task1.sqr(base, i) * 27)
            's' -> (lesson4.task1.sqr(base, i) * 28)
            't' -> (lesson4.task1.sqr(base, i) * 29)
            'u' -> (lesson4.task1.sqr(base, i) * 30)
            'v' -> (lesson4.task1.sqr(base, i) * 31)
            'w' -> (lesson4.task1.sqr(base, i) * 32)
            'x' -> (lesson4.task1.sqr(base, i) * 33)
            'y' -> (lesson4.task1.sqr(base, i) * 34)
            'z' -> (lesson4.task1.sqr(base, i) * 35)
            else -> (lesson4.task1.sqr(base, i) * (number - 48).toInt())
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
fun russian(n: Int): String = TODO()