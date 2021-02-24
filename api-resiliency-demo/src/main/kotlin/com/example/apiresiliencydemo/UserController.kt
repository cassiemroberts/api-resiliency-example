package com.example.apiresiliencydemo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(val userService: UserService){

    /*
    * notice the API version is in the URL
    * notice the corresponding V1 endpoint & response
     */
    @GetMapping("api/v1/users")
    fun getAllUsers(): List<UserResponseV1> {
        val users = userService.findAll()
        return  users.map { convertToUserResponse(it) }
    }

    /*
    * a breaking change was introduced, so the version was bumped to V2
    * notice the corresponding V2 endpoint & response
     */
    @GetMapping("api/v2/users")
    fun getAllUsersV2(): List<UserResponseV2> {
        val users = userService.findAll()
        return  users.map { convertToUserResponseV2(it) }
    }

    @PostMapping("api/v1/users")
    fun saveNewUser(@RequestBody userRequestV1: UserRequestV1): ResponseEntity<User> {
        val newUser = userService.save(buildNewUser(userRequestV1))
        return ResponseEntity(newUser, HttpStatus.CREATED)
    }


    private fun convertToUserResponse(user: User): UserResponseV1 {
        return UserResponseV1(
                name = user.firstName + " " + user.lastName,
                phoneNumber = user.phoneNumber,
                favoriteColor = user.favoriteColor
        )
    }

    private fun convertToUserResponseV2(user: User): UserResponseV2 {
        return UserResponseV2(
                fullName = user.firstName + " " + user.lastName,
                phoneNumber = user.phoneNumber,
                favoriteColor = user.favoriteColor
        )
    }

    private fun buildNewUser(userRequest: UserRequestV1): User {
        return User(
                firstName = userRequest.firstName,
                lastName = userRequest.lastName,
                phoneNumber = userRequest.phoneNumber,
                favoriteColor = userRequest.favoriteColor
        )
    }

}

//NOTE: 👇 data classes living inside this controller just for convenience & demonstration purposes

/*
 * Notice that the API returns LESS data than the User domain model has.
 * Producers should only return what their consumers actually need.
 * This makes producers more flexible in what they can change without having to release a new major version.
 */
data class UserResponseV1(val name: String, val phoneNumber: String, val favoriteColor: String)

/*
 * notice the difference between the V1 & V2 response
 * 'name' has been changed to 'fullName' which is a breaking change
 */
data class UserResponseV2(val fullName: String, val phoneNumber: String, val favoriteColor: String)

data class UserRequestV1(val firstName: String, val lastName: String, val phoneNumber: String, val favoriteColor: String)