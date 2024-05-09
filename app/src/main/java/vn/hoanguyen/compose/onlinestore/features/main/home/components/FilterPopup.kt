package vn.hoanguyen.compose.onlinestore.features.main.home.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
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
import androidx.lifecycle.viewmodel.compose.viewModel
import vn.hoanguyen.compose.onlinestore.components.AppDivider
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import java.text.NumberFormat
import java.util.Locale

private val listFilter = listOf("Relevant", "Price: High-Low", "Price: Low-High")

@Composable
fun FilterPopupContent(
    onHidePopup: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text("Filter", style = AppTypography.titleLarge)
            IconButton(onClick = onHidePopup) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "close")
            }
        }
        AppDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text("Sort by", style = AppTypography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        FilterSelectionBar(viewmodel = viewModel(),
            listItem = listFilter.mapIndexed { index, item ->
                FilterItem(id = index, text = item)
            },
            filterChoice = FilterChoice.Single,
            onSelectedChange = { indexList ->
                val map = indexList.map { index -> listFilter[index] }
                Log.d("Filter", map.toString())
            })

        var sliderPosition by remember { mutableStateOf(0f..1000f) }
        val startInt = sliderPosition.start.toInt()
        val endInt = sliderPosition.endInclusive.toInt()
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale.US)
        val formattedStartCurrency =
            currencyFormat.format(startInt)
        val formattedEndCurrency = currencyFormat.format(endInt)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = "Price", style = AppTypography.titleMedium
            )
            Text(
                text = "$formattedStartCurrency-$formattedEndCurrency",
                style = AppTypography.bodyMedium.copy(color = Color.Gray)
            )
        }

        RangeSlider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0f..1000f,
            onValueChangeFinished = {
                // viewModel.updateSelectedSliderValue(sliderPosition)
            },
            colors = SliderDefaults.colors(
                activeTrackColor = Color.Black, thumbColor = Color.White
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        AppDivider()

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text(
                text = "Size", style = AppTypography.titleMedium
            )
            var expanded by remember { mutableStateOf(false) }
            val items = listOf("XS", "S", "M", "L", "XL", "2XL")
            var selectedItem by remember { mutableStateOf(items[3]) }

            Box(modifier = Modifier.padding(0.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = selectedItem,
                        style = AppTypography.bodyMedium.copy(color = Color.Gray),
                        modifier = Modifier.clickable { expanded = true })

                    Icon(
                        modifier = Modifier.height(18.dp),
                        imageVector = Icons.Outlined.KeyboardArrowDown,
                        contentDescription = "filter",
                        tint = Color.Gray
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .background(color = Color.White)
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(text = { Text(text = item) }, onClick = {
                            selectedItem = item
                            expanded = false
                        })
                    }
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .defaultMinSize(minHeight = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            content = { Text("Apply Filters") },
            onClick = onHidePopup
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterPopupContentPrev() {
    Surface {
        FilterPopupContent() {}
    }
}