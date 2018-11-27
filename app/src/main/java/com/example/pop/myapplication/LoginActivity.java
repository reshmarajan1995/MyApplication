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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    }

    @Override
    public void onClick(View view) {
        user=username.getText().toString();
        pass=pawd.getText().toString();
        if (view==login) {
            selectData sdata = new selectData();
            sdata.execute(user, pass);


//Intent in = new Intent(this, LoginActivity.class);
            //new HomeActivity(in);
            //Toast.makeText(this, "Login Successfully", Toast.LENGTH_LONG).show();
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
            Log.d("alliswell",s);
            Toast.makeText(LoginActivity.this, "hii..Welcome"+s, Toast.LENGTH_SHORT).show();
            try {
                JSONArray j = new JSONArray(s);
                JSONObject jo = j.getJSONObject(0);
                String  id=jo.getString("id");
                String type=jo.getString("usertype");

                if(type.equals("admin")){
                  SharedPreferences sp=getSharedPreferences("my_data",MODE_PRIVATE);
                  SharedPreferences.Editor editor=sp.edit();
                  editor.putString("admin_id",id);
                  Intent Intent=new Intent(LoginActivity.this,AdminhomeActivity.class);
                  startActivity(Intent);

                }
                else if(type.equals("elepowner")){
                    SharedPreferences sp=getSharedPreferences("my_data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("own_id",id);
                    Intent Intent=new Intent(LoginActivity.this,ElepOwnerAcivity.class);
                    startActivity(Intent);

                }
                else if (type.equals("acceowner")){
                    SharedPreferences sp=getSharedPreferences("my_data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("acceown_id",id);
                    Intent Intent=new Intent(LoginActivity.this,AcceOwnerhomeActicity.class);
                    startActivity(Intent);

                }
                else if(type.equals("customer")){
                    SharedPreferences sp=getSharedPreferences("my_data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("cust_id",id);
                    Intent Intent=new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(Intent);


                }
                else if(type.equals("fans")){
                    SharedPreferences sp=getSharedPreferences("my_data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("fans_id",id);
                    Intent Intent=new Intent(LoginActivity.this,ViewElephantBookingsByFans.class);
                    startActivity(Intent);



                }
                else if (type.equals("docter")){                    SharedPreferences sp=getSharedPreferences("my_data",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("docter_id",id);
                    Intent Intent=new Intent(LoginActivity.this,DocterhomeActivity.class);
                    startActivity(Intent);


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    }


}


