package uz.uicgroup.di.modules.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.uicgroup.domain.repository.dictionary.DictionaryRepository
import uz.uicgroup.domain.repository.history.HistoryRepository
import uz.uicgroup.domain.repository.spelling.SpellingRepository
import uz.uicgroup.domain.repository.trans.TranslationRepository
import uz.uicgroup.domain.repository.dictionary.DictionaryRepositoryImpl
import uz.uicgroup.domain.repository.history.HistoryRepositoryImpl
import uz.uicgroup.domain.repository.spelling.SpellingRepositoryImpl
import uz.uicgroup.domain.repository.trans.TranslationRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindTransRepository(impl: TranslationRepositoryImpl): TranslationRepository

    @[Binds Singleton]
    fun bindsSpellingRepository(impl: SpellingRepositoryImpl): SpellingRepository

    @[Binds Singleton]
    fun bindsDictionaryRepository(impl: DictionaryRepositoryImpl): DictionaryRepository

    @[Binds Singleton]
    fun bindsHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository
}