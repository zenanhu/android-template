package info.zenan.android.widget.marquee;

import java.util.List;

public class BaseAdapter<V, D> {
    private List<D> dataList;

    protected V createView(int position) {
        return null;
    }

    protected D getItem(int position) {
        // TODO: throw exception if position is invalid.
        if (dataList == null) {
            return null;
        }
        return dataList.get(position);
    }

    protected void setData(List<D> dataList) {
        this.dataList = dataList;
    }
}
