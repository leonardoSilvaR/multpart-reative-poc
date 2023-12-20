package com.simple.server.controller

import com.simple.server.controller.json.UserRequest
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.nio.charset.StandardCharsets


@RestController
@RequestMapping("/v1/operations")
class OperationController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createOperation(
            @RequestPart(name = "user") user: UserRequest,
            @RequestPart(name = "file") file: MultipartFile
    ): ResponseEntity<String> {
        logger.info("Operation Creation Request by user:${user}")

        val content = String(file.bytes, StandardCharsets.UTF_8)
        return ResponseEntity.ok(content)

    }
}