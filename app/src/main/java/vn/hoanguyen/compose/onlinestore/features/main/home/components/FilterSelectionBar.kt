package vn.hoanguyen.compose.onlinestore.features.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import javax.inject.Inject

@HiltViewModel
class FilterSelectionBarViewModel @Inject constructor() : ViewModel() {
    private val _selectedIndexList = mutableStateListOf<Int>()
    val selectedIndexList: List<Int> = _selectedIndexList

    fun select(index: Int) {
        if (index == 0) {//All
            _selectedIndexList.clear()
            _selectedIndexList.add(0)
            return
        }
        _selectedIndexList.remove(0)
        if (_selectedIndexList.contains(index)) {
            _selectedIndexList.remove(index)
        } else {
            _selectedIndexList.add(index)
        }
    }
}

data class FilterItem(
    val id: Int,
    val text: String
)

@Composable
fun FilterSelectionBar(
    viewmodel: FilterSelectionBarViewModel = hiltViewModel(),
    listItem: List<FilterItem> = emptyList(),
    onSelectedChange: (List<Int>) -> Unit
) {
    val selectedIndexList = viewmodel.selectedIndexList

    Box(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(color = Color.White)
    ) {
        LazyRow(
            Modifier
                .fillMaxWidth()
                .selectableGroup() // Optional, for accessibility purpose
        ) {
            items(count = listItem.size) { index ->
                Text(
                    text = listItem[index].text,
                    textAlign = TextAlign.Center,
                    style = AppTypography.bodyMedium,
                    color = if (selectedIndexList.contains(index)) Color.White else Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minWidth = 100.dp)
                        .padding(4.dp)
                        .border(
                            width = 0.5.dp, color = Color.LightGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(
                            color = if (selectedIndexList.contains(index)) Color.Black
                            else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clip(shape = RoundedCornerShape(12.dp))
                        .selectable(
                            selected = selectedIndexList.contains(index),
                            onClick = {
                                viewmodel.select(index)
                                onSelectedChange.invoke(selectedIndexList)
                            }
                        )
                        .padding(6.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun FilterSelectionBarPrev() {
    Surface {
        FilterSelectionBar(
            listItem = listOf(
                FilterItem(id = 1, "All"),
                FilterItem(id = 1, "T-Shirts"),
                FilterItem(id = 1, "Jeans"),
                FilterItem(id = 1, "Shoes"),
                FilterItem(id = 1, "Liz"),
                FilterItem(id = 1, "Yellow"),
                FilterItem(id = 1, "Long Long"),
                FilterItem(id = 1, "Liz"),
                FilterItem(id = 1, "Yellow"),
                FilterItem(id = 1, "Long Long"),
                FilterItem(id = 1, "Liz"),
            )
        ) {}
    }
}