package com.example.pop.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,pawd;
    Button login,Newreg;
    String user,pass;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.loginuserEditText);
        pawd=(EditText)findViewById(R.id.loginpwdEditText);
        login=(Button)findViewById(R.id.loginButton);
        login.setOnClickListener(this);
        Intent j1= getIntent();
        Newreg=(Button)findViewById(R.id.reg);
        Newreg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        user=username.getText().toString();
        pass=pawd.getText().toString();
        if (view.getId()==R.id.loginButton) {
            selectData sdata = new selectData();
            sdata.execute(user, pass);


//Intent in = new Intent(this, LoginActivity.class);
            //new HomeActivity(in);
            //Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show();
        }
        else{


        }

    }
    private class selectData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller web=new WebServiceCaller();
            web.setSoapObject("login");
            web.addProperty("Username",strings[0]);
            web.addProperty("Password",strings[1]);
            web.callWebService();
            return web.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(LoginActivity.this,"Login Successfull"+s, Toast.LENGTH_LONG).show();
            sh=getSharedPreferences("My data", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sh.edit();
            editor.putString("ID",s);
            editor.commit();
            Intent in =new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(in);

        }


    }


}


