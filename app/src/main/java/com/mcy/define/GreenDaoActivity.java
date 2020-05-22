package com.mcy.define;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = MainActivity.class.getSimpleName();

    MeiziDaoUtils mMeiziDaoUtils;
    private TextView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        gson();

        findViewById(R.id.btn_insert_one).setOnClickListener(this);
        findViewById(R.id.btn_insert_many).setOnClickListener(this);
        findViewById(R.id.btn_alter).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_delete_all).setOnClickListener(this);
        findViewById(R.id.btn_check_one).setOnClickListener(this);
        findViewById(R.id.btn_check_all).setOnClickListener(this);
        findViewById(R.id.btn_query_native_sql).setOnClickListener(this);
        findViewById(R.id.btn_query_builder).setOnClickListener(this);
        mView = findViewById(R.id.tvShow);
        mMeiziDaoUtils = new MeiziDaoUtils(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_insert_one:
                mMeiziDaoUtils.insertMeiZi(new MeiZi(null, "Google",
                        "http://7xi8d6.48096_n.jpg"));
                break;

            case R.id.btn_insert_many:
                List<MeiZi> meiziList = new ArrayList<>();
                meiziList.add(new MeiZi(null, "HuaWei",
                        "http://7xi8d648096_n.jpg"));
                meiziList.add(new MeiZi(null, "Apple",
                        "http://7xi8d648096_n.jpg"));
                meiziList.add(new MeiZi(null, "MIUI",
                        "http://7xi8d648096_n.jpg"));
                mMeiziDaoUtils.insertMultMeiZi(meiziList);
                break;

            case R.id.btn_alter:
                MeiZi meizi = new MeiZi();
                meizi.set_id(1l);
                meizi.setSource("BAIDU");
                meizi.setUrl("http://baidu.jpg");
                mMeiziDaoUtils.updateMeizi(meizi);
                break;

            case R.id.btn_delete:
                MeiZi meizi1 = new MeiZi();
                meizi1.set_id(1l);
                boolean b = mMeiziDaoUtils.deleteMeizi(meizi1);
                mView.setText("delete: " + b);
                break;

            case R.id.btn_delete_all:
                boolean b1 = mMeiziDaoUtils.deleteAll();
                mView.setText("deleteALL: " + b1);
                break;

            case R.id.btn_check_one:
                MeiZi meiZi = mMeiziDaoUtils.queryMeiziById(2l);
                mView.setText(meiZi != null ? meiZi.toString() : "null");
                break;

            case R.id.btn_check_all:
                List<MeiZi> meiziList1 = mMeiziDaoUtils.queryAllMeizi();
                mView.setText(meiziList1.toString());
                break;

            case R.id.btn_query_native_sql:
                String sql = "where _id > ?";
                String[] condition = new String[]{"2"};
                List<MeiZi> meiziList2 = mMeiziDaoUtils.queryMeiziByNativeSql(sql, condition);
                mView.setText(meiziList2.toString());
                break;

            case R.id.btn_query_builder:
                List<MeiZi> meiziList3 = mMeiziDaoUtils.queryMeiziByQueryBuilder(10);
                mView.setText(meiziList3.toString());
                break;
        }
    }

    private void gson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobile", "122");
        jsonObject.addProperty("smsmode", "122");
        String string = jsonObject.toString();
        Log.d("macy777", " gson---> " + string);
        String tojson = new Gson().toJson(jsonObject);
        Log.d("macy7", " xxxxx " + tojson);
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("mobile", "111");
            jsonObject1.put("smsmode", "aaa");
            String s = jsonObject1.toString();
            Log.d("macy7", "ssss---> " + s);
            String s1 = new Gson().toJson(jsonObject1);
            Log.d("macy7", "sss111s---> " + s1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
