package com.example.clientdemo

import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlow

/*
This client calls the users API
 */
@Service
class UserClient {
    fun fetchUsers(): List<User>? {
        val webClient = WebClient.create("http://localhost:8090")
        val users = runBlocking {
            webClient.get().uri("api/v2/users")
                    .header(HttpHeaders.CONTENT_TYPE, "application/json")
                    .retrieve()
                    .bodyToFlow<User>()
                    .toList()
        }
        return users


    }

    /*
    * Only read what you need!
    * Notice that this consumer only cares about the `fullName` of the user
    * there is no reference to `phoneNumber` or `favoriteColor` - this is good!
    * By only taking the elements you need & ignoring anything you don’t,
    * you make your code more resilient to any changes made on the producer side.
    * TODO: @IgnoreUnknown is not necessary
    */
    data class User(var fullName: String)
}
