package com.make.hp.writebyvoice;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    String x = "", y = "";
    String name;
    Button add, copy, delet, deleteall;
    private TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        MobileAds.initialize(this, "YOUR_ADMOB_APP_ID");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
       txvResult = (TextView) findViewById(R.id.txvResult);
        add = (Button) findViewById(R.id.button);
        copy = (Button) findViewById(R.id.button1);
        delet =(Button) findViewById(R.id.button2);
        deleteall=(Button)findViewById(R.id.delete);
        Intent in = getIntent();
        name=in.getStringExtra("name");

    }

    public void getSpeechInput(View view) {
        if (!isNetworkConnected()) {

            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Write by Voice");
            alertDialog.setMessage("\nCheck your internet connection ");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));
                            // finish();
                        }
                    });
            alertDialog.show();


        } else {
            x = "";
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            if (name.equals("englishbritich")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-GB");
            } else if (name.equals("arabic")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ar_EG");
            } else if (name.equals("englishamerican")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
            } else if (name.equals("french")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fr");
            } else if (name.equals("italy")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "it-IT");
            } else if (name.equals("spain")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "es-ES");
            } else if (name.equals("germany")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "de-DE");
            } else if (name.equals("china")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh");
            } else if (name.equals("japan")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ja");
            } else if (name.equals("portugal")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");
            } else if (name.equals("russian")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ru-RU");
            } else if (name.equals("cannada")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en_CA");
            } else if (name.equals("india")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN");
            } else if (name.equals("turkey")) {
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "tr-TR");
            } else
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, 10);
            } else {
                Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    x = result.get(0);
                    if (y.equals("")) {
                        y = x;
                        // x = "";
                    } else {
                        y = y + "\n" + x;
                        // x = "";
                    }

                    txvResult.setText(y);

                    add.setEnabled(true);
                    copy.setEnabled(true);
                    delet.setEnabled(true);
                    deleteall.setEnabled(true);
                }
                break;
        }
    }

    public void addd(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("note name ");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    File root = new File(Environment.getExternalStorageDirectory(), "Notes");
                    if (!root.exists()) {
                        root.mkdirs();
                    }
                    File gpxfile = new File(root, input.getText().toString()+".txt");
                    FileWriter writer = new FileWriter(gpxfile);
                    writer.append(y);
                    writer.flush();
                    writer.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
        x="";
    }
    public void copyy(View view) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copy",y);
        clipboard.setPrimaryClip(clip);
        x="";
        Toast.makeText(this, "copy", Toast.LENGTH_SHORT).show();
    }
    public void delete (View view){
        if(y.equals(x)){
            y="";
        }
        else{
        y=y.replace("\n"+x,"");}
        txvResult.setText(y);
        x="";
        add.setEnabled(false);
        copy.setEnabled(false);
        delet.setEnabled(false);
        deleteall.setEnabled(false);
    }
    public void deletealll (View view){
        y="";
        txvResult.setText(y);
        x="";
        add.setEnabled(false);
        copy.setEnabled(false);
        delet.setEnabled(false);
        deleteall.setEnabled(false);
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}

