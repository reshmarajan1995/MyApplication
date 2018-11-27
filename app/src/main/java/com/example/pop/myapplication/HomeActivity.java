package com.example.pop.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    Button viewelepdetails,pay,doc,fest,acessories;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewelepdetails = (Button) findViewById(R.id.Viewelep);
        pay = (Button) findViewById(R.id.pay);
        pay.setOnClickListener(this);

        doc = (Button) findViewById(R.id.vdoc);
        doc.setOnClickListener(this);
        acessories = (Button) findViewById(R.id.ViewAcce);
        acessories.setOnClickListener(this);
        fest = (Button) findViewById(R.id.fesdetails);
        fest.setOnClickListener(this);




//
//        book = (Button) findViewById(R.id.book);
//        book.setOnClickListener(this);
        SharedPreferences sp = getSharedPreferences("my_data", MODE_PRIVATE);
        String uidd = sp.getString("cust_id", "");

        viewelepdetails.setOnClickListener(this);
        getBookingNoti gn = new getBookingNoti();
        gn.execute(uidd);
    }

    @Override
    public void onClick(View view) {
        if (view == viewelepdetails) {
            Intent in = new Intent(HomeActivity.this, Viewelepdetail.class);
            startActivity(in);
        }
        if (view == pay) {
            Intent in = new Intent(HomeActivity.this, CardActivity.class);
            startActivity(in);
        }
        if (view == doc) {
            Intent in = new Intent(HomeActivity.this, ViewDoctorDetails.class);
            startActivity(in);
        }
//        if (view == book) {
//            Intent in = new Intent(HomeActivity.this, ElephantBooking.class);
//            startActivity(in);
 //       }

        if (view == acessories) {
            Intent in = new Intent(HomeActivity.this, ViewAccDetails.class);
            startActivity(in);
        }

        if (view == fest) {
            Intent in = new Intent(HomeActivity.this, ViewElephantBookingsByFans.class);
            startActivity(in);
        }
    }


    private class getBookingNoti extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller web = new WebServiceCaller();
            web.setSoapObject("getBookingNoti");
            web.addProperty("uid", strings[0]);
            web.callWebService();
            return web.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            String status = "";

            Toast.makeText(HomeActivity.this, "on view postexecute", Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
            Log.d("alliswell_view", s);
            JSONArray j;
            try {
                j = new JSONArray(s);
                JSONObject ob;
                ob = j.getJSONObject(0);
                status = ob.getString("status");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (status.equals("1")) {
                tv.setText("New Notification : Your Booking is Accepted");
            } else if (status.equals("2")) {
                {
                    tv.setText("New Notification : Your Booking is Rejected");
                }
            }

        }
    }
}