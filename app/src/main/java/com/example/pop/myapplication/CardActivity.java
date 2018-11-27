package com.example.pop.myapplication;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {
    Button pay, cancel, save;
    EditText cardno, mm, yy, cvv;
    String id = "", bokid;
    SharedPreferences sh;
    String sh_name = "My Data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        pay = findViewById(R.id.btnok);
        pay.setOnClickListener(this);
        cancel = findViewById(R.id.btncancel);
        cancel.setOnClickListener(this);
        cardno = findViewById(R.id.edtcrdno);
        mm = findViewById(R.id.edtmm);
        yy = findViewById(R.id.edtyy);
        cvv = findViewById(R.id.edtcvv);
        SharedPreferences sp=getSharedPreferences("my_data",MODE_PRIVATE);
      id=sp.getString("cust_id","");

     //   bokid = sh.getString("bid", "");

        //Toast.makeText(this, "bookid"+bokid, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        String card = cardno.getText().toString();
        String month = mm.getText().toString();
        String year = yy.getText().toString();
        String cv = cvv.getText().toString();
        int mon = Integer.parseInt(month);
        int yr = Integer.parseInt(year);
        if (view == pay) {

            if (card.equals("")) {
                cardno.setError("Enter Cardno");
            } else if (!(card.length() == 16)) {
                cardno.setError("Enter a Valid card Number");
            } else if (cvv.getText().equals("")) {
                cvv.setError("Enter CVV");
            } else if (!(cvv.getText().length() == 3)) {
                cvv.setError("Enter a Valid CVV");
            } else if (mm.getText().equals("")) {
                mm.setError("Enter Month");
            } else if (!(mon > 0 && mon < 13)) {
                mm.setError("Enter a valid Month");
            } else if (year.equals("")) {
                yy.setError("Enter Year");
            } else if (!(yr >= 18)) {
                yy.setError("Enter a valid year");
            } else {
             //   updatedata  up = new updatedata();
               // up.execute(bokid);
               // Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

        } else {

            cardno.setText("");
            mm.setText("");
            yy.setText("");
            cvv.setText("");
        }
    }

    private class updatedata extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb = new WebServiceCaller();
            wb.setSoapObject("updatoperat");
            wb.addProperty("Id", strings[0]);

            wb.callWebService();
            return wb.getResponse();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(CardActivity.this, "Payment Succesfull", Toast.LENGTH_SHORT).show();

        }

    }
}



