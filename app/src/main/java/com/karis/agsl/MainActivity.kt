package com.karis.agsl

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import com.karis.agsl.presentation.home.AGSLHomeScreen
import com.karis.agsl.presentation.shaders.Clouds
import com.karis.agsl.presentation.shaders.fastatmosphericscattering.FastAtmosphericScattering
import com.karis.agsl.presentation.shaders.glowingbutton.GlowingButtonExample
import com.karis.agsl.presentation.shaders.gradientshader.GradientShaders
import com.karis.agsl.presentation.shaders.skiasampleshader.SkiaSampleShader
import com.karis.agsl.presentation.shaders.snowybackground.SnowyBackground
import com.karis.agsl.presentation.shaders.textgradientshader.TextGradientShader
import com.karis.agsl.presentation.shaders.warpspeedshader.WarpSpeedShader
import com.karis.agsl.screen.ShaderScreen
import com.karis.style.theme.AGSLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        drawContentBehindSystemBars()
        setContent {
            AGSLTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                        },
                    color = MaterialTheme.colorScheme.background
                ) {
                    AGSLHomeScreen(modifier = Modifier, home = "AGSL", screens = ShadersScreens)
                }
                ChangeSystemBarsTheme(!isSystemInDarkTheme())
            }
        }
    }

    private fun drawContentBehindSystemBars() {
        window.attributes.layoutInDisplayCutoutMode =
            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    @Composable
    private fun ChangeSystemBarsTheme(lightTheme: Boolean) {
        val barColor = MaterialTheme.colorScheme.background.toArgb()
        LaunchedEffect(lightTheme) {
            if (lightTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.light(
                        barColor, barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.light(
                        barColor, barColor,
                    ),
                )
            } else {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                    navigationBarStyle = SystemBarStyle.dark(
                        barColor,
                    ),
                )
            }
        }
    }
}

val ShadersScreens = listOf(
    ShaderScreen(title = "Text Gradient Shader") {
        TextGradientShader()
    },
    ShaderScreen(title = "Gradient Shader") {
        GradientShaders()
    },
    ShaderScreen(title = "Skia Sample Shader") {
        SkiaSampleShader()
    },
    ShaderScreen(title = "Warp Speed Shader") {
        WarpSpeedShader()
    },
    ShaderScreen(title = "Light Scattering Shader") {
        FastAtmosphericScattering()
    },
    ShaderScreen("Glowing Button", isFullScreen = true) {
        GlowingButtonExample()
    },
    ShaderScreen("Snowy Background", isFullScreen = true) {
        SnowyBackground()
    },
    ShaderScreen("Clouds") {
        Clouds()
    }
)


