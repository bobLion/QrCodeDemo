package http;


import java.util.concurrent.TimeUnit;

import config.AppConfig;
import http.converter.ResponseConverterFactory;
import http.interceptors.BaseInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by eagle on 2017-10-9 21:10
 */

public class RestCreator {
    /**
     * 构建全局Retrolfit
     */
    private static final class RetrolfitHolder {
        private static final Retrofit RESTROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .client(OKHttpHolder.OK_HTTP_HOLDER)
                .addConverterFactory(ResponseConverterFactory.create())
                .build();
    }

    /**
     * 构建全局Okhttp
     */
    private static final class OKHttpHolder {
        static BaseInterceptor interceptor = new BaseInterceptor();
        private static final OkHttpClient OK_HTTP_HOLDER = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrolfitHolder.RESTROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }
}
