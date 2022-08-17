package com.example.lydiacontactsapp.ui.components.detailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
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
                    .background(Color.Gray)
            ) {}

            Text(text = viewModel.state.firstname)
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            ) {}

            Spacer(modifier = Modifier.height(20.dp))
            Text(text = viewModel.state.lastName)
            Row(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = viewModel.state.phone)
            Row(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = viewModel.state.city)
            Row(
                modifier = Modifier
                    .height(15.dp)
                    .fillMaxWidth()
                    .background(Color.Gray)
            ) {}

    }
}