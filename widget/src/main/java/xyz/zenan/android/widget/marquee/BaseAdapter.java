package xyz.zenan.android.widget.marquee;

import android.view.View;

import java.util.List;

public class BaseAdapter<V extends View, D> {
    private List<D> dataList;

    protected V getView(int position) {
        return null;
    }

    protected D getItem(int position) {
        // TODO: throw exception if position is invalid.
        if (dataList == null) {
            return null;
        }
        return dataList.get(position);
    }

    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    protected void setData(List<D> dataList) {
        this.dataList = dataList;
    }
}
