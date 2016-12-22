package lesson5.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun timeStrToSeconds() {
        assertEquals(36000, timeStrToSeconds("10:00:00"))
        assertEquals(41685, timeStrToSeconds("11:34:45"))
        assertEquals(86399, timeStrToSeconds("23:59:59"))
    }

    @Test
    @Tag("Example")
    fun timeSecondsToStr() {
        assertEquals("10:00:00", timeSecondsToStr(36000))
        assertEquals("11:34:45", timeSecondsToStr(41685))
        assertEquals("23:59:59", timeSecondsToStr(86399))
    }

    @Test
    @Tag("Normal")
    fun dateStrToDigit() {
        assertEquals("02.11.1", dateStrToDigit("02 ноября 1"))
        assertEquals("02.11.0", dateStrToDigit("02 ноября 0"))
        assertEquals("29.02.2000", dateStrToDigit("29 февраля 2000"))
        assertEquals("15.04.0", dateStrToDigit("15 апреля 0"))
        assertEquals("03.04.2011", dateStrToDigit("3 апреля 2011"))
        assertEquals("15.07.2016", dateStrToDigit("15 июля 2016"))
        assertEquals("", dateStrToDigit("3 мартобря 1918"))
        assertEquals("18.11.2018", dateStrToDigit("18 ноября 2018"))
        assertEquals("", dateStrToDigit("23"))

    }

    @Test
    @Tag("Normal")
    fun dateDigitToStr() {
        assertEquals("1 февраля 0", dateDigitToStr("01.02.0"))
        assertEquals("29 февраля 0", dateDigitToStr("29.02.0"))
        assertEquals("", dateDigitToStr("29.02.2011"))
        assertEquals("31 июля 2016", dateDigitToStr("31.07.2016"))
        assertEquals("", dateDigitToStr("01.02.20.19"))
        assertEquals("", dateDigitToStr("28.00.2000"))
        assertEquals("3 апреля 2011", dateDigitToStr("03.04.2011"))
        assertEquals("", dateDigitToStr("ab.cd.ef"))
    }


    @Test
    fun formatStringForFlattenPhoneNumber() {


        assertEquals(22, formatStringForFlattenPhoneNumber("12 --  34- 5 -- 67 -98"))
        assertEquals(11, formatStringForFlattenPhoneNumber("+12 (3) 4-5"))
        assertEquals(18, formatStringForFlattenPhoneNumber("+7 (921) 123-45-67"))
    }

    @Test
    @Tag("Hard")
    fun flattenPhoneNumber() {
        assertEquals("", flattenPhoneNumber("ab-123"))
        assertEquals("", flattenPhoneNumber("134_+874"))
        assertEquals("123456798", flattenPhoneNumber("12 --  34- 5 -- 67 -98"))
        assertEquals("+12345", flattenPhoneNumber("+12 (3) 4-5"))
        assertEquals("+79211234567", flattenPhoneNumber("+7 (921) 123-45-67"))
    }

    @Test
    fun comparison() {
        assertEquals(false, comparison("5555555555555535555555","5555555555555555555555"))
        assertEquals(false, comparison("1850900345","2147483646"))
        assertEquals(false, comparison("703","717"))
        assertEquals(true, comparison("2147483647","2111733685"))
    }

    @Test
    @Tag("Normal")
    fun bestLongJump() {
        assertEquals(2147483647, bestLongJump("2111733685 - 1416685634 % - - 2147483647 % - 2147483647 0 % - - 1621706585 2147483647 % - - % - 2147483647 - 1232759151 % - 1698612230 - 1 - - 0 - - % 903387998 % - 2147483647 - 1 - - - - - 2147483647 % - 1 678737769 - 833442610 2120434075 2147483647 - 2147483647 - % - % 0 % - - - 1250855939 - - - - % 2072596296 -"))
        assertEquals(2147483646, bestLongJump("2147483646 - 6482632  %% 237997  -  -1850900345"))
        assertEquals(2147483647, bestLongJump("2147483647 - %- 7  - 2147483647 -%%  "))
        assertEquals(2147483647, bestLongJump("2147483647 - - 0 - 1981326223 - - - - % - 2147483647 % 2147483647 2147483647 - - 1 - 2147483647 438948647 % 1 2147483647 1346186929 0 - 818627160 - - 0 - 1 886660334 - 0 - - - 0 - - - 2046133814 % % 1454535385 - 1321681263 % 1055997316 - - 2147483647 - - 1 - 876496039 2147483647 838007933 - - - 2147483647 0 % % - 488486158 - 2141770098 - 7643567 - % - - - % 2147483647 - % 1 939172242 - - % 1741957538 2147483647 0 2147483647 - 1 748161028 1 - - 582067512 % 1436100682 - 1 - - - - % - - 2147483647 2147483647 - - 0 1931040731 1 0 - - - 1258199928 - 1 - - 2092209724 2147483647 - - 2147483647 - 0 - 175014414 % % 1 - - 561595651 - 203253245 2147483647 - 533626306 - 1057699305 % - % - 1998436976 - 1850900345"))
        assertEquals(-1, bestLongJump("700 + 700"))
        assertEquals(717, bestLongJump("706 % 400 - 717"))
        assertEquals(717, bestLongJump("700 717 703 %"))
    }

    @Test
    @Tag("Hard")
    fun bestHighJump() {
        assertEquals(226, bestHighJump("226 +"))
        assertEquals(-1, bestHighJump("???"))
        assertEquals(230, bestHighJump("220 + 224 %+ 228 %- 230 + 232 %%- 234 %"))
    }

    @Test
    @Tag("Hard")
    fun plusMinus() {
        assertEquals(0, plusMinus("0"))
        assertEquals(4, plusMinus("2 + 2"))
        assertEquals(6, plusMinus("2 + 31 - 40 + 13"))
        assertEquals(-1, plusMinus("0 - 1"))
    }

    @Test
    @Tag("Hard")
    fun firstDuplicateIndex() {
        assertEquals(-1, firstDuplicateIndex("Привет"))
        assertEquals(9, firstDuplicateIndex("Он пошёл в в школу"))
        assertEquals(40, firstDuplicateIndex("Яблоко упало на ветку с ветки оно упало на на землю"))
        assertEquals(9, firstDuplicateIndex("Мы пошли прямо Прямо располагался магазин"))
    }

    @Test
    @Tag("Hard")
    fun mostExpensive() {
        assertEquals("", mostExpensive(""))
        assertEquals("Курица", mostExpensive("Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9"))
        assertEquals("Вино", mostExpensive("Вино 255.0"))
    }

    @Test
    @Tag("Hard")
    fun fromRoman() {
        assertEquals(1, fromRoman("I"))
        assertEquals(3000, fromRoman("MMM"))
        assertEquals(1978, fromRoman("MCMLXXVIII"))
        assertEquals(694, fromRoman("DCXCIV"))
        assertEquals(49, fromRoman("XLIX"))
        assertEquals(-1, fromRoman("Z"))
    }

    @Test
    @Tag("Hard")
    fun computeDeviceCells() {
        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1), computeDeviceCells(10, "+>+>+>+>+"))
        assertEquals(listOf(-1, -1, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-"))
        assertEquals(listOf(1, 1, 1, 1, 1, 0, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]"))
        assertEquals(listOf(0, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0),
                computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++{--< <[<] >+[>+] >++}"))
    }
}