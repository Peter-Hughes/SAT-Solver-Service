package fileIO

import arrow.core.Either
import org.junit.jupiter.api.Test
import solver.listAllFiles
import java.io.FileNotFoundException

class FileIOTest {
    private val workingDirectory = "src/test/resources"

    init {
        writeFile("$workingDirectory/deleteFiles/delete.txt", "A")
    }

    @Test
    fun `Can read a file successfully`() {
        val expectedValue = "hello world"

        val actualValue: Either<FileNotFoundException, String> = readFile("$workingDirectory/files/text.txt")

        actualValue.fold(
            { throw it },
            { fileContent -> assert(fileContent == expectedValue) }
        )
    }

    @Test
    fun `Throws exception when file can't be read`() {
        val actualValue: Either<FileNotFoundException, String> = readFile("$workingDirectory/files/A.txt")

        actualValue.fold({ assert(it.message == "File could not be read") }, { assert(false) })
    }

    @Test
    fun `Can delete a file successfully`() {
        val dir = "$workingDirectory/deleteFiles"

        deleteFiles(dir)

        assert(listAllFiles(dir).isEmpty())
    }

}