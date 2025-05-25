package com.jbrigido.creatinglists.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jbrigido.creatinglists.MainActivity.ListPerson
import com.jbrigido.creatinglists.MainActivity.RegisterPerson
import com.jbrigido.creatinglists.domain.Person.Person
import com.jbrigido.creatinglists.ui.screens.list.ListScreen
import com.jbrigido.creatinglists.ui.screens.register.RegisterScreen


/**
 *This composable function declares the navigation in the app
 */
@Composable
fun NavigateController() {
    val navController = rememberNavController()
    val list = remember { mutableStateListOf<Person>() }
    NavHost(navController = navController, startDestination = ListPerson) {
        composable<ListPerson> {
            ListScreen( onNavigateToAddPerson = {
                navController.navigate(RegisterPerson)
            })
        }
        composable<RegisterPerson> {
            RegisterScreen(onNavigateToList = {
                navController.navigate(ListPerson) {
                    popUpTo(RegisterPerson) {
                        inclusive = true
                    }
                }
            })
        }
    }
}