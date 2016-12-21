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
        if (center.distance(other.center) > radius + other.radius) {
            return center.distance(other.center) - (radius + other.radius)
        }
        return 0.0
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean {
        if (center.distance(p) <= radius) return true
        return false
    }
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
    var thisDistance:Double
    var result = Segment(points[0], points[0])
    var alreadyDone = 0
    for (i in 0..points.size - 2) {
        for (j in alreadyDone..points.size - 1) {
            thisDistance = points[i].distance(points[j])
            if (thisDistance > distance) result = Segment(points[i], points[j])
        }
        alreadyDone ++
    }
    return result
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val center = Point((diameter.begin.x + diameter.end.x)/2, (diameter.begin.y + diameter.end.y)/2)
    val radius = diameter.begin.distance(diameter.end)/2
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
        val ang = other.angle

        if (Math.tan(angle) == Math.tan(ang)) throw IllegalArgumentException()

        val x3 = (x1*Math.tan(angle) - x2*Math.tan(ang) + y2 - y1)/(Math.tan(angle) - Math.tan(ang))

        val y3 = when {
            (Math.abs(Math.cos(ang)) < 1.0E-5) -> ((x3 - x1) * Math.tan(angle) + y1)
            else -> ((x3 - x2) * Math.tan(ang) + y2)
        }

        return Point(x3, y3)
    }
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val angle = Math.atan2((s.end.y - s.begin.y) , (s.end.x - s.begin.x))
    return Line (s.begin, angle)
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line {
    val leftP:Point
    val rightP:Point
    if (a.x < b.x) {
        leftP = a
        rightP = b
    } else {
        leftP = b
        rightP = a
    }
    var angle = Math.atan2((leftP.y - rightP.y) , (leftP.x - rightP.x))
    while (angle < -(Math.PI/2)) angle += Math.PI
    while (angle > Math.PI/2) angle -= Math.PI
    return Line (leftP, angle)
}

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val center = Point((a.x + b.x)/2, (a.y + b.y)/2)
    val angle = Math.atan2((a.y - b.y) , (a.x - b.x)) - Math.PI/2
    return Line (center, angle)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) throw IllegalArgumentException()
    var minDist = circles[0].distance(circles[1])
    var result = Pair(circles[0], circles[1])
    for (i in 0..circles.size - 2) {
        for (j in i + 1..circles.size - 1) {
            if (minDist > circles[i].distance(circles[j])) {
                minDist = circles[i].distance(circles[j])
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
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    if (lineByPoints(a, b).angle == lineByPoints(a, c).angle) throw IllegalArgumentException()
    val center = bisectorByPoints(a, b).crossPoint(bisectorByPoints(a, c))
    return Circle(center, center.distance(a))
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

