package com.example.lydiacontactsapp.ui.components.contactsscreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.domain.usecases.GetLydiaContactListUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@ExperimentalPagingApi
class ContactsScreenViewModel
@Inject constructor(
    private val getLydiaContactListUseCaseImpl: GetLydiaContactListUseCaseImpl
) : ViewModel() {

    var state = flowOf<PagingData<LydiaContact>>(PagingData.empty())

    init {
        getContactList()
    }

    private fun getContactList() {
        viewModelScope.launch {
            state = getLydiaContactListUseCaseImpl.invoke().map { it }
        }
    }

}
