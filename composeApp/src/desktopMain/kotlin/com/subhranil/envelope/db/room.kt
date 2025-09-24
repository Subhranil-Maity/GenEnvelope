package com.subhranil.envelope.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.subhranil.envelope.db.dao.AddressDao
import com.subhranil.envelope.db.entity.AddressEntity
import java.io.File



// shared/src/commonMain/kotlin/Database.kt

@Database(entities = [AddressEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAddressDao(): AddressDao
}


fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val appDataPath = System.getenv("APPDATA")
    val dbFile = File(appDataPath, "GenEnvelope/GenEnv.db")
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile.absolutePath,
    )
}