package com.subhranil.envelope.core

data class EnvelopeConfig (
    val senderX: Int,
    val senderY: Int,

    val recipientX: Int,
    val recipientY: Int,

    val senderLineSpacing: Int,
    val recipientLineSpacing: Int,
)