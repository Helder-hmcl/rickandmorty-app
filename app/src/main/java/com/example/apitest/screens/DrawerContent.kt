package com.example.apitest.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apitest.viewModel.BottomIcons
import com.example.apitest.viewModel.BottomNavigationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

object DrawerContent {
    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun DrawerContent(
        navController: NavController,
        drawerState: DrawerState,
        scope: CoroutineScope,
        bottomNavigationViewModel: BottomNavigationViewModel,
    ) {
        ModalDrawerSheet(
            modifier =
                Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 10.dp, end = 10.dp),
            drawerContainerColor = MaterialTheme.colorScheme.background,
            drawerContentColor = MaterialTheme.colorScheme.primary,
        ) {
            Column(
                Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth(),
            ) {
                Column(Modifier.padding(10.dp)) {
                    Image(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "profilePicture",
                        contentScale = ContentScale.Crop,
                        modifier =
                            Modifier
                                .width(90.dp)
                                .height(90.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.inversePrimary),
                    )
                    Text(
                        text = "Hélder Lourenço",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.inversePrimary,
                    )
                }
            }
            Divider()
            Column(
                Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                DrawerItem(
                    "Home",
                    Icons.Rounded.Home,
                    drawerState,
                    scope,
                ) {
                    bottomNavigationViewModel.setBottomMenuSelected(BottomIcons.HOME)
                    navController.navigate("home")
                }
                DrawerItem(
                    "Episodes",
                    Icons.Rounded.DateRange,
                    drawerState,
                    scope,
                ) {
                    bottomNavigationViewModel.setBottomMenuSelected(BottomIcons.EPISODES)
                    navController.navigate("episodes")
                }
                DrawerItem(
                    "Characters",
                    Icons.Rounded.Face,
                    drawerState,
                    scope,
                ) {
                    bottomNavigationViewModel.setBottomMenuSelected(BottomIcons.CHARACTERS)
                    navController.navigate("characters")
                }
                DrawerItem("Favorites", Icons.Filled.FavoriteBorder, drawerState, scope) {}
            }
            Divider()
            Column(Modifier.padding(10.dp)) {
                DrawerItem(
                    text = "Settings",
                    imageVector = Icons.Rounded.Settings,
                    drawerState,
                    scope,
                ) {}
            }
        }
    }

    @Suppress("ktlint:standard:function-naming")
    @Composable
    fun DrawerItem(
        text: String,
        imageVector: ImageVector,
        drawerState: DrawerState,
        scope: CoroutineScope,
        onClick: () -> Unit,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier =
                Modifier
                    .clickable {
                        scope.launch {
                            drawerState.close()
                        }
                        onClick()
                    }
                    .fillMaxWidth(),
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = text,
                Modifier
                    .align(Alignment.CenterVertically)
                    .width(25.dp)
                    .height(25.dp),
            )
            Text(text = text, style = MaterialTheme.typography.titleLarge)
        }
    }
}
