package activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dommy.qrcode.R;
import com.dommy.qrcode.util.Constant;
import com.google.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.VehicleAdapter;
import database.greendao.SQLConnect;
import entity.VehicleEntity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnQrCode; // 扫码
    TextView tvResult; // 结果
    Button btnSelect;//select
    TextView tvSelectResult;
    ListView mLvVehicle;
    private Handler handler;
    private boolean InsertResult = false;
    private String ipString;
    private String keyString;
    private String qrcode;
    private VehicleAdapter adapter;
    private Context mContext;
    private List<VehicleEntity> vehicleEntityList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //ip与MSSQL sa用户登陆密码
//        ipString = "172.20.25.153";
        ipString = "192.168.0.104";
        keyString = "bobadmin";
        tvSelectResult = (TextView) findViewById(R.id.select_result);
        btnSelect = (Button) findViewById(R.id.btn_select);
        btnSelect.setOnClickListener(this);
        handler = new Handler();
        initView();
    }

    private void initView() {
        btnQrCode = (Button) findViewById(R.id.btn_qrcode);
        btnQrCode.setOnClickListener(this);
        tvResult = (TextView) findViewById(R.id.txt_result);
        mLvVehicle = (ListView) findViewById(R.id.lv_vehicle);
    }

    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_qrcode:
                startQrCode();
                break;
            case R.id.btn_select:
                VehicleEntity vehicleEntity = new VehicleEntity();
                vehicleEntity.setQrCode(qrcode).setCheckInDate("20180509").setCheckOutDate("20180512")
                        .setDriverName("简约").setIsCheck(0).setVehicleDepartment("上海市政局").setVehicleWeight("12吨")
                        .setVehicleNum("沪B23112");
                testInsert(vehicleEntity);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            //将扫描出的信息显示出来
            qrcode = scanResult.trim();
            tvResult.setText(qrcode);
            testSelect(qrcode);
        }
    }
    private void testSelect(final String qrCode) {
        Runnable run = new Runnable() {
            public void run() {
                SQLConnect dbTest = new SQLConnect(ipString, keyString);
                dbTest.CreateLink();
                vehicleEntityList = dbTest.select(qrCode);
                dbTest.close();
                handler.post(new Runnable(){
                    public void run() {
                        tvSelectResult.setText("查到数据为："+ vehicleEntityList.size());
                        if(vehicleEntityList.size() != 0){
                            adapter = new VehicleAdapter(mContext,vehicleEntityList);
                            mLvVehicle.setAdapter(adapter);
                        }else{
                            vehicleEntityList.clear();
                            if(null != adapter){
                                adapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
            }
        };
        new Thread(run).start();
    }
    private void testInsert(final VehicleEntity vehicleEntity) {
        Runnable run = new Runnable() {
            public void run() {
                SQLConnect dbTest = new SQLConnect(ipString, keyString);
                dbTest.CreateLink();
                InsertResult = dbTest.ins_upd_del(vehicleEntity);
                dbTest.close();
                handler.post(new Runnable(){
                    public void run() {
                        if(InsertResult){
                            Toast.makeText(MainActivity.this, "Insert Successful",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Insert Failed！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        new Thread(run).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(MainActivity.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
