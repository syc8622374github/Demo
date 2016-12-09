package phone.demo.com.demo.module.huaban;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import phone.demo.com.demo.R;
import phone.demo.com.demo.adapter.RecyclerPinsHeadCardAdapter;
import phone.demo.com.demo.api.HuaBanApi;
import phone.demo.com.demo.module.huaban.bean.PinsDetailBean;
import phone.demo.com.demo.module.huaban.bean.PinsMainEntity;
import phone.demo.com.demo.util.AuthUtils;
import phone.demo.com.demo.util.CompatUtils;
import phone.demo.com.demo.util.Constant;
import phone.demo.com.demo.util.ImageLoadFresco;
import phone.demo.com.demo.util.TimeUtils;
import phone.demo.com.demo.widget.LoadingFooter;
import phone.demo.com.library.util.Logger;
import phone.demo.com.library.util.RetrofitUtils;
import phone.demo.com.library.view.AppDelegate;
import phone.demo.com.library.widget.myRecyclerView.HeaderAndFooterRecyclerViewAdapter;
import phone.demo.com.library.widget.myRecyclerView.RecyclerViewUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by cyc on 2016/12/9 0009.
 */

public class HuaBanImageDetailFDelegate extends AppDelegate {

    private String mKey;
    private int mMaxId = 0;
    private RecyclerView recyclerView;
    private RecyclerPinsHeadCardAdapter mAdapter;
    private LoadingFooter mFooterView;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private String mUserName;
    private String mBoardName;
    private String mUserId;
    private String mBoardId;
    private String mFormatUrlSmall;
    private String mHttpRoot;
    private TextView tv_image_text;
    private TextView tv_image_link;
    private TextView tv_image_gather;
    private TextView tv_image_like;
    private TextView tv_image_user;
    private TextView tv_image_time;
    private TextView tv_image_board;
    private SimpleDraweeView img_image_user;
    private SimpleDraweeView img_image_board_1;
    private SimpleDraweeView img_image_board_2;
    private SimpleDraweeView img_image_board_3;
    private SimpleDraweeView img_image_board_4;
    private ImageButton ibtn_image_board_chevron_right;
    private ImageButton ibtn_image_user_chevron_right;
    private RelativeLayout mRLImageUser;
    private RelativeLayout mRLImageBoard;
    String mStringLikeNumber;
    String mStringGatherNumber;
    String mStringNullDescribe;

    protected HuaBanImageDetailFDelegate(Fragment fragment) {
        super(fragment);
        getBundleData(fragment.getArguments());
        mFormatUrlSmall = fragment.getResources().getString(R.string.url_image_small);
        mHttpRoot = fragment.getResources().getString(R.string.httpRoot);
        mStringGatherNumber = fragment.getResources().getString(R.string.text_gather_number);
        mStringLikeNumber = fragment.getResources().getString(R.string.text_like_number);
        mStringNullDescribe = fragment.getResources().getString(R.string.text_image_describe_null);

    }

    private void getBundleData(Bundle arguments) {
        mKey = arguments.getString(Constant.TYPE_KEY);
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.common_list;
    }

    @Override
    public void initWidget() {
        super.initWidget();
        recyclerView = (RecyclerView) activity.findViewById(R.id.recycler_list);
    }

    @Override
    public void initData() {
        super.initData();
        initRecyclerView();
        getHttpOther();
        getHuaBanData();
    }

    private void initRecyclerView() {
        mAdapter = new RecyclerPinsHeadCardAdapter(recyclerView);
        HeaderAndFooterRecyclerViewAdapter headAdapter = new HeaderAndFooterRecyclerViewAdapter(mAdapter);
        mAdapter.setOnClickItemListener(new RecyclerPinsHeadCardAdapter.OnAdapterListener() {
            @Override
            public void onClickImage(PinsMainEntity bean, View view) {
                Intent intent = new Intent(context, HuaBanImageDetailActivity.class);
                intent.putExtra("transition_bundle", bean.getFile().getKey());
                intent.putExtra("image_url", bean.getLink());
                context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view,
                        bean.getFile().getKey()).toBundle());
            }

            @Override
            public void onClickTitleInfo(PinsMainEntity bean, View view) {

            }

            @Override
            public void onClickInfoGather(PinsMainEntity bean, View view) {

            }

            @Override
            public void onClickInfoLike(PinsMainEntity bean, View view) {

            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(headAdapter);
        //绑定能添加头尾View的adapter后 检查View返回 添加
        if (getHeadView() != null) {
            RecyclerViewUtils.addHearView(recyclerView, getHeadView());
        }
        if (getFootView() != null) {
            RecyclerViewUtils.addFootView(recyclerView, getFootView());
        }
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    //滑动停止
                    boolean isBottom = staggeredGridLayoutManager
                            .findLastCompletelyVisibleItemPositions(new int[2])[1] >= mAdapter.getItemCount();
                    if (isBottom) {
                        getHuaBanData();
                    }
                } else if (RecyclerView.SCROLL_STATE_DRAGGING == newState) {
                    //用户正在滑动
//                    Logger.d("用户正在滑动 position=" + mAdapter.getAdapterPosition());
                } else {
                    //惯性滑动
//                    Logger.d("惯性滑动 position=" + mAdapter.getAdapterPosition());
                }
            }
        });
    }

    protected View getHeadView() {

        View headView = View.inflate(context,R.layout.view_image_detail_info_head, null);
        findHeadView(headView);
        return headView;
    }

    private void findHeadView(View headView) {
        tv_image_text = (TextView) headView.findViewById(R.id.tv_image_text);
        tv_image_link = (TextView) headView.findViewById(R.id.tv_image_link);
        tv_image_gather = (TextView) headView.findViewById(R.id.tv_image_gather);
        tv_image_like = (TextView) headView.findViewById(R.id.tv_image_like);
        tv_image_user = (TextView) headView.findViewById(R.id.tv_image_user);
        tv_image_time = (TextView) headView.findViewById(R.id.tv_image_about);
        tv_image_board = (TextView) headView.findViewById(R.id.tv_image_board);

        img_image_user = (SimpleDraweeView) headView.findViewById(R.id.img_image_user);
        img_image_board_1 = (SimpleDraweeView) headView.findViewById(R.id.img_image_board_1);
        img_image_board_2 = (SimpleDraweeView) headView.findViewById(R.id.img_image_board_2);
        img_image_board_3 = (SimpleDraweeView) headView.findViewById(R.id.img_image_board_3);
        img_image_board_4 = (SimpleDraweeView) headView.findViewById(R.id.img_image_board_4);

        ibtn_image_board_chevron_right = (ImageButton) headView.findViewById(R.id.ibtn_image_board_chevron_right);
        ibtn_image_user_chevron_right = (ImageButton) headView.findViewById(R.id.ibtn_image_user_chevron_right);

        mRLImageUser = (RelativeLayout) headView.findViewById(R.id.relativelayout_image_user);
        mRLImageBoard = (RelativeLayout) headView.findViewById(R.id.relativelayout_image_board);
    }

    protected View getFootView() {
        if (mFooterView == null) {
            mFooterView = new LoadingFooter(fragment.getActivity());
            mFooterView.setState(LoadingFooter.State.Loading);
        }
        return mFooterView;
    }

    /**
     * 获取详情
     */
    protected void getHttpOther() {
        RetrofitUtils.createHuaBanApi(context,HuaBanApi.class,HuaBanApi.api)
                .httpsPinsDetailRx(AuthUtils.getAuthorizations(), mKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PinsDetailBean>() {
                    @Override
                    public void onCompleted() {
                        Logger.d();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e);
                        //网络错误 使用本地缓存
                    }

                    @Override
                    public void onNext(PinsDetailBean pinsDetailBean) {
                        Logger.d();
                        //联网成功使用 正确的网络数据
                        setImageInfo(pinsDetailBean);
                    }
                });

    }

    /**
     * 使用网络数据填充UI控件 赋值全局变量 填充UI控件
     *
     * @param pinsDetailBean 网络bean
     */
    private void setImageInfo(PinsDetailBean pinsDetailBean) {
        PinsDetailBean.PinBean bean = pinsDetailBean.getPin();

        mBoardId = String.valueOf(bean.getBoard_id());
        mUserId = String.valueOf(bean.getUser_id());
        mBoardName = bean.getBoard().getTitle();
        mUserName = bean.getUser().getUrlname();

        //描述
        setImageTextInfo(bean.getRaw_text(),
                bean.getLink(),
                bean.getSource(),
                bean.getRepin_count(),
                bean.getLike_count()
        );

        //用户信息
        String url = bean.getUser().getAvatar();

        setImageUserInfo(url,
                bean.getUser().getUsername(),
                bean.getCreated_at()
        );

        //画板信息
        String url1 = String.format(mFormatUrlSmall, bean.getBoard().getPins().get(0).getFile().getKey());
        String url2 = String.format(mFormatUrlSmall, bean.getBoard().getPins().get(1).getFile().getKey());
        String url3 = String.format(mFormatUrlSmall, bean.getBoard().getPins().get(2).getFile().getKey());
        String url4 = String.format(mFormatUrlSmall, bean.getBoard().getPins().get(3).getFile().getKey());
        setImageBoardInfo(url1, url2, url3, url4, bean.getBoard().getTitle());
    }

    //图像文字信息 填充
    private void setImageTextInfo(String raw, String link, String source, int gather, int like) {
        if (!TextUtils.isEmpty(raw)) {
            tv_image_text.setText(raw);
        } else {
            tv_image_text.setText(mStringNullDescribe);
        }

        if ((!TextUtils.isEmpty(link)) && (!TextUtils.isEmpty(source))) {
            tv_image_link.setText(source);
            tv_image_link.setTag(link);
        } else {
            tv_image_link.setVisibility(View.GONE);
        }

        tv_image_gather.setText(String.format(mStringGatherNumber, gather));
        setTvImageLikeNumber(like);
    }

    private void setTvImageLikeNumber(int like) {
        tv_image_like.setText(String.format(mStringLikeNumber, like));
    }

    //图像的用户信息 填充
    private void setImageUserInfo(String url_head, String username, int created_time) {
        //因为图片来源不定 需要做处理
        if (url_head != null) {
            if (!url_head.contains(mHttpRoot)) {
                url_head = String.format(mFormatUrlSmall, url_head);
            }
            //用户名头像加载
            new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_user, url_head)
                    .setPlaceHolderImage(CompatUtils.getTintDrawable(context, R.drawable.ic_account_circle_gray_48dp, Color.GRAY))
                    .setIsCircle(true)
                    .build();
        }

        //用户名
        tv_image_user.setText(username);
        //创建时间
        tv_image_time.setText(TimeUtils.getTimeDifference(created_time, System.currentTimeMillis()));
    }

    private void setImageBoardInfo(String url1, String url2, String url3, String url4, String board_name) {
        //画板名称
        if (!TextUtils.isEmpty(board_name)) {
            tv_image_board.setText(board_name);
        } else {
            tv_image_board.setText("暂无画板信息");
        }

        new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_board_1, url1)
                .setIsRadius(true, 5)
                .build();
//        Logger.d("id1=" + img_image_board_1.getId());
        new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_board_2, url2)
                .setIsRadius(true, 5)
                .build();
//        Logger.d("id2=" + img_image_board_2.getId());
        new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_board_3, url3)
                .setIsRadius(true, 5)
                .build();
//        Logger.d("id3=" + img_image_board_3.getId());
        new ImageLoadFresco.LoadImageFrescoBuilder(context, img_image_board_4, url4)
                .setIsRadius(true, 5)
                .build();
//        Logger.d("id4=" + img_image_board_4.getId());

    }

    /**
     * 获取推荐详情
     */
    public void getHuaBanData() {
        RetrofitUtils.createHuaBanApi(context, HuaBanApi.class, HuaBanApi.api)
                .httpPinsRecommendRx(AuthUtils.getAuthorizations(), mKey, mMaxId, Constant.LIMIT)
                .filter(new Func1<List<PinsMainEntity>, Boolean>() {
                    @Override
                    public Boolean call(List<PinsMainEntity> pinsMainEntities) {
                        if(pinsMainEntities.size()>0){
                            return true;
                        }
                        return false;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<PinsMainEntity>>() {
                    @Override
                    public void onCompleted() {
                        Logger.d();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e);
                        //checkException(e);//检查错误 弹出提示
                    }

                    @Override
                    public void onNext(List<PinsMainEntity> pinsEntities) {
                        Logger.d();
                        mMaxId = getMaxId(pinsEntities);
                        mAdapter.addListNotify(pinsEntities);
                    }
                });
    }

    /**
     * 从返回联网结果中保存max值 用于下次联网的关键
     *
     * @param result
     * @return
     */
    private int getMaxId(List<PinsMainEntity> result) {
        return result.get(result.size() - 1).getPin_id();
    }
}
