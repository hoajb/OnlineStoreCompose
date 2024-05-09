package vn.hoanguyen.compose.onlinestore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun AppDropdownInput(
    modifier: Modifier = Modifier,
    label: String = "",
    listItems: List<String>,
    defaultItem: String = "",
    onSetValue: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(defaultItem) }

    Column(modifier = modifier
        .fillMaxWidth()
        .clickable(
            indication = null, // no ripple
            interactionSource = remember { MutableInteractionSource() }
        ) {
            expanded = true
        }) {
        if (label.isNotEmpty())
            Text(
                label, modifier = Modifier.padding(bottom = 8.dp),
                style = AppTypography.bodyMedium
            )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    width = 0.5.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(12.dp)
                )
                .wrapContentHeight(align = Alignment.CenterVertically)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedItem,
                style = AppTypography.bodyMedium
            )

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "",
                tint = Color.Gray
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
        ) {
            listItems.forEach { item ->
                DropdownMenuItem(text = { Text(text = item) }, onClick = {
                    selectedItem = item
                    expanded = false
                    onSetValue(item)
                })
            }
        }
    }
}

@Preview
@Composable
private fun AppDateTimeInputPrev() {
    OnlineStoreComposeTheme {
        Scaffold { padding ->
            AppDropdownInput(
                label = "Gender",
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth(),
                listItems = listOf("Male, Female"),
                onSetValue = {}
            )
        }
    }
}