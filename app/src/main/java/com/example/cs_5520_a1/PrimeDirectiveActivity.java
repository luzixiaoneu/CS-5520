package com.example.cs_5520_a1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cs_5520_a1.entity.PrimeDirective;

public class PrimeDirectiveActivity extends AppCompatActivity {
    private Handler textHandler = new Handler();
    public boolean status;
    private TextView primeInfo;
    public int number;
    private TextView largestInfo;
    long threadID = -1;
    int largest = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.number = 3;
        setContentView(R.layout.activity_prime_directive);
        this.status = true;
        this.primeInfo = findViewById(R.id.prime_info);
        this.largestInfo = findViewById(R.id.largest_prime);
        primeInfo.setText("Not Started!");
        largestInfo.setText("Not Started!");
        Button start = findViewById(R.id.find_prime);
        Button stop = findViewById(R.id.stop_search);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = true;

//                Toast.makeText(PrimeDirectiveActivity.this, "123132132321", Toast.LENGTH_SHORT).show();
                runOnDifferentThread(view);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = false;
            }
        });

    }

    public void runOnDifferentThread(View view) {

        differentThread differentThread = new differentThread();
        differentThread.start();

        threadID = differentThread.getId();
        Log.d("WTF", "runOnDifferentThread: " + threadID);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("current_text", primeInfo.getText().toString());
        outState.putInt("current_number", number);
        outState.putLong("thread_id", threadID);
        outState.putInt("largest_number", largest);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {


        this.number = savedInstanceState.getInt("current_number");
        this.status = true;
        this.largest = savedInstanceState.getInt("largest_number");
        differentThread differentThread = new differentThread();
        differentThread.start();
        super.onRestoreInstanceState(savedInstanceState);
    }

    class differentThread extends Thread {
        @Override
        public void run() {
            while (status) {
                textHandler.post(new Runnable() {
                    @Override
                    public void run() {
                      if (PrimeDirective.isPrime(number)) {
                            if (number > largest) {
                                largest = number;

                            }
                          largestInfo.setText("Current Largest Prime: " + largest);
                            primeInfo.setText("Current " + number + " is a prime!");
                      }
                      else {
                          primeInfo.setText("Current Number " + number + " is NOT a prime!");
                      }


                    }
                });
                number += 2;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}