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
        var distance = center.distance(other.center) - (radius + other.radius)
        if (distance < 0) return 0.0
        return distance
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
    var max = 0.0
    if (points.size < 2) throw IllegalArgumentException()
    var maxSeg = Segment(points[0], points[1])
    for (i in 0..points.size - 1) {
        for (n in 0..points.size - 1) {
            if (points[i].distance(points[n]) > max) {
                max = points[i].distance(points[n])
                maxSeg = Segment(points[i], points[n])
            }
        }
    }
    return maxSeg
}


/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle = TODO()

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
        val a1 = -Math.sin(angle)
        val a2 = -Math.sin(other.angle)
        val b1 = Math.cos(angle)
        val b2 = Math.cos(other.angle)
        val c1 = a1 * point.x + b1 * point.y
        val c2 = a2 * other.point.x + b2 * other.point.y
        val denominator = a1 * b2 - a2 * b1
        if (denominator == 0.0) println("Прямые параллельны или совпадают")
        val x = (c1 * b2 - c2 * b1) / denominator
        val y = (a1 * c2 - a2 * c1) / denominator
        return Point(x, y)
    }
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val k = (s.end.y - s.begin.y) / (s.end.x - s.begin.x)
    return Line(s.begin, Math.atan(k))
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
    val xMidpoint = (a.x + b.x) / 2
    val yMidpoint = (a.y + b.y) / 2
    val angle = (Math.atan((b.y - a.y) / (b.x - a.x)) + Math.PI / 2)
    return Line(Point(xMidpoint, yMidpoint), angle)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    var circle1 = circles[0]
    var circle2 = circles[1]
    var min = circle1.distance(circle2)
    if (circles.size < 2) throw IllegalArgumentException()
    for (i in 0..circles.size - 1) {
        for (n in i + 1..circles.size - 1) {
            if (circles[i].distance(circles[n]) < min) {
                min = circles[i].distance(circles[n])
                circle1 = circles[i]
                circle2 = circles[n]
            }
        }
    }
    return Pair(circle1, circle2)
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
    val bisectorAc = bisectorByPoints(a, b)
    val bisectorBc = bisectorByPoints(b, c)
    val crossPoint = bisectorAc.crossPoint(bisectorBc)
    val radius = crossPoint.distance(c)
    return Circle(crossPoint, radius)
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
fun minContainingCircle(vararg points: Point): Circle = TODO()

