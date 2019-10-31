package core

import android.view.View
import android.widget.HorizontalScrollView


fun HorizontalScrollView.onEndScroll(notEnd: () -> Unit, listener: () -> Unit) {
    viewTreeObserver.addOnScrollChangedListener {
        val view = getChildAt(childCount - 1) as View
        val diff = view.right - (width + scrollX)
        if (diff == 0) listener()
        else notEnd()
    }
}