package phone.demo.com.demo.adapter;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;

import phone.demo.com.demo.R;
import phone.demo.com.demo.module.news.bean.JokeItemBean;
import phone.demo.com.demo.module.news.bean.NewsImageBean;
import phone.demo.com.demo.module.news.bean.NewsItemBean;
import phone.demo.com.library.util.Utils;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class RecyclerShowAPINewsAdapter extends BaseRecyclerAdapter {

    public RecyclerShowAPINewsAdapter(RecyclerView mRecyclerView) {
        super(mRecyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_standard, parent, false);
        RecyclerShowAPINewsAdapter.ViewHolderGeneral holder = new RecyclerShowAPINewsAdapter.ViewHolderGeneral(view);//使用子类初始化ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //父类强制转换成子类 因为这个holder本来就是子类初始化的 所以可以强转
        RecyclerShowAPINewsAdapter.ViewHolderGeneral viewHolder = (RecyclerShowAPINewsAdapter.ViewHolderGeneral) holder;//强制类型转换 转成内部的ViewHolder
        bindData(viewHolder, mList.get(position));//绑定用户数据
        bindListener(viewHolder, mList.get(position),position);
    }

    private void bindListener(final ViewHolderGeneral viewHolder, final Object newsItemBean, final int position) {
        if(!(newsItemBean instanceof JokeItemBean)){
            viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClickListener(viewHolder.mView,newsItemBean,position);
                }
            });
        }
    }

    private void bindData(ViewHolderGeneral viewHolder, Object itemBean) {
        if(itemBean instanceof NewsItemBean){
            NewsItemBean newsItemBean = (NewsItemBean) itemBean;
            viewHolder.ll_card_default.setVisibility(View.VISIBLE);
            viewHolder.ll_card_joke.setVisibility(View.GONE);
            //标题
            viewHolder.tv_card_title.setText(newsItemBean.getTitle());
            //缩略图
            ArrayList<NewsImageBean> imageUrls = newsItemBean.getImageurls();
            ViewGroup.LayoutParams params = viewHolder.rl_card_right_content.getLayoutParams();
            params.height = Utils.dp2px(mContext,50);
            if(imageUrls!=null){
                if(imageUrls.size()>=3){
                    params.height = Utils.dp2px(mContext,150);
                    Glide.with(mContext).load(imageUrls.get(0).getUrl()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.grey_400))).into(viewHolder.iv_card_thumbnail_1);
                    Glide.with(mContext).load(imageUrls.get(1).getUrl()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.grey_400))).into(viewHolder.iv_card_thumbnail_2);
                    Glide.with(mContext).load(imageUrls.get(2).getUrl()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.grey_400))).into(viewHolder.iv_card_thumbnail_3);
                    ((RelativeLayout.LayoutParams)viewHolder.ll_image_list.getLayoutParams()).addRule(RelativeLayout.CENTER_IN_PARENT);
                    viewHolder.ll_image_list.setVisibility(View.VISIBLE);
                    viewHolder.iv_card_thumbnail.setVisibility(View.GONE);
                }else if(imageUrls.size()==1){
                    params.height = Utils.dp2px(mContext,75);
                    Glide.with(mContext).load(imageUrls.get(0).getUrl()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.grey_400))).into(viewHolder.iv_card_thumbnail);
                    viewHolder.iv_card_thumbnail.setVisibility(View.VISIBLE);
                    viewHolder.ll_image_list.setVisibility(View.GONE);
                }else{
                    viewHolder.ll_image_list.setVisibility(View.GONE);
                    viewHolder.iv_card_thumbnail.setVisibility(View.GONE);
                }
            }else{
                viewHolder.ll_image_list.setVisibility(View.GONE);
                viewHolder.iv_card_thumbnail.setVisibility(View.GONE);
            }
            //时间
            if(!TextUtils.isEmpty(newsItemBean.getPubDate())){
                viewHolder.tv_time.setText(newsItemBean.getPubDate());
                viewHolder.rl_footer.setVisibility(View.VISIBLE);
            }else{
                viewHolder.rl_footer.setVisibility(View.GONE);
            }
        }else if(itemBean instanceof JokeItemBean){
            JokeItemBean jokeItemBean = (JokeItemBean) itemBean;
            viewHolder.ll_card_default.setVisibility(View.GONE);
            viewHolder.ll_card_joke.setVisibility(View.VISIBLE);
            //标题
            viewHolder.tv_card_joke_title.setText(jokeItemBean.getTitle());
            //展示图
            if(!TextUtils.isEmpty(jokeItemBean.getImg())){
                viewHolder.iv_card_joke_image.setVisibility(View.VISIBLE);
                if(jokeItemBean.getImg().endsWith(".gif")){
                    Glide.with(mContext).load(jokeItemBean.getImg()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.grey_400)))
                            .thumbnail(0.01f).into(new GlideDrawableImageViewTarget(viewHolder.iv_card_joke_image,10));
                }else{
                    Glide.with(mContext).load(jokeItemBean.getImg()).placeholder(new ColorDrawable(mContext.getResources().getColor(R.color.grey_400)))
                            .into(viewHolder.iv_card_joke_image);
                }
            }else{
                viewHolder.iv_card_joke_image.setVisibility(View.GONE);
            }
            //文本内容
            viewHolder.tv_card_joke_content.setText(jokeItemBean.getText());
            //时间
            viewHolder.tv_card_joke_footer_time.setText(jokeItemBean.getCt());
        }
    }

    public static class ViewHolderGeneral extends RecyclerView.ViewHolder {

        public View mView;
        public LinearLayout ll_card_default; //默认样式
        public TextView tv_time;//时间
        public TextView tv_card_title;//标题或描述文字
        public RelativeLayout rl_footer;//地步脚部区域
        public ImageView iv_card_thumbnail;//缩略图
        public ImageView iv_card_thumbnail_1;//多张缩略图1
        public ImageView iv_card_thumbnail_2;//多张缩略图2
        public ImageView iv_card_thumbnail_3;//多张缩略图3
        public LinearLayout ll_image_list;//多张缩略图
        public RelativeLayout rl_card_right_content;//右边模块内容

        public LinearLayout ll_card_joke; //笑话样式
        public TextView tv_card_joke_title; //标题
        public TextView tv_card_joke_content; //内容
        public ImageView iv_card_joke_image; //展示图
        public TextView tv_card_joke_footer_time; //页脚时间

        public ViewHolderGeneral(View view) {
            super(view);
            mView = view;
            //默认样式
            ll_card_default = (LinearLayout) view.findViewById(R.id.ll_card_default);
            tv_card_title = (TextView) view.findViewById(R.id.tv_card_title);
            iv_card_thumbnail = (ImageView) view.findViewById(R.id.iv_card_thumbnail);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            rl_footer = (RelativeLayout) view.findViewById(R.id.rl_footer);
            iv_card_thumbnail_1 = (ImageView) view.findViewById(R.id.iv_card_thumbnail_1);
            iv_card_thumbnail_2 = (ImageView) view.findViewById(R.id.iv_card_thumbnail_2);
            iv_card_thumbnail_3 = (ImageView) view.findViewById(R.id.iv_card_thumbnail_3);
            ll_image_list = (LinearLayout) view.findViewById(R.id.ll_image_list);
            rl_card_right_content = (RelativeLayout) view.findViewById(R.id.rl_card_right_content);
            //笑话样式
            ll_card_joke = (LinearLayout) view.findViewById(R.id.ll_card_joke);
            tv_card_joke_title = (TextView) view.findViewById(R.id.tv_card_joke_title);
            tv_card_joke_content = (TextView) view.findViewById(R.id.tv_card_joke_content);
            iv_card_joke_image = (ImageView) view.findViewById(R.id.iv_card_joke_image);
            tv_card_joke_footer_time = (TextView) view.findViewById(R.id.tv_card_joke_footer_time);
        }
    }
}
