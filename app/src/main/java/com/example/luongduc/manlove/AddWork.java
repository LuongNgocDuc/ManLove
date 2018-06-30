package com.example.luongduc.manlove;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.luongduc.manlove.database.DbManagerListContent;
import com.example.luongduc.manlove.modul.ListWork;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddWork extends AppCompatActivity {
    EditText edtNameAdd, edtAgeAdd, edtDescribleWorkAdd;
    Button btnAdd;
    ImageView imgCapture;
    private String nameWorkAdd;
    private String ageWorkAdd;
    private String describleWorkAdd;
    int numBer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        setUpToolBar();
        setUpAddWork();
    }

    private void setUpAddWork() {
        edtNameAdd = findViewById(R.id.edt_name_add_listwork);
        edtAgeAdd = findViewById(R.id.edt_age_add_listwork);
        edtDescribleWorkAdd = findViewById(R.id.edt_describle_work_add_listwork);
        edtNameAdd.addTextChangedListener(textWatcher);
        edtAgeAdd.addTextChangedListener(textWatcher);
        edtDescribleWorkAdd.addTextChangedListener(textWatcher);

        setUpCaptureImg();

        btnAdd = findViewById(R.id.btn_add_list);
        final DbManagerListContent db = new DbManagerListContent(this, "listWorkContent.sqlite", null, 1);
        db.queryDataList("CREATE TABLE IF NOT EXISTS listWorkContent (ID INTEGER PRIMARY KEY AUTOINCREMENT, IMG BLOB, NAMEUSER VARCHAR, AGEUSER VARCHAR, DESCRIBLEWORK VARCHAR)");
        db.close();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtNameAdd.equals("") && !edtAgeAdd.equals("") && !edtDescribleWorkAdd.equals("")) {
                    try {
                        db.insertDataList(Image_to_byte(imgCapture),
                                edtNameAdd.getText().toString(),
                                edtAgeAdd.getText().toString(),
                                edtDescribleWorkAdd.getText().toString());
                        Toast.makeText(AddWork.this, "Add Work Success", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                    }
                    finish();
                } else if (edtNameAdd.equals("") && edtAgeAdd.equals("") && edtDescribleWorkAdd.equals("")) {
                    Toast.makeText(AddWork.this, "Mistake capture or write fill all field ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setUpCaptureImg() {
        imgCapture = findViewById(R.id.img_capture_user);
        imgCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 3333);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 3333 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgCapture.setImageBitmap(bitmap);
        }

    }

    public byte[] Image_to_byte(ImageView h) {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) h.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return byteArray;

    }

    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            nameWorkAdd = edtNameAdd.getText().toString();
            ageWorkAdd = edtAgeAdd.getText().toString();
            describleWorkAdd = edtDescribleWorkAdd.getText().toString();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar_add_work);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_profire);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
