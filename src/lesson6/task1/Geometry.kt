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
        return (p.distance(center) <= radius)
    }
}


/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    fun length(): Double = sqrt(sqr(begin.x - end.x) + sqr(begin.y - end.y))
    fun middle(): Point = Point((begin.x + end.x) / 2, (begin.y + end.y) / 2)
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
        var x = (this.point.x * Math.tan(this.angle) - other.point.x * Math.tan(other.angle) + other.point.y - this.point.y) /
                (Math.tan(this.angle) - Math.tan(other.angle))
        var y = (this.point.y * cos(angle) / sin(angle) - other.point.y * cos(other.angle) / sin(other.angle) + other.point.x - this.point.x) /
                (cos(angle) / sin(angle) - cos(other.angle) / sin(other.angle))

        if (((angle == PI / 2) || (angle == -PI / 2)) && ((other.angle == 0.0) || (other.angle == PI / 2) || (other.angle == -PI / 2))) {
            x = this.point.x
            y = other.point.y
            return Point(x, y)
        }

        if (((other.angle == PI / 2) || (other.angle == -PI / 2)) && ((angle == 0.0) || (angle == PI / 2) || (angle == -PI / 2))) {
            x = other.point.x
            y = this.point.y
            return Point(x, y)
        }

        if ((angle == PI / 2) || (angle == -PI / 2))
            x = (y - other.point.y) / tan(other.angle) + other.point.x
        if ((other.angle == PI / 2) || (other.angle == -PI / 2))
            x = (y - point.y) / tan(angle) + point.x

        if ((angle == 0.0) || (angle == PI) || (angle == -PI))
            y = (x - other.point.x) / tan(other.angle) + other.point.y
        if ((other.angle == 0.0) || (other.angle == PI) || (other.angle == -PI))
            y = (x - point.x) / tan(angle) + point.y
        /*
        val y = when {
            (x != point.x) -> (x - point.x) * tan(angle) + point.y
            else -> (x - other.point.x) * tan(other.angle) + other.point.y
        }
        */
        return Point(x, y)
    }
}


/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val cat1 = s.end.y - s.begin.y
    val cat2 = s.end.x - s.begin.x
    val angle = atan(cat1 / cat2)
    if (angle == -0.0) abs(angle)
    return (Line(s.begin, angle))
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
    val center = Point((a.x + b.x) / 2, (a.y + b.y) / 2)
    val angle = atan2(b.y - a.y, b.x - a.x) + PI / 2
    return Line(center, angle)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) throw IllegalArgumentException()

    val distances = mutableMapOf<Pair<Circle, Circle>, Double>()

    for (i in circles.withIndex())
        circles.withIndex()
                .filter { i.index < it.index }
                .forEach { distances.put(Pair(i.value, it.value), i.value.distance(it.value)) }

    return distances.minBy { it.value }!!.key
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
    val fBisector = bisectorByPoints(a, b)
    val sBisector = bisectorByPoints(a, c)
    val center = fBisector.crossPoint(sBisector)
    val radius = Segment(center, a).length()

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
    when (points.size) {
        0 -> throw IllegalArgumentException()
        1 -> return Circle(Point(points[0].x, points[0].y), 0.0)
        2 -> return Circle(Point((points[0].x - points[1].x) / 2, (points[0].y - points[1].y) / 2), Segment(points[0], points[1]).length() / 2)
        else -> {
            val s = diameter2(points)
            val radius = sqrt(sqr(s.begin.x - s.end.x) + sqr(s.begin.y - s.end.y)) / 2
            val middle = Point((s.begin.x + s.end.x) / 2, (s.begin.y + s.end.y) / 2)
            return Circle(middle, radius)
        }
    }
}

fun diameter2(points: Array<out Point>): Segment {

    val convexHull = points.asList()

    val segments = mutableListOf<Segment>()
    for (i in convexHull.withIndex()) {
        for (z in convexHull.withIndex()) { // можно ли начать итерацию не с самого начала листа, а с определёенного индекса (в данном случае с i индекса)?
            if (z.index > i.index) segments.add(Segment(i.value, z.value))
        }
    }

    return segments.maxBy { sqrt(sqr(it.begin.x - it.end.x) + sqr(it.begin.y - it.end.y)) / 2 }!!
}