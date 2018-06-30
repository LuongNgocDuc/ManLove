package com.example.luongduc.manlove.custom_adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luongduc.manlove.DetailListPostWork;
import com.example.luongduc.manlove.R;
import com.example.luongduc.manlove.modul.ListWork;

import java.util.ArrayList;

public class CustomListWorkAdapter extends BaseAdapter {
    public Context context;
    public int layout;
    public ArrayList<ListWork> listWorkArrayList;

    public CustomListWorkAdapter(Context context, int layout, ArrayList<ListWork> listWorkArrayList) {
        this.context = context;
        this.layout = layout;
        this.listWorkArrayList = listWorkArrayList;
    }

    @Override
    public int getCount() {
        return listWorkArrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.custom_lv_list_work, parent, false);
        final ListWork listWork = listWorkArrayList.get(position);
        ImageView imgWork = convertView.findViewById(R.id.img_custom_lv_list_work);
        TextView txtNameUser = convertView.findViewById(R.id.txt_name_user);
        TextView txtAgeUser = convertView.findViewById(R.id.txt_age_user);
        TextView txtUserPostWork = convertView.findViewById(R.id.txt_work);

           Bitmap bitmap = BitmapFactory.decodeByteArray(listWork.byteImgArr, 0, listWork.byteImgArr.length);
           imgWork.setImageBitmap(bitmap);

        //imgWork.setImageResource(listWork.getImgUser());
        txtNameUser.setText(listWork.getNameUser());
        txtAgeUser.setText(listWork.getAgeUser());
        txtUserPostWork.setText(listWork.getWorkUser());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailListPostWork.class);
                intent.putExtra("imgUser", listWork.getImgUser()); // sua lai kieu put img ****
                intent.putExtra("nameUser", listWork.getNameUser());
                intent.putExtra("ageUser", listWork.getAgeUser());
                intent.putExtra("workPost", listWork.getWorkUser());
                context.startActivity(intent);
            }
        });
        return convertView;
    }


}
