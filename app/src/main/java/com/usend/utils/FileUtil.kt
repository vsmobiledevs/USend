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

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.webkit.MimeTypeMap
import java.io.*
import java.nio.channels.FileChannel
import java.util.*

/**
 * Created on 19th Sept 2019.
 */
object FileUtil {

    private val DIR_ROOT = "rootFolderName"
    private val isCacheStorage = false


    private fun isExternalStorageWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED.equals(state)
    }

    private fun getRootStorageDir(context: Context): File? {
        var storageDir: File? = null
        try {
            if (isExternalStorageWritable() && !isCacheStorage) {
                storageDir = File(Environment.getExternalStorageDirectory(), DIR_ROOT)
            } else {
                storageDir = context.cacheDir
            }
            if (!storageDir!!.exists()) {
                storageDir.mkdirs()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return storageDir
    }

    fun createDir(mContext: Context, folderName: String): Boolean {

        if (TextUtils.isEmpty(folderName)) {
            return false
        }
        val folder = File(getRootStorageDir(mContext), folderName)
        return folder.exists() && folder.isDirectory || folder.mkdirs()
    }

    fun deleteDir(mContext: Context, folderName: String): Boolean {

        if (TextUtils.isEmpty(folderName)) {
            return false
        }

        val folder = File(getRootStorageDir(mContext), folderName)
        val innerFilesList = folder.listFiles()
        if (innerFilesList != null) {
            for (innerFile in innerFilesList) {
                innerFile.delete()
            }
        }
        return folder.exists() && folder.isDirectory && folder.delete()
    }

    private fun moveFile(sourceFilePath: String, destFilePath: String) {
        if (TextUtils.isEmpty(sourceFilePath) || TextUtils.isEmpty(destFilePath)) {
            throw RuntimeException("Both sourceFilePath and destFilePath cannot be null.")
        }
        moveFile(File(sourceFilePath), File(destFilePath))
    }

    private fun moveFile(srcFile: File, destFile: File) {
        val rename = srcFile.renameTo(destFile)
        if (!rename) {
            copyFile(srcFile, destFile)
            deleteFile(srcFile.absolutePath)
        }
    }

    private fun copyFile(srcPath: String, dstPath: String): Boolean {
        if (TextUtils.isEmpty(srcPath) || TextUtils.isEmpty(dstPath)) {
            throw RuntimeException("Both srcPath and dstPath cannot be null.")
        }
        try {
            File(srcPath).copyTo(File(dstPath), true)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun copyFile(src: File, dst: File): Boolean {
        var inputStream: InputStream? = null
        var out: OutputStream? = null
        try {
            inputStream = FileInputStream(src)
            out = FileOutputStream(dst)

            val buf = ByteArray(1024)
            var len: Int
            len = inputStream.read(buf)
            while (len > 0) {
                out.write(buf, 0, len)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        } finally {
            try {
                if (inputStream != null) inputStream.close()
                if (out != null) out.close()
            } catch (e: IOException) {
                return false
            }

        }
        return true
    }


    @Throws(IOException::class)
    fun copyFile(fromFile: FileInputStream, toFile: FileOutputStream) {
        var fromChannel: FileChannel? = null
        var toChannel: FileChannel? = null
        try {
            fromChannel = fromFile.channel
            toChannel = toFile.channel
            fromChannel.transferTo(0, fromChannel.size(), toChannel)
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close()
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close()
                }
            }
        }
    }


    private fun getExtension(fileName: String): String {
        var ext = ""
        try {
            val filenameArray =
                fileName.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            if (filenameArray.size > 0) {
                ext = filenameArray[filenameArray.size - 1].lowercase(Locale.getDefault())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ext
    }

    private fun deleteFile(filePath: String): Boolean {
        try {
            return deleteFile(File(filePath))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }


    private fun deleteFile(targetFile: File): Boolean {
        var deleted = true
        try {
            deleted = targetFile.delete()
            if (!deleted && !targetFile.isFile) {
            } else {
                deleted = true
            }
        } catch (e: Exception) {
            deleted = false
            e.printStackTrace()
        }

        return deleted
    }

    fun getFileSize(path: String): Long {
        if (TextUtils.isEmpty(path)) {
            return -1
        }

        val file = File(path)
        return (if (file.exists() && file.isFile) file.length() else -1).toLong()
    }

    private fun getMimeType(file: File): String? {

        val extension = getExtension(file.name)

        return if (extension.isNotEmpty()) {
            MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension.substring(1))
        } else "application/octet-stream"

    }

    private fun getMimeType(filePath: String): String {
        return getMimeType(File(filePath).toString())
    }

    fun getFileName(file: File): String? {
        return getFileName(file.absolutePath)
    }

    fun getFileName(filePath: String): String? {
        if (TextUtils.isEmpty(filePath)) {
            return filePath
        }
        val filePosi = filePath.lastIndexOf(File.separator)
        return if (filePosi == -1) filePath else filePath.substring(filePosi + 1)
    }
}