package com.gtari.deltatechenologie.rechargerfacilement;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    public static final String IMAGE_MESSAGE = "IMAGE_MESSAGE";


    public static  MyFragment newInstance(/*String message,*/ int img)
    {
        MyFragment fragment = new MyFragment();
        Bundle bdl = new Bundle();
       // bdl.putString(EXTRA_MESSAGE, message);
        bdl.putInt(IMAGE_MESSAGE,img);
        fragment.setArguments(bdl);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // String message = getArguments().getString(EXTRA_MESSAGE);

        View v = inflater.inflate(R.layout.myfragment_layout, container, false);

       // TextView messageTextView = (TextView)v.findViewById(R.id.textView);

      //  messageTextView.setText(message);

        int img = getArguments().getInt(IMAGE_MESSAGE);

        ImageView image = (ImageView)v.findViewById(R.id.imageView);
        image.setImageResource(img);

        return v;
    }
}
