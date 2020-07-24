package sg.edu.rp.c347.knowyourfacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentAdapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.ViewPager);
        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add((new Frag1()));
        al.add((new Frag2()));

        adapter = new MyFragmentAdapter(fm,al);

        viewPager.setAdapter(adapter);

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
        }
        return super.onOptionsItemSelected(item);
    }
}
