package phone.demo.com.demo.delegate;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import phone.demo.com.demo.R;
import phone.demo.com.demo.bean.NewsTimeLine;
import phone.demo.com.demo.net.ZhihuApi;
import phone.demo.com.demo.utils.Url;
import phone.demo.com.library.util.RetrofitUtils;
import phone.demo.com.library.view.AppDelegate;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author cyc
 * @name phone.demo.com.demo.delegate
 * @description
 * @date 2016/10/17 0017
 */
public class ImageListDelegate extends AppDelegate {

    private RecyclerView recyclerView;

    public ImageListDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.fragment_image_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = get(R.id.feed_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void initData() {
        super.initData();
        initZhiHuData();
    }

    public void initZhiHuData() {
        RetrofitUtils.createApi(context, ZhihuApi.class, Url.ZHIHU_BASE_URL)
                .getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<NewsTimeLine>() {
                    @Override
                    public void call(NewsTimeLine newsTimeLine) {

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
}
