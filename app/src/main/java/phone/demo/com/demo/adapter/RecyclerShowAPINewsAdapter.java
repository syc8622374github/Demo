package phone.demo.com.demo.adapter;

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

import java.util.ArrayList;

import phone.demo.com.demo.R;
import phone.demo.com.demo.module.news.bean.NewsImageBean;
import phone.demo.com.demo.module.news.bean.NewsItemBean;

/**
 * Created by cyc on 2016/12/21 0021.
 */

public class RecyclerShowAPINewsAdapter extends BaseRecyclerAdapter<NewsItemBean> {

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
        NewsItemBean newsItemBean = mList.get(position);
        //父类强制转换成子类 因为这个holder本来就是子类初始化的 所以可以强转
        RecyclerShowAPINewsAdapter.ViewHolderGeneral viewHolder = (RecyclerShowAPINewsAdapter.ViewHolderGeneral) holder;//强制类型转换 转成内部的ViewHolder
        bindData(viewHolder, newsItemBean);//绑定用户数据
        bindListener(viewHolder, newsItemBean,position);
    }

    private void bindListener(ViewHolderGeneral viewHolder, NewsItemBean showAPIItemBean, int position) {
    }

    private void bindData(ViewHolderGeneral viewHolder, NewsItemBean newsItemBean) {
        //标题
        viewHolder.tv_card_title.setText(newsItemBean.getTitle());
        //缩略图
        ArrayList<NewsImageBean> imageUrls = newsItemBean.getImageurls();
        if(imageUrls!=null){
            if(imageUrls.size()>=3){
                Glide.with(mContext).load(imageUrls.get(0).getUrl()).into(viewHolder.iv_card_thumbnail_1);
                Glide.with(mContext).load(imageUrls.get(1).getUrl()).into(viewHolder.iv_card_thumbnail_2);
                Glide.with(mContext).load(imageUrls.get(2).getUrl()).into(viewHolder.iv_card_thumbnail_3);
                viewHolder.ll_image_list.setVisibility(View.VISIBLE);
                viewHolder.iv_card_thumbnail.setVisibility(View.GONE);
            }else if(imageUrls.size()==1){
                Glide.with(mContext).load(imageUrls.get(0).getUrl()).into(viewHolder.iv_card_thumbnail);
                viewHolder.iv_card_thumbnail.setVisibility(View.VISIBLE);
                viewHolder.ll_image_list.setVisibility(View.GONE);
            }else{
                viewHolder.iv_card_thumbnail.setVisibility(View.GONE);
                viewHolder.iv_card_thumbnail.setVisibility(View.GONE);
            }
        }else{
            viewHolder.iv_card_thumbnail.setVisibility(View.GONE);
            viewHolder.iv_card_thumbnail.setVisibility(View.GONE);
        }
        //时间
        if(!TextUtils.isEmpty(newsItemBean.getPubDate())){
            viewHolder.tv_time.setText(newsItemBean.getPubDate());
            viewHolder.rl_footer.setVisibility(View.VISIBLE);
        }else{
            viewHolder.rl_footer.setVisibility(View.GONE);
        }
    }

    public static class ViewHolderGeneral extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView tv_time;//时间
        public final TextView tv_card_title;//标题或描述文字
        public final RelativeLayout rl_footer;//地步脚部区域
        public final ImageView iv_card_thumbnail;//缩略图
        public final ImageView iv_card_thumbnail_1;//多张缩略图1
        public final ImageView iv_card_thumbnail_2;//多张缩略图2
        public final ImageView iv_card_thumbnail_3;//多张缩略图3
        public final LinearLayout ll_image_list;//多张缩略图

        public ViewHolderGeneral(View view) {
            super(view);
            mView = view;
            tv_card_title = (TextView) view.findViewById(R.id.tv_card_title);
            iv_card_thumbnail = (ImageView) view.findViewById(R.id.iv_card_thumbnail);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            rl_footer = (RelativeLayout) view.findViewById(R.id.rl_footer);
            iv_card_thumbnail_1 = (ImageView) view.findViewById(R.id.iv_card_thumbnail_1);
            iv_card_thumbnail_2 = (ImageView) view.findViewById(R.id.iv_card_thumbnail_2);
            iv_card_thumbnail_3 = (ImageView) view.findViewById(R.id.iv_card_thumbnail_3);
            ll_image_list = (LinearLayout) view.findViewById(R.id.ll_image_list);
        }
    }
}
