package com.example.lydiacontactsapp.ui.components.detailsscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lydiacontactsapp.data.local.database.LydiaContactsDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val lydiaContactsDatabase: LydiaContactsDatabase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(ContactDetailsState("", "", "", "", ""))

    init {
        savedStateHandle.get<String>("contactId")?.let { contactId ->
            getContact(contactId)
        }
    }

    private fun getContact(id: String) {
        viewModelScope.launch {
            val result = lydiaContactsDatabase.dao.getLydiaContact(id)
            state = ContactDetailsState(
                result.imageUrl,
                result.firstName,
                result.lastName,
                result.phone,
                result.city
            )
        }
    }
}

data class ContactDetailsState(
    val imageUrl: String,
    val firstname: String,
    val lastName: String,
    val phone: String,
    val city: String,
)