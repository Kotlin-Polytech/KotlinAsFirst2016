@file:Suppress("UNUSED_PARAMETER")
package lesson6.task1

import lesson1.task1.sqr
import java.lang.Math.*
import lesson2.task1.triangleKind

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = sqrt(sqr(x - other.x) + sqr(y - other.y))
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
        return sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
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

    fun type(): TriangleType {
        return when (triangleKind(a.distance(b), b.distance(c), c.distance(a))) {
            0 -> TriangleType.ACUTE
            1 -> TriangleType.RECTANGLE
            2 -> TriangleType.OBTUSE
            else -> TriangleType.NOT_A_TRIANGLE
        }
    }
}

enum class TriangleType {
    ACUTE, RECTANGLE, OBTUSE, NOT_A_TRIANGLE
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
        return if (center.distance(other.center) - (radius + other.radius) < 0.0) 0.0
        else center.distance(other.center) - (radius + other.radius)
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = center.distance(p) <= radius

    fun desposition(other: Circle): CirclesDisposition {
        val temp: Double = center.distance(other.center) - radius - other.radius
        return when {
            temp > 0.0                                              -> CirclesDisposition.BEYOND
            center.distance(other.center) + other.radius <= radius  -> CirclesDisposition.INCLUDE
            center.distance(other.center) + radius < other.radius   -> CirclesDisposition.INSIDE
            temp < 0.0                                              -> CirclesDisposition.CROSSING
            else                                                    -> CirclesDisposition.TOUCHING
        }
    }
}

enum class CirclesDisposition {
    BEYOND, TOUCHING, CROSSING, INSIDE, INCLUDE
}
/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    fun length(): Double = begin.distance(end)
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    if (points.size < 2) throw IllegalArgumentException("")
    else if (points.size == 2) return Segment(points[0], points[1])
    else {
        var maxDistance: Double = 0.0
        var maxSegment: Segment = Segment(points[0], points[1])
        for (i in 0..(points.size - 2)) {
            for (j in (i + 1)..(points.size - 1)) {
                if (points[i].distance(points[j]) > maxDistance) {
                    maxDistance = points[i].distance(points[j])
                    maxSegment = Segment(points[i], points[j])
                }
            }
        }
        return maxSegment
    }
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val center: Point = Point((diameter.end.x + diameter.begin.x)/2, (diameter.end.y + diameter.begin.y)/2)
    val radius: Double = diameter.length()/2
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
        val x1 = point.x
        val y1 = point.y
        val x2 = other.point.x
        val y2 = other.point.y
        val k1 = tan(angle)
        val k2 = tan(other.angle)
        val x3 = (y1 - y2 + k2 * x2 - k1 * x1) / (k2 - k1)
        val y3 = y1 + k1 * (y2 - y1 + k2 * (x1 - x2)) / (k1 - k2)
        return Point(x3, y3)
    }
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line = lineByPoints(s.begin, s.end)

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line = Line(a, atan((b.y - a.y) / (b.x - a.x)))

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val point: Point = Point(a.x / 2 + b.x / 2, a.y / 2 + b.y / 2)
    var angle: Double = lineByPoints(a, b).angle
    if (angle < PI / 2) angle += PI / 2 else angle -= PI / 2
    return Line(point, angle)
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
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val center: Point = bisectorByPoints(a, b).crossPoint(bisectorByPoints(a, c))
    val radius: Double = center.distance(a)
    return Circle(center, radius)
}

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
fun minContainingCircle(vararg points: Point): Circle {
    if (points.isEmpty()) throw IllegalArgumentException("")
    else if (points.size == 1) return Circle(points[0], 0.0)
    else if (points.size == 2) return circleByDiameter(Segment(points[0], points[1]))
    else {
        val diameter: Segment = diameter(*points)
        var maxArea: Double = 0.0
        var maxTriangle: Triangle = Triangle(diameter.begin, diameter.end, points[0])
        for (i in points) {
            val temp: Triangle = Triangle(diameter.begin, diameter.end, i)
            if (temp.area() > maxArea) {
                maxTriangle = temp
                maxArea = temp.area()
            }
        }
        return  if (maxTriangle.type() == TriangleType.ACUTE) {
                    circleByThreePoints(maxTriangle.a, maxTriangle.b, maxTriangle.c)
                }
                else circleByDiameter(diameter)
    }
}

