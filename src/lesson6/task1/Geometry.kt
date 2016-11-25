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
        val length = sqrt(sqr(center.x - other.center.x) + sqr(center.y - other.center.y)) - (radius + other.radius)
        if (length > 0) return length else return 0.0
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean {
        val length = sqrt(sqr(center.x - p.x) + sqr(center.y - p.y))
        return when (length) {
            in 0..radius.toInt() -> true
            else -> false
        }
    }
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    fun length(): Double = sqrt(sqr(begin.x - end.x) + sqr(begin.y - end.y))
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {

    val convexHull = if (points.size >= 3) points.asList() else convexHull(points)

    val segments = mutableListOf<Segment>()
    for (i in convexHull.withIndex()) {
        for (z in convexHull.withIndex()) { // можно ли начать итерацию не с самого начала листа, а с определёенного индекса (в данном случае с i индекса)?
            if (z.index > i.index) segments.add(Segment(i.value, z.value))
        }
    }

    return segments.maxBy { it.length() }!!

}


fun convexHull(points: Array<out Point>): List<Point> {
    val p0 = points.minWith(Comparator { o1, o2 -> if (o1.y == o2.y) (o1.x - o2.x).toInt() else (o1.y - o2.y).toInt() })!!
    val pointsList = points.sortedWith(Comparator { o1, o2 -> -(polarAngle(p0, o1, o2).toInt()) }).toMutableList()
    val convexHullList = mutableListOf<Point>()
    convexHullList.add(p0)
    convexHullList.add(pointsList[1])

    for (i in 2..points.size - 1) {
        while (polarAngle(convexHullList[convexHullList.size - 2], convexHullList[convexHullList.size - 1], pointsList[i]) < 0)
            convexHullList.removeAt(convexHullList.size - 1)
        convexHullList.add(pointsList[i])
    }

    return convexHullList
}


fun polarAngle(p0: Point, p1: Point, p2: Point): Double {
    val a = (p1.x - p0.x) * (p2.y - p1.y) - (p1.y - p0.y) * (p2.x - p1.x)
    if (a == 0.0) return Segment(p0, p2).length() - Segment(p0, p1).length() else return a
}


/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val xCenter = (diameter.begin.x + diameter.end.x) / 2
    val yCenter = (diameter.begin.y + diameter.end.y) / 2
    val center = Point(xCenter, yCenter)
    val radius = diameter.length() / 2

    return (Circle(center, radius))
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
        val k = when {
            (tan(angle) == tan(PI / 2)) || (tan(angle) == tan(-PI / 2)) -> 0.0
            else -> tan(angle)
        } // угловой коэффициент первой прямой
        val c = -point.x * k + point.y //штука первой прямой
        val m = when {
            (tan(other.angle) == tan(PI / 2)) || (tan(other.angle) == tan(-PI / 2)) -> 0.0
            else -> tan(other.angle)
        } // угловой коэфф второй прямой
        val b = -other.point.x * m + other.point.y //штука второй прямой

        if ((k == 0.0) && (m == 0.0)) return when {
            (tan(angle) == tan(PI / 2)) || (tan(angle) == tan(-PI / 2)) -> Point(point.x, b)
            else -> Point(other.point.x, c)
        }

        val X = (b - c) / (k - m)
        val Y = when {
            k == 0.0 -> (X - other.point.x) * tan(other.angle) + other.point.y
            m == 0.0 -> (X - point.x) * tan(angle) + point.y
            else -> (X - point.x) * tan(angle) + point.y

        }
        return Point(X, Y)
    }
}


/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val tan = (s.begin.y - s.end.y) / (s.begin.x - s.end.x)
    val angle = atan(tan)
    return (Line(s.begin, abs(angle)))
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line {
    return lineBySegment(Segment(a, b))
}

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val angle = lineByPoints(a, b).angle
    val center = Point((a.x + b.x) / 2, (a.y + b.y) / 2)
    val asin = when {
        angle == 0.0 -> PI / 2
        angle == PI / 2 || angle == -PI / 2 -> 0.0
        angle < PI / 2 -> -PI / 2 - angle
        else -> PI / 2 - angle

    }
    return Line(center, asin)
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

