@file:Suppress("UNUSED_PARAMETER")

package lesson5.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    val mounths = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val parts = str.split(" ")
    if (parts.size < 3) return ""

    val day: Int
    val mounth: Int
    val year: Int

    try {
        day = parts[0].toInt()
        mounth = mounths.indexOf(parts[1]) + 1
        if (mounth == 0) return ""
        year = parts[2].toInt()
    } catch (e: NumberFormatException) {
        return ""
    }

    return String.format("%02d.%02d.%d", day, mounth, year)
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val mounths = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
    val parts = digital.split(".")
    if (parts.size != 3) return ""
    val day: Int
    val mounth: Int
    val year: Int

    try {
        day = parts[0].toInt()
        mounth = parts[1].toInt()
        year = parts[2].toInt()
    } catch (e: NumberFormatException) {
        return ""
    }

    if (mounth < 1) return ""

    return String.format("%d %s %d", day, mounths[mounth - 1], year)
}

/**
 * Сложная
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    val allowedChars = listOf(' ', '-', '+', '(', ')')
    val intChars = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    var newPhone = ""

    for (i in phone) {
        if (i !in allowedChars && i !in intChars) return ""
        if (newPhone.length == 0 && i == '+') newPhone = "+"
        if (i in intChars) newPhone += i
    }

    return newPhone
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var result = -1
    val intChars = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    val allowedChars = listOf('-', ' ', '%')
    var strNum = ""

    try {

        for (i in jumps) {
            if (i !in intChars && i !in allowedChars) return -1
            if (i in intChars) strNum += i
            else {
                if (strNum.length > 0) result = Math.max(result, strNum.toInt())
                strNum = ""
            }
        }

        if (strNum.length > 0) result = Math.max(result, strNum.toInt())
    } catch (e: NumberFormatException) {
        return -1
    }

    return result
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val intChars = listOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    val allowedChars = listOf('-', ' ', '%', '+')

    var result = -1
    var jump = -1
    var strJump = ""

    for (i in jumps) {
        if (i !in intChars && i !in allowedChars) return -1

        if (i in intChars) {
            if (jump != -1) jump = -1
            strJump += i
        } else if (strJump.length > 0) {
            jump = strJump.toInt()
            strJump = ""
        }

        if (i == '+' && jump != -1) result = Math.max(result, jump)
    }

    return result
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (expression.length == 0) throw IllegalArgumentException()

    val listSigns = listOf("-", "+")

    var result = 0
    var lastNum = -1
    var lastSign = 1

    val expList = expression.split(' ')

    for (i in 0..expList.size-1) {
        if (expList[i] !in listSigns) {
            try {
                lastNum = expList[i].toInt()
            }
            catch (e: NumberFormatException) {
                throw IllegalArgumentException()
            }
            result += lastSign*lastNum
        }
        else if (i != 0 && expList[i-1] !in listSigns) {
            if (expList[i] == "-") lastSign = -1
            else lastSign = 1
        }
        else throw IllegalArgumentException()
    }

    return if (lastNum != -1) result else throw IllegalArgumentException()
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val parts = str.split(" ")
    var lastWord = ""
    var count = 0

    for (part in parts) {
        if (part.toUpperCase() == lastWord.toUpperCase() && part != "") return count - part.length - 1
        count += part.length + 1
        lastWord = part
    }

    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */
fun mostExpensive(description: String): String {
    val parts = description.split("; ")
    var maxPrice = -1.0
    var curPrice: Double
    var result = ""

    if (parts.isEmpty()) return ""

    for (part in parts) {
        val couple = part.split(" ")

        try {
            curPrice = couple[1].toDouble()
        } catch (e: NumberFormatException) {
            return ""
        } catch (e: IndexOutOfBoundsException) {
            return ""
        }

        if (curPrice > maxPrice) {
            result = couple[0]
            maxPrice = curPrice
        }
    }

    return result
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val mapRoman = hashMapOf("I" to 1, "IV" to 4, "V" to 5, "IX" to 9, "X" to 10, "XL" to 40,
            "L" to 50, "XC" to 90, "C" to 100, "CD" to 400, "D" to 500, "CM" to 900, "M" to 1000)

    var i = 0
    var result = 0
    while (i < roman.length) {
        if (roman.length - i - 1 > 0) {
            val romanStr = roman.substring(i, i + 2)
            if (romanStr in mapRoman) {
                val mapValue = mapRoman[romanStr]
                if (mapValue != null) {
                    result += mapValue
                    i += 2
                    continue
                }
            }
        }
        val romanStr = roman[i].toString()
        if (romanStr in mapRoman) {
            val mapValue = mapRoman[romanStr]
            if (mapValue != null) {
                result += mapValue
                i++
                continue
            }
        } else return -1
    }

    return result
}

/**
 * Сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за следующей командой ']';
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за предыдущей командой '[';
 *	{ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за следующей командой '}';
 *	} - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за предыдущей командой '{';
 * (комбинации [] и {} имитируют циклы)
 *
 * Пробел кодирует пустую команду.
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] { } не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после выполнения всех команд.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 */
fun computeDeviceCells(cells: Int, commands: String): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 1..cells) list.add(0)
    var iterator = cells / 2
    var strIterator = 0
    if (commands.isEmpty()) return list

    fun checkOp(iter: Int, op: Char): Int {
        val sign: Int
        if (op == '{' || op == '[') sign = -1 else sign = 1
        var i = iter + sign
        while (i in 0..commands.length - 1 && commands[i] != op) i += sign
        if (commands[i] == op) return i
        else throw IllegalArgumentException()
    }

    while (true) {
        when (commands[strIterator]) {
            '>' -> iterator++
            '<' -> iterator--
            '+' -> list[iterator]++
            '-' -> list[iterator]--
            '[' -> {
                if (list[iterator] == 0) {
                    strIterator = checkOp(strIterator, ']')
                }
            }
            ']' -> {
                if (list[iterator] != 0) {
                    strIterator = checkOp(strIterator, '[')
                }
            }
            '{' -> {
                if (list[iterator] == 0) {
                    strIterator = checkOp(strIterator, '}')
                }
            }
            '}' -> {
                if (list[iterator] != 0) {
                    strIterator = checkOp(strIterator, '{')
                }
            }
            ' ' -> {
            }
            else -> throw IllegalArgumentException()
        }
        if (iterator !in 0..list.size-1) throw IllegalStateException()

        if (strIterator < commands.length - 1) strIterator++
        else return list
    }
}