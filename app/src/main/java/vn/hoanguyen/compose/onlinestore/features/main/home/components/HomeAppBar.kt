@file:OptIn(ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.main.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    title: String,
    onNavigateNotification: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = AppTypography.headlineLarge.copy(
                    color = Color.Black
                )

            )
        },
        actions = {
            IconButton(onClick = onNavigateNotification) {
                Icon(Icons.Outlined.Notifications, contentDescription = "notification")
            }
        },
    )
}

@Preview
@Composable
private fun HomeAppBarPrev() {
    Surface {
        HomeAppBar(title = "Discover") {}
    }
}