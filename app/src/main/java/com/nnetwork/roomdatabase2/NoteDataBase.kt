package com.nnetwork.roomdatabase2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object {
        var database: NoteDataBase? = null

        fun getDB(context: Context): NoteDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(context, NoteDataBase::class.java, "Note-db")
                    .allowMainThreadQueries().build()
                database as NoteDataBase
            } else {
                database as NoteDataBase
            }


        }


    }

}