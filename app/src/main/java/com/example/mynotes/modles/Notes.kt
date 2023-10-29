package com.example.mynotes.modles

import android.graphics.drawable.Drawable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mynotes.R

@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val text:String,

)
