@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.auth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.hoanguyen.compose.onlinestore.components.AppPasswordTextField
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.isValidPassword

sealed interface ResetPassStatus {
    data object NotMatchedError : ResetPassStatus
    data object LengthError : ResetPassStatus
    data object Idle : ResetPassStatus
}

@Composable
fun ResetPasswordScreen(
    onNavigationBack: () -> Unit,
    onNavigationToLogin: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = FocusRequester()
    var passText by remember { mutableStateOf("") }
    var passReenterText by remember { mutableStateOf("") }
    var resetPassStatus: ResetPassStatus by remember { mutableStateOf(ResetPassStatus.Idle) }

    Scaffold(topBar = { AppTopAppBar(onBack = onNavigationBack) },
        containerColor = Color.White,
        content = { padding ->
            Box(Modifier.padding(padding)) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                ) {
                    Text("Reset Password", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Set the new password for your account so you can login and access all the features."
                    )
                    Spacer(modifier = Modifier.size(20.dp))

                    AppPasswordTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        label = "Password",
                        value = passText,
                        onValueChange = {
                            passText = it
                            resetPassStatus = ResetPassStatus.Idle
                        },
                        placeholder = "Enter your password",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        isError = resetPassStatus != ResetPassStatus.Idle,
                        supportingText = when (resetPassStatus) {
                            is ResetPassStatus.NotMatchedError -> "Password is not matched"
                            ResetPassStatus.LengthError -> "Password must have more than 6 characters"
                            else -> ""
                        }
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    AppPasswordTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        label = "Password",
                        value = passReenterText,
                        onValueChange = {
                            passReenterText = it
                            resetPassStatus = ResetPassStatus.Idle
                        },
                        placeholder = "Enter your password",
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            focusManager.clearFocus()
                        }),
                        isError = resetPassStatus != ResetPassStatus.Idle,
                        supportingText = when (resetPassStatus) {
                            is ResetPassStatus.NotMatchedError -> "Password is not matched"
                            ResetPassStatus.LengthError -> "Password must have more than 6 characters"
                            else -> ""
                        }
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 50.dp),
                        enabled = passText.isNotEmpty() && passReenterText.isNotEmpty(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                        ),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
                            if (passText.isNotEmpty() && passReenterText.isNotEmpty()) {
                                if (passText != passReenterText) {
                                    resetPassStatus = ResetPassStatus.NotMatchedError
                                } else if (passText.isValidPassword().not()
                                    || passReenterText.isValidPassword().not()
                                ) {
                                    resetPassStatus = ResetPassStatus.LengthError
                                } else {
                                    onNavigationToLogin.invoke()
                                }
                            }
                        }) {
                        Text(text = "Continue")
                    }
                }
            }
        })
}

@Preview
@Composable
private fun ResetPasswordPrev() {
    OnlineStoreComposeTheme {
        Surface {
            ResetPasswordScreen(onNavigationToLogin = {}, onNavigationBack = {})
        }
    }
}