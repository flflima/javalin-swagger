package com.javalinswagger

import io.javalin.Context
import io.javalin.Handler
import io.javalin.core.util.OptionalDependency

class SwaggerRenderer(private val specUrl: String) : Handler {

    private val swaggerVersion = OptionalDependency.SWAGGERUI.version

    override fun handle(ctx: Context) {
        ctx.html(
            """
            <head>
                <meta charset="UTF-8">
                <title>Swagger UI</title>
                <link rel="icon" type="image/png" href="/webjars/swagger-ui/$swaggerVersion/favicon-16x16.png" sizes="16x16" />
                <link rel="stylesheet" href="/webjars/swagger-ui/$swaggerVersion/swagger-ui.css" >
                <script src="/webjars/swagger-ui/$swaggerVersion/swagger-ui-bundle.js"></script>
                <style>body{background:#fafafa;}</style>
            </head>
            <body>
                <div id="swagger-ui"></div>
                <script>
                    window.ui = SwaggerUIBundle({
                        url: "$specUrl",
                        dom_id: "#swagger-ui",
                        deepLinking: true,
                        presets: [SwaggerUIBundle.presets.apis],
                    });
                </script>
            </body>""".trimIndent()
        )
    }

}