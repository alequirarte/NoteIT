package com.example.appnotas.state

import com.example.appnotas.model.Nota

data class NotaState(
    val notas: List<Nota> = emptyList(),
    val estaCargando: Boolean = true,
)