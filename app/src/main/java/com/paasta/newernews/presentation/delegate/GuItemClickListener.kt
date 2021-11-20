package com.paasta.newernews.presentation.delegate

import android.view.View
import com.paasta.newernews.domain.model.Gus

interface GuItemClickListener {
    fun onItemClick(view: View, item: Gus)
}