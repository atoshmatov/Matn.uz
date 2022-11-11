package uz.uicgroup.presentation.viewModel

import android.widget.EditText
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.uicgroup.data.common.Resource
import uz.uicgroup.domain.model.CorrectData
import uz.uicgroup.domain.model.SuggestionsData

interface EditorViewModel {
    val words: Flow<Resource<String>>
    val showLoadingFlow: Flow<Resource<Boolean>>
    val checkLoading: Flow<Resource<Boolean>>
    val corrects: Flow<Resource<CorrectData>>
    val suggestions:Flow<Resource<SuggestionsData>>
    val noConnectionFlow: Flow<Boolean>
    val showMassageFlow: Flow<Resource<String>>
    val errorFlow: Flow<Resource<String>>
    val buttonLive: LiveData<Resource<Boolean>>

    //method
    fun getLatin(text: String)
    fun getCyrillic(text: String)
    fun savePosition(box: EditText)

    //spelling
    fun getCorrect(correctList: List<String>)
    fun getSuggestions(suggestionsList: List<String>)
}