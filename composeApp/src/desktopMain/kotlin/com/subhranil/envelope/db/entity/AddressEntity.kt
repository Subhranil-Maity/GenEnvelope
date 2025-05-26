package com.subhranil.envelope.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.subhranil.envelope.models.Address

@Entity(tableName = "address")
data class AddressEntity(
    @PrimaryKey()
    val name: String,
    val address: List<String>,
)
fun AddressEntity.toAddress(): Address {
    return Address(
        this.name,
        this.address,
    )
}
