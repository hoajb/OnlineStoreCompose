package vn.hoanguyen.compose.onlinestore.features.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.AuthService
import javax.inject.Inject


@HiltViewModel
class LoginViewmodel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    val emailText = mutableStateOf("")
    val passText = mutableStateOf("")
    val loading = mutableStateOf(false)
    val error = mutableStateOf("")

    fun login(navigateToHome: () -> Unit) {
        if (emailText.value.isEmpty() || passText.value.isEmpty()) {
            error.value = "Email or Pass is invalid"
            return
        }
        loading.value = true
        viewModelScope.launch {
            authService.signInWithEmailAndPassword(emailText.value, passText.value).fold(
                onSuccess = {
                    loading.value = false
                    navigateToHome.invoke()
                },
                onFailure = { e ->
                    loading.value = false
                    error.value = "[${e.message}]"
                }
            )
        }
    }

    fun onLoginByGoogle() {
        error.value = "Feature is under development"
    }

    fun onLoginByFacebook() {
        error.value = "Feature is under development"
    }
}