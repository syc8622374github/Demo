package phone.demo.com.library.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

/**
 * @author cyc
 * @name phone.demo.com.library.util
 * @description
 * @date 2016/10/17 0017
 * gson singleton
 */
public enum GsonUtils {
    INSTANCE;

    private final Gson gson;

    GsonUtils() {
        GsonBuilder builder = new GsonBuilder();
        builder
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .enableComplexMapKeySerialization()
                //                .registerTypeAdapter(Response.class, new MyCommonGsonAdapter());
                .registerTypeAdapter(Date.class, new DateTypeAdapter());
        gson = builder.create();
    }

    public Gson get() {
        return gson;
    }

}
