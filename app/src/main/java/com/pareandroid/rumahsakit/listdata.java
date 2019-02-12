package com.pareandroid.rumahsakit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.pareandroid.rumahsakit.model.data;
import com.pareandroid.rumahsakit.adapter.Adapter;
import com.pareandroid.rumahsakit.Database.DatabaseHelper;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class listdata extends AppCompatActivity {
    ListView userlist;
    AlertDialog.Builder alert;
    List<data> item = new ArrayList<data>();
    Adapter adapter;
    DatabaseHelper db = new DatabaseHelper(this);

    public static final String Tag_ID = "id";
    public static final String Tag_nama = "nama";
    public static final String Tag_Jenis_kelamin = "jenis_kelamin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        userlist = (ListView) findViewById(R.id.list_view);


        db = new DatabaseHelper(getApplicationContext());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listdata.this, inputdata.class);
                startActivity(intent);
            }
        });
        adapter = new Adapter(listdata.this, item);
        userlist.setAdapter(adapter);

        // long press listview to show edit and delete
        userlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String idx = item.get(position).getId();
                final String name = item.get(position).getNama();
                final String Jenis = item.get(position).getJenis_kelamin();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                alert = new AlertDialog.Builder(listdata.this);
                alert.setCancelable(true);
                alert.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                Intent intent = new Intent(listdata.this, inputdata.class);
                                intent.putExtra(Tag_ID, idx);
                                intent.putExtra(Tag_nama, name);
                                intent.putExtra(Tag_Jenis_kelamin, Jenis);
                                startActivity(intent);
                                break;
                            case 1:
                                db.delete(Integer.parseInt(idx));
                                item.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });

        getData();
    }

    private void getData() {
        ArrayList<HashMap<String, String>> row = db.getData();
        for (int i = 0; i < row.size(); i++) {
            String id = row.get(i).get(Tag_ID);
            String poster = row.get(i).get(Tag_nama);
            String title = row.get(i).get(Tag_Jenis_kelamin);

            data data = new data();

            data.setId(id);
            data.setNama(poster);
            data.setJenis_kelamin(title);

            item.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        item.clear();
        getData();
    }


}



