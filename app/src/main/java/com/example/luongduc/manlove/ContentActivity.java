package com.example.luongduc.manlove;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.luongduc.manlove.custom_adapter.CustomListWorkAdapter;
import com.example.luongduc.manlove.database.DbManagerListContent;
import com.example.luongduc.manlove.modul.ListWork;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ContentActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public ActionBarDrawerToggle drawerToggle;
    public DrawerLayout drawerLayout;
    public CustomListWorkAdapter customListWorkAdapter;
    public ArrayList<ListWork> listWorkArrayList;
    private CustomListWorkAdapter adapterTabHostTwo;

    // Su dung ViewPager, Fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
      //  DbManagerListContent dbManagerListContent = new DbManagerListContent(this, "listWorkContent.sqlite", null, 1);
      //  dbManagerListContent.clearTable();
        setUpToolBarAndToggleDrawer();
        setUpTabHost();
        setUpEventNavigation();
        setUpTimerShowDialog();
//        setUpImageNavCircle();
       setUpRecyclerListWorkTabOne();
        setUpRecyclerFindWorkTabTwo();
        //  setUpGetIntentData();
        setUpBottomNavigation();

    }

    private void setUpGetIntentData() {
        Intent intent = getIntent();
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, intent.getStringExtra("nameUserPost"),
                intent.getStringExtra("ageUserPost"), intent.getStringExtra("describleUserPost")));
        customListWorkAdapter.notifyDataSetChanged();
    }

    private void setUpImageNavCircle() {
        ImageView imgNav = findViewById(R.id.img_nav);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        imgNav.setImageDrawable(roundedBitmapDrawable);
    }

    private void setUpBottomNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.find_work:
                        Intent intent1 = new Intent(ContentActivity.this, AddWork.class);
                        startActivity(intent1);
                        Toast.makeText(ContentActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.add_work:
                        Intent intent2 = new Intent(ContentActivity.this, AddWork.class);
                        startActivity(intent2);
                        break;
                    case R.id.my_profire:
                        Intent intent3 = new Intent(ContentActivity.this, MyProfire.class);
                        startActivity(intent3);
                        break;
                    case R.id.contact:
                        break;
                }
                return true;
            }
        });
    }

    private void setUpRecyclerFindWorkTabTwo() {

        ListView listView = findViewById(R.id.lv_tab_one);
        ArrayList<ListWork> listWorkArrayList = new ArrayList<ListWork>();

        DbManagerListContent db = new DbManagerListContent(this, "listWorkContent.sqlite", null, 1);
        db.queryDataList("CREATE TABLE IF NOT EXISTS listWorkContent (ID INTEGER PRIMARY KEY AUTOINCREMENT, IMG BLOB, NAMEUSER VARCHAR, AGEUSER VARCHAR, DESCRIBLEWORK VARCHAR)");

        DbManagerListContent dbManagerListContent = new DbManagerListContent(this, "listWorkContent.sqlite", null, 1);
        Cursor cursor = dbManagerListContent.getDataList("SELECT * FROM listWorkContent");
        while (cursor.moveToNext()) {
            listWorkArrayList.add(new ListWork(cursor.getBlob(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4)));
        }
        db.close();
        adapterTabHostTwo = new CustomListWorkAdapter(this, R.layout.custom_lv_list_work, listWorkArrayList);
        listView.setAdapter(adapterTabHostTwo);

    }

    private void setUpRecyclerListWorkTabOne() {
        ListView listView = findViewById(R.id.lv_tab_two);
        listWorkArrayList = new ArrayList<ListWork>();
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        listWorkArrayList.add(new ListWork(R.mipmap.my_profire, "Nguyễn Văn A", "25", "Play game"));
        customListWorkAdapter = new CustomListWorkAdapter(this, R.layout.custom_lv_list_work, listWorkArrayList);
        listView.setAdapter(customListWorkAdapter);
    }

    private void setUpTimerShowDialog() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(ContentActivity.this);
                        builder.setTitle("Register Account");
                        builder.setMessage("Bạn đã có tài khoản chưa." + "\n" + "Vui lòng đăng kí để được hỗ trợ.");
                        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(ContentActivity.this, AccountSignIn.class);
                                startActivity(intent);
                            }
                        });
                        builder.show();
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 10000000);
    }

    private void setUpEventNavigation() {
        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_item:
                        Toast.makeText(ContentActivity.this, "1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.introduce_item:
                        break;
                    case R.id.support_item:
                        break;
                    case R.id.account_item:
                        Intent intent = new Intent(ContentActivity.this, AccountSignIn.class);
                        startActivity(intent);
                        break;
                    case R.id.log_out_item:
                        break;
                }
                return true;
            }
        });
    }


    private void setUpTabHost() {
        TabHost tabHost = findViewById(R.id.tab_host_content);
        tabHost.setup();
        TabHost.TabSpec tabOne = tabHost.newTabSpec("t1");
        tabOne.setIndicator("Danh sách ứng viên");
        tabOne.setContent(R.id.tab1);
        tabHost.addTab(tabOne);

        TabHost.TabSpec tabTwo = tabHost.newTabSpec("t2");
        tabTwo.setIndicator("Danh sách công việc");
        tabTwo.setContent(R.id.tab2);
        tabHost.addTab(tabTwo);
    }

    private void setUpToolBarAndToggleDrawer() {

        drawerLayout = findViewById(R.id.drawer_content);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        toolbar = findViewById(R.id.tool_bar_content);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.menu_drawer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
