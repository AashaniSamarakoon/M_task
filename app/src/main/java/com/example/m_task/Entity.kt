package com.example.m_task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "To_Do")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var title:String = "",
    var priority:String = ""
)