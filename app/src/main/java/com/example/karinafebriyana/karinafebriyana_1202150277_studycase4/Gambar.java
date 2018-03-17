package com.example.karinafebriyana.karinafebriyana_1202150277_studycase4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class Gambar extends AppCompatActivity {

    private ImageView downloadedImage; //deklarasi variable
    private ProgressDialog mProgressDialog;
    private EditText linkUrl;
    private Button imageDownloaderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);

        imageDownloaderButton = (Button) findViewById(R.id.Button);
        downloadedImage = (ImageView) findViewById(R.id.ImageView);
        linkUrl = (EditText)findViewById(R.id.EditText);

        imageDownloaderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //untuk mengaktifkan button
                String downloadUrl = linkUrl.getText().toString();
                if(downloadUrl.isEmpty()){
                    Toast.makeText(Gambar.this,"Masukkan URL Gambar",Toast.LENGTH_SHORT).show(); //toast
                }else {
                    new ImageDownloader().execute(downloadUrl); //eksekusi gambar asynctask
                }
            }
        });
    }
    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(Gambar.this);
            mProgressDialog.setTitle("Search Image"); //set judul
            mProgressDialog.setMessage("Loading..."); //set pesan
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show(); //menampilkan progress dialog
        }


        @Override
        protected Bitmap doInBackground(String... URL) { //untuk URL

            String imageURL = URL[0];

            Bitmap bitmap = null;
            try {

                InputStream input = new java.net.URL(imageURL).openStream(); //download/menampilkan gambar dari URL

                bitmap = BitmapFactory.decodeStream(input); //untuk membaca bitmap
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {

            downloadedImage.setImageBitmap(result); //agar gambar masuk ke ImageView
            mProgressDialog.dismiss(); //selesai
        }
    }
}
