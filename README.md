# JavalinSwagger

### Simple example of Javalin integration with Swagger using Kotlin

This approach uses [Swagger 2.X Annotations](https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations) to generate a api specification.

## Features
- Enable openAPI endpoint for javalin
- Enable swagger-ui endpoint for javalin

## Usage example

### Controller
```java
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
```

### Create and start server
```java
val app = Javalin.create().swagger()
    app.get("/hero/:id", HeroController()::findHero)
    app.start()
```

Try access:

http://localhost:7000/openapi.json for specification
or
http://localhost:7000/swagger-ui for swagger-ui