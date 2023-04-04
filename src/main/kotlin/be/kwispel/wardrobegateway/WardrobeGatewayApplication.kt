package be.kwispel.wardrobegateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WardrobeGatewayApplication

fun main(args: Array<String>) {
	runApplication<WardrobeGatewayApplication>(*args)
}
