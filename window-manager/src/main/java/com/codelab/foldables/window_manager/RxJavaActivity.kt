package com.codelab.foldables.window_manager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import androidx.window.rxjava2.layout.windowLayoutInfoObservable
import com.codelab.foldables.window_manager.databinding.ActivityRxjavaBinding

class RxJavaActivity : AppCompatActivity() {

    private val TAG = "CESAR"
    private lateinit var binding: ActivityRxjavaBinding

    private lateinit var windowInfoTracker: WindowInfoTracker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxjavaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showUI()

        windowInfoTracker = WindowInfoTracker.getOrCreate(this)

        //If you want to get changes whenever there is an Activity recreation
        obtainWindowLayoutInfo()
    }

    private fun showUI() {
        binding.text1.text = "Default text 1"
        binding.text2.text = "Default text 2"

        binding.collect.setOnClickListener {
            obtainWindowLayoutInfo()
        }
    }

    private fun showUI(windowLayoutInfo: WindowLayoutInfo) {
        binding.text1.text = "Size: ${windowLayoutInfo.displayFeatures.size}"
        binding.text2.text = "WindowLayoutInfo: ${windowLayoutInfo.displayFeatures}"
    }

    private fun obtainWindowLayoutInfo() {
        //Using Observables
        val observable = windowInfoTracker.windowLayoutInfoObservable(this)
        observable.subscribe { value -> showUI(value) }

        //Using Flowable
//        val observable = windowInfoRepo.windowLayoutInfoFlowable()
//        observable.subscribe { value -> showUI(value) }
    }

}
