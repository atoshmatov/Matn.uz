package uz.uicgroup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.uicgroup.data.local.dao.DictionaryDao

@Database(entities = [WordEntity::class], version = 4)
abstract class DataBase : RoomDatabase() {
    abstract fun getWordsDao(): DictionaryDao
}