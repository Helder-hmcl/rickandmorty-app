package com.example.apitest.screens.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalGridCard(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Card(onClick = { onClick() }, modifier = Modifier.wrapContentHeight()) {
        Column(Modifier.padding(10.dp)) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = title,
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = description,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridCard(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = title,
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = description,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterCard(name: String, image: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(4 / 6f)
            .shadow(4.dp) // Add a drop shadow with elevation
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .aspectRatio(1f) // Set the aspect ratio to maintain the same image size
        ) {
            GlideImage(
                model = image,
                contentDescription = name,
                modifier = Modifier.fillMaxSize() // Fill the available space in the Box
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

