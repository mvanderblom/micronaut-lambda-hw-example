package nl.mvdb.micronaut.hw.lambda.web

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller
class HelloWorldController {
    @Get
    fun sayHello(): Map<String, String> {
        return mapOf("message" to "Hello World")
    }
}