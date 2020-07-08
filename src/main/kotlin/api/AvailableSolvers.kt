package api

import org.http4k.contract.ContractRoute
import org.http4k.contract.meta
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK

fun availableSolvers(getSolvers: (String) -> List<String>, path: String = "src/main/resources/solvers"): ContractRoute {

    fun response(): HttpHandler = {
        Response(OK).body(getSolvers(path).toString())
    }

    return "/availableSolvers" meta {
        summary = "Get all available solvers"
        description = "Gets a list of all the available solver names"
        returning(OK to "A list of all the available solver names")
    } bindContract GET to response()
}