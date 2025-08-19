package com.subhranil.envelope.core

class EnvelopeConfigBuilder {
    val senderLineSpacing = 10
    val recipientLineSpacing = 15
    private val baseX = 200
    private val baseY = 450  // Y near bottom, adjust based on envelope height

    // Position offsets for recipient (relative to sender base)
    private val recipientOffsetX = 200
    private val recipientOffsetY = -180 - recipientLineSpacing*3  // move up to center



    public fun build(
        senderAddress: List<String>,
        recipientAddress: List<String>
    ): EnvelopeConfig {

        return EnvelopeConfig(
            senderX = baseX,
            senderY = baseY - (senderAddress.size * senderLineSpacing),
            recipientX = baseX + recipientOffsetX,
            recipientY = baseY + recipientOffsetY,
            senderLineSpacing = senderLineSpacing,
            recipientLineSpacing = recipientLineSpacing
        )
    }


}