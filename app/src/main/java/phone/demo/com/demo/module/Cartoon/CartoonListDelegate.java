package phone.demo.com.demo.module.cartoon;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import java.util.List;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.RecyclerShowAPICardAdapter;
import phone.demo.com.demo.api.ShowApi;
import phone.demo.com.demo.module.cartoon.bean.ShowApiItemBean;
import phone.demo.com.demo.module.cartoon.bean.ShowApiResponse;
import phone.demo.com.demo.util.Constant;
import phone.demo.com.demo.util.PermissionUtil;
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
    private final Bundle bundle;

    protected CartoonListDelegate(Fragment fragment) {
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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String key = bundle.getString(Constant.TYPE_KEY);
                switch (key){
                    case Constant.CARTTON_BAW_TYPE_KBMH:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_KBMH);
                        break;
                    case Constant.CARTTON_BAW_TYPE_GSMH:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_GSMH);
                        break;
                    case Constant.CARTTON_BAW_TYPE_DZS:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_DZS);
                        break;
                    case Constant.CARTTON_BAW_TYPE_LZS:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_LZS);
                        break;
                    case Constant.CARTTON_BAW_TYPE_QIQU:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_QIQU);
                        break;
                    case Constant.CARTTON_BAW_TYPE_DY:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_DY);
                        break;
                    case Constant.CARTTON_BAW_TYPE_GX:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_GX);
                        break;
                    case Constant.CARTTON_BAW_TYPE_MC:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_MC);
                        break;
                    case Constant.CARTTON_BAW_TYPE_XQ:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_XQ);
                        break;
                    case Constant.CARTTON_BAW_TYPE_HQ:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_HQ);
                        break;
                    case Constant.CARTTON_BAW_TYPE_SY:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_SY);
                        break;
                    case Constant.CARTTON_BAW_TYPE_WY:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_WY);
                        break;
                    case Constant.CARTTON_BAW_TYPE_CH:
                        getBAWCartoonList(true,Constant.CARTTON_BAW_TYPE_CH);
                        break;
                    case Constant.CARTTON_CONNOTATION_TYPE_ALL:
                        getConnotationCartoonList(true);
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        initPermission();
        initRecyclerView();
        String key = bundle.getString(Constant.TYPE_KEY);
        switch (key){
            case Constant.CARTTON_BAW_TYPE_KBMH:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_KBMH);
                break;
            case Constant.CARTTON_BAW_TYPE_GSMH:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_GSMH);
                break;
            case Constant.CARTTON_BAW_TYPE_DZS:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_DZS);
                break;
            case Constant.CARTTON_BAW_TYPE_LZS:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_LZS);
                break;
            case Constant.CARTTON_BAW_TYPE_QIQU:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_QIQU);
                break;
            case Constant.CARTTON_BAW_TYPE_DY:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_DY);
                break;
            case Constant.CARTTON_BAW_TYPE_GX:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_GX);
                break;
            case Constant.CARTTON_BAW_TYPE_MC:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_MC);
                break;
            case Constant.CARTTON_BAW_TYPE_XQ:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_XQ);
                break;
            case Constant.CARTTON_BAW_TYPE_HQ:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_HQ);
                break;
            case Constant.CARTTON_BAW_TYPE_SY:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_SY);
                break;
            case Constant.CARTTON_BAW_TYPE_WY:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_WY);
                break;
            case Constant.CARTTON_BAW_TYPE_CH:
                getBAWCartoonList(false,Constant.CARTTON_BAW_TYPE_CH);
                break;
            case Constant.CARTTON_CONNOTATION_TYPE_ALL:
                getConnotationCartoonList(false);
                break;
        }
    }

    //请求权限
    private void initPermission() {
        PermissionUtil.needPermission(activity,3, Manifest.permission.WRITE_EXTERNAL_STORAGE);
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
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    //滑动停止
                    /*boolean isBottom = staggeredGridLayoutManager
                            .findLastCompletelyVisibleItemPositions(new int[2])[1] >= mAdapter.getItemCount();*/
                    boolean isBottom = linearLayoutManager.findLastCompletelyVisibleItemPosition() >= mAdapter.getItemCount();
                    if (isBottom && !swipeRefreshLayout.isRefreshing()) {
                        String key = bundle.getString(Constant.TYPE_KEY);
                        switch (key){
                            case Constant.CARTTON_BAW_TYPE_KBMH:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_KBMH);
                                break;
                            case Constant.CARTTON_BAW_TYPE_GSMH:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_GSMH);
                                break;
                            case Constant.CARTTON_BAW_TYPE_DZS:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_DZS);
                                break;
                            case Constant.CARTTON_BAW_TYPE_LZS:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_LZS);
                                break;
                            case Constant.CARTTON_BAW_TYPE_QIQU:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_QIQU);
                                break;
                            case Constant.CARTTON_BAW_TYPE_DY:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_DY);
                                break;
                            case Constant.CARTTON_BAW_TYPE_GX:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_GX);
                                break;
                            case Constant.CARTTON_BAW_TYPE_MC:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_MC);
                                break;
                            case Constant.CARTTON_BAW_TYPE_XQ:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_XQ);
                                break;
                            case Constant.CARTTON_BAW_TYPE_HQ:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_HQ);
                                break;
                            case Constant.CARTTON_BAW_TYPE_SY:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_SY);
                                break;
                            case Constant.CARTTON_BAW_TYPE_WY:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_WY);
                                break;
                            case Constant.CARTTON_BAW_TYPE_CH:
                                getBAWCartoonOnScroll(Constant.CARTTON_BAW_TYPE_CH);
                                break;
                            case Constant.CARTTON_CONNOTATION_TYPE_ALL:
                                getConnotationCartoonOnScroll();
                                break;
                        }
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
                    String key = bundle.getString(Constant.TYPE_KEY);
                    switch (key){
                        case Constant.CARTTON_CONNOTATION_TYPE_ALL:
                            getConnotationCartoonDetailData(showAPIItemBean.getId());
                            break;
                        default:
                            getBAWCartoonDetailData(showAPIItemBean.getId());
                            break;
                    }
                }else{
                    // TODO: 2016/11/25 0025 无法查找到漫画提示
                }
            }
        });
    }

    @Override
    public View getLoadingTargetView() {
        return recyclerView;
    }

    protected View getFootView() {
        if (mFooterView == null) {
            mFooterView = new LoadingFooter(fragment.getActivity());
            mFooterView.setState(LoadingFooter.State.Loading);
        }
        return mFooterView;
    }

    /**
     * 获取黑白漫画列表数据
     * @param isRefresh
     * @param cartoonType
     */
    public void getBAWCartoonList(final boolean isRefresh,String cartoonType){
        if (!isRefresh) {
            varyViewHelper.showLoadingView();
            swipeRefreshLayout.setEnabled(false);
        }
        mPage = 1;
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getBAWCartoonListData(Constant.APPID,Constant.SECRET,cartoonType,mPage)
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
                            }else{
                                varyViewHelper.showEmptyView();
                            }
                        }else{
                            varyViewHelper.showEmptyView();
                        }
                    }
                });
    }

    /**
     * 获取黑白漫画详细信息数据
     * @param id
     */
    public void getBAWCartoonDetailData(String id){
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
                            intent.putExtra(CartoonReadActivity.ARRAY_DATA,showAPIResponse.getShowapi_res_body().getItem().getImgList());
                            context.startActivity(intent);
                        }
                    }
                });
    }

    /**
     * 加载黑白漫画更多
     * @param cartoonType
     */
    public void getBAWCartoonOnScroll(String cartoonType) {
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getBAWCartoonListData(Constant.APPID, Constant.SECRET, cartoonType, mPage++)
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

    /**
     * 获取内涵漫画列表数据
     * @param isRefresh
     */
    public void getConnotationCartoonList(final boolean isRefresh){
        if (!isRefresh) {
            varyViewHelper.showLoadingView();
            swipeRefreshLayout.setEnabled(false);
        }
        mPage = 1;
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getConnotationCartoonListData(Constant.APPID,Constant.SECRET,mPage)
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
                            }else{
                                varyViewHelper.showEmptyView();
                            }
                        }else{
                            varyViewHelper.showEmptyView();
                        }
                    }
                });
    }

    /**
     * 获取内涵漫画详细信息数据
     * @param id
     */
    public void getConnotationCartoonDetailData(String id){
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getConnotationCartoonDetailData(Constant.APPID, Constant.SECRET, id)
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
                            intent.putExtra(CartoonReadActivity.DATA,showAPIResponse.getShowapi_res_body().getImg());
                            context.startActivity(intent);
                        }
                    }
                });
    }

    /**
     * 加载内涵漫画更多
     */
    public void getConnotationCartoonOnScroll() {
        RetrofitUtils.createShowApi(context, ShowApi.class, ShowApi.API)
                .getConnotationCartoonListData(Constant.APPID, Constant.SECRET, mPage++)
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
}
