package vn.hoanguyen.compose.onlinestore.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.AuthService
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AuthViewmodel @Inject constructor(
    private val authService: AuthService
) : ViewModel() {
    private val splashShowFlow = MutableStateFlow(true)
    val isSplashShow = splashShowFlow.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            splashShowFlow.value = false
        }
    }

    fun onNavigateHandle(
        onNavigateLogin: () -> Unit,
        onNavigateHome: () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                if (authService.isLoggedIn())
                    onNavigateHome.invoke()
                else
                    onNavigateLogin.invoke()

            } catch (_: Exception) {
            }
        }
    }
}