package com.pareandroid.rumahsakit.dokter;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.pareandroid.rumahsakit.API.ApiInterface;
import com.pareandroid.rumahsakit.API.Apiconfig;
import com.pareandroid.rumahsakit.Database.DatabaseHelper;
import com.pareandroid.rumahsakit.R;
import com.pareandroid.rumahsakit.model.ModelApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class inputdata extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    Button add_data;
    EditText edt_nama, edt_nip, edt_tmt,edt_masakerja,edt_keterangan,edt_id;
    ListView userlist;
    String nama, nip, tmt,masakerja,keterangan,id;
    ListDataDokter listDataDokter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);

        progressDialog  = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu...");


        add_data = (Button) findViewById(R.id.btn_add);
        edt_nama = (EditText) findViewById(R.id.edt_nama);
        edt_nip = (EditText) findViewById(R.id.edt_nip);
        edt_tmt = (EditText) findViewById(R.id.edt_tmt);
        edt_masakerja = (EditText) findViewById(R.id.edt_masakerja);
        edt_keterangan = (EditText) findViewById(R.id.edt_keterangan);
        edt_id = findViewById(R.id.edt_id);
//
//
//        id = getIntent().getStringExtra(ListDataDokter.Tag_ID);
//        nama = getIntent().getStringExtra(ListDataDokter.Tag_nama);
//        jenis_kelamin = getIntent().getStringExtra(ListDataDokter.Tag_Jenis_kelamin);
//
//        if (id == null | id == "") {
//            setTitle("Tambah data");
//        } else {
//            setTitle("Edit data");
//            edt_id.setText(id);
//            edt_nama.setText(nama);
//            edt_jenis.setText(jenis_kelamin);
//        }
//
//        add_data.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    if (edt_id.getText().toString().equals("")) {
//                        save();
//                    } else {
//                        edit();
//                    }
//                } catch (Exception e) {
//                    Log.e("Add data", e.toString());
//                }
//            }
//        });
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        finish();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                kosong();
//                this.finish();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//
//    //Edit Text kosong pada saat submit data
//    private void kosong() {
//        edt_nama.requestFocus();
//        edt_id.setText(null);
//        edt_jenis.setText(null);
//    }
//
//    //Simpan Data
//    private void save() {
//        if (String.valueOf(edt_nama.getText()).equals(null) || String.valueOf(edt_nama.getText()).equals("") ||
//                String.valueOf(edt_jenis.getText()).equals(null) || String.valueOf(edt_jenis.getText()).equals("")) {
//            Toast.makeText(getApplicationContext(),
//                    "Silahkan Input Data...", Toast.LENGTH_SHORT).show();
//        } else {
//            db.tambahkandata(edt_nama.getText().toString().trim(), edt_jenis.getText().toString().trim());
//            finish();
//            kosong();
//        }
//
//    }
//
//        // Update data in SQLite database
//        private void edit() {
//            if (String.valueOf(edt_nama.getText()).equals(null) || String.valueOf(edt_nama.getText()).equals("") ||
//                    String.valueOf(edt_jenis.getText()).equals(null) || String.valueOf(edt_jenis.getText()).equals("")) {
//                Toast.makeText(getApplicationContext(),
//                        "Please input name or address ...", Toast.LENGTH_SHORT).show();
//            } else {
//                db.update(Integer.parseInt(edt_id.getText().toString().trim()), edt_nama.getText().toString().trim(),
//                        edt_jenis.getText().toString().trim());
//                kosong();
//                finish();
//            }


//        id = getIntent().getStringExtra("id");
//        nama = getIntent().getStringExtra("nama");
//        nip = getIntent().getStringExtra("nip");
//        tmt = getIntent().getStringExtra("tmt");
//        masakerja = getIntent().getStringExtra("masakerja");
//        keterangan = getIntent().getStringExtra("keterangan");
//
//        edt_nama.setText(nama);
//        edt_nip.setText(nip);
//        edt_tmt.setText(tmt);
//        edt_masakerja.setText(masakerja);
//        edt_keterangan.setText(keterangan);
//        edt_id.setText(id);


        add_data.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (edt_nama.getText().toString().isEmpty()) {
                    edt_nama.setError("Mohon Isikan Nama...");
                } else if (edt_nip.getText().toString().isEmpty()) {
                    edt_nip.setError("Mohon Isikan NIP");
                } else if (edt_tmt.getText().toString().isEmpty()) {
                    edt_tmt.setError("Mohon Isikan TMT..");
                } else if (edt_masakerja.getText().toString().isEmpty()) {
                    edt_masakerja.setError("Mohon Isikan...");
                } else if (edt_keterangan.getText().toString().isEmpty()) {
                    edt_keterangan.setError("Mohon Isikan");
                }else{


                ApiInterface apiInterface = Apiconfig.getApiClient().create(ApiInterface.class);
                Call<ModelApi> listCall = apiInterface.save(edt_nama.getText().toString(), edt_nip.getText().toString(), edt_tmt.getText().toString(), edt_masakerja.getText().toString(), edt_keterangan.getText().toString());

                listCall.enqueue(new Callback<ModelApi>() {
                    @Override
                    public void onResponse(Call<ModelApi> call, Response<ModelApi> response) {
                        progressDialog.show();
                        response.isSuccessful();
                        response.body();
                        listDataDokter.list.refresh();
                        Toast.makeText(inputdata.this, "Data Berhasil Tambahkan", Toast.LENGTH_SHORT).show();
                        finish();


                    }


                    @Override
                    public void onFailure(Call<ModelApi> call, Throwable t) {
                        Toast.makeText(inputdata.this, "Unable to insert data", Toast.LENGTH_SHORT).show();

                    }
                });


                }

            }

        });





        }







}

