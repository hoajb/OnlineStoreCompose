package vn.hoanguyen.compose.onlinestore.features.main.cart.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val ButtonSize = 32.dp

@Composable
fun AddItemCart(
    number: Int = 1,
    onUpdate: (Int) -> Unit
) {
    val num = remember { mutableIntStateOf(number) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .height(ButtonSize)
                .width(ButtonSize)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    if (num.intValue > 1) {
                        num.intValue -= 1
                        onUpdate.invoke(num.intValue)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "-", fontSize = 18.sp, textAlign = TextAlign.Center)
        }


        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = num.intValue.toString(), modifier = Modifier.width(24.dp),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .height(ButtonSize)
                .width(ButtonSize)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    if (num.intValue < 20) //max 20 items
                    {
                        num.intValue += 1
                        onUpdate.invoke(num.intValue)
                    }

                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "+", fontSize = 18.sp, textAlign = TextAlign.Center)
        }
    }
}

@Preview
@Composable
private fun AddItemCartPrev() {
    AddItemCart() {}
}