package com.example.apitest.screens.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalGridCard(title: String, description: String, onClick: () -> Unit) {
    Card(onClick = { onClick() }, modifier = Modifier.wrapContentHeight()) {
        Column(Modifier.padding(10.dp)) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = title
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = description
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridCard(title: String, description: String, onClick: () -> Unit) {
    Card(onClick = { onClick() }, modifier = Modifier.wrapContentHeight().fillMaxWidth().fillMaxHeight()) {
        Column(Modifier.padding(10.dp)) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = title
            )
            Text(
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = description,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
