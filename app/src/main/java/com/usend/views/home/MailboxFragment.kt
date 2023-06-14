package com.usend.views.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.Observer
import com.base.network.model.ListPackageResponse
import com.base.network.model.PackageList
import com.base.network.model.SuccessModel
import com.base.network.model.User
import com.usend.R
import com.usend.adapter.MailboxPackagesItemAdapter
import com.usend.base.ViewModelFragment
import com.usend.comman.Resource
import com.usend.databinding.FragmentMailboxBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.models.MailboxItemModel
import com.usend.utils.*
import com.usend.utils.CommonUtils.showAffirmationDialog
import com.usend.viewmodels.MailboxViewModel

class MailboxFragment(
    override val modelClass: Class<MailboxViewModel> = MailboxViewModel::class.java,
    override val layoutId: Int = R.layout.fragment_mailbox
) : ViewModelFragment<FragmentMailboxBinding, MailboxViewModel>() {

    private var arrayList = arrayListOf<MailboxItemModel>()
    private var arrayListPackageItems = arrayListOf<PackageList>()

    private var user: User? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun initControls() {

        user = PreferenceHelper.getUserObject()

        binding.viewModel = viewModel
        binding.frag = this

        binding.txtMailBoxNo.text = user?.hostAddress

        viewModel.isSelectMode.observe(this) {

            if (!it) {
                arrayList.forEachIndexed { index, _ ->

                    arrayList[index].isSelectMode = false
                    arrayList[index].isSelected = false
                }

                binding.adapter?.notifyDataSetChanged()
            }
        }

        binding.swipePackages.setOnRefreshListener {
            binding.swipePackages.isRefreshing = false
            viewModel.getPackageListApi()
        }

        binding.adapter = MailboxPackagesItemAdapter(
            arrayList,
            arrayListPackageItems,
            viewModel.isSelectMode.value!!
        ) { it, type ->

            if (type == LONG_CLICK) {

                if (!viewModel.isSelectMode.value!!) {
                    viewModel.isSelectMode.value = true
                }

                if (viewModel.isSelectMode.value!!) {
                    arrayList.forEachIndexed { index, _ ->

                        arrayList[index].isSelectMode = true
                        if (index == it) {
                            arrayList[index].isSelected = true
                        }
                    }

                    binding.adapter?.notifyDataSetChanged()
                }
            } else {
                PackageDetailActivity.newIntent(
                    requireActivity(), Intent(requireActivity(), PackageDetailActivity::class.java)
                        .putExtra(EXTRA_DATA, arrayListPackageItems[it])
                )
            }
        }

        viewModel.getPackageListApi()
    }

    fun onSentToTrash() {
        val listIds = arrayListOf<String>()

        arrayList.forEachIndexed { index, mailboxItemModel ->
            if (mailboxItemModel.isSelected) {
                listIds.add(arrayListPackageItems[index].id.toString())
            }
        }
        val ids = android.text.TextUtils.join(",", listIds)
        if (listIds.size > 0) {
            activity?.showAffirmationDialog(
                title = getString(R.string.lbl_confirm_trash),
                btnText = getString(R.string.lbl_yes),
                btnNegativeText = getString(R.string.lbl_no)
            )
            {
                viewModel.packageSendToTrash(ids)
            }
        } else {
            showToast(requireActivity(), resources.getString(R.string.msg_plz_select_package_trash))
        }
    }

    fun onCreateShipmentClick() {

        val listIds = arrayListOf<String>()

        arrayList.forEachIndexed { index, mailboxItemModel ->

            if (mailboxItemModel.isSelected) {
                listIds.add(arrayListPackageItems[index].id.toString())
            }
        }

        if (listIds.size > 0) {
            ShipToAddressActivity.newIntent(
                requireActivity(), Intent(requireActivity(), ShipToAddressActivity::class.java)
                    .putExtra(FROM, FROM_PACKAGEDETAIL)
                    .putStringArrayListExtra(PACKAGE_IDs, listIds)
            )
        } else {
            showToast(requireActivity(), resources.getString(R.string.msg_plz_select_package))
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSelectClick() {

        if (!viewModel.isSelectMode.value!!) {
            viewModel.isSelectMode.value = true

            if (viewModel.isSelectMode.value!!) {
                arrayList.forEachIndexed { index, _ ->

                    arrayList[index].isSelectMode = true
                }

                binding.adapter?.notifyDataSetChanged()
            }
        } else if (viewModel.isSelectMode.value!!) {
            if (binding.txtSelect.text == resources.getString(R.string.lbl_select_all)) {
                arrayList.forEachIndexed { index, _ ->

                    if (!arrayListPackageItems[index].isSplit!!) {
                        arrayList[index].isSelected = true
                    }
                }
                binding.txtSelect.text = resources.getString(R.string.lbl_deselect_all)
                binding.adapter?.notifyDataSetChanged()
            } else {

                viewModel.isSelectMode.value = false

                arrayList.forEachIndexed { index, _ ->

                    arrayList[index].isSelected = false
                    arrayList[index].isSelectMode = false
                }
                binding.txtSelect.text = resources.getString(R.string.lbl_select)
                binding.adapter?.notifyDataSetChanged()
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun onSelectCancelClick() {
        if (!viewModel.isSelectMode.value!!) {

            viewModel.isSelectMode.value = true

            arrayList.forEachIndexed { index, _ ->

                arrayList[index].isSelectMode = true
            }

            binding.adapter?.notifyDataSetChanged()
        } else {
            viewModel.isSelectMode.value = false

            arrayList.forEachIndexed { index, _ ->

                arrayList[index].isSelected = false
                arrayList[index].isSelectMode = false
            }

            binding.adapter?.notifyDataSetChanged()
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(TAG, "Message: ${response.data}")
                showToast(requireActivity(), response.message.toString())
            }
            is Resource.ItemsNotFound<*> -> {

                binding.isEmpty = true
            }
            is Resource.Success<*> -> {

                arrayListPackageItems.clear()
                response.data as ListPackageResponse
                arrayListPackageItems.addAll(response.data.responseData?.packages!!)
                binding.txtTotalPackages.text = arrayListPackageItems.size.toString()

                arrayListPackageItems.forEach { _ ->
                    arrayList.add(
                        MailboxItemModel(
                            isSelectMode = viewModel.isSelectMode.value!!,
                            isSelected = false
                        )
                    )
                }

                binding.isEmpty = arrayListPackageItems.size == 0
                binding.adapter?.notifyDataSetChanged()

            }

            is Resource.SuccessWithData<*> -> {
                hideProgressDialog()
                viewModel.getPackageListApi()
                if (response.data is SuccessModel) {
                    onSelectCancelClick()
                    showToast(requireActivity(), response.data.responseMessage)
                }
            }

            is Resource.Loading<*> -> {

                response.isLoadingShow.let {
                    if (it as Boolean) {
                        showProgressDialog(requireActivity())
                    } else {
                        hideProgressDialog()
                    }
                }
            }
            is Resource.NoInternetError<*> -> {
                CommonUtils.showOkDialog(requireActivity(), message = resources.getString(response.id!!))
            }
            is Resource.ValidationError<*> -> {
                showToast(requireActivity(), resources.getString(response.id.nullSafe()))
            }
            is Resource.UnAuthorisedRequest<*> -> {
                showTokenExpiredDialog(resources.getString(R.string.lbl_session_expired_msg))
            }
        }
    }

    companion object {
        private val TAG = MailboxFragment::class.java.simpleName
    }
}