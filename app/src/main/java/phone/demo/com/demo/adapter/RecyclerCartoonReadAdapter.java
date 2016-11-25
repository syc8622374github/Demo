package phone.demo.com.demo.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import phone.demo.com.demo.R;

/**
 * Created by cyc on 2016/11/25 0025.
 */

public class RecyclerCartoonReadAdapter extends BaseRecyclerAdapter<String> {

    public RecyclerCartoonReadAdapter(RecyclerView mRecyclerView) {
        super(mRecyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderGeneral holder = null;//ViewHolder的子类

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.common_item_base, parent, false);
        holder = new ViewHolderGeneral(view);//使用子类初始化ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String cartoonUrl = mList.get(position);
        bindData((ViewHolderGeneral)holder,cartoonUrl);
    }

    private void bindData(final ViewHolderGeneral holder, String cartoonUrl) {
        holder.iv_image_view.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(cartoonUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                holder.iv_image_view.setImageBitmap(resource);
                /*BitmapRegionDecoder bitmapRegionDecoder = null;
                InputStream is = BitmapUtils.Bitmap2IS(resource);
                try {
                    bitmapRegionDecoder = BitmapRegionDecoder.newInstance(is,false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(ScreenUtil.isOutSizeOfMaxLoadingBitmapSize(resource.getWidth(),resource.getHeight())){
                    if(bitmapRegionDecoder!=null){
                        bitmapRegionDecoder.decodeRegion(new Rect(), new BitmapFactory.Options());
                    }
                }*/
            }
        });
    }

    public static class ViewHolderGeneral extends RecyclerView.ViewHolder {

        public final View mView;
        public final ImageView iv_image_view;

        public ViewHolderGeneral(View view) {
            super(view);
            mView = view;
            iv_image_view = (ImageView) view.findViewById(R.id.iv_image_view);
        }
    }
}
