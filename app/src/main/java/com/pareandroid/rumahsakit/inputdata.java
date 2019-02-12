package com.pareandroid.rumahsakit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.pareandroid.rumahsakit.Database.DatabaseHelper;

public class inputdata extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    Button add_data;
    EditText edt_nama, edt_id, edt_jenis;
    ListView userlist;
    String id, nama, jenis_kelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);


        add_data = (Button) findViewById(R.id.btn_add);
        edt_nama = (EditText) findViewById(R.id.txt_nama);
        edt_id = (EditText) findViewById(R.id.txt_id);
        edt_jenis = (EditText) findViewById(R.id.txt_jenis);


        id = getIntent().getStringExtra(listdata.Tag_ID);
        nama = getIntent().getStringExtra(listdata.Tag_nama);
        jenis_kelamin = getIntent().getStringExtra(listdata.Tag_Jenis_kelamin);

        if (id == null | id == "") {
            setTitle("Tambah data");
        } else {
            setTitle("Edit data");
            edt_id.setText(id);
            edt_nama.setText(nama);
            edt_jenis.setText(jenis_kelamin);
        }

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (edt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    Log.e("Add data", e.toString());
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                kosong();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //Edit Text kosong pada saat submit data
    private void kosong() {
        edt_nama.requestFocus();
        edt_id.setText(null);
        edt_jenis.setText(null);
    }

    //Simpan Data
    private void save() {
        if (String.valueOf(edt_nama.getText()).equals(null) || String.valueOf(edt_nama.getText()).equals("") ||
                String.valueOf(edt_jenis.getText()).equals(null) || String.valueOf(edt_jenis.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Silahkan Input Data...", Toast.LENGTH_SHORT).show();
        } else {
            db.tambahkandata(edt_nama.getText().toString().trim(), edt_jenis.getText().toString().trim());
            finish();
            kosong();
        }

    }

        // Update data in SQLite database
        private void edit() {
            if (String.valueOf(edt_nama.getText()).equals(null) || String.valueOf(edt_nama.getText()).equals("") ||
                    String.valueOf(edt_jenis.getText()).equals(null) || String.valueOf(edt_jenis.getText()).equals("")) {
                Toast.makeText(getApplicationContext(),
                        "Please input name or address ...", Toast.LENGTH_SHORT).show();
            } else {
                db.update(Integer.parseInt(edt_id.getText().toString().trim()), edt_nama.getText().toString().trim(),
                        edt_jenis.getText().toString().trim());
                kosong();
                finish();
            }
        }







}

