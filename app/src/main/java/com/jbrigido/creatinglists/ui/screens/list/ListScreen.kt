package com.jbrigido.creatinglists.ui.screens.list

import Database
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jbrigido.creatinglists.domain.Person.Person
import com.jbrigido.creatinglists.ui.components.PersonItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ListScreen(onNavigateToAddPerson: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        val context = LocalContext.current
        val db = remember { Database.getInstance(context) }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            val personlist = remember { mutableStateOf<List<Person>>(emptyList()) }

            LaunchedEffect(Unit) {
                db.personDAO().getAll().collect { people ->
                    personlist.value = people
                }
            }
            DisplayInformation(personlist.value, Modifier.weight(1f, true))



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {


                Button(onClick = {
                    onNavigateToAddPerson()
                }) {
                    Text("Add")
                }
                Button(onClick = {
                    val person = personlist.value.lastOrNull()
                    if (person != null) {
                        CoroutineScope(Dispatchers.IO).launch {
                            db.personDAO().delete(person)
                        }
                    }
                }) {
                    Text("Delete")
                }
            }
        }
    }
}


@Composable
fun DisplayInformation(list: List<Person>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(list) { item ->
            PersonItem(item)
        }
    }
}