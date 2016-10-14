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
    val parts=str.split(" ")
    if (parts.size!=3)return ""
    try{
        if (parts[0].toInt()>31)return ""
    }
    catch(e:NumberFormatException){
        return ""
    }
    var str1=""
            when{
                parts[1]=="января"->str1="01"
                parts[1]=="февраля"->{
                    if (parts[0].toInt()>28) {
                        if ((parts[0].toInt()%2==0)&&(parts[0].toInt()%4!=0)&&(parts[0].toInt()==29)) str1="02"
                        else return ""
                    } else str1="02"
                }
                parts[1]=="марта"->str1="03"
                parts[1]=="апреля"->str1="04"
                parts[1]=="мая"->str1="05"
                parts[1]=="июня"->str1="06"
                parts[1]=="июля"->str1="07"
                parts[1]=="августа"->str1="08"
                parts[1]=="сентября"->str1="09"
                parts[1]=="октября"->str1="10"
                parts[1]=="ноября"->str1="11"
                parts[1]=="декабря"->str1="12"
                else->return ""
            }
    if (((str1=="04")||(str1=="06")||(str1=="09")||(str1=="11"))&&(parts[0].toInt()>30))return ""
    var str0=""
    if (parts[0].length<2)str0="0"+parts[0] else str0=parts[0]
    val result= str0+"."+str1+"."+parts[2]
    return result
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */
fun dateDigitToStr(digital: String): String {
    val parts=digital.split(".")
    if (parts.size!=3)return ""
    try {
        if (parts[0].toInt()>31)return ""
    }
    catch (e: NumberFormatException) {
        return ""
    }
    if (((parts[1]=="04")||(parts[1]=="06")||(parts[1]=="09")||(parts[1]=="11"))&&(parts[0].toInt()>30))return ""
    var str1=""
    when{
        parts[1]=="01"->str1="января"
        parts[1]=="02"->{
            if (parts[0].toInt()>28) {
                if ((parts[0].toInt()%2==0)&&(parts[0].toInt()%4!=0)&&(parts[0].toInt()==29)) str1="февраля"
                else return ""
            } else str1="февраля"
        }
        parts[1]=="03"->str1="марта"
        parts[1]=="04"->str1="апреля"
        parts[1]=="05"->str1="мая"
        parts[1]=="06"->str1="июня"
        parts[1]=="07"->str1="июля"
        parts[1]=="08"->str1="августа"
        parts[1]=="09"->str1="сентября"
        parts[1]=="10"->str1="октября"
        parts[1]=="11"->str1="ноября"
        parts[1]=="12"->str1="декабря"
        else -> return ""
    }
    var str0=""
    if (parts[0].toInt()<10)str0=parts[0].last().toString() else str0=parts[0]
    val result=str0+" "+str1+" "+parts[2]
    return result
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
    if (phone=="") return ""
    var z=0
    if (phone[0]=='+')z=1
    for (i in 1..phone.length-1){
        if (phone[i]=='+') return ""
    }
    val parts=phone.split("(",")","-"," ","+").toMutableList()
    for (i in 0..parts.size-1){
        parts.remove("")
    }
    for (i in 0..parts.size-1)
            try {
                val number=parts[i].toInt()
            } catch (e: NumberFormatException) {
                return ""
            }
        var result=""
        for (i in 0..parts.size-1) {
            result+=parts[i]
        }
    if (z==1) return "+"+result else return result
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
    val parts=jumps.split(" ","%","-").toMutableList()
    for (i in 0..parts.size-1) {
        parts.remove("")
    }
    var max=-1
    try {
        for (part in parts) {
            if (part.toInt() > max) max=part.toInt()
        }
    } catch (e: NumberFormatException){
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
    val parts = jumps.split(" ")
    val list:MutableList<String> = mutableListOf()
    for (i in 1..parts.size-1){
        if ('+' in parts[i]) list += parts[i-1]
    }
    var max = -1
    try {
        for (element in list) {
            if (element.toInt() > max) max = element.toInt()
        }
    } catch (e: NumberFormatException){
        return -1
    }
    return max
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
    val parts=expression.split(" ")
    var result=0
    for (i in 0..parts.size-1) {
        when{
            (parts[i]=="+")->result+=parts[i+1].toInt()
            (parts[i]=="-")->result+=(parts[i+1].toInt()*(-1))
        }
    }
    if (parts[0]!="-") result+=parts[0].toInt()
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
    val parts=str.split(" ").toMutableList()
    var sum=0
    for (i in 0..parts.size-2){
        parts[i+1]=parts[i+1].toLowerCase()
        if (parts[i]==parts[i+1]) return sum
        sum+=parts[i].length+1
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
    if (description == "") return ""
    val parts = description.split(" ","; ",".")
    if (parts.size % 3 != 0) return ""
    val listPrice: MutableList<String> = mutableListOf()
    val listProducts: MutableList<String> = mutableListOf()
    for (i in 0..parts.size - 1){
        if (i % 3 == 0) listProducts += parts[i]
        else if (i % 3 == 1) listPrice += parts[i]
        else listPrice[listPrice.size - 1] += parts[i]
    }
    var max = "0"
    try {
        for (element in listPrice) {
            if (element.toInt() > max.toInt()) max = element
        }
    } catch (e:NumberFormatException) {
        return ""
    }
    return listProducts[listPrice.indexOf(max)]
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