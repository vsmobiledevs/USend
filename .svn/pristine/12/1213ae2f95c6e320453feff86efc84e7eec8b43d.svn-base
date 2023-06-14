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

import java.nio.charset.Charset
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


object SecurityUtils {

    val TOKEN = "token"
    val NONCE = "nonce"
    val TIMESTAMP = "timestamp"


    val USASCII = "US-ASCII"
    val HMACSHA256 = "HmacSHA256"
    val RANDOMNONCE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    val apiPrivateKey = "Hg1dhgKS1A1MT0AI5Pf5ydf7r6vlwgjUfa9s"
    val apiSecretKey = "lc5dhgKS1A1MT5AI8SAMf5yds7s6vlwgjUfa9s"

    val timestamp = DateTimeUtil.getCurrentTimeStamp()
    val nonce: String = generateNonce()
    val token = getTokenForURl("$NONCE=$nonce&$TIMESTAMP=$timestamp|$apiSecretKey")


    //nonce
    private fun generateNonce(): String {
        var nonce = ""
        val rnd = Random()
        val randomLetters = RANDOMNONCE
        for (n in 0..5) {
            nonce += randomLetters.get(rnd.nextInt(randomLetters.length))
        }
        return nonce
    }


    private fun getTokenForURl(Data: String): String {
        try {
            val asciiCs = Charset.forName(USASCII)
            val sha256_HMAC = Mac.getInstance(HMACSHA256)
            val secret_key = SecretKeySpec(asciiCs.encode(apiPrivateKey).array(), HMACSHA256)
            sha256_HMAC.init(secret_key)
            val mac_data = sha256_HMAC.doFinal(asciiCs.encode(Data).array())
            var result = ""
            mac_data.forEach {

                val i = it.toInt()
                result += ((i and 0xff) + 0x100).toString(16).substring(1)
            }
            return result

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }


}
