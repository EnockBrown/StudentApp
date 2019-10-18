package com.example.studentapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.studentapp.R;
import com.example.studentapp.fragments.ExamCard;
import com.example.studentapp.fragments.ResultsFragment;
import com.example.studentapp.fragments.UnitsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Academics extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academics);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new UnitsFragment());
    }

    private void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment=null;
        switch(menuItem.getItemId()){
            case R.id.units:
                fragment=new UnitsFragment();
                break;
            case R.id.results:
                fragment=new ResultsFragment();
                break;
            case R.id.exam_card:
                fragment=new ExamCard();
                break;
        }
        if (fragment!=null){
            displayFragment(fragment);
        }
        return false;
    }
}
