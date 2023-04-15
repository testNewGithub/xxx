package com.gtari.deltatechenologie.elkolxelkol;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custem_item_layout);
        TextView title=(TextView)findViewById(R.id.idAvTitle);
        TextView message=(TextView)findViewById(R.id.idAvMessage);
        TextView c=(TextView)findViewById(R.id.idAvC);
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.idAv);
        title.setText(AvTitle());
        message.setText(AvMessge());
        c.setText(AvC());
        linearLayout.setBackgroundColor(Color.parseColor( AvColorbckg()));
    }
    private native String AvTitle();
    private native String AvMessge();
    private native String AvColorbckg();
    private native String AvC();
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    public void onBackPressed() {finish();}
    @Override
    public void finish() {
        super.finish();
    }
}


