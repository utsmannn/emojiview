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

/**
 * [EmojiViewItem] is a class holding the displayed emoji and its emoji variants
 *
 * @param emoji Used to represent the displayed emoji of the [EmojiViewItem].
 * @param variants Used to represent the corresponding emoji variants of this base emoji.
 */
class EmojiViewItem(val emoji: String, val variants: List<String>)
