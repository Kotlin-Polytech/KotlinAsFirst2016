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
        if (other.center.distance(center) > other.radius + radius) return other.center.distance(center) - radius - other.radius
        else return 0.0
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
    var max = -1.0
    var max1 = Point(0.0, 0.0)
    var max2 = Point(0.0, 0.0)
    if (points.size < 2) throw IllegalArgumentException()
    for (point1 in points) {
        for (point2 in points) {
            if (point1.distance(point2) > max) {
                max = point1.distance(point2)
                max1 = point1
                max2 = point2
            }
        }
    }
    return Segment(max1, max2)
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val center = Point((diameter.end.x + diameter.begin.x) / 2, (diameter.end.y + diameter.begin.y) / 2)
    val radius = center.distance(diameter.end)
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
    fun crossPoint(other: Line): Point { //еще не решена
        val x = when {
            (Math.cos(angle) - Math.cos(Math.PI / 2) < 1e-10) -> point.x
            (Math.cos(other.angle) == Math.cos(Math.PI / 2)) -> other.point.x
            else -> (other.point.y - point.y - other.point.x * Math.tan(other.angle) + point.x * Math.tan(angle)) /
                    (Math.tan(angle) - Math.tan(other.angle))
        }
        val y = when {
            (Math.abs(Math.cos(angle)) - Math.cos(Math.PI / 2) < 1e-10) ->
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
fun lineBySegment(s: Segment): Line {
   return if (s.begin.x == s.end.x) Line(s.begin, Math.PI / 2)
       else Line(s.begin, Math.atan((s.end.y - s.begin.y) / (s.end.x - s.begin.x)))
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
    return if (Math.abs(a.x - b.x) > 1e-10) Line(Point((a.x + b.x) / 2, (a.y + b.y) / 2), Math.PI / 2 + Math.atan(Math.abs((a.y - b.y) / (a.x - b.x))))
    else Line(Point((a.x + b.x) / 2, (a.y + b.y) / 2), 0.0)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size < 2) throw IllegalArgumentException()
    var min = circles[0].distance(circles[1])
    var min1 = Pair(circles[0], circles[1])
    for (circle1 in circles) {
        for(circle2 in circles) {
            if ((circle1.distance(circle2) < min) && (circle1 != circle2)) {
                min = circle1.distance(circle2)
                min1 = Pair(circle1, circle2)
            }
        }
    }
    return min1
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



