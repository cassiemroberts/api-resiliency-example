package com.example.apiresiliencydemo

import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    /*
    * notice 'business logic' goes here, not in the controller
    * keeping concerns organized will help you when you need to version the API
    * notice there is no code related to API versions here
    */
    fun save(user: User): User {

        if(user.luckyNumber == null) {
            user.luckyNumber = Math.random()
        }

        return userRepository.save(user)
    }

}
