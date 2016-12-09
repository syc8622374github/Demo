package phone.demo.com.demo.api;

import java.util.List;

import phone.demo.com.demo.module.huaban.bean.ListPinsBean;
import phone.demo.com.demo.module.huaban.bean.PinsDetailBean;
import phone.demo.com.demo.module.huaban.bean.PinsMainEntity;
import phone.demo.com.demo.util.Constant;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by cyc on 2016/11/3 0003.
 */

public interface HuaBanApi {
    String api = "https://api.huaban.com/";

    //https//api.huaban.com/favorite/food_drink?limit=20
    // 模板类型
    @GET("favorite/{type}")
    Observable<ListPinsBean> httpsTypeLimitRx(@Header(Constant.Authorization) String authorization, @Path("type") String type, @Query("limit") int limit);

    //https//api.huaban.com/favorite/food_drink?max=5445324325&limit=20
    //模板类型 的后续联网 max
    @GET("favorite/{type}")
    Observable<ListPinsBean> httpsTypeMaxLimitRx(@Header(Constant.Authorization) String authorization, @Path("type") String type, @Query("max") int max, @Query("limit") int limit);

    //https://api.huaban.com/pins/663478425
    //根据图片id获取详情
    @GET("pins/{pinsId}")
    Observable<PinsDetailBean> httpsPinsDetailRx(@Header(Constant.Authorization) String authorization, @Path("pinsId") String pinsId);

    //https//api.huaban.com/pins/654197326/recommend/?page=1&limit=40
    //获取某个图片的推荐图片列表
    @GET("pins/{pinsId}/recommend/")
    Observable<List<PinsMainEntity>> httpPinsRecommendRx(@Header(Constant.Authorization) String authorization, @Path("pinsId") String pinsId, @Query("page") int page, @Query("limit") int limit);

}
