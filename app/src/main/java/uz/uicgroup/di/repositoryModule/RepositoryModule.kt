package uz.uicgroup.di.repositoryModule

import android.view.textservice.SpellCheckerInfo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.uicgroup.domain.repository.DictionaryRepository
import uz.uicgroup.domain.repository.HistoryRepository
import uz.uicgroup.domain.repository.SpellingRepository
import uz.uicgroup.domain.repository.TransRepository
import uz.uicgroup.domain.repository.impl.DictionaryRepositoryImpl
import uz.uicgroup.domain.repository.impl.HistoryRepositoryImpl
import uz.uicgroup.domain.repository.impl.SpellingRepositoryImpl
import uz.uicgroup.domain.repository.impl.TransRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindTransRepository(impl: TransRepositoryImpl): TransRepository

    @[Binds Singleton]
    fun bindsSpellingRepository(impl: SpellingRepositoryImpl): SpellingRepository

    @[Binds Singleton]
    fun bindsDictionaryRepository(impl: DictionaryRepositoryImpl): DictionaryRepository

    @[Binds Singleton]
    fun bindsHistoryRepository(impl: HistoryRepositoryImpl): HistoryRepository
}