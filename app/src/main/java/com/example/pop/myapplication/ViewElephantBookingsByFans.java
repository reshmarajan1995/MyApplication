package com.example.pop.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewElephantBookingsByFans extends AppCompatActivity {
RecyclerView rv;
    AdapterFansView ab;
    List<BeanViewFans> beanlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_elephant_bookings_by_fans);
        rv=(RecyclerView)findViewById(R.id.rvFan);
        beanlist=new ArrayList<>();
        fillDoc fd=new fillDoc();
        fd.execute();
    }
    private class fillDoc extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller web = new WebServiceCaller();
            web.setSoapObject("viewBookingsByFans");
            web.callWebService();
            return web.getResponse();
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ViewElephantBookingsByFans.this,"on view postexecute",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
            Log.d("alliswell_view",s);
            JSONArray j;
            try {
                j = new JSONArray(s);
                JSONObject ob;

                for (int i = 0; i < j.length(); i++) {
                    ob = j.getJSONObject(i);

                    BeanViewFans bobj = new BeanViewFans();
                    bobj.setEname(ob.getString("name"));
                    bobj.setNeed(ob.getString("need"));
                    bobj.setDate(ob.getString("date"));


                    beanlist.add(bobj);
                }

                ab= new AdapterFansView(beanlist,ViewElephantBookingsByFans.this);
                rv.setLayoutManager(new LinearLayoutManager(ViewElephantBookingsByFans.this));
                rv.setAdapter(ab);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}


