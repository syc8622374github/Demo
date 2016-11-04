package phone.demo.com.library.util;

/**
 * @author cyc
 * @name phone.demo.com.library.util.varyview
 * @description
 * @date 2016/10/17 0017
 */

import android.content.Context;

import com.google.gson.Gson;

import phone.demo.com.library.util.converter.AvatarConverter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * retain retrofit singleton
 */
public class RetrofitUtils {
    /**
     * baseUrl end add "/"
     * path end no add "/"
     */
    private static Retrofit singleton;

    public static <T> T createApi(Context context, Class<T> clazz, String host) {
        if (singleton == null) {
            synchronized (RetrofitUtils.class) {
                if (singleton == null) {
                    Gson gson = new Gson();
                    singleton = new Retrofit.Builder()
                            .baseUrl(host)
                            .addConverterFactory(AvatarConverter.create(gson))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpUtils.getInstance(context))
                            .build();
                }
            }
        }
        return singleton.create(clazz);
    }

}
