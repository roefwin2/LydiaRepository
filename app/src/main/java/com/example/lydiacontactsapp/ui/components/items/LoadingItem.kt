package com.example.lydiacontactsapp.ui.components.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingItem(){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        CircularProgressIndicator(modifier = Modifier.padding(10.dp).align(Alignment.Center))
    }
}