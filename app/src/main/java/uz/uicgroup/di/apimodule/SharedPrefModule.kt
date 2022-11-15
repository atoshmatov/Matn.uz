package uz.uicgroup.di.apimodule

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.uicgroup.data.local.SharedPref
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefModule {
    @[Provides Singleton]
    fun getSharedPref(@ApplicationContext context: Context): SharedPref = SharedPref(context)
}