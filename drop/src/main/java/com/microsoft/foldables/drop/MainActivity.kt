package com.microsoft.foldables.drop

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnDragListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drop_edit_text.setOnDragListener(this)
    }

    override fun onDrag(v: View, event: DragEvent): Boolean {
        return when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                    setDragStartedBackground()
                    true
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                setDragEnteredBackground()
                true
            }
            DragEvent.ACTION_DROP -> {
                handleDrop(event)
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                clearBackgroundColor()
                true
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                setDragStartedBackground()
                true
            }
            else -> false
        }
    }

    private fun handleDrop(event: DragEvent) {
        val dropPermissions = requestDragAndDropPermissions(event)
        val item = event.clipData.getItemAt(0)
        val dragData = item.text.toString()
//        var view = event.localState as? View
        drop_edit_text.setText(dragData)
        dropPermissions?.release()
    }

    private fun clearBackgroundColor() {
        drop_edit_text.background.clearColorFilter()
        drop_edit_text.hint = getString(R.string.default_hint)
        drop_edit_text.elevation = 0f
        drop_edit_text.invalidate()
    }

    private fun setDragStartedBackground() {
        val colorFilter = PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN)
        drop_edit_text.hint = getString(R.string.move_text_to_drop)
        drop_edit_text.background.colorFilter = colorFilter
        drop_edit_text.elevation = 4f
        drop_edit_text.invalidate()
    }

    private fun setDragEnteredBackground() {
        val colorFilter = PorterDuffColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN)
        drop_edit_text.hint = getString(R.string.drop_text_now)
        drop_edit_text.background.colorFilter = colorFilter
        drop_edit_text.elevation = 4f
        drop_edit_text.invalidate()
    }

    private fun isText(mime: String?): Boolean {
        return mime?.startsWith("text/") ?: false
    }
}