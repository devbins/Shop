package com.dev.bins.shop.widget;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by bin on 27/02/2017.
 */

public class CountDownView extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     * to {@link #start()} until the countdown is done and {@link #onFinish()}
     * is called.
     * @param countDownInterval The interval along the way to receive
     * {@link #onTick(long)} callbacks.
     */

    TextView mCountDownView;
    String s_end;
    public CountDownView(TextView countDownView,String end, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        mCountDownView = countDownView;
        this.s_end = end;
    }

    public CountDownView(TextView mCountDownView,String end) {
        super(6000, 1000);
        this.mCountDownView = mCountDownView;
        this.s_end = end;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mCountDownView.setText(millisUntilFinished/1000+"秒后可重新发送");
        mCountDownView.setEnabled(false);
    }

    @Override
    public void onFinish() {
        mCountDownView.setEnabled(true);
        mCountDownView.setText(s_end);
    }
}
