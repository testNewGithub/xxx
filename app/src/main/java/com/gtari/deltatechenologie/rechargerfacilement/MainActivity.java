package com.gtari.deltatechenologie.rechargerfacilement;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Locale;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

//Torche import libreries


//hashCode= 62:9D:46:E9:DF:C7:26:77:2D:BE:C9:F1:0F:79:FB:0B:79:02:58:23

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    SurfaceView cameraView;
    TextView textView;
    CameraSource cameraSource;
    ImageView imageView;
    Spinner operatorCode;
    final int RequestCameraPermissionID = 1001;
    private Context context = this;
    private Boolean isNotSendYet = true;
    private String IHMCode;
    private String IHMSolde;
    private String IHMForfaitInternet;
    private String encodedHash = Uri.encode("#");
    private String trueCodeNumber="";



    //Torche variables
    private Camera camera;
    private Camera.Parameters parameters;
    private boolean isTorcheOn = false;
    private static final int p_req = 150;

    CameraManager cameraManager;
    String cameraId;
    String _codeNumber = "";

    private ImageView imageMenuOption;

    String[] operators; //{"Ooredoo","Orange","Tunisie Télécom"};
    int operatorsLogo[] = {R.drawable.ooredoo_icon, R.drawable.orange_icon, R.drawable.tunisie_telecom_icon};

    private InterstitialAd mInterstitialAd;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.MyTheme);
        super.onCreate(savedInstanceState);
        loadLocate();
        setContentView(R.layout.activity_main);

        //Check for permissions
        if(Build.VERSION.SDK_INT >=23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED )
            {
                Intent intent = new Intent(this,GetPemission.class);
                startActivity(intent);
            }
        }

        //Animation Internet Forfait
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.internet_button_anim);
        ImageView shine =(ImageView)findViewById(R.id.shine);
        shine.startAnimation(anim);

        //Get Array of operator
        operators=getResources().getStringArray(R.array.Operator);

        //AdMobBanner

        MobileAds.initialize(this,
                "ca-app-pub-4168864559615120~6007578450");

        AdView mAdView = (AdView) findViewById(R.id.adView);
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

// Intialise InterstitialAd Full screen Ad
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

         //Option Menu buttons

        final Animation animShow = AnimationUtils.loadAnimation(this, R.anim.background_menu);
        final Animation animHide = AnimationUtils.loadAnimation(this, R.anim.background_menu_hide);
        final Animation animHideButton = AnimationUtils.loadAnimation(this, R.anim.shake_hide);
        final Animation animSetButton = AnimationUtils.loadAnimation(this, R.anim.shake_set);
        imageMenuOption=(ImageView) findViewById(R.id.imageView);

        FloatingActionMenu fam = (FloatingActionMenu) findViewById(R.id.fab_menu);
        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                FrameLayout helpButton=(FrameLayout)findViewById(R.id.idHelp);
                FrameLayout noteButton=(FrameLayout) findViewById(R.id.idNote);
                ImageView underOption=(ImageView) findViewById(R.id.idUnderOptionMenu);
                final Animation underOptionHideAnim = AnimationUtils.loadAnimation(context, R.anim.under_option_hide);
                final Animation underOptionSetAnim = AnimationUtils.loadAnimation(context, R.anim.under_option_set);

                if (opened) {
                    //showToast("Menu is opened");
/*
                    fabEdit.startAnimation(shake);
                    fabAdd.startAnimation(shake);
                    fabDelete.startAnimation(shake);
                    fabLast.startAnimation(shake);
*/

                    helpButton.setVisibility(INVISIBLE);
                    helpButton.startAnimation(animHideButton);
                    noteButton.setVisibility(INVISIBLE);
                    noteButton.startAnimation(animHideButton);
                    imageMenuOption.startAnimation(animShow);
                    imageMenuOption.setVisibility(VISIBLE);

                    underOption.setVisibility(INVISIBLE);
                    underOption.setAnimation(underOptionHideAnim);

                } else {
                    //showToast("Menu is closed");
                    helpButton.setVisibility(VISIBLE);
                    helpButton.startAnimation(animSetButton);
                    noteButton.setVisibility(VISIBLE);
                    noteButton.startAnimation(animSetButton);
                    imageMenuOption.startAnimation(animHide);
                    imageMenuOption.setVisibility(INVISIBLE);

                    underOption.setVisibility(VISIBLE);
                    underOption.setAnimation(underOptionSetAnim);

                }
            }
        });

        //Testing Ads are corrects or not
        if(GetAd(getResources().getString(R.string.ad_banner),getResources().getString(R.string.ad_interstitial)).equals("*#")) {
            isNot();
            return;}

        // OptionMenuListner();

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);


        cameraView = (SurfaceView) findViewById(R.id.surface_view);
        textView =(TextView) findViewById(R.id.text_view);
        imageView = (ImageView) findViewById(R.id.Led);






        operatorCode = (Spinner) findViewById(R.id.codeOperator);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.Operator, android.R.layout.simple_spinner_item);
        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),operatorsLogo,operators);
        //customAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operatorCode.setAdapter(customAdapter);
        operatorCode.setOnItemSelectedListener(this);


        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Toast.makeText(this,R.string.dependencies, Toast.LENGTH_LONG).show();
        } else {

            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();


            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {

                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {


                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if (items.size() != 0) {
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                //textView.setText(items.valueAt(0).getValue());

                                for (int i = 0; i < items.size(); ++i) {
                                    TextBlock item= items.valueAt(i);
                                    stringBuilder.append(item.getValue());

                                    int indexOf = stringBuilder.indexOf(" ");
                                    while (indexOf != -1) {
                                        stringBuilder.deleteCharAt(indexOf);
                                        indexOf = stringBuilder.indexOf(" ");
                                    }

                                    stringBuilder.append("*");
                               }

                                //if(CodeNumber(stringBuilder.toString()).length()==14)
                               _codeNumber = CodeNumber(stringBuilder.toString());


                                if (isCodeNumber(_codeNumber) && isNotSendYet) {
                                    RunAnimation(R.id.text_view,R.anim.code_detected_anim,_codeNumber);
                                    SmsCodeSend("Call permission was denied",IHMCode + "*" + _codeNumber);
                                    imageView.setImageResource(R.drawable.greenled);
                                    isNotSendYet = false;
                                }


                            }
                        });
                    }
                }
            });
        }

    }


    private String CodeNumber(String codeNumber) {
        String strCode = "";
        int j = 0;
      /*     for(int i=0;i<codeNumber.length();i++) {
               if (codeNumber.charAt(i)==' ') {
                   codeNumber = codeNumber.replace(' ', '\0');
               }
           }
     */
        for (int i = 0; i < codeNumber.length(); i++) {

            if (Character.isDigit(codeNumber.charAt(i)) && j < 14) {
                strCode = strCode + codeNumber.charAt(i);
                j++;
            } else if (!Character.isDigit(codeNumber.charAt(i)) && j < 14) {
                strCode = "";
                j = 0;
            } else if(codeNumber.charAt(j)=='*' && j==14)return strCode;

        }
         /*  if(strCode.length()==14)
         return strCode;
*/
        return strCode;
    }

    private boolean isCodeNumber(String _codeNumber) {

        return _codeNumber.length() == 14;
    }


    public void buRestartCode(View view) {
        isNotSendYet = true;
        _codeNumber="";
        Animation reloadAnim = AnimationUtils.loadAnimation(this,R.anim.rotate_anim);
        ImageButton restartButton=(ImageButton)findViewById(R.id.idRestart);

        restartButton.startAnimation(reloadAnim);
        RunAnimation(R.id.text_view,R.anim.code_detected_anim,_codeNumber);
        imageView.setImageResource(R.drawable.redled);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        //String operator =parent.getItemAtPosition(position).toString();
        TextView grattezText=(TextView)findViewById(R.id.idGrattez);
        if(GetPackage(position,ReturnPagckage())[0].equals("*#")) {
            isNot();
            return;}

        IHMCode = GetPackage(position,ReturnPagckage())[0];
        IHMSolde=GetPackage(position,ReturnPagckage())[1];
        IHMForfaitInternet=GetPackage(position,ReturnPagckage())[2];
        if (position!=0) {
            grattezText.setVisibility(VISIBLE);
            RunAnimation(R.id.idGrattez, R.anim.fade_in, getResources().getString(R.string.gratter));
            grattezText.setVisibility(INVISIBLE);
        }

       // Toast.makeText(this,IHMCode+"**"+IHMSolde+"**"+IHMForfaitInternet,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }






/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            break;
        }
    }

*/


    public void getCameraparametres() {
        if (camera == null) {
            try {
                camera = getCamera(cameraSource);

                // Camera.open();
                assert camera != null;
                parameters = camera.getParameters();



            } catch (Exception ignored) {
            }

        }
    }


    public void buOnTorche(View view) {



        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, p_req);
                return;
            }
        }
        OnOffTorche();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults) {

        switch (requestCode) {
            case p_req:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    OnOffTorche();
                } else {
                    // permission Denied
                    Toast.makeText(this, "Je pense, donc je suis.", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permission, grantResults);

        }


    }

    public String ReturnPagckage() {
     return getPackageName();
    }

    private void TurnOnTorche() {
        //  CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        ImageButton buttonTorch=(ImageButton)findViewById(R.id.idTorche);
        ImageView imageTorch=(ImageView)findViewById(R.id.imageTorch);

        try {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            buttonTorch.setBackgroundResource(R.drawable.button_torche);
            imageTorch.setImageResource(R.drawable.torche_on);
            //camera.startPreview();

            // String cameraId = cameraManager.getCameraIdList()[0];
            // cameraManager.setTorchMode(cameraId, true);
            isTorcheOn = true;
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


    private void TurnOffTorche() {
        //  CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        ImageButton buttonTorch=(ImageButton)findViewById(R.id.idTorche);
        ImageView imageTorch=(ImageView)findViewById(R.id.imageTorch);
        try {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            buttonTorch.setBackgroundResource(R.drawable.bt_torche_off);
            imageTorch.setImageResource(R.drawable.torche_off);
            //camera.stopPreview();


            // String cameraId = cameraManager.getCameraIdList()[0];
            // cameraManager.setTorchMode(cameraId, false);
            isTorcheOn = false;
        } catch (Exception e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    boolean isCameraFeatureExist() {
       /* boolean isCameraFlash=true;
        boolean deviceHasFlash =
        PackageManager pm = context.getPackageManager();

        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(getApplicationContext(),"Votre téléphone ne possède pas de flash!", Toast.LENGTH_SHORT).show();
        isCameraFlash=false;
        }*/

        return getApplication().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);


    }


    public void OnOffTorche() {
        if (!isCameraFeatureExist()) {
            Toast.makeText(this, R.string.torch_alert, Toast.LENGTH_LONG).show();
            return;
        }

        if (isTorcheOn) {
            TurnOffTorche();

        } else {
            getCameraparametres();
            TurnOnTorche();

        }

    }


    @Override
    public void onStop() {
        super.onStop();
        if (camera != null) {
            // camera.release();
            camera = null;
            parameters = null;
        }

    }

    @Override
    public void onRestart() {
        super.onRestart();
    }


    public static Camera getCamera(@NonNull CameraSource cameraSource) {
        Field[] declaredFields = CameraSource.class.getDeclaredFields();

        for (Field field : declaredFields) {
            if (field.getType() == Camera.class) {
                field.setAccessible(true);
                try {
                    Camera camera = (Camera) field.get(cameraSource);
                    if (camera != null) {
                        return camera;
                    }

                    return null;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                break;
            }
        }

        return null;
    }

// Back Button Event
    @Override
    public void onBackPressed() {
        CancelDialog();
    }

/*
    public void buCancel(View view) {
        Toast.makeText(this,"Back",Toast.LENGTH_SHORT).show();
        isBack=true;
        finish();
        onBackPressed();
    }

    public void buContinue(View view) {
        Toast.makeText(this,"Continue",Toast.LENGTH_SHORT).show();

    }
 */



private void SmsCodeSend(String _permissinDenied,String _IHM){
    Intent intent = new Intent(Intent.ACTION_CALL);
    intent.setData(Uri.parse("tel:" + "*"+_IHM + encodedHash));
    if(Build.VERSION.SDK_INT>=23) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"Je pense, donc je suis.",Toast.LENGTH_LONG).show();
            return;
        }
    }
    startActivity(intent);

}

    public void buSolde(View view) {

        SmsCodeSend("Call permission was denied",IHMSolde);
        Button internetButton=(Button)findViewById(R.id.idButtonSolde);
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.ihm_bt_click_anim);
        internetButton.startAnimation(shake);
    }

    public void buForfaitInternet(View view) {
        SmsCodeSend("Call permission was denied",IHMForfaitInternet);
        FrameLayout soldeButton=(FrameLayout) findViewById(R.id.idFrameForfaitInternet);
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.ihm_bt_click_anim);
        soldeButton.startAnimation(shake);
    }


    //Animation TextView
    private void RunAnimation(int _sourceText,int _sourceAnimation,String _text) {
        Animation a = AnimationUtils.loadAnimation(this,_sourceAnimation);
        a.reset();
        TextView tv = (TextView) findViewById(_sourceText);
        tv.setText(_text);
        tv.clearAnimation();
        tv.setAnimation(a);
        tv.startAnimation(a);
    }





    //Option Menu Listner

    public void buOptionMenuA(View view) {
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

    private void isNot() {
        Intent intent = new Intent(this,CustomItemActivity.class);
        startActivity(intent);
        finish();
    }


    public void buOptionMenuB(View view) {
        Intent intent = new Intent(this,OtherApplication.class);
        startActivity(intent);
    }



    public void buOptionMenuC(View view) {
        if(isConnected()){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=com.gtari.deltatechenologie.rechargerfacilement"));
            startActivity(intent);
            Toast.makeText(this,R.string.commentaire,Toast.LENGTH_LONG).show();
        }
        else {
            final Dialog dialog = new Dialog(this);
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            dialog.setContentView(R.layout.internet_state_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(true);
            dialog.show();

            dialog.findViewById(R.id.idConnectionButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }

            });


        }

    }



    public void buOptionMenuD(View view) {
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);
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

// Launch Help Activity
    public void buHelp(View view) {
        Intent intent = new Intent(this,HelpActivity.class);
        startActivity(intent);
    }
// Notate Application
    public void buNote(View view) {

        // Link pakage Test com.example.hussienalrubaye.webview;
        //"com.app ruabye.hussienalrubaye.webview"



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
                if(!isConnected()){
                    dialog.cancel();
                    final Dialog dialog1 = new Dialog(context);
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
                else {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.gtari.deltatechenologie.rechargerfacilement"));
                    startActivity(intent);
                    dialog.cancel();
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
    private boolean isConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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

// Cancel Application Event
    public void CancelAppButton(View view) {
        CancelDialog();
    }


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
                .addTestDevice("0E4A0338E926A76C1DA8FAC01CCCF46E")  // An example device ID
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


    public native static String[] GetPackage(int position,String string);
    public  native static String GetAd(String adb_name,String adi_name);
    static {
        System.loadLibrary("native-lib");
    }


    private void privacyPolicyDialog() {
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
        privacyPolicyDialog();
    }

}

