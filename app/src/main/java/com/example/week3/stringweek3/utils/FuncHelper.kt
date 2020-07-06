package com.example.week3.stringweek3.utils

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.onScrollToEnd(
    onScrollNearEnd: (Unit) -> Unit
) = addOnScrollListener(object : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!canScrollVertically(1)) {
            onScrollNearEnd(Unit)
        }
    }
})