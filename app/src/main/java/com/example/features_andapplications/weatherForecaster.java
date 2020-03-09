package com.example.features_andapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.concurrent.ExecutionException;

public class weatherForecaster extends AppCompatActivity {

    ImageView weatherImageView;
    String result="";
    TextView weatherResultView;
    EditText cityName;

    public void findWeather(View view){
        //Toast.makeText(this, cityName.getText().toString(), Toast.LENGTH_SHORT).show();

        InputMethodManager mgr= (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(cityName.getWindowToken(),0);

        TaskforDownload downloadTask=new TaskforDownload();
        downloadTask.execute("https://api.openweathermap.org/data/2.5/weather?q="+cityName.getText().toString()+"&appid=1367b938e15f1d7a58e9fe768f082ddc");

    }

    public class TaskforDownload extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... urls) {

            try {

                URL url=new URL(urls[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                int data=inputStreamReader.read();

                while (data!=-1){

                    result+= (char) data;
                    data=inputStreamReader.read();
                }

                return result;

            } catch (Exception e) {

                Toast.makeText(weatherForecaster.this, "Wrong City", Toast.LENGTH_SHORT).show();

            }

            return "Content Downloading Failed";
        }

        @Override

        //new Way :)
        //no need to do String result=class object .execute(url).get; etc in onCreate method :)

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject jsonObject=new JSONObject(result);
                String weather=jsonObject.getString("weather");
                JSONArray jsonArray=new JSONArray(weather);

                for (int i=0;i<jsonArray.length();i++){
                    JSONObject JSONpart = jsonArray.getJSONObject(i);
                    weatherResultView.setText("Main:"+JSONpart.getString("main")+"\r\n"+"Description:"+JSONpart.getString("description"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecaster);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        weatherResultView =findViewById(R.id.weatherResultView);
        cityName=findViewById(R.id.weatherEditText);

    }
}
