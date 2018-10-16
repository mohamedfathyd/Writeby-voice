package com.make.hp.writebyvoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


/**
 * Created by hp on 8/3/2017.
 */
public class languges extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.languges);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        MobileAds.initialize(this, "YOUR_ADMOB_APP_ID");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        Button mClickButton1 = (Button)findViewById(R.id.englishbritich);
        mClickButton1.setOnClickListener(this);
        Button mClickButton2 = (Button)findViewById(R.id.arabic);
        mClickButton2.setOnClickListener(this);
        Button mClickButton3 = (Button)findViewById(R.id.englishamerican);
        mClickButton3.setOnClickListener(this);
        Button mClickButton4 = (Button)findViewById(R.id.french);
        mClickButton4.setOnClickListener(this);
        Button mClickButton5 = (Button)findViewById(R.id.italy);
        mClickButton5.setOnClickListener(this);
        Button mClickButton6= (Button)findViewById(R.id.spain);
        mClickButton6.setOnClickListener(this);
        Button mClickButton7 = (Button)findViewById(R.id.germany);
        mClickButton7.setOnClickListener(this);
        Button mClickButton8 = (Button)findViewById(R.id.china);
        mClickButton8.setOnClickListener(this);
        Button mClickButton9 = (Button)findViewById(R.id.japan);
        mClickButton9.setOnClickListener(this);
        Button mClickButton10 = (Button)findViewById(R.id.portugal);
        mClickButton10.setOnClickListener(this);
        Button mClickButton11 = (Button)findViewById(R.id.russian);
        mClickButton11.setOnClickListener(this);
       Button mClickButton12 = (Button)findViewById(R.id.cannada);
        mClickButton12.setOnClickListener(this);
        Button mClickButton13 = (Button)findViewById(R.id.india);
        mClickButton13.setOnClickListener(this);
        Button mClickButton14 = (Button)findViewById(R.id.turkey);
        mClickButton14.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       String IdAsString = view.getResources().getResourceName(view.getId());
        int x =  IdAsString.indexOf("/");
        String name="";
        for(int i=28;i<IdAsString.length();i++){
            name+=IdAsString.charAt(i);
        }
        Toast.makeText(this,name, Toast.LENGTH_LONG).show();
      Intent intent = new Intent(this,MainActivity.class);
       intent.putExtra("name", name);
        startActivity(intent);

    }

}

