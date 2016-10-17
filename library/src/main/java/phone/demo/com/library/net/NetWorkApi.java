package phone.demo.com.library.net;

import phone.demo.com.library.bean.FreshEvent;
import phone.demo.com.library.bean.ImageDetail;
import phone.demo.com.library.net.response.FreshEventDetailResponse;
import phone.demo.com.library.net.response.FreshEventResponse;
import phone.demo.com.library.net.response.ImageListResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author cyc
 * @name phone.demo.com.library.net
 * @description
 * @date 2016/10/17 0017
 */
public interface NetWorkApi {

    int PAGE_LIMIT = 200;

    /**
     * 新鲜事列表
     */
    @GET("http://jandan.net/?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1")
    Observable<FreshEventResponse<FreshEvent>> getFreshList(@Query("page") int startIndex);

    /**
     * 新鲜事详情
     */
    @GET("http://jandan.net/?oxwlxojflwblxbsapi=get_post&include=content&id=73580?oxwlxojflwblxbsapi=get_post&include=content")
    Observable<FreshEventDetailResponse> getFreshDetail(@Query("id") int id);

    /**
     * 图片列表
     */
    @GET("http://image.baidu.com/data/imgs?tag=全部&rn="+PAGE_LIMIT+"&from=1")
    Observable<ImageListResponse<ImageDetail>> getImageList(@Query("col") String classify, @Query("pn") int startIndex);

}
