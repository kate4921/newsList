package com.example.practise2023.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practise2023.R
import com.example.practise2023.presentation.Dimens
import com.example.practise2023.presentation.Dimens.ExtraSmallPadding3
import com.example.practise2023.presentation.Dimens.MediumPadding1

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f, targetValue = 0.9f, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {

    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(15.dp)
                .shimmerEffect()
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(ExtraSmallPadding3))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.ArticleCardHeight)
                .shimmerEffect()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArticleEffect(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ArticleCardShimmerEffect()
    }
}