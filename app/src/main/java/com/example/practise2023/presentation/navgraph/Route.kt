package com.example.practise2023.presentation.navgraph

sealed class Route(
    val route:String
){
    object HomeScreen:Route(route = "homeScreen")
    object BookmarkScreen:Route(route = "bookmarkScreen")
    object DetailsScreen:Route(route = "detailsScreen")

}
