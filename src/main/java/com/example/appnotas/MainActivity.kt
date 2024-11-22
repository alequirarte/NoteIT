package com.example.appnotas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.appnotas.navigation.NavManager
import com.example.appnotas.room.NotaDatabase
import com.example.appnotas.ui.theme.AppNotasTheme
import com.example.appnotas.viewmodels.NotaViewModel

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            context = applicationContext,
            klass = NotaDatabase::class.java,
            name = "products.db"
        ).fallbackToDestructiveMigration()

    }

    // Método que es llamado al crear la activity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        // Instancia del viewModel.
        val viewModel: NotaViewModel = NotaViewModel(db.build().notesDao())

        // Establecer el contenido de la aplicación.
        setContent {
            AppNotasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavManager(viewModel = viewModel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}


