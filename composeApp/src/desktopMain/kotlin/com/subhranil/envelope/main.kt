package com.subhranil.envelope

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import genenvelope.composeapp.generated.resources.IconFile
import org.jetbrains.compose.resources.painterResource
import genenvelope.composeapp.generated.resources.Res

fun main() {

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "GenEnvelope",
            icon = painterResource(Res.drawable.IconFile),
        ) {
//            App(address = address)
            App()
        }
    }
}