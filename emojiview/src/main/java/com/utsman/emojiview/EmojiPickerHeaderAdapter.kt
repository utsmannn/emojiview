/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.utsman.emojiview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/** RecyclerView adapter for emoji header. */
internal class EmojiPickerHeaderAdapter(
    context: Context,
    private val emojiPickerItems: EmojiPickerItems,
    private val onHeaderIconClicked: (Int) -> Unit,
) : Adapter<ViewHolder>() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    var selectedGroupIndex: Int = 0
        set(value) {
            if (value == field) return
            notifyItemChanged(field)
            notifyItemChanged(value)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return object :
            ViewHolder(
                layoutInflater.inflate(
                    R.layout.header_icon_holder,
                    parent,
                    /* attachToRoot = */ false
                )
            ) {}
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val isItemSelected = i == selectedGroupIndex
        val headerIcon =
            ViewCompat.requireViewById<ImageView>(
                    viewHolder.itemView,
                    R.id.emoji_picker_header_icon
                )
                .apply {
                    setImageDrawable(context.getDrawable(emojiPickerItems.getHeaderIconId(i)))
                    isSelected = isItemSelected
                    contentDescription = emojiPickerItems.getHeaderIconDescription(i)
                }
        viewHolder.itemView.setOnClickListener {
            onHeaderIconClicked(i)
            selectedGroupIndex = i
        }
        if (isItemSelected) {
            headerIcon.post {
                headerIcon.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_HOVER_ENTER)
            }
        }

        ViewCompat.requireViewById<View>(viewHolder.itemView, R.id.emoji_picker_header_underline)
            .apply {
                visibility = if (isItemSelected) View.VISIBLE else View.GONE
                isSelected = isItemSelected
            }

        ViewCompat.requireViewById<ImageView>(viewHolder.itemView, R.id.emoji_picker_header_icon)
            .apply {
                if (isItemSelected) {
                    setColorFilter(Color.parseColor("#5F5E5F"), PorterDuff.Mode.SRC_ATOP)
                } else {
                    setColorFilter(Color.parseColor("#A1A5AC"), PorterDuff.Mode.SRC_ATOP)
                }
            }
    }

    override fun getItemCount(): Int {
        return emojiPickerItems.numGroups
    }
}
