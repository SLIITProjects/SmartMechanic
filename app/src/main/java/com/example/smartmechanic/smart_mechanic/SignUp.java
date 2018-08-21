package com.example.smartmechanic.smart_mechanic;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SignUp extends AppCompatActivity {

    private TabLayout tablayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tablayout = (TabLayout)findViewById(R.id.signUpTab);
        appBarLayout = (AppBarLayout)findViewById(R.id.signUpAppBar);
        viewPager = (ViewPager)findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FragmentCustomer(),"Customer Registration");
        adapter.AddFragment(new FragmentMechanic(),"Mechanic Registration");

        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
}
