package uz.uicgroup.presentation.screens.edit.pager.viewmodel

import android.widget.EditText
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.uicgroup.utils.common.Resource
import uz.uicgroup.domain.models.CorrectData
import uz.uicgroup.domain.models.SuggestionsData

interface EditorViewModel {
    val words: Flow<Resource<String>>
    val noConnectionFlow: Flow<Boolean>
    val showLoadingFlow: Flow<Resource<Boolean>>
    val showCorrectMessageFlow:Flow<Resource<String>>
    val errorFlow: Flow<Resource<String>>
    val showMassageFlow: Flow<Resource<String>>
    val checkLoading: Flow<Resource<Boolean>>
    val corrects: Flow<Resource<CorrectData>>
    val suggestions:Flow<Resource<SuggestionsData>>
    val buttonLive: LiveData<Boolean>

    //method
    fun getLatin(text: String)
    fun getCyrillic(text: String)
    fun savePosition(box: EditText)

    //spelling
    fun getCorrect(correctList: List<String>)
    fun getSuggestions(suggestionsList: List<String>)
}