package info.zenan.android.widget.marquee;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class MarqueeView extends ViewFlipper {

    public MarqueeView(Context context) {
        super(context);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

    }

}
