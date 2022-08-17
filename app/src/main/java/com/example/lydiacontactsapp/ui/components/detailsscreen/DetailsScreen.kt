package com.example.lydiacontactsapp.ui.components.detailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
            .padding(24.dp)) {
        Image(
            painter = rememberAsyncImagePainter(viewModel.state.imageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .padding(4.dp)
        )
        Text(text = viewModel.state.firstname)

    }
}