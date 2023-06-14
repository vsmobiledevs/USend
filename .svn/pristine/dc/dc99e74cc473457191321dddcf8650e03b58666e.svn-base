package com.usend.utils

import android.R
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.FileProvider
import com.bumptech.glide.util.Util
import com.usend.BuildConfig
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


fun getPickImageIntent(context: Context): Intent {

    var chooserIntent: Intent? = null
    /*val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "IMG_" + timeStamp + "_"
    val storageDir: File = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    try {
        val image = File.createTempFile(imageFileName, ".jpg", storageDir)
        val uri =
            FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", image)
        currentPhotoPath = image.absolutePath
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(
            intent,
            Util.getResources().getInteger(R.integer.ACTION_FOTO_CAPTURE)
        )
    } catch (e: IOException) {
        e.printStackTrace()
    }*/

    val intentList = ArrayList<Intent>()
    val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, true)
    intentList.add(takePhotoIntent)
    val tempFile = context.getTemporalFile()

//    val fileUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", tempFile)
    val fileUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".file_provider", tempFile)
    takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
    context.addIntentsToList(intentList, takePhotoIntent)
    val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    context.addIntentsToList(intentList, pickIntent)

    if (intentList.size > 0) {
        chooserIntent = Intent.createChooser(intentList.removeAt(intentList.size - 1), "Add Image")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intentList.toTypedArray<Parcelable>())
        chooserIntent.putExtra("camera_file", tempFile.toString())
        chooserIntent.putExtra("camera_file_path", tempFile.absolutePath)
    }

    return chooserIntent!!

}

private fun Context.addIntentsToList(list: ArrayList<Intent>, intent: Intent) {
    val resInfo = this.packageManager.queryIntentActivities(intent, 0)
    for (resolveInfo in resInfo) {
        val packageName = resolveInfo.activityInfo.packageName
        val targetedIntent = Intent(intent)
        targetedIntent.setPackage(packageName)
        list.add(targetedIntent)
    }
}

private fun Context.getTemporalFile(): File {
    return File(this.externalCacheDir, "android_${System.currentTimeMillis()}.jpeg")
}

private fun Context.getTemporalVideoFile(): File {
    return File(this.externalCacheDir, "android_${System.currentTimeMillis()}.mp4")
}

fun checkImagePickerIntentResult(
    context: Context,
    intent: Intent,
    imageReturnedIntent: Intent?,
    callback: (filePath: String?, error: String?) -> Unit
) {
    val isCamera = (imageReturnedIntent == null
            || imageReturnedIntent.data == null
            || imageReturnedIntent.data.toString().contains(intent.getStringExtra("camera_file")!!))

    Log.e("==> isCamera :$isCamera", "")
    if (isCamera) {
        callback(intent.getStringExtra("camera_file_path"), null)
    } else {
        saveImage(context, imageReturnedIntent?.data, callback)
    }
}

private fun saveImage(
    context: Context,
    imageUri: Uri?,
    callback: (filePath: String?, error: String?) -> Unit
) {
    if (imageUri != null && imageUri.authority != null) {
        var errorMessage: String? = null
        var inputStream: InputStream? = null
        var bitmap: Bitmap? = null
        var fos: FileOutputStream? = null
        val tempFile = context.getTemporalFile()
        try {
            inputStream = context.contentResolver.openInputStream(imageUri)
            bitmap = BitmapFactory.decodeStream(inputStream)
            fos = FileOutputStream(tempFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        } catch (e: IOException) {
            e.printStackTrace()
            errorMessage = e.localizedMessage
        } finally {
            if (null != bitmap && !bitmap.isRecycled) {
                bitmap.recycle()
            }
            try {
                inputStream?.close()
                fos?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        callback(errorMessage ?: tempFile.absolutePath, errorMessage)
    }
    return callback(null, "Image not found")
}

fun getPickVideoIntent(context: Context): Intent {

    val pickerIntent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
    pickerIntent.type = "video/*"

    return pickerIntent!!
}


fun checkVideoPickerIntentResult(
    context: Context,
    intent: Intent,
    callback: (path: String?, error: String?) -> Unit
) {
    callback(getPath(context, intent.data!!), null)
}

fun getPath(context: Context?, uri: Uri?): String? {
    val projection =
        arrayOf(MediaStore.Video.Media.DATA)
    val cursor: Cursor = context?.contentResolver!!.query(uri!!, projection, null, null, null)!!
    return if (cursor != null) {
        val column_index: Int = cursor
            .getColumnIndexOrThrow(projection[0])
        cursor.moveToFirst()
        cursor.getString(column_index)
    } else null
}