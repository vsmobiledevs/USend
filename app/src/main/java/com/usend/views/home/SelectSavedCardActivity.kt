package com.usend.views.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.base.network.BuildConfig
import com.base.network.model.*
import com.usend.R
import com.usend.adapter.SelectSavedCardsItemAdapter
import com.usend.base.ViewModelActivity
import com.usend.comman.Resource
import com.usend.databinding.ActivitySelectSavedCardBinding
import com.usend.extensions.nullSafe
import com.usend.extensions.showToast
import com.usend.models.SelectCardItemModel
import com.usend.utils.*
import com.usend.utils.CommonUtils.showAffirmationDialog
import com.usend.utils.CommonUtils.showAffirmationErrorDialog
import com.usend.viewmodels.SavedCardsViewModel
import kotlinx.android.synthetic.main.app_toolbar.*
import sqip.Callback
import sqip.CardEntry
import sqip.CardEntryActivityResult
import java.util.*
import kotlin.reflect.KClass

class SelectSavedCardActivity(
    override val modelClass: KClass<SavedCardsViewModel> = SavedCardsViewModel::class,
    override val layoutId: Int = R.layout.activity_select_saved_card
) : ViewModelActivity<ActivitySelectSavedCardBinding, SavedCardsViewModel>() {

    private var arrayList = arrayListOf<SelectCardItemModel>()
    private var selectedPosition: Int = 1
    private var addressId: Int? = null
    private var autoShipmentServiceId: Int? = null

    private var from: String? = null
    private var cardId: String? = null

    override fun initView() {
        super.initView()

        initToolbar(
            toolbar = toolbar,
            title = resources.getString(R.string.lbl_saved_payment_methods)
        )
        binding.rvSavedCards.addItemDecoration(
            ItemOffsetDecoration(
                resources.getDimensionPixelOffset(R.dimen.dimen_10dp), "top", pos = 1
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initControls() {
        super.initControls()

        if (intent.hasExtra(ADDRESS_ID)) {
            addressId = intent.getIntExtra(ADDRESS_ID, 0)
        }
        if (intent.hasExtra(SERVICE_NAME)) {
            autoShipmentServiceId = intent.getIntExtra(SERVICE_NAME, 0)
        }

        if (intent.hasExtra(FROM)) {
            from = intent.getStringExtra(FROM)
        }

        if (intent.hasExtra(CARD_ID)) {
            cardId = intent.getStringExtra(CARD_ID)
        }

        arrayList.clear()
        arrayList.add(SelectCardItemModel())

        binding.adapter = SelectSavedCardsItemAdapter(
            arrayList,
            isCheckBoxVisible = (from != UPDATE_AUTO_SHIPMENT)
        ) { mPosition, type ->

            when (type) {
                ADD -> {
                    CardEntry.startCardEntryActivity(
                        this, true,
                        CardEntry.DEFAULT_CARD_ENTRY_REQUEST_CODE
                    )
                }
                else -> {
                    if ((from != UPDATE_AUTO_SHIPMENT)) {
                        selectedPosition = mPosition
                        arrayList.forEachIndexed { index, _ ->
                            arrayList[index].isSelected = index == mPosition
                        }
                        binding.adapter?.notifyDataSetChanged()
                    } else {
                        selectedPosition = mPosition
                        if (arrayList.size > 1) {
                            showAffirmationDialog(
                                title = getString(R.string.lbl_deduct_for_verificaion),
                                btnText = getString(R.string.lbl_proceed),
                                btnNegativeText = getString(R.string.lbl_cancel)
                            )
                            {
                                viewModel.check3dCard(
                                    arrayList[selectedPosition].model?.id ?: ""
                                )
                            }
                        }
                    }
                }
            }

        }

        val customerId= PreferenceHelper.getUserObject().customerId
        if (customerId != null) {
            viewModel.ListCardApiS(SQUARE_UP_TOKEN,customerId)
        }

        binding.btnNext.setOnClickListener {
            if (arrayList.size > 1) {
                if (binding.cbTerms.isChecked) {
                    showAffirmationDialog(
                        title = getString(R.string.lbl_deduct_for_verificaion),
                        btnText = getString(R.string.lbl_proceed),
                        btnNegativeText = getString(R.string.lbl_cancel)
                    )
                    {
                        viewModel.check3dCard(arrayList[selectedPosition].model?.id?: "")
                    }
                } else {
                    showToast(
                        "Please agree " + getString(R.string.lbl_t_and_c) + " " + getString(R.string.lbl_and) + " " + getString(
                            R.string.lbl_privacy_policy
                        ) + "."
                    )
                }
            }
        }

        if (from == UPDATE_AUTO_SHIPMENT) {
            binding.btnNext.text = getString(R.string.lbl_update)
            binding.llBottomButton.visibility = View.GONE
            binding.rvSavedCards.setPadding(0,0,0,0)
        }
        setSpannableSignUp(binding.txtPrivacyDetails, binding.btnNext.text.toString())
    }

    @SuppressLint("StringFormatInvalid")
    private fun setSpannableSignUp(tvTermsConditions: TextView, name: String) {

        val builderAgreement = SpannableStringBuilder()
        val fullText = resources.getString(R.string.msg_continue_accept_terms_policy, name)


        val txtBySigningUp = SpannableString(fullText)
        txtBySigningUp.setSpan(
            StyleSpan(Typeface.NORMAL),
            0,
            fullText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtBySigningUp.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    this,
                    R.color.color802D2D2D
                )
            ), 0, fullText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        builderAgreement.append(txtBySigningUp)

        val txtTermsOfService =
            SpannableString(" " + resources.getString(R.string.lbl_t_and_c) + " ")
        txtTermsOfService.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            resources.getString(R.string.lbl_t_and_c).length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtTermsOfService.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    this,
                    R.color.colorLink
                )
            ),
            0,
            resources.getString(R.string.lbl_t_and_c).length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtTermsOfService.setSpan(
            MyClickableSpan(1),
            0,
            resources.getString(R.string.lbl_t_and_c).length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        builderAgreement.append(txtTermsOfService)

        val txtAnd = SpannableString(resources.getString(R.string.lbl_and))
        txtAnd.setSpan(
            StyleSpan(Typeface.NORMAL),
            0,
            resources.getString(R.string.lbl_and).length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtAnd.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.color802D2D2D)),
            0,
            resources.getString(R.string.lbl_and).length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        builderAgreement.append(txtAnd)

        val txtPrivacyPolicy =
            SpannableString(" " + resources.getString(R.string.lbl_privacy_policy))
        txtPrivacyPolicy.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            resources.getString(R.string.lbl_privacy_policy).length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtPrivacyPolicy.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    this,
                    R.color.colorLink
                )
            ),
            0,
            resources.getString(R.string.lbl_privacy_policy).length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtPrivacyPolicy.setSpan(
            MyClickableSpan(2),
            0,
            resources.getString(R.string.lbl_privacy_policy).length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        builderAgreement.append(txtPrivacyPolicy)

        tvTermsConditions.setText(builderAgreement.append("."), TextView.BufferType.SPANNABLE)
        tvTermsConditions.movementMethod = LinkMovementMethod.getInstance()

    }

    private class MyClickableSpan(var pos: Int) : ClickableSpan() {

        override fun onClick(widget: View) {
            when (pos) {
                1 -> {
                    WebViewActivity.newIntent(
                        widget.context, Intent(widget.context, WebViewActivity::class.java)
                            .putExtra(
                                TITLE,
                                widget.context.resources.getString(R.string.lbl_t_and_c)
                            )
                            .putExtra(SLUG, "terms_and_conditions")
                    )
                }
                2 -> {
                    WebViewActivity.newIntent(
                        widget.context, Intent(widget.context, WebViewActivity::class.java)
                            .putExtra(
                                TITLE,
                                widget.context.resources.getString(R.string.lbl_privacy_policy)
                            )
                            .putExtra(SLUG, "privacy_policy")
                    )
                }
            }
        }


        override fun updateDrawState(ds: TextPaint) {
            ds.isUnderlineText = false
        }

    }

    override fun addObserver() {
        viewModel.status.observe(this, mObserver)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val mObserver = Observer<Any> { response ->
        when (response) {
            is Resource.Error<*> -> {
                hideProgressDialog()
                JLog.e(Companion.TAG, "Message: ${response.data}")
                showToast(response.message.toString())
            }
            is Resource.AddSquareupCard<*> -> {
                response.data as SquareUpCardList
                var isAdded = false
                response.data.cards.forEachIndexed { index, item ->
                    if (from == UPDATE_AUTO_SHIPMENT) {
                        if (item.id == cardId) {
                            isAdded = true
                        }
                        arrayList.add(
                            SelectCardItemModel(
                                item,
                                item.id == cardId
                            )
                        )
                    } else {
                        arrayList.add(SelectCardItemModel(item, index == 0))
                    }
                }
                if (!isAdded && (response.data.cards.isNotEmpty()) && from == UPDATE_AUTO_SHIPMENT
                ) {
                    arrayList[1].isSelected = true
                }

                binding.adapter?.notifyDataSetChanged()
            }
            is Resource.SquareupError<*> ->
            {

                response.data as SquareupErrorResponse
                showAffirmationErrorDialog(
                    title = resources.getString(R.string.square_error_message)+
                            "\n\n "+"1.  "+ response.data.errors[0].category+
                            "\n"+"2.  "+ response.data.errors[0].code
                            +"\n"+"3.  "+ response.data.errors[0].detail+
                            "\n"+"4.  "+ response.data.errors[0].field,
                    btnText = resources.getString(R.string.lbl_okay)
                )
                {

                }
            }
            is Resource.SquareupFailureError<*> -> {
                showAffirmationErrorDialog(
                    title = response.message.toString(),
                    btnText = resources.getString(R.string.lbl_okay)
                )
                {

                }
                // response.data as Resource.SquareupFailureError
            }
            is Resource.AddUsendCard<*> -> {
                response.data as SquareupCreateResponse
                arrayList.add(SelectCardItemModel(response.data.card,false))
                Log.e("res", "Message: ${arrayList.size}")
                binding.adapter?.notifyDataSetChanged()
                showAffirmationDialog(title = resources.getString(R.string.msg_card_added_sucessfully),
                    btnText = resources.getString(R.string.lbl_okay))
                {
                    val intent = Intent()
                    // intent.putExtra(POSITION, mPos)
                    intent.putExtra(TYPE, ADD_CARD)
                    setResult(RESULT_OK, intent)
                }

            }
            is Resource.SuccessWithData<*> -> {
                hideProgressDialog()
                if (response.data is AutoShipmentResponse) {
                    if ((response.data.responseCode ?: 0) == SUCCESS) {
                        response.data.responseMessage?.let { showToast(it) }
                        when {
                            response.model != null && response.model is Int && response.model == 111 -> {

                            }
                            else -> {
                                AutoShipActivity.newIntent(
                                    this@SelectSavedCardActivity,
                                    Intent(this@SelectSavedCardActivity, AutoShipActivity::class.java)
                                )
                            }
                        }
                        val newIntent = Intent()
                        newIntent.putExtra(SHIPMENT_MODEL, response.data.responseData)
                        setResult(RESULT_OK, newIntent)
                        finish()
                    } else {
                        //response.data.responseMessage?.let { showToast(it) }
                    }
                } else if (response.data is SuccessModel) {
                    if (response.data.responseCode == SUCCESS) {
                        if (from == UPDATE_AUTO_SHIPMENT) {
                            val newIntent = Intent()
                            newIntent.putExtra(PAYMENT_MODEL, arrayList[selectedPosition].model)
                            setResult(RESULT_OK, newIntent)
                            finish()
                            /*viewModel.updateAutoShipment(
                                paymentDetailId = arrayList[selectedPosition].model?.id ?: 0
                            )*/
                        } else {
                            viewModel.newAutoShipment(
                                paymentDetailId = (arrayList[selectedPosition].model?.id ?: 0) as String,
                                addressId = addressId ?: 0,
                                autoShipmentServiceId = autoShipmentServiceId ?: 0
                            )
                        }
                    } else {
                        showToast(response.data.responseMessage)
                    }
                }

                else {
                    response.message?.let { showToast(it) }
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
                // use if you wanna show dialog or snackbar
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        CardEntry.handleActivityResult(
            data,
            object : Callback<CardEntryActivityResult> {
                override fun onResult(result: CardEntryActivityResult) {
                    val uuid = UUID.randomUUID().toString()
                    val cardholderName =
                        PreferenceHelper.getUserObject().firstName + " " + PreferenceHelper.getUserObject().lastName
                    val customerId = PreferenceHelper.getUserObject().customerId
                    if (result.isSuccess()) {
                        val card = result.getSuccessValue()
                        val cards = CreateCard.Cards(
                            cardholderName,
                            customerId,
                            card.card.expirationMonth,
                            card.card.expirationYear
                        )
                        val createCard = CreateCard(uuid, card.nonce, cards)
                        val squareUpToken: String = if (BuildConfig.DEBUG) {
                            SQUARE_UP_TOKEN
                        } else {
                            SQUARE_UP_TOKEN
                        }

                        viewModel.addCardApiS(squareUpToken, createCard, ADD)
                    } else if (result.isCanceled()) {
                        /* JLog.e(TAG, "Message: ${result.data}")*/
                        showToast("Canceled")
                    }
                }
            })
        if (resultCode == Activity.RESULT_OK) {

            var pos = -1
            var type = 0
            if (data != null) {
                if (data.hasExtra(POSITION)) {
                    pos = data.getIntExtra(POSITION, 0)
                    type = data.getIntExtra(TYPE, 0)
                }
            }

            when (requestCode) {

                ADD_CARD -> {

                    // val card: Card? = data?.getParcelableExtra(CARD)

                    /* arrayList.add(
                         1, SelectCardItemModel(
                             CardList(
                                 cardId = card?.cardId,
                                 brand = card?.brand,
                                 cardHolderName = card?.cardHolderName,
                                 expiryMonth = card?.expiryMonth,
                                 expiryYear = card?.expiryYear,
                                 last4 = card?.last4
                             ), isSelected = true
                         )
                     )*/

                    binding.adapter?.notifyItemInserted(1)
                }

                EDIT_CARD -> {

                    if (type == EDIT_CARD) {

                        //  val card: UpdateCard? = data?.getParcelableExtra(CARD)

                        /* arrayList[pos] = SelectCardItemModel(
                             CardXX(
                                 cardId = card?.cardId,
                                 brand = card?.brand,
                                 cardHolderName = card?.cardHolderName,
                                 expiryMonth = card?.expiryMonth,
                                 expiryYear = card?.expiryYear,
                                 last4 = card?.last4
                             ), isSelected = true
                         )
 */
                        binding.adapter?.notifyItemChanged(pos)
                    }
                    else if (type == DELETE_CARD) {
                        arrayList.removeAt(pos)

                        binding.adapter?.notifyItemRemoved(pos)
                    }
                }
            }
        }
    }


    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }

        private val TAG = SavedCardsActivity::class.java.simpleName
    }
}