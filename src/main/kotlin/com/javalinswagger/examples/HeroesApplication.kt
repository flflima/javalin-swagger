package com.javalinswagger.examples

import com.javalinswagger.swagger
import io.javalin.Context
import io.javalin.Javalin
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.servers.Server
import io.swagger.v3.oas.annotations.tags.Tag
import org.eclipse.jetty.http.HttpStatus
import javax.ws.rs.GET
import javax.ws.rs.Path


@OpenAPIDefinition(
    info = Info(
        title = "Heroes API",
        version = "0.1",
        description = "Super API of Heroes",
        license = License(name = "Apache 2.0")
    ),
    servers = [
        Server(
            description = "Heroes Server",
            url = "http://localhost:7000"
        )
    ]
)
@Path("/hero")
@Tag(name = "Heroes API v0.0.1")
class HeroController {

    @GET
    @Path("/{id}")
    @Operation(summary = "Find a hero by hero id")
    @Parameter(`in` = ParameterIn.PATH, name = "id")
    fun findHero(@Parameter(hidden = true) context: Context) {
        val heroId = context.pathParam("id")
        context.status(HttpStatus.OK_200)
    }

}


fun main() {
    val app = Javalin.create().swagger()
    app.get("/hero/:id", HeroController()::findHero)
    app.start()
}