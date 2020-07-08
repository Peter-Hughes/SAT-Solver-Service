package fileIO

import arrow.core.Either
import java.io.File
import java.io.FileNotFoundException

// Delete files under a given directory, returns a sequence of if files where deleted
fun deleteFiles(path: String) = File(path)
    .walk()
    .filter { it.isFile }
    .forEach { it.delete() }

fun writeFile(path: String, input: String) = File(path).writeText(input)

fun readFile(fileName: String): Either<FileNotFoundException, String> {
    val file = File(fileName)
    return when {
        file.exists() -> Either.right(file.readText())
        else -> Either.left(FileNotFoundException("File could not be read"))
    }
}
