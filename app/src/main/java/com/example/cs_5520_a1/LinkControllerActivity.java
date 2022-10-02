package com.example.cs_5520_a1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cs_5520_a1.entity.LinkElement;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LinkControllerActivity extends AppCompatActivity implements  RecyclerAdapter.OnNoteListener{

    private static final String TAG = "debug";
    private RecyclerView linkController;
    private List<LinkElement> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_controller);
        linkController = findViewById(R.id.link_recycle_view);
        list = new ArrayList<>();
        FloatingActionButton add = findViewById(R.id.add_button);
        FloatingActionButton reset = findViewById(R.id.reset_button);

        reset.setOnClickListener(view -> reset());
        add.setOnClickListener(view -> openDialog());
//        setAdapter();
    }

    private void reset() {
        list = new ArrayList<>();
        setAdapter();
    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(this.list, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        linkController.setLayoutManager(layoutManager);
        linkController.setItemAnimator(new DefaultItemAnimator());
        linkController.setAdapter(adapter);
    }


    private void openDialog() {
        Dialog dialog = new Dialog(LinkControllerActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);

        dialog.setContentView(R.layout.add_link_dialog);
        EditText enterName = dialog.findViewById(R.id.enterName);
        EditText enterURL = dialog.findViewById(R.id.enterURL);
        Button submit = dialog.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String name = enterName.getText().toString();
                String URL = enterURL.getText().toString();
                transferData(name, URL);
                dialog.dismiss();
                showSnackBar();
            }
        });
        dialog.show();
    }



    public void transferData(String name, String URL) {
        this.list.add(new LinkElement(name, URL));
        setAdapter();
    }

    public void showSnackBar() {
        Snackbar bar = Snackbar.make(findViewById(R.id.link_recycle_view), "Successfully added Link", Snackbar.LENGTH_SHORT);
        bar.show();
    }

    @Override
    public void onNoteClick(int position) {
//        Log.d(TAG, "onNoteClick: " + position);
        String url = this.list.get(position).getURL();
        Log.d(TAG, "onNoteClick: " + url);
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);

    }
}