package com.codelab.foldables.window_manager.embedding

import android.content.Context
import androidx.startup.Initializer
import androidx.window.core.ExperimentalWindowApi
import androidx.window.embedding.SplitController
import com.codelab.foldables.window_manager.R

/**
 * Initializes SplitController with a set of statically defined rules.
 */
@OptIn(ExperimentalWindowApi::class)
class ExampleWindowInitializer : Initializer<SplitController> {
    override fun create(context: Context): SplitController {
        SplitController.initialize(context, R.xml.main_split_config)
        return SplitController.getInstance()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}