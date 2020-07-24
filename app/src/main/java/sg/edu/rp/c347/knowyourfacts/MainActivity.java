package sg.edu.rp.c347.knowyourfacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentAdapter adapter;
    ViewPager viewPager;
    Button btnReadLater;
    int reqCode = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.ViewPager);
        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add((new Frag1()));
        al.add((new Frag2()));

        btnReadLater = findViewById(R.id.btnReadLater);

        adapter = new MyFragmentAdapter(fm,al);

        viewPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MINUTE,5);

                Intent intent = new Intent(MainActivity.this,NotificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,reqCode,intent,PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),pendingIntent);


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_Next) {
            int max = viewPager.getChildCount();
            if (viewPager.getCurrentItem()<max-1){
                int nextPage = viewPager.getCurrentItem()+1;
                viewPager.setCurrentItem(nextPage,true);
            }
        }else if (id == R.id.action_Previous){
            if (viewPager.getCurrentItem()>0){
                int previousPage = viewPager.getCurrentItem()-1;
                viewPager.setCurrentItem(previousPage,true);
            }
        }else if (id == R.id.action_Random){
            int max = viewPager.getChildCount();
            if(viewPager.getCurrentItem() >= 0 && viewPager.getCurrentItem() <= max ){
                int i = (int)(Math.random()*(max - 0 + 1) + 0);
                viewPager.setCurrentItem(i,true);
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
