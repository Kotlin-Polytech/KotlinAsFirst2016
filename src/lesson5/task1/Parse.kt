@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

import lesson4.task1.decimal

val MONTHS = listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")

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
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
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
    val dateList = str.split(" ")
    if (dateList.size != 3) return ""
    try {
        val day = dateList[0].toInt()
        val month = dateList[1]
        if ((day !in 1..31) or (month !in MONTHS)) return ""
        val year = dateList[2].toInt()
        return String.format("%02d.%02d.%d", day, MONTHS.indexOf(month) + 1, year)
    } catch (e: NumberFormatException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val dateList = digital.split(".")
    if (dateList.size != 3) return ""
    try {
        val day = dateList[0].toInt()
        val month = dateList[1].toInt()
        if ((day !in 1..31) or (month !in 1..12)) return ""
        val year = dateList[2].toInt()
        return String.format("%d %s %d", day, MONTHS[month - 1], year)
    } catch (e: NumberFormatException) {
        return ""
    }
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
    var phoneWithoutSmbl = phone.filter { it != '-' && it != ' ' }
    val bool = phoneWithoutSmbl.matches(Regex("""(\+\d+)?(\(\d+\))?\d+"""))
    phoneWithoutSmbl = phoneWithoutSmbl.filter { it in '0'..'9' || it == '+' }
    if (!bool) return ""
    return phoneWithoutSmbl
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
    val clearJumps = jumps.split(" ").filter { it != "-" && it != "%" }.filter { it != "" }
    if (clearJumps.isEmpty()) return -1
    for (element in clearJumps.joinToString("")) {
        if (element !in '0'..'9') return -1
    }
    val jumpsList = clearJumps.map { it.toInt() }
    return jumpsList.max() ?: -1
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
    val jumpsList = jumps.split(" ")
    if ((jumpsList.size < 2) or (jumpsList.size % 2 != 0)) return -1
    var luckyJumps = listOf<Int>()
    var bestJump = 0
    try {
        for (i in 0..jumpsList.size - 1 step 2) {
            if (jumpsList[i + 1].matches(Regex(""".*\+.*"""))) {
                luckyJumps += jumpsList[i].toInt()
            }
        }
        bestJump = luckyJumps.max() ?: -1
    } catch (e: NumberFormatException) {
        -1
    }
    return bestJump
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
    if (!expression.matches(Regex("""\d+( (\+|\-) \d+)*"""))) throw IllegalArgumentException()
    val symbols = expression.split(" ")
    val digits = symbols.filter { it != "-" && it != "+" }
    val operations = symbols.filter { it == "-" || it == "+" }
    var result = digits[0].toInt()
    for (i in 0..digits.size - 2) {
        if (operations[i] == "-") result -= digits[i + 1].toInt() else result += digits[i + 1].toInt()
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
    val strList = str.toLowerCase().split(" ")
    var index = 0
    for (i in 0..strList.size - 2) {
        if (strList[i] == strList[i + 1]) return index
        index += strList[i].length + 1
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
    var descriptionList = description.split(";")
    val descSize = descriptionList.size
    val result:String
    if (descSize==1) {
        if (!description.matches(Regex(""".* \d+(\.\d+)?"""))) return ""
        return descriptionList[0].split(" ")[0]
    } else{
        if (!description.matches(Regex("""(.* \d+(\.\d+)?;)+ (.* \d+(\.\d+)?)"""))) return ""
        descriptionList = descriptionList.map {it.trim()}
        var products = listOf<String>()
        var prices = listOf<Double>()
        var productAndPrice:List<String>
        for (element in descriptionList){
            productAndPrice = element.split(" ")
            products += productAndPrice[0]
            try{
                prices += productAndPrice[1].toDouble()
            } catch (e: NumberFormatException) {return ""}
        }
        result = products[prices.indexOf(prices.max())]
    }
    return result
/*-----------------------------ЭТОТ КОД ТОЖЕ РАБОТАЕТ--------------------------------
    val descriptionList = description.split(" ")
    val descSize = descriptionList.size
    if (descSize % 2 != 0) return ""
    var x = ";"
    var i = 1
    var prices = listOf<Double>()
    while (i < descSize) {
        if (i == descSize - 1) {
            if (descriptionList[i].matches(Regex("""\d*\.?\d*"""))) {
                prices += descriptionList[i].toDouble()
            } else return ""
        } else if (descriptionList[i].matches(Regex("""\d*\.?\d*\;"""))) {
            prices += Regex("""\;""").replace(descriptionList[i], "").toDouble()
        } else return ""
        i += 2
    }
    val maxPrice = prices.max() ?: return ""
    if ((prices.indexOf(maxPrice) + 1) * 2 == descSize) x = ""
    if (descriptionList[i - 2].matches(Regex("""\d*"""))) return descriptionList[descriptionList.indexOf("${maxPrice.toInt()}$x") - 1]
    return descriptionList[descriptionList.indexOf("$maxPrice$x") - 1]
------------------------------------------------------------------------------------*/
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