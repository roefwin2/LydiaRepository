package com.example.lydiacontactsapp.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items

import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.ui.theme.LydiaContactsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LydiaContactsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val  viewModel: MainActivityViewModel = hiltViewModel()
                    ContactsScreen(viewModel.state)
                }
            }
        }
    }
}

@Composable
fun Contact(contact: LydiaContact) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Contact ${contact.firstName}!")
    }

}

@Composable
fun ContactsScreen(
    contacts : Flow<PagingData<LydiaContact>>
) {
    val lydiaContacts = contacts
    val lazyContactsItems: LazyPagingItems<LydiaContact> = lydiaContacts.collectAsLazyPagingItems()

    LazyColumn {
        items(lazyContactsItems) { contacts ->
                Contact(contact = contacts!!)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LydiaContactsAppTheme {

    }
}