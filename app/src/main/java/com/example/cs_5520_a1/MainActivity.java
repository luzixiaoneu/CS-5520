package com.example.cs_5520_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button a = findViewById(R.id.button_a);
        Button b = findViewById(R.id.button_b);
        Button c = findViewById(R.id.button_c);
        Button d = findViewById(R.id.button_d);
        Button e = findViewById(R.id.button_e);
        Button f = findViewById(R.id.button_f);
        Button linkController = findViewById(R.id.link_controller);
        Button prime = findViewById(R.id.prime_directive);
        prime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPrimeDirectiveActivity();
            }
        });

        linkController.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLinkControllerActivity();
            }
        });


        TextView output = (TextView) findViewById(R.id.pressed_text);
        output.setText("Pressed: -");
        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        d.setOnClickListener(this);
        e.setOnClickListener(this);
        f.setOnClickListener(this);

    }

    public void openPrimeDirectiveActivity() {
        Intent intent = new Intent(this, PrimeDirectiveActivity.class);
        startActivity(intent);
    }
    public void openLinkControllerActivity() {
        Intent intent = new Intent(this, LinkControllerActivity.class);
        startActivity(intent);
    }
    public void clickSelfIntro(View view) {
        Toast.makeText(MainActivity.this, "My name is Zixiao Lu, My email is luzixiaoui@gmail.com"
        ,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        TextView output = (TextView) findViewById(R.id.pressed_text);
        switch (view.getId()) {
            case R.id.button_a:
                output.setText("Pressed: A");
                break;
            case R.id.button_b:
                output.setText("Pressed: B");
                break;
            case R.id.button_c:
                output.setText("Pressed: C");
                break;
            case R.id.button_d:
                output.setText("Pressed: D");
                break;
            case R.id.button_e:
                output.setText("Pressed: E");
                break;
            case R.id.button_f:
                output.setText("Pressed: F");
                break;

        }
    }
}