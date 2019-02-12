package com.javalinswagger

import io.javalin.Javalin

fun Javalin.swagger(
    enable: Boolean = true,
    enableUiEndpoint: Boolean = true,
    enableJsonEndpoint: Boolean = true,
    packageToScan: String = "",
    apiFileName: String = "openapi.json",
    swaggerUIendPoint: String = "swagger-ui"
): Javalin {

    if (enable) {

        this.enableWebJars()

        val openApi = OpenApiParser.parse(packageToScan)

        this.routes {

            if (enableJsonEndpoint) {
                get(apiFileName) {
                    it.result(openApi.toJson()).contentType("application/json")
                }
            }

            if (enableUiEndpoint) {
                get(swaggerUIendPoint, SwaggerRenderer("/$apiFileName"))
            }

        }

    }

    return this
}