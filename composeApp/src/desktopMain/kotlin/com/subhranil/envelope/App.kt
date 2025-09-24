package com.subhranil.envelope

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.subhranil.envelope.GlobalState.getAddressDao
import com.subhranil.envelope.address.ManageAddress
import com.subhranil.envelope.address.OneSide
import com.subhranil.envelope.core.printer
import com.subhranil.envelope.db.entity.toAddress
import com.subhranil.envelope.models.Address
import com.subhranil.envelope.ui.GenTheme


@Composable
//fun App(address: List<Address> = emptyList()) {
fun App() {
    GenTheme {
        val addressDao = getAddressDao()
        val address: List<Address> = addressDao.getAll().collectAsStateWithLifecycle(emptyList()).value.map {
            it.toAddress()
        }
        var senderAddress by remember { mutableStateOf<Address?>(null) }
        var receiverAddress by remember { mutableStateOf<Address?>(null) }

        Scaffold { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {

                    OneSide(allAddress = address, senderAddress){
                        senderAddress = it
                    }
                    Spacer(modifier = Modifier.weight(0.01f))
                    OneSide(allAddress = address, receiverAddress) {
                        receiverAddress = it
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .padding(8.dp)
                ) {
                    ManageAddress(
                        allAddress = address,
                    )

                    Button(onClick = {
                        if (senderAddress != null && receiverAddress != null) {
                            printer(
                                senderAddress = senderAddress!!.address,
                                recipientAddress = receiverAddress!!.address,
                            )
                        }
                    }){
                        Text("Print Envelope")
                    }
                }
            }
        }
    }
}