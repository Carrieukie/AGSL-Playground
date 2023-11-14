package com.karis.agsl.presentation.shaders.gradientshader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.unit.dp
import com.karis.shaders.GradientShader
import com.karis.shaders.SimpleSketchWithCache

@Composable
fun GradientShaders(modifier: Modifier = Modifier) {
    val brush = remember { ShaderBrush(GradientShader) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleSketchWithCache(
            speed = 1f,
            modifier = modifier
                .fillMaxSize(0.8f)
                .clip(MaterialTheme.shapes.large)
        ) { time ->
            GradientShader.setFloatUniform(
                "resolution",
                this.size.width, this.size.height
            )
            GradientShader.setFloatUniform("time", time)
            onDrawBehind {
                drawRect(brush)
            }
        }

    }
}

