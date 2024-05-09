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

    private var faqList: List<FAQ> = emptyList()

    suspend fun getFAQList(): List<FAQ> {
        return withContext(Dispatchers.IO) {
            if (faqList.isEmpty()) {
                val productsArray =
                    gson.fromJson(faqJson, Array<FAQ>::class.java)
                faqList = productsArray.toList()
            }

            faqList
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

private const val faqJson =
    "[\n" +
            "  {\n" +
            "    \"title\": \"How do I make a purchase?\",\n" +
            "    \"content\": \"When you find a product you want to purchase, tap on it to view the product details. Check the price, description, and available options (if applicable), and then tap the \\\"Add to Cart\\\" button. Follow the on-screen instructions to complete the purchase, including providing shipping details and payment information.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Can I return an item if I'm not satisfied?\",\n" +
            "    \"content\": \"Yes, most items can be returned within a specified return period if you're not satisfied. Please review our return policy for details on how to initiate a return and any conditions that may apply.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Do you offer gift wrapping services?\",\n" +
            "    \"content\": \"Yes, we offer gift wrapping services for an additional fee. During the checkout process, you'll have the option to select gift wrapping and add a personalized message to your order.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How can I contact customer support?\",\n" +
            "    \"content\": \"You can contact our customer support team by phone, email, or live chat. Visit our Contact Us page for more information on how to reach us.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Are there any discounts or promotions available?\",\n" +
            "    \"content\": \"We frequently offer discounts and promotions on select items. Sign up for our newsletter or follow us on social media to stay updated on the latest deals.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What should I do if I forgot my password?\",\n" +
            "    \"content\": \"If you forgot your password, you can reset it by clicking on the \\\"Forgot Password\\\" link on the login page. Follow the instructions to reset your password using your registered email address.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Is my personal information secure?\",\n" +
            "    \"content\": \"Yes, we take the security of your personal information seriously. We use industry-standard encryption and security measures to protect your data.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Do you ship internationally?\",\n" +
            "    \"content\": \"Yes, we offer international shipping to many countries. During the checkout process, you can select your country from the list of available shipping destinations.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How can I unsubscribe from marketing emails?\",\n" +
            "    \"content\": \"You can unsubscribe from marketing emails by clicking on the \\\"Unsubscribe\\\" link at the bottom of any promotional email you receive from us. Alternatively, you can update your email preferences in your account settings.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Can I change or cancel my order after it's been placed?\",\n" +
            "    \"content\": \"Once an order has been placed, it may not be possible to change or cancel it, especially if it's already been processed for shipping. Please contact customer support as soon as possible if you need to make any changes to your order.\",\n" +
            "    \"type\": 0\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How do I create an account?\",\n" +
            "    \"content\": \"To create an account, click on the 'Sign Up' or 'Register' button on the homepage. Fill in the required information such as your email address, name, and password. Once completed, click on the 'Submit' button to create your account. You may need to verify your email address to activate your account.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What are the benefits of creating an account?\",\n" +
            "    \"content\": \"Creating an account allows you to save your shipping addresses, track your orders, and manage your preferences more easily.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How can I update my account information?\",\n" +
            "    \"content\": \"You can update your account information, including your email address, shipping address, and password, by logging into your account and navigating to the 'Account Settings' or 'Profile' section. From there, you can edit your information and save the changes.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Can I have multiple shipping addresses saved?\",\n" +
            "    \"content\": \"Yes, you can save multiple shipping addresses to your account. During the checkout process, you'll have the option to select from your saved addresses or add a new one.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What should I do if I suspect unauthorized activity on my account?\",\n" +
            "    \"content\": \"If you suspect unauthorized activity on your account, such as unauthorized purchases or changes to your information, please contact customer support immediately. We will investigate the issue and take appropriate action to secure your account.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How do I delete my account?\",\n" +
            "    \"content\": \"To delete your account, please contact customer support and request an account deletion. Note that deleting your account will permanently remove all your account information, including order history and saved addresses.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Do I need an account to make a purchase?\",\n" +
            "    \"content\": \"While you can make a purchase as a guest, creating an account offers additional benefits such as order tracking and faster checkout for future purchases.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What is the minimum age requirement to create an account?\",\n" +
            "    \"content\": \"You must be at least 18 years old to create an account on our platform. By creating an account, you confirm that you meet this age requirement.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How can I recover my account if I forget my login credentials?\",\n" +
            "    \"content\": \"If you forget your login credentials, such as your password or email address, you can use the 'Forgot Password' feature on the login page to reset your password. If you've forgotten your email address, please contact customer support for assistance.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Can I change my email address associated with my account?\",\n" +
            "    \"content\": \"Yes, you can change the email address associated with your account by logging into your account and navigating to the 'Account Settings' or 'Profile' section. From there, you can update your email address and save the changes.\",\n" +
            "    \"type\": 1\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How do I track my order?\",\n" +
            "    \"content\": \"You can track your order by logging into your account and navigating to the 'Order History' section. Here, you'll find a list of all your previous orders along with their current status. Click on the order you wish to track for more detailed information, including tracking numbers and estimated delivery dates.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What should I do if my order is delayed?\",\n" +
            "    \"content\": \"If your order is delayed, we apologize for any inconvenience. Please check the estimated delivery date provided with your order confirmation email. If your order has not arrived by the expected delivery date, please contact our customer support team for assistance.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Do you offer expedited shipping?\",\n" +
            "    \"content\": \"Yes, we offer expedited shipping options for an additional fee. During the checkout process, you'll have the option to select expedited shipping if available for your location.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What is your customer satisfaction guarantee?\",\n" +
            "    \"content\": \"We strive to provide excellent customer service and stand behind the quality of our products. If you are not completely satisfied with your purchase, please contact us within [number] days of receiving your order for assistance.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How can I provide feedback or suggestions?\",\n" +
            "    \"content\": \"We welcome your feedback and suggestions to improve our products and services. You can submit feedback through our website's contact form or email us directly at [email address]. Your input helps us better serve you and other customers.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Do you offer installation or setup services?\",\n" +
            "    \"content\": \"While we don't offer installation or setup services directly, some products may come with installation instructions or recommendations. If you need assistance, we recommend consulting with a professional installer or contacting the manufacturer for support.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Can I change the shipping address after placing an order?\",\n" +
            "    \"content\": \"If you need to change the shipping address after placing an order, please contact our customer support team as soon as possible. We'll do our best to accommodate your request, but changes may not be possible if the order has already been processed for shipping.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What should I do if I receive the wrong item?\",\n" +
            "    \"content\": \"If you receive the wrong item in your order, please contact our customer support team immediately. We'll arrange for the correct item to be sent to you as soon as possible, and we may provide a prepaid return label for the incorrect item.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Are there any restrictions on shipping certain items?\",\n" +
            "    \"content\": \"Yes, there may be restrictions on shipping certain items due to size, weight, or shipping regulations. Please review our shipping policy for information on any restrictions that may apply to your order.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How can I check the status of my refund?\",\n" +
            "    \"content\": \"You can check the status of your refund by logging into your account and navigating to the 'Order History' section. Here, you'll find information on any refunds processed for your orders. If you have any questions or concerns, please contact our customer support team for assistance.\",\n" +
            "    \"type\": 2\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What payment methods do you accept?\",\n" +
            "    \"content\": \"We accept various payment methods including credit/debit cards (Visa, MasterCard, American Express), PayPal, and sometimes other options like Apple Pay or Google Pay. During the checkout process, you can select your preferred payment method and follow the prompts to complete your purchase.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Is it safe to enter my credit card information on your website?\",\n" +
            "    \"content\": \"Yes, it is safe to enter your credit card information on our website. We use industry-standard encryption and security measures to protect your personal and financial information during the checkout process.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Can I save my payment information for future purchases?\",\n" +
            "    \"content\": \"Yes, you can choose to save your payment information securely on our website for faster checkout on future purchases. Your payment details will be encrypted and stored in accordance with industry standards.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Do you offer installment payment options?\",\n" +
            "    \"content\": \"Yes, we may offer installment payment options for certain purchases. During the checkout process, you'll have the option to select installment payments if available for your order. Please review the terms and conditions for installment payments before proceeding.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Can I use multiple payment methods for a single order?\",\n" +
            "    \"content\": \"At this time, we only support using one payment method per order. If you wish to use multiple payment methods, you may need to split your order into separate transactions.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"What should I do if my payment is declined?\",\n" +
            "    \"content\": \"If your payment is declined, please double-check that the information entered is correct and that your payment method is valid. If the issue persists, you may need to contact your bank or financial institution for further assistance.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Are there any additional fees for using certain payment methods?\",\n" +
            "    \"content\": \"Some payment methods may incur additional fees, such as currency conversion fees or processing fees. Please review the terms and conditions of your chosen payment method for information on any applicable fees.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"How can I request a refund for my order?\",\n" +
            "    \"content\": \"To request a refund for your order, please contact our customer support team with your order details and reason for the refund. We'll process your refund according to our refund policy, and the funds will be returned to the original payment method used for the purchase.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Is it possible to change the payment method after placing an order?\",\n" +
            "    \"content\": \"Once an order has been placed, it may not be possible to change the payment method. If you need to update your payment information, please contact our customer support team for assistance as soon as possible.\",\n" +
            "    \"type\": 3\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Do you offer discounts for specific payment methods?\",\n" +
            "    \"content\": \"We may offer discounts or promotions for specific payment methods from time to time. Please check our website or promotional materials for information on any current offers.\",\n" +
            "    \"type\": 3\n" +
            "  }\n" +
            "]"