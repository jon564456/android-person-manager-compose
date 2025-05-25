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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jbrigido.creatinglists.Core.AppDatabase
import com.jbrigido.creatinglists.domain.Person.Person
import com.jbrigido.creatinglists.ui.components.PersonItem

@Composable
fun ListScreen(onNavigateToAddPerson: () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        var db: AppDatabase?
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            db = Database.getInstance(context)
            db!!.personDAO().getAll()
            val list: List<Person>? =

                DisplayInformation(list, Modifier.weight(1f, true))

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
                    if (list.isNotEmpty()) {
                        list.removeAt(list.lastIndex)
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