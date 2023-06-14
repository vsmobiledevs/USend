package com.usend.utils

import android.content.Context
import android.util.Log
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.spaceo.basecode.CustomProgressDialog
import com.usend.R
import com.usend.fcm.UploadImageModel

/**
 * [dirName] Name of folder at AWS
 * */
class AWSUploadMultipleTask(private val context: Context,
                            private val listItems: ArrayList<UploadImageModel>,
                            private val directory: String,
                            private val uploadListener: UploadListener
) {

    val TAG = "AWSUploadMultipleTask"
    val outputList = ArrayList<UploadImageModel>()
    var hideDialogInSuccess: Boolean = false

    private var mProgressDialog: CustomProgressDialog? = null


    interface UploadListener {
        fun onSuccess(listItems: ArrayList<UploadImageModel>)
        fun onFail()
    }

    /**
     * Start Uploading image.
     * [showDialog] Show dialog while uploading images, default is true
     * [hideDialogInSuccess] Should hide dialog after upload success, default is false
     * */
    fun startUploading(showDialog: Boolean = true, hideDialogInSuccess: Boolean = false) {

        this.hideDialogInSuccess = hideDialogInSuccess
        if (showDialog)

            showProgressDialog(context)

        if (listItems.isEmpty()) {
            handleUploadResult(false)
            return
        }

        val uploadModelPhotoVideo = listItems[0]

        uploadFile(uploadModelPhotoVideo)

    }




    private fun uploadFile(modelPhotoVideo: UploadImageModel) {

        if (modelPhotoVideo.mFile != null) {

            var uploadModel: Any?

            if (!modelPhotoVideo.isUploaded) {
                uploadModel = modelPhotoVideo

                val file = uploadModel.mFile

                TransferNetworkLossHandler.getInstance(context)

                val transferUtility = TransferUtility.builder().context(context)
                    .awsConfiguration(AWSMobileClient.getInstance().configuration)
                    .s3Client(AmazonS3Client(AWSMobileClient.getInstance().awsCredentials, Region.getRegion(Regions.SA_EAST_1)))
                    .build()

                //key - The key in the specified bucket by which to store the new object.
                //You can define folder name and file name in key i.e."s3Folder/s3Key.txt"

                val fileName = uploadModel.mUploadName

                val uploadObserver = transferUtility.upload(
                        "${directory}${fileName}",
                        file,
                        CannedAccessControlList.PublicRead)

                uploadObserver.setTransferListener(object : TransferListener {

                    override fun onProgressChanged(id: Int,
                                                   bytesCurrent: Long,
                                                   bytesTotal: Long) {
                        val percentage = bytesCurrent.toFloat() / bytesTotal.toFloat() * 100
                        Log.e(TAG, "onProgressChanged $percentage")
                    }

                    override fun onStateChanged(id: Int, state: TransferState?) {

                        Log.e(TAG, "onStateChanged $state")
                        if (state == TransferState.COMPLETED) {

                            uploadModel.isUploaded = true

                            //Add To uploaded List
                            outputList.add(uploadModel)

                            //Remove From List
                            listItems.remove(modelPhotoVideo)

                            if (listItems.size == 0) {
                                handleUploadResult(true)
                                //uploadListener.onSuccess(uploadedListItems)
                            } else {
                                uploadFile(listItems[0])
                            }

                        } else if (state == TransferState.FAILED || state == TransferState.CANCELED) {
                            handleUploadResult(false)
                        }
                    }

                    override fun onError(id: Int, ex: Exception?) {
                        ex?.printStackTrace()
                        Log.e(TAG, "onError : ${ex.toString()}")
                        handleUploadResult(false)
                    }
                })
            } else {
                outputList.add(modelPhotoVideo)
                listItems.remove(modelPhotoVideo)

                if (listItems.size == 0) {
                    handleUploadResult(true)
                    //uploadListener.onSuccess(uploadedListItems)
                } else {
                    uploadFile(listItems[0])
                }

            }
        } else {
            outputList.add(modelPhotoVideo)
            listItems.remove(modelPhotoVideo)

            if (listItems.size == 0) {
                handleUploadResult(true)
                //uploadListener.onSuccess(uploadedListItems)
            } else {
                uploadFile(listItems[0])
            }

        }
    }

    /*
    * Handle success and fail response of upload
    * */
    private fun handleUploadResult(isSuccess: Boolean) {
        if (isSuccess) {
            hideProgressDialog()
            uploadListener.onSuccess(outputList)
        } else {
            hideProgressDialog()
            uploadListener.onFail()
        }
    }

    private fun showProgressDialog(mContext: Context,
                                   title: String = "",
                                   message: String = context.getString(R.string.default_loading_message)) {
        mProgressDialog = CustomProgressDialog(mContext)
        mProgressDialog!!.setCanceledOnTouchOutside(false)
        mProgressDialog!!.setCancelable(false)
        if (title.isNotEmpty()) mProgressDialog!!.setTitle(title)
        mProgressDialog!!.setMessage(message)
        mProgressDialog!!.show()
    }

    private fun hideProgressDialog() {
        if (mProgressDialog != null) if (mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    private fun deleteOldPic(imgName : String) {

        Thread(Runnable {

            val s3Client = AmazonS3Client(AWSMobileClient.getInstance().awsCredentials, Region.getRegion(Regions.US_EAST_2))

            s3Client.deleteObject(AMAZONE_BUCKET, imgName)

            Log.e("delete"," => $imgName")

        }).start()
    }

}