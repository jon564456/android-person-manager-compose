package com.jbrigido.creatinglists.ui.screens.register

import Database
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jbrigido.creatinglists.domain.Person.Person
import kotlinx.coroutines.launch

/**
 * This composable function generates a screen
 * where a new person can be registered
 * @param onAddedPerson this lambda expression receives a person Object
 * @param onNavigateToList
 */
@Composable
fun RegisterScreen(onNavigateToList: () -> Unit) {
    var stateName by remember { mutableStateOf("") }
    var stateAge by remember { mutableStateOf("") }
    var stateGender by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = remember { Database.getInstance(context) }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val modifier: Modifier = Modifier.padding(10.dp)
            TextField(stateName, onValueChange = {
                stateName = it
            }, label = {
                Text("Insert your name here")
            }, modifier = modifier
            )

            TextField(stateAge, onValueChange = { value ->
                if (value.all { it.isDigit() }) {
                    stateAge = value
                }
            }, label = {
                Text("Insert your age here")
            }, keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ), modifier = modifier
            )

            TextField(stateGender, onValueChange = {
                stateGender = it
            }, label = {
                Text("Insert your Gender here")
            }, modifier = modifier
            )

            Row(
                Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        if (stateName.isNotEmpty() && stateAge.isNotEmpty() && stateGender.isNotEmpty()) {
                            val person = Person(
                                uid = 0,
                                name = stateName,
                                age = stateAge,
                                gender = stateGender
                            )

                            coroutineScope.launch {
                                db.personDAO().insert(person)
                            }
                            stateName = ""
                            stateAge = ""
                            stateGender = ""
                        }
                    }, modifier = modifier.weight(1f, true)
                ) {
                    Text("Save")
                }
                Button(
                    onClick = {
                        onNavigateToList()
                    }, modifier = modifier.weight(1f, true)
                ) { Text("Back") }
            }
        }
    }

}
