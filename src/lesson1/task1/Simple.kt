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
    // Решаем x^2 - 3*x + 2 = 0
    val x1x2 = quadraticRootProduct(1.0, -3.0, 2.0)
    println("Root product: $x1x2")
}

/**
 * Тривиальная
 *
 * Пользователь задает время в часах, минутах и секундах, например, 8:20:35.
 * Рассчитать время в секундах, прошедшее с начала суток (30035 в данном случае).
 */
fun minutesToSeconds(minutes: Int): Int = minutes*60
fun hoursToMinutes(hours: Int): Int = hours*60
fun seconds(hours: Int, minutes: Int, seconds: Int): Int {
    return minutesToSeconds(hoursToMinutes(hours))+ minutesToSeconds(minutes)+seconds
}

/**
 * Тривиальная
 *
 * Пользователь задает длину отрезка в саженях, аршинах и вершках (например, 8 саженей 2 аршина 11 вершков).
 * Определить длину того же отрезка в метрах (в данном случае 18.98).
 * 1 сажень = 3 аршина = 48 вершков, 1 вершок = 4.445 см.
 */
fun sagenesToArshins(sagenes: Int): Int = sagenes*3
fun arshinsToVershoks(arshins: Int): Int = arshins*16
fun vershoksToMeters(vershoks: Int): Double = vershoks*0.04445
fun lengthInMeters(sagenes: Int, arshins: Int, vershoks: Int): Double {
    return vershoksToMeters(arshinsToVershoks(sagenesToArshins(sagenes)+arshins)+vershoks)
}

/**
 * Тривиальная
 *
 * Пользователь задает угол в градусах, минутах и секундах (например, 36 градусов 14 минут 35 секунд).
 * Вывести значение того же угла в радианах (например, 0.63256).
 */
fun angleInRadian(grad: Int, min: Int, sec: Int): Double {
    return (grad*60*60+min*60+sec)*(PI/(180*60*60))
}

/**
 * Тривиальная
 *
 * Найти длину отрезка, соединяющего точки на плоскости с координатами (x1, y1) и (x2, y2).
 * Например, расстояние между (3, 0) и (0, 4) равно 5
 */
fun trackLength(x1: Double, y1: Double, x2: Double, y2: Double): Double {
    return sqrt(pow(x2-x1,2.0)+pow(y2-y1,2.0))
}

/**
 * Простая
 *
 * Пользователь задает целое число, большее 100 (например, 3801).
 * Определить третью цифру справа в этом числе (в данном случае 8).
 */
fun thirdDigit(number: Int, digit: Int = 3): Int {
    val degree: Int = digit-1
    return (number/ pow(10.0,degree.toDouble()).toInt())%10
}

/**
 * Простая
 *
 * Поезд вышел со станции отправления в h1 часов m1 минут (например в 9:25) и
 * прибыл на станцию назначения в h2 часов m2 минут того же дня (например в 13:01).
 * Определите время поезда в пути в минутах (в данном случае 216).
 */
fun travelMinutes(hoursDepart: Int, minutesDepart: Int, hoursArrive: Int, minutesArrive: Int): Int {
    return (hoursToMinutes(hoursArrive)+minutesArrive)-(hoursToMinutes(hoursDepart)+minutesDepart)
}

/**
 * Простая
 *
 * Человек положил в банк сумму в s рублей под p% годовых (проценты начисляются в конце года).
 * Сколько денег будет на счету через 3 года (с учётом сложных процентов)?
 * Например, 100 рублей под 10% годовых превратятся в 133.1 рубля
 */
fun accountInThreeYears(initial: Int, percent: Int): Double {
    var result: Double = initial.toDouble()
    for(i in 1..3)
    {
        result*=(1+(percent.toDouble()/100))
    }
    return result
}

/**
 * Простая
 *
 * Пользователь задает целое трехзначное число (например, 478).
 *Необходимо вывести число, полученное из заданного перестановкой цифр в обратном порядке (например, 874).
 */
fun numberRevert(number: Int): Int {
    var result: Int = 0
    var mutableNumber: Int = number
    do{
        result=result*10+mutableNumber%10
        mutableNumber/=10
    }while(mutableNumber!=0)
    return result
}
