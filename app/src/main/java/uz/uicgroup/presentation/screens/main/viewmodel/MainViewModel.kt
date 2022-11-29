package uz.uicgroup.presentation.screens.main.viewmodel

import kotlinx.coroutines.flow.Flow

interface MainViewModel {
    val showLoadingFlow: Flow<String>
    val notConnection: Flow<Boolean>
    val openNextScreenFLow: Flow<Unit>
    val openNoErrorScreenFLow: Flow<Unit>
    val messageFlow: Flow<String>

    fun openEditorScreen()
}