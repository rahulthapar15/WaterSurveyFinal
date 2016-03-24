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

public class InterviewerActivity extends Activity {

   private Button save_next_interviewer;
    private String URL_NEW_INTERVIEWER = "http://192.168.0.106/Water_Supply_Survey/new_interviewer.php";
    private EditText editTextName;
    private EditText editTextDate;
    private EditText editTextLocation;

    String name_of_interviewer="";
    String date="";
    String location="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewer);


        editTextName = (EditText) findViewById(R.id.edit_name_of_interviewer);
       // name_of_interviewer = editTextName.getText().toString();

        editTextDate = (EditText) findViewById(R.id.edit_date);
        //date = editTextDate.getText().toString();

        editTextLocation = (EditText) findViewById(R.id.edit_location);
       // location = editTextLocation.getText().toString();

        save_next_interviewer = (Button)findViewById(R.id.save_next_interviewer_details);
        save_next_interviewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name_of_interviewer = editTextName.getText().toString();
                date = editTextDate.getText().toString();
                location=editTextLocation.getText().toString();
                new AddNewInterviewer().execute(name_of_interviewer,date,location);
                Intent i = new Intent(InterviewerActivity.this, RespondantActivity.class);
                startActivity(i);
            }
        });
    }

    private class AddNewInterviewer extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg) {
            // TODO Auto-generated method stub
            String Name = arg[0];
            String Date = arg[1];
            String Location = arg[2];

            // Preparing post params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Name", Name));
            params.add(new BasicNameValuePair("Date", Date));
            params.add(new BasicNameValuePair("Location", Location));

            ServiceHandler serviceClient = new ServiceHandler();

            String json = serviceClient.makeServiceCall(URL_NEW_INTERVIEWER,
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
