package http.interceptors;


import java.io.IOException;

import application.GlobalApplication;
import config.AppConfig;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import util.AesUtils;

/**
 * Created by eagle on 2017-10-9 21:23
 */

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        /*String credentials = ":";
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);*/
        String ip = GlobalApplication.getInstance().getIp();
        Request originalRequest = chain.request();
        String cacheControl = originalRequest.cacheControl().toString();
        Request.Builder requestBuilder = originalRequest.newBuilder() //Basic Authentication,也可用于token验证,OAuth验证
                .header("Authorization", "").header("Accept", "application/json")
                .addHeader("packageName", AppConfig.PACKAGE_NAME)
                .addHeader("ip",ip)
                .addHeader("os","Android").method(originalRequest.method(), originalRequest.body());
        if (originalRequest.body() instanceof FormBody) {
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) originalRequest.body();
            for (int i = 0; i < oldFormBody.size(); i++) {
                String name = oldFormBody.encodedName(i);
                String value = oldFormBody.value(i);
                if (name.equals(AppConfig.REQUEST_PARAM)) {
                    newFormBody.addEncoded(name, AesUtils.encode(value));
                } else {
                    newFormBody.addEncoded(name, value);
                }
            }
            requestBuilder.method(originalRequest.method(), newFormBody.build());
        }

        Request request = requestBuilder.build();
        Response originalResponse = chain.proceed(request);
        Response.Builder responseBuilder = //Cache control设置缓存
                originalResponse.newBuilder().header("Cache-Control", cacheControl);

        return responseBuilder.build();

    }
}
