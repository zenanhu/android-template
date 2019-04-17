package info.zenan.android.widget.marquee;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

public class BaseMarqueeView extends FrameLayout {
    private int mCurrentPosition = 0;

    public BaseMarqueeView(Context context) {
        super(context);
    }

    public BaseMarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

    }

    public void showNextView() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                beginAnimation(getChildAt(mCurrentPosition));
            }
        }, 1000);
    }

    private void beginAnimation(View view) {
        ObjectAnimator translateY = ObjectAnimator.ofFloat(view, "translationY", 0f, view.getHeight());
        Log.d("TAG", "view's height: " + view.getHeight());
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0f);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(3000);
        set.setInterpolator(new LinearInterpolator());
        set.play(translateY);
        set.start();
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);

        if (getChildCount() == 1) {
            child.setVisibility(View.VISIBLE);
        } else {
            child.setVisibility(View.GONE);
        }
    }
}
