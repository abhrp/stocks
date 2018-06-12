package com.github.abhrp.stocksdemo.ui.views

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import com.github.abhrp.stocksdemo.application.StocksApplication

class NetworkImageView : ImageView {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: kotlin.Int) : super(context, attrs, defStyleAttrs)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttrs: Int, defStyleRes: Int) : super(context, attrs, defStyleAttrs, defStyleRes)

    fun setImageUrl(url: String?, placeholder: Int) {
        StocksApplication.getApplication().getApplicationComponent().picasso().load(url).placeholder(placeholder).into(this)
    }
}
