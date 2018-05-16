package http;


import java.util.Map;

import config.AppConfig;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

/**
 * Created by eagle on 2017-10-9 15:06
 */

public interface RestService {

    /**
     * 用户登录
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/login/userLogin")
    Call<ResponseResult> login(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 获取渣土车预警数
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/alarm/count")
    Call<ResponseResult> countAlarm(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 获取渣土车预警信息
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/alarm/listVehicleAlarm")
    Call<ResponseResult> listVehicleAlarm(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     *  获取今日各时段过车统计
     *@parem jsonParam
     *@return
     */
    @FormUrlEncoded
    @POST("/app/rank/listStatistInterval")
    Call<ResponseResult> listTodayVehicle(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     *  获取今日卡口过车数量TOP10
     *@parem jsonParam
     *@return
     */
    @FormUrlEncoded
    @POST("/app/rank/listVehicleRank")
    Call<ResponseResult> listTopTenBayonet(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     *  获取今日过车数量各省份分布
     *@parem jsonParam
     *@return
     */
    @FormUrlEncoded
    @POST("/app/rank/listPorviceRatio")
    Call<ResponseResult> listProvinceVehicleDistribute(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 关键字搜索
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/search/listVehicleSimpleSearch")
    Call<ResponseResult> listVehicleSimpleSearch(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 获取渣土车排名信息
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/slagCarRanking/listRranking")
    Call<ResponseResult> listSlagCarRanking(@Field(AppConfig.REQUEST_PARAM) String jsonParam);


    /**
     * 获取最新的过车记录
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/search/getLatestPassVehicle")
    Call<ResponseResult> getLatestPassVehicle(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 获取渣土车排名信息
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/slagCarRanking/listVehicleRranking")
    Call<ResponseResult> listVehicleCarRanking(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 高级搜索
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/search/listVehicleDetailSearch")
    Call<ResponseResult> listVehicleDetailSearch(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 请求应用APK版本号
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/version/getVersion")
    Call<ResponseResult> getVersion(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 下载应用apk
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/appFile/appPackage/download")
    Call<ResponseBody> downloadApp(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
      * 下载doc文档
     * @param jsonParam
     * @return
             */
    @FormUrlEncoded
    @POST("/appFile/document/download")
    Call<ResponseBody> downloadDoc(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     *获取卡口实时过车数据
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/search/getPassVehicle")
    Call<ResponseResult> getRealTimePassVehicle(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 获取指定卡口今日过车数据
     * **/
    @FormUrlEncoded
    @POST("app/rank/getStatisticsByCode")
    Call<ResponseResult> getByonetVehicleData(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 修改密码
     * **/
    @FormUrlEncoded
    @POST("/app/login/updatePassWord")
    Call<ResponseResult> updatePassWord(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 查看车辆轨迹
     * **/
    @FormUrlEncoded
    @POST("/app/trailSearch/vehicleTrailSearch")
    Call<ResponseResult> vehicleTrailSearch(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 获取卡口所在区域
     * **/
    @FormUrlEncoded
    @POST("/app/search/getAllBayonetArea")
    Call<ResponseResult> getAllBayonetArea(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 获取卡口
     * **/
    @FormUrlEncoded
    @POST("/app/search/getBayonet")
    Call<ResponseResult> getBayonet(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
    /**
     * 请求数据库版本号
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/version/getEquipmentDBVersion")
    Call<ResponseResult> getEquipmentDBVersion(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 下载应用数据库
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/appFile/appEquipment/download")
    Call<ResponseBody> downloadDB(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 以图搜车上传图片
     * @param params
     * @return
     */
    @Multipart
    @POST("/app/queryImage/uploadPic")
    Call<ResponseResult> uploadImage(@PartMap Map<String, RequestBody> params);

    /**
     * 消息推送
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/message/listMessgePush")
    Call<ResponseResult> listMessgePush(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 获取特征库
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/queryImage/listRepositoryId")
    Call<ResponseResult> listRepositoryId(@Field(AppConfig.REQUEST_PARAM) String jsonParam);

    /**
     * 以图搜车
     * @param jsonParam
     * @return
     */
    @FormUrlEncoded
    @POST("/app/queryImage/searchVehicle")
    Call<ResponseResult> searchVehicle(@Field(AppConfig.REQUEST_PARAM) String jsonParam);
}
