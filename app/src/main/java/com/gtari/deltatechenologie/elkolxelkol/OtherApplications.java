package com.gtari.deltatechenologie.elkolxelkol;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class OtherApplications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_apps_activity);
    }
    public native String getPackageAppName();
    public void buDownload(View view) {
        Uri marketUri = Uri.parse("market://details?id="+getPackageAppName());
        Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
        startActivity(marketIntent);
    }
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
