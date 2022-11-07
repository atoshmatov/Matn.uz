package uz.uicgroup.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.uicgroup.domain.repository.TransRepository
import uz.uicgroup.domain.repository.impl.TransRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindTransRepository(impl: TransRepositoryImpl): TransRepository
}