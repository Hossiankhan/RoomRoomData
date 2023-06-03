package com.nnetwork.roomdatabase2


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {


    @Insert
    fun insertData(note: note)

    @Update
    fun updateNote(note: note)

    @Delete
    fun deleteNote(note: note)

    @Query("SELECT*FROM note")
    fun getAllNote(): List<note>

    @Query("SELECT* FROM note WHERE noteid1 IN (:id)")
    fun getNoteById(id: List<Int>): List<note>


}