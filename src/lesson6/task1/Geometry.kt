@file:Suppress("UNUSED_PARAMETER")
package lesson6.task1

import lesson1.task1.sqr
import java.lang.Math.*
/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = Math.sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками
 */
data class Triangle(val a: Point, val b: Point, val c: Point) {
    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return Math.sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        if (center.distance(other.center) - (radius + other.radius) > 0)
            return center.distance(other.center) - (radius + other.radius)
        else
            return 0.0
    }




    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = sqr(p.x - center.x) + sqr(p.y - center.y) <= sqr(radius)
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point)

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    var maxDistance = 0.0
    var pointX1 = Point(0.0, 0.0)
    var pointX2 = Point(0.0, 0.0)
    if (points.size < 2)
        throw IllegalArgumentException()
    for (i in 0..points.size - 1 step 1){
        for (j in i + 1..points.size - 1 step 1) {
            if (points[i].distance(points[j]) > maxDistance){
                maxDistance = points[i].distance(points[j])
                pointX1 = points[i]
                pointX2 = points[j]
            }
        }
    }
    return Segment(pointX1, pointX2)
 }

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val radius = diameter.begin.distance(diameter.end) / 2
    val center = Point ((diameter.end.x + diameter.begin.x) / 2, (diameter.end.y + diameter.begin.y) / 2)
    return Circle(center, radius)
}

/**
 * Прямая, заданная точкой и углом наклона (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 */
data class Line(val point: Point, val angle: Double) {
    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */

    fun crossPoint(other: Line): Point {
        val x =(point.x * tan(angle) - other.point.x * tan(other.angle) + other.point.y - point.y) / (tan(angle) -tan(other.angle))
        val y = x * tan(other.angle) - other.point.x * tan(other.angle) + other.point.y
        return Point(x,y)
    }

}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val point = Point(s.begin.x, s.begin.y)

    if ((s.end.x - s.begin.x) != 0.0)
        return Line(point, atan((s.end.y - s.begin.y) / (s.end.x - s.begin.x)))
    else
        if  ((s.end.y - s.begin.y) != 0.0)
            return Line(point, PI / 2)
        else
            return Line(point, Double.NaN)
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line =
        if ((b.x - a.x) != 0.0)
            Line(Point(a.x, a.y), atan( (b.y - a.y) / (b.x - a.x)))
        else
            if ((b.y - a.y) != 0.0)
                Line(Point(a.x, a.y), PI / 2)
            else
                Line(Point(a.x, a.y), Double.NaN)

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    if (b.x - a.x == 0.0)
        return Line(Point((b.x + a.x) / 2 , (b.y + a.y) / 2), 0.0)
    if (b.y - a.y == 0.0)
        return Line(Point((b.x + a.x) / 2 , (b.y + a.y) / 2), Math.PI / 2)
    else
        return Line (Point((b.x + a.x) / 2 , (b.y + a.y) / 2), atan( (- 1) * 1 / ((b.y - a.y) / (b.x - a.x))))
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> = TODO()

/**
 * Очень сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */

fun circleByThreePoints(a: Point, b: Point, c: Point): Circle = TODO()






/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun minContainingCircle(vararg points: Point): Circle = TODO()

