package com.example.practise2023.presentation.common


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.practise2023.R
import com.example.practise2023.domain.model.Article
import com.example.practise2023.domain.model.Source
import com.example.practise2023.presentation.Dimens
import com.example.practise2023.ui.theme.Practise2023Theme

//@Preview(showBackground = true)
@Composable
fun PodcastCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: (() -> Unit)? = null
) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable { onClick?.invoke() },
        shape = RoundedCornerShape(15.dp),
    ) {
        Column() {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    //.clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.FillBounds,
                    alpha = 0.5f,
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = "article image"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.padding(
                            start = 15.dp,
                            top = 10.dp,
                            bottom = 10.dp,
                            end = 10.dp
                        )
                    ) {
                        Text(
                            text = article.title,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 24.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = article.source.name,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_time),
                                contentDescription = null,
                                modifier = Modifier.size(Dimens.SmallIconSize),
                                tint = Color.White
                            )
                            Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding))
                            Text(
                                text = article.publishedAt,
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White
                            )
                        }
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_play_arrow_24),//painterResource(id = R.drawable.baseline_pause_24),
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(end = 25.dp, start = 5.dp)
                            .size(40.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PodcastCardPreview() {

    Practise2023Theme(dynamicColor = false) {
        PodcastCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "", name = "BBC"),
                title = "Her train broke down. Her phone died. And then she met her Saver in a",
                url = "",
                urlToImage = "https://ichef.bbci.co.uk/live-experience/cps/624/cpsprodpb/11787/production/_124395517_bbcbreakingnewsgraphic.jpg"
            )
        )
    }
}