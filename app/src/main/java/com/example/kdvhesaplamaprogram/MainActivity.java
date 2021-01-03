package com.example.kdvhesaplamaprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huawei.hms.ads.AdListener;
import com.huawei.hms.ads.AdParam;
import com.huawei.hms.ads.BannerAdSize;
import com.huawei.hms.ads.HwAds;
import com.huawei.hms.ads.InterstitialAd;
import com.huawei.hms.ads.banner.BannerView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTextPersonName2 , editTextTextPersonName5 ;
    private Button buttonYuzde18, buttonYuzde8, buttonYuzde1, buttonHesapla ;
    private TextView textView5, textView7, textView9 ,textViewislemTutari,textViewKdvtutari,textViewToplamtutari ;
    private RadioGroup radioGroup ;
    private boolean kdvdahil = true;
    private double tutar = 0.0 ;
    private double kdv = 0.0 ;
    InterstitialAd interstitialAd;
    private boolean isKdvdahil = true;
    private TextWatcher editTextTextPersonName2degisimler = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        try {
            tutar=Double.parseDouble(s.toString());

        } catch (NumberFormatException e){
            tutar =0.0;
        }
        guncelle();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private TextWatcher editTextTextPersonName5degisimler  = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                kdv =Double.parseDouble(s.toString());
            }catch (NumberFormatException e){
                kdv =0.0;
            }
            guncelle();

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    } ;
     private RadioGroup.OnCheckedChangeListener radioGroupdegisimler = new RadioGroup.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(RadioGroup group, int checkedId) {
             if (checkedId == R.id.radioButtonKDVhariç){
                 kdvdahil = true;
             }
        else if (checkedId == R.id.radioButtonKDVhariç){
                kdvdahil= false;
             }
        guncelle();
         }
     };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HwAds.init(this); //HMS ads servisini tetikledik.
        loadBannerAdd(); //yüklenirken banneri yükledik

        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdId("testb4znbuh3n2");//video test id ekledik.
        interstitialAd.setAdListener(adListener);
        AdParam adParam = new AdParam.Builder().build();
        interstitialAd.loadAd(adParam);

    }
    public void loadBannerAdd() {
        //ad parmeter objesini oluşturduk
        AdParam adParam = new AdParam.Builder().build();
        //banner view oluşturduk.
        BannerView bannerView = new BannerView(this);
        // Reklam ıd miz
        bannerView.setAdId("testw6vs28auh3");
        bannerView.setBannerAdSize(BannerAdSize.BANNER_SIZE_360_57);
        // Add BannerView to the layout.
        //Reklamın gözükeceği layout eriştik.
        RelativeLayout rootView = findViewById(R.id.Bannerüst);
        rootView.addView(bannerView);

        bannerView.loadAd(adParam);
    } //Reklam kodları

    private final AdListener adListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            super.onAdLoaded();
            Toast.makeText(MainActivity.this, "Reklam Yüklendi", Toast.LENGTH_SHORT).show();
            // Display an interstitial ad.
            showInterstitial();
        }

        @Override
        public void onAdFailed(int errorCode) {
            Toast.makeText(MainActivity.this, "Reklam yüklemesi hata koduyla başarısız oldu: " + errorCode,
                    Toast.LENGTH_SHORT).show();
            Log.d("TAG", "Reklam yüklemesi hata koduyla başarısız oldu: " + errorCode);
        }

        @Override
        public void onAdClosed() {
            super.onAdClosed();
            Log.d("TAG", "Kapanan Reklam");
        }

        @Override
        public void onAdClicked() {
            Log.d("TAG", "Tıklanan Reklam");
            super.onAdClicked();
        }

        @Override
        public void onAdOpened() {
            Log.d("TAG", "Açılan Reklam");
            super.onAdOpened();
        }
    };//Reklam kodları

    private void showInterstitial() {

        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Toast.makeText(this, "Reklam Yüklenmedi", Toast.LENGTH_SHORT).show();
        }




        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
        buttonYuzde18 = findViewById(R.id.buttonYuzde18);
        buttonYuzde8 = findViewById(R.id.buttonYuzde8);
        buttonYuzde1 = findViewById(R.id.buttonYuzde1);
        buttonHesapla = findViewById(R.id.buttonHesapla);
        textView5 = findViewById(R.id.textView5) ;
        textView7 = findViewById(R.id.textView7) ;
        textView9 = findViewById(R.id.textView9) ;
        textViewislemTutari = findViewById(R.id.textViewislemTutari) ;
        textViewKdvtutari = findViewById(R.id.textViewKdvtutari) ;
        textViewToplamtutari = findViewById(R.id.textViewToplamtutari) ;
        radioGroup = findViewById(R.id.radioGroup) ;


        editTextTextPersonName2.addTextChangedListener(editTextTextPersonName2degisimler);
        editTextTextPersonName5.addTextChangedListener(editTextTextPersonName5degisimler);
        radioGroup.setOnCheckedChangeListener(radioGroupdegisimler);


        buttonYuzde1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextTextPersonName5.setText(String.valueOf(1));
            }
        });
        buttonYuzde8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextTextPersonName5.setText(String.valueOf(8));
            }
        });
        buttonYuzde18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextTextPersonName5.setText(String.valueOf(18));
            }
        });
        guncelle();

   }
    public void guncelle (){

        buttonHesapla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);

                DecimalFormat formatter = new DecimalFormat("###,###.##");
                double kdvdahilislemtutari = tutar / (1+kdv/100);
                double kdvdahilkdvTutari = tutar - kdvdahilislemtutari;
                double kdvHaricKdvsi = tutar * (kdv/100 );
                double kdvharicToplamtutar = tutar + kdvHaricKdvsi;

                if (kdvdahil){
                    textViewToplamtutari.setText(formatter.format(tutar));
                    textViewislemTutari.setText(formatter.format(kdvdahilislemtutari));
                    textViewKdvtutari.setText(formatter.format(kdvdahilkdvTutari));
                }
                else {
                    textViewislemTutari.setText(formatter.format(tutar));
                    textViewKdvtutari.setText(formatter.format(kdvHaricKdvsi));
                    textViewToplamtutari.setText(formatter.format(kdvharicToplamtutar));


                }


            }
        });
    }
}