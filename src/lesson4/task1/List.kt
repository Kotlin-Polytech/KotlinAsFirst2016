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
    // val v1 : List <Double> = v.map {it * it}
    return Math.sqrt ( ( v.map {it * it} ).sum() )
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if ( list.isEmpty() ) return 0.0
    else return ( list.sum() / list.size )
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
	if ( list.isEmpty() ) return list
    val ariphmetical_average = list.sum() / list.size.toDouble()
    for( ( index, element ) in list.withIndex() ) list[index] -= ariphmetical_average

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
    var qbzv = 0.0
    if ( a.size != b.size ) return 0.0
    for( i in 0..( a.size - 1 ) ) {
        qbzv += a[i] * b[i]
    }

    return qbzv
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

    var qbzv = 0.0

    for( i in 0..p.size - 1 ) {
        qbzv += p[i] * Math.pow( x, i.toDouble() )
    }

    return qbzv
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
    var previous_element = 0.0
    for ( ( index, element ) in list.withIndex() ) {
        list[index] += previous_element
        previous_element = list[index]
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
fun number_is_prime(n: Int): Boolean {
    if ( n == 0 || n == 1 || n == -1 ) return false
    for(i in 2..Math.abs( n/2 )) if ( n % i == 0 ) return false
    return true
}

fun factorize(n: Int): List<Int> {
    var current_number = n
    val prime_divisors_storage = mutableListOf <Int> ()

    while ( true ) {
        for (i in current_number / 2 downTo 2) {
            if ( current_number % i == 0 && number_is_prime(i) ) {
                prime_divisors_storage.add(i)
                current_number /= i
                break
            }
        }
        if (number_is_prime(current_number)) {
            prime_divisors_storage.add( current_number )
            break
        }
    }

    return prime_divisors_storage.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String = factorize ( n ).joinToString ( "*", "", "", -1, "..." )

/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val digit_storage = mutableListOf <Int> ()
    var current_number = n

    if ( n == 0 ) return listOf(0)
    if ( n < 0 || base < 2 ) return listOf()

    while ( current_number > 0 ) {
        digit_storage.add ( current_number % base )
        current_number /= base
    }
    return digit_storage.reversed()
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
    val digit_storage = convert ( n, base )
    var digit_line = ""

    if ( n < 0 || base < 2 || base > 36 ) return ""

    for ( element in digit_storage ) digit_line += if ( element < 10 ) "$element" else "${ ( 'a' + element - 10 ).toChar() }"

    return digit_line
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var target = 0

    for ( element in digits ) target = ( target * base ) + element

    return target
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
	var digit_storage = mutableListOf <Int> ()
	var target = 0
	
	for ( element in str ) digit_storage.add ( element.toInt() )
	
	return decimal ( digit_storage, base )
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
    var target = ""

    val digit_1   = listOf ( "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" ) // 1 2 3 4 5 6 7 8 9
    val digit_10  = listOf ( "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" ) // 0 10 20 30 40 50 60 70 80 90
    val digit_100 = listOf ( "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" ) // 0 100 200 300 400 500 600 700 800 900
//    val digit_1000 = listOf ( "", "M", "MM", "MMM", "Mv", "v", "vM", "vMM", "vMMM", "ix" ) // 0 1000 2000 3000 4000 5000 6000 7000 8000 9000


    for ( i in 0 .. ( n / 1000 - 1 ) ) target += "M"
//    target += digit_1000 [ n / 1000 ]
    target += digit_100  [ ( n % 1000 ) / 100 ]
    target += digit_10   [ ( n % 100 ) / 10 ]
    target += digit_1    [ n % 10 ]

    return target
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = TODO()
