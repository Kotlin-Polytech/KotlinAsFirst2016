@file:Suppress("UNUSED_PARAMETER")

package lesson8.task1

import java.io.File

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                } else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val map = mutableMapOf<String, Int>()
    var j = 0
    val fileText = File(inputName).readText().toLowerCase()
    for (string in substrings) {
        j = 0
        if (string.toLowerCase() in fileText)
            loop@ for (i in 0..fileText.length - string.length){
                for (k in 0..string.length - 1) if (fileText[i + k] != string.toLowerCase()[k]) continue@loop
                j++
            }
        map.put(string, j)
    }
    return map
}


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    var maxLength = -1
    val lines = File(inputName).readLines()
    for (line in lines) if (line.trim().length > maxLength) maxLength = line.trim().length
    val outputStream = File(outputName).bufferedWriter()
    for (i in 0..lines.size - 1) {
        val lineLength = lines[i].trim().length
        for (j in 1..(maxLength - lineLength) / 2) outputStream.write(" ")
        outputStream.write(lines[i].trim())
        if (i != lines.size - 1) outputStream.newLine()
    }
    outputStream.close()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов выводятся трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 3) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 1) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 2) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (русских, либо латинских, без знаков препинания и цифр).
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> {
    TODO()
}

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    TODO()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
    <body>
        <p>
            Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
            Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
        </p>
        <p>
            Suspendisse <s>et elit in enim tempus iaculis</s>.
        </p>
    </body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimpleConstructor(lines: List<String>):String {
    val keys = listOf(Triple("**", "<b>", "</b>"), Triple("*", "<i>", "</i>"),Triple("~~", "<s>", "</s>"))
    var text = lines.joinToString(separator = "\n").split("\n\n").joinToString(separator = "</p><p>")
    for (key in keys) {
        val temp = text.split(key.first).toMutableList()
        if (temp.size == 1) continue
        if (temp.size % 2 == 0) {
            temp[temp.size - 2] += key.first + temp[temp.size - 1]
            temp.removeAt(temp.size - 1)
        }
        val sb = StringBuilder()
        var k = true
        for (i in 0..temp.size - 2) {
            if (k) {
                sb.append((listOf(temp[i], temp[i + 1])).joinToString(separator = key.second))
                k = false
            } else {
                sb.append(key.third)
                k = true
            }
        }
        sb.append(temp[temp.size - 1])
        text = sb.toString()
    }
    return text
}

fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val lines = File(inputName).readLines()
    val text = markdownToHtmlSimpleConstructor(lines)
    val outputStream = File(outputName).bufferedWriter()
    outputStream.write("<html><body><p>$text</p></body></html>")
    outputStream.close()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
* Утка по-пекински
    * Утка
    * Соус
* Салат Оливье
    1. Мясо
        * Или колбаса
    2. Майонез
    3. Картофель
    4. Что-то там ещё
* Помидоры
* Фрукты
    1. Бананы
    23. Яблоки
        1. Красные
        2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
  <body>
    <ul>
      <li>
        Утка по-пекински
        <ul>
          <li>Утка</li>
          <li>Соус</li>
        </ul>
      </li>
      <li>
        Салат Оливье
        <ol>
          <li>Мясо
            <ul>
              <li>
                  Или колбаса
              </li>
            </ul>
          </li>
          <li>Майонез</li>
          <li>Картофель</li>
          <li>Что-то там ещё</li>
        </ol>
      </li>
      <li>Помидоры</li>
      <li>
        Яблоки
        <ol>
          <li>Красные</li>
          <li>Зелёные</li>
        </ol>
      </li>
    </ul>
  </body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlListsConstructor (lines: List<String>, index: Int): String{
    val sb = StringBuilder()
    if (lines[0][index] == '*') sb.append("<ul>")
    if (lines[0][index] in '1'..'9') sb.append("<ol>")
    var label = true
    for (i in 0..lines.size - 1){
        val temp = lines[i].filter {it !in "123456890. "}
        if (lines[i][index] != ' '){
            label = true
            sb.append("<li>$temp")
            if (i == lines.size - 1 || lines[i + 1][index] != ' ') sb.append("</li>")
        }
        if (lines[i][index] == ' ' && label) {
            val list = mutableListOf(lines[i])
            var k = i + 1
            while (k <= lines.size - 1 && lines[k][index] == ' ') {
                list.add(lines[k])
                k++
            }
            label = false
            sb.append(markdownToHtmlListsConstructor(list, index + 4))
            sb.append("</li>")
        }
    }
    if (lines[0][index] == '*') sb.append("</ul>")
    if (lines[0][index] in '1'..'9') sb.append("</ol>")
    return sb.toString().split("<li>*").joinToString(separator = "<li>")
}

fun markdownToHtmlLists(inputName: String, outputName: String) {
    val lines = File(inputName).readLines()
    val outputStream = File(outputName).bufferedWriter()
    val text = markdownToHtmlListsConstructor(lines, 0)
    outputStream.write("<html><body>$text</body></html>")
    outputStream.close()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    val lines = File(inputName).readLines()
    val outputStream = File(outputName).bufferedWriter()
    outputStream.write("<html><body>")
    var labelLists = true
    var labelSimple = true
    for (i in 0..lines.size - 1){
        when{
            lines[i].isEmpty() && i != lines.size - 1-> {
                outputStream.newLine()
                labelLists = true
                labelSimple = true
            }
            lines[i].trim()[0] != '*' && lines[i].trim()[0] !in '1'..'9' && labelSimple-> {
                labelSimple = false
                labelLists = true
                val list = mutableListOf(lines[i])
                var k = i + 1
                while (k <= lines.size - 1 && lines[k].isNotEmpty() &&
                        (lines[k][0] != '*' && lines[k][0] !in '1'..'9')) {
                    list.add(lines[k])
                    k++
                }
                outputStream.write("<p>")
                outputStream.write(markdownToHtmlSimpleConstructor(list))
                outputStream.write("</p>")
            }
            (lines[i][0] == '*' || lines[i][0] in '1'..'9') && labelLists -> {
                labelLists = false
                labelSimple = true
                val list = mutableListOf(lines[i])
                var k = i + 1
                while (k <= lines.size - 1 && lines[k].isNotEmpty() && (lines[k][0] in "* " || lines[k][0] in '0'..'9')) {
                    list.add(lines[k])
                    k++
                }
                outputStream.write(markdownToHtmlListsConstructor(list, 0))
                if (k != lines.size - 1) outputStream.newLine()
            }
        }
    }
    outputStream.write("</body></html>")
    outputStream.close()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
   19935
*    111
--------
   19935
+ 19935
+19935
--------
 2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
  235
*  10
-----
    0
+235
-----
 2350
 *
 */
fun spaceConstructor(number: Int): String{
    val sb = StringBuilder()
    for (i in 1..number){
        sb.append(" ")
    }
    return sb.toString()
}
fun lineConstructor(number: Int): String{
    val sb = StringBuilder()
    for (i in 1..number){
        sb.append("-")
    }
    return sb.toString()
}

fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    val length = (lhv * rhv).toString().length + 1
    val outputStream = File(outputName).bufferedWriter()
    val rhvString = rhv.toString()
    outputStream.write(spaceConstructor(length - lhv.toString().length))
    outputStream.write("$lhv\n*")
    outputStream.write(spaceConstructor(length - 1 - rhvString.length))
    outputStream.write("$rhv\n")
    outputStream.write(lineConstructor(length))
    outputStream.newLine()
    var k = 1
    for (i in rhvString.length - 1 downTo 0){
        if (i == rhvString.length - 1)outputStream.write(" ") else outputStream.write("+")
        outputStream.write(spaceConstructor(length - k - (lhv * rhvString[i].toString().toInt()).toString().length))
        outputStream.write((lhv * rhvString[i].toString().toInt()).toString())
        outputStream.newLine()
        k++
    }
    outputStream.write(lineConstructor(length))
    outputStream.newLine()
    outputStream.write(" ")
    outputStream.write((lhv * rhv).toString())
    outputStream.close()
}


/**
 * Очень сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
  19935 | 22
 -198     906
 ----
    13
    -0
    --
    135
   -132
   ----
      3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

