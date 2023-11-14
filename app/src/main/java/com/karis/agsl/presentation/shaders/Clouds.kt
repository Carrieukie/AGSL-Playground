package com.karis.agsl.presentation.shaders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ShaderBrush
import com.karis.shaders.SimpleSketchWithCache
import com.karis.shaders.cloudyShader

@Composable
fun Clouds(
    modifier: Modifier = Modifier
) {
    val brush = remember { ShaderBrush(cloudyShader) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        SimpleSketchWithCache(
            speed = 1f,
            modifier = modifier.fillMaxSize(0.8f).clip(MaterialTheme.shapes.large)
        ) { time ->
            cloudyShader.setFloatUniform(
                "resolution",
                this.size.width, this.size.height
            )
            cloudyShader.setFloatUniform("time", time)
            onDrawBehind {
                drawRect(brush)
            }
        }
    }
}