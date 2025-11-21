package com.example.myautoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myautoo.R

@Composable
fun BrandCard(
    brandImageUrl: String,
    brandName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(colorResource(R.color.grey))
            .padding(16.dp)
    ) {
        // Imagen de la marca (desde URL)
        AsyncImage(
            model = brandImageUrl,
            contentDescription = brandName,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .fillMaxWidth()
        )
        // Nombre de la marca
        Text(
            text = brandName,
            color = colorResource(R.color.black),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}