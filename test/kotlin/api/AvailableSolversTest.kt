package api

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Test
import solver.listAllFiles

class AvailableSolversTest {

    @Test
    fun `Get all available solvers, returns a list of available solvers`() {
        val expectedBody = "[expMC_minisat.exe, glucose.exe, lingeling.exe, minisat.exe]"

        val contractRoute = availableSolvers(::listAllFiles, "src/test/resources/solvers")
        val response = contractRoute(Request(GET, "/availableSolvers"))

        assert(response.status == OK)
        assert(response.bodyString() == expectedBody)
    }
}