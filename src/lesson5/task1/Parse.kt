@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

import lesson4.task1.elem
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
    val parts = str.split(" ")
    var answ = ""
    var lenght = 0
    for (part in parts)
        lenght += 1
    if (lenght != 3) return ""
    if (parts[0].length > 2) return ""
    answ += parts[0]
    if (answ.length == 1) answ = "0" + parts[0]
    answ += "."
    when (parts[1]) {
        "января" -> answ += "01."
        "февраля" -> answ += "02."
        "марта" -> answ += "03."
        "апреля" -> answ += "04."
        "мая" -> answ += "05."
        "июня" -> answ += "06."
        "июля" -> answ += "07."
        "августа" -> answ += "08."
        "сентября" -> answ += "09."
        "октября" -> answ += "10."
        "ноября" -> answ += "11."
        "декабря" -> answ += "12."
        else -> return ""
    }
    if (parts[2].length > 5) return ""
            answ += parts[2]
    return answ
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    var answ = ""
    var lenght = 0
    val abc = "1234567890."
    var iNeedIt: Int = 0
    for (i in 0..digital.length - 1) {
        for (j in 0..abc.length - 1)
            if (digital[i] == abc[j]) iNeedIt = 1
        if (iNeedIt != 1) return ""
        iNeedIt = 0
    }
    val parts = digital.split(".")
    for (part in parts)
        lenght += 1
    if (lenght != 3) return ""
    if (parts[0][0] == abc[9]) answ += parts[0][1] + " "
    else answ += parts[0] + " "
    when (parts[1]){
        "01" -> answ += "января "
        "02" -> answ += "февраля "
        "03" -> answ += "марта "
        "04" -> answ += "апреля "
        "05" -> answ += "мая "
        "06" -> answ += "июня "
        "07" -> answ += "июля "
        "08" -> answ += "августа "
        "09" -> answ += "сентября "
        "10" -> answ += "октября "
        "11" -> answ += "ноября "
        "12" -> answ += "декабря "
        else -> return ""
    }
    answ += parts[2]
    return answ
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
    var forError = 0
    val iNeedIt = "1234567890+-() "
    if ((phone.length == 1) && (phone[0] == iNeedIt[14])) return ""
    for (i in 0..phone.length - 1) {
        for (j in 0..iNeedIt.length - 1) {
            if (phone[i] == iNeedIt[j]) forError += 1
        }
        if (forError != 1) return ""
        forError = 0
    }
    val parts = phone.split(" ")
    var strAnsw = ""
    if (phone[0] == iNeedIt[11]) strAnsw += "+"
    for (part in parts) {
       for (i in 0..part.length - 1) {
           if ((part[i] != iNeedIt[11]) && (part[i] != iNeedIt[12]) && (part[i] != iNeedIt[13]) && (part[i] != iNeedIt[14])) strAnsw += part[i]
       }
    }
    return strAnsw
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
    var result = ""
    var answ = 0
    var forError = 0
    val iNeedIt = "0123456789-% "
    for (i in 0..jumps.length - 1) {
        for (j in 0..iNeedIt.length - 1) {
            if (jumps[i] == iNeedIt[j]) forError += 1
        }
        if (forError != 1) return -1
        forError = 0
    }
    var parts = jumps.split(" ")
    for (part in parts)
            if ((part[0] != iNeedIt[10]) and (part[0] != iNeedIt[11])) result += part + " "
    var mnojitel = 1
    var element = 0
    if (result.length == 0) return (-1)
    parts = result.split(" ")
    for (part in parts) {
        for (i in part.length - 1 downTo 0) {
            for (j in 0..9) {
                if (iNeedIt[j] == part[i]) {
                    element += j * mnojitel
                    mnojitel *= 10
                }
            }
        }
            if (element > answ) answ = element
            mnojitel = 1
            element = 0
        }
    return answ
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
    var mbAnsw = ""
    var bestJump = 0
    var forError = 0
    val iNeedIt = "0123456789+-% "
    for (i in 0..jumps.length - 1) {
        for (j in 0..iNeedIt.length - 1) {
            if (jumps[i] == iNeedIt[j]) forError += 1
        }
        if (forError != 1) return -1
        forError = 0
    }
    var parts = jumps.split(" ")
    var element = ""
    for (part in parts) {
        for (i in 0..part.length - 1)
            if (part[i] == iNeedIt[10]) mbAnsw += element + " "
        element = part
    }
    var mnojitel = 1
    var elementInt = 0
    if (mbAnsw.length == 0) return (-1)
    parts = mbAnsw.split(" ")
    for (part in parts) {
        for (i in part.length - 1 downTo 0) {
            for (j in 0..9) {
                if (iNeedIt[j] == part[i]) {
                    elementInt += j * mnojitel
                    mnojitel *= 10
                }
            }
        }
        if (elementInt > bestJump) bestJump = elementInt
        mnojitel = 1
        elementInt = 0
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
    if (expression.length == 0) throw IllegalArgumentException()
    var answ = 0
    var forError = 0
    val iNeedIt = "0123456789+- "
    for (i in 0..expression.length - 1) {
        for (j in 0..iNeedIt.length - 1) {
            if (expression[i] == iNeedIt[j]) forError += 1
        }
        if (forError != 1) throw IllegalArgumentException()
        forError = 0
    }
    var parts = expression.split(" ")
    var mnojitel = 1
    var element = "+"
    var elementInt = 0
    for (part in parts){
        for (i in 0..iNeedIt.length -1) {
            if (element[0] == iNeedIt[i]) {
                if ((i > 9) && ((part[0] == iNeedIt[10]) || (part[0] == iNeedIt[11]))) throw IllegalArgumentException()
                    if ((i < 10) && (part[0] != iNeedIt[10]) && (part[0] != iNeedIt[11])) throw IllegalArgumentException()
            }
        }
        for (i in part.length - 1 downTo 0) {
            for (j in 0..9) {
                if (iNeedIt[j] == part[i]) {
                    elementInt += j * mnojitel
                    mnojitel *= 10
                }
            }
        }
        if (element[0] == iNeedIt[10]) answ += elementInt
        if (element[0] == iNeedIt[11]) answ -= elementInt
        element = part
        mnojitel = 1
        elementInt = 0
    }
    return answ
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
    var parts = str.split(" ")
    var counter = 0
    var element = ""
    var answ = -1
    for (part in parts) {
        counter += 1
    }
    if (counter == 1) return -1
    for (i in 0..counter - 2) {
        if (parts[i].capitalize() == parts[i+1].capitalize()) {
            answ += 1
            break
        } else {
            answ += parts[i].length + 1
            continue
        }
    }
    if (str.length - parts[counter - 1].length == answ ) return -1
    return answ
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
fun mostExpensive(description: String): String = TODO()

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