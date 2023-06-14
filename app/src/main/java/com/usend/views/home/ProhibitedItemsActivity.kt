package com.usend.views.home

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import com.usend.R
import com.usend.adapter.ProhibitedItemAdapter
import com.usend.base.BaseViewModel
import com.usend.base.ViewModelActivity
import com.usend.databinding.ActivityProhibitedItemsBinding
import com.usend.models.ProhibitedItemModel
import kotlinx.android.synthetic.main.app_toolbar.*
import kotlin.reflect.KClass

class ProhibitedItemsActivity(
    override val modelClass: KClass<BaseViewModel> = BaseViewModel::class,
    override val layoutId: Int = R.layout.activity_prohibited_items
) : ViewModelActivity<ActivityProhibitedItemsBinding, BaseViewModel>() {

    private var arrayList = arrayListOf<ProhibitedItemModel>()

    override fun initView() {
        super.initView()

        initToolbar(toolbar = toolbar, title = resources.getString(R.string.lbl_prohibited_item))

        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_alcohol), img = R.drawable.ic_alcohol))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_animals), img = R.drawable.ic_animals))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_biohazard), img = R.drawable.ic_biohazard))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_cbd), img = R.drawable.ic_cbd))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_chemicals), img = R.drawable.ic_chemical))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_illegal_substance), img = R.drawable.ic_illegal_substance))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_explosives), img = R.drawable.ic_explosives))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_fertilizers_pesticidess), img = R.drawable.ic_fertilizers))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_fire_arms), img = R.drawable.ic_fire_arms))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_radioactive_material), img = R.drawable.ic_radioactive))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_hazardous_materials), img = R.drawable.ic_hazardous))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_ivory), img = R.drawable.ic_ivory))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_poison), img = R.drawable.ic_poison))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_pornography), img = R.drawable.ic_pornography))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_cash), img = R.drawable.ic_cash))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_lead_acid_battery), img = R.drawable.ic_lead_acid))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_pressurized_cans), img = R.drawable.ic_pressurized_cans))
        arrayList.add(ProhibitedItemModel(title = resources.getString(R.string.lbl_tobacco), img = R.drawable.ic_tobacco))

        binding.rvProhibitedItems.layoutManager = GridLayoutManager(this, 3)
        binding.rvProhibitedItems.adapter = ProhibitedItemAdapter(arrayList){

        }
    }



    companion object {
        fun newIntent(context: Context, intent: Intent) {
            context.startActivity(intent)
        }
    }
}