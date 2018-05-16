package http.converter;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import http.ResponseResult;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by eagle on 2017-10-9 20:06
 */

public class ResponseConverterFactory extends Converter.Factory{
    public static final ResponseConverterFactory INSTANCE = new ResponseConverterFactory();
    public static ResponseConverterFactory create(){
        return INSTANCE;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == ResponseResult.class){
            return ResponseConverter.INSTANCE;
        }
        return null;
    }
}
