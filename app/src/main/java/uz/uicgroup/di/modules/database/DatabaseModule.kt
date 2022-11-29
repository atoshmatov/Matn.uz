package uz.uicgroup.di.modules.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.uicgroup.data.local.DataBase
import uz.uicgroup.data.local.dao.DictionaryDao
import uz.uicgroup.di.modules.database.TableName.tableName
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @[Provides Singleton]
    fun getAppDatabase(@ApplicationContext context: Context): DataBase =
        Room.databaseBuilder(context, DataBase::class.java, tableName)
            .allowMainThreadQueries()
            .build()

    @[Provides Singleton]
    fun getWordsDao(dataBase: DataBase): DictionaryDao = dataBase.getWordsDao()
}