package solver

import org.junit.jupiter.api.Test


class ListSolversTest {
    private val workingDirectory = "src/test/resources"

    @Test
    fun `List all solvers, lists all the solvers in the resource folder`(){
        val expectedNames = listOf("expMC_minisat.exe", "glucose.exe", "lingeling.exe", "minisat.exe")

        val solverNames = listAllFiles("$workingDirectory/solvers")

        assert(solverNames == expectedNames)
    }

    @Test
    fun `List all solvers, does not contain the parent directory`(){
        val parentDirectory = "solver"

        val solverNames = listAllFiles("$workingDirectory/solvers")

        assert(!solverNames.contains(parentDirectory))
    }

    @Test
    fun `Get solver or else to return the solver name if it is valid`(){
        val expectedName = "minisat.exe"

        val solverName = getOrElseSolver(workingDirectory, expectedName)

        assert(solverName == expectedName)
    }

    @Test
    fun `Get solver or else to return the default solver name if the solver is not valid`(){
        val solver = "A"
        val expectedName = "expMC_minisat.exe"

        val solverName = getOrElseSolver(workingDirectory, solver)

        assert(solverName == expectedName)
    }
}