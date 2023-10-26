package com.example.facebooksimilar.navigation

sealed class Destination(val route: String) {
    object Home : Destination("home")
    object Notification : Destination("notification")
    object Detail : Destination("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/$itemId"
    }

}
