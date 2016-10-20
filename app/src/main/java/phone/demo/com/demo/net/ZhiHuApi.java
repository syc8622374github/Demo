package phone.demo.com.demo.net;

import phone.demo.com.demo.bean.News;
import phone.demo.com.demo.bean.NewsTimeLine;
import phone.demo.com.demo.bean.SplashImage;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author cyc
 * @name phone.demo.com.demo
 * @description
 * @date 2016/10/19 0019
 */
public interface ZhihuApi {


    @GET("start-image/1080*1920")
    Observable<SplashImage> getSplashImage();

    @GET("news/latest")
    Observable<NewsTimeLine> getLatestNews();

    @GET("news/before/{time}")
    Observable<NewsTimeLine> getBeforetNews(@Path("time") String time);

    @GET("news/{id}")
    Observable<News> getDetailNews(@Path("id") String id);
}
