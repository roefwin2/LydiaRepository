package com.example.lydiacontactsapp.ui.components.contactsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.ui.components.items.CustomSnackBar
import com.example.lydiacontactsapp.ui.components.items.contactitem.Contact

@ExperimentalPagingApi
@Composable
fun ContactsScreen(
    navController: NavController,
    viewModel : ContactsScreenViewModel = hiltViewModel()
) {
    val lydiaContacts = viewModel.state
    val lazyContactsItems: LazyPagingItems<LydiaContactEntity> =
        lydiaContacts.collectAsLazyPagingItems()

    Column(Modifier.fillMaxWidth()) {
        if (lazyContactsItems.loadState.mediator?.append is LoadState.Loading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        val append = lazyContactsItems.loadState.append
        val refresh = lazyContactsItems.loadState.refresh

        val error = when {
            append is LoadState.Error -> append.error.toString()
            refresh is LoadState.Error -> refresh.error.toString()
            else -> "not compatible error"
        }
        if (lazyContactsItems.loadState.append is LoadState.Error || lazyContactsItems.loadState.refresh is LoadState.Error) {

            CustomSnackBar(error) {
                lazyContactsItems.retry()
            }
        }
        LazyColumn {
            items(lazyContactsItems) { contact ->
                Contact(contact = contact!!){
                    navController.navigate("contact_details" + "/${contact.id}")
                }
            }
        }
    }
}