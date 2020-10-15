package api

import arrow.core.Either
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Status
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException

class SATSolverTest {

    @Test
    fun `Solver route returns 200 when solve function successfully solves`() {
        val solver = "solver"
        val body = "body"
        val expectedBody = "$solver $body"

        val contractRoute = solver(
            solve = { a, b -> Either.right("$a $b") },
            formatOutput = { _, result -> result }
        )
        val response = contractRoute(Request(POST, "solve/$solver").body(body))

        assert(response.status == Status.OK)
        assert(response.bodyString() == expectedBody)
    }

    @Test
    fun `Solver route returns 400 when solve function throws exception`() {
        val expectedBody = "Solver encountered an error"

        val contractRoute = solver(
            solve = { _, _ -> Either.left(FileNotFoundException("A")) },
            formatOutput = { _, result -> result }
        )
        val response = contractRoute(Request(POST, "solve/solver").body("body"))

        assert(response.status == Status.BAD_REQUEST)
        assert(response.bodyString() == expectedBody)
    }
}
