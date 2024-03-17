package com.yonasoft.jadedictionary.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yonasoft.jadedictionary.data.constants.Screen
import com.yonasoft.jadedictionary.ui.screens.account.AccountScreen
import com.yonasoft.jadedictionary.ui.screens.lists.ListsScreen
import com.yonasoft.jadedictionary.ui.screens.search.SearchScreen

@Composable
fun setupNavigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.Search.route ){
        composable(Screen.Search.route){
            SearchScreen()
        }
        composable(Screen.Lists.route){
            ListsScreen()
        }
        composable(Screen.Account.route){
            AccountScreen(navController = navController)
        }
    }
}