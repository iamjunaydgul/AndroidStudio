package com.example.features_andapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class downloadingWebContent extends AppCompatActivity {

    TextView sd;
    EditText userUrl;


    public class DownloadWebContent extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {

            String result="";

            //as we already know it is a string but it have to be in url format like http://..., thats why we use URL and storing that string.
            URL url;
            //for fetching the content of the url we HttpURLConnection , it is like a opening browser for us
            HttpURLConnection urlConnection;

            try {

                url=new URL(urls[0]);
                //for opening browser or website
                urlConnection= (HttpURLConnection) url.openConnection();
                urlConnection.connect();

                //building our system to read the content of that url

                //string to hold input of data
                //here we are holding the website data
                InputStream inputStream=urlConnection.getInputStream();

                //for reading the website data
                //here we are reading the website data
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);

                //now we have to read one character at a time thats why we use integer to read one character at a time
                //here integer store the location of the data of the html
                int data=inputStreamReader.read();

                while(data!=-1){

                    char current= (char) data;

                    result+=current;

                    //for moving data to next character until ends to -1
                    data=inputStreamReader.read();

                }
                return result;


            }catch (Exception ex){

                ex.printStackTrace();

                return "enter Correct url";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            String result;
            result=s;
            sd.setText(result);


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloading_web_content);


        //task.execute("https://www.google.com/");
        String result= null;
        sd=findViewById(R.id.sd);
        userUrl=findViewById(R.id.userUrl);




        //Log.i("Content of URL",result);

    }

    public void getContent(View view){

        DownloadWebContent task=new DownloadWebContent();
        String url=userUrl.getText().toString();

        if(url!=null){
            task.execute(url);
        }
        else{

            Toast.makeText(this, "enter correct URL", Toast.LENGTH_SHORT).show();

        }

    }
}
