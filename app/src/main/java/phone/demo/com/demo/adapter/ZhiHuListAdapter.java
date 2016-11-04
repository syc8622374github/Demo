package phone.demo.com.demo.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import phone.demo.com.demo.R;
import phone.demo.com.demo.bean.NewsTimeLine;
import phone.demo.com.demo.bean.Stories;
import phone.demo.com.demo.bean.TopStories;
import phone.demo.com.demo.util.ScreenUtil;
import phone.demo.com.demo.widget.TopStoriesViewPager;

/**
 * @author cyc
 * @name phone.demo.com.demo.adapter
 * @description
 * @date 2016/10/19 0019
 */
public class ZhiHuListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private NewsTimeLine newsTimeLine;
    private Context context;
    //滑动加载更多状态
    private int status = 1;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_TOP = -1;
    private static final int TYPE_FOOTER = -2;

    public ZhiHuListAdapter(Context context,NewsTimeLine newsTimeLine) {
        this.newsTimeLine = newsTimeLine;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (newsTimeLine.getTop_stories() != null) {
            if (position == 0) {
                return TYPE_TOP;
            } else if (position + 1 == getItemCount()) {
                return TYPE_FOOTER;
            } else {
                return position;
            }
        } else if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return position;
        }
    }

    public void notifyDataSetChanged(NewsTimeLine newsTimeLine){
        switch (status){
            case LOAD_MORE:
                this.newsTimeLine.getStories().addAll(newsTimeLine.getStories());
                break;
            default:
                this.newsTimeLine = newsTimeLine;
                break;
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TOP) {
            View rootView = View.inflate(parent.getContext(), R.layout.item_zhihu_top_stories, null);
            return new TopStoriesViewHolder(rootView);
        } else if(viewType == TYPE_FOOTER){
            View view = View.inflate(parent.getContext(), R.layout.activity_view_footer, null);
            return new FooterViewHolder(view);
        }else {
            View rootView = View.inflate(parent.getContext(), R.layout.item_zhihu_stories, null);
            return new StoriesViewHolder(rootView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        }else if (holder instanceof TopStoriesViewHolder) {
            TopStoriesViewHolder topStoriesViewHolder = (TopStoriesViewHolder) holder;
            topStoriesViewHolder.bindItem(newsTimeLine.getTop_stories());
        } else if (holder instanceof StoriesViewHolder) {
            StoriesViewHolder storiesViewHolder = (StoriesViewHolder) holder;
            storiesViewHolder.bindItem(newsTimeLine.getStories().get(position-1));
        }
    }

    @Override
    public int getItemCount() {
        return newsTimeLine.getStories().size()+2;
    }

    /**
     * footer view
     */
    class FooterViewHolder extends RecyclerView.ViewHolder {
        TextView tv_load_prompt;
        ProgressBar progress;

        public FooterViewHolder(View itemView) {
            super(itemView);
            tv_load_prompt = (TextView) itemView.findViewById(R.id.tv_load_prompt);
            progress = (ProgressBar) itemView.findViewById(R.id.progress);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtil.dp2px(context,40));
            itemView.setLayoutParams(params);
        }

        private void bindItem() {
            switch (status) {
                case LOAD_MORE:
                    progress.setVisibility(View.VISIBLE);
                    tv_load_prompt.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progress.setVisibility(View.GONE);
                    tv_load_prompt.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    System.out.println("LOAD_NONE----");
                    progress.setVisibility(View.GONE);
                    tv_load_prompt.setText("已无更多加载");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                default:
                    break;
            }
        }
    }


    /**
     * TopStories
     */
    class TopStoriesViewHolder extends RecyclerView.ViewHolder {

        TopStoriesViewPager vp_top_stories;
        TextView tv_top_title;

        public TopStoriesViewHolder(View itemView) {
            super(itemView);
            tv_top_title = (TextView) itemView.findViewById(R.id.tv_top_title);
            vp_top_stories = (TopStoriesViewPager) itemView.findViewById(R.id.vp_top_stories);
        }

        public void bindItem(List<TopStories> topList) {
            vp_top_stories.init(topList, tv_top_title, new TopStoriesViewPager.ViewPagerClickListenner() {
                @Override
                public void onClick(TopStories item) {

                }
            });
        }
    }

    /**
     * Stories
     */
    class StoriesViewHolder extends RecyclerView.ViewHolder {

        CardView card_stories;
        TextView tv_stories_title;
        ImageView iv_stories_img;

        public StoriesViewHolder(View itemView) {
            super(itemView);
            card_stories = (CardView) itemView.findViewById(R.id.card_stories);
            tv_stories_title = (TextView) itemView.findViewById(R.id.tv_stories_title);
            iv_stories_img = (ImageView) itemView.findViewById(R.id.iv_stories_img);
            int screenWidth = ScreenUtil.getScreenWidth(context);
            card_stories.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT));

        }

        public void bindItem(Stories stories) {
            tv_stories_title.setText(stories.getTitle());
            String[] images = stories.getImages();
            Glide.with(context).load(images[0]).centerCrop().into(iv_stories_img);

            card_stories.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //context.startActivity(ZhihuWebActivity.newIntent(context, stories.getId()))
                }
            });
        }
    }

    // change recycler state
    public void updateLoadStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }

    public int getStatus() {
        return status;
    }
}
