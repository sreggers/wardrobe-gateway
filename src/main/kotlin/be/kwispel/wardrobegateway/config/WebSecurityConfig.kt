package be.kwispel.wardrobegateway.config

import be.kwispel.wardrobegateway.jwt.JwtAuthConverter
import be.kwispel.wardrobegateway.jwt.JwtAuthConverterProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@Configuration
@EnableConfigurationProperties(JwtAuthConverterProperties::class)
@EnableWebSecurity
class WebSecurityConfig(val jwtAuthConverter: JwtAuthConverter) {
    companion object {
        const val ADMIN = "admin";
        const val USER = "user";
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.cors()
        http.authorizeHttpRequests { auth -> auth
            .requestMatchers(HttpMethod.GET, "/item/**", "/set/**").hasRole(USER)
            .anyRequest().authenticated()
        }
        http.oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthConverter)

        return http.build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val configuration = CorsConfiguration()
        configuration.allowedHeaders = listOf("Authorization", "Cache-Control", "Content-Type")
        configuration.allowedOrigins = listOf("http://localhost:5173")
        configuration.allowedMethods = listOf("GET", "POST")
        configuration.allowCredentials = true
        configuration.exposedHeaders = listOf("Authorization")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}