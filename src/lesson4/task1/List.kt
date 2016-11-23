@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr

fun elem(x : Int) : Int {
    var element = 0
    for (i in 2..x){
        element = i
        if (x % i == 0) break
    }
    return element
}
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
    if (v.size == 1) return v[0]
    var sum : Double = 0.0
    for (i in 0..v.size-1) {
        sum += sqr(v[i])
    }
    return Math.sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.size == 0) return 0.0
    var sum = 0.0
    for (i in 0..list.size-1)
        sum += list[i]
    return sum/list.size
}
/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.size == 0) return mutableListOf()
    val mean = mean(list)
    for (i in 0..list.size-1)
        list[i] -= mean
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
    var C = 0.0
    if ((a.size == 0) && (b.size == 0)) return 0.0
    for (i in 0..a.size-1)
        C += a[i] * b[i]
    return C
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
    var degree = 0.0
    var P = 0.0
    for (i in 0..p.size-1) {
        P += p[i] * Math.pow(x , degree)
        degree += 1.0
    }
    return P
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
    if (list.size == 0) return mutableListOf()
    if (list.size == 1) return  list
    for ( i in list.size-1 downTo 1){
        for (j in i-1 downTo 0)
            list[i] +=list[j]
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
    val answ = mutableListOf<Int>()
    var number = 0
    var copy = n
    for (i in 1..n) {
        if (copy > 1) {
            answ.add(elem(copy))
            copy /= answ[number]
            number += 1
        }
        else break
    }
    return answ
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    val abc = factorize(n)
    val answ : String = abc.joinToString( separator = "*" )
    return answ
}

/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    if (n < base) return listOf(n)
    val answ = mutableListOf<Int>()
    var element = 0
    var copy = n
    for (i in 1..n) {
        if (copy < base){
            answ +=copy
            break
        }
        element = copy - copy/base*base
        answ += element
        copy = (copy - element) / base
    }
    for (i in 0..(answ.size-1)/2) {
        element = answ[answ.size - 1 - i]
        answ[answ.size - 1 - i] = answ[i]
        answ[i] = element
    }
    return answ
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
    val list = convert(n , base)
    var answ : String = ""
    var element = 0
    val ABC = "abcdefghijklmnopqrstuvwxyz"
    for (i in 0..list.size - 1) {
        element = list[i]
        if (list[i] > 9)
            answ += ABC[list[i] - 10]
        else answ += "$element"
    }
    return answ
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var answ = 0
    var mnojitel = 1
    for (i in digits.size - 1 downTo 0){
        answ += digits[i]*mnojitel
        mnojitel *= base
    }
    return answ
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
 val ABC = "0123456789abcdefghijklmnopqrstuvwxyz"
    var answ = 0
    var mnojitel = 1
    for ( i in str.length-1 downTo 0) {
        for (j in 0..ABC.length - 1)
            if (str[i] == ABC[j]) answ  += j * mnojitel
        mnojitel *= base
    }
    return answ
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
    var copy = n
    var str = ""
     for (i in 1..99999){
         if (copy >= 1000) {
             str +="M"
             copy -= 1000
         }
         else break
    }
    for (i in 1..99999){
        if (copy >= 900) {
            str +="CM"
            copy -= 900
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 500) {
            str +="D"
            copy -= 500
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 400) {
            str +="CD"
            copy -= 400
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 100) {
            str +="C"
            copy -= 100
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 90) {
            str +="XC"
            copy -= 90
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 50) {
            str +="L"
            copy -= 50
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 40) {
            str +="XL"
            copy -= 40
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 10) {
            str +="X"
            copy -= 10
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 9) {
            str +="IX"
            copy -= 9
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 5) {
            str +="V"
            copy -= 5
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 4) {
            str +="IV"
            copy -= 4
        }
        else break
    }
    for (i in 1..99999){
        if (copy >= 1) {
            str +="I"
            copy -= 1
        }
        else break
    }
    return str
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val iNeedIt = " "
    var copy = n
    var answ = ""
    var anotheransw = ""
    if (n >= 100000) {
        if (copy / 100000 == 1) answ += "сто"
        if (copy / 100000 == 2) answ += "двести"
        if (copy / 100000 == 3) answ += "триста"
        if (copy / 100000 == 4) answ += "четыреста"
        if (copy / 100000 == 5) answ += "пятьсот"
        if (copy / 100000 == 6) answ += "шестьсот"
        if (copy / 100000 == 7) answ += "семьсот"
        if (copy / 100000 == 8) answ += "восемьсот"
        if (copy / 100000 == 9) answ += "девятьсот"
        copy = copy % 100000
        if (copy < 1000) answ += " тысяч"
    }
    if (copy >= 10000) {
        if (copy / 10000 == 1) {
            if ((copy % 10000) / 1000 == 0) answ += " десять тысяч"
            if ((copy % 10000) / 1000 == 1) answ += " одиннадцать тысяч"
            if ((copy % 10000) / 1000 == 2) answ += " двенадцать тысяч"
            if ((copy % 10000) / 1000 == 3) answ += " тринадцать тысяч"
            if ((copy % 10000) / 1000 == 4) answ += " четырнадцать тысяч"
            if ((copy % 10000) / 1000 == 5) answ += " пятьнадцать тысяч"
            if ((copy % 10000) / 1000 == 6) answ += " шестнадцать тысяч"
            if ((copy % 10000) / 1000 == 7) answ += " семьнадцать тысяч"
            if ((copy % 10000) / 1000 == 8) answ += " восемнадцать тысяч"
            if ((copy % 10000) / 1000 == 9) answ += " девятнадцать тысяч"
        }
        if (copy / 10000 == 2) answ += " двадцать"
        if (copy / 10000 == 3) answ += " тридцать"
        if (copy / 10000 == 4) answ += " сорок"
        if (copy / 10000 == 5) answ += " пятьдесят"
        if (copy / 10000 == 6) answ += " шестьдесят"
        if (copy / 10000 == 7) answ += " семьдесят"
        if (copy / 10000 == 8) answ += " восемьдесят"
        if (copy / 10000 == 9) answ += " девяносто"
        copy %= 10000
        if (copy < 1000) answ += " тысяч"
    }
    if ((copy >= 1000) && ((n % 100000)/10000 != 1)) {
        if (copy / 1000 == 1) answ += " одна тысяча"
        if (copy / 1000 == 2) answ += " две тысячи"
        if (copy / 1000 == 3) answ += " три тысячи"
        if (copy / 1000 == 4) answ += " четыре тысячи"
        if (copy / 1000 == 5) answ += " пять тысяч"
        if (copy / 1000 == 6) answ += " шесть тысяч"
        if (copy / 1000 == 7) answ += " семь тысяч"
        if (copy / 1000 == 8) answ += " восемь тысяч"
        if (copy / 1000 == 9) answ += " девять тысяч"
        copy %= 1000
    }
    else copy %= 1000
    if (copy >= 100) {
        if (copy / 100 == 1) answ += " сто"
        if (copy / 100 == 2) answ += " двести"
        if (copy / 100 == 3) answ += " триста"
        if (copy / 100 == 4) answ += " четыреста"
        if (copy / 100 == 5) answ += " пятьсот"
        if (copy / 100 == 6) answ += " шестьсот"
        if (copy / 100 == 7) answ += " семьсот"
        if (copy / 100 == 8) answ += " восемьсот"
        if (copy / 100 == 9) answ += " девятьсот"
        copy = copy % 100
    }
    if (copy >= 10) {
        if (copy / 10 == 1) {
            if ((copy % 10) == 0) answ += " десять"
            if ((copy % 10)  == 1) answ += " одиннадцать"
            if ((copy % 10) == 2) answ += " двенадцать"
            if ((copy % 10) == 3) answ += " тринадцать"
            if ((copy % 10) == 4) answ += " четырнадцать"
            if ((copy % 10) == 5) answ += " пятьнадцать"
            if ((copy % 10) == 6) answ += " шестьнадцать"
            if ((copy % 10) == 7) answ += " семьнадцать"
            if ((copy % 10) == 8) answ += " восемьнадцать"
            if ((copy % 10) == 9) answ += " девятьнадцать"
        }
        if (copy / 10 == 2) answ += " двадцать"
        if (copy / 10 == 3) answ += " тридцать"
        if (copy / 10 == 4) answ += " сорок"
        if (copy / 10 == 5) answ += " пятьдесят"
        if (copy / 10 == 6) answ += " шестьдесят"
        if (copy / 10 == 7) answ += " семьдесят"
        if (copy / 10 == 8) answ += " восемьдесят"
        if (copy / 10 == 9) answ += " девяносто"
        copy %= 10
    }
    if ((copy >= 1) && ((n % 100)/10 != 1)) {
        if (copy == 1) answ += " один"
        if (copy == 2) answ += " два"
        if (copy == 3) answ += " три"
        if (copy == 4) answ += " четыре"
        if (copy == 5) answ += " пять"
        if (copy == 6) answ += " шесть"
        if (copy == 7) answ += " семь"
        if (copy == 8) answ += " восемь"
        if (copy == 9) answ += " девять"
    }
    if (answ[0] == iNeedIt[0]) {
        for (i in 1..answ.length - 1)
            anotheransw += answ[i]
    return anotheransw
    }
    else return answ
}