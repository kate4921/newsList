package com.example.practise2023.presentation.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practise2023.R
import com.example.practise2023.presentation.Dimens.BigIconSize
import com.example.practise2023.ui.theme.Practise2023Theme

@Composable
fun ScrollToTopButton(
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.padding(bottom = 20.dp, end = 20.dp).size(50.dp).clip(CircleShape),
        onClick =  onClick ,
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = colorResource(id = R.color.top_bar_bg),
            contentColor = Color.Black
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_upward_24),
            contentDescription = null,
            modifier = Modifier.padding(15.dp).size(BigIconSize)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScrollToButton(){
    Practise2023Theme{
        ScrollToTopButton(onClick = {})
    }
}