package com.example.clientdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ClientDemoApplication

fun main(args: Array<String>) {
	runApplication<ClientDemoApplication>(*args)
}
