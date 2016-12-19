@file:Suppress("UNUSED_PARAMETER")

package lesson6.task1

import lesson1.task1.sqr

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
    fun distance(other: Circle): Double = if (center.distance(other.center) <= radius + other.radius) 0.0
    else center.distance(other.center) - radius - other.radius

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
    var a = points[0].distance(points[1])
    var b = Segment(points[0], points[1])
    if (points.size < 2) throw IllegalAccessException()
    else {
        for (i in 0..points.size - 2) {
            for (j in i + 1..points.size - 1) {
                if (points[i].distance(points[j]) > a) {
                    a = points[i].distance(points[j])
                    b = Segment(points[i], points[j])
                }
            }
        }
        return b
    }
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val a = Point((diameter.begin.x + diameter.end.x) / 2, (diameter.begin.y + diameter.end.y) / 2)
    return Circle(a, diameter.begin.distance(diameter.end) / 2)
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
        var x = (point.x * Math.tan(angle) - point.y - other.point.x * Math.tan(other.angle) + other.point.y) / (Math.tan(angle) - Math.tan(other.angle))
        x = x.toInt().toDouble()
        val y = (x - other.point.x) * Math.tan(other.angle) + other.point.y
        return Point(x, y)
    }
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val a = Math.atan((s.end.y - s.begin.y) / (s.end.x - s.begin.x))
    val p = Point(s.begin.x, s.begin.y)
    return Line(p, a)
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
    val s = lineByPoints(a, b)
    var k: Double
    if (Math.abs(s.angle) == Math.PI / 2) k = 0.0
    else if (s.angle == 0.0) k = Math.PI / 2
    else k = Math.atan(1 / Math.tan(s.angle))
    return Line(Point((a.x + b.x) / 2, (a.y + b.y) / 2), k)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) throw IllegalArgumentException()
    else {
        var a = circles[0].distance(circles[1])
        var b = Pair(circles[0], circles[1])
        for (i in 0..circles.size - 2)
            for (j in i + 1..circles.size - 1) {
                if (circles[i].distance(circles[j]) < a) {
                    a = circles[i].distance(circles[j])
                    b = Pair(circles[i], circles[j])
                }
            }
        return b
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

