package solver

import arrow.core.Either
import fileIO.deleteFiles
import fileIO.readFile
import fileIO.writeFile
import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.TimeUnit

fun solveProblemModel(solver: String, problemModel: String): Either<FileNotFoundException, String> {
    val dir = "src/main/resources"
    // Delete any input/output files in directory already existing
    deleteFiles("$dir/files")
    // Create input file of the problem model
    writeFile("$dir/files/input.txt", problemModel)

    callSolverExe(dir, getOrElseSolver(dir, solver))
    // Read the file, returns either a file not found exception or the solution
    return readFile("$dir/files/output.txt")
}

// Call the a given solver in a working directory and return if command was executed
fun callSolverExe(workingDir: String, solver: String): Boolean = when (solver) {
    "minisat.exe" -> "$workingDir/solvers/$solver files/input.txt files/output.txt".runCommand(File(workingDir))
    "glucose.exe" -> "$workingDir/solvers/$solver -model".runCommand(File(workingDir))
    else -> "$workingDir/solvers/$solver".runCommand(File(workingDir))
}

// Extend string with a function to run a command in a given directory and to wait for
// command to finish
fun String.runCommand(workingDir: File): Boolean =
    ProcessBuilder(*split(" ").toTypedArray())
        .directory(workingDir)
        .redirectInput(File("$workingDir/files/input.txt"))
        .redirectOutput(File("$workingDir/files/output.txt"))
        .redirectError(File("$workingDir/files/error.txt"))
        .start()
        .waitFor(3, TimeUnit.MINUTES)

// Format the output of minisat solver as it is not in standard format
fun formatOutput(solver: String, solution: String): String = when (solver) {
    "minisat.exe" -> {
        solution.replaceFirst("\n", " ")
            .replaceAfter("\n", "")
            .replace(" ", "\nv ")
    }
    else -> solution
}
