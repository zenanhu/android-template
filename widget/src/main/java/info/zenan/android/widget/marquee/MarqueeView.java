package info.zenan.android.widget.marquee;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ViewFlipper;

public class MarqueeView<V extends View, D> extends ViewFlipper {
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

    public void setAdapter(BaseAdapter adapter) {
        mAdapter = adapter;
    }

    public void notifyDataSetChanged() {
        if (mAdapter == null) {
            return;
        }

        for (int i = 0; i < mAdapter.getItemCount(); ++i) {
            addView((View) mAdapter.getView(i));
        }
    }
}
