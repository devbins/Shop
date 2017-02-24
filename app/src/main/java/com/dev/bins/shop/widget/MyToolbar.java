package com.dev.bins.shop.widget;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;


/**
 * Created by bin on 24/02/2017.
 */

public class MyToolbar extends Toolbar {
    public MyToolbar(Context context) {
        this(context,null);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
