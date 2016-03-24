package com.example.rahulthapar.watersurvey11;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RespondantActivity extends Activity {

    private String URL_NEW_RESPONDANT = "http://192.168.0.106/Water_Supply_Survey/new_respondant.php";
    private EditText editTextName;
    private EditText editTextMobile;
    private EditText editTextAddress;
    private Button save_next_respondant;

    String Name="";
    String Mobile_Number="";
    String Address="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respondant);

        editTextName = (EditText)findViewById(R.id.edit_name_of_respondant);
        editTextAddress= (EditText)findViewById(R.id.edit_address);
        editTextMobile = (EditText)findViewById(R.id.edit_mobile_no);

        save_next_respondant = (Button)findViewById(R.id.save_next_respondant);
        save_next_respondant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = editTextName.getText().toString();
                Mobile_Number = editTextMobile.getText().toString();
                Address = editTextAddress.getText().toString();
                new AddNewRespondant().execute(Name,Mobile_Number,Address);
                Intent i = new Intent(RespondantActivity.this,Year2014.class);
                startActivity(i);
            }
        });
    }
    private class AddNewRespondant extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg) {
            // TODO Auto-generated method stub
            String Name = arg[0];
            String MobileNumber = arg[1];
            String Address = arg[2];

            // Preparing post params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Name", Name));
            params.add(new BasicNameValuePair("MobileNumber", MobileNumber));
            params.add(new BasicNameValuePair("Address", Address));

            ServiceHandler serviceClient = new ServiceHandler();

            String json = serviceClient.makeServiceCall(URL_NEW_RESPONDANT,
                    ServiceHandler.POST, params);

            Log.d("Create Request: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    boolean error = jsonObj.getBoolean("error");
                    // checking for error node in json
                    if (!error) {
                        // new category created successfully
                        Log.e("added successfully ",
                                "> " + jsonObj.getString("message"));
                    } else {
                        Log.e("Add Error: ",
                                "> " + jsonObj.getString("message"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "JSON data error!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
