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
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;

import java.net.ServerSocket;
import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.SECONDS;


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
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main2);
        beepForAnHour();
//        testVolatile();
//        testThread();
//        Thread thread = new Thread();
//        Object object = new Object();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        Future<String> submit = executorService.submit(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "xxxxx";
//            }
//        });
//        try {
//            String s = submit.get();
//            Toast.makeText(this, "s " + s, Toast.LENGTH_LONG).show();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        object.notify();
//        try {
//            thread.join();
//            thread.interrupt();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        initListView();
        CustomMoveView customMoveView = findViewById(R.id.custom);
//        customMoveView.smoothScrollTo(-400,-500);
        TextView textView = findViewById(R.id.tvMian);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animator(textView);
        }
        final TextInputLayout textInputLayout = findViewById(R.id.tI_edit);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textInputLayout.getEditText().getText().toString();
                if (s.length() < 6) {
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setError("11111");
                } else {
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
        if (navigationView != null) {
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
        menuInflater.inflate(R.menu.item, menu);
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

        for (int i = 0; i < titles.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles.get(i)));
        }

        final List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
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

    public void showSnackBar() {
        Snackbar.make(rvHome, "xxx", Snackbar.LENGTH_LONG)
                .setAction("click", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "请手动将通知打开", Toast.LENGTH_SHORT).show();
                    }
                }).setDuration(1000).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void animator(final View view) {
        @SuppressLint("ObjectAnimatorBinding") final ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationX", 0, 500, 0, 500);
        objectAnimator.setDuration(3000);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ValueAnimator valueAnimator = ValueAnimator.ofArgb(Color.RED, Color.BLACK, Color.YELLOW);
        valueAnimator.setTarget(view);
        valueAnimator.setDuration(3000);
//        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                view.setBackgroundColor(value);
            }
        });
//        ObjectAnimator animator = ObjectAnimator.ofInt(view, "BackgroundColor", 0xffff00ff, 0xffffff00, 0xffff00ff);
//        animator.setDuration(8000);
//        animator.setEvaluator(new ArgbEvaluator());
//        animator.start();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(5000).playTogether(objectAnimator, valueAnimator);
        animatorSet.start();

//        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("rotation", 0,180,0,-180,0);
//        PropertyValuesHolder propertyValuesHolder1= PropertyValuesHolder.ofFloat("scaleX", 0,1,0,1);
//        PropertyValuesHolder propertyValuesHolder2 = PropertyValuesHolder.ofFloat("alpha", 0,1,0,1);
//        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(view, propertyValuesHolder, propertyValuesHolder1, propertyValuesHolder2);
//        objectAnimator1.setDuration(5000).start();

        Animator animator = AnimatorInflater.loadAnimator(this, R.animator.scale);
        animator.setTarget(view);
        animator.start();
    }

    public class ArgbEvaluator implements TypeEvaluator {
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            int startInt = (Integer) startValue;
            int startA = (startInt >> 24);
            int startR = (startInt >> 16) & 0xff;
            int startG = (startInt >> 8) & 0xff;
            int startB = startInt & 0xff;

            int endInt = (Integer) endValue;
            int endA = (endInt >> 24);
            int endR = (endInt >> 16) & 0xff;
            int endG = (endInt >> 8) & 0xff;
            int endB = endInt & 0xff;

            return (int) ((startA + (int) (fraction * (endA - startA))) << 24) |
                    (int) ((startR + (int) (fraction * (endR - startR))) << 16) |
                    (int) ((startG + (int) (fraction * (endG - startG))) << 8) |
                    (int) ((startB + (int) (fraction * (endB - startB))));
        }
    }

    public void initListView() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        list1.add(1);
        List<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        list2.add(2);
        List<Integer> list3 = new ArrayList<>();
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        list3.add(3);
        ListView listView1 = findViewById(R.id.lv1);
        ListView listView2 = findViewById(R.id.lv2);
        ListView listView3 = findViewById(R.id.lv3);
        listView1.setAdapter(new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, list1));
        listView2.setAdapter(new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, list2));
        listView3.setAdapter(new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, list3));
    }

    private void testThread() {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
        try {
            Thread.sleep(100);
            synchronized (object) {
                object.notify();
            }
            Thread.sleep(100);
            myRunnable.setStop(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.interrupt();
        final Alipay alipay = new Alipay(10, 1000);
        final Random random = new Random();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        int from = random.nextInt(10);
                        int to = random.nextInt(10);
                        if (from != to) {
                            alipay.transfer(from, to, 600);
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Thread(runnable));
            executorService.submit(runnable);
        }
//        for(Thread thread1: list){
//            thread1.start();
//        }
        try {
            Thread.sleep(2000);
            int total = 0;
            for (int i = 0; i < 10; i++) {
                total += alipay.accounts[i];
            }
            Log.d("macy7", "totalNum== " + total);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    final Object object = new Object();

    class MyRunnable implements Runnable {
        long i;
        private volatile boolean isStop = false;

        @Override
        public void run() {
            long timeStart = System.currentTimeMillis();
            Log.d("macy7", "start--> " + timeStart);
            while (!isStop) {
                i++;
                if (i == 500) {
                    try {
                        synchronized (object) {
                            object.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d("macy7", " stop end------> " + (System.currentTimeMillis() - timeStart)
                    + " i=" + i);
        }

        private void setStop(boolean isStop) {
            this.isStop = isStop;
        }
    }

    static class Alipay {
        private double[] accounts;
        private Lock alipayLock;
        private Condition condition;

        public Alipay(int n, double money) {
            accounts = new double[n];
            alipayLock = new ReentrantLock();
            condition = alipayLock.newCondition();
            for (int i = 0; i < n; i++) {
                accounts[i] = money;
            }
        }

        public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
//            alipayLock.lock();
            try {
                while (accounts[from] < amount) {
                    Log.d("macy7", "await--> from=" + from
                            + " to=" + to + " " + accounts[from]);
//                    condition.await();
                    wait();
                }
                accounts[from] = accounts[from] - amount;
                accounts[to] = accounts[to] + amount;
                Log.d("macy7", "signalAll--> from=" + from
                        + " to=" + to + " " + accounts[from] + " " + accounts[to]);
//                condition.signalAll();
                notifyAll();
            } finally {
//                alipayLock.unlock();
            }


        }
    }

    static class Test {
        private volatile int inc = 0;

        void increase() {
            inc++;
        }
    }

    private void testVolatile() {
        final Test test = new Test();
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                }
            }).start();
        }

//        while (Thread.activeCount() > 2){
//            Thread.yield();
//        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("macy7", " final value == " + test.inc);
    }

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public void beepForAnHour() {
        final Runnable beeper1 = new Runnable() {

            public void run() {

                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                测试scheduleAtFixedRate方法时，放开此注释，有异常，请扑获

                System.out.println("beep1");
            }

        };

        final Runnable beeper2 = new Runnable() {

            public void run() {

                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
// 测试scheduleWithFixedDelay方法时，放开此注释，有异常，请扑获

                System.out.println("beep2");
            }

        };


        final ScheduledFuture<?> beeperHandle1 =

                scheduler.scheduleAtFixedRate(beeper1, 5, 5, SECONDS);

        final ScheduledFuture<?> beeperHandle2 =
                scheduler.scheduleAtFixedRate(beeper2, 5, 5, SECONDS);


        scheduler.schedule(new Runnable() {

            public void run() {
                beeperHandle1.cancel(true);
            }
        }, 20, SECONDS);

        scheduler.schedule(new Runnable() {

            public void run() {
                beeperHandle2.cancel(true);
            }
        }, 20, SECONDS);
    }

}
