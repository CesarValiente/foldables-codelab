package com.codelab.foldables.window_manager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import androidx.window.layout.WindowMetricsCalculator
import com.codelab.foldables.window_manager.databinding.ActivityFlowBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {

    private val TAG = "CESAR"
    private lateinit var binding: ActivityFlowBinding

    private lateinit var windowInfoTracker: WindowInfoTracker
    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlowBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showUI()

        windowInfoTracker = WindowInfoTracker.getOrCreate(this)

        //If you want to get changes whenever there is an Activity recreation
//        obtainWindowLayoutInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    private fun showUI() {
        binding.text1.text = "Default text 1"
        binding.text2.text = "Default text 2"

        binding.collect.setOnClickListener {
            Log.d(TAG, "---- before calling coroutine ----")
            obtainWindowLayoutInfo()
            obtainWindowMetrics()
            Log.d(TAG, "---- after calling coroutine ----")
        }
    }

    private fun showUI(windowLayoutInfo: WindowLayoutInfo) {
        Log.d(TAG, "---- showUI: ${windowLayoutInfo.displayFeatures} -----")
        binding.text1.text = "Size: ${windowLayoutInfo.displayFeatures.size}"
        binding.text2.text = "WindowLayoutInfo: ${windowLayoutInfo.displayFeatures}"
    }

    private fun obtainWindowLayoutInfo() {
        scope.launch {
            Log.d(TAG, "---- inside coroutine ----")
            windowInfoTracker.windowLayoutInfo(this@FlowActivity).collect { value -> showUI(value) }
            Log.d(TAG, "---- end coroutine ----")
        }
        Log.d(TAG, "------ end obtainWindowLayoutInfo ----")
    }

    private fun obtainWindowMetrics() {
        val windowMetricsCalculator = WindowMetricsCalculator.getOrCreate()
        val currentWindowMetrics = windowMetricsCalculator.computeCurrentWindowMetrics(this)
        val maxWindowMetrics = windowMetricsCalculator.computeMaximumWindowMetrics(this)
        binding.text3.text =
            "CurrentWindowMetrics: ${currentWindowMetrics.bounds} \n " +
                "MaxWindowMetrics: ${maxWindowMetrics.bounds}"
    }
}
