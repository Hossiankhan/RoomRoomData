package com.nnetwork.roomdatabase2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class note(

    @PrimaryKey(autoGenerate = true)

    var noteid1: Int =0,

    var title: String,
    var time: String,
    var date: String,
    var spinn: String,

    )
