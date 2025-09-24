package com.subhranil.envelope.address

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subhranil.envelope.models.Address

@Composable
fun addNewAddress(
    onAddressSelected: (Address) -> Unit = {},
    cancel: () -> Unit = {}
) {
    Column(
        Modifier.padding(8.dp)
    ) {
        var name by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { androidx.compose.material3.Text("Enter Name") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = address,
            onValueChange = {
                address = it
            },
            label = { androidx.compose.material3.Text("Enter Address") },
            modifier = Modifier.fillMaxWidth().heightIn(min = 100.dp),
        )
        Button(
            onClick = {
                if (name.isNotBlank() && address.isNotBlank()) {
                    onAddressSelected(Address(name, address.split("\n")))
                    name = ""
                    address = ""
                }
            },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            androidx.compose.material3.Text("Add Address")
        }
        OutlinedButton(
            onClick = cancel,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text("Cancel")
        }

    }
}