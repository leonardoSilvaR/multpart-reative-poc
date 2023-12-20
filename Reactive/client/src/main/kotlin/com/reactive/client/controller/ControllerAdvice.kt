package com.reactive.client.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerAdvice {

    private val logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(Exception::class)
    fun serverException(ex:Exception){
        logger.error("Ex ", ex)
    }

}