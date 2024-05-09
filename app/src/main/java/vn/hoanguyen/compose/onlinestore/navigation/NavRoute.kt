package vn.hoanguyen.compose.onlinestore.navigation

private object Path {
    const val SPLASH = "splash"
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGOT_PASSWORD = "forgot_password"
    const val RESET_PASSWORD = "Reset_password"
    const val OTP = "otp"
    const val MAIN = "main"
    const val HOME = "home"
    const val SEARCH = "search"
    const val SAVED = "saved"
    const val CART = "cart"
    const val ACCOUNT = "account"
    const val CHECKOUT = "checkout"

    const val MY_ORDERS = "my_orders"
    const val MY_DETAILS = "my_details"
    const val ADDRESS_BOOK = "address_book"
    const val ADDRESS_ADD = "address_add"
    const val PAYMENT_METHODS = "payment_methods"
    const val CARD_ADD = "card_add"
    const val NOTIFICATION_SETTING = "notification_setting"
    const val NOTIFICATIONS = "notifications"
    const val FAQS = "faqs"
    const val HELP_CENTER = "help_center"

    const val PRODUCT_DETAILS = "product_details"
}

sealed class NavRoute(val path: String) {
    data object Splash : NavRoute(Path.SPLASH)
    data object Welcome : NavRoute(Path.WELCOME)
    data object Login : NavRoute(Path.LOGIN)
    data object Register : NavRoute(Path.REGISTER)
    data object ForgotPassword : NavRoute(Path.FORGOT_PASSWORD)
    data object ResetPassword : NavRoute(Path.RESET_PASSWORD)
    data object OTP : NavRoute(Path.OTP)
    data object Main : NavRoute(Path.MAIN)
    data object Home : NavRoute(Path.HOME)
    data object Saved : NavRoute(Path.SAVED)
    data object Search : NavRoute(Path.SEARCH)
    data object Cart : NavRoute(Path.CART)
    data object Account : NavRoute(Path.ACCOUNT)
    data object Checkout : NavRoute(Path.CHECKOUT)

    data object MyOrders : NavRoute(Path.MY_ORDERS)
    data object MyDetails : NavRoute(Path.MY_DETAILS)
    data object AddressBook : NavRoute(Path.ADDRESS_BOOK)
    data object AddressAddNew : NavRoute(Path.ADDRESS_ADD)
    data object PaymentMethods : NavRoute(Path.PAYMENT_METHODS)
    data object CardAddNew : NavRoute(Path.CARD_ADD)
    data object NotificationSetting : NavRoute(Path.NOTIFICATION_SETTING)
    data object Notifications : NavRoute(Path.NOTIFICATIONS)
    data object FAQS : NavRoute(Path.FAQS)
    data object HelpCenter : NavRoute(Path.HELP_CENTER)


    data object ProductDetails : NavRoute(Path.PRODUCT_DETAILS) {
        val productId = "productId"
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