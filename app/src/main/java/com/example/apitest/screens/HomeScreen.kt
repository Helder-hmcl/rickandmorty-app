package com.example.apitest.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.apitest.screens.elements.GridCard
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object HomeScreen {
    @Suppress("ktlint:standard:function-naming")
    @OptIn(ExperimentalTextApi::class)
    @Composable
    fun HomeScreen(
        context: Context,
        navController: NavController,
        scope: CoroutineScope,
        drawerState: DrawerState,
    ) {
        Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = "Welcome to the Rick And Morty App",
                style = MaterialTheme.typography.titleLarge,
            )
            val text =
                buildAnnotatedString {
                    append("This is a demo application that makes use of the ")
                    withAnnotation("tag", "annotation") {
                        withStyle(
                            style =
                                SpanStyle(
                                    color = Blue,
                                    fontWeight = FontWeight.Bold,
                                ),
                        ) {
                            append("Rick And Morty API")
                        }
                    }
                    append(" in order to study the jetpack compose framework.")
                }
            ClickableText(text = text) {
                text.getStringAnnotations(it, it).firstOrNull()?.tag?.let {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.rickandmortyapi.com"))
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(context, browserIntent, null)
                }
            }
            Divider()
            Column {
                val listOfTitles =
                    listOf(
                        "List all episodes",
                        "List all characters",
                        "Open the Drawer menu",
                        "Test2",
                    )
                val listOfDescription =
                    listOf(
                        "Press to see all episodes",
                        "Press to see all characters",
                        "Press to open the drawer Menu",
                        "Test2",
                    )
                val listOfActions =
                    listOf<() -> Unit>(
                        { navController.navigate("episodes") },
                        { navController.navigate("characters") },
                        {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        {},
                    )
                if (listOfTitles.size != listOfDescription.size || listOfTitles.size != listOfActions.size) {
                    Text("Oops... Something went wrong")
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(listOfTitles.size) {
                            Box(
                                Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .aspectRatio(16 / 10f),
                            ) {
                                GridCard(
                                    title = listOfTitles[it],
                                    description = listOfDescription[it],
                                ) {
                                    listOfActions[it]()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
