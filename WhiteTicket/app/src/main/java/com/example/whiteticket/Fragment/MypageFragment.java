package com.example.whiteticket.Fragment;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whiteticket.Data.User;
import com.example.whiteticket.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MypageFragment extends Fragment {

    User user = null;
    private ImageView iv;
    private String text;
    private Button button;

    public MypageFragment() {
        // Required empty public constructor
    }

    public static MypageFragment newInstance(User user) {
        MypageFragment fragment = new MypageFragment();
        Bundle args = new Bundle();
        args.putParcelable("User",user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mypage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user = (User)getArguments().get("User");
        Log.d("search_fragment", user.toString());

        button = view.findViewById(R.id.mypage_qr_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.dialog_qrcode);
                dialog.setTitle("QRcode Dialog");

                ImageView iv = (ImageView) dialog.findViewById(R.id.qrcode);
                text = "https://github.com/Siihyun/2019-Founders-Summer-BlockChain";

                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                try{
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    iv.setImageBitmap(bitmap);
                }catch (Exception e){}

                dialog.show();

            }
        });



    }

}
