package application.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import config.AppConfig;

/**
 * Created by tangyy on 2017/12/22 0022.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = "UncaughtException";
    private static CrashHandler instance;
    private Context mContext;
    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    // 单例
    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    private CrashHandler() {
    }

    // 初始化
    public void init(Context context) {
        this.mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : " + e);
            }
            //退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

    }

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        //在此可执行其它操作，如获取设备信息、执行异常登出请求、保存错误日志到本地或发送至服务端
//将异常写入本地文件
        writeContentToFile(ex);
        //将异常文件上传
        return true;
    }


    private void writeContentToFile(Throwable ex) {
        try {
            File file = new File(AppConfig.getUncaughtExceptionPath(), "log" + ".trace");
            if (!file.exists()) {
                file.createNewFile();
            }
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));

            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file,false)));
            pw.println("异常时间："+time);
            recordPhoneInfo(pw);
            ex.printStackTrace(pw);
            pw.println();
            pw.close();

            Log.e(TAG, "UncaughtException写入成功: " + ex.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void recordPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException {
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi=pm.getPackageInfo(mContext.getPackageName(), PackageManager.GET_ACTIVITIES);
        pw.print("应用版本: ");
        pw.print(pi.versionName);
        pw.print("_");
        pw.println(pi.versionCode);
        //安卓版本号
        pw.print("安卓版本: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);
        //手机制造商
        pw.print("制造厂家: ");
        pw.println(Build.MANUFACTURER);
        //手机型号
        pw.print("手机型号: ");
        pw.println(Build.MODEL);
        //手机CPU架构
        pw.print("CPU架构: ");
        pw.println(Build.CPU_ABI);

    }
}
