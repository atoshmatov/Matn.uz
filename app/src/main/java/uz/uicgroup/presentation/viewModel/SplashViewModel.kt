package uz.uicgroup.presentation.viewModel

import kotlinx.coroutines.flow.Flow

interface SplashViewModel {
    val showLoadingFlow: Flow<String>
    val notConnection: Flow<Boolean>
    val openNextScreenFLow: Flow<Unit>
    val messageFlow: Flow<String>
}