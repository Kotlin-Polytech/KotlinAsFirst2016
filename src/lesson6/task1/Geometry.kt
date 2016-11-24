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
    fun distance(other: Circle): Double {
        val result = center.distance(other.center) - (radius + other.radius)
        if (result <= 0) return 0.0
        return result
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
    var otr = Segment(points[0], points[0])
    var result1 = -0.0
    for (i in 0..points.size - 2) {
        for (j in 1..points.size - 1) {
            var result = points[i].distance(points[j])
            if (result1 < result) {
                result1 = result
                otr = Segment(points[i], points[j])
            }
        }
    }
    if (points.size < 2) throw IllegalArgumentException()
    return otr
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val center = Point(((diameter.begin.x + diameter.end.x) / 2), ((diameter.begin.y +
            diameter.end.y) / 2))
    val radius = (diameter.begin.distance(diameter.end)) / 2
    val circle = Circle(center, radius)
    return circle
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
        val a1 = Math.tan(angle)
        val b1 = -point.x * a1 + point.y
        val a2 = Math.tan(other.angle)
        val b2 = -other.point.x * a2 + other.point.y
        val x = (b2 - b1) / (a1 - a2)
        val y = when {
            (angle == Math.PI / 2 || angle == Math.PI / -2) -> other.point.y
            (other.angle == Math.PI / 2 || other.angle == Math.PI / -2) -> point.y
            else -> (x - point.x) * a1 + point.y
        }
        return Point(x, y)
    }
}


/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    var ugl = 0.0
    if (s.begin != s.end) {
        ugl = Math.atan((s.end.y - s.begin.y) / (s.end.x - s.begin.x))
    }
    return Line(Point(s.begin.x, s.begin.y), ugl)
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
    var angle = Math.PI - Math.atan((a.x - b.x) / (a.y - b.y))
    if (a.x == b.x) angle = 0.0
    else if (a.y == b.y) angle = Math.PI / 2
    val distance1: Double
    val distance2: Double
    when {
        a.x > b.x -> distance1 = a.x - Math.abs(a.x - b.x) / 2
        else -> distance1 = b.x - Math.abs(a.x - b.x) / 2
    }
    when {
        a.y > b.y -> distance2 = a.y - Math.abs(a.y - b.y) / 2
        else -> distance2 = b.y - Math.abs(a.y - b.y) / 2
    }
    return Line(Point(distance1, distance2), angle)

}    /**
     * Средняя
     *
     * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
     * Если в списке менее двух окружностей, бросить IllegalArgumentException
     */
    fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
        var otr = Pair(circles[0], circles[0])
        var result1 = -0.0
        if (circles.size < 2) throw IllegalArgumentException()
        for (i in 0..circles.size - 2) {
            for (j in 1..circles.size - 1) {
                var result = circles[i].distance(circles[j])
                if (result1 < result) {
                    result1 = result
                    otr = Pair(circles[i], circles[j])
                }
            }
        }
        return otr
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
    fun circleByThreePoints(a: Point, b: Point, c: Point): Circle{
               val center = bisectorByPoints(a, b).crossPoint(bisectorByPoints(a, c))
               val radius = center.distance(a)
        return Circle(center, radius)}

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

