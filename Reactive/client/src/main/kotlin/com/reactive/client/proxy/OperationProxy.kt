package com.reactive.client.proxy

import com.reactive.client.controller.json.UserRequest
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.client.MultipartBodyBuilder
import org.springframework.http.codec.multipart.FilePart
import org.springframework.stereotype.Component
import org.springframework.web.accept.MappingMediaTypeFileExtensionResolver
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.net.URI


private const val OPERATION_NON_REACTIVE_HOST = "http://localhost:8083"
private const val OPERATION_HOST = "http://localhost:8080"
private const val OPERATION_PATH = "/v1/operations"

@Component
class OperationProxy {

    private val logger  = LoggerFactory.getLogger(javaClass)

    fun sendToOperation(file: FilePart, userRequest: UserRequest): Mono<String> {
        logger.info("Calling operation external service")

        val uri = UriComponentsBuilder
                .fromUri(URI.create(OPERATION_HOST + OPERATION_PATH))
                .build()
                .toUri()

        val bodyBuilder = MultipartBodyBuilder()
        bodyBuilder.part("file", file)
        bodyBuilder.part("user", userRequest).contentType(MediaType.APPLICATION_JSON)

        val request = WebClient.create()
                .post()
                .uri(uri)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))

        return request
                .retrieve()
                .bodyToMono(String::class.java)
    }

    fun sendToOperationNonReactive(file: FilePart, userRequest: UserRequest): Mono<String> {
        logger.info("Calling operation external service")

        val uri = UriComponentsBuilder
                .fromUri(URI.create(OPERATION_NON_REACTIVE_HOST + OPERATION_PATH))
                .build()
                .toUri()

        val bodyBuilder = MultipartBodyBuilder()
        bodyBuilder.part("file", file)
        bodyBuilder.part("user", userRequest).contentType(MediaType.APPLICATION_JSON)

        val request = WebClient.create()
                .post()
                .uri(uri)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))

        return request
                .retrieve()
                .bodyToMono(String::class.java)
    }

}