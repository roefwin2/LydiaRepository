package com.example.lydiacontactsapp.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.ui.theme.LydiaContactsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
@ExperimentalPagingApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LydiaContactsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val viewModel: MainActivityViewModel = hiltViewModel()
                    ContactsScreen(viewModel.state)
                }
            }
        }
    }
}

@Composable
fun Contact(contact: LydiaContactEntity) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
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

@Composable
fun ContactsScreen(
    contacts: Flow<PagingData<LydiaContactEntity>>
) {
    val lydiaContacts = contacts
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
            items(lazyContactsItems) { contacts ->
                Contact(contact = contacts!!)
            }
        }
    }
}

@Composable
fun CustomSnackBar(message: String, onClickListener: (() -> Unit)) {
    Snackbar(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(text = message)
            OutlinedButton(onClick = {
                onClickListener.invoke()
            })
            {
                Text(text = "retry")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LydiaContactsAppTheme {

    }
}