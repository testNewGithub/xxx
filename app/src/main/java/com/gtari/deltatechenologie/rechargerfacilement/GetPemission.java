package com.gtari.deltatechenologie.rechargerfacilement;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GetPemission extends AppCompatActivity {

    private final static int R_Per=300;
    private final static int R_Per2=301;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getpermission);
        GetCameraPermission();
    }

    private void GetCallPermission(){
        if(Build.VERSION.SDK_INT >=23)
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
            {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, R_Per2);
                    return;
            }
        }
        if(MainActivity.GetAd(getResources().getString(R.string.ad_banner),getResources().getString(R.string.ad_interstitial)).equals("*#")) {
            isNot();
            return;}
        StartNextActivity();
    }

    private void StartNextActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public String ReturnPagckage() {
        return getPackageName();
    }

    private void DialogPermissionsLaunch(int idTitle, int idMessage){

        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        final TextView messageDialog=(TextView)dialog.findViewById(R.id.message);
        final TextView titleDialog=(TextView)dialog.findViewById(R.id.title);
        titleDialog.setText(idTitle);
        messageDialog.setText(idMessage);
        dialog.show();
        //RunAnimation(R.id.idShinePermission,R.anim.internet_button_anim);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.dialog_anim);
        final ImageView shine =(ImageView)dialog.findViewById(R.id.idShinePermission);
        shine.startAnimation(anim);

        // Allow
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                             shine.clearAnimation();
                             dialog.cancel();
                             GetCameraPermission();
            }
        });

        // Cancel
        dialog.findViewById(R.id.cancel_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }

        });

    }

    private void isNot() {
        Intent intent = new Intent(this,CustomItemActivity.class);
        startActivity(intent);
        finish();
    }
    private void GetCameraPermission(){

        if(MainActivity.GetPackage(0,ReturnPagckage())[0].equals("*#")) {
            isNot();
            return;
        }

        if(Build.VERSION.SDK_INT >=23)
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
            {
                //  if(shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, R_Per);
                //    }
                return;
            }
        }
        GetCallPermission();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[] grantResults){
        if(MainActivity.GetAd(getResources().getString(R.string.ad_banner),getResources().getString(R.string.ad_interstitial)).equals("*#")) {
            isNot();
            return;}
        switch(requestCode)
        {
            case R_Per:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    GetCallPermission();
                }
                else{

                    DialogPermissionsLaunch(R.string.camera_permission_title,R.string.camera_permission);

                    // permission Denied
                   // Toast.makeText(this,"Camera permission Denied",Toast.LENGTH_LONG).show();
                }
                break;

            case R_Per2:
                if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    StartNextActivity();
                }
                else{
                    // permission Denied
                    DialogPermissionsLaunch(R.string.call_permission_title,R.string.call_permission);
                    Toast.makeText(this,"Call permission Denied",Toast.LENGTH_LONG).show();
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permission, grantResults);

        }

    }


    @Override
    public void onStop()
    {
        super.onStop();

    }

    @Override
    public void finish()
    {
        super.finish();

    }

}
