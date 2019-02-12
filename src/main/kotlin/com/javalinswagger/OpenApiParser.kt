package com.javalinswagger

import io.swagger.v3.jaxrs2.Reader
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.OpenAPI
import org.reflections.Reflections
import javax.ws.rs.Path

object OpenApiParser {

    private val defaultAnonations = mutableSetOf(
        OpenAPIDefinition::class.java,
        Path::class.java
    )

    fun parse(packagePrefix: String): OpenAPI {
        val reflections = Reflections(packagePrefix)
        val types = typesAnnotatedWith(reflections, defaultAnonations)
        val reader = Reader()
        return reader.read(types)
    }

    private fun typesAnnotatedWith(
        reflections: Reflections,
        annotations: Set<Class<out Annotation>>
    ): Set<Class<*>> {
        val types = mutableSetOf<Class<*>>()
        for (annotation: Class<out Annotation> in annotations) {
            types.addAll(reflections.getTypesAnnotatedWith(annotation))
        }
        return types
    }

}