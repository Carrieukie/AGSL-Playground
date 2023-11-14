package com.karis.agsl.presentation.shaders.glowingbutton

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import com.karis.shaders.glowingButtonShader

@Composable
fun GlowingButtonExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .windowInsetsPadding(WindowInsets.statusBars),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var radius by remember { mutableStateOf(0.0f) }
        var intensity by remember { mutableStateOf(0.7f) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.Black),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GlowingButton(
                glowColor = Color(0xFF32de84),
                text = "🕶",
                textColor = Color(0xFF000814),
                radius = radius,
                intensity = intensity
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f), verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Radius",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Slider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(20.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF32de84),
                        activeTrackColor = Color(0xFF32de84)
                    ),
                    value = radius,
                    onValueChange = { radius = it }
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Intensity",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
                Slider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(20.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0xFF32de84),
                        activeTrackColor = Color(0xFF32de84)
                    ),
                    value = intensity,
                    valueRange = 0f..2f,
                    onValueChange = { intensity = it }
                )
            }
        }
    }
}

@Composable
fun GlowingButton(
    glowColor: Color,
    text: String,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.Black,
    radius: Float = 0.3f,
    intensity: Float = 0.5f
) {
        val shader = remember { RuntimeShader(glowingButtonShader) }
        var width by remember { mutableStateOf(0f) }
        var height by remember { mutableStateOf(0f) }

        val interactionSource = remember { MutableInteractionSource() }

        shader.setColorUniform(
            "glowColor",
            glowColor.toArgb()
        )

        shader.setFloatUniform(
            "glowRadius",
            radius
        )

        shader.setFloatUniform(
            "glowIntensity",
            intensity
        )
        Box(
            modifier = Modifier
                .graphicsLayer {
                    with(shader) {
                        setFloatUniform(
                            "size",
                            width,
                            height
                        )
                        setFloatUniform(
                            "radius",
                            30.dp.toPx()
                        )
                    }
                    renderEffect = RenderEffect
                        .createRuntimeShaderEffect(shader, "button")
                        .asComposeRenderEffect()

                }
                .width(200.dp)
                .height(80.dp)
                .background(color = backgroundColor, shape = RoundedCornerShape(30.dp))
                .clip(RoundedCornerShape(30.dp))
                .clickable(interactionSource = interactionSource, indication = null) {}
                .onSizeChanged { size ->
                    width = size.width.toFloat()
                    height = size.height.toFloat()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text, style = MaterialTheme.typography.labelLarge, color = textColor)
        }
    }

