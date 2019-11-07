package com.matthewcannefax.learnsightwords.word

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matthewcannefax.learnsightwords.ITEM_HEIGHT

class AutoFitGridLayoutManager(context: Context, columnWidth: Int, itemCount: Int, screenHeight: Int, screenWidth: Int, itemHeightPX: Int) : GridLayoutManager(context, 1) {

    private var columnWidth: Int = 0
    private var columnWidthChanged = true
    private var mItemCount: Int = 0
    private var mContext: Context = context
    private var mScreenHeight = screenHeight
    private var mScreenWidth = screenWidth
    private var mItemHeightPX = itemHeightPX

    init {
        setColumnWidth(columnWidth)
        mItemCount = itemCount
    }

    fun setColumnWidth(newColumnWidth: Int) {
        if (newColumnWidth > 0 && newColumnWidth != columnWidth) {
            columnWidth = newColumnWidth
            columnWidthChanged = true
        }
    }



    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        if (columnWidthChanged && columnWidth > 0) {
            val totalSpace: Int
            if (orientation == RecyclerView.VERTICAL) {
//                totalSpace = height - paddingTop - paddingBottom
                totalSpace = mScreenHeight
                val itemsPerColumn = totalSpace / mItemHeightPX
                val spanCount = mItemCount / (itemsPerColumn -1)
                setSpanCount(spanCount)
            } else {
//                totalSpace = width - paddingRight - paddingLeft
                totalSpace = mScreenWidth
                val itemsPerColumn = totalSpace / mItemHeightPX
                val spanCount = mItemCount / (itemsPerColumn - 2)
                setSpanCount(spanCount)
            }



            columnWidthChanged = false
        }

        super.onLayoutChildren(recycler, state)
    }
}
