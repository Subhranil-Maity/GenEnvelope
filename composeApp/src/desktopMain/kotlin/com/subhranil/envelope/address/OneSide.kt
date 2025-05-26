package com.subhranil.envelope.address

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.subhranil.envelope.models.Address
import com.subhranil.envelope.models.dummyAddressList
import com.subhranil.envelope.ui.components.SelectableDropdownMenu


@Composable
fun RowScope.OneSide(
    allAddress: List<Address>,
    selectedAddress: Address? = null,
    onSelectAddress: (Address) -> Unit = {}
) {
//    var selectedAddress by remember { mutableStateOf<Address?>(null) }
    Column(
        modifier = Modifier.weight(0.5f)
    ) {
        SelectableDropdownMenu(allAddress) { address ->
            onSelectAddress(address)
        }
        OutlinedTextField(
            value = selectedAddress?.getAddress() ?: "",
            onValueChange = {
                if (selectedAddress != null) {
                    onSelectAddress(
                        selectedAddress.copy(
                            address = it.split("\n")
                        )
                    )
                }
            },
            label = { Text(selectedAddress?.name ?: "Select Address") },
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}