package com.example.clientdemo

import com.example.clientdemo.UserClient.User
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH,
        ids = ["com.example:api-resiliency-demo:+:stubs:8090"],
        mappingsOutputFolder = "build/mappings",
        generateStubs = true
)
class UserClientContractTest {

    @Autowired
    lateinit var userClient: UserClient

    @Test
    fun `should fetch users`() {
        val actualUsers = userClient.fetchUsers()

        val expectedUsers = listOf(
                User("Teri Dactyl"), User("Liz Erd")
        )

        assertEquals(expectedUsers, actualUsers)
    }
}
