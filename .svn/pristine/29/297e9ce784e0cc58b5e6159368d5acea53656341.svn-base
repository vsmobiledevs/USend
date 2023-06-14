/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 https://www.spaceotechnologies.com
 *
 * Permissions is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the
 * Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.usend.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar


object SnackBarUtils {
    /************************************ ShowSnackbar with message, KeepItDisplayedOnScreen for few seconds */
    fun showSnakbarTypeOne(rootView: View, mMessage: String) {
        Snackbar.make(rootView, mMessage, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }

    /************************************ ShowSnackbar with message, KeepItDisplayedOnScreen */
    fun showSnakbarTypeTwo(rootView: View, mMessage: String) {
        Snackbar.make(rootView, mMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction("Action", null)
            .show()
    }

    /************************************ ShowSnackbar without message, KeepItDisplayedOnScreen, OnClickOfOk restrat the activity */
    fun Activity.showSnakbarTypeThree(rootView: View) {
        Snackbar
            .make(rootView, "NoInternetConnectivity", Snackbar.LENGTH_INDEFINITE)
            .setAction("TryAgain") {
                val intent = intent
                finish()
                startActivity(intent)
            }
            .setActionTextColor(Color.CYAN)
            .addCallback(object : Snackbar.Callback() {
            })
            .show()
    }

    /************************************ ShowSnackbar with message, KeepItDisplayedOnScreen, OnClickOfOk restrat the activity */
    fun Activity.showSnakbarTypeFour(rootView: View, mMessage: String) {
        Snackbar
            .make(rootView, mMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction("TryAgain") {
                val intent = intent
                finish()
                startActivity(intent)
            }
            .setActionTextColor(Color.CYAN)
            .addCallback(object : Snackbar.Callback() {
            })
            .show()
    }
}