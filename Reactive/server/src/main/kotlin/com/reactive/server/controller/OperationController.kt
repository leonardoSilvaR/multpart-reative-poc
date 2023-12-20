package com.reactive.server.controller

import com.reactive.server.controller.json.UserRequest
import org.slf4j.LoggerFactory
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets


@RestController
@RequestMapping("/v1/operations")
class OperationController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createOperation(
            @RequestPart(name = "user") user: UserRequest,
            @RequestPart(name = "file") file: FilePart
    ): Mono<String> {
        logger.info("Operation Creation Request by user:${user.name}")

        return file.content().map{
            val bytes = ByteArray(it.readableByteCount())
            it.read(bytes)
            DataBufferUtils.release(it)
            val content = String(bytes, StandardCharsets.UTF_8)
            return@map content
        }.next()

    }
}