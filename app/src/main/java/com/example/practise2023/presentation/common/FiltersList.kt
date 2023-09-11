package com.example.practise2023.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practise2023.R
import com.example.practise2023.presentation.Dimens
import com.example.practise2023.presentation.home.HomeEvent
import com.example.practise2023.ui.theme.Practise2023Theme

@Composable
fun FiltersList(
    modifier: Modifier = Modifier,
    categories: List<String>,
    categoryIndex: Int,
    event: (HomeEvent) -> Unit
) {
    // Creates the list state
    val listState = rememberLazyListState()

    LazyRow(
        state = listState,
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,//.spacedBy(IndicatorSize),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(count = categories.size) {
            categories[it]?.let { category ->
                Button(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    onClick = {
                        event(HomeEvent.UpdateCategory(category, it))
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = if (categoryIndex == it) colorResource(id = R.color.selected_button_filter_bg)
                        else colorResource(id = R.color.button_filter_bg)
                    ),
                    contentPadding = PaddingValues(Dimens.ExtraSmallPadding3),
                    shape = RoundedCornerShape(0.dp)
                ) {
                    Text(
                        text = "#$category",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFiltersList() {
    Practise2023Theme(dynamicColor = false) {
        FiltersList(
            event = {},
            categories = listOf(
                "all",
                "technology",
                "business",
                "science",
                "sports",
                "entertainment",
                "general",
                "health"
            ),
            categoryIndex = 1
        )
    }
}