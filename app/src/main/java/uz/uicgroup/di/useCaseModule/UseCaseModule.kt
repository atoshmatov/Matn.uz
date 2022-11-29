package uz.uicgroup.di.useCaseModule

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.uicgroup.domain.use_case.DictionaryUseCase
import uz.uicgroup.domain.use_case.HistoryUseCase
import uz.uicgroup.domain.use_case.SpellingUseCase
import uz.uicgroup.domain.use_case.TransUseCase
import uz.uicgroup.domain.use_case.impl.DictionaryUseCaseImpl
import uz.uicgroup.domain.use_case.impl.HistoryUseCaseImpl
import uz.uicgroup.domain.use_case.impl.SpellingUseCaseImpl
import uz.uicgroup.domain.use_case.impl.TransUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindsTransUseCase(impl: TransUseCaseImpl): TransUseCase

    @Binds
    fun bindsSpellingUseCase(impl: SpellingUseCaseImpl): SpellingUseCase

    @Binds
    fun bindsDictionaryUseCase(impl: DictionaryUseCaseImpl): DictionaryUseCase

    @Binds
    fun bindsHistoryUseCase(impl: HistoryUseCaseImpl): HistoryUseCase
}