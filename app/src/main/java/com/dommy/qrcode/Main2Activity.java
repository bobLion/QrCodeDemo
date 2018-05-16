package com.dommy.qrcode;

import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import database.greendao.SQLConnect;

public class Main2Activity extends AppCompatActivity {

    private Button selectButton;
    private Button insertButton;
    private TextView textView;
    private Handler handler;
    private String SelectResult[] =new String[9];
    private boolean InsertResult = false;
    private String ipString;
    private String keyString;
    private String tmbh = "03900540";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //ip与MSSQL sa用户登陆密码
//        ipString = "192.168.0.104";
        ipString = "172.20.25.153";
        keyString = "bobadmin";
        textView = (TextView) findViewById(R.id.text_View);
        selectButton = (Button) findViewById(R.id.selectbt);
        selectButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                testSelect();
            }
        });
        insertButton = (Button) findViewById(R.id.insertbt);
        insertButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                testInsert();
            }
        });
        handler = new Handler();
    }
    private void testSelect() {
        Runnable run = new Runnable() {
            public void run() {
                SQLConnect dbTest = new SQLConnect(ipString, keyString);
                dbTest.CreateLink();
//                SelectResult = dbTest.select(tmbh);
                dbTest.close();
                handler.post(new Runnable(){
                    public void run() {
                        textView.setText("车牌号码:"+SelectResult[1]+
                                "\n车辆载重:"+SelectResult[2]+"\n是否检入:"+
                                SelectResult[3]+"\n检入时间:"+SelectResult[4]+
                                "\n检出时间:"+SelectResult[5]+"\n驾驶人姓名:"+SelectResult[6]+
                        "\n车辆单位:"+SelectResult[7]+"\n条码编号:"+SelectResult[8]);
                    }
                });
            }
        };
        new Thread(run).start();
    }
    private void testInsert() {
        Runnable run = new Runnable() {
            public void run() {
                SQLConnect dbTest = new SQLConnect(ipString, keyString);
                dbTest.CreateLink();
//                InsertResult = dbTest.ins_upd_del();
                dbTest.close();
                handler.post(new Runnable(){
                    public void run() {
                        if(InsertResult){
                            Toast.makeText(Main2Activity.this, "Insert Successful",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Main2Activity.this, "Insert Failed！",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
        new Thread(run).start();
    }
}
