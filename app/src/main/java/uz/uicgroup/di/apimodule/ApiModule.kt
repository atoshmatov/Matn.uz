package uz.uicgroup.di.apimodule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import uz.uicgroup.data.remote.api.SpellingApi
import uz.uicgroup.data.remote.api.TransApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @[Provides Singleton]
    fun provideTransApi(retrofit: Retrofit): TransApi =
        retrofit.create(TransApi::class.java)

    @[Provides Singleton]
    fun provideSpellingApi(retrofit: Retrofit): SpellingApi =
        retrofit.create(SpellingApi::class.java)
}