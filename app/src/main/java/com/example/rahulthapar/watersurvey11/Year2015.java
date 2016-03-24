package com.example.rahulthapar.watersurvey11;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Year2015 extends Activity {

    private EditText jan,feb,march,april,may,june,july,aug,sept,oct,nov,dec;
    private Button year2015;
    private String URL_YEAR_2015 = "http://192.168.0.106/Water_Supply_Survey/year2015.php";

    String jan_1 = "0";
    String feb_1 = "0";
    String march_1 = "0";
    String april_1 = "0";
    String may_1 = "0";
    String june_1 = "0";
    String july_1 = "0";
    String aug_1 = "0";
    String sept_1 = "0";
    String oct_1 = "0";
    String nov_1 = "0";
    String dec_1 = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year2015);

        jan = (EditText)findViewById(R.id.edit_jan_2015);
        feb = (EditText)findViewById(R.id.edit_feb_2015);
        march = (EditText)findViewById(R.id.edit_march_2015);
        april = (EditText)findViewById(R.id.edit_april_2015);
        may = (EditText)findViewById(R.id.edit_may_2015);
        june = (EditText)findViewById(R.id.edit_june_2015);
        july = (EditText)findViewById(R.id.edit_july_2015);
        aug = (EditText)findViewById(R.id.edit_aug_2015);
        sept = (EditText)findViewById(R.id.edit_sept_2015);
        oct = (EditText)findViewById(R.id.edit_oct_2015);
        nov = (EditText)findViewById(R.id.edit_nov_2015);
        dec = (EditText)findViewById(R.id.edit_dec_2015);

        year2015 = (Button)findViewById(R.id.save_next_year2015);
        year2015.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jan_1 = jan.getText().toString();
                feb_1 = feb.getText().toString();
                march_1 = march.getText().toString();
                april_1 = april.getText().toString();
                may_1 = may.getText().toString();
                june_1 = june.getText().toString();
                july_1 = july.getText().toString();
                aug_1 = aug.getText().toString();
                sept_1 = sept.getText().toString();
                oct_1 = oct.getText().toString();
                nov_1 = nov.getText().toString();
                dec_1 = dec.getText().toString();

                new WaterUse2015().execute(jan_1,feb_1,march_1,april_1,may_1,june_1,july_1,aug_1,sept_1,oct_1,nov_1,dec_1);
                Intent i = new Intent(Year2015.this,Year2016.class);
                startActivity(i);
            }
        });
    }
    private class WaterUse2015 extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... arg) {
            // TODO Auto-generated method stub
            String January = arg[0];
            String February = arg[1];
            String March = arg[2];
            String April = arg[3];
            String May = arg[4];
            String June = arg[5];
            String July = arg[6];
            String August = arg[7];
            String September = arg[8];
            String October = arg[9];
            String November = arg[10];
            String December = arg[11];


            // Preparing post params
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("January", January));
            params.add(new BasicNameValuePair("February", February));
            params.add(new BasicNameValuePair("March", March));
            params.add(new BasicNameValuePair("April", April));
            params.add(new BasicNameValuePair("May", May));
            params.add(new BasicNameValuePair("June", June));
            params.add(new BasicNameValuePair("July", July));
            params.add(new BasicNameValuePair("August", August));
            params.add(new BasicNameValuePair("September", September));
            params.add(new BasicNameValuePair("October", October));
            params.add(new BasicNameValuePair("November", November));
            params.add(new BasicNameValuePair("December", December));


            ServiceHandler serviceClient = new ServiceHandler();

            String json = serviceClient.makeServiceCall(URL_YEAR_2015,
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
