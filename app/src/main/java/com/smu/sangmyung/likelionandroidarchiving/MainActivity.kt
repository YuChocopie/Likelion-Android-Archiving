package com.smu.sangmyung.likelionandroidarchiving


import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_feed_box.*
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.ShapeDrawable



/**
 * PagerActivity: A Sample Activity for PagerContainer
 */
class MainActivity : Activity() {

    var mContainer: PagerContainer? = null
    var pageNum = 4

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvFeedUserProfileImg.background = ShapeDrawable(OvalShape())
        tvFeedUserProfileImg2.background = ShapeDrawable(OvalShape())
        tvFeedUserProfileImg.clipToOutline = true
        tvFeedUserProfileImg2.clipToOutline = true

        mContainer = findViewById<View>(R.id.pager_container) as PagerContainer

        val pager = mContainer!!.viewPager as ViewPager
        val adapter = MyPagerAdapter()
        pager.setAdapter(adapter)
        pager.setOffscreenPageLimit(adapter.count)
        pager.setPageMargin(8)
        pagerIndicator?.createDotPanel(pageNum, R.drawable.indicator_dot_off, R.drawable.indicator_dot_on, pager.currentItem)
        pager_container.setIndicator(pagerIndicator)


        pager.setClipChildren(false)
    }

    private inner class MyPagerAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view = TextView(this@MainActivity)
            view.text = "Item $position"
            view.gravity = Gravity.CENTER
            view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50))

            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int {
            return 5
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }
    }
}