package com.example.cs_5520_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickSelfIntro(View view) {
        Toast.makeText(MainActivity.this, "My name is Zixiao Lu, My email is luzixiaoui@gmail.com"
        ,Toast.LENGTH_LONG).show();
    }
}