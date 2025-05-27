package com.jbrigido.creatinglists.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jbrigido.creatinglists.R
import com.jbrigido.creatinglists.domain.Person.Person


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

