package vn.hoanguyen.compose.onlinestore.navigation

private object Path {
    const val SPLASH = "splash"
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGOT_PASSWORD = "forgot_password"
    const val RESET_PASSWORD = "Reset_password"
    const val OTP = "otp"
    const val HOME = "home"
    const val PROFILE = "profile"
}

sealed class NavRoute(val path: String) {
    data object Splash : NavRoute(Path.SPLASH)
    data object Welcome : NavRoute(Path.WELCOME)
    data object Login : NavRoute(Path.LOGIN)
    data object Register : NavRoute(Path.REGISTER)
    data object ForgotPassword : NavRoute(Path.FORGOT_PASSWORD)
    data object ResetPassword : NavRoute(Path.RESET_PASSWORD)
    data object OTP : NavRoute(Path.OTP)
    data object Home : NavRoute(Path.HOME)

    data object Profile : NavRoute(Path.PROFILE) {
        val id = "id"
        val showDetails = "showDetails"
    }

    // build navigation path (for screen navigation)
    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    // build and setup route format (in navigation graph)
    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}