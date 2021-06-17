package com.codelab.foldables.window_manager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.window.WindowInfoRepo
import androidx.window.WindowLayoutInfo
import androidx.window.WindowManager
import androidx.window.rxjava2.windowLayoutInfoFlowable
import com.codelab.foldables.window_manager.databinding.ActivityRxjavaBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

class RxJavaActivity : AppCompatActivity() {

    private val TAG = "CESAR"
    private lateinit var binding: ActivityRxjavaBinding

    private lateinit var windowInfoRepo: WindowInfoRepo
    private lateinit var wm: WindowManager
    private val scope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxjavaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showUI()

        windowInfoRepo = WindowInfoRepo.create(this)

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
            obtainWindowLayoutInfo()
        }
    }

    private fun showUI(windowLayoutInfo: WindowLayoutInfo) {
        Log.d(TAG, "---- showUI: ${windowLayoutInfo.displayFeatures} -----")
        binding.text1.text = "Size: ${windowLayoutInfo.displayFeatures.size}"
        binding.text2.text = "WindowLayoutInfo: ${windowLayoutInfo.displayFeatures}"
    }

    private fun obtainWindowLayoutInfo() {
        //Using Observables
//        val observable = windowInfoRepo.windowLayoutInfoObservable()
//        observable.subscribe { value -> showUI(value) }

        //Using Flowable
        val observable = windowInfoRepo.windowLayoutInfoFlowable()
        observable.subscribe { value -> showUI(value) }
    }

}
