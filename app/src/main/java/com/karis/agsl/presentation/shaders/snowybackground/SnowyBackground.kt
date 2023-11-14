package com.karis.agsl.presentation.shaders.snowybackground

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.tooling.preview.Preview
import com.karis.shaders.SimpleSketchWithCache
import com.karis.shaders.swedenSnowShader

@Preview
@Composable
fun SnowyBackground() {
    val brush = remember { ShaderBrush(swedenSnowShader) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {        SimpleSketchWithCache(
            speed = 1f,
            modifier = Modifier
                .fillMaxSize()
        ) { time ->
            swedenSnowShader.setFloatUniform(
                "resolution",
                this.size.width, this.size.height
            )
            swedenSnowShader.setFloatUniform("time", time * 0.3f)
            onDrawBehind {
                drawRect(brush)
            }
        }
    }
}