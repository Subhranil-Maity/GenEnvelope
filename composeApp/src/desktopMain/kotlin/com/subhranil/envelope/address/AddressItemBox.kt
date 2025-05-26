package com.subhranil.envelope.address

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.subhranil.envelope.models.Address


@Composable
fun AddressItemBox(
    address: Address,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val borderColor = MaterialTheme.colorScheme.primary
    val labelColor = MaterialTheme.colorScheme.primary
    val backgroundColor = MaterialTheme.colorScheme.background
    val shape = RoundedCornerShape(16.dp)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        // The actual content box with extra top padding
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, borderColor, shape)
                .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp) // more top padding!
        ) {
            Text(
                text = address.getAddress(),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Floating label above the border line
        Text(
            text = address.name,
            color = labelColor,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(start = 24.dp)
                .background(backgroundColor)
                .offset(y = (-8).dp)
                .padding(horizontal = 4.dp)
        )
    }
}
