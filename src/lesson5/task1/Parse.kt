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
    val parts = str.split(" ")
    val listDate = mutableListOf<String>()
    if (parts.size == 3) {
        try {
            listDate.add(
            when {
                parts[0].toInt() in 1..9 -> twoDigitStr(parts[0].toInt())
                else -> parts[0]
            }
            )
            listDate.add(
            when {
                parts[1] == "января" && parts[0].toInt() < 32 -> ".01."
                parts[1] == "февраля" && parts[0].toInt() == 29 &&
                        parts[2].toInt() % 4 == 0-> ".02."
                parts[1] == "февраля" && parts[0].toInt() < 29 -> ".02."
                parts[1] == "марта" && parts[0].toInt() < 32 -> ".03."
                parts[1] == "апреля" && parts[0].toInt() < 31-> ".04."
                parts[1] == "мая" && parts[0].toInt() < 32 -> ".05."
                parts[1] == "июня" && parts[0].toInt() < 31 -> ".06."
                parts[1] == "июля" && parts[0].toInt() < 32 -> ".07."
                parts[1] == "августа" && parts[0].toInt() < 32 -> ".08."
                parts[1] == "сентября" && parts[0].toInt() < 31 -> ".09."
                parts[1] == "октября" && parts[0].toInt() < 32 -> ".10."
                parts[1] == "ноября" && parts[0].toInt() < 31 -> ".11."
                parts[1] == "декабря" && parts[0].toInt() < 32 -> ".12."
                else -> return ""
            }
            )
            listDate.add(parts[2])
        } catch (e: NumberFormatException) {
            return ""
        }
        return listDate.joinToString("")
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
    val parts = digital.split(".")
    var result = ""
    if (parts.size == 3) {
        try {
            val day = parts[0].toInt()
            if (day  !in 1..31) {
                return ""
            }
            else {
                result = day.toString()
                result += when (parts[1]) {
                    "01" -> " января "
                    "02" -> " февраля "
                    "03" -> " марта "
                    "04" -> " апреля "
                    "05" -> " мая "
                    "06" -> " июня "
                    "07" -> " июля "
                    "08" -> " августа "
                    "09" -> " сентября "
                    "10" -> " октября "
                    "11" -> " ноября "
                    "12" -> " декабря "
                    else -> return ""
                }
            }
            result += parts[2].toInt().toString()
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
    val phoneFilter = phone.filter { it != ' ' && it != '-' }
    val phoneResult = Regex("""(?:\+\d+)?(?:\(\d+\))?\d+""")
    if (!phoneResult.matches(phoneFilter)) return ""
    return phoneFilter.filter { it !in '('..')' }
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
    val parts = jumps.split(" ").filter {it != ""}
    var max = -1
    try {
        for (part in parts) {
            if (part == "%" || part == "-") continue
            else {
                val number = part.toInt()
                if (max < number) max = number
            }
        }
    } catch (e: NumberFormatException) {
        return -1
    }
    return max
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
    var maxhigh = 0
    var count = 0
    val parts = jumps.split(" ")
    try {
        for (i in 0..parts.size - 1 step 2) {
            val jumpRegular = Regex("""\d+[-+%]+""")
            if (!jumpRegular.matches(parts[i] + parts[i + 1])) return -1
            if ('+' in parts[i + 1] && parts[i].toInt() >= maxhigh) {
                maxhigh = parts[i].toInt()
                count++
            }

        }
    } catch (e: IndexOutOfBoundsException){
        return -1
    }
    if (count > 0) {
        return maxhigh
    } else {
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
    val sumstr = Regex("""(?:(\d+\s+[-+]\s+)+)?\d+""")
    try {
        if (!sumstr.matches(expression)) {
            throw IllegalArgumentException("IllegalArgumentException")
        } else {
            val parts = expression.split(" ")
            var sum = parts[0].toInt()
            for (i in 0..parts.size - 3 step 2) {
                if (parts[i + 1] == "+") {
                    sum += parts[i + 2].toInt()
                } else {
                    sum -= parts[i + 2].toInt()
                }
            }
            return sum
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("IllegalArgumentException")
    }
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
    val parts = str.toLowerCase().split(" ").filter { it != ""}
    val listOfWords = mutableListOf<String>()
    var index = 0
    for (part in parts){
        listOfWords.add(part)
    }
        for (i in 0..listOfWords.size - 2) {
            if (listOfWords[i] == listOfWords[i + 1]) {
                return index
            }
            index += listOfWords[i].length + 1
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
    val parts = description.split(" ")
    val priceRegex = Regex("""\S+\s\d+\.\d+((\;\s\S+\s\d+\.\d+)+)*""")
    var index = 0
    if (!priceRegex.matches(description)) {
        return ""
    } else {
        var max = 0.0
        for (i in 1..parts.size - 1 step 2) {
            val prise = parts[i].filter { it != ';' }.toDouble()
            if (prise >= max) {
                max = prise
                index = i - 1
            }
        }
    }
    return parts[index]
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