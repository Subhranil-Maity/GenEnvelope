package com.subhranil.envelope

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.subhranil.envelope.db.AppDatabase
import com.subhranil.envelope.db.dao.AddressDao
import com.subhranil.envelope.db.entity.toAddress
import com.subhranil.envelope.db.getDatabaseBuilder
import com.subhranil.envelope.models.Address
import com.subhranil.envelope.models.toAddressEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

data object GlobalState{
    private var dao: AppDatabase? = null
    private var addressDao: AddressDao? = null


    fun getDao(): AppDatabase {
        if (dao == null) {
            dao = getDatabaseBuilder()
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
        return dao!!
    }
    fun getAddressDao(): AddressDao {
        if (addressDao == null) {
            addressDao = getDao().getAddressDao()
        }
        return addressDao!!
    }
    fun addAddress(address: Address) {
        runBlocking {
            getAddressDao().insert(address.toAddressEntity())
        }
    }
}
