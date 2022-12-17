import java.util.PriorityQueue

fun main() {
    fun part1(input: List<String>): Int {
        var acc = 0;
        val heap = PriorityQueue<Int>()
        input.forEach {
            if (it.isEmpty()) {
                heap.add(acc)
                acc = 0
            } else {
                acc += it.toInt()
            }
        }

        println(heap)

        return heap.max()
    }

    fun part2(input: List<String>): Int {
        var acc = 0;
        val heap = PriorityQueue<Int> { i, j -> j - i }
        input.forEach {
            if (it.isEmpty()) {
                heap.add(acc)
                acc = 0
            } else {
                acc += it.toInt()
            }
        }

        return (0..3).reduce { acc, _ ->
            acc + heap.remove()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24_000)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
