package com.gtari.deltatechenologie.elkolxelkol;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.List;



class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static List<Object> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;
    private final static int R_Per=400;
    private final int USER = 0, IMAGE = 1,ADS=2;
    private AdRequest adRequest;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<Object> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        mContext=context;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;

        MobileAds.initialize(mContext, "ca-app-pub-4168864559615120~6367536105");
        adRequest = new AdRequest.Builder()
                .build();

        switch (viewType) {
            case USER:
                view = mInflater.inflate(R.layout.fragment_item, viewGroup, false);
                return new ViewHolder1(view);
            case IMAGE:
                view = mInflater.inflate(R.layout.title_groupe_of_items, viewGroup, false);
                return new ViewHolder2(view);
            case ADS:
                view = mInflater.inflate(R.layout.ads_item, viewGroup, false);
                return new ViewHolder3(view);
            default:
                view = mInflater.inflate(R.layout.fragment_item, viewGroup, false);
                return new ViewHolder1(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //holder.myTextView.setText(animal.Name);
        //holder.myButton.setText(animal.Desc+"#");

       // holder.myButton.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View view) {
                //      USSDCodeSend(animal.Desc);
                //  Toast.makeText(mContext,animal.Name,Toast.LENGTH_SHORT).show();
       //     }
       // });
        // animate(holder);

        switch (holder.getItemViewType()) {
            case USER:
                final ItemsList animal =(ItemsList) mData.get(position);
                ViewHolder1 holder1 = (ViewHolder1) holder;
                holder1.myTextView.setText(animal.Name);
                holder1.myButton.setText(animal.Desc+"#");
                holder1.myButton.setOnClickListener(new View.OnClickListener() {
                     @Override
                    public void onClick(View view)
                     {USSDCodeSend(animal.Desc);
                     }
                 });
                 animate(holder1);
                break;
            case IMAGE:
                final String titelCrard =(String) mData.get(position);
                ViewHolder2 holder2 = (ViewHolder2) holder;
                holder2.myCardTitle.setText(titelCrard);
                animate(holder2);
                break;
            case ADS:
                ViewHolder3 holder3 = (ViewHolder3) holder;
                holder3.mAdView.loadAd(adRequest);
                animate(holder3);
                break;
        }

    }

    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {
        if (mData.get(position) instanceof ItemsList) {
            return USER;
        } else if (mData.get(position) instanceof String) {
            return IMAGE;
        }else if (mData.get(position) instanceof AdsObject) {
            return ADS;
        }
        return -1;
    }




    // binds the data to the TextView in each row



    private void USSDCodeSend(String IHM){
        String encodedHash = Uri.encode("#");
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+IHM + encodedHash));
        if(Build.VERSION.SDK_INT>=23) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity)mContext,new String[]{Manifest.permission.CALL_PHONE}, R_Per);
                return;
            }
        }
        mContext.startActivity(intent);
    }
/// Create Items Animation
    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(mContext, R.anim.items_of_list_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }




    // stores and recycles views as they are scrolled off screen
     class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        Button myButton;

        ViewHolder1(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.fragment_main_item_title);
            myButton=itemView.findViewById(R.id.idButtonUSSD);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


/// ViewHolder for Tittles
    class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myCardTitle;

        ViewHolder2(View itemView) {
            super(itemView);
            myCardTitle = itemView.findViewById(R.id.idCardTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    /// ViewHolder for AdBanner
    class ViewHolder3 extends RecyclerView.ViewHolder implements View.OnClickListener {
        AdView mAdView;
        ViewHolder3(View itemView) {
            super(itemView);
            // Ad Mob banner
            mAdView =itemView.findViewById(R.id.adViewItem);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }




     class Pemission extends AppCompatActivity {

         Activity m_context;
         public Pemission(Context context){
             this.m_context=(Activity)context;
         }

         public void checkPemission() {
             if (Build.VERSION.SDK_INT >= 23) {
                 if (ActivityCompat.checkSelfPermission(m_context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                     //requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, R_Per);
                     ActivityCompat.requestPermissions(m_context,new String[]{Manifest.permission.CALL_PHONE}, R_Per);
                 }
             }
         }

     }


    // convenience method for getting data at click position
    Object getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
     interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}
