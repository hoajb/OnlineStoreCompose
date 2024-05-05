package vn.hoanguyen.compose.onlinestore.features.main.checkout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun EnterPromoCode(
    modifier: Modifier = Modifier, onAddedPromoCode: () -> Unit
) {
    val (buttonStatus, updateButtonStatus) = remember { mutableStateOf(false) }
    val (searchText, updateSearchText) = remember { mutableStateOf("") }
    val promoList = remember { mutableStateListOf<String>() }
    val focusManager = LocalFocusManager.current

    val onPromo: () -> Unit = {
        updateSearchText("")
        promoList.add(searchText)
        onAddedPromoCode.invoke()
        updateButtonStatus(false)
        focusManager.clearFocus()
    }
    Column(
        modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.LightGray,
                    unfocusedPlaceholderColor = Color.LightGray,
                ),
                shape = RoundedCornerShape(12.dp),
                value = searchText,
                onValueChange = {
                    updateButtonStatus(it.isNotEmpty())
                    updateSearchText(it)
                },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                textStyle = AppTypography.bodyMedium.copy(
                    color = Color.Gray
                ),
                placeholder = { Text("Enter promo code") },
                leadingIcon = {
                    Icon(
                        Icons.Outlined.Sell, tint = Color.Gray, contentDescription = "search"
                    )
                },

                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Characters,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(onGo = {
                    onPromo.invoke()
                }),
                singleLine = true,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(modifier = Modifier
                .width(80.dp)
                .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                enabled = buttonStatus,
                onClick = {
                    onPromo.invoke()
                }) {
                Text(text = "Add", style = AppTypography.bodyMedium.copy(color = Color.White))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        PromoList(modifier = Modifier.fillMaxWidth(),
            promoList = promoList,
            onDelete = { promoText ->
                promoList.remove(promoText)
            })
    }
}

@Composable
fun PromoList(
    modifier: Modifier = Modifier, promoList: List<String>, onDelete: (String) -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        promoList.onEach { promoText ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = promoText,
                    style = AppTypography.titleSmall.copy(color = Color.Gray)
                )

                Icon(
                    modifier = Modifier
                        .size(36.dp)
                        .padding(8.dp)
                        .clickable {
                            onDelete.invoke(promoText)
                        }, imageVector = Icons.Outlined.Clear, contentDescription = "clear_promo"
                )
            }
        }
    }
}

@Preview
@Composable
private fun PromoListPrev() {
    OnlineStoreComposeTheme {
        PromoList(promoList = listOf("ABCXYZ", "AABCFJD"), onDelete = {})
    }
}

@Preview
@Composable
private fun EnterPromoCodePrev() {
    OnlineStoreComposeTheme {
        EnterPromoCode(onAddedPromoCode = {})
    }
}