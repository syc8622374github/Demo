package phone.demo.com.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import phone.demo.com.demo.bean.ShowApiResponse;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class RecyclerSimpleCardAdapter extends BaseRecyclerAdapter<ShowApiResponse> {

    public RecyclerSimpleCardAdapter(RecyclerView mRecyclerView) {
        super(mRecyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
