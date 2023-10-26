package com.example.facebooksimilar

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.facebooksimilar.data.Shortcut
import com.example.facebooksimilar.data.getRandomItems
import com.example.facebooksimilar.navigation.BottomNav
import com.example.facebooksimilar.navigation.Destination
import com.example.facebooksimilar.ui.screen.HomeScreen
import com.example.facebooksimilar.ui.screen.ItemDetailScreen
import com.example.facebooksimilar.ui.screen.NavigationDrawerScreen
import com.example.facebooksimilar.ui.screen.NotificationScreen
import com.example.facebooksimilar.ui.theme.FacebookSimilarTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacebookSimilarTheme {
                // A surface container using the 'background' color from the theme
                androidx.compose.material.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    FacebookScaffold(navController = navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FacebookScaffold(navController: NavHostController) {

    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val onDrawerIconClick: () -> Unit = {
        scope.launch { scaffoldState.drawerState.open() }
    }
    val context = LocalContext.current

    val randomItems = remember { mutableStateOf(getRandomItems(10)) }
    val shortcuts = remember { mutableStateOf(Shortcut.getShortcuts()) }

    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNav(navController, onDrawerIconClick) },
        drawerContent = { NavigationDrawerScreen(randomItems = randomItems.value, shortcuts = shortcuts.value) }
    ) { padding ->
        val stdModifier = Modifier
            .padding(bottom = padding.calculateBottomPadding())
            .background(Color(0xFFcccccc))
        NavHost(
            navController = navController,
            startDestination = Destination.Home.route
        ) {
            composable(Destination.Home.route) {
                HomeScreen(
                    navController = navController,
                    modifier = stdModifier
                )
            }
            composable(Destination.Notification.route) {
                NotificationScreen(modifier = stdModifier)
            }

            composable(Destination.Detail.route,
                deepLinks = listOf(
                    navDeepLink { uriPattern = "https://www.fblikeapp.com/{itemId}" }
                )
            ) {
                val itemId = it.arguments?.getString("itemId")
                if (itemId == null)
                    Toast.makeText(context, "Id is required", Toast.LENGTH_SHORT).show()
                else
                    ItemDetailScreen(itemId.toInt(), modifier = stdModifier)
            }
        }

    }

}
