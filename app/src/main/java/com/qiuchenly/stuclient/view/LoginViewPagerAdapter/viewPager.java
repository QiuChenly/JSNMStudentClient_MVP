package com.qiuchenly.stuclient.view.LoginViewPagerAdapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by QiuChenly on 2017/9/9.
 */

public class viewPager extends PagerAdapter {
    List<View> listView;
    public viewPager (List<View> listView){
        this.listView=listView;
    }

    @Override
    public int getCount() {
        return listView.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(listView.get(position));
        return listView.get(position);
    }


}
