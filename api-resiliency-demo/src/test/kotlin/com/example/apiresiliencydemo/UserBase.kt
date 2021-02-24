package com.example.apiresiliencydemo

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringRunner


/*
* This is the base class provided for the setup of the generated Spring Cloud Contract tests.
* To see the tests, build the app & look under 'Build -> generated-test-sources'
* We are using @SpringBootTest to start up the application context.
* Notice that nothing is mocked here. We are using the real controller,
* service layer & database, making this this test more of an end-to-end contract.
* This test validates the API all the way through the database interaction.
 */

@SpringBootTest
@RunWith(SpringRunner::class)
//here, we insert test data before our tests run & clean up after
@SqlGroup(
        Sql(scripts = ["/sql/insert_user_test_data.sql"], executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        Sql(scripts = ["/sql/delete_users_test_data.sql"], executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
)
abstract class UserBase {

    @Autowired
    lateinit var userController: UserController

    @Before
    fun setup() {
        RestAssuredMockMvc.standaloneSetup(userController)
    }

}