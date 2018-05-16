package http;

/**
 * Created by eagle on 2017-10-9 19:32
 */

public class ResponseResult {
    public static String SUCCEED_CODE = "1000";

    public static String SERVER_ERROR_CODE = "9999";

    public static String PARAMS_ERROR_CODE = "2001";

    public static String VERIFY_ERROR_CODE = "3001";

    public static String DATA_EMPTY_CODE = "4001";
    public static String LOAD_NO_MORE = "6001";

    public static String PASSWORD_ERROR_CODE = "5001";


    private String responseCode;
    private String message;
    private String content;

    public ResponseResult() {
    }

    public ResponseResult(String responseCode, String message, String content) {
        this.responseCode = responseCode;
        this.message = message;
        this.content = content;
    }

    public ResponseResult(String responseCode, String content) {
        this.responseCode = responseCode;
        this.content = content;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
