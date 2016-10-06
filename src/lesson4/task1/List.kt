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

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.size == 0) return 0.0
    var result = 0.0
    for (element in list) {
        result = result + element
    }
    result = result / list.size
    return result
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    var center=0.0
    for (i in 0..list.size-1){
        center += list[i]
    }
    center = center/list.size
    for (i in 0..list.size-1){
        list[i]=list[i]-center
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
    if ((a.size==0) || (b.size==0)) return 0.0
    var element= 0.0
    for (i in 0..a.size-1){
        element=element+(a[i]*b[i])
    }
    return element
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */

fun stepDouble(x:Double, s:Int):Double {
    var x1=1.0
    for (i in 1..s){
        x1=x1*x
    }
    return x1
}

fun polynom(p: List<Double>, x: Double): Double {
    var result=0.0
    for (i in 0..p.size-1) {
        result=result+p[i]*stepDouble(x,i)
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
    var a:MutableList<Double> = list
    for (i in 1..list.size-1) {
        if (i==1) a[i] = list[i]
        a[i]=list[i]+a[i-1]
    }
    return a
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var list:MutableList<Int> = mutableListOf()
    var nMutable=n
    for (i in 2..n){
        if (nMutable==0) break
        for(j in 2..n)
            if (nMutable%j==0){
                nMutable=nMutable/j
                list.add(j)
                break
            }
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */

fun buildExample(list: List<Int>):String = list.joinToString(separator = "*")


fun factorizeToString(n: Int): String {
    var list:MutableList<Int> = mutableListOf()
    var nMutable=n
    for (i in 2..n){
        if (nMutable==0) break
        for(j in 2..n)
            if (nMutable%j==0){
                nMutable=nMutable/j
                list.add(j)
                break
            }
    }
    return buildExample(list)
}

/**
 * Средняя
 *
 * Перевести заданное натуральное число n в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */

fun convert(n: Int, base: Int): List<Int> {
    var list : MutableList<Int> = mutableListOf()
    if (n==1) return listOf(1)
    var n1=n*base
    var mod=0
    while (n1 !=0){
        n1=n1/base
        mod=n1%base
        list.add(0,mod)
    }
    list.removeAt(0)
    return list
}

/**
 * Сложная
 *
 * Перевести заданное натуральное число n в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun str(list: List<String>):String = list.joinToString(separator = "")

fun convertToString(n: Int, base: Int): String {
    var list:MutableList<String> = mutableListOf()
    if (n==1) return "1"
    var n1=n*base
    var mod=0
    while (n1 !=0 ){
        n1=n1/base
        mod=n1%base
        if (mod>9) {
            if (mod==10) list.add(0,"a")
            if (mod==11) list.add(0,"b")
            if (mod==12) list.add(0,"c")
            if (mod==13) list.add(0,"d")
            if (mod==14) list.add(0,"e")
            if (mod==15) list.add(0,"f")
            if (mod==16) list.add(0,"g")
            if (mod==17) list.add(0,"h")
            if (mod==18) list.add(0,"i")
            if (mod==19) list.add(0,"j")
            if (mod==20) list.add(0,"k")
            if (mod==21) list.add(0,"l")
            if (mod==22) list.add(0,"m")
            if (mod==23) list.add(0,"n")
            if (mod==24) list.add(0,"o")
            if (mod==25) list.add(0,"p")
            if (mod==26) list.add(0,"q")
            if (mod==27) list.add(0,"r")
            if (mod==28) list.add(0,"s")
            if (mod==29) list.add(0,"t")
            if (mod==30) list.add(0,"u")
            if (mod==31) list.add(0,"v")
            if (mod==32) list.add(0,"w")
            if (mod==33) list.add(0,"x")
            if (mod==34) list.add(0,"y")
            if (mod==35) list.add(0,"z")
        }
        else list.add(0,"$mod")
    }
    list.removeAt(0)
    return str(list)
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun step(x:Int, n:Int):Int {
    var x1=1
    for (i in 1..n){
        x1 = x1*x
    }
    return x1
}

fun decimal(digits: List<Int>, base: Int): Int {
    var result=0
    for ( i in 0..digits.size-1) {
        result = result + (digits[i]* step(base,digits.size-1-i))
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
    var result=0
    var Int=1
    var str1:String= String()
    for ( i in 0..str.length-1) {
        if (str[i] in 'a'..'z') {
            Int=str[i]-'a'+10
        } else Int=str[i] -'0'
        result = result + (Int*step(base,str.length-1-i))
    }
    return result
}
//235=2*100+3*10+5    =(2*10+3)*10+5

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
    var result = String()
    if (n < 1000) {
        if (n / 100 == 1) result += "сто"
        if (n / 100 == 2) result += "двести"
        if (n / 100 == 3) result += "триста"
        if (n / 100 == 4) result += "четыреста"
        if (n / 100 == 5) result += "пятьсот"
        if (n / 100 == 6) result += "шестьсот"
        if (n / 100 == 7) result += "семьсот"
        if (n / 100 == 8) result += "восемьсот"
        if (n / 100 == 9) result += "девятьсот"
        var p = n % 100
        if (p in 10..19) {
            if (p == 10) result += " десять"
            if (p == 11) result += " одиннадцать"
            if (p == 12) result += " двенадцать"
            if (p == 13) result += " тринадцать"
            if (p == 14) result += " четырнадцать"
            if (p == 15) result += " пятнадцать"
            if (p == 16) result += " шестнадцать"
            if (p == 17) result += " семнадцать"
            if (p == 18) result += " восемнадцать"
            if (p == 19) result += " девятнадцать"
        } else {
            p /= 10
            if (p == 2) result += " двадцать"
            if (p == 3) result += " тридцать"
            if (p == 4) result += " сорок"
            if (p == 5) result += " пятьдесят"
            if (p == 6) result += " шестьдесят"
            if (p == 7) result += " семьдесят"
            if (p == 8) result += " восемьдесят"
            if (p == 9) result += " девяносто"
            p = n % 10
            if (p == 1) result += " один"
            if (p == 2) result += " два"
            if (p == 3) result += " три"
            if (p == 4) result += " четыре"
            if (p == 5) result += " пять"
            if (p == 6) result += " шесть"
            if (p == 7) result += " семь"
            if (p == 8) result += " восемь"
            if (p == 9) result += " девять"
        }
    } else {
        var thous=n/1000
        val unit=n%1000
        var x=1
        if (thous%10==2) {
            thous=(thous/10)*10
            x=0
        }
        var str1=russian(thous)
        var str2=russian(unit)
        if ((thous%10 == 0) && (x==1)) str1+=" тысяч "
        if (thous%10 == 1) str1+=" тысяча "
        if (thous%10 in 2..4) str1+=" тысячи "
        if (thous%10 in 5..9) str1+=" тысяч "
        if (x == 0) str1+=" две тысячи "
        result=str1+str2
    }
return result.trim()
}