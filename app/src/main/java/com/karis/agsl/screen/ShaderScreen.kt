package com.karis.agsl.screen

import androidx.compose.runtime.Composable

data class ShaderScreen(
    override val title: String,
    override val description: String? = null,
    override val isFullScreen: Boolean = false,
    val content: @Composable () -> Unit,
) : Screen

