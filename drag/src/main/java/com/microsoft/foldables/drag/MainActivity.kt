package com.microsoft.foldables.drag

import android.content.ClipData
import android.content.ClipDescription
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.foldables.drag.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnLongClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.dragTextView.tag = "text_view"
        binding.dragTextView.setOnLongClickListener(this)
    }

    override fun onLongClick(view: View): Boolean {
        return if (view is TextView) {
            val text = ClipData.Item(view.text)
            val mimeType = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val dataToShare = ClipData(view.tag.toString(), mimeType, text)
            val dragShadowBuilder = View.DragShadowBuilder(view)

            //These flags are needed to share between apps
            val flags =
                View.DRAG_FLAG_GLOBAL or View.DRAG_FLAG_GLOBAL_URI_READ

            view.startDragAndDrop(dataToShare, dragShadowBuilder, view, flags)
            true
        } else {
            false
        }
    }
}