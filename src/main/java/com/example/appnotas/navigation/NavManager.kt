package com.example.appnotas.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.appnotas.viewmodels.NotaViewModel
import com.example.appnotas.views.EditView
import com.example.appnotas.views.FormView
import com.example.appnotas.views.HomeView
import com.example.appnotas.views.ListView


@Composable
fun NavManager(viewModel: NotaViewModel, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        composable<Home> {
            HomeView(navController)
        }
        composable<ListaNotas> {
            ListView(viewModel, navController)
        }
        composable<FormularioNotas> {
            FormView(navController, viewModel)
        }
        composable<EditarNota> { navBackStackEntry ->
            val args = navBackStackEntry.toRoute<EditarNota>()
            EditView(args.noteId, navController, viewModel)
        }
    }
}


