package com.example.karinafebriyana.karinafebriyana_1202150277_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button nama, gambar; //deklarasi variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = (Button) findViewById(R.id.nama);
        gambar = (Button) findViewById(R.id.gambar);

        nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {        // eksekusi button list nama mahasiswa
                Toast.makeText(MainActivity.this, "List Nama Mahasiswa di Pilih", Toast.LENGTH_SHORT).show(); //toast saat memilih
                Intent a = new Intent(MainActivity.this, TampilkanNama.class); //untuk intent atau pindah ke activity selanjutnya
                startActivity(a);
            }
        });

        gambar.setOnClickListener(new View.OnClickListener() { //eksekusi button pencarian gambar
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Pencari Gambar Dipilih", Toast.LENGTH_SHORT).show(); //memunculkan toast
                Intent b = new Intent(MainActivity.this, Gambar.class);
                startActivity(b);
            }
        });
    }
}