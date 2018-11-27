package com.example.pop.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewDoctorDetails extends AppCompatActivity {
RecyclerView rv;

    AdapterDoctor ab;
    List<BeanDoctor> beanlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor_details);
        rv= (RecyclerView)findViewById(R.id.rv_doc);
        beanlist=new ArrayList<>();
        fillDoc fd= new fillDoc();
        fd.execute();
    }

    private class fillDoc extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller web = new WebServiceCaller();
            web.setSoapObject("Docterviewddetails");
            web.callWebService();
            return web.getResponse();
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ViewDoctorDetails.this,"on view postexecute",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
            Log.d("alliswell_view",s);
            JSONArray j;
            try {
                j = new JSONArray(s);
                JSONObject ob;

                for (int i = 0; i < j.length(); i++) {
                    ob = j.getJSONObject(i);

                    BeanDoctor bobj = new BeanDoctor();
                    bobj.setName(ob.getString("name"));
                    bobj.setPlace(ob.getString("place"));
                    bobj.setPhoto(ob.getString("photo"));
                    bobj.setQualification(ob.getString("quali"));
                    bobj.setContact(ob.getString("contact"));

                    beanlist.add(bobj);
                }

                ab= new AdapterDoctor(beanlist,ViewDoctorDetails.this);
                rv.setLayoutManager(new LinearLayoutManager(ViewDoctorDetails.this));
                rv.setAdapter(ab);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
