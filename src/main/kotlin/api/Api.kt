package api

import org.http4k.contract.contract
import org.http4k.contract.openapi.ApiInfo
import org.http4k.contract.openapi.v3.OpenApi3
import org.http4k.format.Jackson
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind
import org.http4k.routing.routes
import solver.formatOutput
import solver.listAllFiles
import solver.solveProblemModel

fun api(): RoutingHttpHandler = "/api" bind routes(
    contract {
        renderer = OpenApi3(
            ApiInfo(title = "SAT Solver Server API", version = "v1.0", description = "SAT Solver server API"),
            Jackson
        )
        descriptionPath = "/api-docs"
        routes += solver(::solveProblemModel, ::formatOutput)
        routes += availableSolvers(::listAllFiles)
    }
)