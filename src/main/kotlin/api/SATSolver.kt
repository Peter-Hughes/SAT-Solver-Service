package api

import arrow.core.Either
import org.http4k.contract.ContractRoute
import org.http4k.contract.div
import org.http4k.contract.meta
import org.http4k.core.Body
import org.http4k.core.ContentType.Companion.TEXT_PLAIN
import org.http4k.core.HttpHandler
import org.http4k.core.Method.POST
import org.http4k.core.Response
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.BiDiBodyLens
import org.http4k.lens.Path
import org.http4k.lens.string
import java.io.FileNotFoundException

fun solver(
    solve: (String, String) -> Either<FileNotFoundException, String>,
    formatOutput: (String, String) -> String
): ContractRoute {
    // Extract problemModel from body
    val problemModel: BiDiBodyLens<String> = Body.string(TEXT_PLAIN).toLens()

    // Respond with the solution for a given problemModel
    fun response(solver: String): HttpHandler = { request ->
        // Get the solution for a given problem model
        solve(solver, problemModel(request)).fold(
            { Response(BAD_REQUEST).body("Solver encountered an error") },
            { Response(OK).body(formatOutput(solver, it)) }
        )
    }

    // Bind route to response function and creating documentation for swagger in the meta tag
    return "solve" /
            Path.of("solver", "Which solver to be used, full name required")  meta {
        summary = "Solve"
        description = "Solves a SAT problem using a specified solver"
        receiving(problemModel)
        returning(OK to "The solution")
        returning(BAD_REQUEST to "Solver encountered an error")
    } bindContract POST to ::response
}
