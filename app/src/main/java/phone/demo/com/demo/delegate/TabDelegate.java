package phone.demo.com.demo.delegate;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import phone.demo.com.demo.R;
import phone.demo.com.library.bean.ImageDetail;
import phone.demo.com.library.net.Host;
import phone.demo.com.library.net.NetWorkApi;
import phone.demo.com.library.net.response.ImageListResponse;
import phone.demo.com.library.util.LogUtil;
import phone.demo.com.library.util.RetrofitUtils;
import phone.demo.com.library.view.AppDelegate;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by cyc on 2016/10/16.
 */
public class TabDelegate extends AppDelegate {

    private RecyclerView recyclerView;

    public TabDelegate(Fragment fragment) {
        super(fragment);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.base_fragment;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = get(R.id.content_list);
    }

    @Override
    public void initData() {
        super.initData();
        RetrofitUtils
                .createApi(context, NetWorkApi.class, Host.host).getImageList("美女",0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ImageListResponse<ImageDetail>>() {
                    @Override
                    public void call(ImageListResponse<ImageDetail> imageDetailImageListResponse) {
                        //((TextView)rootView.findViewById(R.id.textView)).setText(imageDetailImageListResponse.getResult().toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtil.e(throwable.getStackTrace().toString());
                        Toast.makeText(context, R.string.load_error, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
