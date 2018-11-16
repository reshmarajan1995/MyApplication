package com.example.pop.myapplication;

import android.location.Address;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Customerregistration extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText fname,lname,address,contact,email,username,password,answer;
    Button submit,cancel;
    Spinner place,dist,state,securityquestion;
    String places[]=null;
    String place_id[]=null;
    String dists[]=null;
    String dist_id[]=null;
    String states[]=null;
    String state_id[]=null;
    String stat,dis,pla,pl;
    ArrayAdapter adapter=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerregistration);
        fname=(EditText) findViewById(R.id.fnameEditText);
        lname=(EditText) findViewById(R.id.lnameEditText);
        address=(EditText) findViewById(R.id.addressEditText);
        contact=(EditText) findViewById(R.id.contactEditText);
        email=(EditText) findViewById(R.id.emailEditText);
        username=(EditText) findViewById(R.id.usernameEditText);
        password=(EditText) findViewById(R.id.pwdEditText);
        place=(Spinner) findViewById(R.id.placeSpinner);
        //places=(EditText)findViewById(R.id.placeEditText);
        dist=(Spinner) findViewById(R.id.distSpinner);
        state=(Spinner) findViewById(R.id.stateSpinner);

        securityquestion=(Spinner) findViewById(R.id.securiSpinner);
        answer=(EditText)findViewById(R.id.answerEditText);
        submit=(Button)findViewById(R.id.subButton);
        cancel=(Button)findViewById(R.id.cancelButton);


        submit.setOnClickListener(this);
        cancel.setOnClickListener(this);
        dist.setOnItemSelectedListener(this);
        state.setOnItemSelectedListener(this);
        place.setOnItemSelectedListener(this);
        selstate ss=new selstate();
        ss.execute();


    }


    @Override
    public void onClick(View view) {
        String firstname=fname.getText().toString();
        String lastname=lname.getText().toString();
        String adress=address.getText().toString();
        String contacts=contact.getText().toString();
        String mail=email.getText().toString();
        String uname=username.getText().toString();
        String pass=password.getText().toString();
        String answer1=answer.getText().toString();

        insertdata id = new insertdata();
        id.execute(firstname,lastname,adress,contacts,mail,uname,pass,answer1);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

class selstate extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... strings) {
        WebServiceCaller web = new WebServiceCaller();
        web.setSoapObject("state");

        web.callWebService();
        return web.getResponse();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Toast.makeText(UserRegActivity.this,""+s,Toast.LENGTH_LONG).show();
        JSONArray j;
        try {
            j = new JSONArray(s);
            JSONObject obj;
            states = new String[j.length()];
            state_id = new String[j.length()];
            for (int i = 0; i < j.length(); i++) {
                obj = j.getJSONObject(i);
                states[i] = obj.getString("State_Name");
                state_id[i] = obj.getString("State_Id");
            }
            adapter = new ArrayAdapter<String>(Customerregistration.this, android.R.layout.simple_spinner_item, states);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            state.setAdapter(adapter);

        } catch (Exception e) {

        }
    }
}


    private class insertdata  extends  AsyncTask<String , String, String>{
        @Override
        protected String doInBackground(String... strings) {
            WebServiceCaller wb = new WebServiceCaller();
            wb.setSoapObject("custreg");
            wb.addProperty("fname",strings[0]);
            wb.addProperty("lname",strings[1]);
            wb.addProperty("address",strings[2]);
            wb.addProperty("contact",strings[3]);
            wb.addProperty("email",strings[4]);
            wb.addProperty("placeid",strings[5]);
            wb.addProperty("username",strings[6]);
            wb.addProperty("password",strings[7]);
            wb.addProperty("securityquestion",strings[8]);
            wb.addProperty("answer",strings[9]);
            wb.callWebService();
            return wb.getResponse();
        }
    }
}
