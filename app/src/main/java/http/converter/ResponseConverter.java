package http.converter;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import http.ResponseResult;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by eagle on 2017-10-9 20:04
 */

public class ResponseConverter implements Converter<ResponseBody, ResponseResult> {
    public static final ResponseConverter INSTANCE = new ResponseConverter();
    @Override
    public ResponseResult convert(ResponseBody value) throws IOException {
        return JSON.parseObject(value.string(),ResponseResult.class);
    }
}
