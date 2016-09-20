@file:Suppress("UNUSED_PARAMETER")
package lesson1.task1

import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление квадрата вещественного числа
 */
fun sqr(x: Double) = x * x

/**
 * Пример
 *
 * Вычисление дискриминанта квадратного уравнения
 */
fun discriminant(a: Double, b: Double, c: Double) = sqr(b) - 4 * a * c

/**
 * Пример
 *
 * Поиск одного из корней квадратного уравнения
 */
fun sqRoot(a: Double, b: Double, c: Double) = (-b + sqrt(discriminant(a, b, c))) / (2 * a)

/**
 * Пример
 *
 * Поиск произведения корней квадратного уравнения
 */
fun quadraticRootProduct(a: Double, b: Double, c: Double): Double {
    val sd = sqrt(discriminant(a, b, c))
    val x1 = (-b + sd) / (2 * a)
    val x2 = (-b - sd) / (2 * a)
    return x1 * x2 // Результат
}

/**
 * Пример главной функции
 */
fun main(args: Array<String>) {
    println("fun seconds")
    println("30035, seconds(8, 20, 35) - " + seconds(8, 20, 35))
    println("86400, seconds(24, 0, 0) - " + seconds(24, 0, 0))
    println("13, seconds(0, 0, 13) - " + seconds(0, 0, 13))
    println("========================")
    println("fun lengthInMeters")
    println("18.98, lengthInMeters(8, 2, 11) - " + lengthInMeters(8, 2, 11))
    println("2.13, lengthInMeters(1, 0, 0) - " + lengthInMeters(1, 0, 0))
    println("========================")
    println("fun angleInRadian")
    println("0.63256, angleInRadian(36, 14, 35) - " + angleInRadian(36, 14, 35))
    println("Math.PI / 2.0, angleInRadian(90, 0, 0) - " + angleInRadian(90, 0, 0))
    println("========================")
    println("fun trackLength")
    println("5.0, trackLength(3.0, 0.0, 0.0, 4.0) - " + trackLength(3.0, 0.0, 0.0, 4.0))
    println("1.0, trackLength(0.0, 1.0, -1.0, 1.0) - " + trackLength(0.0, 1.0, -1.0, 1.0))
    println("1.41, trackLength(1.0, 1.0, 2.0, 2.0) - " + trackLength(1.0, 1.0, 2.0, 2.0))
    println("========================")
    println("fun thirdDigit")
    println("8, thirdDigit(3801) - " + thirdDigit(3801))
    println("1, thirdDigit(100) - " + thirdDigit(100))
    println("0, thirdDigit(1000) - " + thirdDigit(1000))
    println("========================")
    println("fun travelMinutes")
    println("216, travelMinutes(9, 25, 13, 1) - " + travelMinutes(9, 25, 13, 1))
    println("1, travelMinutes(21, 59, 22, 0) - " + travelMinutes(21, 59, 22, 0))
    println("========================")
    println("fun accountInThreeYears")
    println("133.1, accountInThreeYears(100, 10) - " + accountInThreeYears(100, 10))
    println("1.0, accountInThreeYears(1, 0) - " + accountInThreeYears(1, 0))
    println("104.0, accountInThreeYears(13, 100) - " + accountInThreeYears(13, 100))
    println("========================")
    println("fun numberRevert")
    println("874, numberRevert(478) - " + numberRevert(478))
    println("201, numberRevert(102) - " + numberRevert(102))
    println("_______________________________")
}

/**
 * Тривиальная
 *
 * Пользователь задает время в часах, минутах и секундах, например, 8:20:35.
 * Рассчитать время в секундах, прошедшее с начала суток (30035 в данном случае).
 */
fun seconds(hours: Int, minutes: Int, seconds: Int): Int = seconds + minutes*60 + hours*3600

/**
 * Тривиальная
 *
 * Пользователь задает длину отрезка в саженях, аршинах и вершках (например, 8 саженей 2 аршина 11 вершков).
 * Определить длину того же отрезка в метрах (в данном случае 18.98).
 * 1 сажень = 3 аршина = 48 вершков, 1 вершок = 4.445 см.
 */
fun lengthInMeters(sagenes: Int, arshins: Int, vershoks: Int): Double =
        (vershoks*4.445 + arshins*4.445*16 + sagenes*48*4.445)/100

/**
 * Тривиальная
 *
 * Пользователь задает угол в градусах, минутах и секундах (например, 36 градусов 14 минут 35 секунд).
 * Вывести значение того же угла в радианах (например, 0.63256).
 */
fun angleInRadian(grad: Int, min: Int, sec: Int): Double =
        (sec/3600.0+min/60.0+grad)/180*Math.PI
/**
 * Тривиальная
 *
 * Найти длину отрезка, соединяющего точки на плоскости с координатами (x1, y1) и (x2, y2).
 * Например, расстояние между (3, 0) и (0, 4) равно 5
 */
fun trackLength(x1: Double, y1: Double, x2: Double, y2: Double): Double =
        sqrt(sqr(x2-x1)+sqr(y2-y1))

/**
 * Простая
 *
 * Пользователь задает целое число, большее 100 (например, 3801).
 * Определить третью цифру справа в этом числе (в данном случае 8).
 */
fun thirdDigit(number: Int): Int = number/100%10



/**
 * Простая
 *
 * Поезд вышел со станции отправления в h1 часов m1 минут (например в 9:25) и
 * прибыл на станцию назначения в h2 часов m2 минут того же дня (например в 13:01).
 * Определите время поезда в пути в минутах (в данном случае 216).
 */
fun travelMinutes(hoursDepart: Int, minutesDepart: Int, hoursArrive: Int, minutesArrive: Int): Int =
        (minutesArrive + 60*hoursArrive) - (minutesDepart + 60*hoursDepart)

/**
 * Простая
 *
 * Человек положил в банк сумму в s рублей под p% годовых (проценты начисляются в конце года).
 * Сколько денег будет на счету через 3 года (с учётом сложных процентов)?
 * Например, 100 рублей под 10% годовых превратятся в 133.1 рубля
 */
fun accountInThreeYears(initial: Int, percent: Int): Double =
        initial*(1+percent/100.0)*(1+percent/100.0)*(1+percent/100.0)

/**
 * Простая
 *
 * Пользователь задает целое трехзначное число (например, 478).
 *Необходимо вывести число, полученное из заданного перестановкой цифр в обратном порядке (например, 874).
 */
fun numberRevert(number: Int): Int = number%10*100 + number/10%10*10 + number/100%10