@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun OTPScreen(
    onNavigationBack: () -> Unit, email: String, onOTPEntered: (String) -> Unit
) {
    var otp by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(topBar = { AppTopAppBar(onBack = onNavigationBack) },
        containerColor = Color.White,
        content = { padding ->
            Box {
                Box(Modifier.padding(padding)) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                    ) {
                        Text("Enter 4 Digit Code", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = "Enter 4 digit code that your receive on your email ($email)."
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        OTPInput(
                            otpLength = 4,
                            onOTPEntered = {
                                otp = it
                                Toast.makeText(context, "OT: $it", Toast.LENGTH_SHORT).show()
                                Log.d("OTP", otp)

                            })
                        Spacer(modifier = Modifier.size(20.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                "Email not received? ", style = LocalTextStyle.current.copy(
                                    color = Color.Gray,
                                )
                            )
                            Text(
                                "Resend code", modifier = Modifier.clickable {
                                    Toast.makeText(
                                        context, "Resend code success", Toast.LENGTH_SHORT
                                    ).show()
                                }, style = LocalTextStyle.current.copy(
                                    color = Color.Black, textDecoration = TextDecoration.Underline
                                )
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Button(modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 50.dp)
                            .imePadding(),
                            enabled = otp.trim().length > 3,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                            ),
                            shape = RoundedCornerShape(12.dp),
                            onClick = {
                                // You can perform verification logic here
                                onOTPEntered(otp)
                            }) {
                            Text(text = "Verify")
                        }
                    }
                }
            }
        })
}

@Composable
fun OTPInput(
    otpLength: Int = 4,
    onOTPEntered: (String) -> Unit
) {
    var otpText by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    BasicTextField(modifier = Modifier.focusRequester(focusRequester),
        value = otpText,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        onValueChange = {
            if (it.length <= otpLength) {
                otpText = it
            }

            if (otpText.length >= otpLength) {
                focusManager.clearFocus()
                onOTPEntered(otpText)
            }
        }) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(otpLength) { index ->
                val number = when {
                    index >= otpText.length -> ""
                    else -> otpText[index]
                }

                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp)
                        .border(
                            width = 1.dp, color = when (otpText.length) {
                                index -> Color.Gray
                                else -> Color.LightGray
                            }, shape = RoundedCornerShape(12.dp)
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = number.toString(),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun OTPScreenPrev() {
    OnlineStoreComposeTheme {
        Surface {
            OTPScreen(email = "example@email.com", onOTPEntered = {}, onNavigationBack = {})
        }
    }
}