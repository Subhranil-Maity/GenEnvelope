package com.subhranil.envelope.address

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subhranil.envelope.GlobalState
import com.subhranil.envelope.models.Address
import com.subhranil.envelope.ui.GenTheme2

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


@Composable
fun SelectAddress(
    allAddress: List<Address>,
    onAddressSelected: (Address) -> Unit = {}
) {
    var showAddNew by remember { mutableStateOf(false) }

    GenTheme2 {
        if (showAddNew) {
            addNewAddress(
                onAddressSelected = { address ->
                    GlobalState.addAddress(address)
                    onAddressSelected(address)
                    showAddNew = false
                },
                cancel = {
                    showAddNew = false
                }
            )
        } else {
            SearchAddress(
                allAddress,
                onAddressSelected = { address ->
                    onAddressSelected(address)
                },
                onAddNewAddress = { showAddNew = true }
            )
        }
    }
}

@Composable
fun SearchAddress(
    allAddress: List<Address>,
    onAddNewAddress: () -> Unit = {},
    onAddressSelected: (Address) -> Unit = {}
) {
    var search by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Button(
            onClick = onAddNewAddress,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp), // match TextField height
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
            shape = RoundedCornerShape(8.dp) // match TextField shape
        ) {
            Text("Add New Address")
        }
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = { Text("Search", color = MaterialTheme.colorScheme.primary) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    focusedLabelColor = MaterialTheme.colorScheme.primary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.primary,
                )
            )




        Column(
            Modifier.verticalScroll(rememberScrollState())
        ) {
            allAddress.filter { it.name.contains(search, ignoreCase = true) }.forEach {
                AddressItemBox(
                    address = it,
                    onClick = {
                        onAddressSelected(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

    }
}
