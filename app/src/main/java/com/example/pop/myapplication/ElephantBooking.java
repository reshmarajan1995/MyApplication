package com.example.pop.myapplication;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Calendar;

public class ElephantBooking extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener  {
    Spinner Need;
    TextView Startdate,Enddate,setStart,setEnd;
    Button Submit;
    ArrayAdapter adpneed;
    String needname[],needid[],startdate,enddate;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elephant_booking);
        Need=(Spinner) findViewById(R.id.need);
        Startdate=(TextView) findViewById(R.id.textView6);
        Enddate=(TextView) findViewById(R.id.textView8);
        setStart=(TextView) findViewById(R.id.startdate);
        setEnd=(TextView) findViewById(R.id.enddate);
        Submit=(Button) findViewById(R.id.submit);

        GetNeed gn=new GetNeed();
        gn.execute();

        Startdate.setOnClickListener(this);
        Enddate.setOnClickListener(this);
        Submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==Startdate){

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    setStart.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    startdate=year+"-"+(monthOfYear + 1) + "-"+dayOfMonth;
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (view==Enddate)
        {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {

                    setEnd.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    enddate=year+"-"+(monthOfYear + 1) + "-"+dayOfMonth;
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
        if (view==Submit){

            String eleid=getIntent().getStringExtra("elephantid");
            String nid=needid[Need.getSelectedItemPosition()];

            SharedPreferences sp=getSharedPreferences("my_data",MODE_PRIVATE);
           String uidd=sp.getString("cust_id","");
            Booking B= new Booking();
            B.execute(startdate,enddate,eleid,nid,uidd);
        }



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class GetNeed extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wc=new WebServiceCaller();
            wc.setSoapObject("GetNeed");
            wc.callWebService();
            return wc.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(ElephantBooking.this, "PostExecuteElephnatBooking", Toast.LENGTH_SHORT).show();

            JSONArray ja=null;
            try{
                ja =new JSONArray(s);
                JSONObject jb=null;

                needid=new String[ja.length()];
                needname=new String[ja.length()];

                for (int i=0;i<ja.length();i++)
                {
                    jb=ja.getJSONObject(i);
                    needid[i]=jb.getString("needid");
                    needname[i]=jb.getString("needname");
                    Log.d("needname",needname[i]);
                }

                adpneed = new ArrayAdapter<String>(ElephantBooking.this,android.R.layout.simple_spinner_item,needname);
                adpneed.setDropDownViewResource(android.R.layout.simple_spinner_item);
                Need.setAdapter(adpneed);

            }
            catch(Exception ex)
            {


            }

        }
    }

    public class Booking extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wc=new WebServiceCaller();
            wc.setSoapObject("Booking");
            wc.addProperty("startdate",strings[0]);
            wc.addProperty("enddate",strings[1]);
            wc.addProperty("elephantid",strings[2]);
            wc.addProperty("needid",strings[3]);
            wc.addProperty("uid",strings[4]);
            wc.callWebService();
            return wc.getResponse();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(ElephantBooking.this, "Booking Successfull", Toast.LENGTH_SHORT).show();
        }
    }
}
