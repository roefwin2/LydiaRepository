package com.example.lydiacontactsapp.ui.components.contactitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity


@Composable
fun Contact(contact: LydiaContactEntity, onClickListener: (id : Int?) -> Unit) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp).clickable {
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
                Text(text = "Contact ${contact.firstName}!")
                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "Number ${contact.lastName}!")
                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "Number ${contact.phone}!")
            }

        }

    }

}