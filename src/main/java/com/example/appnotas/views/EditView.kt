package com.example.appnotas.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appnotas.dialogs.AlertaEliminar
import com.example.appnotas.model.Nota
import com.example.appnotas.navigation.ListaNotas
import com.example.appnotas.ui.theme.Color1
import com.example.appnotas.ui.theme.Color2
import com.example.appnotas.ui.theme.Color3
import com.example.appnotas.ui.theme.Color4
import com.example.appnotas.viewmodels.NotaViewModel
import kotlinx.coroutines.launch

@Composable
fun EditView(productId: Int, navController: NavController, viewModel: NotaViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        BodyEdit(nota = viewModel.getNoteById(productId), navController, viewModel, snackbarHostState)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyEdit(nota: Nota?, navController: NavController, viewModel: NotaViewModel, snackbarHostState: SnackbarHostState) {
    val coroutineScope = rememberCoroutineScope()
    var title by remember { mutableStateOf(nota?.titulo ?: "") }
    var description by remember { mutableStateOf(nota?.descripcion ?: "") }
    var selectedColor by remember { mutableStateOf(Color(nota?.color?.toInt() ?: 0xFFFFFF)) }
    var showDeleteDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7EEE5)) // Fondo principal
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(5.dp))

        // Flecha para regresar
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .align(Alignment.Start) 
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // Contenedor de la nota
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .shadow(8.dp, shape = RoundedCornerShape(12.dp))
                .background(selectedColor, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            
            Text(
                text = "Información de la nota",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4B4B4B),
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Campos del formulario
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                        .height(80.dp)
                        .padding(4.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(10.dp))
                    .padding(4.dp)
            ) {
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(4.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = Color.Transparent, 
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
        }

        // Selección de color
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            val colors = listOf(Color1, Color2, Color3, Color4)
            colors.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .shadow(8.dp, RoundedCornerShape(100))
                        .background(color, shape = RoundedCornerShape(50))
                        .border(
                            width = 2.dp,
                            color = if (selectedColor == color) Color.Black else Color.Transparent,
                            shape = RoundedCornerShape(50)
                        )
                        .clickable { selectedColor = color }
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Botones
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = {
                    viewModel.updateNote(Nota(id = nota?.id!!, titulo = title, descripcion = description,  color = selectedColor.toArgb().toString()))
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("Nota editada exitosamente")
                        navController.navigate(ListaNotas)
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .shadow(10.dp, RoundedCornerShape(40.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF608364),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Actualizar",
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { showDeleteDialog = true },
                modifier = Modifier
                    .weight(1f)
                    .shadow(10.dp, RoundedCornerShape(40.dp)),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE05E5E),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Eliminar",
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

    // Alerta de eliminación
    AlertaEliminar(
        onConfirmDelete = {
            viewModel.deleteNote(Nota(nota?.id!!, "", "", ""))
            coroutineScope.launch {
                snackbarHostState.showSnackbar("Nota eliminada exitosamente")
                navController.navigate(ListaNotas)
            }
            showDeleteDialog = false 
        },
        onCancelDelete = { showDeleteDialog = false }, 
        showDialog = showDeleteDialog
    )
}



