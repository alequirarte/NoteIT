package com.example.appnotas.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object ListaNotas

@Serializable
object FormularioNotas

@Serializable
data class EditarNota(val noteId: Int)




