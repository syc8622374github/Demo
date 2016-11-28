package phone.demo.com.demo.api;

import phone.demo.com.demo.module.cartoon.bean.ShowApiResponse;
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
    @GET("/958-1")
    //获取黑白漫画数据列表
    Observable<ShowApiResponse> getBAWCartoonListData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign, @Query("type") String type, @Query("page") int page);

    @GET("/958-2")
        //获取黑白漫画详细信息
    Observable<ShowApiResponse> getBAWCartoonDetailData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign, @Query("id") String id);


    /***
     * 内涵漫画
     ***/
    @GET("/978-2")
    //获取内涵漫画详细列表
    Observable<ShowApiResponse> getConnotationCartoonListData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign, @Query("page") int page);

    @GET("/978-1")
    //获取内涵漫画详细信息
    Observable<ShowApiResponse> getConnotationCartoonDetailData(@Query("showapi_appid") String appid, @Query("showapi_sign") String sign, @Query("id") String id);

}
