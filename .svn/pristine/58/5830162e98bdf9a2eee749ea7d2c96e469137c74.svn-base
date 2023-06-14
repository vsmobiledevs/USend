package com.usend.views.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import com.base.network.model.NotificationsListReponse
import com.base.network.model.UserNotification
import com.usend.R
import com.usend.adapter.NotificationItemAdapter
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivityNotificationBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.utils.*
import com.usend.viewmodels.NotificationViewModel
import com.usend.views.MainActivity
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlinx.android.synthetic.main.app_toolbar.view.*
import kotlin.reflect.KClass

class NotificationActivity(
    override val modelClass: KClass<NotificationViewModel> = NotificationViewModel::class,
    override val layoutId: Int = R.layout.activity_notification
) : ViewModelActivity<ActivityNotificationBinding, NotificationViewModel>() {

    private var arrayList = arrayListOf<UserNotification>()

    override fun initView() {
        super.initView()

        initToolbar(toolbar = toolbar, title = resources.getString(R.string.lbl_notification))

        viewModel.getNotificationList()

        binding.rvNoti.addItemDecoration(
            ItemOffsetDecoration(
                resources.getDimensionPixelOffset(R.dimen.dimen_10dp),
                "top"
            )
        )
        binding.adapter = NotificationItemAdapter(arrayList) { mPos, type ->

            when (type) {

                NORMAL -> {

                    if (!arrayList[mPos].isRead!!) {
                        viewModel.readNotification(arrayList[mPos].id!!)
                    }

                    val model = arrayList[mPos]
                    val type = model.notificationType
                    model.isRead = true
                    arrayList[mPos] = model
                    binding.rvNoti.adapter?.notifyItemChanged(mPos)

                    when (type) {

                        PACKAGE_CREATED_BY_ADMIN -> {

                            /*PackageDetailActivity.newIntent(this, Intent(this, PackageDetailActivity::class.java)
                                .putExtra(PACKAGE_ID, arrayList[mPos].notifiableId)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))*/

                        }
                        ORDER_CREATED -> {

                            OrderDetailsActivity.newIntent(this, Intent(this, OrderDetailsActivity::class.java)
                                .putExtra(ORDER_ID, arrayList[mPos].notifiableId)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))
                        }
                        ORDER_ACCEPTED -> {

                            OrderDetailsActivity.newIntent(this, Intent(this, OrderDetailsActivity::class.java)
                                .putExtra(ORDER_ID, arrayList[mPos].notifiableId)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))
                        }
                        ORDER_LABEL_CREATED -> {

                            OrderDetailsActivity.newIntent(this, Intent(this, OrderDetailsActivity::class.java)
                                .putExtra(ORDER_ID, arrayList[mPos].notifiableId)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))
                        }
                        CONCIERGE_STATUS_UPDATED_BY_ADMIN -> {

                            MainActivity.newIntent(this, Intent(this, MainActivity::class.java)
                                .putExtra(FROM, FROM_CONCIERGE)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))
                        }
                        CONCIERGE_NOTE_UPDATE->{
                            MainActivity.newIntent(this, Intent(this, MainActivity::class.java)
                                .putExtra(FROM, FROM_CONCIERGE)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))
                        }

                        CONCIERGE_QUOTE_GENERATED -> {

                            MainActivity.newIntent(this, Intent(this, MainActivity::class.java)
                                .putExtra(FROM, FROM_CONCIERGE)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))
                        }
                        USPS_STATUS_UPDATE -> {

                            MainActivity.newIntent(this, Intent(this, MainActivity::class.java)
                                .putExtra(FROM, FROM_NOTIFICATION)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))
                        }
                        AUTO_SHIP_ORDER_CREATE->{
                            OrderDetailsActivity.newIntent(this, Intent(this, OrderDetailsActivity::class.java)
                                .putExtra(ORDER_ID, arrayList[mPos].notifiableId)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))
                        }
                        else -> {

                            MainActivity.newIntent(this, Intent(this, MainActivity::class.java)
                                .putExtra(EXTRA_FROM_TYPE, TYPE_NOTIFICATION_ACTIVITY))

                        }
                    }
                }
                DELETE -> {
                    viewModel.deleteNotification(arrayList[mPos].id.nullSafe(),"single")
                }
            }
        }
    }

    override fun initControls() {
        super.initControls()

        toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {

                JLog.e(Companion.TAG, "Message: ${response.data}")
                showToast(response.message.toString())
            }
            is Resource.NotiListSuccess<*> -> {

                response.data as NotificationsListReponse

                arrayList.clear()
                arrayList.addAll(response.data.responseData?.notifications!!)

                binding.adapter?.notifyDataSetChanged()

            }
            is Resource.ReadNotificationSuccess<*> -> {

                viewModel.getNotificationList()
                JLog.e(Companion.TAG, "Message: ${response.data}")
            }
            is Resource.DeleteNotificationSuccess<*> -> {
                viewModel.getNotificationList()
            }
            is Resource.ItemsNotFound<*> -> {

                binding.isEmpty = true
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

    override fun onBackPressed() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }

        private val TAG = NotificationActivity::class.java.simpleName
    }
}