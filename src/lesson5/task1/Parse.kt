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
    var result: String = ""
    val parts = str.split(" ")
    if (parts.size == 3) {
        try {
            if (parts[0].toInt() in 0..9) result = '0' + parts[0].toInt().toString() + "."
            else result = parts[0].toInt().toString() + "."
            result += when (parts[1]) {
                "января" -> "01"
                "февраля" -> "02"
                "марта" -> "03"
                "апреля" -> "04"
                "мая" -> "05"
                "июня" -> "06"
                "июля" -> "07"
                "августа" -> "08"
                "сентября" -> "09"
                "октября" -> "10"
                "ноября" -> "11"
                "декабря" -> "12"
                else -> return ""
            }

            result = result + "." + parts[2].toInt().toString()
        } catch (e: NumberFormatException) {
            return ""
        }
        return result
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
    val parts = digital.split(".")
    if (parts.size == 3) {
        try {
            result = parts[0].toInt().toString() + " "
            result += when (parts[1]) {
                "01" -> "января "
                "02" -> "февраля "
                "03" -> "марта "
                "04" -> "апреля "
                "05" -> "мая "
                "06" -> "июня "
                "07" -> "июля "
                "08" -> "августа "
                "09" -> "сентября "
                "10" -> "октября "
                "11" -> "ноября "
                "12" -> "декабря "
                else -> return ""
            }

            result = result + parts[2].toInt().toString()
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
    var result: String = ""
    if (phone[0].toString() == "+") {
        result += "+"
        for (i in 1..phone.length - 1) {
            if ((phone[i] == ' ') || (phone[i] == ')') || (phone[i] == '(') || (phone[i] == '-'))
                result += ""
            else
                if (phone[i] in '0'..'9')
                    result += phone[i]
                else {
                    result = ""
                    break

                }
        }
    } else
        for (i in 0..phone.length - 1) {
            if ((phone[i] == ' ') || (phone[i] == ')') || (phone[i] == '(') || (phone[i] == '-'))
                result += ""
            else
                if (phone[i] in '0'..'9')
                    result += phone[i]
                else {
                    result = ""
                    break
                }
        }

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
    for (i in 0..jumps.length - 1)
        if ((jumps == "")
                || ((jumps[i] != '%')
                && (jumps[i] != '-')
                && (jumps[i] != ' ')
                && (jumps[i] !in '0'..'9')))
            return -1
    var resultJumps = jumps.filter { (it in '0'..'9') || (it == ' ') }
    val parts = resultJumps.split(" ")
    var parts0 = listOf<Int>()
    for (i in 0..parts.size - 1) if (parts[i] != "") parts0 = parts0 + parts[i].toInt()
    var result = -1
    for (i in 0..parts0.size - 1) result = Math.max(result, parts0[i])
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
    fun checkPlus(str: String): Boolean {
        for (i in 0..str.length - 1) if (str[i] == '+') return true
        return false
    }

    if (jumps == "") return -1
    var maxMax = -1
    var maxI = -1
    var string = listOf<String>()
    try {
        string = jumps.split(" ")
        if (string.size % 2 == 1) return -1
        for (i in 0..string.size - 1 step 2) {
            if ((string[i].toInt() > maxMax) && (checkPlus(string[i + 1]) == true)) {
                maxMax = string[i].toInt()
                maxI = i
            }
        }
        if (maxI == -1) return -1 else return string[maxI].toInt()
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
    for (i in 0..expression.length - 1)
        if ((expression == "") || ((expression[i] != '+') && (expression[i] != '-') && (expression[i] != ' ') && (expression[i] !in '0'..'9')))
            return throw IllegalArgumentException("Description")
    var resultExpression = expression
    val parts = resultExpression.split(" ")
    var result = 0
    try {
        if ((parts[0] == "-") || (parts[0] == "+"))
            for (i in 1..parts.size - 1 step 2) if (parts[i - 1] == "-") result -= parts[i].toInt()
            else result += parts[i].toInt()
        else {
            result += parts[0].toInt()
            for (i in 2..parts.size - 1 step 2) if (parts[i - 1] == "-") result -= parts[i].toInt()
            else result += parts[i].toInt()
        }
    } catch (e: IllegalArgumentException) {
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
    var string = str.toLowerCase().split(" ")
    var result = 0
    for (i in 0..string.size - 2) {
        if ((string[i] == string[i + 1]) && (string[i] != "")) return result
        result += string[i].length + 1
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
    val check = Regex("""[а-яА-Я]+\s(\d)+\.\d(;\s|$)""").find(description)
    if (check == null) return ""
    val checkExcess = Regex("""[^а-яА-Я\.\s;\d]""").find(description)
    if (checkExcess != null) return ""
    val price = Regex("""(\d)+.\d""").findAll(description)
    if (price == null) return ""
    val priceList = mutableListOf<String>()
    for (i in price) {
        priceList.add(i.value)
    }
    val mostPrice = priceList.map { it.toDouble() }.sorted().last().toString()
    val result = Regex("""([а-яА-Я])+(?=\s$mostPrice)""").find(description)
    if (result == null) return ""
    return result.value.toString()
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