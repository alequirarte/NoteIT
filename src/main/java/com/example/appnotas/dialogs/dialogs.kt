package com.example.appnotas.dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable


@Composable
fun AlertaEliminar(
    onConfirmDelete: () -> Unit,
    onCancelDelete: () -> Unit,
    showDialog: Boolean
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = "Eliminar Nota")
            },
            text = {
                Text("¿Estás seguro de que deseas eliminar esta nota?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmDelete()  // Acción cuando se confirma la eliminación
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onCancelDelete()  // Acción cuando se cancela la eliminación
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}