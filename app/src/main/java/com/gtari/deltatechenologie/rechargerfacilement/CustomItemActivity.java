package com.gtari.deltatechenologie.rechargerfacilement;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
    public void onBackPressed() {CancelDialog();}

    private void CancelDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.cancel_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(true);
        dialog.show();



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
            }

        });

        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }

        });

    }
}

