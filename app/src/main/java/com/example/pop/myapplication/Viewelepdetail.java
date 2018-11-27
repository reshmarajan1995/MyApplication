package com.example.pop.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Viewelepdetail extends AppCompatActivity {
    String elephnatname[] = null;
    String elephantheight[] = null;
    String elephantrate[] = null;
    String elephantphoto[] = null;
    String elephantownername[] = null;
    RecyclerView rcv;
Adapterelephant ab;
    List<Beanelephant> beanlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewelepdetail);
       // Toast.makeText(this, "Detailllllsss", Toast.LENGTH_SHORT).show();
        beanlist = new ArrayList<>();
        rcv = (RecyclerView) findViewById(R.id.rcv);
        Viewdetails ss = new Viewdetails();
           ss.execute();
    }


    private class Viewdetails extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller web = new WebServiceCaller();
            web.setSoapObject("viewelepdetails");
            web.callWebService();
            return web.getResponse();
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(Viewelepdetail.this,"on view postexecute",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
            Log.d("alliswell_view",s);
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
                    elephnatname[i] = ob.getString("name");
                    elephantheight[i] = ob.getString("height");
                    elephantrate[i] = ob.getString("rate");
                    elephantphoto[i] = ob.getString("photo");
                    elephantownername[i] = ob.getString("ownername");
                    Beanelephant bobj = new Beanelephant();
                    bobj.setElephantname(elephnatname[i]);
                    bobj.setElephantheight(elephantheight[i]);
                    bobj.setElephantrate(elephantrate[i]);
                    bobj.setElephantphoto(elephantphoto[i]);
                    bobj.setElephantownername(elephantownername[i]);
                    beanlist.add(bobj);
                }

                ab= new Adapterelephant(beanlist,Viewelepdetail.this);
                rcv.setLayoutManager(new LinearLayoutManager(Viewelepdetail.this));
                rcv.setAdapter(ab);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
