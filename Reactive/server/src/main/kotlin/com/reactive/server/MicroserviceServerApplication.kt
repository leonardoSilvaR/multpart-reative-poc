package com.reactive.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MicroserviceServerApplication

fun main(args: Array<String>) {
	runApplication<MicroserviceServerApplication>(*args)
}
