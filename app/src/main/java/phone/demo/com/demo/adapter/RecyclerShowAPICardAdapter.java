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

import phone.demo.com.demo.R;
import phone.demo.com.demo.module.cartoon.bean.ShowApiItemBean;

/**
 * Created by cyc on 2016/11/23 0023.
 */

public class RecyclerShowAPICardAdapter extends BaseRecyclerAdapter<ShowApiItemBean> {

    private OnAdapterListener mListener;

    public interface OnAdapterListener {
        void onItemClickListener(View view, ShowApiItemBean showAPIItemBean, int position);
    }

    public RecyclerShowAPICardAdapter(RecyclerView mRecyclerView) {
        super(mRecyclerView);
    }

    public void setOnClickItemListener(OnAdapterListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolderGeneral holder = null;//ViewHolder的子类

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_standard, parent, false);
        holder = new ViewHolderGeneral(view);//使用子类初始化ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ShowApiItemBean showAPIItemBean = mList.get(position);
        //父类强制转换成子类 因为这个holder本来就是子类初始化的 所以可以强转
        ViewHolderGeneral viewHolder = (ViewHolderGeneral) holder;//强制类型转换 转成内部的ViewHolder
        bindData(viewHolder, showAPIItemBean);//绑定用户数据
        bindListener(viewHolder, showAPIItemBean,position);
    }

    private void bindListener(ViewHolderGeneral viewHolder, final ShowApiItemBean showAPIItemBean, final int position) {
        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClickListener(v, showAPIItemBean,position);
            }
        });
    }

    private void bindData(ViewHolderGeneral holder, ShowApiItemBean showAPIItemBean) {
        holder.tv_title.setText(showAPIItemBean.getTitle());
        if(showAPIItemBean.getThumbnailList()!=null&&showAPIItemBean.getThumbnailList().size()>0){
            holder.iv_thumbnail.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(showAPIItemBean.getThumbnailList().get(0)).centerCrop().into(holder.iv_thumbnail);
        }else{
            holder.iv_thumbnail.setVisibility(View.GONE);
        }
        if(!TextUtils.isEmpty(showAPIItemBean.getTime())){
            holder.tv_time.setText(showAPIItemBean.getTime());
            holder.tv_time.setVisibility(View.VISIBLE);
        }else{
            holder.tv_time.setVisibility(View.GONE);
        }
    }

    public static class ViewHolderGeneral extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView tv_title;//标题或描述文字
        public final ImageView iv_thumbnail;//缩略图
        public final LinearLayout ll_content;//内容区域
        public final RelativeLayout rl_footer;//地步脚部区域
        public final TextView tv_time;//时间

        public ViewHolderGeneral(View view) {
            super(view);
            mView = view;
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            iv_thumbnail = (ImageView) view.findViewById(R.id.iv_thumbnail);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            ll_content = (LinearLayout) view.findViewById(R.id.ll_content);
            rl_footer = (RelativeLayout) view.findViewById(R.id.rl_footer);
        }
    }
}
