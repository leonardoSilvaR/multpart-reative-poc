package com.reactive.client.controller

import com.reactive.client.controller.json.UserRequest
import com.reactive.client.proxy.OperationProxy
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/v1/transactions")
class TransactionController(
        private val operationProxy: OperationProxy
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createOperation(
            @RequestPart(name = "user") user: UserRequest,
            @RequestPart(name = "file") file: FilePart
    ): Mono<String> {
        logger.info("Operation request creation received")

        return operationProxy
                .sendToOperation(file, user)
                .flatMap {
                    Mono.just(it)
                }
    }

    @PostMapping("/non-reactive",consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createOperationNonReactive(
            @RequestPart(name = "user") user: UserRequest,
            @RequestPart(name = "file") file: FilePart
    ): Mono<String> {
        logger.info("Operation request creation received")

        return operationProxy
                .sendToOperationNonReactive(file, user)
                .flatMap {
                    Mono.just(it)
                }
    }
}