package com.smu.sangmyung.likelionandroidarchiving

import android.support.v4.view.ViewPager
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.graphics.Point
import android.support.annotation.Px
import android.view.MotionEvent
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class PagerContainer : FrameLayout, ViewPager.OnPageChangeListener {

    private var mIndicatorView: PagerIndicator? = null

    var viewPager: ViewPager? = null
    var mNeedsRedraw = false

    private val mCenter = Point()
    private val mInitialTouch = Point()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        //Disable clipping of children so non-selected pages are visible
        clipChildren = false

        //Child clipping doesn't work with hardware acceleration in Android 3.x/4.x
        //You need to set this value here if using hardware acceleration in an
        // application targeted at these releases.
        setLayerType(View.LAYER_TYPE_SOFTWARE,Paint(alpha.compareTo(0.3)))
    }

    @SuppressLint("MissingSuperCall")
    override fun onFinishInflate() {
        try {
            viewPager = getChildAt(0) as ViewPager
            viewPager!!.setOnPageChangeListener(this)
        } catch (e: Exception) {
            throw IllegalStateException("The root child of PagerContainer must be a ViewPager")
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mCenter.x = w / 2
        mCenter.y = h / 2
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        /* ViewPager 외부에서(FrameLayout 안에서) 터치 및 드래그 했을 때에도
           ViewPager가 스와이핑 될 수 있도록 터치 이벤트 좌표를 옮겨주는 코드 */
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mInitialTouch.x = event.x.toInt()
                mInitialTouch.y = event.y.toInt()
                event.offsetLocation((mCenter.x - mInitialTouch.x).toFloat(),
                    (mCenter.y - mInitialTouch.y).toFloat())
            }
            else -> event.offsetLocation((mCenter.x - mInitialTouch.x).toFloat(),
                (mCenter.y - mInitialTouch.y).toFloat())
        }

        return viewPager!!.dispatchTouchEvent(event)
    }

    fun setIndicator(pagerIndicator: PagerIndicator) {
        this.mIndicatorView = pagerIndicator
    }

    override fun onPageSelected(p0: Int) {
        mIndicatorView?.selectDot(p0)
    }
    override fun onPageScrolled(position: Int, p1: Float, @Px p2: Int) {}
    override fun onPageScrollStateChanged(state: Int) {}

}