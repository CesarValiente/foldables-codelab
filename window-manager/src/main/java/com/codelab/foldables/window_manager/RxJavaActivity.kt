package com.codelab.foldables.window_manager

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.window.layout.WindowInfoRepository
import androidx.window.layout.WindowInfoRepository.Companion.windowInfoRepository
import androidx.window.layout.WindowLayoutInfo
import androidx.window.rxjava2.layout.windowLayoutInfoFlowable
import androidx.window.rxjava2.layout.windowLayoutInfoObservable
import com.codelab.foldables.window_manager.databinding.ActivityRxjavaBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

class RxJavaActivity : AppCompatActivity() {

    private val TAG = "CESAR"
    private lateinit var binding: ActivityRxjavaBinding

    private lateinit var windowInfoRepo: WindowInfoRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRxjavaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showUI()

        windowInfoRepo = windowInfoRepository()

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
        val observable = windowInfoRepo.windowLayoutInfoObservable()
        observable.subscribe { value -> showUI(value) }

        //Using Flowable
//        val observable = windowInfoRepo.windowLayoutInfoFlowable()
//        observable.subscribe { value -> showUI(value) }
    }

}
