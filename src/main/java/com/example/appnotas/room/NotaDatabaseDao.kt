package com.example.appnotas.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appnotas.model.Nota
import kotlinx.coroutines.flow.Flow

@Dao
interface NotaDatabaseDao {

    @Query("SELECT * FROM notas")
    fun getNotes(): Flow<List<Nota>>

    @Query("SELECT * FROM notas WHERE id = :id")
    fun getNoteById(id: Int): Flow<Nota>

    @Insert(entity = Nota::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addNote(note: Nota)

    @Update(entity = Nota::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun  updateNote(note: Nota)

    @Delete
    suspend fun deleteNote(note: Nota)

}




