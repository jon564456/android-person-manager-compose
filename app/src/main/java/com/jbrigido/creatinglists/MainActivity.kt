package com.jbrigido.creatinglists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jbrigido.creatinglists.ui.navigation.NavigateController
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

}
