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
fun abs(v: List<Double>): Double {
    var sum = 0.0
    for (elem in v) {
        sum += elem * elem
    }
    return (Math.sqrt(sum))
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.size == 0) 0.0 else (list.sum() / list.size)
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mid = mean(list)
    for (i in 0..list.size - 1) {
        val element = list[i]
        list[i] = element - mid
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
    for (i in 0..a.size - 1)
        result += a[i] * b [i]
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
    for (i in 0..p.size - 1)
        result += p[i] * Math.pow(x, i.toDouble())
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
    for (i in 1..list.size - 1){
        list[i] += list[i-1]
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
    var m = n
    for (i in 2..n){
        while (m % i == 0){
            m = m / i
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
fun factorizeToString(n: Int) : String = factorize(n).joinToString(separator = "*")


/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */

fun convert(n: Int, base: Int): List<Int> {
    val result1 = mutableListOf<Int>()
    var m = n
    while (m > 0) {
        result1.add(m % base)
        m /= base
    }
    return if (n == 0) listOf(0) else result1.reversed()
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
    val result2 = convert(n, base)
    var result1 = String()
    val ALPHABET = "abcdefghijklmnopqrstuvwxyz"
    for (m in result2) {
        if (m < 10) result1 += m  else
        result1 += ALPHABET[m - 10]
    }
    return if (n == 0) "0" else result1
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    return if (digits.size == 0) 0 else {
        var result = 0
        var n = digits[0]
        for (i in 0..digits.size - 2) {
            result = n * base + digits[i + 1]
            n = result
        }
        return if (digits.size == 1) n else result

    }
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
fun decimalFromString(str: String, base: Int): Int{
    val middle = mutableListOf<Int>()
    for (i in 0..str.length - 1) {
        if (str[i] in '0'..'9')
            middle.add(str[i] - '0') else middle.add(str[i] - 'a' + 10)
        }
    return if (str.length == 1) middle[0] else decimal (middle,base)
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
    var result = String()
    if (n / 1000 >= 1) {
        var thousand = n / 1000
        while (thousand > 0) {
            result += 'M'
            thousand--
        }
    }
    var middle = n % 1000
    if (middle / 100 >= 1) {
        if (middle / 100 < 4) {
            var hundred = middle / 100
            while (hundred > 0) {
                result += 'C'
                hundred--
            }
        } else
            if (middle / 100 == 4)
                result += "CD" else
                if (middle / 100 == 9) result += "CM" else {
                    result += 'D'
                    var plus = middle / 100 - 5
                    while (plus > 0) {
                        result += 'C'
                        plus--
                    }
                }
    }
    middle %= 100
    if (middle / 10 >= 1) {
        if (middle / 10 < 4) {
            var ten = middle / 100
            while (ten > 0) {
                result += 'X'
                ten--
            }
        } else
            if (middle / 10 == 4)
                result += "XL" else
                if (middle / 10 == 9) result += "XC" else {
                    result += 'L'
                    var plus = middle / 10 - 5
                    while (plus > 0) {
                        result += 'X'
                        plus--
                    }
                }
    }
    middle %= 10
    if (middle >= 1) {
        if (middle < 4) {
            var units = middle
            while (units > 0) {
                result += 'I'
                units--
            }
        } else
            if (middle == 4)
                result += "IV" else
                if (middle == 9) result += "IX" else {
                    result += 'V'
                    var plus = middle - 5
                    while (plus > 0) {
                        result += 'I'
                        plus--
                    }
                }
    }
    return result
}

val hd = listOf("сто ","двести ","триста ","четыреста ","пятьсот ","шестьсот ","семьсот ","восемьсот ","девятьсот ")
val tn = listOf("двадцать ","тридцать ","сорок ","пятьдесят ","шестьдесят ","семьдесят ","восемьдесят ","девяносто ")
val tn1 = listOf("десять","одиннадцать","двенадцать","тринадцать","четырнадцать","пятнадцать","шестнадцать","семнадцать","восемнадцать","девятнадцать")
val un = listOf("одна ","две ","три ","четыре ","пять ","шесть ","семь ","восемь ","девять ")
val un1 = listOf("один","два","три","четыре","пять","шесть","семь","восемь","девять")

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var result = String()
    if (n / 1000 > 0) {
        val middle = n / 1000
        if (middle / 100 >= 1) {
            val th1 = middle / 100
            result += hd[th1 - 1]
        }
        if ((middle % 100) / 10 == 1) {
            val mid = middle % 10
            result += tn1[mid]
            if (n%1000>0) result+=" "
        }
        else if ((middle % 100) / 10 == 0){
            val mid2 = middle % 10
            if(mid2 == 0 )  result += ""
            else result += un[mid2 -1]
        } else {
            val mid1 = middle % 100 / 10
            result += tn[mid1 - 2]

            val mid2 = middle % 10
            result += un[mid2 - 1]
        }
        if (middle %10 ==1 ) result += "тысяча" else if (middle%10==2) result += "тысячи" else result += "тысяч"
        if (n%1000>0) result += " "
    }
    val middle2 = n % 1000
    if (middle2 / 100 >= 1) {
        val th1 = middle2 / 100
        result += hd[th1 - 1]
    }
    if ((middle2 % 100) / 10 == 1) {
        val mid = middle2 % 10
        result += tn1[mid]
    }
    else  if ((middle2 % 100) / 10 == 0){
        val mid2 = middle2 % 10
        if (mid2==0) result+= ""
        else result += un1[mid2 - 1]
    } else

    {
        val mid1 = middle2 % 100 / 10
        result += tn[mid1 - 2]
        val mid2 = middle2 % 10
        result += un1[mid2 - 1]
    }
    return result
}


