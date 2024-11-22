package com.example.appnotas.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appnotas.model.Nota
import com.example.appnotas.navigation.EditarNota
import com.example.appnotas.navigation.FormularioNotas
import com.example.appnotas.navigation.Home
import com.example.appnotas.viewmodels.NotaViewModel


@Composable
fun ListView(viewModel: NotaViewModel, navController: NavController) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(FormularioNotas) },
                containerColor = Color(0xFFF4D06F),
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        BodyList(viewModel, navController)
    }
}

@Composable
fun BodyList(viewModel: NotaViewModel, navController: NavController) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7EEE5)) // Fondo principal
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // Flecha para regresar
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    onClick = { navController.navigate(Home) },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }
        }



        // Sección de lista de notas (solo si hay notas)
        item {
            Text(
                text = "Notas Guardadas",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4B4B4B),
                modifier = Modifier
                    .padding(vertical = 16.dp),
            )
        }

        val estado = viewModel.estado
        // Estado cargando
        if (estado.estaCargando) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color.Gray)
                }
            }
        }
        // Estado sin productos
        else if (estado.notas.isEmpty()) {
            item {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "No hay notas guardadas.",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        // Estado con notas
        else {
            items(estado.notas) { nota ->
                NotaItem(
                    nota = nota,
                    navController
                )
            }
        }
    }
}



// Nota individual
@Composable
fun NotaItem(nota: Nota, navController: NavController) {
    val backgroundColor = Color(nota.color.toInt())

    Column(
        modifier = Modifier
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundColor)
            .clickable { navController.navigate(EditarNota(nota.id)) } // Al picarle se manda a EditarNota
            .padding(16.dp)

    ) {
        Text(
            text = nota.titulo,
            fontSize = 18.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

    }
}

