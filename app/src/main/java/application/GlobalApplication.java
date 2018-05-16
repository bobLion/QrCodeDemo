package application;

import android.app.Application;

import com.dommy.qrcode.R;
import com.rey.material.app.ThemeManager;
import org.greenrobot.greendao.database.Database;

import application.crash.CrashHandler;
import config.AppConfig;
//import database.greendao.DaoMaster;
//import database.greendao.DaoSession;
import entity.UserInfoEntity;
import util.NetworkUtils;

//import com.sailing.android.bayonet.database.greendao.VehicleBrandMaster;
//import com.sailing.android.bayonet.database.greendao.VehicleBrandSession;

/**
 * Created by eagle on 2017-9-15 17:27
 */

public class GlobalApplication extends Application {
    private static GlobalApplication application;
    public static GlobalApplication getInstance() {
        return application;
    }
    private UserInfoEntity userInfoEntity;

    public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }
    /*private static DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoSession brandDaoSession;*/


  /*  public DaoSession getDaoSession() {
        return daoSession;
    }
    public DaoSession getBrandDaoSession() {
        return brandDaoSession;
    }*/


    public UserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    @Override
    public void onCreate() {
        // 应用程序入口处调用，避免手机内存过小，杀死后台进程后通过历史intent进入Activity造成SpeechUtility对象为null
        // 如在Application中调用初始化，需要在Mainifest中注册该Applicaiton
        // 注意：此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，请增加参数：SpeechConstant.FORCE_LOGIN+"=true"
        // 参数间使用半角“,”分隔。
        // 设置你申请的应用appid,请勿在'='与appid之间添加空格及空转义符

        // 注意： appid 必须和下载的SDK保持一致，否则会出现10407错误
//        SpeechUtility.createUtility(this, "appid=" + getString(R.string.app_id));

        // 以下语句用于设置日志开关（默认开启），设置成false时关闭语音云SDK日志打印
        // Setting.setShowLog(false);
        super.onCreate();
        application = this;
//        initDataBase();
        //material  UI组件
        ThemeManager.init(this, 2, 0, null);
        //捕获异常
        CrashHandler.getInstance().init(this);
    }

   /* private void initDataBase(){
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), AppConfig.APP_DATABASE_NAME);
        Database database = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();

        DaoMaster.DevOpenHelper brandOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), AppConfig.VEHICLE_BRAND_DATABASE_NAME);
        Database brandDatabase = brandOpenHelper.getWritableDb();
        DaoMaster brandDaoMaster = new DaoMaster(brandDatabase);
        brandDaoSession = brandDaoMaster.newSession();
    }*/

    public String getIp() {
        return NetworkUtils.getIPAddress(getApplicationContext());
    }
}