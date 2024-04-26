package vn.hoanguyen.compose.onlinestore.features.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.AuthService
import javax.inject.Inject


@HiltViewModel
class RegisterViewmodel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    val fullNameText = mutableStateOf("")
    val emailText = mutableStateOf("")
    val passText = mutableStateOf("")
    val rePassText = mutableStateOf("")
    val loading = mutableStateOf(false)
    val error = mutableStateOf("")

    fun register(navigateToHome: () -> Unit) {
        if (fullNameText.value.isEmpty() || emailText.value.isEmpty() || passText.value.isEmpty() || rePassText.value.isEmpty()) {
            error.value = "Please input all fields"
            return
        }

        if (passText.value != rePassText.value) {
            error.value = "Password is not matched."
            return
        }

        loading.value = true
        viewModelScope.launch {
            authService.signUpWithEmailAndPassword(emailText.value, passText.value)
                .fold(onSuccess = {
                    loading.value = false
                    navigateToHome.invoke()
                }, onFailure = { e ->
                    loading.value = false
                    error.value = "[${e.message}]"
                })
        }
    }

    fun onLoginBySocialNetwork(navigateToHome: () -> Unit) {
        loading.value = false
        navigateToHome.invoke()
    }
}