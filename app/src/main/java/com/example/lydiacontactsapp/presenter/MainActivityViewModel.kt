package com.example.lydiacontactsapp.presenter


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lydiacontactsapp.domain.models.LydiaContact
import com.example.lydiacontactsapp.domain.usecases.GetLydiaContactListUseCase
import com.example.lydiacontactsapp.utils.Error
import com.example.lydiacontactsapp.utils.Loading
import com.example.lydiacontactsapp.utils.Resource
import com.example.lydiacontactsapp.utils.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getLydiaContactListUseCase: GetLydiaContactListUseCase
) : ViewModel() {

    var state: MutableState<Resource<ScreenState>> = mutableStateOf(Loading())

    init {
        getContactList()
    }

    private fun getContactList() {
        viewModelScope.launch {
            getLydiaContactListUseCase.invoke(20, 1).collect {
                state.value = when (it) {
                    is Success -> Success(ScreenState(it.value.first, it.value.second))
                    is Loading -> Loading()
                    is Error -> Error<ScreenState>(it.msg)
                }
            }
        }
    }
}


data class ScreenState(
    val page: Int,
    val contacts: List<LydiaContact>
)