package info.zenan.android.widget.marquee;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class MarqueeView<V extends View, D> extends BaseMarqueeView {
    private BaseAdapter<V, D> mAdapter;

    public MarqueeView(Context context) {
        super(context);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
//        setOrientation(LinearLayout.VERTICAL);
//        if (getInAnimation() == null || getOutAnimation() == null) {
//            setInAnimation(getContext(), R.anim.in_bottom);
//            setOutAnimation(getContext(), R.anim.out_top);
//        }
//        getInAnimation().setDuration(1000);
//        getOutAnimation().setDuration(1000);

    }

    public void startFlipping() {
        showNextView();
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
