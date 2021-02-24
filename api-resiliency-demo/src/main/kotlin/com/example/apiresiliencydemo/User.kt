package com.example.apiresiliencydemo

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(

        var firstName: String,

        var lastName: String,

        var phoneNumber: String,

        var favoriteColor: String,

        /*
        * We don't need to couple our domain model to our database model/tables/column names.
        * Just because the database column name needs to be 'timestamp' doesn't
        * mean our domain model needs to have the same name.
        */
        @Column(name = "timestamp")
        var creationDate: LocalDate = LocalDate.now(),

        var luckyNumber: Double? = null,

        @Id()
        @GeneratedValue()
        var id: Int? = null

)