package phone.demo.com.demo.module.huaban;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import phone.demo.com.demo.R;
import phone.demo.com.demo.widget.LoadingFooter;
import phone.demo.com.library.view.AppDelegate;

/**
 * Created by cyc on 2016/11/24 0024.
 * 漫画浏览
 */

public class CartoonReadDelegate extends AppDelegate {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private LoadingFooter mFooterView;

    protected CartoonReadDelegate(Activity activity) {
        super(activity);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.common_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        /*mAdapter = new RecyclerPinsHeadCardAdapter(recyclerView);
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mAdapter.setOnClickItemListener(new RecyclerPinsHeadCardAdapter.OnAdapterListener() {
            @Override
            public void onClickImage(PinsMainEntity bean, View view) {

            }

            @Override
            public void onClickTitleInfo(PinsMainEntity bean, View view) {

            }

            @Override
            public void onClickInfoGather(PinsMainEntity bean, View view) {

            }

            @Override
            public void onClickInfoLike(PinsMainEntity bean, View view) {

            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(headAdapter);
        if (getFootView() != null) {
            RecyclerViewUtils.addFootView(recyclerView, getFootView());
        }
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画*/
    }

    protected View getFootView() {
        if (mFooterView == null) {
            mFooterView = new LoadingFooter(fragment.getActivity());
            mFooterView.setState(LoadingFooter.State.Loading);
        }
        return mFooterView;
    }
}
