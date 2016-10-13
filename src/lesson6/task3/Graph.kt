package lesson6.task3

import java.util.*

class Graph {
    private data class Vertex(val name: String) {
        val neighbors = mutableSetOf<Vertex>()
    }

    private val vertices = mutableMapOf<String, Vertex>()

    private operator fun get(name: String) = vertices[name] ?: throw IllegalArgumentException()

    fun addVertex(name: String) {
        vertices[name] = Vertex(name)
    }

    private fun connect(first: Vertex, second: Vertex) {
        first.neighbors.add(second)
        second.neighbors.add(first)
    }

    fun connect(first: String, second: String) = connect(this[first], this[second])

    /**
     * Пример
     *
     * По двум вершинам рассчитать расстояние между ними = число дуг на самом коротком пути между ними.
     * Вернуть -1, если пути между вершинами не существует.
     *
     * Используется поиск в ширину
     */
    fun bfs(start: String, finish: String) = bfs(this[start], this[finish])

    fun bfsPath(start: String, finish: String) = bfsPath(this[start], this[finish])

    private fun bfs(start: Vertex, finish: Vertex): Int {
        val queue = ArrayDeque<Vertex>()
        queue.add(start)
        val visited = mutableMapOf(start to 0)
        while (queue.isNotEmpty()) {
            val next = queue.poll()
            val distance = visited[next]!!
            if (next == finish) return distance
            for (neighbor in next.neighbors) {
                if (neighbor in visited) continue
                visited.put(neighbor, distance + 1)
                queue.add(neighbor)
            }
        }
        return -1
    }
    //Алгоритм аналагичный реализованному вами поиску в ширину, только возвращающий путь, в качестве результата
    private fun bfsPath(start: Vertex, finish: Vertex): List<String> {
        val queue = ArrayDeque<Vertex>()
        //Предыдущая обработанная вершина
        queue.add(start)
        val visited = mutableMapOf<Vertex,MutableList<String>>()
        while (queue.isNotEmpty()) {
            val next = queue.poll()
            if (next == finish) return visited[next] ?: listOf()
            for (neighbor in next.neighbors) {
                if (neighbor in visited) continue
                //Сохраняем путь до вершины
                //Довольно интересно было на практике, при поиске ошибки, узнать, что в Котлине,
                //как и в C# листы не копируются через оператор "=" напряую, а копируется лишь сслыка на них
                val path = visited[next]?.take(visited[next]?.size ?: 0)?.toMutableList() ?: mutableListOf()
                path.add(next.name)
                visited.put(neighbor, path)
                queue.add(neighbor)
            }
        }
        return listOf()
    }

    /**
     * Пример
     *
     * По двум вершинам рассчитать расстояние между ними = число дуг на самом коротком пути между ними.
     * Вернуть -1, если пути между вершинами не существует.
     *
     * Используется поиск в глубину
     */
    fun dfs(start: String, finish: String): Int = dfs(this[start], this[finish], setOf()) ?: -1

    private fun dfs(start: Vertex, finish: Vertex, visited: Set<Vertex>): Int? =
            if (start == finish) 0
            else {
                val min = start.neighbors.filter { it !in visited }
                        .map { dfs(it, finish, visited + start) }
                        .filterNotNull().min()
                if (min == null) null else min + 1
            }
}
