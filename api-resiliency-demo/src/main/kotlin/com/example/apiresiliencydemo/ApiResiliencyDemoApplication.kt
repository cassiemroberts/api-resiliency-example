package com.example.apiresiliencydemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiResiliencyDemoApplication

fun main(args: Array<String>) {
	runApplication<ApiResiliencyDemoApplication>(*args)
}
