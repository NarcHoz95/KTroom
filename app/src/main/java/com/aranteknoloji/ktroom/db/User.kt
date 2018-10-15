package com.aranteknoloji.ktroom.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class User(
        @PrimaryKey(autoGenerate = true)
        var uid: Int?,

        var firstname: String,

        var lastname: String
)