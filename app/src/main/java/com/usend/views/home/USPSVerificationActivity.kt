package com.usend.views.home

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import com.usend.R
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivityUSPSVerificationBinding
import com.usend.extensions.loadImageUrl
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.fcm.UploadImageModel
import com.usend.utils.*
import com.usend.utils.CommonUtils.showAffirmationDialog
import com.usend.viewmodels.USPSVerificationViewModel
import kotlinx.android.synthetic.main.app_toolbar.*
import java.io.*
import kotlin.reflect.KClass


class USPSVerificationActivity(
    override val modelClass: KClass<USPSVerificationViewModel> = USPSVerificationViewModel::class,
    override val layoutId: Int = R.layout.activity_u_s_p_s_verification
) : ViewModelActivity<ActivityUSPSVerificationBinding, USPSVerificationViewModel>() {

    override fun initView() {
        super.initView()

        initToolbar(toolbar = toolbar, title = "")
    }

    override fun initControls() {
        super.initControls()

        viewModel.listUpload.value = arrayListOf()
        viewModel.listUpload.value?.add(UploadImageModel())
        viewModel.listUpload.value?.add(UploadImageModel())
        viewModel.listUpload.value?.add(UploadImageModel())

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.addPlugin(AWSS3StoragePlugin())
            Amplify.configure(applicationContext)

            JLog.e("MyAmplifyApp", "Initialized Amplify")
        } catch (error: AmplifyException) {
            JLog.e("MyAmplifyApp", "Could not initialize Amplify$error")
        }

        binding.imgPassport.setOnClickListener {

            viewModel.requestPermission(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ) { isGranted ->
                if (isGranted) {
                    val intent = getPickImageIntent(this)
                    startActivityForResult(intent) { data ->
                        checkImagePickerIntentResult(this, intent, data) { filePath, _ ->

                            if (filePath != null) {

                                viewModel.passportImage.value = DateTimeUtil.getCurrentTimeStamp() + JPG
                                binding.imgPassport.loadImageUrl(
                                    this,
                                    filePath,
                                    cornerRadius = CommonUtils.dpToPx(this, 10)
                                ) {

                                }

                                viewModel.listUpload.value!![0] = UploadImageModel(
                                    mTitle = "Passport",
                                    mFile = File(
                                        filePath
                                    ),
                                    mUploadName = viewModel.passportImage.value!!,
                                    isUploaded = false
                                )
                            }
                        }
                    }
                }
            }
        }

        binding.imgDrivingLicense.setOnClickListener {

            viewModel.requestPermission(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ) { isGranted ->
                if (isGranted) {
                    val intent = getPickImageIntent(this)
                    startActivityForResult(intent) { data ->
                        checkImagePickerIntentResult(this, intent, data) { filePath, _ ->

                            if (filePath != null) {

                                viewModel.drivingLicenseImage.value = DateTimeUtil.getCurrentTimeStamp() + JPG
                                binding.imgDrivingLicense.loadImageUrl(
                                    this,
                                    filePath,
                                    cornerRadius = CommonUtils.dpToPx(this, 10)
                                ) {

                                }
                                viewModel.listUpload.value!![1] = UploadImageModel(
                                    mTitle = "Driving License",
                                    mFile = File(
                                        filePath
                                    ),
                                    mUploadName = viewModel.drivingLicenseImage.value!!,
                                    isUploaded = false
                                )
                            }
                        }
                    }
                }
            }
        }

        binding.img1583Form.setOnClickListener {

            viewModel.requestPermission(
                this,
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ) { isGranted ->
                if (isGranted) {

                    val intent = Intent()
                    intent.type = "application/pdf"
                    intent.action = Intent.ACTION_GET_CONTENT
                    val mimetypes = arrayOf("application/pdf")
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes)
                    startActivityForResult(
                        Intent.createChooser(intent, "Select Pdf"),
                        REQ_CODE_PDF_INTENT
                    )
                }
            }
        }

        binding.btnSubmit.setOnClickListener {
            viewModel.submit()
        }

        binding.llDownload.setOnClickListener {

            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(USPS_DOWNLOAD)
            )
            startActivity(browserIntent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK)
        {
            if(requestCode == REQ_CODE_PDF_INTENT){

                JLog.e("success", "success")

                val uri = data?.data
                
                savePdf(uri)
                { filePath, _ ->

                    if(filePath != null)
                    {
                        viewModel.form1583.value = DateTimeUtil.getCurrentTimeStamp() + PDF

                        JLog.e("path",filePath.toString())
                    }

                    binding.img1583Form.loadImageUrl(
                        this,
                        R.drawable.img_pdf,
                        cornerRadius = CommonUtils.dpToPx(this, 10)
                    ) {

                    }


                    UploadImageModel(
                        mTitle = "1583 Form",
                        mFile = File(
                            filePath
                        ),
                        mUploadName =  viewModel.form1583.value!!,
                        isUploaded = false
                    ).also { viewModel.listUpload.value!![2] = it }
                }
            }
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(Companion.TAG, "Message: ${response.data}")
                showToast(response.message.toString())
            }
            is Resource.Success<*> -> {

                showAffirmationDialog(
                    title = resources.getString(R.string.mssg_usps_verification_submitted),
                    btnText = resources.getString(
                        R.string.lbl_okay
                    )
                ) {

                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
            is Resource.Loading<*> -> {

                response.isLoadingShow.let {
                    if (it as Boolean) {
                        showProgressDialog(this)
                    } else {
                        hideProgressDialog()
                    }
                }
            }
            is Resource.NoInternetError<*> -> {
                CommonUtils.showOkDialog(this, message = resources.getString(response.id!!))
            }
            is Resource.ValidationError<*> -> {
                showToast(resources.getString(response.id.nullSafe()))
            }
            is Resource.UnAuthorisedRequest<*> -> {
                showTokenExpiredDialog(resources.getString(R.string.lbl_session_expired_msg))
            }
        }
    }

    private fun savePdf(uri: Uri?,
        callback: (filePath: String?, error: String?) -> Unit
    ) {
        if (uri != null && uri.authority != null) {
            var errorMessage: String? = null
            var fos: FileOutputStream? = null
            val tempFile = File(this.externalCacheDir, "android_${System.currentTimeMillis()}.pdf")
            try {

                fos = FileOutputStream(tempFile)
                fos.writer()

            } catch (e: IOException) {
                e.printStackTrace()
                errorMessage = e.localizedMessage
            } finally {
                try {
                    fos?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            callback(errorMessage ?: tempFile.absolutePath, errorMessage)
        }
    }

    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }

        private val TAG = USPSVerificationActivity::class.java.simpleName
    }
}