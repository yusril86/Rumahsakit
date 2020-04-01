package com.pareandroid.rumahsakit.dokter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.pareandroid.rumahsakit.API.ApiInterface;
import com.pareandroid.rumahsakit.API.Apiconfig;
import com.pareandroid.rumahsakit.R;
import com.pareandroid.rumahsakit.adapter.AdapterOnlineDokter;
import com.pareandroid.rumahsakit.model.ModelApi;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDataDokter extends AppCompatActivity {
    AdapterOnlineDokter adapterOnlineDokter;

//    ListView userlist;
    AlertDialog.Builder alert;
//    List<ModelApi> item = new ;
//    Adapter adapter;
//    DatabaseHelper db = new DatabaseHelper(this);
//    public static final String Tag_ID = "id";
//    public static final String Tag_nama = "nama";
//    public static final String Tag_Jenis_kelamin = "jenis_kelamin";
    TextView tv_nama,tv_nip,tv_tmt,tv_masakerja,tv_keterangan;
    ProgressDialog progressDialog;
    ApiInterface apiInterface;
    private RecyclerView.Adapter Radapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager RlayoutManager;
    public static ListDataDokter list;

//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdatadokter);
//        recyclerView = (RecyclerView) findViewById(R.id.rv_list_dokter);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon Tunggu...");
// private List<ModelApi>modelApis;
//        db = new DatabaseHelper(getApplicationContext());
//

        tv_nama = findViewById(R.id.tv_nama);
//        tv_nip = findViewById(R.id.txt_nip);
//        tv_tmt = findViewById(R.id.txt_tmt);
        tv_masakerja = findViewById(R.id.tv_masa_kerja);
        tv_keterangan = findViewById(R.id.tv_keterangan);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListDataDokter.this, inputdata.class);
                startActivity(intent);
            }
        });





//        adapter = new Adapter(ListDataDokter.this, item);
//        userlist.setAdapter(adapter);
//
//        // long press listview to show edit and delete
//        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(final AdapterView<?> parent, View view,
//                                           final int position, long id) {
//                // TODO Auto-generated method stub
//                final String idx = item.get(position).getId();
//                final String name = item.get(position).getNama();
//                final String Jenis = item.get(position).getJenis_kelamin();
//
//                final CharSequence[] dialogitem = {"Edit", "Delete"};
//                alert = new AlertDialog.Builder(ListDataDokter.this);
//                alert.setCancelable(true);
//                alert.setItems(dialogitem, new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // TODO Auto-generated method stub
//                        switch (which) {
//                            case 0:
//                                Intent intent = new Intent(ListDataDokter.this, inputdata.class);
//                                intent.putExtra(Tag_ID, idx);
//                                intent.putExtra(Tag_nama, name);
//                                intent.putExtra(Tag_Jenis_kelamin, Jenis);
//                                startActivity(intent);
//                                break;
//                            case 1:
//                                db.delete(Integer.parseInt(idx));
//                                item.clear();
//                                getData();
//                                break;
//                        }
//                    }
//                }).show();
//                return false;
//            }
//        });
//
//        getData();
//    }
//
//    private void getData() {
//        ArrayList<HashMap<String, String>> row = db.getData();
//        for (int i = 0; i < row.size(); i++) {
//            String id = row.get(i).get(Tag_ID);
//            String poster = row.get(i).get(Tag_nama);
//            String title = row.get(i).get(Tag_Jenis_kelamin);
//
//            data data = new data();
//
//            data.setId(id);
//            data.setNama(poster);
//            data.setJenis_kelamin(title);
//
//            item.add(data);
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        item.clear();
//        getData();
//    }

        recyclerView = findViewById(R.id.rv_list_dokter);

        //Tampilkan Data




//        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                final String name = tv_nama.getText().toString();
//                final String nip = tv_nip.getText().toString();
//                final String tmt = tv_tmt.getText().toString();
//                final String masakerja = tv_masakerja.getText().toString();
//                final String keterangan = tv_keterangan.getText().toString();
//                final CharSequence[] dialogitem = {"Edit", "Delete"};
//                alert = new AlertDialog.Builder(ListDataDokter.this);
//                alert.setCancelable(true);
//                alert.setItems(dialogitem , new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which){
//                            case 0:
//                                Intent intent = new Intent(ListDataDokter.this, inputdata.class);
//                                intent.putExtra("nama",name );
//                                intent.putExtra("nip", nip);
//                                intent.putExtra("tmt", tmt);
//                                intent.putExtra("masakerja", masakerja);
//                                intent.putExtra("keterangan", keterangan);
//                                startActivity(intent);
//                                break;
//
//                            case 1:
//
//                        }
//
//                    }
//                });
//                return false;
//            }
//        });


//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String name = tv_nama.getText().toString();
//                final String nip = tv_nip.getText().toString();
//                final String tmt = tv_tmt.getText().toString();
//                final String masakerja = tv_masakerja.getText().toString();
//                final String keterangan = tv_keterangan.getText().toString();
//                Intent intent = new Intent(ListDataDokter.this, inputdata.class);
//                intent.putExtra("nama",name );
//                intent.putExtra("nip", nip);
//                intent.putExtra("tmt", tmt);
//                intent.putExtra("masakerja", masakerja);
//                intent.putExtra("keterangan", keterangan);
//                startActivity(intent);
//
//            }
//        });

         apiInterface = Apiconfig.getApiClient().create(ApiInterface.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        list = this;

        refresh();

}

    public void loaddata(List<ModelApi> body) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setHasFixedSize(true);
//        adapterOnlineDokter = new AdapterOnlineDokter(body);
//        recyclerView.setAdapter(adapterOnlineDokter);

    }

     public void deletonte(int id){
        ApiInterface apiInterface1 = Apiconfig.getApiClient().create(ApiInterface.class);
        Call<ModelApi> listCall =apiInterface1.deleteNote(id);

        listCall.enqueue(new Callback<ModelApi>() {
            @Override
            public void onResponse(Call<ModelApi> call, Response<ModelApi> response) {

                   list.refresh();
//                     progressDialog.show();

//                     Toast.makeText(ListDataDokter.this,"Data Berhasil Dihapus",Toast.LENGTH_SHORT).show();
                     finish();
            }

            @Override
            public void onFailure(Call<ModelApi> call, Throwable t) {
                Toast.makeText(ListDataDokter.this,"Data GAGAL dihapus",Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void Update (int id ,String nama ,String nip,String tmt, String masa_kerja, String keterangan){
//        ApiInterface apiInterface = Apiconfig.getApiClient().create(ApiInterface.class);
//        Call<ModelApi> call = apiInterface.Update(id,nama,nip,tmt,masa_kerja,keterangan);
//
//        call.enqueue(new Callback<ModelApi>() {
//            @Override
//            public void onResponse(Call<ModelApi> call, Response<ModelApi> response) {
//                response.isSuccessful();
//                response.body();
//              finish();
//            }
//
//            @Override
//            public void onFailure(Call<ModelApi> call, Throwable t) {
//                Toast.makeText(ListDataDokter.this,"Data GAGAL Update",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }

    public void refresh (){
//        ApiInterface apiInterface = Apiconfig.getApiClient().create(ApiInterface.class);

        Call<List<ModelApi>> listCall = apiInterface.getdata();


        listCall.enqueue(new Callback<List<ModelApi>>() {
            @Override
            public void onResponse(Call<List<ModelApi>> call, Response<List<ModelApi>> response) {
//                loaddata(response.body());
                List<ModelApi> modelApis = response.body();
                Radapter= new AdapterOnlineDokter(modelApis);
                recyclerView.setAdapter(Radapter);
            }

            @Override
            public void onFailure(Call<List<ModelApi>> call, Throwable t) {
                Toast.makeText(ListDataDokter.this, "Unable to load users", Toast.LENGTH_SHORT).show();

            }
        });
    }


}



