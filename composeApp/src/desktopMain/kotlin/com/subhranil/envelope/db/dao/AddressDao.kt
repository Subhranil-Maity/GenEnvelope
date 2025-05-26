package com.subhranil.envelope.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.subhranil.envelope.db.entity.AddressEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AddressDao {
    @Insert
    suspend fun insert(address: AddressEntity)

    @Query("select * from Address")
    fun getAll(): Flow<List<AddressEntity>>

//    @Query("SELECT * FROM address WHERE street LIKE '%' || :searchString || '%'")
//    fun search(searchString: String): List<Address>
}