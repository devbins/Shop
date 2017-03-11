package com.dev.bins.shop;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.dev.bins.shop.fragment.Cart.CartFragment;
import com.dev.bins.shop.fragment.category.CategoryFragment;
import com.dev.bins.shop.fragment.home.HomeFragment;
import com.dev.bins.shop.fragment.find.FindFragment;
import com.dev.bins.shop.fragment.me.MeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNavigation;
    private List<Fragment> mFragments = new ArrayList<Fragment>(5);
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();
        init();
        mBottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = mFragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.home:
                        transaction.replace(R.id.content, mFragments.get(0));
                        break;
                    case R.id.hot:
                        transaction.replace(R.id.content, mFragments.get(1));
                        break;
                    case R.id.category:
                        transaction.replace(R.id.content, mFragments.get(2));
                        break;
                    case R.id.cart:
                        transaction.replace(R.id.content, mFragments.get(3));
                        break;
                    case R.id.me:
                        transaction.replace(R.id.content, mFragments.get(4));
                        break;
                }
                transaction.commit();
                return true;
            }
        });

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, mFragments.get(0));
        fragmentTransaction.commit();

    }

    private void init() {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(FindFragment.newInstance());
        mFragments.add(CategoryFragment.newInstance());
        mFragments.add(CartFragment.newInstance());
        mFragments.add(MeFragment.newInstance());

    }


}
