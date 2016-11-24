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
    private static Retrofit singletonHuaBanApi;

    public static <T> T createHuaBanApi(Context context, Class<T> clazz, String host) {
        if (singletonHuaBanApi == null) {
            synchronized (RetrofitUtils.class) {
                if (singletonHuaBanApi == null) {
                    Gson gson = new Gson();
                    singletonHuaBanApi = new Retrofit.Builder()
                            .baseUrl(host)
                            .addConverterFactory(AvatarConverter.create(gson))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpUtils.getInstance(context))
                            .build();
                }
            }
        }
        return singletonHuaBanApi.create(clazz);
    }


    private static Retrofit singletonShowApi;
    /**
     *
     * @param context
     * @param clazz
     * @param host
     * @param <T>
     * @return
     */
    public static <T> T createShowApi(Context context, Class<T> clazz, String host) {
        if (singletonShowApi == null) {
            synchronized (RetrofitUtils.class) {
                if (singletonShowApi == null) {
                    Gson gson = new Gson();
                    singletonShowApi = new Retrofit.Builder()
                            .baseUrl(host)
                            .addConverterFactory(AvatarConverter.create(gson))
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(OkHttpUtils.getInstance(context))
                            .build();
                }
            }
        }
        return singletonShowApi.create(clazz);
    }

}
