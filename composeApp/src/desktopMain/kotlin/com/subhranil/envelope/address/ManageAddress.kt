package com.subhranil.envelope.address

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.subhranil.envelope.GlobalState
import com.subhranil.envelope.GlobalState.getAddressDao
import com.subhranil.envelope.models.Address
import com.subhranil.envelope.ui.GenTheme2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ManageAddress(
    allAddress: List<Address>,
) {
    var bringManageAddress by remember { mutableStateOf(false) }

    if (bringManageAddress) {
        Window(
            onCloseRequest = { bringManageAddress = false },
            state = rememberWindowState(width = 400.dp, height = 600.dp),
            alwaysOnTop = true,
            title = "Manage Address",
        ) {
            var search by remember { mutableStateOf("") }
            var showAddNew by remember { mutableStateOf(false) }

            GenTheme2 {
                Column(
                    Modifier.verticalScroll(rememberScrollState())
                ) {
                    if (showAddNew) {
                        addNewAddress(
                            onAddressSelected = { address ->
                                GlobalState.addAddress(address)
                                showAddNew = false
                            },
                            cancel = {
                                showAddNew = false
                            }
                        )
                    } else {
                        Button(
                            onClick = {
                                showAddNew = true
                            },
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
                        allAddress
                            .filter { it.name.contains(search, ignoreCase = true) }
                            .forEach { address ->
                                AddressItem(
                                    name = address.name,
                                    onDelete = {
                                        val addressDao = getAddressDao()
                                        CoroutineScope(Dispatchers.Default).launch {
                                            addressDao.deleteByName(address.name)
                                        }
                                    }
                                )
                            }

                    }
                }
            }
        }
    }
    Button(onClick = {
        bringManageAddress = true
    }) {
        Text("Manage Address")
    }
}

@Composable
fun AddressItem(
    name: String,
    onDelete: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )

            Row {
                TextButton(onClick = onDelete) {
                    Text("Delete", color = MaterialTheme.colorScheme.error)
                }
            }
        }

        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
    }
}
