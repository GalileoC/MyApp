package com.example.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppActivity extends Activity implements SearchView.OnQueryTextListener{
    private ListView lv;
    private SearchView sv;
    private long exitTime = 0;
    private List<Map<String, Object>> data;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        lv = findViewById(R.id.list1);
        sv = findViewById(R.id.search1);

        data = new ArrayList<Map<String, Object>>();

        getData(data);

        lv.setAdapter(new SimpleAdapter(this, data, R.layout.item_list, new String[]{"AppName", "icon"}, new int[]{R.id.tv1, R.id.iv}));

        lv.setTextFilterEnabled(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> map1 = (HashMap<String, Object>) parent.getItemAtPosition(position);
                String appName = String.valueOf(map1.get("AppName"));

                AlertDialog.Builder dialog = new AlertDialog.Builder(parent.getContext());

                dialog.setMessage("AppName: " + appName + "\n" +
                        "StartTime: " + appName + "\n" +
                        "EndTime: " + appName + "\n" +
                        "Duration: " + appName + "\n" +
                        "BatteryUsage: " + appName);
                dialog.setPositiveButton("确认", null);

                AlertDialog dialogShow = dialog.create();
                dialogShow.show();
            }
        });

        sv.setOnQueryTextListener(this);
        sv.setSubmitButtonEnabled(false);

        bt = findViewById(R.id.btn2);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppActivity.this,PredictActivity.class);
                startActivity(intent);
            }
        });

    }



    public void getData(List<Map<String, Object>> data){
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("AppName", "App1");
        map1.put("icon", R.mipmap.ic_launcher);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("AppName", "App2");
        map2.put("icon", R.mipmap.ic_launcher);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("AppName", "App3");
        map3.put("icon", R.mipmap.ic_launcher);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("AppName", "App4");
        map4.put("icon", R.mipmap.ic_launcher);
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("AppName", "App5");
        map5.put("icon", R.mipmap.ic_launcher);

        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);

        return;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<Map<String, Object>> newdata = searchItem(newText);
        updateLayout(newdata);
        return false;
    }

    public List<Map<String, Object>> searchItem(String name) {
        List<Map<String, Object>> newdata = new ArrayList<Map<String, Object>>();;
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < data.size(); i++){
            map = data.get(i);
            if (map.get("AppName").equals(name)){
                newdata.add(map);
            } else if(name.equals("")){
                newdata=data;
            }
        }
        return newdata;
    }


    public void updateLayout(List<Map<String, Object>> newdata) {
        lv.setAdapter(new SimpleAdapter(getApplicationContext(), newdata, R.layout.item_list, new String[]{"AppName", "icon"}, new int[]{R.id.tv1, R.id.iv}));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(AppActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}






































//        ListView listView = findViewById(R.id.list1);
//        String[] ctype = new String[]{"App1", "App2", "App3", "App4", "App5", "App6"
//                , "App7", "App8", "App9", "App10", "App11", "App12", "App13", "App14"
//                , "App15", "App16"};
//        t1_out = findViewById(R.id.t1_out);

//        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> appTasks = activityManager.getRunningTasks(10);
//        String[] ctype = new String[appTasks.size()];
//        String temp;
//        if (null != appTasks && !appTasks.isEmpty()) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                ctype = new String[appTasks.size()];
//                for(int i=0;i<appTasks.size();i++){
//                    ctype[i] = appTasks.get(i).topActivity.getPackageName();
//                }
//            }
//        }
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ctype);
//
//        listView.setAdapter(adapter);
