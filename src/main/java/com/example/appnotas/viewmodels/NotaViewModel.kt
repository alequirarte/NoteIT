package com.example.appnotas.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnotas.model.Nota
import com.example.appnotas.room.NotaDatabaseDao
import com.example.appnotas.state.NotaState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NotaViewModel(
    private val dao: NotaDatabaseDao
    ) : ViewModel() {
    // Estado del modelo.
    var estado by mutableStateOf(NotaState())
        private set

    // Inicializar del view model.
    init {
        viewModelScope.launch {
            estado = estado.copy(
                estaCargando = true
            )
            // Obtener la lista de notas en la base de datos.
            dao.getNotes().collectLatest {
                estado = estado.copy(
                    notas = it,
                    estaCargando = false
                )
            }

        }
    }

    fun getNoteById(id: Int): Nota? {
        return estado.notas.find {
            id == it.id
        }
    }

    fun addNote(note: Nota) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.addNote(note)
        }
    }

    fun updateNote(note: Nota) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.updateNote(note)
        }
    }

    fun deleteNote(note: Nota) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteNote(note)
        }
    }
}




