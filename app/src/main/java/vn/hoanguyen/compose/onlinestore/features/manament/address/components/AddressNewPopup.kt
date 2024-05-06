package vn.hoanguyen.compose.onlinestore.features.manament.address.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.components.AppDivider
import vn.hoanguyen.compose.onlinestore.components.AppTextField
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography

@Composable
fun AddressNewPopupContent(
    onHidePopup: () -> Unit,
    onAddAddress: (String, String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()

    val (addressNickName, updateAddressNickName) = remember { mutableStateOf("") }
    val (addressFull, updateAddressFull) = remember { mutableStateOf("") }
    val (markDefaultAddress, updateMarkDefaultAddress) = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Text("Address", style = AppTypography.titleLarge)
            IconButton(onClick = onHidePopup) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "close")
            }
        }
        AppDivider()
        Spacer(modifier = Modifier.height(10.dp))
        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            label = "Address Nickname",
            value = addressNickName,
            singleLine = true,
            onValueChange = updateAddressNickName,
            placeholder = "Nick name",
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            label = "Full Address",
            value = addressFull,
            singleLine = true,
            onValueChange = updateAddressFull,
            placeholder = "Enter your full address",
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            }),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = markDefaultAddress,
                onCheckedChange = updateMarkDefaultAddress,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black,
                    uncheckedColor = Color.Gray
                )
            )
            Text(
                text = "Make this as a default address",
                style = AppTypography.bodyMedium.copy(color = Color.Gray)
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .defaultMinSize(minHeight = 50.dp),
            enabled = addressNickName.isNotEmpty() && addressFull.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            shape = RoundedCornerShape(12.dp),
            content = { Text("Add") },
            onClick = {
                focusManager.clearFocus()
                onHidePopup.invoke()
                onAddAddress(addressNickName, addressFull)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddressNewPopupContentPrev() {
    Surface {
        AddressNewPopupContent(
            onAddAddress = { _, _ -> },
            onHidePopup = {}
        )
    }
}