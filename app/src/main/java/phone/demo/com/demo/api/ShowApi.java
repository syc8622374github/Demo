package phone.demo.com.demo.api;

import phone.demo.com.demo.module.cartoon.bean.CartoonResBody;
import phone.demo.com.demo.module.cartoon.bean.ShowApiResponse;
import phone.demo.com.demo.module.news.bean.NewsChannelResBody;
import phone.demo.com.demo.module.news.bean.NewsResBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cyc on 2016/11/24 0024.
 */

public interface ShowApi {
    //showapi
    String API = "http://route.showapi.com";

    /***
     * 黑白漫画
     ***/
    //获取黑白漫画数据列表
    @GET("/958-1")
    Observable<ShowApiResponse<CartoonResBody>> getBAWCartoonListData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign, @Query("type") String type, @Query("page") int page);

    //获取黑白漫画详细信息
    @GET("/958-2")
    Observable<ShowApiResponse<CartoonResBody>> getBAWCartoonDetailData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign, @Query("id") String id);


    /***
     * 内涵漫画
     ***/
    //获取内涵漫画详细列表
    @GET("/978-2")
    Observable<ShowApiResponse<CartoonResBody>> getConnotationCartoonListData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign, @Query("page") int page);

    //获取内涵漫画详细信息
    @GET("/978-1")
    Observable<ShowApiResponse<CartoonResBody>> getConnotationCartoonDetailData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign, @Query("id") String id);



    /***
     * 新闻api接口
     ***/
    //新闻频道
    @GET("/109-34")
    Observable<ShowApiResponse<NewsChannelResBody>> getNewsChannelData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign);

    //新闻查询
    @GET("/109-35")
    Observable<ShowApiResponse<NewsResBody>> getNewData(@Query("showapi_appid") String appid,
                                                        @Query("showapi_sign") String sign,
                                                        @Query("channelId") String channelId,
                                                        @Query("channelName") String channelName,
                                                        @Query("title") String title,
                                                        @Query("page") String page,
                                                        @Query("needAllList") String needAllList,
                                                        @Query("maxResult") String maxResult);

}
