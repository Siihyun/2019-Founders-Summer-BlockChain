package com.example.whiteticket.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.whiteticket.Data.User;
import com.example.whiteticket.Fragment.BoardFragment;
import com.example.whiteticket.Fragment.HomeFragment;
import com.example.whiteticket.Fragment.MypageFragment;
import com.example.whiteticket.Fragment.SearchFragment;
import com.example.whiteticket.R;
import com.iammert.library.readablebottombar.BottomBarItem;
import com.iammert.library.readablebottombar.ReadableBottomBar;

public class MainActivity extends AppCompatActivity {

    ReadableBottomBar bottomBar;

    private User user = null;
    Fragment fragment = null;
    private FragmentManager fragmentManager;
    int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = getIntent().getParcelableExtra("User");
        if(user == null){
            user = new User("abc", "abc");
            System.out.println("user null setting default");
        }

        fragmentManager = getSupportFragmentManager();

        // HomeActivity의 Default Fragment 설정
        if (savedInstanceState == null) {
            fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putParcelable("User", user);
            fragment.setArguments(args);
            getSupportFragmentManager().
                    beginTransaction().replace(R.id.main_container, fragment).commit();
        }


        bottomBar = findViewById(R.id.main_bottom_bar);

        bottomBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {
                flag = 0;
                Log.d("mainactivity", "onItemSelected: " + i);

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                switch (i) {
                    case 0:
                        if (!(currentFragment instanceof HomeFragment)) {
                            fragment = new HomeFragment().newInstance(user);
                            Bundle args = new Bundle();
                            args.putParcelable("User", user);
                            fragment.setArguments(args);
                        }
                        break;
                    case 1:
                        if (!(currentFragment instanceof SearchFragment)) {
                            fragment = new SearchFragment().newInstance(user);
                            Bundle args = new Bundle();
                            args.putParcelable("User", user);
                            fragment.setArguments(args);
                        }
                        break;
                    case 2:
                        if (!(currentFragment instanceof BoardFragment)) {
                            fragment = new BoardFragment().newInstance(user);
                            Bundle arg = new Bundle();
                            arg.putParcelable("User", user);
                            fragment.setArguments(arg);
                        }
                        break;
                    case 4:
                        if (!(currentFragment instanceof MypageFragment)) {
                            fragment = new MypageFragment().newInstance(user);
                            Bundle arg = new Bundle();
                            arg.putParcelable("User", user);
                            fragment.setArguments(arg);
                        }
                        break;
                    case 3:

                        break;

                        default:
                            System.out.println("Error Occured!");
                            break;
                }
                if (flag == 1) {
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment, "TAG").commit();
                }
            }
        });
    }
}
