@file:OptIn(ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar

@Deprecated("Developing purpose")
@Composable
fun EmptyScreen(title: String = "", onBack: () -> Unit = {}) {
    Scaffold(
        topBar = { AppTopAppBar(title = title, onBack = onBack) }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(title.ifEmpty { "EmptyScreen" }.plus("Under development!"))
        }
    }
}