package com.csci448.fpmobileapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.csci448.fpmobileapp.R

@Composable
fun LogoImage(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.studysauruslogo),
        contentDescription = "Logo Drawing",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(size)
    )
}