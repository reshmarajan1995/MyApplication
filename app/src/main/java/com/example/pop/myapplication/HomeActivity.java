package com.example.pop.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button viewelepdetails;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewelepdetails=(Button)findViewById(R.id.Viewelep);
        viewelepdetails.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view==viewelepdetails) {
            Intent in = new Intent(HomeActivity.this, Viewelepdetail.class);
            startActivity(in);
        }



    }
}
