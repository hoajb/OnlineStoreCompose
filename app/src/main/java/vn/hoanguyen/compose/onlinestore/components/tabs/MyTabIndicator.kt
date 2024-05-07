package vn.hoanguyen.compose.onlinestore.components.tabs

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
private fun TabIndicator(
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(
                width = indicatorWidth,
            )
            .offset(
                x = indicatorOffset,
            )
            .clip(
                shape = RoundedCornerShape(10.dp),
            )
            .background(
                color = indicatorColor,
            ),
    )
}

@Composable
private fun TabItem(
    isSelected: Boolean,
    onClick: () -> Unit,
    tabWidth: Dp,
    text: String,
) {
    val tabTextColor: Color by animateColorAsState(
        targetValue = if (isSelected) {
            Black
        } else {
            Gray
        },
        animationSpec = tween(easing = LinearEasing, durationMillis = 100), label = "",
    )
    Text(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onClick()
            }
            .width(tabWidth)
            .padding(
                vertical = 8.dp,
                horizontal = 12.dp,
            ),
        text = text,
        textAlign = TextAlign.Center,
        style = AppTypography.bodyMedium.copy(
            color = tabTextColor
        )
    )
}

@Composable
fun AppCustomTab(
    selectedItemIndex: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    tabWidth: Dp = 300.dp,
    onClick: (index: Int) -> Unit,
) {
    val tabSize = (tabWidth / 2 - 4.dp)
    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabSize * selectedItemIndex,
        animationSpec = tween(easing = LinearEasing, durationMillis = 100), label = "",
    )

    Box(
        modifier = modifier
            .background(
                Color.LightGray.copy(alpha = 0.5f), shape = RoundedCornerShape(12.dp)
            )
            .height(intrinsicSize = IntrinsicSize.Min)
            .padding(4.dp),
    ) {
        TabIndicator(
            indicatorWidth = tabSize,
            indicatorOffset = indicatorOffset,
            indicatorColor = Color.White,
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.clip(RoundedCornerShape(12.dp)),
        ) {
            items.mapIndexed { index, text ->
                val isSelected = index == selectedItemIndex
                TabItem(
                    isSelected = isSelected,
                    onClick = {
                        onClick(index)
                    },
                    tabWidth = tabSize,
                    text = text,
                )
            }
        }
    }
}

@Composable
fun CustomTabSample() {
    val configuration = LocalConfiguration.current
    val (selected, setSelected) = remember {
        mutableIntStateOf(1)
    }

    AppCustomTab(
        items = listOf("MUSIC", "PODCAST"),
        selectedItemIndex = selected,
        onClick = setSelected,
        tabWidth = (configuration.screenWidthDp).dp
    )
}

@Preview
@Composable
private fun CustomTabSamplePrev() {
    OnlineStoreComposeTheme {
        CustomTabSample()
    }
}