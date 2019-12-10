package com.example.coals.instargramnewstest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomMenu;
    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;
    private Fragment5 fragment5;
    private static final String TAG="MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomMenu = findViewById(R.id.bottomMenu);
        frameLayout = findViewById(R.id.frameLayout);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();
        Log.d(TAG,"onCreate");
        //bottomMenu 를 변경을 했을때 해당된 fragment를 세팅하는 리스너
        bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
//                    case R.id.action_1: //프레그먼트 화면전환
//                        setChangeFragment(0);
//                        break;
                    case R.id.action_2:
                        setChangeFragment(1);
                        break;
                    case R.id.action_3:
                        setChangeFragment(2);
                        break;
                    case R.id.action_4:
                        setChangeFragment(3);
                        break;
                    case R.id.action_5:
                        setChangeFragment(4);
                        break;
                }

                return true;
            }
        });
        setChangeFragment(0);
    }

    private void setChangeFragment(int position) {
        //화면을 전환하기 위해서는 fragment manager가 필요함
        fragmentManager = getSupportFragmentManager();
        //fragment manager 권한을 받아서 화면 체인지 하는 트랜잭션
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                fragmentTransaction.replace(R.id.frameLayout, fragment1);
                break;
            case 1:
                fragmentTransaction.replace(R.id.frameLayout, fragment2);
                break;
            case 2:
                fragmentTransaction.replace(R.id.frameLayout, fragment3);
                break;
            case 3:
                fragmentTransaction.replace(R.id.frameLayout, fragment4);
                break;
            case 4:
                fragmentTransaction.replace(R.id.frameLayout, fragment5);
                break;
        }
        fragmentTransaction.commit();
        return;
    }
}
