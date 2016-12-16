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
    var segment = Segment(points[0], points[0])
    var maxDistance = -1.0
    for (i in 0..points.size - 2) {
        for (j in 1..points.size - 1) {
            val distance = points[i].distance(points[j])
            if (maxDistance < distance) {
                maxDistance = distance
                segment = Segment(points[i], points[j])
            }
        }
    }
    if (points.size < 2) throw IllegalArgumentException()
    return segment
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
    val circle = Circle(center, (diameter.begin.distance(diameter.end)) / 2)
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
        val a1 = -Math.sin(angle)
        val a2 = Math.cos(angle)
        val b1 = -a1 * point.x - a2 * point.y
        val a3 = -Math.sin(other.angle)
        val a4 = Math.cos(other.angle)
        val b2 = -a3 * other.point.x - a4 * other.point.y
        val x = (b2 * a2 - b1 * a4) / (a1 * a4 - a2 * a3)
        val y = (b1 * a3 - b2 * a1) / (a1 * a4 - a2 * a3)
        return Point(x, y)
    }
}


/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    var angle = 0.0
    if (s.begin != s.end) {
        angle = Math.atan2(s.end.y - s.begin.y, s.end.x - s.begin.x)
    }
    return Line(Point(s.begin.x, s.begin.y), angle)
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
    val x = a.x + (b.x - a.x) / 2
    val y = a.y + (b.y - a.y) / 2
    return Line(Point(x, y), (lineByPoints(a, b).angle + Math.PI / 2) % Math.PI)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    var minDistance = -1.0
    var pair = Pair(circles[0], circles[1])
    if (circles.size < 2) throw IllegalArgumentException()
    for (i in 0..circles.size - 2)
        for (j in i + 1..circles.size - 1) {
            val distance = circles[i].distance(circles[j])
            if ((distance < minDistance) || (minDistance == -1.0)) {
                pair = Pair(circles[i], circles[j])
                minDistance = distance
            }
        }
    return pair
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
    val center = bisectorByPoints(a, b).crossPoint(bisectorByPoints(a, c))
    val radius = center.distance(a)
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
fun minContainingCircle(vararg points: Point): Circle = TODO()

