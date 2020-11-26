package com.microsoft.foldables.window_manager

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.util.Consumer
import androidx.core.view.doOnLayout
import androidx.window.DeviceState
import androidx.window.WindowLayoutInfo
import androidx.window.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private lateinit var wm: WindowManager
    private val deviceStateChangeCallback = DeviceStateChangeCallback()
    private val layoutStateChangeCallback = LayoutStateChangeCallback()

    private fun runOnUiThreadExecutor(): Executor {
        val handler = Handler(Looper.getMainLooper())
        return Executor() {
            handler.post(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wm = WindowManager(this, null)
        wm.registerDeviceStateChangeCallback(
            runOnUiThreadExecutor(),
            deviceStateChangeCallback
        )

        window.decorView.doOnLayout {
            printDeviceStateChanges(wm.deviceState)
            printLayoutStateChange(wm.windowLayoutInfo)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wm.unregisterDeviceStateChangeCallback(deviceStateChangeCallback)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        wm.registerLayoutChangeCallback(
            runOnUiThreadExecutor(),
            layoutStateChangeCallback
        )
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        wm.unregisterLayoutChangeCallback(layoutStateChangeCallback)
    }

    private fun printDeviceStateChanges(newDeviceState: DeviceState) {
        device_state_change_text.text = "Posture: ${newDeviceState.toString()}"
    }

    private fun printLayoutStateChange(newLayoutInfo: WindowLayoutInfo) {
        layout_change_text.text = newLayoutInfo.toString()
        if (newLayoutInfo.displayFeatures.size > 0) {
            configuration_changed.text = "Spanned across displays"
            alignViewToDeviceFeatureBoundaries(newLayoutInfo)
        } else {
            configuration_changed.text = "One logic/physical display - unspanned"
        }
    }

    private fun alignViewToDeviceFeatureBoundaries(newLayoutInfo: WindowLayoutInfo) {
        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraint_layout)
        val set = ConstraintSet()
        set.clone(constraintLayout)

        val statusBarHeight = calculateStatusBarHeight()
        val toolBarHeight = calculateToolbarHeight()

        //We get the display feature bounds.
        val rect = newLayoutInfo.displayFeatures[0].bounds

        //Set the view to match the height and width of the device feature
        set.constrainHeight(
            R.id.device_feature,
            rect.bottom - rect.top - statusBarHeight - toolBarHeight
        )
        set.constrainWidth(R.id.device_feature, rect.right - rect.left)

        set.connect(
            R.id.device_feature, ConstraintSet.START,
            ConstraintSet.PARENT_ID, ConstraintSet.START, 0
        )
        set.connect(
            R.id.device_feature, ConstraintSet.TOP,
            ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0
        )

        if (rect.top == 0) {
            set.setMargin(R.id.device_feature, ConstraintSet.START, rect.left)
            set.connect(
                R.id.layout_change_text, ConstraintSet.END,
                R.id.device_feature, ConstraintSet.START, 0
            )
        } else {
            set.setMargin(R.id.device_feature, ConstraintSet.TOP, rect.top - statusBarHeight - toolBarHeight)
            set.connect(
                R.id.layout_change_text, ConstraintSet.TOP,
                R.id.device_feature, ConstraintSet.BOTTOM, 0
            )
        }

        //Set the view to visible and apply constraints
        set.setVisibility(R.id.device_feature, View.VISIBLE)
        set.applyTo(constraintLayout)
    }

    private fun calculateToolbarHeight(): Int {
        val typedValue = TypedValue()
        return if (theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
        } else {
            0
        }
    }

    private fun calculateStatusBarHeight(): Int {
        val rect = Rect()
        window.decorView.getWindowVisibleDisplayFrame(rect)
        val statusBarHeight: Int = rect.top
        val contentViewTop: Int = window.findViewById<View>(Window.ID_ANDROID_CONTENT).top
        return contentViewTop - statusBarHeight
    }

    inner class DeviceStateChangeCallback : Consumer<DeviceState> {
        override fun accept(newDeviceState: DeviceState) {
            printDeviceStateChanges(newDeviceState)
        }
    }

    inner class LayoutStateChangeCallback : Consumer<WindowLayoutInfo> {
        override fun accept(newLayoutInfo: WindowLayoutInfo) {
            printLayoutStateChange(newLayoutInfo)
        }
    }
}