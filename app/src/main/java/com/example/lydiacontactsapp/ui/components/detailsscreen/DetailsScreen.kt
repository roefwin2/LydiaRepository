package com.example.lydiacontactsapp.ui.components.detailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import coil.compose.rememberAsyncImagePainter

@ExperimentalPagingApi
@Composable
fun DetailsScreen(
    viewModel: DetailsScreenViewModel = hiltViewModel()
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color.White, shape = RectangleShape),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(viewModel.state.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(4.dp),

            )
        Row(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {}

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = viewModel.state.firstname, fontStyle = FontStyle.Italic)
        Row(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {}

        Spacer(modifier = Modifier.height(20.dp))
        Text(text = viewModel.state.lastName, fontStyle = FontStyle.Italic)
        Row(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = viewModel.state.phone, fontStyle = FontStyle.Italic)
        Row(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {}
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = viewModel.state.city, fontStyle = FontStyle.Italic)
        Row(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {}

    }
}