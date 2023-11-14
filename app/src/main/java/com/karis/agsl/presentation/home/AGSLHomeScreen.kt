package com.karis.agsl.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.karis.agsl.screen.Screen
import com.karis.agsl.screen.ShaderScreen
import com.karis.style.components.LargeCard


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AGSLHomeScreen(
    modifier: Modifier = Modifier,
    home: String,
    screens: List<ShaderScreen>,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = home
    ) {
        composable(home) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars),
            ) {
                ScreenList(
                    screens = screens,
                    onClick = { screen ->
                        navController.navigate(screen.title)
                    }
                )
            }
        }

        screens.forEach { screen ->
            composable(screen.title) {
                if (screen.isFullScreen) {
                    screen.content()
                } else {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = screen.title,
                                        style = MaterialTheme.typography.headlineMedium,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                },
                            )
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(it)
                                .fillMaxSize()
                        ) {
                            screen.content()
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun ScreenList(
    screens: List<Screen>,
    onClick: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .wrapContentHeight()
            .padding(3.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Spacer(Modifier.height(4.dp))
        }

        items(screens, key = { it.title }) { screen ->
            LargeCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                title = screen.title,
                subtitle = screen.description,
                onClick = { onClick(screen) }
            )
            Spacer(Modifier.height(3.dp))
        }
        item {
            Spacer(Modifier.height(12.dp))
        }
    }
}