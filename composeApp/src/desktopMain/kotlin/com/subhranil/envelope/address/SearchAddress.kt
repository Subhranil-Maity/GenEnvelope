package com.subhranil.envelope.address

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subhranil.envelope.models.Address

@Composable

fun SearchAddress(
    allAddress: List<Address>,
    onAddressSelected: (Address) -> Unit = {}
) {
    var search by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

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
