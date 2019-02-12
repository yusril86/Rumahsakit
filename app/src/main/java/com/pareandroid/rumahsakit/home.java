package com.pareandroid.rumahsakit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void dok (View view){
        Intent intent = new Intent(home.this,listdata.class);
        startActivity(intent);
    }
}
