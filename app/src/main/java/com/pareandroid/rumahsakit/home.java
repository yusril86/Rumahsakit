package com.pareandroid.rumahsakit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.pareandroid.rumahsakit.dokter.ListDataDokter;
import com.pareandroid.rumahsakit.perawat.ListDataPerawat;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void perawat(View view){
        Intent intent1 = new Intent(home.this, ListDataPerawat.class);
        startActivity(intent1);
    }

    public void dok (View view){
        Intent intent = new Intent(home.this, ListDataDokter.class);
        startActivity(intent);

    }


}
