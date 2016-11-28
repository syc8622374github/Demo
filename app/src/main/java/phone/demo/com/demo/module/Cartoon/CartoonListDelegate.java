package phone.demo.com.demo.module.Cartoon;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.RecyclerShowAPICardAdapter;
import phone.demo.com.demo.api.ShowApi;
import phone.demo.com.demo.bean.ShowApiItemBean;
import phone.demo.com.demo.bean.ShowApiResponse;
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
 * Created by cyc on 2016/11/23 0023.
 */

public class CartoonListDelegate extends AppDelegate {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LoadingFooter mFooterView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RecyclerShowAPICardAdapter mAdapter;
    private int mPage = 1;

    protected CartoonListDelegate(Fragment fragment) {
        super(fragment);
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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCartoonList(true);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();
        getCartoonList(false);
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerShowAPICardAdapter(recyclerView);
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(headAdapter);
        if (getFootView() != null) {
            RecyclerViewUtils.addFootView(recyclerView, getFootView());
        }
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    //滑动停止
                    boolean isBottom = staggeredGridLayoutManager
                            .findLastCompletelyVisibleItemPositions(new int[2])[1] >= mAdapter.getItemCount();
                    if (isBottom && !swipeRefreshLayout.isRefreshing()) {
                        getCartoonOnScroll();
                    }
                } else if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    //用户正在滑动
//                    Logger.d("用户正在滑动 position=" + mAdapter.getAdapterPosition());
                } else {
                    //惯性滑动
//                    Logger.d("惯性滑动 position=" + mAdapter.getAdapterPosition());
                }
            }
        });
        mAdapter.setOnClickItemListener(new RecyclerShowAPICardAdapter.OnAdapterListener() {
            @Override
            public void onItemClickListener(View view, ShowApiItemBean showAPIItemBean, int position) {
                if(!TextUtils.isEmpty(showAPIItemBean.getId())){
                    getCartoonDetailData(showAPIItemBean.getId());
                }else{
                    // TODO: 2016/11/25 0025 无法查找到漫画提示
                }
            }
        });
    }

    public void getCartoonDetailData(String id){
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getBAWCartoonDetailData(Constant.APPID, Constant.SECRET, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShowApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e);
                    }

                    @Override
                    public void onNext(ShowApiResponse showAPIResponse) {
                        if (showAPIResponse.getShowapi_res_body() != null) {
                            Intent intent = new Intent(context,CartoonReadActivity.class);
                            intent.putExtra(CartoonReadActivity.DATA,showAPIResponse.getShowapi_res_body().getItem().getImgList());
                            context.startActivity(intent);
                        }
                    }
                });
    }

    /**
     * 加载更多
     */
    public void getCartoonOnScroll() {
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getBAWCartoonListData(Constant.APPID, Constant.SECRET, Constant.CARTTON_BAW_TYPE_KBMH, mPage++)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShowApiResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e);
                    }

                    @Override
                    public void onNext(ShowApiResponse showAPIResponse) {
                        if (showAPIResponse.getShowapi_res_body() != null) {
                            mAdapter.addListNotify(showAPIResponse.getShowapi_res_body().getPagebean().getContentlist());
                        }
                    }
                });
    }

    @Override
    public View getLoadingTargetView() {
        return recyclerView;
    }

    public void getCartoonList(final boolean isRefresh){
        if (!isRefresh) {
            varyViewHelper.showLoadingView();
            swipeRefreshLayout.setEnabled(false);
        }
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getBAWCartoonListData(Constant.APPID,Constant.SECRET,Constant.CARTTON_BAW_TYPE_KBMH,mPage++)
                .subscribeOn(Schedulers.io())//发布者的运行线程 联网操作属于IO操作
                .observeOn(AndroidSchedulers.mainThread())//订阅者的运行线程 在main线程中才能修改UI
                .subscribe(new Subscriber<ShowApiResponse>() {
                    @Override
                    public void onCompleted() {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        varyViewHelper.showErrorView();
                        swipeRefreshLayout.setRefreshing(false);
                        Logger.e(e);
                    }

                    @Override
                    public void onNext(ShowApiResponse showAPIResponse) {
                        swipeRefreshLayout.setEnabled(true);
                        if(showAPIResponse.getShowapi_res_code()==0){
                            if(showAPIResponse.getShowapi_res_body()!=null){
                                List<ShowApiItemBean> beans = showAPIResponse.getShowapi_res_body().getPagebean().getContentlist();
                                mAdapter.setListNotify(beans);
                                if (!isRefresh) {
                                    if (beans.size() > 0) {
                                        varyViewHelper.showDataView();
                                    } else {
                                        varyViewHelper.showEmptyView();
                                    }
                                }
                            }
                        }else{
                            varyViewHelper.showEmptyView();
                        }
                    }
                });
    }

    protected View getFootView() {
        if (mFooterView == null) {
            mFooterView = new LoadingFooter(fragment.getActivity());
            mFooterView.setState(LoadingFooter.State.Loading);
        }
        return mFooterView;
    }
}
