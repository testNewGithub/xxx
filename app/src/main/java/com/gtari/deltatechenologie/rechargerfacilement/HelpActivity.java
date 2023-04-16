package com.gtari.deltatechenologie.rechargerfacilement;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends FragmentActivity {
    MyPageAdapter pageAdapter;
    ImageView helpLeftimage;
    ImageView helpRightimage;
    Animation animSwapeRight;
    Animation animSwapeLeft;
    Context context;

    int savedItem=-1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_activity);

        context=this;
        //Set and Start Swape Animation
        helpLeftimage =(ImageView)findViewById(R.id.idSwapeLeft);
        helpRightimage =(ImageView)findViewById(R.id.idSwapeRight);
        animSwapeLeft = AnimationUtils.loadAnimation(this,R.anim.hide_swipe);
        animSwapeRight = AnimationUtils.loadAnimation(context,R.anim.swape_right_anime);
        helpRightimage.startAnimation(animSwapeRight);
        helpLeftimage.startAnimation(animSwapeLeft);


        List<Fragment> fragments = getFragments();

        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager)findViewById(R.id.viewpager);
        pager.setAdapter(pageAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {

                if(position==0){
                    animSwapeLeft = AnimationUtils.loadAnimation(context,R.anim.hide_swipe);
                    helpLeftimage.startAnimation(animSwapeLeft);
                }
                else {
                    animSwapeLeft = AnimationUtils.loadAnimation(context,R.anim.swape_left_anime);
                    helpLeftimage.startAnimation(animSwapeLeft);
                }

                if(position==pageAdapter.getCount()-1){
                    animSwapeRight = AnimationUtils.loadAnimation(context,R.anim.hide_swipe);
                    helpRightimage.startAnimation(animSwapeRight);
                }
                else {
                    animSwapeRight = AnimationUtils.loadAnimation(context,R.anim.swape_right_anime);
                    helpRightimage.startAnimation(animSwapeRight);
                }
               // Toast.makeText(context,String.valueOf(position),Toast.LENGTH_SHORT).show();
            }
        });



    }

    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();

        fList.add(MyFragment.newInstance( R.drawable.help_img_a));
        fList.add(MyFragment.newInstance( R.drawable.help_img_b));
        fList.add(MyFragment.newInstance( R.drawable.help_img_c));
        fList.add(MyFragment.newInstance( R.drawable.help_img_d));
        return fList;
    }

    public void BackHelp(View view) {
        onBackPressed();
    }


    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private int[] mResources;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments ) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount()
        {
            return this.fragments.size();
        }
    }
}
