package com.usend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.usend.R
import kotlinx.android.synthetic.main.row_item_intropager.view.*

class IntroPageItemAdapter : PagerAdapter() {

    private val pageCount: Int = 3

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(collection.context)
        val layout = inflater.inflate(R.layout.row_item_intropager, collection, false) as ViewGroup

        when (position) {
            0 -> {
                layout.txtIntroTitle.text = layout.context.getString(R.string.lbl_intro_title1)
                layout.txtIntroDesc.text = layout.context.getString(R.string.lbl_receive_your_tax)
                layout.introImage.setImageResource(R.drawable.ic_intro_image1)
            }
            1 -> {
                layout.txtIntroTitle.text = layout.context.getString(R.string.lbl_intro_title2)
                layout.txtIntroDesc.text = layout.context.getString(R.string.lbl_free_ship)
                layout.introImage.setImageResource(R.drawable.ic_intro_image2)
            }
            2 -> {
                layout.txtIntroTitle.text = layout.context.getString(R.string.lbl_intro_title3)
                layout.txtIntroDesc.text = layout.context.getString(R.string.lbl_consolidate)
                layout.introImage.setImageResource(R.drawable.ic_intro_image3)
            }
            /*3 -> {
                layout.txtIntroTitle.text = layout.context.getString(R.string.lbl_intro_title4)
                layout.txtIntroDesc.text = layout.context.getString(R.string.lbl_lorem_ipsum)
                layout.introImage.setImageResource(R.drawable.ic_intro_image_4)
            }*/
        }

        collection.addView(layout)
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getCount(): Int {
        return pageCount
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}