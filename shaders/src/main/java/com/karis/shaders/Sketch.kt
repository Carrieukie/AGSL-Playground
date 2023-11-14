package com.karis.shaders

import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.DrawResult
import androidx.compose.ui.draw.drawWithCache

@Composable
fun produceDrawLoopCounter(speed: Float = 1f): State<Float> {
    return produceState(0f) {
        while (true) {
            withInfiniteAnimationFrameMillis {
                value = it / 1000f * speed
            }
        }
    }
}

@Composable
fun SimpleSketchWithCache(
    modifier: Modifier = Modifier,
    speed: Float = 1f,
    onDrawWithCache: CacheDrawScope.(time: Float) -> DrawResult
) {
    val time by produceDrawLoopCounter(speed)
    Box(
        modifier = modifier.drawWithCache {
            onDrawWithCache(time)
        }
    )
}


