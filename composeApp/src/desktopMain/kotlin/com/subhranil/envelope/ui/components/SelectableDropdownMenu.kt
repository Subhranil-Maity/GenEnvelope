package com.subhranil.envelope.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subhranil.envelope.models.Address

import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.subhranil.envelope.address.SelectAddress



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectableDropdownMenu(
    allAddress: List<Address>,
    onAddressSelected: (Address) -> Unit = {}
) {
    var bringAddressSelectScreen by remember { mutableStateOf(false) }
    var f by remember { mutableStateOf("false") }
    if (bringAddressSelectScreen) {
        Window(
            onCloseRequest = { bringAddressSelectScreen = false },
            title = "Select Address",
            state = rememberWindowState(width = 400.dp, height = 600.dp),
            alwaysOnTop = true,
        ) {
            SelectAddress(allAddress) {
                onAddressSelected(it)
                bringAddressSelectScreen = false
            }
        }
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text("Select Address")
        OutlinedButton(
            onClick = {
                bringAddressSelectScreen = true
            }
        ) {
            Text("Select Address")
        }
    }
}
