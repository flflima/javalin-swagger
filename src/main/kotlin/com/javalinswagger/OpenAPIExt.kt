package com.javalinswagger

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.swagger.v3.oas.models.OpenAPI

fun OpenAPI.toJson(): String {
    val objectMapper = ObjectMapper()
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
    objectMapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
    return this.toJson(objectMapper)
}

fun OpenAPI.toJson(objectMapper: ObjectMapper): String {
    return objectMapper.writeValueAsString(this)
}