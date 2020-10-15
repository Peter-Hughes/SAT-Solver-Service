package performance

import solver.callSolverExe
import solver.listAllFiles

fun main() {
    val dir = "src/test/resources"

    val solvers = listAllFiles("$dir/solvers")

    solvers.forEach { solver ->
        println("======================\n$solver \n======================")

        simpleMeasure {
            callSolverExe(dir, solver)
        }
    }

}


