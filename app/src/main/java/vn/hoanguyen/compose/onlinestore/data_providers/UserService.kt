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