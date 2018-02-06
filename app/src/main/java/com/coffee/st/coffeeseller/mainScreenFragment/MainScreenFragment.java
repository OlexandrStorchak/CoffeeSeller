package com.coffee.st.coffeeseller.mainScreenFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffee.st.coffeeseller.R;


public class MainScreenFragment extends Fragment implements MainScreenInterface {


    public static final String QRCODE_KEY = "qrcode";

    public MainScreenFragment() {
        // Required empty public constructor
    }

    private String mQRcode = "Scan QR code";
    private TextView mQRTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState!=null && savedInstanceState.get(QRCODE_KEY)!=null ) {

            mQRcode = savedInstanceState.get(QRCODE_KEY).toString();

        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_screen, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mQRTextView = view.findViewById(R.id.qr_text);

    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }


    @Override
    public void setQrCode(String qrCode) {
        mQRcode = qrCode;
    }

    void updateView() {
        mQRTextView.setText(mQRcode);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(QRCODE_KEY, mQRcode);
    }

}
