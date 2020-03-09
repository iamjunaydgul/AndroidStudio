package com.example.features_andapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class javaScriptObjectNotation extends AppCompatActivity {
    String result = "";

    public class downloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data != -1) {

                    result += (char) data;
                    data = inputStreamReader.read();
                }

                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Content Downloading Failed";
        }

        @Override

        //new Way :)
        //no need to do String result=class object .execute(url).get; etc in onCreate method :)

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                String weather = jsonObject.getString("weather");
                JSONArray jsonArray = new JSONArray(weather);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject JSONpart = jsonArray.getJSONObject(i);
                    Toast.makeText(javaScriptObjectNotation.this, JSONpart.getString("main") + JSONpart.getString("description"), Toast.LENGTH_SHORT).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }



       /*     Pattern pattern=Pattern.compile("\"description\":\"(.*?)\",\"icon\"");
            Matcher matcher=pattern.matcher(result);
            while (matcher.find()){
                Toast.makeText(getApplicationContext(), matcher.group(1), Toast.LENGTH_LONG).show();
                Log.i("Content",matcher.group(1));
            }
            //Toast.makeText(javaScriptObjectNotation.this, "WebContent:" +result, Toast.LENGTH_SHORT).show();*/

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_script_object_notation);

        //just create the object and execute using the required urls

        downloadTask downloadTask = new downloadTask();
        try {
            downloadTask.execute("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }

