package com.mcy.define;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class MainActivity extends AppCompatActivity {

    RecyclerView rvHome;
    Toolbar toolbar;
    Bitmap bitmap;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputLayout textInputLayout = findViewById(R.id.tI_edit);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textInputLayout.getEditText().getText().toString();
                if(s.length() < 6){
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("11111");
                }else {
                    textInputLayout.setErrorEnabled(false);
                }

            }
        });

        rvHome = findViewById(R.id.rvHome);
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
//        supportActionBar.setHomeAsUpIndicator(R.drawable.t);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawLayoutFg);
        NavigationView navigationView = findViewById(R.id.design_navigation_view);
        if(navigationView != null){
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    menuItem.setChecked(true);
                    String s = menuItem.getTitle().toString();
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
                    drawerLayout.closeDrawers();
                    return false;
                }
            });
        }
        viewPager = findViewById(R.id.viewpager);
        initViePager();
        findViewById(R.id.floatBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackBar();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvHome.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));

        rvHome.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        rvHome.addItemDecoration(new DividerGridItemDecoration(this, R.drawable.divider));
        HomeAdapter homeAdapter = new HomeAdapter();
        homeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(View view, int position) {
//                if (position % 2 == 0) {
//                    showNotification();
//                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.t);
//                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                        @Override
//                        public void onGenerated(@Nullable Palette palette) {
//                            assert palette != null;
//                            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//                            MainActivity.this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(vibrantSwatch.getRgb()));
//                        }
//                    });
//                } else {
//                    showNotification2();
//                    bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
//                    Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                        @Override
//                        public void onGenerated(@Nullable Palette palette) {
//                            assert palette != null;
//                            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//                            MainActivity.this.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(vibrantSwatch.getRgb()));
//                        }
//                    });
//                }
//                requestPermission();
                showSnackBar();
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "xxx" + position, Toast.LENGTH_SHORT).show();
            }
        });
        rvHome.setAdapter(homeAdapter);
        RecyclerView recyclerView = findViewById(R.id.rvrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new HomeAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.item,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item4) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    TabLayout tabLayout;
    private void initViePager() {
        tabLayout = findViewById(R.id.tabs);
        final List<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("体育");
        titles.add("杂志");
        titles.add("汽车");
        titles.add("世界");
        titles.add("中国");
        titles.add("天下");
        titles.add("人民");
        titles.add("潍坊");
        titles.add("医疗");
        titles.add("新型");

        for(int i=0; i<titles.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }

        final List<Fragment> fragments = new ArrayList<>();
        for(int i=0;i <titles.size();i++){
            fragments.add(new ListFragment());
        }

        final FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(fragmentPagerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("111111111111111");
        builder.setNumber(1);
        Notification notification = builder.build();
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.home_adaper);
        notification.bigContentView = remoteViews;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1", "11", NotificationManager.IMPORTANCE_HIGH);
            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel("1");
            if (notificationChannel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent1 = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent1.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
                intent1.putExtra(Settings.EXTRA_CHANNEL_ID, notificationChannel.getId());
                startActivity(intent1);
                Toast.makeText(this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        }
        notificationManager.notify(1, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showNotification2() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "2");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("22222222222");
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        assert notificationManager != null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("2", "22", NotificationManager.IMPORTANCE_DEFAULT));
        }
        notificationManager.notify(2, notification);
    }

    public void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this
                        , Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setMessage("该功能需要访问电话的权限，不开启将")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(MainActivity.this, "权限被拒绝", Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }).create();
                    dialog.show();
                }

            }
        } else {

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    public void showSnackBar(){
        Snackbar.make(rvHome,"xxx", Snackbar.LENGTH_LONG)
                .setAction("click", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
                    }
                }).setDuration(1000).show();
    }
}
