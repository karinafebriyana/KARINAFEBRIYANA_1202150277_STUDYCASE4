package com.example.karinafebriyana.karinafebriyana_1202150277_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;

public class TampilkanNama extends AppCompatActivity {

    private ListView mListView;
    private ProgressBar mProgressBar;
    private Button mStartAsyncTask; // deklarasi variable yang digunakan

    private String [] namaMahasiswa= {
            "Karina", "Ayu", "Febriyana", "Sheila", "Gusti", // variable array yang menyimpan string nama2
            "Shaiba", "Maura", "Keisya", "Tufael", "Aldebaran",
            "Desmond", "Hillary", "Marshall", "Elizher", "Marisca"
    };

    private AddItemToListView mAddItemToListView; //variable untuk menambah item pada listView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilkan_nama);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView);
        mStartAsyncTask = (Button) findViewById(R.id.button_startAsyncTask);

        mListView.setVisibility(View.GONE); //ngeset progressbar visible saat app di run

        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>())); //set adapter

        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // agar button berfungsi
                mAddItemToListView = new AddItemToListView(); // adapter menyambung dengan asynctask
                mAddItemToListView.execute();
            }
        });
    }

    public class AddItemToListView  extends AsyncTask<Void, String, Void> { // untuk asyntask

        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(TampilkanNama.this);

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter(); //suggestion progress bar pada UI

            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); //progress dialog
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setProgress(0);

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) { //untuk button cancel pada dialog
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE); //visible progress bar setelah di cancel
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) { //dipanggil setelah onPreExecute, perhitungan background yg memakan waktu lama
            for (String item : namaMahasiswa){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) { //update UI progress bar
            mAdapter.add(values[0]);

            Integer current_status = (int)((counter/(float)namaMahasiswa.length)*100);
            mProgressBar.setProgress(current_status);

            mProgressDialog.setProgress(current_status); //set progress
            mProgressDialog.setMessage(String.valueOf(current_status+"%")); //set message
            counter++;
        }

        @Override
        protected void onPostExecute(Void Void) {
            mProgressBar.setVisibility(View.GONE); //menyembunyikan progress bar

            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE); //menghilangkan progress dialog
        }


    }
}
