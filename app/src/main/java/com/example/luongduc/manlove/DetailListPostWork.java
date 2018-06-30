package com.example.luongduc.manlove;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luongduc.manlove.database.DbManagerListContent;

import java.io.ByteArrayOutputStream;

public class DetailListPostWork extends AppCompatActivity {
    ImageView imgUserDetail;
    TextView txtNameUserDetail,txtAgeUserDetail,txtWorkDetail;
    Button btnRent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list_post_work);
        txtNameUserDetail = findViewById(R.id.txt_name_user_detai_list_post_work);
        txtAgeUserDetail = findViewById(R.id.txt_age_user_detail_list_post_work);
        txtWorkDetail = findViewById(R.id.txt_work_detail);
        imgUserDetail = findViewById(R.id.img_detail_list_post_work);

        Intent intent = getIntent();
       DbManagerListContent db = new DbManagerListContent(this, "listWorkContent.sqlite", null, 1);
       Cursor cursor = db.getDataList("SELECT * FROM listWorkContent");

        imgUserDetail.setImageResource(intent.getIntExtra("imgUser", 22));
        txtNameUserDetail.setText(intent.getStringExtra("nameUser"));
        txtAgeUserDetail.setText(intent.getStringExtra("ageUser"));
        txtWorkDetail.setText(intent.getStringExtra("workPost"));

        Toolbar toolbar = findViewById(R.id.tool_bar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_profire);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public int Image_to_byte(ImageView h) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) h.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        int img = Integer.parseInt(byteArrayOutputStream.toString());
        return img;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void rent(View view) {
        Intent intent = new Intent(DetailListPostWork.this,ContentActivity.class);
        startActivity(intent);
        Toast.makeText(this, "hiiiiii", Toast.LENGTH_SHORT).show();
    }
}
