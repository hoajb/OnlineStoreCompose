package vn.hoanguyen.compose.onlinestore.features.manament.order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.components.AppDivider
import vn.hoanguyen.compose.onlinestore.components.AppTextField
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.ColorYellow

@Composable
fun ReviewPopupContent(
    onHidePopup: () -> Unit,
) {
    val focusRequester = FocusRequester()
    val (reviewText, updateReviewText) = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text("Leave a Review", style = AppTypography.titleLarge)
            IconButton(onClick = onHidePopup) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "close")
            }
        }
        AppDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Text("How was your order?", style = AppTypography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Please give your rating and also your review.",
            style = AppTypography.bodyMedium.copy(color = Color.Gray)
        )
        Spacer(modifier = Modifier.height(10.dp))

        RatingBarComposable()

        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            minLines = 4,
            value = reviewText,
            singleLine = true,
            onValueChange = updateReviewText,
            placeholder = "Write your review...",
        )

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .defaultMinSize(minHeight = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            content = { Text("Submit") },
            onClick = onHidePopup
        )
    }
}

@Composable
private fun RatingBarComposable() {
    var rating by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        repeat(5) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Filled.Star else Icons.Filled.StarOutline,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .clickable(
                        indication = null, // no ripple
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        rating = index + 1
                    }
                    .padding(4.dp),
                tint = ColorYellow
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun FilterPopupContentPrev() {
    Surface {
        ReviewPopupContent() {}
    }
}