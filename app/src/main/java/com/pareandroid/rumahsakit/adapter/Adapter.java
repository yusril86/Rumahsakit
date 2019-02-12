package com.pareandroid.rumahsakit.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pareandroid.rumahsakit.R;
import com.pareandroid.rumahsakit.model.data;


import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<data> item;

    public Adapter(Activity activity, List<data> item){
        this.activity = activity;
        this.item = item;
    }
    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int location) {
        return item.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.content, null);

        TextView id = (TextView) convertView.findViewById(R.id.tv_id);
        TextView nama = (TextView) convertView.findViewById(R.id.tv_nama);
        TextView jenis_kelamin = (TextView) convertView.findViewById(R.id.tv_jeniskelamin);
        data data = item.get(position);

        id.setText(data.getId());
        nama.setText(data.getNama());
        jenis_kelamin.setText(data.getJenis_kelamin());
        return convertView;
    }


}
