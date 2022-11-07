package uz.uicgroup.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.uicgroup.domain.use_case.TransUseCase
import uz.uicgroup.domain.use_case.impl.TransUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindsTransUseCase(impl: TransUseCaseImpl): TransUseCase
}