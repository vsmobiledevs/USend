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
import android.util.Log
import com.usend.BuildConfig

object JLog {


    private val LOG = BuildConfig.DEBUG
    fun i(tag: String, string: String) {
        if (LOG) {
            Log.i(tag, string)
        }
    }

    fun e(tag: String, string: String) {
        if (LOG) {
            Log.e(tag, string)
        }
    }

    fun d(tag: String, string: String) {
        if (LOG) {
            Log.d(tag, string)
        }
    }

    fun v(tag: String, string: String) {
        if (LOG) {
            Log.v(tag, string)
        }
    }

    fun w(tag: String, string: String) {
        if (LOG) {
            Log.w(tag, string)
        }
    }

    fun isLog(): Boolean {
        return LOG
    }

}