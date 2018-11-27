package com.example.pop.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Viewelephantdetails extends AppCompatActivity {
    String elephnatname[] = null;
    String elephantheight[] = null;
    String elephantrate[] = null;
    String elephantphoto[] = null;
    String elephantownername[] = null;
    RecyclerView rcv;

    List<Beanelephant> beanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewelephantdetails);

        beanlist = new ArrayList<>();
        rcv = (RecyclerView) findViewById(R.id.rcv);
        viewdetails ss = new viewdetails();
        ss.execute();

    }


    class viewdetails extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller web = new WebServiceCaller();
            web.setSoapObject("viewelephnatdetails");
            web.callWebService();
            return web.getResponse();
        }


        protected void onPostExecute(String s) {
            onPostExecute(s);
            JSONArray j;
            try {
                j = new JSONArray(s);
                JSONObject ob;
                elephnatname = new String[j.length()];
                elephantheight = new String[j.length()];
                elephantrate = new String[j.length()];
                elephantphoto = new String[j.length()];
                elephantownername = new String[j.length()];

                for (int i = 0; i < j.length(); i++) {
                    ob = j.getJSONObject(i);
                    elephnatname[i] = ob.getString("elep_name");
                    elephantheight[i] = ob.getString("elep_height");
                    elephantrate[i] = ob.getString("elep_rate");
                    elephantphoto[i] = ob.getString("elep_photo");
                    elephantownername[i] = ob.getString("own_name");
                    Beanelephant bobj = new Beanelephant();
                    bobj.setElephantname(elephnatname[i]);
                    bobj.setElephantheight(elephantheight[i]);
                    bobj.setElephantrate(elephantrate[i]);
                    bobj.setElephantphoto(elephantphoto[i]);
                    bobj.setElephantownername(elephantownername[i]);
                    beanlist.add(bobj);
                }
//                Adapterelephant ada = new Adapterelephant(beanlist, Viewelephantdetails.this);
//                rcv.setLayoutManager(new LinearLayoutManager(Viewelephantdetails.this));
//                rcv.setAdapter(ada);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
