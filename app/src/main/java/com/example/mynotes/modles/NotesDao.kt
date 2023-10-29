package com.example.mynotes.modles

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    @Insert
    suspend fun InsertNotes(notes: Notes)
    @Delete
    suspend fun DeleteNotes(notes: Notes)

    @Query("Select * from Notes order by id")
    fun getlistofNotes():LiveData<List<Notes>>

    @Query("SELECT * FROM Notes WHERE text LIKE :id")
    fun seacrhNotes(id:String):LiveData<List<Notes>>

    @Query("DELETE FROM Notes")
    suspend fun deleteAll()
    @Update
    suspend fun update(notes: Notes)


}