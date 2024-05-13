# OnlineStoreCompose

OnlineStoreCompose is an Android Compose project dedicated to crafting a beautiful and intuitive
user interface for an online store application using Jetpack Compose.
Modern UI designed with Android Compose.

## Figma Design
Public Figma Community
For design mockups and prototypes, please refer to
the [Figma Mockup](https://www.figma.com/file/1AlIWGpEbtPmpOdApSDTZT/Spotify-Ecommerce-%3D-Creative-Modern-Mobile-Application-Ui-Design-Kit-(Preview)?type=design&node-id=0-1&mode=design&t=k5nmA5bBnrOmxBfV-0
) where you can find public community design.

## Features

- Auth (Login, Register, Forgot Password, OTP Input)
- HomePage with Product List
- SearchBar
- Filter Popup
- Product List with Favorite
- Cart List, Checkout
- Payment Method, Order Tracker
- Review Popup
- Notification Setting and List
- User Details Information

## Technical

- Compose UI(Compose Navigation, Theme, Font custom, Material Icons)
- Viewmodel
- Dagger/Hilt
- Phone number input with flag
- Google Maps, Location and Permissions, secrets Maps API key

## How to run
Just Run app on Android Studio. No authenticate requires.

- Add MAPS_API_KEY (Not require) to secrets.properties to see Maps in Address Add New page feature. Then keep secrets.properties file it in gitignore.

#https://developers.google.com/maps/documentation/embed/get-api-key
#MAPS_API_KEY=AIza-xxxxxxxxxxxxxxxxxxxxxx
MAPS_API_KEY=

## Screenshots

<table>
  <tr>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Onboarding.jpg" alt="Login" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Login.jpg" alt="Login" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Register.jpg" alt="Register" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/ResetPassword.jpg" alt="ResetPassword" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Home.jpg" alt="Home" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Filter.jpg" alt="Filter" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Saved.jpg" alt="Saved" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/EmptyPage.jpg" alt="EmptyPage" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Details.jpg" alt="Details" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/MyDetails.jpg" alt="MyDetails" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Cartd.jpg" alt="Cartd" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Checkout.jpg" alt="Checkout" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/OrderCompleted.jpg" alt="OrderCompleted" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/OrderOngoing.jpg" alt="OrderOngoing" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Payment.jpg" alt="Payment" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Address.jpg" alt="Address" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/CardInput.jpg" alt="CardInput" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/NewAddress.jpg" alt="NewAddress" width="200"></td>
  </tr>
  <tr>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Notification.jpg" alt="Notification" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/FAQs.jpg" alt="FAQs" width="200"></td>
    <td><img src="https://github.com/hoajb/OnlineStoreCompose/blob/master/screenshoots/Review.jpg" alt="Review" width="200"></td>
  </tr>
</table>


## Getting Started

To get started with OnlineStoreCompose, simply clone the repository and open it in Android Studio:

```bash
git clone git@github.com:hoajb/OnlineStoreCompose.git
