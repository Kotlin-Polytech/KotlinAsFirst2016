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
    parts.asSequence().map(String::toInt).forEach { result = result * 60 + it }
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
fun dayInMonth(month: Int, year: Int): Int {
    return when {
        month == 1                      -> 31
        month == 2 && isLeapYear(year)  -> 29
        month == 2 && !isLeapYear(year) -> 28
        month == 3                      -> 31
        month == 4                      -> 30
        month == 5                      -> 31
        month == 6                      -> 30
        month == 7                      -> 31
        month == 8                      -> 31
        month == 9                      -> 30
        month == 10                     -> 31
        month == 11                     -> 30
        month == 12                     -> 31
        else
        -> throw IllegalArgumentException("This is wrong number of month: $month.")
    }
}

val numberOfMonth: List<String> =
        listOf("января", "февраля", "марта", "апреля",
                "мая", "июня", "июля", "августа",
                "сентября", "октября", "ноября", "декабря")

fun isLeapYear(year: Int): Boolean {
    return if (year < 0) throw IllegalArgumentException("This is wrong number of year: $year.")
    else ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
}

fun isCorrectDay(day: Int, month: Int, year: Int): Boolean = day in 1..dayInMonth(month, year)
fun isCorrectMonth(month: Int): Boolean = month in 1..12
fun isCorrectYear(year: Int): Boolean = year >= 0

fun isCorrectDate(date: List<String>): Boolean {
    val isWord = Regex("""^[а-я]+$""")
    val isNumber = Regex("""^[0-9]{1,2}$""")
    val day = date[0].toInt()
    val year = date[2].toInt()
    if (day !in 1..31) return false
    else if (!isCorrectYear(year)) return false
    else if (isWord.containsMatchIn(date[1])) {
        val month = numberOfMonth.indexOf(date[1]) + 1
        return isCorrectMonth(month) && isCorrectDay(day, month, year)
    } else if (isNumber.containsMatchIn(date[1])) {
        val month = date[1].toInt()
        return isCorrectMonth(month) && isCorrectDay(day, month, year)
    } else return false
}

fun dateStrToDigit(str: String): String {
    val checkFormat = Regex("""^\d{1,2} [а-я]+ \d+$""")
    if (!checkFormat.matches(str)) return ""
    else {
        val date: MutableList<String> = str.split(" ").toMutableList()
        if (isCorrectDate(date)) {
            date[1] = twoDigitStr(numberOfMonth.indexOf(date[1])+1)
            date[0] = twoDigitStr(date[0].toInt())
            return date.joinToString(".")
        } else return ""
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
    val checkFormat = Regex("""^\d\d\.\d\d.\d+$""")
    if (!checkFormat.matches(digital)) return ""
    else {
        val date: MutableList<String> = digital.split(".").toMutableList()
        if (isCorrectDate(date)) {
            date[0] = date[0].toInt().toString()
            date[1] = numberOfMonth[date[1].toInt()-1]
            return date.joinToString(" ")
        }
        else return ""
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
    val checkFormat = Regex("""^(\+?\d+)?[ -]*(\(\d+\))?[ -]*\d[ 0-9-]*$""")
    return if (!checkFormat.matches(phone)) ""
    else phone.filterNot { it == ' ' || it == '(' || it == ')' || it == '-' }
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
    val checkFormat = Regex("""(\d+ |[%-] )+(\d+|[%-])""")
    if (!checkFormat.matches(jumps)) return -1
    else {
        var bestLongJump: Int = -1
        val longJumps = Regex("""\d+""").findAll(jumps)
        for (i in longJumps) {
            try {
                if (i.value.toInt() > bestLongJump) bestLongJump = i.value.toInt()
            }
            catch (e: NumberFormatException) {
                return -1
            }
        }
        return bestLongJump
    }
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
tailrec fun jumpsProcessing (jumps: String, bestHighJump: Int):Int {
    var result: Int = bestHighJump
    val plus = Regex("""\+""")
    if (jumps.isEmpty()) return result
    else {
        val jump: Int = jumps.takeWhile { it != ' ' }.toInt()
        var temp: String = jumps.dropWhile { it != ' ' }.dropWhile { it == ' ' }
        val mark: String = temp.takeWhile { it != ' ' }
        temp = temp.drop(mark.length).dropWhile { it == ' ' }
        if (plus.containsMatchIn(mark) && jump > bestHighJump) result = jump
        return jumpsProcessing(temp, result)
    }
}

fun bestHighJump(jumps: String): Int {
    val checkFormat = Regex("""(\d+ [+%-]+ )*(\d+ [+%-]+)""")
    if (!checkFormat.matches(jumps)) return -1
    return jumpsProcessing(jumps, -1)
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
    val checkFormat = Regex("""(\d+ (\+|-) )*\d+""")
    if (!checkFormat.matches(expression)) throw IllegalArgumentException(expression)
    else {
        try {
            val parts = expression.split(" ")
            var result: Int = parts[0].toInt()
            for (i in 2..parts.size-1 step 2) {
                if (parts[i-1] == "+") result += parts[i].toInt()
                else if (parts[i-1] == "-") result -= parts[i].toInt()
                else throw IllegalArgumentException(parts[i-1])
            }
            return result
        }
        catch (e: NumberFormatException) {
            throw IllegalArgumentException(expression)
        }
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
fun firstDuplicateIndex(str: String): Int =
    Regex("""([А-яA-z]+) (\1)\b""", RegexOption.IGNORE_CASE).find(str)?.range?.first ?: -1


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
    val goods = description.split("; ")
    var maxPrice: Double = 0.0
    var name: String = ""
    val findPrice = Regex("""\d+(\.\d+)?$""")
    val findName = Regex(""".+(?= \d+(\.\d+)?$)""")
    for (i in goods) {
        val temp = findPrice.find(i)?.value?.toDouble() ?: return ""
        if (temp > maxPrice) {
            maxPrice = temp
            name = findName.find(i)?.value ?: return ""
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
val table: Map<Char, Int> =
        mapOf(  'M' to 1000,
                'D' to 500,
                'C' to 100,
                'L' to 50,
                'X' to 10,
                'V' to 5,
                'I' to 1)

fun fromRoman(roman: String): Int {
    val checkFormat1 = Regex("""IIII|XXXX|CCCC|VV|LL|DD""")
    val checkFormat2 = Regex("""II(?=(V|X))|XX(?=(L|C))|CC(?=(D|M))""")
    val checkFormat3 = Regex("""V(?=(L|IV|IX))|L(?=(D|XL|XC))|D(?=(CD|CM))""")
    val checkFormat4 = Regex("""(IV|IX)(?=(I|V|X|L|C|D|M))|(XL|XC)(?=(X|L|C|D|M))|(CD|CM)(?=(C|D|M))""")

    if (    checkFormat1.containsMatchIn(roman) ||
            checkFormat2.containsMatchIn(roman) ||
            checkFormat3.containsMatchIn(roman) ||
            checkFormat4.containsMatchIn(roman)) return -1
    else if (roman == "") return 0
    else {
        var result: Int = 0
        var parts = roman.toList()
        while (parts.size > 1) {
            val current: Int = table[parts[0]] ?: return -1
            val next: Int = table[parts[1]] ?: return -1
            when {
                next / current <= 1                             -> result += current
                next / current == 5 || next / current == 10     -> result -= current
                else                                            -> return -1
            }
            parts = parts.drop(1)
        }
        result += table[parts[0]] ?: return -1
        return result
    }
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
    val computeDevice: ComputeDevice = createComputeDevice(cells)
    computeDevice.execute(commands)
    return computeDevice.conveyor()
}

/**
 *  Интерфейс устройства, ориентированный на задачу computeDeviceCells.
 *  Метод execute() выполняет изменение состояния устройства в соответствии
 *  с переданной в качестве параметра строкой команд.
 *  Метод getConveyor() позволяет получить список целых чисел, представляющий
 *  ячейки конвейера.
 */
interface ComputeDevice {
    fun execute(commands: String): Unit
    fun conveyor(): List<Int>
}

/**
 * Функция для создания экземпляра устройства.
 */
fun createComputeDevice(cells: Int): ComputeDevice {
    return ComputeDeviceImpl(cells)
}

/**
 * Класс реализации устройства.
 * Поля класса:
 *      cells - количество ячеек устройства.
 *      conveyor - представление конвейера ячеек.
 *      current - текущее положение датчика.
 */
class ComputeDeviceImpl(val cells: Int) : ComputeDevice{
    private val conveyor: MutableList<Int>

    private var current: Int = cells / 2

    private var commandProcessingStrategy: CommandProcessingStrategy = RightAlgorithm(this)

    init {
        conveyor = mutableListOf()
        for (i in 0..cells - 1) {
            conveyor.add(0)
        }
    }

    ///Команда '+'
    fun plus(): Unit {
        conveyor[current]++
    }

    ///Команда '-'
    fun minus(): Unit {
        conveyor[current]--
    }

    ///Команда '>'
    fun right(): Unit {
        if (current != cells - 1) {
            current++
        }
        else throw IllegalStateException("Out of conveyor.")
    }

    ///Команда '<'
    fun left(): Unit {
        if (current != 0) {
            current--
        }
        else throw IllegalStateException("Out of conveyor.")
    }

    ///Команда ' '
    fun space(): Unit {}

    ///Получение значения ячейки под датчиком.
    fun getCurrentCell(): Int = conveyor[current]

    override fun execute(commands: String): Unit {
        commandProcessingStrategy.commandProcessing(commands)
    }

    override fun conveyor(): List<Int> = conveyor

    ///Перегрузка toString() для осмысленных диагностических сообщений. (При работе функции не используется)
    override fun toString(): String {
        return "[conveyor = $conveyor, current = $current]"
    }
}

abstract class CommandProcessingStrategy(open var computeDevice: ComputeDeviceImpl) {
    abstract fun commandProcessing(commands: String): ComputeDevice
}

class WrongAlgorithm(override var computeDevice: ComputeDeviceImpl) : CommandProcessingStrategy(computeDevice) {
    ///Обработка команд.
    override fun commandProcessing(commands: String): ComputeDevice {
        var index: Int = 0
        while (index != commands.length) {
            when (commands[index]) {
                '+' -> { computeDevice.plus(); index++ }
                '-' -> { computeDevice.minus(); index++ }
                '>' -> { computeDevice.right(); index++ }
                '<' -> { computeDevice.left(); index++ }
                ' ' -> { computeDevice.space(); index++ }
                '[' -> {
                    val expr = getComplicatedExpression(commands.drop(index+1), '[', ']')
                    loopProcessing(expr)
                    index += (expr.length + 2)
                }
                '{' -> {
                    val expr = getComplicatedExpression(commands.drop(index+1), '{', '}')
                    loopProcessing(expr)
                    index += (expr.length + 2)
                }
                else -> throw IllegalArgumentException("Wrong symbol: ${commands[index]}.")
            }
        }
        return computeDevice
    }

    ///Извлечь выражение из цикла.
    private fun getComplicatedExpression(str: String, opening: Char, closing: Char): String {
        var counterOpenBrackets: Int = 1
        val expression = StringBuilder("")
        for (i in str) {
            when (i) {
                closing -> counterOpenBrackets--
                opening -> { counterOpenBrackets++; expression.append(i) }
                else    -> expression.append(i)
            }
            if (counterOpenBrackets == 0) return expression.toString()
        }
        throw IllegalArgumentException("Unclosed brackets.")
    }

    ///Обработка выражения в цикле.
    private fun loopProcessing(expr: String): ComputeDeviceImpl {
        while (computeDevice.getCurrentCell() != 0) {
            commandProcessing(expr)
        }
        return computeDevice
    }


}

class RightAlgorithm(override var computeDevice: ComputeDeviceImpl) : CommandProcessingStrategy(computeDevice) {
    override fun commandProcessing(commands: String): ComputeDevice {
        var index: Int = 0
        while (index != commands.length) {
            when (commands[index]) {
                '+' -> { computeDevice.plus(); index++ }
                '-' -> { computeDevice.minus(); index++ }
                '>' -> { computeDevice.right(); index++ }
                '<' -> { computeDevice.left(); index++ }
                ' ' -> { computeDevice.space(); index++ }
                '[' -> {
                    if (computeDevice.getCurrentCell() == 0) {
                        index += rightShiftTo(commands.drop(index + 1), ']')
                    }
                    else index++
                }
                ']' -> {
                    if (computeDevice.getCurrentCell() != 0) {
                        index -= leftShiftTo(commands.dropLast(commands.length - index), '[')
                    }
                    else index++
                }
                '{' -> {
                    if (computeDevice.getCurrentCell() == 0) {
                        index += rightShiftTo(commands.drop(index + 1), '}')
                    }
                    else index++
                }
                '}' -> {
                    if (computeDevice.getCurrentCell() != 0) {
                        index -= leftShiftTo(commands.dropLast(commands.length - index), '{')
                    }
                    else index++
                }
                else -> throw IllegalArgumentException("Wrong symbol: ${commands[index]}.")

            }
        }
        return computeDevice
    }

    private fun rightShiftTo(str: String, symbol: Char): Int {
        val temp: Int = str.indexOfFirst { it == symbol }
        if (temp == -1) throw IllegalArgumentException("Unclosed bracket.")
        else return temp + 1
    }

    private fun leftShiftTo(str: String, symbol: Char): Int {
        val temp: Int = str.length - str.lastIndexOf(symbol)
        if (temp == -1) throw IllegalArgumentException("Unclosed bracket.")
        else return temp - 1
    }
}