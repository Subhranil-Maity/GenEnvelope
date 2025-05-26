package com.subhranil.envelope

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.subhranil.envelope.GlobalState.getAddressDao
import com.subhranil.envelope.address.OneSide
import com.subhranil.envelope.core.printer
import com.subhranil.envelope.db.entity.toAddress
import com.subhranil.envelope.db.getDatabaseBuilder
import com.subhranil.envelope.models.Address
import com.subhranil.envelope.models.dummyAddressList
import com.subhranil.envelope.models.toAddressEntity
import com.subhranil.envelope.ui.GenTheme
import kotlinx.coroutines.Dispatchers


@Composable
//fun App(address: List<Address> = emptyList()) {
fun App() {
    GenTheme {
//        val dao = getDatabaseBuilder()
//            .setDriver(BundledSQLiteDriver())
//            .setQueryCoroutineContext(Dispatchers.IO)
//            .build()
//
        val addressDao = getAddressDao()
        val address: List<Address> = addressDao.getAll().collectAsStateWithLifecycle(emptyList()).value.map {
            it.toAddress()
        }
        var senderAddress by remember { mutableStateOf<Address?>(null) }
        var reciverAddress by remember { mutableStateOf<Address?>(null) }

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
                    OneSide(allAddress = address, reciverAddress) {
                        reciverAddress = it
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .padding(8.dp)
                ) {
                    Button(onClick = {
                        if (senderAddress != null && reciverAddress != null) {
                            printer(
                                senderAddress = senderAddress!!.address,
                                recipientAddress = reciverAddress!!.address,
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