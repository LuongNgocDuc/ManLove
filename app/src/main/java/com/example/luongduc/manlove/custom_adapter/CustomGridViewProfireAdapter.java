package com.example.luongduc.manlove.custom_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luongduc.manlove.R;
import com.example.luongduc.manlove.modul.ListGridViewMyProfire;

import java.util.ArrayList;

public class CustomGridViewProfireAdapter extends BaseAdapter {
    Context context;
    int layout;
    ArrayList<ListGridViewMyProfire> listGridViewMyProfires;

    public CustomGridViewProfireAdapter(Context context, int layout, ArrayList<ListGridViewMyProfire> listGridViewMyProfires) {
        this.context = context;
        this.layout = layout;
        this.listGridViewMyProfires = listGridViewMyProfires;
    }

    @Override
    public int getCount() {
        return listGridViewMyProfires.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout, parent, false);
        ListGridViewMyProfire listGridViewMyProfire = listGridViewMyProfires.get(position);
        ImageView imgGridView = convertView.findViewById(R.id.img_list_grid_profire);
        TextView txtGridView = convertView.findViewById(R.id.txt_list_grid_profire);
        imgGridView.setImageResource(listGridViewMyProfire.getImgGridViewProfire());
        txtGridView.setText(listGridViewMyProfire.getNameGridViewProfire());
        return convertView;
    }
}
