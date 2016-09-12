@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import com.sun.xml.internal.fastinfoset.util.StringArray
import lesson1.task1.discriminant
import java.lang.Math.*

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
fun abs(v: List<Double>): Double = sqrt(v.sumByDouble{it*it})

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.isEmpty()) 0.0
    else list.sum()/list.count()

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> =
        if(list.isEmpty()) list else list.map {it-list.sum()/list.count()}.toMutableList()

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double = a.sumByDouble { it*b[a.indexOf(it)] }
/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double = p.sumByDouble { it*pow(x,p.indexOf(it).toDouble()) }

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> =
        list.map { it+list.subList(0,list.indexOf(it)-1).sum() }.toMutableList()

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var list = mutableListOf<Int>()
    var num: Int = n
    while(num%2 == 0) {
        num /= 2
        list.add(2)
    }
    var b: Int = 3
    while (num > 2) {
        if (num%b == 0) {
            list.add(b)
            num /= b
        } else {
            b += 2
        }
    }
    list.sort()
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    var str: String = ""
    var num: Int = n
    while(num%2 == 0) {
        num /= 2
        if (str.length == 0) str += "2" else str += "*2"
    }
    var b: Int = 3
    while (num > 2) {
        if (num % b == 0) {
            if (str.length == 0) str += b else str += "*"+b
            num /= b
        } else {
            b += 2
        }
    }
    return str
}

/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var num: Int = n
    var list = mutableListOf<Int>()
    while (num > base) {
        list.add(num%base)
        num /= base
    }
    list.add(num)
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
    val chars: String = "abcdefghijklmnopqrstuywxyz"
    var num: Int = n
    var str: String = ""
    while (num > base) {
        if (num%base < 10)
            str += num%base
        else
            str += chars[num%base-10]
        num /= base
    }
    if (num < 10)
        str += num%base
    else
        str += chars[num%base-10]
    return str.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var i: Int = 0
    return digits.reversed().sumBy { it*Math.pow(base.toDouble(), i++.toDouble()).toInt() }
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
    val chars: String = "abcdefghijklmnopqrstuywxyz"
    var i: Int = 0
    return str.reversed().sumBy { (if (chars.contains(it)) chars.indexOf(it)+10 else it.toString().toInt())*
                                     Math.pow(base.toDouble(), i++.toDouble()).toInt() }
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
    val s = arrayOf("","I","II","III","IV","V","VI","VII","VIII","IX")
    var str: String = ""
    var num: Int = n
    if (num/1000 > 0)
        for (i in 1..num/1000)
            str += "M"
    num %= 1000
    if (num/500 == 1)
        if (num%500 >= 400) {
            str += "CM"
            num -= 900
        } else {
            str += "D"
            num %= 500
        }
    if (num/100 > 0)
        if (num/100 == 4) {
            str += "CD"
        } else {
            for (i in 1..num/100)
                str += "C"
        }
    num %= 100
    if (num/50 == 1)
        if (num%50 >= 40) {
            str += "XC"
            num -= 90
        } else {
            str += "L"
            num %= 50
        }
    if (num/10 > 0)
        if (num/10 == 4) {
            str += "XL"
        } else {
            for (i in 1..num/10)
                str += "X"
        }
    num %= 10
    str += s[num]
    return str
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun points(n: Int, female: Boolean): String {
    var str: String = ""
    when(n){
        0 -> str = ""
        1 -> if (!female) str = "один" else str = "одна"
        2 -> if (!female) str = "два" else str = "две"
        3 -> str = "три"
        4 -> str = "четыре"
        5 -> str = "пять"
        6 -> str = "шесть"
        7 -> str = "семь"
        8 -> str = "восемь"
        9 -> str = "девять"
    }
    return str
}
fun tens(n: Int,female: Boolean): String {
    var str: String = ""
    when (n) {
        in 1..9 -> str = points(n,female)
        10 -> str = "десять"
        11 -> str = "одинадцать"
        12 -> str = "двенадцать"
        13 -> str = "тринадцать"
        14 -> str = "четырнадцать"
        15 -> str = "пятнадцать"
        16 -> str = "шестнадцать"
        17 -> str = "семнадцать"
        18 -> str = "восенадцать"
        19 -> str = "девятнадцать"
        in 20..29 -> str = "двадцать" + (if(n%10 > 0) " " else "") + points(n%10,female)
        in 30..39 -> str = "тридцать" + (if(n%10 > 0) " " else "") + points(n%10,female)
        in 40..49 -> str = "сорок" + (if(n%10 > 0) " " else "") + points(n%10,female)
        in 50..59 -> str = "пятьдесят" + (if(n%10 > 0) " " else "") + points(n%10,female)
        in 60..69 -> str = "шестьдесят" + (if(n%10 > 0) " " else "") + points(n%10,female)
        in 70..79 -> str = "семьдесят" + (if(n%10 > 0) " " else "") + points(n%10,female)
        in 80..89 -> str = "восемдесят" + (if(n%10 > 0) " " else "") + points(n%10,female)
        in 90..99 -> str = "девяносто" + (if(n%10 > 0) " " else "") + points(n%10,female)
    }
    return str
}
fun hundreds(n: Int): String {
    var str: String = ""
    when(n) {
        1 -> str = "сто"
        2 -> str = "двести"
        3 -> str = "триста"
        4 -> str = "четыреста"
        in 5..9 -> str = points(n,false)+"сот"
    }
    return str
}
fun keyword(n: Int): String {
    if ((n>4 && n<21) || n%10 == 0 || n%10 > 4){
        return "тысяч"
    } else if(n%10 == 1) {
        return "тысяча"
    } else {
        return "тысячи"
    }
}
fun russian(n: Int): String {
    var str: String = ""
    if (n/1000 > 0) {
        if (n/100000 > 0)
            str += hundreds(n/100000)
        if ((n/1000)%100 > 0)
            str += (if(n/100000 > 0) " " else "") + tens((n/1000)%100,true)
        str += " " + keyword((n/1000)%100) + if((n%1000) > 0) " " else ""
    }
    if (n%1000 > 0) {
        if ((n%1000)/100 > 0)
            str += hundreds((n%1000)/100)
        if ((n%1000)%100 > 0)
            str += (if((n%1000)/100 > 0) " " else "") + tens((n%1000)%100,false)
    }
    return str
}