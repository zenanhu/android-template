package info.zenan.android.widget.marquee;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ViewFlipper;

public class MarqueeView<V, D> extends ViewFlipper {
    private BaseAdapter<V, D> mAdapter;

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
