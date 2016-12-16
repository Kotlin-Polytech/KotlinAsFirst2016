@file:Suppress("UNUSED_PARAMETER")

package lesson6.task1

import lesson1.task1.sqr
import lesson3.task1.sin

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
        val circles = center.distance(other.center) - radius - other.radius
        if (circles > 0) {
            return circles
        } else return 0.0
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = center.distance(p) <= radius
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
    var point1 = Point(0.0, 0.0)
    var point2 = Point(0.0, 0.0)
    var maxdistance = point1.distance(point2)
    if (points.size <= 1) {
        throw IllegalArgumentException("IllegalArgumentException")
    } else {
        for (i in 0..points.size - 1) {
            for (j in (i + 1)..points.size - 1) {
                val distansePoints = points[i].distance(points[j])
                if (distansePoints > maxdistance) {
                    maxdistance = distansePoints
                    point1 = points[i]
                    point2 = points[j]
                }
            }
        }
        return Segment(point1, point2)
    }
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle = Circle(
        Point(
                (diameter.begin.x + diameter.end.x) / 2,
                (diameter.begin.y + diameter.end.y) / 2),
        diameter.begin.distance(diameter.end) / 2)

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
        val x = when {
            angle == Math.PI / 2 -> point.x
            other.angle == Math.PI / 2 -> other.point.x
            else -> (other.point.y - point.y - other.point.x * Math.tan(other.angle) + point.x * Math.tan(angle)) /
                    (Math.tan(angle) - Math.tan(other.angle))
        }
        val y = when {
            angle == Math.PI / 2 ->
                (x - other.point.x) * Math.tan(other.angle) + other.point.y
            else -> (x - point.x) * Math.tan(angle) + point.y
        }
        return Point(x, y)
    }
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line = Line(s.begin, Math.atan((s.end.y - s.begin.y) / (s.end.x - s.begin.x)))

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
fun bisectorByPoints(a: Point, b: Point): Line = Line(
        Point(
                (a.x + b.x) / 2,
                (a.y + b.y) / 2),
        Math.atan((b.y - a.y) / (b.x - a.x)) + Math.PI / 2)

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) {
        throw IllegalArgumentException("IllegalArgumentException")
    } else {
        var nearestCircle1 = circles[0]
        var nearestCircle2 = circles[1]
        var minDistance = circles[0].center.distance(circles[1].center) - circles[0].radius - circles[1].radius
        for (i in 0..circles.size - 1) {
            for (j in (i + 1)..circles.size - 1) {
                if (minDistance == 0.0) return Pair(nearestCircle1, nearestCircle2)
                val lengthCircles = circles[i].distance(circles[j])
                if (lengthCircles < minDistance) {
                    minDistance = lengthCircles
                    nearestCircle1 = circles[i]
                    nearestCircle2 = circles[j]
                }
            }
        }
        return Pair(nearestCircle1, nearestCircle2)
    }
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
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val bisectorAB = bisectorByPoints(a, b)
    val bisectorAC = bisectorByPoints(a, c)
    return Circle(bisectorAB.crossPoint(bisectorAC),
            a.distance(bisectorAB.crossPoint(bisectorAC)))
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
    var maxTriangleSquare = 0.0
    var pointTriangleA = Point(0.0, 0.0)
    var pointTriangleB = Point(0.0, 0.0)
    var pointTriangleC = Point(0.0, 0.0)
    if (points.size < 1) throw IllegalArgumentException("IllegalArgumentException")
    if (points.size == 1) return Circle(points[0], 0.0)
    if (points.size == 2) return circleByDiameter(Segment(points[0], points[1]))
    var maxSegment = pointTriangleA.distance(pointTriangleB)
    for (i in 0..points.size - 1) {
        for (j in (i + 1)..points.size - 1) {
            val distancePoints = points[i].distance(points[j])
            if (distancePoints > maxSegment) {
                maxSegment = distancePoints
                pointTriangleA = points[i]
                pointTriangleB = points[j]
            }
        }
    }
    for (i in 0..points.size - 1) {
        val TriangleSquareOfPoints = Triangle(pointTriangleA, pointTriangleB, points[i]).area()
        if (TriangleSquareOfPoints > maxTriangleSquare) {
            maxTriangleSquare = TriangleSquareOfPoints
            pointTriangleC = points[i]
        }
    }
    if (sqr(pointTriangleA.distance(pointTriangleC)) +
            sqr(pointTriangleB.distance(pointTriangleC)) < sqr(maxSegment)) {
        return circleByDiameter(Segment(pointTriangleB, pointTriangleA))
    } else {
        return circleByThreePoints(pointTriangleA, pointTriangleB, pointTriangleC)
    }
}