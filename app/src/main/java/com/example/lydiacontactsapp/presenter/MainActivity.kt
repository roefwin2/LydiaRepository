package com.example.lydiacontactsapp.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.ui.theme.LydiaContactsAppTheme
import com.example.lydiacontactsapp.utils.Error
import com.example.lydiacontactsapp.utils.Loading
import com.example.lydiacontactsapp.utils.Success
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LydiaContactsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ContactsScreen()
                }
            }
        }
    }
}

@Composable
fun Contact(contact: LydiaContact) {
    Text(text = "Contact ${contact.firstName}!")
}

@Composable
fun ContactsScreen(
    viewModel : MainActivityViewModel = hiltViewModel()
){
       LazyColumn(){
           val state  = viewModel.state.value
           when(state){
               is Error -> {
               }
               is Loading -> {}
               is Success -> {
                   items(state.value.contacts) { contact ->
                       Contact(contact = contact)
                   }
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