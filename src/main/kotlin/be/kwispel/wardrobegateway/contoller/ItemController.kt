package be.kwispel.wardrobegateway.contoller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController("/item")
class ItemController {
    @GetMapping("/")
    fun helloWorld() = "Hello World!"
}