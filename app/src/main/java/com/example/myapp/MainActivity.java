package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText et1_in;
    private EditText et2_in;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        et1_in = findViewById(R.id.et1_in);
        et2_in = findViewById(R.id.et2_in);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = et1_in.getText().toString();
                String password = et2_in.getText().toString();
                if(user.equals("abc") && password.equals("123")){
                    Intent intent = new Intent(MainActivity.this,AppActivity.class);
                    Toast.makeText(MainActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();

                } else if (user.equals("") && password.equals("")){
                    Toast.makeText(MainActivity.this, "请输入用户名和密码!", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(MainActivity.this, "用户名或者密码错误!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
