package com.example.appnotas.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appnotas.R
import com.example.appnotas.navigation.ListaNotas

@Composable
fun HomeView(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        BodyHome(navController)
    }
}

@Composable
fun BodyHome(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDBE2D9)), // Fondo principal
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(30.dp))

        // Logo Unison
        Image(
            painter = painterResource(id = R.drawable.logo_unison),
            contentDescription = "Logo",
            modifier = Modifier
                .size(300.dp)
        )



        // Título aplicación
        Text(
            text = "Note IT",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            color = Color(0xFF3A3A3A)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botón para ir a las notas
        Button(
            onClick = {
                navController.navigate(ListaNotas)
            },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(60.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6D8E6F),
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(10.dp)
        ) {
            Text(
                text = "Ir a Notas",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(170.dp))

        Text(
            text = "Quirarte Agüero Alejandra",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = Color(0xFF3A3A3A)
        )
    }
}