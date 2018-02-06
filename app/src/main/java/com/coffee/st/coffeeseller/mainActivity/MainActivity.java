package com.coffee.st.coffeeseller.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.coffee.st.coffeeseller.R;
import com.coffee.st.coffeeseller.ScanQRActivity;
import com.coffee.st.coffeeseller.mainScreenFragment.MainScreenFragment;

import static com.coffee.st.coffeeseller.Const.MAIN_SCREEN_FRAGMENT_TAG;
import static com.coffee.st.coffeeseller.Const.RQCODE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FragmentManager mFragmentManager = getSupportFragmentManager();
    private FloatingActionButton mFAB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFAB = findViewById(R.id.fab);
        mFAB.setOnClickListener(this);
        showMainScreenFragment();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void showMainScreenFragment() {
        if (mFragmentManager.findFragmentByTag(MAIN_SCREEN_FRAGMENT_TAG) == null) {

            mFragmentManager.beginTransaction()
                    .add(R.id.main_frame, new MainScreenFragment(), MAIN_SCREEN_FRAGMENT_TAG)
                    .commit();


        } else {

            mFragmentManager.beginTransaction().replace(R.id.main_frame,
                    mFragmentManager.findFragmentByTag(MAIN_SCREEN_FRAGMENT_TAG)).commit();

        }

    }

    void fabAction() {

        startActivityForResult(new Intent(this, ScanQRActivity.class), RQCODE);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RQCODE:
                    MainScreenFragment mainScreenFragment = (MainScreenFragment) mFragmentManager
                            .findFragmentByTag(MAIN_SCREEN_FRAGMENT_TAG);
                    mainScreenFragment.setQrCode(data.getStringExtra("qr"));
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fab:
                fabAction();
                break;
        }


    }


}
