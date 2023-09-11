package com.example.practise2023.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.practise2023.R
import com.example.practise2023.domain.model.Article
import com.example.practise2023.presentation.Dimens.BigIconSize
import com.example.practise2023.presentation.Dimens.ExtraSmallPadding2
import com.example.practise2023.presentation.Dimens.ExtraSmallPadding3
import com.example.practise2023.presentation.Dimens.MediumPadding1
import com.example.practise2023.presentation.Dimens.SmallIconSize
import com.example.practise2023.presentation.common.ArticlesList
import com.example.practise2023.presentation.common.FiltersList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@Composable
fun HomeScreen(
    //navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.state.value
    val event = viewModel::onEvent
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(colorResource(id = R.color.top_bar_bg)) //.padding(horizontal = MediumPadding1),
//                .padding(top = ExtraSmallPadding2, bottom = ExtraSmallPadding2),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//
//            Text(
//                text = "#legend",
//                style = MaterialTheme.typography.labelLarge.copy(color = Color.Black)
//            )
//
//            IconButton(onClick = { /*TODO*/ }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.bell),
//                    contentDescription = null,
//                    tint = Color.Black,
//                    modifier = Modifier.size(SmallIconSize)
//                )
//            }
//        }


        Spacer(modifier = Modifier.height(MediumPadding1))

        FiltersList(
            event = viewModel::onEvent,
            categories = state.categories,
            categoryIndex = state.selectedCategoryIndex,
            modifier = Modifier
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(ExtraSmallPadding3))

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { event(HomeEvent.LoadStuff) },
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    backgroundColor = Color.DarkGray,
                    contentColor = colorResource(id = R.color.top_bar_bg)
                )
            },
        ) {
            state.articles?.let {
                val articles = it.collectAsLazyPagingItems()
                ArticlesList(
                    modifier = Modifier.padding(horizontal = MediumPadding1),
                    articles = articles,
                    onClick = navigateToDetails
                )
            }
        }
    }
}