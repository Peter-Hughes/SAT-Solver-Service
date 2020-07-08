package solver

import java.io.File

// Lists all the file names in a given directory
fun listAllFiles(workingDir: String): List<String> = File(workingDir)
    .walk()
    .filter { it.isFile }
    .map { it.name }
    .toList()

// Get the solver or return the first available solver
fun getOrElseSolver(workingDir: String, solver: String): String =
    when (listAllFiles("$workingDir/solvers").contains(solver)) {
        true -> solver
        false -> listAllFiles("$workingDir/solvers").first()
    }
