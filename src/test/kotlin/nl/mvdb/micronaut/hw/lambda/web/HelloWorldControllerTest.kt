package nl.mvdb.micronaut.hw.lambda.web

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import io.micronaut.function.aws.proxy.MockLambdaContext
import io.micronaut.function.aws.proxy.payload1.ApiGatewayProxyRequestEventFunction
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class HelloWorldControllerTest{
    private lateinit var handler: ApiGatewayProxyRequestEventFunction

    @BeforeAll
    fun setUp() {
        handler = ApiGatewayProxyRequestEventFunction()

    }

    @AfterAll
    fun tearDown() {
        handler.applicationContext.close()
    }

    @Test
    fun testHandler() {
        val request = APIGatewayProxyRequestEvent()
            .withHttpMethod("GET")
            .withPath("/")

        val response = handler.handleRequest(request, MockLambdaContext())

        assertThat(response.statusCode).isEqualTo(HttpStatus.OK.code)
        assertThat(response.body).isEqualTo("{\"message\":\"Hello World\"}")
    }
}