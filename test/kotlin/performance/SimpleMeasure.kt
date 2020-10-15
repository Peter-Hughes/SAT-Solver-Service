package performance


import java.util.ArrayList


/**
 * Iterates provided by [callback] code [iterations]x[testCount] times.
 * Performs warming by iterating [iterations]x[warmCount] times.
 */
fun simpleMeasure(iterations: Int = 10, testCount: Int = 10, warmCount: Int = 2, callback: () -> Unit) {
    val results = ArrayList<Long>()
    var totalTime = 0L
    var t = 0

    println("$PRINT_PREFIX -> Start")

    while (++t <= testCount + warmCount) {
        val startTime = System.currentTimeMillis()

        var i = 0
        while (i++ < iterations)
            callback()

        if (t <= warmCount) {
            println("$PRINT_PREFIX Warming $t of $warmCount")
            continue
        }

        val time = System.currentTimeMillis() - startTime
        println(PRINT_PREFIX + " " + time.toString() + "ms")

        results.add(time)
        totalTime += time
    }

    results.sort()

    val average = totalTime / testCount
    val median = results[results.size / 2]

    println("$PRINT_PREFIX -> average=${average}ms / median=${median}ms\n")
}

/**
 * Used to filter console messages easily
 */
private const val PRINT_PREFIX = "[TimeTest]"