/*
 * Developed by Hung Pham.
 * Email: admin@yomemo.com || numerotech@gmail.com.
 * Copyright (c) 2019. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *       https://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.atex.app.utils

import android.content.res.Resources
import androidx.annotation.Px

fun getScreenWidth() = Resources.getSystem().displayMetrics.widthPixels


fun getScreenHeight() = Resources.getSystem().displayMetrics.heightPixels


//    fun setOverLapTop(overlapDp: Int) {
//        val params: CoordinatorLayout.LayoutParams =
//            nested_scroll_layout.getLayoutParams() as (CoordinatorLayout.LayoutParams)
//        val behavior: AppBarLayout.ScrollingViewBehavior = params.behavior as (AppBarLayout.ScrollingViewBehavior)
//        behavior.setOverlayTop(overlapDp.dp)
//    }


/**
 * How tall the navigation bar is.
 *
 * @return 0 if navbar is hardware, or the height of the software bar
 */
val Resources.navBarHeight: Int
    @Px get() {
        val id = getIdentifier("navigation_bar_height", "dimen", "android")
        return when {
            id > 0 -> getDimensionPixelSize(id)
            else -> 0
        }
    }

val Resources.statusBarHeight: Int
    @Px get() {
        val id = getIdentifier("status_bar_height", "dimen", "android")
        return when {
            id > 0 -> getDimensionPixelSize(id)
            else -> 0
        }
    }


