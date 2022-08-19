package com.example.lydiacontactsapp.ui.components.items.contactitem

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lydiacontactsapp.domain.models.LydiaContact


@Composable
fun Contact(contact: LydiaContact, onClickListener: (id: String) -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.DarkGray)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable {
                    onClickListener.invoke(contact.id)
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(contact.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp)
                    .padding(4.dp)
            )
            Column() {
                Text(text = "Firstname :  ${contact.firstName}", fontWeight = FontWeight.Bold, fontFamily = FontFamily.Cursive)
                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "Lastname :  ${contact.lastName}",fontWeight = FontWeight.Bold,fontFamily = FontFamily.Cursive)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Number :  ${contact.number}",fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif
                )
            }

        }

    }

}