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

public class ViewAccDetails extends AppCompatActivity {
    String accessoriesname[] = null;
    String accessoriesrate[] = null;
    String accessoriescount[] = null;
    String accesoriesphoto[] = null;
    String accessoriesownername[] = null;
    RecyclerView rv_acce;

    AdapterAcce ab;
    List<Beanaccessories> beanlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_acc_details);
        beanlist = new ArrayList<Beanaccessories>();
        rv_acce = (RecyclerView) findViewById(R.id.rv_acce);
        Viewdetails ss = new Viewdetails();
        ss.execute();
    }

    public class Viewdetails extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller web = new WebServiceCaller();
            web.setSoapObject("Acessoviewdetails");
            web.callWebService();
            return web.getResponse();
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(ViewAccDetails.this,"on view postexecute",Toast.LENGTH_LONG).show();
            super.onPostExecute(s);
            Log.d("alliswell_view",s);
            JSONArray j;
            try {
                j = new JSONArray(s);
                JSONObject ob;
                accessoriesname = new String[j.length()];
                accessoriesrate = new String[j.length()];
               accessoriescount = new String[j.length()];
                accesoriesphoto= new String[j.length()];
                accessoriesownername = new String[j.length()];

                for (int i = 0; i < j.length(); i++) {
                    ob = j.getJSONObject(i);
                    accessoriesname[i] = ob.getString("name");
                    accessoriesrate[i] = ob.getString("rate");
                    accessoriescount[i] = ob.getString("count");
                    accesoriesphoto[i] = ob.getString("photo");
                    accessoriesownername[i] = ob.getString("ownname");
                    Beanaccessories bobj = new Beanaccessories();
                    bobj.setAccessoriesname(accessoriesname[i]);
                    bobj.setAccessoriesrate(accessoriesrate[i]);
                    bobj.setAccessoriescount(accessoriescount[i]);
                    bobj.setAccessoriesphoto(accesoriesphoto[i]);
                    bobj.setAccessoriesownername(accessoriesownername[i]);
                    beanlist.add(bobj);
                }

                ab= new AdapterAcce(beanlist,ViewAccDetails.this);
                rv_acce.setLayoutManager(new LinearLayoutManager(ViewAccDetails.this));
                rv_acce.setAdapter(ab);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}




