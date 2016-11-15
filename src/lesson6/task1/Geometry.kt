@file:Suppress("UNUSED_PARAMETER")

package lesson6.task1

import lesson1.task1.sqr
import java.lang.Math.*
import java.util.*

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
        val distanceCenter = center.distance(other.center)
        val radiusSum = radius + other.radius
        val result = distanceCenter - radiusSum
        if (result < 0.0) return 0.0
        return result
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = p.distance(center) <= radius
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
    if (points.size < 2) throw IllegalArgumentException()
    var distance = 0.0
    var result = Segment(points[0], points[0])
    for (pointOne in points) {
        for (pointTwo in points) {
            val thisDistance = pointOne.distance(pointTwo)
            if (thisDistance > distance) {
                distance = thisDistance
                result = Segment(pointOne, pointTwo)
            }
        }
    }
    return result
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun segmentCenter(segment: Segment): Point {
    val newPointX = (segment.end.x + segment.begin.x) / 2
    val newPointY = (segment.end.y + segment.begin.y) / 2
    return Point(newPointX, newPointY)
}

fun circleByDiameter(diameter: Segment): Circle {
    val result = Circle(segmentCenter(diameter), segmentCenter(diameter).distance(diameter.end))
    return result
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
    fun crossPoint(other: Line): Point = TODO()
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val rad: Double
    if (s.begin.x == s.end.x) {
        rad = PI / 2
    } else {
        val cathetusOne = s.end.y - s.begin.y
        val cathetusTwo = s.end.x - s.begin.x
        rad = atan(cathetusOne / cathetusTwo)
    }
    return Line(s.begin, rad)
}


/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line = lineBySegment(Segment(a, b))

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val line = Segment(a, b)
    val angle = lineBySegment(line).angle
    if (angle >= PI / 2) return Line(segmentCenter(line), angle - PI / 2)
    return Line(segmentCenter(line), angle + PI / 2)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */

fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) throw IllegalArgumentException()
    var distance = circles[0].distance(circles[1])
    var result = Pair(circles[0], circles[1])
    for (i in 0..circles.size - 2) {
        for (j in i + 1..circles.size - 1) {
            val thisDistance = circles[i].distance(circles[j])
            if (thisDistance < distance) {
                distance = thisDistance
                result = Pair(circles[i], circles[j])
            }
        }
    }
    return result
}

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
/*
fun minContainingCircle(vararg points: Point): Circle {
    if(points.size < 2) return Circle(points[0], 0.0)
    //ищем прямоугольник, содержащий все точки
    var maxY = points[0].y
    var minY = maxY
    var maxX = points[0].x
    var minX = maxX
    for (point in points){
        val pointY = point.y
        val pointX = point.x
        if(pointY > maxY) maxY = pointY
        if(pointY < minY) minY = pointY
        if(pointX > maxX) maxX = pointX
        if(pointX < minX) minX = pointX
    }

    //находим центр прямоугольника
    val centerY = (maxY + minY) / 2
    val centerX = (maxX + minX) / 2
    val center = Point(centerX,centerY)
    var radius = 0.0
    //находим самую отдаленную от центра точку
    for (point in points){
        val distance = center.distance(point)
        if(distance>radius) radius = distance
    }
    return Circle(center, radius)
}
*/
