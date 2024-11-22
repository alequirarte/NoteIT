package com.example.appnotas.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appnotas.model.Nota


@Database(
    entities = [Nota::class],
    version = 3,
    exportSchema = false)
abstract class NotaDatabase : RoomDatabase() {
    abstract fun notesDao(): NotaDatabaseDao
}


