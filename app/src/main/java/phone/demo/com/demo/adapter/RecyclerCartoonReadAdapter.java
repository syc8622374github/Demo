package phone.demo.com.demo.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.io.File;

import phone.demo.com.demo.R;
import phone.demo.com.demo.util.ImageUtils;
import phone.demo.com.library.util.Logger;

/**
 * Created by cyc on 2016/11/25 0025.
 */

public class RecyclerCartoonReadAdapter extends BaseRecyclerAdapter<String> {

    private DisplayMetrics displaysMetrics;

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

    private void bindData(final ViewHolderGeneral holder, final String cartoonUrl) {
        displaysMetrics = new DisplayMetrics();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);
        Glide.with(mContext).load(cartoonUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onLoadStarted(Drawable placeholder) {
                super.onLoadStarted(placeholder);
                holder.iv_image_view.setImageResource(R.mipmap.ic_launcher);
                holder.iv_image_view.setVisibility(View.VISIBLE);
                holder.iv_image_view.setScaleType(ImageView.ScaleType.CENTER);
            }

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                int height = resource.getScaledHeight(displaysMetrics);
                int width = resource.getScaledWidth(displaysMetrics);
                holder.iv_image_view.setScaleType(ImageView.ScaleType.FIT_XY);
                if(height > 4096 || width > 4096){
                    Logger.i("bitmap than size");
                    File file = ImageUtils.saveBitmap(mContext,resource,cartoonUrl);
                    holder.iv_huge_image_view.setImage(ImageSource.uri(Uri.fromFile(file)));
                    holder.iv_huge_image_view.setVisibility(View.VISIBLE);
                    holder.iv_image_view.setVisibility(View.GONE);
                }else{
                    holder.iv_image_view.setImageBitmap(resource);
                    holder.iv_image_view.setVisibility(View.VISIBLE);
                    holder.iv_huge_image_view.setVisibility(View.GONE);
                    if(holder.iv_huge_image_view.hasImage()){
                        holder.iv_huge_image_view.recycle();
                        Logger.i("recycle hugeImageView");
                    }
                }
            }
        });
    }

    public static class ViewHolderGeneral extends RecyclerView.ViewHolder {

        public final View mView;
        public final SubsamplingScaleImageView iv_huge_image_view;
        public final ImageView iv_image_view;

        public ViewHolderGeneral(View view) {
            super(view);
            mView = view;
            iv_image_view = (ImageView) view.findViewById(R.id.iv_image_view);
            iv_huge_image_view = (SubsamplingScaleImageView) view.findViewById(R.id.iv_huge_image_view);
            iv_huge_image_view.setZoomEnabled(false);
        }
    }
}
