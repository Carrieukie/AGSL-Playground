package com.karis.agsl.presentation.shaders.textgradientshader

import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import com.karis.shaders.produceDrawLoopCounter

@Composable
fun TextGradientShader() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        val time by produceDrawLoopCounter(1.0f)

        Text(
            modifier = Modifier
                .align(Alignment.Center)
                .onSizeChanged {
                    shader.setFloatUniform(
                        "size",
                        it.width.toFloat(),
                        it.height.toFloat()
                    )
                }
                .graphicsLayer {
                    clip = true
                    shader.setFloatUniform("time", time)
                    renderEffect = RenderEffect
                        .createRuntimeShaderEffect(shader, "composable")
                        .asComposeRenderEffect()
                },
            text = "TextGradientShader",
            style = MaterialTheme.typography.displayMedium
        )
    }
}

val shader = RuntimeShader("""
    uniform shader composable;
    uniform float2 size;
    uniform float time;

    vec4 main(vec2 fragCoord) {
        // Normalized pixel coordinates (from 0 to 1)
        vec2 uv = fragCoord/size.xy;
    
        // Time varying pixel color
        vec3 col = 0.8 + 0.2 * sin(time*2.0+uv.xyx*2.0+vec3(1,2,4));
            
        // Output to screen
        return vec4(col,composable.eval(fragCoord).a);
    }
   
""".trimIndent())