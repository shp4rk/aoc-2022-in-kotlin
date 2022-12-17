fun main() {
    fun part1(input: List<String>): Int {
        var regX = 1;
        var acc = 0;
        var ip = 0;
        var busy = false;
        var insn: String = "noop"

        for (cycle in 1 until Int.MAX_VALUE) {
            if (cycle % 40 == 20)
                acc += cycle * regX

            if (!busy) {
                if (ip >= input.size)
                    break

                insn = input.getOrElse(ip % input.size) { _ -> "noop" }
                ip += 1

                if (insn != "noop") {
                    busy = true
                }
            } else {
                regX += insn.split(' ')[1].toInt()
                busy = false
            }
        }

        return acc
    }

    fun part2(input: List<String>): CharArray {
        var regX = 1;
        var ip = 0;
        var busy = false;
        var insn: String = "noop"
        var crt = CharArray(40 * 6) { '.' }

        for (cycle in 0 until 240) {
            val xOffset = cycle % 40

            if (regX - 1 <= xOffset && xOffset <= regX + 1) {
                println("$cycle $xOffset $regX")
                crt[cycle] = '#'
            }

            if (!busy) {
                if (ip >= input.size)
                    break

                insn = input.getOrElse(ip % input.size) { _ -> "noop" }
                ip += 1

                if (insn != "noop") {
                    busy = true
                }
            } else {
                regX += insn.split(' ')[1].toInt()
                busy = false
            }
        }

        return crt
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    // check(part1(testInput) == 24_000)
    part1(testInput).println()

    val input = readInput("Day10")
    part1(input).println()
    val crt = part2(input)
    for (row in 0 until 6) {
        for (col in 0 until 40) {
            print("${crt[row * 40 + col]}")
        }
        println()
    }
}
