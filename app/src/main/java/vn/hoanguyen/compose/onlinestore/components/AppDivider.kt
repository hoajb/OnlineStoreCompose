package vn.hoanguyen.compose.onlinestore.components

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier,
        thickness = 0.5.dp,
        color = Color.Black.copy(alpha = 0.3f)
    )
}