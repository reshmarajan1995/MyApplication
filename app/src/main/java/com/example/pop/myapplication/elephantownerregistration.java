package com.example.pop.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;

public class elephantownerregistration extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText fname,lname,contact,email,username,password,answer;
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
        setContentView(R.layout.activity_elephantownerregistration);
        fname=(EditText) findViewById(R.id.fnameEditText);
        lname=(EditText) findViewById(R.id.lnameEditText);
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
        String contacts=contact.getText().toString();
        String mail=email.getText().toString();
        String uname=username.getText().toString();
        String pass=password.getText().toString();
        String answer1=answer.getText().toString();


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
            adapter = new ArrayAdapter<String>(elephantownerregistration.this, android.R.layout.simple_spinner_item, states);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            state.setAdapter(adapter);

        } catch (Exception e) {

        }
    }
}
}
