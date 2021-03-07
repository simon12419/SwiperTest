package com.example.swipertest.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.swipertest.R
import com.example.swipertest.util.DisplayUtil.px
import kotlinx.android.synthetic.main.view_tab.view.*

class TabView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_tab, this, false)
        addView(view)

        val typedArray = context.theme
            .obtainStyledAttributes(attrs, R.styleable.CustomView, 0, 0)
        try {
            val imgRes = typedArray.getResourceId(R.styleable.CustomView_cvIcon, -1)
            view.iv_icon.setImageResource(imgRes)
            view.tv_title.text = typedArray.getText(R.styleable.CustomView_cvTitle)

            val textSizePx = typedArray.getDimension(R.styleable.CustomView_cvTextSize, -1f).px
            if (textSizePx > 0)
                view.tv_title.textSize = textSizePx

            val colorRes = typedArray.getColorStateList(R.styleable.CustomView_cvTextColor)
            view.tv_title.setTextColor(colorRes)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            typedArray.recycle()
        }
    }
}