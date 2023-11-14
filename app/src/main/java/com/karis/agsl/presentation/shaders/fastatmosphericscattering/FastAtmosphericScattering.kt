package com.karis.agsl.presentation.shaders.fastatmosphericscattering

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.karis.shaders.LightScatteringShader
import com.karis.shaders.SimpleSketchWithCache

@Composable
fun FastAtmosphericScattering(modifier: Modifier = Modifier) {
    val brush = remember { ShaderBrush(LightScatteringShader) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleSketchWithCache(
            speed = 1f,
            modifier = modifier
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        LightScatteringShader.setFloatUniform(
                            "iMouse",
                            dragAmount.y, dragAmount.x
                        )
                    }
                }
                .fillMaxSize(0.8f)
                .clip(MaterialTheme.shapes.large)
        ) { time ->
            LightScatteringShader.setFloatUniform(
                "resolution",
                this.size.width, this.size.height
            )
            LightScatteringShader.setFloatUniform("time", time)
            onDrawBehind {
                drawRect(brush)
            }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(.8f)
                .padding(top = 16.dp),
            text = "GradientShader"
        )

    }
}