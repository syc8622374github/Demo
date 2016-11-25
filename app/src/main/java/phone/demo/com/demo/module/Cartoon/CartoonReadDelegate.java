package phone.demo.com.demo.module.Cartoon;

import android.app.Activity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.RecyclerCartoonReadAdapter;
import phone.demo.com.library.view.AppDelegate;
import phone.demo.com.library.widget.myRecyclerView.HeaderAndFooterRecyclerViewAdapter;

/**
 * Created by cyc on 2016/11/24 0024.
 * 漫画浏览
 */

public class CartoonReadDelegate extends AppDelegate {

    private RecyclerView recyclerView;
    private RecyclerCartoonReadAdapter mAdapter;

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
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_list);
        /*final SubsamplingScaleImageView subsamplingScaleImageView = get(R.id.scale_image_view);
        subsamplingScaleImageView.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CUSTOM);
        subsamplingScaleImageView.setMinScale(1.0F);
        Glide.with(context)
                .load("http://s.cimg.163.com/i/img.heibaimanhua.com/wp-content/uploads/2016/11/25/20161125_5837da6d7d570.png_s.0x0.auto.jpg")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        subsamplingScaleImageView.setImage(ImageSource.bitmap(resource));
                    }
                });*/
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();
        List<String> imageUrls = activity.getIntent().getStringArrayListExtra(CartoonReadActivity.DATA);
        if(imageUrls!=null&&imageUrls.size()>0){
            mAdapter.setListNotify(imageUrls);
            //varyViewHelper.showDataView();
        }else{
            //varyViewHelper.showEmptyView();
        }
    }

    /*@Override
    public View getLoadingTargetView() {
        return recyclerView;
    }*/

    private void initRecyclerView() {
        mAdapter = new RecyclerCartoonReadAdapter(recyclerView);
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(headAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
    }
}
