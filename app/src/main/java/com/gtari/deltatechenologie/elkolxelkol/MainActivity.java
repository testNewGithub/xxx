package com.gtari.deltatechenologie.elkolxelkol;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener,AdapterView.OnItemSelectedListener {

    MyRecyclerViewAdapter adapter;
    private Context mContext;
    private final static int R_Per=400;
    private String mServiceClientsNumber;
    private Spinner operatorCode;
    private String[] operators; //{"Ooredoo","Orange","Tunisie Télécom"};
    private int operatorsLogo[] = {R.drawable.ooredoo_icon, R.drawable.orange_icon, R.drawable.tunisie_telecom_icon};
    private RecyclerView recyclerView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocate();
        setContentView(R.layout.activity_main);

        mContext=this;
        //Get Array of operator
        operators=getResources().getStringArray(R.array.Operator);



        // set up the RecyclerView
        recyclerView = findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // adapter = new MyRecyclerViewAdapter(this, animalNames);

       // recyclerView.setAdapter(adapter);

        //Add divider between items
    /*    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
*/


        //Spinner (Select Operrator)
        operatorCode =findViewById(R.id.codeOperator);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.Operator, android.R.layout.simple_spinner_item);
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),operatorsLogo,operators);
        //customAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operatorCode.setAdapter(customAdapter);
        operatorCode.setOnItemSelectedListener(this);


        MobileAds.initialize(this, "ca-app-pub-4168864559615120~6367536105");
        AdView mAdView = findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder()
                .build();
        mAdView.loadAd(adRequest);

        // Reintialise InterstitialAd Full screen Ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.ad_interstitial));
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(adRequest);
            }
        });

    }


    @Override
    public void onItemClick(View view, int position) {
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
        switch(requestCode)
        {
            case R_Per:
                if (grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    //do
                    DialogPermissionsLaunch();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permission, grantResults);
        }
    }

    // Back Button Event
    @Override
    public void onBackPressed() {
        CancelDialog();
    }


    private void DialogPermissionsLaunch(){

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_permission);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();


        Animation anim = AnimationUtils.loadAnimation(this,R.anim.dialog_anim);
        final ImageView shine =dialog.findViewById(R.id.idShinePermission);
        shine.startAnimation(anim);

        // Allow
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shine.clearAnimation();
                dialog.cancel();
                ActivityCompat.requestPermissions((Activity)mContext,new String[]{Manifest.permission.CALL_PHONE}, R_Per);
            }
        });

        // Cancel
        dialog.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shine.clearAnimation();
                dialog.cancel();
            }

        });

    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        ArrayList<Object> animalNames = new ArrayList<>();
        SharedPreferences perfers=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language="";
        if(perfers.contains("My_Language")) {language=perfers.getString("My_Language","");}
        if(!getPackageAppsName().equals(getPackageName())){isNot();}

        switch (position){
               case 0:
                      if(language.equals("ar")) {
                          for (int i = 0; i < 45; i++)
                              animalNames.add(new ItemsList(stringFromJNIooredooAr(i, 0), stringFromJNIooredooAr(i, 1)));

                          //  Mon Compte 11   *Internet 2*Appel *International & Roaming *SMS  *Entertainment * Offre et Forfait  *Migration *Divers
                          animalNames.add(0,"حسابي");
                          animalNames.add(11,"الإنترنت");
                          animalNames.add(14,"إتصالات");
                          animalNames.add(18,"خدمات دولية & التجوال");
                          animalNames.add(23,"إرساليات قصيرة");
                          animalNames.add(26,"الترفيه");
                          animalNames.add(30,"العروض و الخدمات");
                          animalNames.add(43,"تحويل الخط");
                          animalNames.add(48,"خدمات مختلفة");
                      }else {
                          for (int i = 0; i < 45; i++)
                              animalNames.add(new ItemsList(stringFromJNIooredoo(i, 0), stringFromJNIooredoo(i, 1)));

                          //  Mon Compte 11   *Internet 2*Appel *International & Roaming *SMS  *Entertainment * Offre et Forfait  *Migration *Divers
                          animalNames.add(0,"Mon Compte");
                          animalNames.add(11,"Internet");
                          animalNames.add(14,"Appel");
                          animalNames.add(18,"International & Roaming");
                          animalNames.add(23,"SMS");
                          animalNames.add(26,"Entertainment");
                          animalNames.add(30,"Offre et Forfait");
                          animalNames.add(43,"Migration");
                          animalNames.add(48,"Divers");
                      }

                   //     animalNames.add(10,"Offre et Forfait");

                   if(isConnected()) {
                       animalNames.add(14, new AdsObject());
                       animalNames.add(44,new AdsObject());
                       animalNames.add(56,new AdsObject());
                   }

                       // set Service Client Number
                   mServiceClientsNumber="1111";
                   break;
               case 1:
                   if(language.equals("ar")) {
                       for (int i = 0; i < 28; i++)
                           animalNames.add(new ItemsList(stringFromJNIorangeAr(i, 0), stringFromJNIorangeAr(i, 1)));

                       // Mon Compte 11 *Internet 2*Appel *International & Roaming *Entertainment * Offre, Forfait et Option  *Divers
                       animalNames.add(0,"حسابي");
                       animalNames.add(11,"الإنترنت");
                       animalNames.add(17,"إتصالات");
                       animalNames.add(20,"خدمات دولية & التجوال");
                       animalNames.add(24,"الترفيه");
                       animalNames.add(26,"العروض و الخدمات و الخيرات");
                       animalNames.add(29,"خدمات مختلفة");
                   }else {
                       for (int i = 0; i < 28; i++)
                           animalNames.add(new ItemsList(stringFromJNIorange(i, 0), stringFromJNIorange(i, 1)));

                       // Mon Compte 11 *Internet 2*Appel *International & Roaming *Entertainment * Offre, Forfait et Option  *Divers
                       animalNames.add(0,"Mon Compte");
                       animalNames.add(11,"Internet");
                       animalNames.add(17,"Appel");
                       animalNames.add(20,"International & Roaming");
                       animalNames.add(24,"Entertainment");
                       animalNames.add(26,"Offre, Forfait et Option");
                       animalNames.add(29,"Divers");
                   }

                   if(isConnected()) {
                       animalNames.add(17, new AdsObject());
                       animalNames.add(27,new AdsObject());
                       animalNames.add(37,new AdsObject());
                   }

                   // set Service Client Number
                   mServiceClientsNumber="1150";
                   break;
               case 2:
                   if(language.equals("ar")) {
                       for (int i = 0; i < 58; i++)
                           animalNames.add(new ItemsList(stringFromJNItunisieTelecomAr(i, 0), stringFromJNItunisieTelecomAr(i, 1)));

                       // Mon Compte  *Internet *Appel *International & Roaming *Fixe *Entertainment * Offre et Forfait *Migration *Divers
                       animalNames.add(0,"حسابي");
                       animalNames.add(12,"الإنترنت");
                       animalNames.add(21,"إتصالات");
                       animalNames.add(25,"خدمات دولية & التجوال");
                       animalNames.add(29,"قارّ ");
                       animalNames.add(31,"الترفيه");
                       animalNames.add(35,"العروض و الخدمات");
                       animalNames.add(49,"تحويل الخط");
                       animalNames.add(55,"خدمات مختلفة");
                   }else {
                       for (int i = 0; i < 58; i++)
                           animalNames.add(new ItemsList(stringFromJNItunisieTelecom(i, 0), stringFromJNItunisieTelecom(i, 1)));

                       // Mon Compte  *Internet *Appel *International & Roaming *Fixe *Entertainment * Offre et Forfait *Migration *Divers
                       animalNames.add(0,"Mon Compte");
                       animalNames.add(12,"Internet");
                       animalNames.add(21,"Appel");
                       animalNames.add(25,"International & Roaming");
                       animalNames.add(29,"Fixe");
                       animalNames.add(31,"Entertainment");
                       animalNames.add(35,"Offre et Forfait");
                       animalNames.add(49,"Migration");
                       animalNames.add(55,"Divers");
                   }

                   if(isConnected()) {
                       animalNames.add(21, new AdsObject());
                       animalNames.add(36,new AdsObject());
                       animalNames.add(51,new AdsObject());
                       animalNames.add(70,new AdsObject());
                   }
                   // set Service Client Number
                   mServiceClientsNumber="1298";
                   break;
               default:
                   break;
           }

        adapter = new MyRecyclerViewAdapter(this, animalNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);


        // Toast.makeText(this,IHMCode+"**"+IHMSolde+"**"+IHMForfaitInternet,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    // Dilago Cancel App
    private void CancelDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.cancel_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();

        //Launch Full Screen Ad
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mInterstitialAd.show();
            }
        },300); // Millisecond 1000 = 1 sec



        //Dialog Alert
        dialog.findViewById(R.id.positive_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }

        });

        dialog.findViewById(R.id.cancel_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                finish();
            }

        });

        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }

        });

    }

    // Launch Cancel Application Dialog
    public void CancelAppButton(View view) {
        CancelDialog();
    }


// NDK Lib
    public native String stringFromJNIooredoo(int i,int j);
    public native String stringFromJNIooredooAr(int i,int j);
    public native String stringFromJNIorange(int i,int j);
    public native String stringFromJNIorangeAr(int i,int j);
    public native String stringFromJNItunisieTelecom(int i,int j);
    public native String stringFromJNItunisieTelecomAr(int i,int j);
    public native String getPackageAppsName();

    static {
        System.loadLibrary("native-lib");
    }


    //Opetion Menu Buttons
// Call Clients's Service
    public void buServiceClients(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+mServiceClientsNumber));
        if(Build.VERSION.SDK_INT>=23) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity)mContext,new String[]{Manifest.permission.CALL_PHONE}, R_Per);
                return;
            }
        }
        mContext.startActivity(intent);
    }

    //Select Application Language
    public void buLanguages(View view) {

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_language_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();


        //Select Application Language
        dialog.findViewById(R.id.buttonFrensh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("fr");
                dialog.cancel();
                recreate();
            }

        });

        dialog.findViewById(R.id.buttonArabic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("ar");
                dialog.cancel();
                recreate();
            }

        });

        dialog.findViewById(R.id.close_language_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }

        });


    }



    // Launch other apllication Intent
    public void buOwerApps(View view) {
        Intent intent = new Intent(this,OtherApplications.class);
        startActivity(intent);
    }

    //  Openion And Comments
    public void buYourOpenion(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.note_application_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();


        dialog.findViewById(R.id.idNoteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected()){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.gtari.deltatechenologie.ussd"));
                    dialog.cancel();
                    startActivity(intent);


                }
                else {
                    dialog.cancel();
                    final Dialog dialog1 = new Dialog(mContext);
                    dialog1.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    dialog1.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
                    dialog1.setContentView(R.layout.internet_state_dialog);
                    dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog1.setCancelable(true);
                    dialog1.show();

                    dialog1.findViewById(R.id.idConnectionButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog1.cancel();
                        }
                    });
                }

            }

        });

        dialog.findViewById(R.id.idCancelNoteDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }

        });

    }
    //Get Connectivity State
    private void isNot() {
        Intent intent = new Intent(this,CustomItemActivity.class);
        startActivity(intent);
        finish();
    }
    private boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //select Locale
    private void setLocale(String language){
        Locale locale=new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

        // /Save Date For Next Time
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Language",language);
        editor.apply();
    }

    // Load loacte language
    private void loadLocate(){
        SharedPreferences perfers=getSharedPreferences("Settings", Activity.MODE_PRIVATE);

        if(!perfers.contains("My_Language")) {
            return;
        }

        String language=perfers.getString("My_Language","");
        setLocale(language);
    }



    @Override
    public void finish() {
        super.finish();
    }

    private void privacyPolcyDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getResources().getString(R.string.privacy_policy));

        WebView wv = new WebView(this);
        wv.loadUrl("file:///android_asset/privacy_policy.html");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);

                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton(getResources().getString(R.string.close), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public void buPrivacyPolicy(View view) {
        privacyPolcyDialog();
    }

}

