package com.karis.agsl.presentation.shaders.skiasampleshader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ShaderBrush
import com.karis.shaders.NoodleZoomShader
import com.karis.shaders.SimpleSketchWithCache

@Composable
fun SkiaSampleShader(modifier: Modifier = Modifier) {
    val brush = remember { ShaderBrush(NoodleZoomShader) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {        SimpleSketchWithCache(
            speed = 1f,
            modifier = modifier.fillMaxSize(0.8f).clip(MaterialTheme.shapes.large)
        ) { time ->
            NoodleZoomShader.setFloatUniform(
                "resolution",
                this.size.width, this.size.height
            )
            NoodleZoomShader.setFloatUniform("time", time)
            onDrawBehind {
                drawRect(brush)
            }
        }
    }
}