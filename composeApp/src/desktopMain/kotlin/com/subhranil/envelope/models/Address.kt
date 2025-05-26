package com.subhranil.envelope.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.subhranil.envelope.db.entity.AddressEntity


data class Address(
    val name: String,
    val address: List<String>,
){
    companion object{
        fun getDefault(): Address {
            return Address(
                name = "",
                address = listOf()
            )
        }
    }
    fun getAddress(): String {
        return address.joinToString(separator = "\n")
    }
}

fun Address.toAddressEntity(): AddressEntity{
    return AddressEntity(
        name = name,
        address = address,
    )
}

// dummy data
val dummyAddressList = listOf(
    Address(
        name = "John Doe",
        address = listOf(
            "123 Main St",
            "Apt 4B",
            "New York, NY 10001"
        )
    ),
    Address(
        name = "Jane Smith",
        address = listOf(
            "456 Elm St",
            "Los Angeles, CA 90001"
        )
    ),
    Address(
        name = "Bob Johnson",
        address = listOf(
            "789 Oak St",
            "Chicago, IL 60601"
        )
    ),
    Address(
        name = "Alice Williams",
        address = listOf(
            "321 Pine Ave",
            "San Francisco, CA 94107"
        )
    ),
    Address(
        name = "Michael Brown",
        address = listOf(
            "654 Maple Drive",
            "Suite 201",
            "Austin, TX 73301"
        )
    ),
    Address(
        name = "Emily Davis",
        address = listOf(
            "987 Birch Blvd",
            "Floor 5",
            "Seattle, WA 98101"
        )
    ),
    Address(
        name = "David Wilson",
        address = listOf(
            "135 Cedar Rd",
            "Portland, OR 97201"
        )
    ),
    Address(
        name = "Sophia Miller",
        address = listOf(
            "246 Spruce St",
            "Unit 3C",
            "Boston, MA 02108"
        )
    )
)
