package lesson6.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun pointDistance() {
        assertEquals(0.0, Point(0.0, 0.0).distance(Point(0.0, 0.0)), 1e-5)
        assertEquals(5.0, Point(3.0, 0.0).distance(Point(0.0, 4.0)), 1e-5)
        assertEquals(50.0, Point(0.0, -30.0).distance(Point(-40.0, 0.0)), 1e-5)
    }

    @Test
    @Tag("Example")
    fun halfPerimeter() {
        assertEquals(6.0, Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).halfPerimeter(), 1e-5)
        assertEquals(2.0, Triangle(Point(0.0, 0.0), Point(0.0, 1.0), Point(0.0, 2.0)).halfPerimeter(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun triangleArea() {
        assertEquals(6.0, Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).area(), 1e-5)
        assertEquals(0.0, Triangle(Point(0.0, 0.0), Point(0.0, 1.0), Point(0.0, 2.0)).area(), 1e-5)
    }

    @Test
    @Tag("Example")
    fun triangleContains() {
        assertTrue(Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).contains(Point(1.5, 1.5)))
        assertFalse(Triangle(Point(0.0, 0.0), Point(0.0, 3.0), Point(4.0, 0.0)).contains(Point(2.5, 2.5)))
    }

    @Test
    @Tag("Easy")
    fun circleDistance() {
        assertEquals(0.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(1.0, 0.0), 1.0)), 1e-5)
        assertEquals(0.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(0.0, 2.0), 1.0)), 1e-5)
        assertEquals(1.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(-4.0, 0.0), 2.0)), 1e-5)
        assertEquals(2.0 * Math.sqrt(2.0) - 2.0, Circle(Point(0.0, 0.0), 1.0).distance(Circle(Point(2.0, 2.0), 1.0)), 1e-5)
    }

    @Test
    @Tag("Trivial")
    fun circleContains() {
        val center = Point(1.0, 2.0)
        assertTrue(Circle(center, 1.0).contains(center))
        assertFalse(Circle(center, 2.0).contains(Point(0.0, 0.0)))
        assertTrue(Circle(Point(0.0, 3.0), 5.01).contains(Point(-4.0, 0.0)))
    }

    @Test
    @Tag("Normal")
    fun diameter() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 4.0)
        val p3 = Point(-2.0, 2.0)
        val p4 = Point(3.0, -1.0)
        val p5 = Point(-3.0, -2.0)
        val p6 = Point(0.0, 5.0)
        assertEquals(Segment(p5, p6), diameter(p1, p2, p3, p4, p5, p6))
        assertEquals(Segment(p4, p6), diameter(p1, p2, p3, p4, p6))
        assertEquals(Segment(p3, p4), diameter(p1, p2, p3, p4))
        assertEquals(Segment(p2, p4), diameter(p1, p2, p4))
        assertEquals(Segment(p1, p4), diameter(p1, p4))
    }

    @Test
    @Tag("Easy")
    fun circleByDiameter() {
        assertEquals(Circle(Point(2.0, 1.5), 2.5), circleByDiameter(Segment(Point(4.0, 0.0), Point(0.0, 3.0))))
    }

    @Test
    @Tag("Normal")
    fun crossPoint() {
        val l3 = Line(point = Point(x = -632.0, y = -1000.0), angle = 0.8929531914571557)
        val l4 = Line(point = Point(x = -1000.0, y = -1000.0), angle = 4.9E-324)
        val p2 = Point(x = -632.0, y = -1000.0000000000001)
        val l1 = Line(point = Point(x = -999.3608407105896, y = -999.6046181381707), angle = 0.0)
        val l2 = Line(point = Point(x = -632.0, y = -999.4562234029594), angle = 0.4076829484684751)
        val p1 = Point(x = -632.3436023981205, y = -999.6046181381708)
        assertTrue(p2.distance(l3.crossPoint(l4)) < 1e-5)
        assertTrue(p1.distance(l1.crossPoint(l2)) < 1e-5)
        assertTrue(Point(2.0, 3.0).distance(Line(Point(2.0, 0.0), Math.PI / 2).crossPoint(Line(Point(0.0, 3.0), 0.0))) < 1e-5)
        assertTrue(Point(2.0, 2.0).distance(Line(Point(0.0, 0.0), Math.PI / 4).crossPoint(Line(Point(0.0, 4.0), -Math.PI / 4))) < 1e-5)
        val p = Point(1.0, 3.0)
        assertTrue(p.distance(Line(p, 1.0).crossPoint(Line(p, 2.0))) < 1e-5)

    }

    @Test
    @Tag("Normal")
    fun lineBySegment() {
        assertEquals(Line(Point(0.0, 0.0), 0.0), lineBySegment(Segment(Point(0.0, 0.0), Point(7.0, 0.0))))
        assertEquals(Line(Point(0.0, 0.0), Math.PI / 2), lineBySegment(Segment(Point(0.0, 0.0), Point(0.0, 8.0))))
        assertEquals(Line(Point(1.0, 1.0), Math.PI / 4), lineBySegment(Segment(Point(1.0, 1.0), Point(3.0, 3.0))))
    }

    @Test
    @Tag("Normal")
    fun lineByPoint() {
        assertEquals(Line(Point(1.0, 1.0), Math.PI / 4), lineByPoints(Point(1.0, 1.0), Point(3.0, 3.0)))
    }

    @Test
    @Tag("Hard")
    fun bisectorByPoints() {
        assertEquals(Line(Point(2.0, 0.0), Math.PI / 2), bisectorByPoints(Point(0.0, 0.0), Point(4.0, 0.0)))
        assertEquals(Line(Point(1.0, 2.0), 0.0), bisectorByPoints(Point(1.0, 5.0), Point(1.0, -1.0)))
    }

    @Test
    @Tag("Normal")
    fun findNearestCirclePair() {
        val c1 = Circle(Point(0.0, 0.0), 1.0)
        val c2 = Circle(Point(3.0, 0.0), 5.0)
        val c3 = Circle(Point(-5.0, 0.0), 2.0)
        val c4 = Circle(Point(0.0, 7.0), 3.0)
        val c5 = Circle(Point(0.0, -6.0), 4.0)
        assertEquals(Pair(c1, c5), findNearestCirclePair(c1, c3, c4, c5))
        assertEquals(Pair(c2, c4), findNearestCirclePair(c2, c4, c5))
        assertEquals(Pair(c1, c2), findNearestCirclePair(c1, c2, c4, c5))
    }

    @Test
    @Tag("Impossible")
    fun circleByThreePoints() {
        val a4 = Point(x = -632.0, y = -632.0)
        val b4 = Point(x = -999.5450011747622, y = -999.0993580952794)
        val c4 = Point(x = -632.0, y = -1000.0)
        val result4 = circleByThreePoints(a4, b4, c4)
        assertTrue(result4.center.distance(Point(x = -815.3227256427617, y = -815.9999999999999)) < 1e-5)
        assertEquals(259.73683169140884, result4.radius)
        val a2 = Point(x = -632.0, y = -1000.0)
        val b2 = Point(x = -632.0, y = -632.0)
        val c2 = Point(x = -999.4136814310411, y = -1000.0)
        val result3 = circleByThreePoints(a2, b2, c2)
        assertTrue(result3.center.distance(Point(x = -815.7068407155206, y = -1000.0)) < 1e-5)
        assertEquals(183.70684071552057, result3.radius, 1e-5)
        val a3 = Point(x = -999.6881956009286, y = -632.0)
        val b3 = Point(x = -999.1770833703151, y = -1000.0)
        val c3 = Point(x = -632.0, y = -632.0)
        val resultM = circleByThreePoints(a3, b3, c3)
        assertTrue(resultM.center.distance(Point(x = -815.8440978004643, y = -815.745015355827)) < 1e-5)
        assertEquals(259.9247640456365, resultM.radius, 1e-5)
        val a1 = Point(x = -632.0, y = -632.0)
        val b1 = Point(x = -999.1243063977258, y = -632.0)
        val c1 = Point(x = -632.0, y = -999.6643804587314)
        val result1 = circleByThreePoints(a1, b1, c1)
        assertTrue(result1.center.distance(Point(x = -815.562153198863, y = -1250.882319379864)) < 1e-5)
        assertEquals(645.5310909073263, result1.radius, 1e-5)
        val result2 = circleByThreePoints(Point(5.0, 0.0), Point(3.0, 4.0), Point(0.0, -5.0))
        assertTrue(result2.center.distance(Point(0.0, 0.0)) < 1e-5)
        assertEquals(5.0, result2.radius, 1e-5)
    }
}
/*
    @Test
    @Tag("Impossible")
    fun minContainingCircle() {
        val p1 = Point(0.0, 0.0)
        val p2 = Point(1.0, 4.0)
        val p3 = Point(-2.0, 2.0)
        val p4 = Point(3.0, -1.0)
        val p5 = Point(-3.0, -2.0)
        val p6 = Point(0.0, 5.0)
        val result = minContainingCircle(p1, p2, p3, p4, p5, p6)
        assertEquals(4.0, result.radius, 0.02)
        for (p in listOf(p1, p2, p3, p4, p5, p6)) {
            assertTrue(result.contains(p))
        }
    }
}
*/