package lesson1.task1

//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Tag
//import org.junit.jupiter.api.Test
import org.junit.Assert.*
import org.junit.Test

class Tests {
    @Test
    fun sqr() {
        assertEquals(0.0, sqr(0.0), 1e-5)
        assertEquals(4.0, sqr(2.0), 1e-5)
        assertEquals(9.0, sqr(-3.0), 1e-5)
    }

    @Test
    fun discriminant() {
        assertEquals(0.0, discriminant(0.0, 0.0, 0.0), 1e-5)
        assertEquals(0.0, discriminant(1.0, -2.0, 1.0), 1e-5)
        assertEquals(1.0, discriminant(1.0, 3.0, 2.0), 1e-5)
    }

    @Test
    fun sqRoot() {
        assertEquals(1.0, sqRoot(1.0, -2.0, 1.0), 1e-5)
        assertEquals(-3.0, sqRoot(1.0, 6.0, 9.0), 1e-5)
    }

    @Test
    fun quadraticRootProduct() {
        assertEquals(1.0, quadraticRootProduct(1.0, -2.0, 1.0), 1e-5)
        assertEquals(9.0, quadraticRootProduct(1.0, 6.0, 9.0), 1e-5)
        assertEquals(2.0, quadraticRootProduct(1.0, 3.0, 2.0), 1e-5)
    }

    @Test
    fun seconds() {
        assertEquals(30035, seconds(8, 20, 35))
        assertEquals(86400, seconds(24, 0, 0))
        assertEquals(13, seconds(0, 0, 13))
    }

    @Test
    fun lengthInMeters() {
        assertEquals(18.98, lengthInMeters(8, 2, 11), 1e-2)
        assertEquals(2.13, lengthInMeters(1, 0, 0), 1e-2)
    }

    @Test
    fun angleInRadian() {
        assertEquals(0.63256, angleInRadian(36, 14, 35), 1e-5)
        assertEquals(Math.PI / 2.0, angleInRadian(90, 0, 0), 1e-5)
    }

    @Test
    fun trackLength() {
        assertEquals(5.0, trackLength(3.0, 0.0, 0.0, 4.0), 1e-5)
        assertEquals(1.0, trackLength(0.0, 1.0, -1.0, 1.0), 1e-5)
        assertEquals(1.41, trackLength(1.0, 1.0, 2.0, 2.0), 1e-2)
    }

    @Test
    fun takeDigit() {
        assertEquals(8, takeDigit(3801,3))
        assertEquals(1, takeDigit(100,3))
        assertEquals(0, takeDigit(1000,3))
        assertEquals(-1, takeDigit(3801,-2))
        assertEquals(-1, takeDigit(1000,100))
        assertEquals(8, takeDigit(8))
        assertEquals(3, takeDigit(1453))
    }

    @Test
    fun travelMinutes() {
        assertEquals(216, travelMinutes(9, 25, 13, 1))
        assertEquals(1, travelMinutes(21, 59, 22, 0))
    }

    @Test
    fun accountInThreeYears() {
        assertEquals(133.1, accountInThreeYears(100, 10), 1e-2)
        assertEquals(1.0, accountInThreeYears(1, 0), 1e-2)
        assertEquals(104.0, accountInThreeYears(13, 100), 1e-2)
    }

    @Test
    fun numberRevert() {
        assertEquals(874, numberRevert(478))
        assertEquals(201, numberRevert(102))
        assertEquals(87431, numberRevert(13478))
        assertEquals(0, numberRevert(0))
        assertEquals(3, numberRevert(3))
        assertEquals(-7,numberRevert(-7))
        assertEquals(-45296,numberRevert(-69254))
        assertEquals(-111, numberRevert(-111))
        assertEquals(17571, numberRevert(17571))
        assertEquals(123456789, numberRevert(987654321))
    }
}