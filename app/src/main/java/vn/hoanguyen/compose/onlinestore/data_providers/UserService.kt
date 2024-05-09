package vn.hoanguyen.compose.onlinestore.data_providers

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(
    private val gson: Gson
) {
    private var addressList: List<Address> = emptyList()

    suspend fun getAddressList(): List<Address> {
        return withContext(Dispatchers.IO) {
            if (addressList.isEmpty()) {
                val productsArray = gson.fromJson(addressListJson, Array<Address>::class.java)
                addressList = productsArray.toList()
            }

            addressList
        }
    }

    private var cardList: List<CardInfo> = emptyList()

    suspend fun getCardList(): List<CardInfo> {
        return withContext(Dispatchers.IO) {
            if (cardList.isEmpty()) {
                val productsArray = gson.fromJson(cardListJson, Array<CardInfo>::class.java)
                cardList = productsArray.toList()
            }

            cardList
        }
    }

    private var notificationsList: List<Notification> = emptyList()

    suspend fun getNotificationsList(): List<Notification> {
        return withContext(Dispatchers.IO) {
            if (notificationsList.isEmpty()) {
                val productsArray =
                    gson.fromJson(notificationsJson, Array<Notification>::class.java)
                notificationsList = productsArray.toList()
            }

            notificationsList
        }
    }
}

private const val cardListJson = "[\n" +
        "  {\n" +
        "    \"name\": \"American Express\",\n" +
        "    \"number\": \"378282246310005\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"name\": \"JCB\",\n" +
        "    \"number\": \"3530111333300000\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"name\": \"MasterCard\",\n" +
        "    \"number\": \"5105105105105100\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"name\": \"Visa\",\n" +
        "    \"number\": \"4222222222222\"\n" +
        "  }\n" +
        "]"

private const val addressListJson = "[\n" +
        "  {\n" +
        "    \"name\": \"Home\",\n" +
        "    \"address\": \"23 Hoang Dieu Street, District 1, Ho Chi Minh City, Vietnam, Postal Code: 700000\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"name\": \"Office\",\n" +
        "    \"address\": \"56 Tran Hung Dao Street, Hai Ba Trung District, Hanoi, Vietnam, Postal Code: 100000\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"name\": \"Apartment\",\n" +
        "    \"address\": \"89 Nguyen Hue Avenue, Da Nang City, Vietnam, Postal Code: 550000\"\n" +
        "  },\n" +
        "  {\n" +
        "    \"name\": \"Parent's House\",\n" +
        "    \"address\": \"12 Le Loi Street, Hue City, Vietnam, Postal Code: 530000\"\n" +
        "  }\n" +
        "]"

private const val notificationsJson =
   "[\n" +
           "  {\n" +
           "    \"title\": \"30% Special Discount!\",\n" +
           "    \"content\": \"Special promotion only valid today.\",\n" +
           "    \"type\": \"0\",\n" +
           "    \"timestamp\": 1715227585766\n" +
           "  },\n" +
           "  {\n" +
           "    \"title\": \"Top Up E-wallet Successfully!\",\n" +
           "    \"content\": \"You have top up your e-wallet.\",\n" +
           "    \"type\": \"1\",\n" +
           "    \"timestamp\": 1710176400000\n" +
           "  },\n" +
           "  {\n" +
           "    \"title\": \"New Service Available!\",\n" +
           "    \"content\": \"Now you can track order in real-time.\",\n" +
           "    \"type\": \"2\",\n" +
           "    \"timestamp\": 1710187440000\n" +
           "  },\n" +
           "  {\n" +
           "    \"title\": \"Credit Card Connected!\",\n" +
           "    \"content\": \"Credit card has been linked.\",\n" +
           "    \"type\": \"3\",\n" +
           "    \"timestamp\": 1707670800000\n" +
           "  },\n" +
           "  {\n" +
           "    \"title\": \"Account Setup Successfully!\",\n" +
           "    \"content\": \"Your account has been created.\",\n" +
           "    \"type\": \"4\",\n" +
           "    \"timestamp\": 1707717840000\n" +
           "  }\n" +
           "]\n"