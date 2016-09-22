@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1


import java.lang.Math.*
import java.util.*

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
    val parts = str.split(' ')
    if (parts.count() == 3) {
        val day = parts[0].toInt()
        val year = parts[2].toInt()
        val month = when (parts[1]) {
            "января" -> 1
            "февраля" -> 2
            "марта" -> 3
            "апреля" -> 4
            "мая" -> 5
            "июня" -> 6
            "июля" -> 7
            "августа" -> 8
            "сентября" -> 9
            "октября" -> 10
            "ноября" -> 11
            "декабря" -> 12
            else -> 0
        }
        if (month != 0)
            return String.format("%02d.%02d.%d", day, month, year)
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
    val parts = digital.split('.')
    var isNum = true; parts.forEach {if (it.matches(Regex("[0-9]+")) && isNum) isNum = true else isNum = false }
    if (parts.count() == 3 && isNum) {
        val day = parts[0].toInt()
        val year = parts[2].toInt()
        val month = when (parts[1].toInt()) {
            1 -> "января"
            2 -> "февраля"
            3 -> "марта"
            4 -> "апреля"
            5 -> "мая"
            6 -> "июня"
            7 -> "июля"
            8 -> "августа"
            9 -> "сентября"
            10 -> "октября"
            11 -> "ноября"
            12 -> "декабря"
            else -> ""
        }
        if (month != "")
            return String.format("%d %s %d", day, month, year)
        else return ""
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
    if (phone.matches(Regex("^[+]?[-()0-9 ]+"))){
        return phone.replace(Regex("[-() ]+"),"")
    } else return ""
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
    if(jumps.matches(Regex("[-%[0-9] ]+"))){
        return Regex("[0-9]+").findAll(jumps).map { it.value.toInt() }.max() ?: -1
    } else return -1
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
    if(jumps.matches(Regex("[-+%[0-9] ]+"))){
        return Regex("([0-9]+) [+]").findAll(jumps).map { it.groupValues[1].toInt() }.max() ?: -1
    } else return -1
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
    if (expression.matches(Regex("""^([0-9]+)(\s+[-+]\s+[0-9]+)*"""))) {
        return Regex("[-+]?[0-9]+").findAll(expression.replace(" ","")).sumBy { it.groupValues[0].toInt() }
    } else throw java.lang.IllegalArgumentException(java.lang.NumberFormatException(expression))
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
    var found = false
    var last = " "
    var passed = 0
    for (w in str.split(" ")) {
        if (w.toLowerCase() == last && w != " ") {
            found = true
            passed -= (w.length + 1)
            break
        }
        passed += w.length + 1
        last = w.toLowerCase()
    }
    return if (found) passed else -1
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
    var most = ""
    var value = -1.0
    for (one in description.split("; ")) {
        if (one.isNotEmpty()) {
            val values = one.split(" ")
            val name = values[0]
            val price = values[1]
            if (price.toDouble() > value) {
                most = name;
                value = price.toDouble()
            }
        }
    }
    return most
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
    var count: Int = roman.length-1
    var num: Int = 0
    while (count >= 0) {
        when (roman[count]) {
            'I' -> {num++ ; count--}
            'V' -> {
                if (count != 0 && roman[count-1] == 'I') {
                    num += 4
                    count -= 2
                } else {
                    num += 5
                    count--
                }
            }
            'X' -> {
                if (count != 0 && roman[count-1] == 'I') {
                    num += 9
                    count -= 2
                } else {
                    num += 10
                    count--
                }
            }
            'L' -> {
                if (count != 0 && roman[count-1] == 'X') {
                    num += 40
                    count -= 2
                } else {
                    num += 50
                    count--
                }
            }
            'C' -> {
                if (count != 0 && roman[count-1] == 'X') {
                    num += 90
                    count -= 2
                } else {
                    num += 100
                    count--
                }
            }
            'D' -> {
                if (count != 0 && roman[count-1] == 'C') {
                    num += 400
                    count -= 2
                } else {
                    num += 500
                    count--
                }
            }
            'M' -> {
                if (count != 0 && roman[count-1] == 'C') {
                    num += 900
                    count -= 2
                } else {
                    num += 1000
                    count--
                }
            }
            else -> {count = -1; num = -1}
        }
    }
    return num
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
fun findCycles(commands: String, open: Char, close: Char): Map<Int,Int> {
    //Сдвиг для открывающего символа цикла
    var pointerOpen = 0
    //Сдвиг для закрывающего сивола цикла
    var pointerClose = 0
    //Ассоциативный массив для результатов
    val map: MutableMap<Int,Int> = HashMap()
    //Считаем количесво открывающих знаков, чтобы получить общее количесво циклов
    val count = commands.count { it == open }
    //Повторяем для каждого найденного цикла
    for (i in 1..count) {
        //Положение открывающего символа с учетом сдвига
        val indexOpen = commands.indexOf(open,pointerOpen)
        //Положение закрывающего символа с учетом сдвига
        val indexClose = commands.indexOf(close,pointerClose)
        //Если сначала идет закрывающий, считаем, что у него нет пары, и возвращаем ошибку
        if (indexOpen > indexClose) {
            throw java.lang.IllegalStateException("No pair for bracket")
        } else {
            //Сохраняем в массив два значения, чтобы можно было получить конец цикла по началу и наоборот
            map.put(indexOpen,indexClose)
            map.put(indexClose,indexOpen)
            //Меняем сдвиги для дальнейшего поиска
            pointerOpen = indexOpen+1
            pointerClose = indexClose+1
        }
    }
    return map
}
fun computeDeviceCells(cells: Int, commands: String): List<Int> {
    //Проверяем, парный ли каждый цикл
    if (commands.count { it=='[' } == commands.count { it=='[' } &&
            commands.count { it=='{' } == commands.count { it=='}' } ) {
        //Если ноль ячеек сразу возвращаем ответ
        if (cells == 0) return  listOf<Int>()
        //Создаем массив размером cells и заполняем его нулями
        val cellsArr = Array(cells, {i -> 0})
        //Начальная точка
        var point = cells/2
        //Указатель на текущую команду
        var commandPoint = 0
        //Длина строки команд
        val commandLength = commands.length
        //Находим все циклы
        val cycleOne = findCycles(commands,'[',']')
        val cycleTwo = findCycles(commands,'{','}')
        //Выполняем пока не конец командной строки
        while (commandPoint < commandLength) {
            when (commands[commandPoint]) {
                ' ' -> commandPoint++ //Пустая команда, просто сдвигаем указатель
                '>' -> {
                    //Сдвигаем ячейку
                    point++
                    //Выдаем ошибку, если выходим за границы
                    if (point > cells-1) throw java.lang.IllegalStateException()
                    //Сдвигаем указатель
                    commandPoint++
                }
                '<' -> {
                    //Аналогично ">"
                    point--
                    if (point < 0) throw java.lang.IllegalStateException()
                    commandPoint++
                }
                '+' -> {
                    //Увеличиваем значение в ячейке и сдвигаем указатель
                    cellsArr[point]++
                    commandPoint++
                }
                '-' -> {
                    //Аналогично "-"
                    cellsArr[point]--
                    commandPoint++
                }
                '[' -> {
                    if (cellsArr[point] == 0)
                        //Если значение в ячейке 0, то выходим из цикла
                        commandPoint = (cycleOne[commandPoint] ?: 0) + 1
                    else
                        //Иначе просто сдвигаем указатель
                        commandPoint++
                }
                ']' -> {
                    if (cellsArr[point] != 0) {
                        //Если значение в ячейке не равно 0, возвращаемся в начало цикла
                        commandPoint = (cycleOne[commandPoint] ?: 0) + 1
                    } else {
                        //Сдивагаем указатель и выходи из цикла
                        commandPoint++
                    }
                }
                //Аналогично прошлому циклу
                '{' -> {
                    if (cellsArr[point] == 0)
                        commandPoint = (cycleTwo[commandPoint] ?: 0) + 1
                    else
                        commandPoint++
                }
                '}' -> {
                    if (cellsArr[point] != 0) {
                        commandPoint = (cycleTwo[commandPoint] ?: 0) + 1
                    } else {
                        commandPoint++
                    }
                }
                //Если сивол не предусмотрен выдаем ошибку
                else -> throw java.lang.IllegalArgumentException()
            }
        }
        return cellsArr.toList()
    } else throw IllegalAccessException()
}