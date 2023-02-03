package com.example.seradmin.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

public class MiLinearLayoutManager  extends LinearLayoutManager {
    public MiLinearLayoutManager(Context context) {
        super(context);
    }

    public MiLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MiLinearLayoutManager(Context context, int orientation, Boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean supportsPredictiveItemAnimations(){
        return false;
    };
}
