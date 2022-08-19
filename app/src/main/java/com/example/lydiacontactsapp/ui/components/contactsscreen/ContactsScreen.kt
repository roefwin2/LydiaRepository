package com.example.lydiacontactsapp.ui.components.contactsscreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.ui.components.items.CustomSnackBar
import com.example.lydiacontactsapp.ui.components.items.LoadingItem
import com.example.lydiacontactsapp.ui.components.items.contactitem.Contact

@ExperimentalPagingApi
@Composable
fun ContactsScreen(
    navController: NavController,
    viewModel: ContactsScreenViewModel = hiltViewModel()
) {
    val lydiaContacts = viewModel.state
    val lazyContactsItems: LazyPagingItems<LydiaContact> =
        lydiaContacts.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyContactsItems) { contact ->
            contact?.let {
                Contact(contact = it) {
                    navController.navigate("contact_details" + "/${contact.id}")
                }
            }
        }
        lazyContactsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        LoadingItem()
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        LoadingItem()
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    item {
                        CustomSnackBar((loadState.refresh as LoadState.Error).error.toString()) {
                            lazyContactsItems.retry()
                        }
                    }
                }
                loadState.append is LoadState.Error -> {
                    item {
                        CustomSnackBar((loadState.append as LoadState.Error).error.toString()) {
                            lazyContactsItems.retry()
                        }
                    }
                }
            }
        }
    }
}