package uz.uicgroup.data.local.dao

import androidx.room.*
import kotlinx.coroutines.selects.select


interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)
}