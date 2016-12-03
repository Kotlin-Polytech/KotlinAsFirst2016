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
/*fun main(args: Array<String>) {
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
} */

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
val MONTH_NAME = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")

fun dateStrToDigit(str: String): String {
    var year = -1
    var month1 = -1
    var day = -1
    val parts = str.split(" ")
    if (parts.size == 3) {
        try {
            day = parts[0].toInt()
            month1 = MONTH_NAME.indexOf(parts[1]) + 1
            if (month1 == 0) return ""
            year = parts[2].toInt()
        } catch (e: NumberFormatException) {
            return ""
        }
        if ((year != -1) && (month1 != -1) && (day != -1))
            return String.format("%02d.%02d.%d", day, month1, year)
        else return ""
    } else return ""
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    var result: String = ""
    var month1 = ""
    val parts = digital.split(".")
    if (digital != (digital.filter { (it in '0'..'9') || (it == '.') })) return ""
    if (parts.size == 3) {
        try {
            result = parts[0].toInt().toString() + " "
            if (parts[1].toInt() in 1..12) {
                month1 = MONTH_NAME[parts[1].toInt() - 1]
            } else return ""
            result = result + month1 + " " + parts[2]
        } catch (e: NumberFormatException) {
            return ""
        }
        return result
    } else return ""
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
    val result = phone.filter { (it in '0'..'9') || (it == '+') }

    for (i in 0..phone.length - 1)
        if ((phone[i] != '(')
                && (phone[i] != ')')
                && (phone[i] != '-')
                && (phone[i] != ' ')
                && (phone[i] != '+')
                && (phone[i] !in '0'..'9'))
            return ""
    if (result.length > 0) {
        if (result[0] == '+') {
            for (i in 1..result.length - 1) if (result[i] !in '0'..'9') return ""
        } else {
            for (i in 0..result.length - 1) if (result[i] !in '0'..'9') return ""
        }
    } else return ""

    return result
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
    if (jumps.isEmpty())
        return -1
    for (jump in jumps) {
        if (((jump != '%') && (jump != '-') && (jump != ' ') && (jump !in '0'..'9')))
            return -1
    }
    var resultJumps = jumps.filter { (it in '0'..'9') || (it == ' ') }
    val parts = resultJumps.split(" ")
    var result = -1
    for (part in parts) {
        if (part != "")
            result = Math.max(result, part.toInt())
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
fun checkPlus(str: String): Boolean = str.any { str == "+" }

fun bestHighJump(jumps: String): Int {
    if (jumps == "") return -1
    var maxMax = -1
    try {
        val stringList = jumps.split(" ")
        if (stringList.size % 2 == 1) return -1
        for (i in 0..stringList.size - 1 step 2) {
            if ((stringList[i].toInt() > maxMax) && (checkPlus(stringList[i + 1]))) {
                maxMax = stringList[i].toInt()

            }
        }
        if (maxMax == -1) return -1 else return maxMax
    } catch (e: NumberFormatException) {
        return -1
    }

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
    var j = 0
    if (expression.isEmpty())
        throw IllegalArgumentException("Description")
    for (char in expression) {
        if ((char != '+') && (char != '-') && (char != ' ') && (char !in '0'..'9'))
            throw IllegalArgumentException("Description")
    }
    val parts = expression.split(" ")
    var result = 0
    try {
        if ((parts[0] == "-") || (parts[0] == "+"))
            j = 1
        else
            j = 2
        result += parts[0].toInt()
        for (i in j..parts.size - 1 step 2) {
            if (parts[i - 1] == "-")
                result -= parts[i].toInt()
            if (parts[i - 1] == "+")
                result += parts[i].toInt()
        }

    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
    return result
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
    var stringParts = str.toLowerCase().split(" ")
    var result = 0
    for (i in 0..stringParts.size - 2) {
        if ((stringParts[i] == stringParts[i + 1]) && (stringParts[i] != "")) return result
        result += stringParts[i].length + 1
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
    var max = -1.0
    var name = ""

    val parts1 = description.split("; ")
    if (description.isNotEmpty()) {
        try {
            for (element in parts1) {
                var part2 = element.split(" ")
                if (part2[1].toDouble() > max) {
                    name = part2[0]
                    max = part2[1].toDouble()
                }
            }

        } catch (e: NumberFormatException) {
            return ""
        }
    }
    return name
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
fun fromRoman(roman: String): Int = TODO()


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
fun computeDeviceCells(cells: Int, commands: String): List<Int> = TODO()


fun myFun(coins: String, sum: Double): List<String> {
    if (coins == "") return listOf()
    var sum = sum
    val stringList: List<String>
    var result = mutableListOf<Int>()
    try {
        stringList = coins.split(", ")
        for (i in 0..stringList.size - 1) {
            result.add(0)
            while (sum - stringList[i].toDouble() >= 0) {
                sum -= stringList[i].toDouble()
                result[i] += 1
            }
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
    var resultString = listOf<String>()
    for (i in 0..stringList.size - 1) if (result[i] != 0) resultString += result[i].toString() + " * " + stringList[i]
    return resultString
}

fun main(args: Array<String>) {
    val str = "5000, 1000, 500, 100, 50, 10, 5, 2, 1, 0.50, 0.10, 0.05, 0.01"
    val result = myFun(str, 7633.21)
    println(result)
    val str2 = ""
    val result2 = myFun(str2, 7633.21)
    println(result2)
    val str3 = "abcd"
    val result3 = myFun(str3, 123.4)
    println(result3)

}