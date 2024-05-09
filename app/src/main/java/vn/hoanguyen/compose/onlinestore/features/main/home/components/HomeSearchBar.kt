package vn.hoanguyen.compose.onlinestore.features.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.components.AppSearchBar

@Composable
fun HomeSearchBar(
    onSearch: (String) -> Unit,
    onFilter: () -> Unit,
    onValueChange: (String) -> Unit = {},
) {
    Row(
        Modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AppSearchBar(
            modifier = Modifier.weight(1f),
            hintSearch = "Search for clothes...",
            onSearch = onSearch,
            onValueChange = onValueChange
        )
        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .background(color = Color.Black, shape = RoundedCornerShape(12.dp))
                .clickable {
                    onFilter.invoke()
                }, contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Outlined.Tune,
                modifier = Modifier
                    .width(32.dp)
                    .rotate(90f),
                tint = Color.White,
                contentDescription = "voice"
            )
        }
    }
}

@Preview
@Composable
private fun HomeAppBarPrev() {
    Surface {
        HomeSearchBar(onSearch = {}, onFilter = {})
    }
}