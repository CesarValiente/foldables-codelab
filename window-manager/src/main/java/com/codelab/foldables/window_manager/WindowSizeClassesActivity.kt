package com.codelab.foldables.window_manager

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.layout.WindowMetricsCalculator
import com.codelab.foldables.window_manager.databinding.ActivityWindowSizeClassesBinding

class WindowSizeClassesActivity : Activity() {
    private lateinit var binding: ActivityWindowSizeClassesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWindowSizeClassesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val (width, height) = computeWindowSizeClasses()
        binding.width.text = "Width: $width"
        binding.height.text = "Height: $height"
    }

    private fun computeWindowSizeClasses(): Pair<WindowWidthSizeClass, WindowHeightSizeClass> {
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)

        //Calculate current window width and height taking into account display density
        // so we have the correct value in dps
        val widthDp = metrics.bounds.width() /
                resources.displayMetrics.density

        val heightDp = metrics.bounds.height() /
                resources.displayMetrics.density

        //We get the WindowSize class values regarding the width and height our window has
        val windowSizeClass = WindowSizeClass.compute(widthDp, heightDp)

        //Return the width and height WindowSize class value
        return Pair(windowSizeClass.windowWidthSizeClass, windowSizeClass.windowHeightSizeClass)
    }
}