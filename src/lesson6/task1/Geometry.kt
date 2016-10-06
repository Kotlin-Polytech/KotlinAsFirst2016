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
        val distanceCenter = Math.sqrt(sqr(center.x - other.center.x) + sqr(center.y - other.center.y))
        val radiusSum = radius + other.radius
        if (distanceCenter - radiusSum <= 0.0) return 0.0
        else return distanceCenter - radiusSum
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = Math.sqrt(sqr(center.x - p.x) + sqr(center.y - p.y)) <= radius
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

    var maxDiameter = -1.0
    var maxSegm = Segment(points.first(), points.last())

    if (points.size > 2)
    for (i in 0..points.size - 2) {
        for (j in i+1..points.size - 1) {
            val localDiameter = Math.sqrt(sqr(points[i].x - points[j].x)+sqr(points[i].y - points[j].y))
            if (localDiameter > maxDiameter) {
                maxDiameter = localDiameter
                maxSegm = Segment(points[i], points[j])
            }
        }
    }

    return maxSegm
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val x1 = diameter.begin.x
    val x2 = diameter.end.x
    val y1 = diameter.begin.y
    val y2 = diameter.end.y

    val radius = Math.sqrt(sqr(x1 - x2) + sqr(y1 - y2))/2.0
    val x3 = Math.min(x1, x2) + (Math.max(x1, x2) - Math.min(x1, x2))/2.0
    val y3 = Math.min(y1, y2) + (Math.max(y1, y2) - Math.min(y1, y2))/2.0
    return Circle(Point(x3, y3), radius)
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
        val angle2 = other.angle
        /**
         *
         * y - y1 = (x - x1) * tan(angle1)
         * (x - x1) * tan(angle1) + y1 = (x - x2) * tan(angle2) + y2
         * x * (tan(angle1) - tan(angle2)) - x1 * tan(angle1) + x2 * tan(angle2) = y2 - y1
         * x = ( y2 - y1 + x1 * tan(angle1) - x2 * tan(angle2) ) / ( tan(angle1) - tan(angle2) )
         * y = (x - x1) * tan(angle1) + y1
         */

        val x3 = (x1*Math.tan(angle) - x2*Math.tan(angle2) + y2 - y1)/(Math.tan(angle) - Math.tan(angle2))
        val y3 = (x3-x1)*Math.tan(angle) + y1

        return Point(x3, y3)
    }
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line = TODO()

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line = TODO()

/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line = TODO()

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

