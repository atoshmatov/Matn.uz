package uz.uicgroup.presentation.viewModel

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.uicgroup.data.remote.request.LatinRequest

interface EditorViewModel {
    val _text: Flow<String>
    val showLoadingFlow: Flow<Boolean>
    val noConnectionFlow: Flow<Boolean>
    val showMassageFlow: Flow<String>
    val errorFlow: Flow<String>
    val buttonLive:LiveData<Boolean>

    //method
    fun getLatin(text: LatinRequest)
    fun getCyrillic(text: LatinRequest)
}