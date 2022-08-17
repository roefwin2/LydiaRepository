package com.example.lydiacontactsapp.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.example.lydiacontactsapp.data.local.entity.LydiaContactEntity
import com.example.lydiacontactsapp.ui.components.contactsscreen.ContactsScreen
import com.example.lydiacontactsapp.ui.components.detailsscreen.DetailsScreen
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
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "contact_list_screen" ){
                        composable(
                            route = "contact_list_screen"
                        ){
                            val viewModel: MainActivityViewModel = hiltViewModel()
                            ContactsScreen(contacts = viewModel.state, navController = navController)
                        }
                        composable(route = "contact_details"+"/{contactId}"){
                            DetailsScreen()
                        }
                    }
                }
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