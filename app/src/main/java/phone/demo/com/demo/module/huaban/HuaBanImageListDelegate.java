package phone.demo.com.demo.module.huaban;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.List;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.RecyclerPinsHeadCardAdapter;
import phone.demo.com.demo.api.HuaBanApi;
import phone.demo.com.demo.module.huaban.bean.ListPinsBean;
import phone.demo.com.demo.module.huaban.bean.PinsMainEntity;
import phone.demo.com.demo.util.Constant;
import phone.demo.com.library.util.RetrofitUtils;
import phone.demo.com.library.view.AppDelegate;
import phone.demo.com.library.widget.myRecyclerView.HeaderAndFooterRecyclerViewAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cyc on 2016/11/3 0003.
 */
public class HuaBanImageListDelegate extends AppDelegate {

    private RecyclerView recyclerView;
    private String mKey;
    private RecyclerPinsHeadCardAdapter mAdapter;

    protected HuaBanImageListDelegate(Fragment fragment) {
        super(fragment);
        getBundleData(fragment.getArguments());
    }

    private void getBundleData(Bundle arguments) {
        mKey = arguments.getString(Constant.TYPE_KEY);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_huaban_image_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
    }

    @Override
    public void initData() {
        super.initData();
        mAdapter = new RecyclerPinsHeadCardAdapter(recyclerView);
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        recyclerView.setAdapter(headAdapter);
        recyclerView.setLayoutManager(getLayoutManager());
        initHuabanData();
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public View getLoadingTargetView() {
        return recyclerView;
    }

    public void initHuabanData(){
        varyViewHelper.showLoadingView();
        RetrofitUtils.createApi(context, HuaBanApi.class, HuaBanApi.api)
                .httpsTypeLimitRx("Basic MWQ5MTJjYWU0NzE0NGZhMDlkODg6Zjk0ZmNjMDliNTliNDM0OWExNDhhMjAzYWIyZjIwYzc=", mKey, Constant.LIMIT)
                .map(new Func1<ListPinsBean, List<PinsMainEntity>>() {
                    @Override
                    public List<PinsMainEntity> call(ListPinsBean listPinsBean) {
                        return listPinsBean.getPins();
                    }
                })
                .subscribeOn(Schedulers.io())//发布者的运行线程 联网操作属于IO操作
                .observeOn(AndroidSchedulers.mainThread())//订阅者的运行线程 在main线程中才能修改UI
                .subscribe(new Subscriber<List<PinsMainEntity>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        varyViewHelper.showErrorView();
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onNext(List<PinsMainEntity> result) {
                        mAdapter.setListNotify(result);
                        if(result.size()>0){
                            varyViewHelper.showDataView();
                        }else{
                            varyViewHelper.showEmptyView();
                        }
                    }
                });

    }
}
