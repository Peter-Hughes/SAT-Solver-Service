package solver

import fileIO.readFile
import org.junit.jupiter.api.Test

class SolverTest {
    private val workingDirectory = "src/test/resources/files"

    @Test
    fun `Expect minisat solver to solve an example input with the example output`() {
        val solver = "minisat.exe"
        val cnfModel = readFile("$workingDirectory/ExampleInput.txt").fold({ throw it }, { it })
        val expectedSolution = readFile("$workingDirectory/ExampleOut.txt")
            .fold({ throw it }, { it.replace("\r\n", "") })

        val solution = solveProblemModel(solver, cnfModel)

        solution.fold({ assert(false) }, {
            assert(expectedSolution == it.replace("\n", ""))
        })
    }

    @Test
    fun `Expect glucose solver to solve an example input with the example output`() {
        val solver = "glucose.exe"
        val cnfModel = readFile("$workingDirectory/ExampleInput.txt").fold({ throw it }, { it })
        val expectedSolution = readFile("$workingDirectory/ExampleOutDIMAC.txt")
            .fold({ throw it }, { it.replace("\r\n", "") })

        val solution = solveProblemModel(solver, cnfModel)

        expectedSolution.forEachIndexed { index, c ->  }

        solution.fold({ assert(false) }, {
            assert(expectedSolution == removeComments(it).replace("\n", ""))
        })
    }

    @Test
    fun `Expect lingeling solver to solve an example input with the example output`() {
        val solver = "expMC_minisat.exe"
        val cnfModel = readFile("$workingDirectory/ExampleInput.txt").fold({ throw it }, { it })
        val expectedSolution = readFile("$workingDirectory/ExampleOutDIMAC.txt")
            .fold({ throw it }, { it.replace("\r\n", "") })

        val solution = solveProblemModel(solver, cnfModel)

        solution.fold({ assert(false) }, {
            assert(expectedSolution == removeComments(it).replace("\n", ""))
        })
    }

    @Test
    fun `Format Output formats correctly when given minisat solver`() {
        val input = "SAT\n-1 -2 -3"

        val expected = """
            SAT
            v -1
            v -2
            v -3
        """.trimIndent()

        val actual = formatOutput("minisat.exe", input)

        assert(actual == expected)
    }

    @Test
    fun `Format Output formats correctly when not given minisat solver`() {
        val expected = "s SATISFIABLE\nv 1"

        val actual = formatOutput("A", expected)

        assert(actual == expected)
    }
}

fun removeComments(solution: String): String =
    solution
        .split("\n")
        .filter { !it.startsWith("c") &&  it !=  ""}
        .joinToString(",")
        .replace(",", "\n")
