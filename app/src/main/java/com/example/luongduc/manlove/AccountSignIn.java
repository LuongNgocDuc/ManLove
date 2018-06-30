package com.example.luongduc.manlove;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.luongduc.manlove.database.DbManager;

public class AccountSignIn extends AppCompatActivity {
    Button btnSignIn, btnRegister;
    EditText edtAccountSignIn, edtPasswordSignIn,
            edtAccountRegister, edtPasswordRegister, edtConfirmPasswordRegister, edtEmailOrTelephone;
    private String accountSignIn;
    private String passwordSignIn;
    private String accountRegister;
    private String passWordRegister;
    private String confrimPassWordRegister;
    private String emailOrTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sign_in);
        setUpTabHostSignIn();
        setUpToolBar();
        setUpDataBase();
        setUpSignIn();
        setUpRegister();

    }

    private void setUpToolBar() {
        Toolbar toolbar = findViewById(R.id.tool_bar_information_account);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.back_profire);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpDataBase() {
        createTable();
        cursorDataTable();
    }

    private void cursorDataTable() {
        DbManager dbManager = new DbManager(this, "informationAccount.sqlite", null, 1);
        Cursor cursor = dbManager.getData("SELECT * FROM informationAccount");
    }


    private void createTable() {
        DbManager dbManager = new DbManager(this, "informationAccount.sqlite", null, 1);
        dbManager.queryData("CREATE TABLE IF NOT EXISTS informationAccount (Id INTEGER PRIMARY KEY AUTOINCREMENT, Account VARCHAR, Password VARCHAR, EmailOrTel VARCHAR)");
    }

    private void setUpRegister() {
        edtAccountRegister = findViewById(R.id.edt_account_register);
        edtPasswordRegister = findViewById(R.id.edt_password_register);
        edtConfirmPasswordRegister = findViewById(R.id.edt_confirm_password_register);
        edtEmailOrTelephone = findViewById(R.id.edt_email_or_tel_register);
        btnRegister = findViewById(R.id.btn_register);

        edtAccountRegister.addTextChangedListener(textWatcherRegister);
        edtPasswordRegister.addTextChangedListener(textWatcherRegister);
        edtConfirmPasswordRegister.addTextChangedListener(textWatcherRegister);
        edtEmailOrTelephone.addTextChangedListener(textWatcherRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AccountSignIn.this, "Register Success", Toast.LENGTH_SHORT).show();
                setUpInsertData();
            }
        });

    }

    private void setUpInsertData() {
        DbManager dbManager = new DbManager(this, "informationAccount.sqlite", null, 1);
        dbManager.insetData(accountRegister,passWordRegister,emailOrTel);
    }

    private TextWatcher textWatcherRegister = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            accountRegister = edtAccountRegister.getText().toString();
            passWordRegister = edtPasswordRegister.getText().toString();
            confrimPassWordRegister = edtConfirmPasswordRegister.getText().toString();
            emailOrTel = edtEmailOrTelephone.getText().toString();

            btnRegister.setEnabled(!accountRegister.isEmpty() && passWordRegister.equals(confrimPassWordRegister) && !emailOrTel.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void setUpSignIn() {
        btnSignIn = findViewById(R.id.btn_signin);
        edtAccountSignIn = findViewById(R.id.edt_account_signin);
        edtPasswordSignIn = findViewById(R.id.edt_password_signin);
        edtAccountSignIn.addTextChangedListener(textWatcherSignIn);
        edtPasswordSignIn.addTextChangedListener(textWatcherSignIn);

    }

    private TextWatcher textWatcherSignIn = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            accountSignIn = edtAccountSignIn.getText().toString();
            passwordSignIn = edtPasswordSignIn.getText().toString();
            DbManager dbManager = new DbManager(getApplicationContext(), "informationAccount.sqlite", null, 1);
            Cursor cursor = dbManager.getData("SELECT * FROM informationAccount");
            while (cursor.moveToNext()){
                if (accountSignIn.equals(cursor.getString(1))&& passwordSignIn.equals(cursor.getString(2))){
                    btnSignIn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(AccountSignIn.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AccountSignIn.this, ContentActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void setUpTabHostSignIn() {
        TabHost tabHost = findViewById(R.id.tab_host_account);
        tabHost.setup();
        TabHost.TabSpec tabOne = tabHost.newTabSpec("t3");
        tabOne.setIndicator("Đăng Nhập");
        tabOne.setContent(R.id.tab_signin);
        tabHost.addTab(tabOne);

        TabHost.TabSpec tabTwo = tabHost.newTabSpec("t4");
        tabTwo.setIndicator("Đăng Kí");
        tabTwo.setContent(R.id.tab_register);
        tabHost.addTab(tabTwo);

    }
}
