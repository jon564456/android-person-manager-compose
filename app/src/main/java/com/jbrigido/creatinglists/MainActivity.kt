package com.jbrigido.creatinglists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jbrigido.creatinglists.domain.Person
import com.jbrigido.creatinglists.ui.theme.CreatingListsTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {

    @Serializable
    object ListPerson

    @Serializable
    object RegisterPerson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CreatingListsTheme {
                NavigateController()
            }
        }
    }

    /**
     * This composable function generates a button with the text Add
     *  and navigates from this screen to the Register screen
     * @param modifier
     */
    @Composable
    fun CreatePerson(
        modifier: Modifier, navigateToRegister: () -> Unit
    ) {
        Button(modifier = modifier, onClick = {
            navigateToRegister()
        }) {
            Text("Add")
        }
    }

    /**
     * This composable function generates a button with the text Delete
     * and removes a person from the list
     * @param modifier
     */
    @Composable
    fun DeletePerson(modifier: Modifier, list: MutableList<Person>) {
        Button(modifier = modifier, onClick = {
            if (list.isNotEmpty()) {
                list.removeAt(list.lastIndex)
            }
        }) {
            Text("Delete")
        }
    }

    /**
     * This composable function generates a item with information
     * about a previously added person
     * @param person object with information about this person
     */
    @Composable
    fun PersonItem(person: Person) {
        val img = painterResource(R.drawable.spiderman)
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = img,
                contentScale = ContentScale.Crop,
                contentDescription = "SpiderMan Avatar",
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)
                    .clip(
                        RoundedCornerShape(100)
                    )
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    "Name: ${person.name}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    "Age:  ${person.age}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    "Genere: ${person.gender}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
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

    @Composable
    fun ListScreen(list: MutableList<Person>, onNavigateToAddPerson: () -> Unit) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                DisplayInformation(list, Modifier.weight(1f, true))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    CreatePerson(
                        Modifier
                            .weight(1f, true)
                            .padding(5.dp), onNavigateToAddPerson
                    )
                    DeletePerson(
                        Modifier
                            .weight(1f, true)
                            .padding(5.dp), list
                    )
                }
            }
        }
    }


    /**
     * This composable function generates a screen
     * where a new person can be registered
     * @param onAddedPerson this lambda expression receives a person Object
     * @param onNavigateToList
     */
    @Composable
    fun RegisterScreen(onAddedPerson: (Person) -> Unit, onNavigateToList: () -> Unit) {
        var stateName by remember { mutableStateOf("") }
        var stateAge by remember { mutableStateOf("") }
        var stateGender by remember { mutableStateOf("") }

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
                                    name = stateName, age = stateAge, gender = stateGender
                                )
                                onAddedPerson(person)
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

    /**
     *This composable function declares the navigation in the app
     */
    @Composable
    fun NavigateController() {
        val navController = rememberNavController()
        val list = remember { mutableStateListOf<Person>() }
        NavHost(navController = navController, startDestination = ListPerson) {
            composable<ListPerson> {
                ListScreen(list, onNavigateToAddPerson = {
                    navController.navigate(RegisterPerson)
                })
            }
            composable<RegisterPerson> {
                RegisterScreen(onAddedPerson = { person ->
                    list.add(person)
                }, onNavigateToList = {
                    navController.navigate(ListPerson) {
                        popUpTo(RegisterPerson) {
                            inclusive = true
                        }
                    }
                })
            }
        }
    }
}
