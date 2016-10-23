@file:Suppress("UNUSED_PARAMETER")

package lesson5.task1

import javafx.beans.binding.NumberBinding

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


//////////////////////////////////////////////////////////////////////////
val monthlist = listOf("января", "февраля", "марта", "апреля", "мая",
        "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря")
//////////////////////////////////////////////////////////////////////////

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateStrToDigit(str: String): String {
    if (str.isEmpty()) return ""
    val parts = str.split(" ")
    if (parts.size != 3) return ""
    val index = monthlist.indexOf(parts[1])
    if (index == -1) return ""
    val numb: Int
    val year: Int
    try {
        numb = parts[0].toInt()
        year = parts[2].toInt()
        if (!dateCheck(numb, index + 1, year)) return ""
    } catch (e: NumberFormatException) {
        return ""
    }
    return String.format("%02d.%02d.%d", numb, index + 1, year)
}

/////////////////////////////
fun dateCheck(numb: Int, month: Int, year: Int): Boolean {
    if (month !in 1..12 || year < 0 || numb !in 1..31) return false
    if (month == 4 || month == 6 || month == 9 || month == 11) {
        if (numb > 30) return false
    } else if (month == 2) {
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            if (numb > 29) return false
        } else if (numb > 28) return false
    }
    return true
}
//////////////////////////////////

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    if (digital.isEmpty()) return ""
    val parts = digital.split(".")
    if (parts.size != 3) return ""
    val numb: Int
    val month: Int
    val year: Int
    try {
        numb = parts[0].toInt()
        month = parts[1].toInt()
        year = parts[2].toInt()
        if (!dateCheck(numb, month, year)) return ""
    } catch (e: NumberFormatException) {
        return ""
    }
    return String.format("%d %s %d", numb, monthlist[month - 1], year)
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
    if (phone.isEmpty()) return ""
    if (phone == "+") return ""
    var phonenum = phone.filter { it != ' ' && it != '-' }
    val indexopen = phonenum.indexOf('(') //находим индекс открывающейся скобки
    //если открывающаяся скобка есть, то проверяем, что она одна
    if (!search(indexopen, phonenum, '(')) return ""
    val indexclose = phonenum.indexOf(')') //то же самое с закрывающейся скобкой
    if (!search(indexclose, phonenum, ')')) return ""
    if ((indexopen != -1 && indexclose == -1) || //если скобка открывается, но не закрывается
            (indexopen == -1 && indexclose != -1)) return "" //если закрывается, но не открывается
    if (indexopen > indexclose) return "" //если сначала закрывается, а потом открывается
    val indexplus = phonenum.indexOf('+') //находим индекс плюса
    //если плюс не один - ошибка
    if (!search(indexplus, phonenum, '+')) return ""
    //если плюса нет или он в начале(нулевой индекс) - хорошо, иначе ошибка
    if (indexplus > 0) return ""
    //проверяем, есть ли между плюсом и открывающейся скобкой хотя бы одна цифра
    if (indexopen != -1 && indexplus == 0) {
        if (indexopen - indexplus < 2) return ""
    }
    //проверяем, есть ли между двумя скобками хотя бы одна цифра
    if (indexopen != -1 && indexclose != -1) {
        if (indexclose - indexopen < 2) return ""
    }
    phonenum = phonenum.filter { it != '(' && it != ')' } //убираем скобки
    val phonenum1 = phonenum.filter { it == '+' || it in '0'..'9' } //оставляем только плюс и цифры
    //если отфильтрованная строка не совпадает с изначальной, то
    // там есть символы, кроме + и цифр, что является ошибкой, а иначе все хорошо
    if (!phonenum.equals(phonenum1)) return ""
    else return phonenum1
}

///////////////////////////////////////////////////
fun search(index: Int, str: String, ch: Char): Boolean {
    if (index != -1) {
        if (index != str.lastIndexOf(ch)) return false
    }
    return true
}
///////////////////////////////////////////////////


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
    if (jumps.isEmpty()) return -1
    val jump = jumps.filter { it != '%' && it != '-' }
    //jump = deletespace(jump)
    val results = jump.split(" ").filter { it.length != 0 }
    if (results.size == 0) return -1
    var answer = -1
    try {
        for (element in results) {
            val elem = element.toInt()
            if (elem > answer)
                answer = elem
        }

    } catch (e: NumberFormatException) {
        return -1
    }
    return answer
}

/////////////////////////////////////////////////////////////
fun deletespace(str: String): String {
    var new = str.trim()
    while (new.contains("  "))
        new = new.replace("  ", " ")
    return new
}
////////////////////////////////////////////////////////////


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
    if (jumps.isEmpty()) return -1
    var maxhight = -1
    try {
        val jump = jumps.split(" ")
        if (jump.size % 2 == 1) return -1
        for (i in 0..jump.size - 1 step 2) {
            val str = jump[i+1].filter { it != '%' }
            if (str.length > 2) return -1
            val jumpInt = jump[i].toInt()
            if (jumpInt > maxhight && str == "+") {
                maxhight = jumpInt
            }
        }
        return maxhight
    } catch(e: NumberFormatException) {
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
    if (expression.isEmpty()) throw IllegalArgumentException()
    var answer = 0
    val expressions = expression.split(" ").filter { it.length != 0 }
    if (expressions.size == 0 || expressions.size % 2 != 1) throw IllegalArgumentException()
    try {
        answer = expressions[0].toInt()
        for (i in 1..expressions.size - 2 step 2) {
            val exptoint = expressions[i + 1].toInt()
            when (expressions[i]) {
                "+" -> answer += exptoint
                "-" -> answer -= exptoint
                else -> throw IllegalArgumentException()
            }
        }
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException()
    }
    return answer
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
    if (str.isEmpty()) return -1
    var index = 0
    val strin = str.toLowerCase() //преобразуем всю строку в один регистр(нижний)
    val strings = strin.split(" ")
    if (strings.size < 2) return -1
    for (i in 0..strings.size - 2) {
        if (strings[i].equals(strings[i + 1])) return index
        else index += strings[i].length + 1
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
    if (description.isEmpty()) return ""
    val strings = description.filter { it != ';' }.split(" ")
    if (strings.size < 2) return ""
    var maxindex = 1
    try {
        for (i in 1..strings.size - 1 step 2) {
            val strInt = strings[i].toDouble()
            if (strInt < 0) return ""
            if (strInt >= strings[maxindex].toDouble()) {
                maxindex = i
            }
        }
    } catch (e: NumberFormatException) {
        return ""
    }
    return strings[maxindex - 1]
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