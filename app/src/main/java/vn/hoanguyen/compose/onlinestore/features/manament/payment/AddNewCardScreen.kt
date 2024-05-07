@file:OptIn(ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.manament.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.components.AppTextField
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.components.dialog.AppDialog
import vn.hoanguyen.compose.onlinestore.data_providers.CardType
import vn.hoanguyen.compose.onlinestore.features.manament.payment.components.DateVisualTransformation
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.toCardIcon

@Composable
fun AddNewCardScreen(
    onBack: () -> Unit
) {
    var showSuccess by remember { mutableStateOf(false) }
    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "New Card", onBack = onBack)
    }) { padding ->
        Box(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            val focusManager = LocalFocusManager.current
            val focusRequesterExpiryDate = FocusRequester()
            val focusRequesterCVC = FocusRequester()

            val (cardNumber, updateCardNumber) = remember { mutableStateOf("") }
            val (expiryDate, updateExpiryDate) = remember { mutableStateOf("") }
            val (securityCode, updateSecurityCode) = remember { mutableStateOf("") }

            Column(
            ) {
                Text("Add Debit or Credit Card", style = AppTypography.titleMedium)
                Spacer(modifier = Modifier.height(10.dp))
                AppTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = "Card Number",
                    value = cardNumber,
                    singleLine = true,
                    onValueChange = updateCardNumber,
                    placeholder = "Enter your card number",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    prefix = {
                        val cardType = detectCardType(cardNumber)
                        Image(
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp),
                            painter = painterResource(id = cardType.toCardIcon()),
                            contentDescription = "card type"
                        )
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    AppTextField(
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequesterExpiryDate),
                        label = "Expiry Date",
                        value = expiryDate,
                        singleLine = true,
                        onValueChange = { input ->
                            if (input.length <= 4) // MM YY
                                updateExpiryDate(input)
                        },
                        visualTransformation = remember { DateVisualTransformation() },
                        placeholder = "MM/YY",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    AppTextField(
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequesterCVC),
                        label = "Security Code",
                        value = securityCode,
                        singleLine = true,
                        onValueChange = {
                            if (it.length <= 3)
                                updateSecurityCode(it)
                        },
                        placeholder = "CVC",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        }),
                    )
                }

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .defaultMinSize(minHeight = 50.dp),
                    enabled = cardNumber.isNotEmpty() && expiryDate.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(12.dp),
                    content = { Text("Add") },
                    onClick = {
                        focusManager.clearFocus()
                        showSuccess = true
                    }
                )
            }
        }
    }

    if (showSuccess) {
        AppDialog(
            onDismissRequest = onBack,
            onPositivePressed = {},
            title = "Congratulation!",
            content = "Your new card has been added.",
            positive = "Thanks",
            icon = Icons.Default.CheckCircle,
            iconTint = Color.Green
        )
    }
}

fun detectCardType(cardNumber: String): String {
    return when {
        cardNumber.startsWith("4") -> CardType.VISA.type
        cardNumber.startsWith("5") -> CardType.MasterCard.type
        cardNumber.startsWith("3") -> CardType.JCB.type
        // Add more cases for other card types as needed
        else -> "Unknown"
    }
}

@Preview
@Composable
private fun AddNewCardScreenPrev() {
    OnlineStoreComposeTheme {
        AddNewCardScreen(onBack = {})
    }
}