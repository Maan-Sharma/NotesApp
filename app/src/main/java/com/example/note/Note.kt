package com.example.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notestable")
class Note(
    @ColumnInfo(name = "text")
    val text: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int
)