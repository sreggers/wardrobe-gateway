package be.kwispel.wardrobegateway.contoller

import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController("/item")
class ItemController {
    @GetMapping("/hello")
    fun helloWorld(principal: Principal): ResponseEntity<String> {
        val token = principal as JwtAuthenticationToken
        return ResponseEntity.ok("Hello " + token.tokenAttributes["name"])
    }
}