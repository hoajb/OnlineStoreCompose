package vn.hoanguyen.compose.onlinestore.components.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Adb
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun AppDialog(
    onDismissRequest: () -> Unit,
    onPositivePressed: (() -> Unit)? = null,
    onNegativePressed: (() -> Unit)? = null,
    title: String,
    content: String,
    positive: String = "Ok",
    negative: String = "Cancel",
    icon: ImageVector?,
    iconTint: Color = Color.Black,
) {
    AlertDialog(
        containerColor = Color.White,
        shape = RoundedCornerShape(18.dp),
        icon = icon?.let {
            {
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = it,
                    contentDescription = it.toString(),
                    tint = iconTint
                )
            }
        },
        title = {
            Text(text = title, style = AppTypography.titleMedium)
        },
        text = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = content,
                    style = AppTypography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                content = { Text(positive) },
                onClick = {
                    onNegativePressed?.invoke()
                    onDismissRequest.invoke()
                }
            )
        },

        dismissButton = {
            onNegativePressed?.let {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black.copy(alpha = 0.5f),
                    ),
                    shape = RoundedCornerShape(12.dp),
                    content = { Text(negative) },
                    onClick = {
                        onPositivePressed?.invoke()
                        onDismissRequest.invoke()
                    }
                )
            }
        }
    )
}

@Preview
@Composable
private fun AppDialogPrev() {
    OnlineStoreComposeTheme {
        AppDialog(
            onDismissRequest = {},
            title = "Online Store",
            content = "Your information is showing!",
            icon = Icons.Default.Adb,
            onPositivePressed = {}
        )
    }
}