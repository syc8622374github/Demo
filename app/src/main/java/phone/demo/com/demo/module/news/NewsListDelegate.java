package phone.demo.com.demo.module.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.RecyclerShowAPINewsAdapter;
import phone.demo.com.demo.api.ShowApi;
import phone.demo.com.demo.module.cartoon.bean.ShowApiResponse;
import phone.demo.com.demo.module.news.bean.NewsItemBean;
import phone.demo.com.demo.module.news.bean.NewsResBody;
import phone.demo.com.demo.util.Constant;
import phone.demo.com.demo.widget.LoadingFooter;
import phone.demo.com.library.util.Logger;
import phone.demo.com.library.util.RetrofitUtils;
import phone.demo.com.library.view.AppDelegate;
import phone.demo.com.library.widget.myRecyclerView.HeaderAndFooterRecyclerViewAdapter;
import phone.demo.com.library.widget.myRecyclerView.RecyclerViewUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class NewsListDelegate extends AppDelegate {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Bundle bundle;
    private int mPage = 1;
    private LoadingFooter mFooterView;

    protected NewsListDelegate(Fragment fragment) {
        super(fragment);
        bundle = fragment.getArguments();
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_huaban_image_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_widget);
    }

    protected View getFootView() {
        if (mFooterView == null) {
            mFooterView = new LoadingFooter(fragment.getActivity());
            mFooterView.setState(LoadingFooter.State.Loading);
        }
        return mFooterView;
    }

    @Override
    public void initData() {
        super.initData();
        final RecyclerShowAPINewsAdapter mAdapter = new RecyclerShowAPINewsAdapter(recyclerView);
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(headAdapter);
        if (getFootView() != null) {
            RecyclerViewUtils.addFootView(recyclerView, getFootView());
        }
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getNewData(Constant.APPID, Constant.SECRET, bundle.get(Constant.TYPE_KEY).toString(), bundle.get(Constant.TITLE).toString(), "", String.valueOf(mPage), "0", String.valueOf(20))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShowApiResponse<NewsResBody>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e);
                    }

                    @Override
                    public void onNext(ShowApiResponse<NewsResBody> showApiResponse) {
                        if(showApiResponse.getShowapi_res_code()==0&&showApiResponse.getShowapi_res_body()!=null){
                            ArrayList<NewsItemBean> datas = showApiResponse.getShowapi_res_body().getPageBean().getContentlist();
                            mAdapter.setListNotify(datas);
                        }
                    }
                });
    }
}
