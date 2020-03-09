package com.example.features_andapplications;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class downloadingImages extends AppCompatActivity {

    ImageView downloadedImageView;
    EditText enterUrl;
    boolean x;


    public void downloadImage(View view){
        //https://i0.wp.com/trendingbios.com/wp-content/uploads/2019/07/Mia-Khalifa-boob.jpg?fit=819%2C1024&ssl=1
        //Toast.makeText(this, "Tapped", Toast.LENGTH_SHORT).show();

        ImageDownloader task=new ImageDownloader();
        Bitmap myImage = null;
        String image=enterUrl.getText().toString();

        if(enterUrl.getText()!=null){

            try {
                myImage=task.execute(image).get();
                if(x==true){
                    Toast.makeText(this, "Downloaded", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Enter a valid URL :)", Toast.LENGTH_SHORT).show();
                }
                downloadedImageView.setImageBitmap(myImage);


            } catch (Exception e) {
                Toast.makeText(this, "not valid url :)", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "not valid url :)", Toast.LENGTH_SHORT).show();
        }
    }

    public class ImageDownloader extends AsyncTask<String,Void, Bitmap> {


        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                //proper url format
                URL url=new URL(urls[0]);

                //open url connection ,browser
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();

                //for holding the content
                InputStream inputStream= httpURLConnection.getInputStream();

                //converting the content directly into image
                Bitmap myBitmap= BitmapFactory.decodeStream(inputStream);
                x=true;
                return myBitmap;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            x=false;
            return null;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloading_images);

        downloadedImageView=findViewById(R.id.downloadedImage);
        enterUrl=findViewById(R.id.enterURLeditText);
    }
}
