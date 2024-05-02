package vn.hoanguyen.compose.onlinestore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography

@Composable
fun ListEmptyItem(
    icon: ImageVector,
    title: String,
    content: String,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = icon,
            tint = Color.LightGray,
            contentDescription = "icon"
        )
        Spacer(Modifier.height(10.dp))
        Text(title, style = AppTypography.titleMedium)
        Spacer(Modifier.height(10.dp))
        Text(
            content,
            style = AppTypography.bodyMedium.copy(color = Color.Gray),
            textAlign = TextAlign.Center
        )
    }
}