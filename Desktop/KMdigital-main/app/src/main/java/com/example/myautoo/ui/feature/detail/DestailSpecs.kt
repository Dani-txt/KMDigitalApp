package com.example.myautoo.ui.feature.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myautoo.R
import com.example.myautoo.ui.components.BrandCard
import com.example.myautoo.ui.components.SpecCard

@Composable
fun DetailSpecs(
    brand: String,
    brandImageUrl: String,
    rating: Double
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BrandCard(brandImageUrl, brand)
        Column {
            Text("Rating:", fontWeight = FontWeight.SemiBold)
            Text(rating.toString())
        }
    }
}
