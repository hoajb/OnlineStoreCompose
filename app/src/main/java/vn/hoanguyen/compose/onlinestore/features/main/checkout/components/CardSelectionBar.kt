package vn.hoanguyen.compose.onlinestore.features.main.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import javax.inject.Inject

@HiltViewModel()
class PaymentSelectionBarViewModel @Inject constructor(
) : ViewModel() {
    private val _selectedIndexList = mutableStateListOf<Int>()
    val selectedIndexList: List<Int> = _selectedIndexList

    fun select(index: Int) {
        if (_selectedIndexList.contains(index).not()) {
            _selectedIndexList.clear()
            _selectedIndexList.add(index)
        }
    }
}

data class PaymentItem(
    val id: Int,
    val text: String,
    val icon: ImageVector?,
)

@Composable
fun PaymentSelectionBar(
    viewmodel: PaymentSelectionBarViewModel,
    modifier: Modifier = Modifier,
    listItem: List<PaymentItem> = emptyList(),
    onSelectedChange: (List<Int>) -> Unit
) {
    val selectedIndexList = viewmodel.selectedIndexList

    LaunchedEffect(Unit) {
        viewmodel.select(0)
    }

    Box(
        modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(color = Color.Transparent)
    ) {
        LazyRow(
            Modifier
                .fillMaxWidth()
                .selectableGroup() // Optional, for accessibility purpose
        ) {
            items(count = listItem.size) { index ->
                Row(
                    modifier = Modifier
                        .width(120.dp)
                        .height(50.dp)
                        .padding(4.dp)
                        .border(
                            width = 0.5.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(
                            color = if (selectedIndexList.contains(index)) Color.Black
                            else Color.Transparent, shape = RoundedCornerShape(12.dp)
                        )
                        .clip(shape = RoundedCornerShape(12.dp))
                        .selectable(selected = selectedIndexList.contains(index), onClick = {
                            viewmodel.select(index)
                            onSelectedChange.invoke(selectedIndexList)
                        }),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    listItem[index].icon?.let {
                        Icon(
                            imageVector = it,
                            contentDescription = "pay",
                            tint = if (selectedIndexList.contains(index)) Color.White else Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(
                        text = listItem[index].text,
                        textAlign = TextAlign.Center,
                        style = AppTypography.bodyMedium.copy(
                            fontWeight = FontWeight.W500
                        ),
                        color = if (selectedIndexList.contains(index)) Color.White else Color.Black,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PaymentSelectionBarPrev() {
    Surface {
        PaymentSelectionBar(
            viewmodel = viewModel(), listItem = listOf(
                PaymentItem(id = 1, text = "Card", icon = Icons.Outlined.CreditCard),
                PaymentItem(id = 2, text = "Cash", icon = Icons.Outlined.Money),
                PaymentItem(id = 3, text = "Apple Pay", icon = null),
            )
        ) {}
    }
}