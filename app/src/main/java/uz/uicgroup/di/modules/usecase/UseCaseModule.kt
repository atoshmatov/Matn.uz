package uz.uicgroup.di.modules.usecase

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.uicgroup.domain.use_case.dictionary.DictionaryUseCase
import uz.uicgroup.domain.use_case.history.HistoryUseCase
import uz.uicgroup.domain.use_case.spelling.SpellingUseCase
import uz.uicgroup.domain.use_case.trans.TransUseCase
import uz.uicgroup.domain.use_case.dictionary.DictionaryUseCaseImpl
import uz.uicgroup.domain.use_case.history.HistoryUseCaseImpl
import uz.uicgroup.domain.use_case.spelling.SpellingUseCaseImpl
import uz.uicgroup.domain.use_case.trans.TransUseCaseImpl

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