package com.dev.sachin.sachinkumardemo.HelperClasses;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.sachin.sachinkumardemo.R;

import java.util.ArrayList;

/**
 * Created by sachin on 27-01-2018.
 */

public class SwipeAdapter extends PagerAdapter {

    ArrayList<ReposDetails> arrayList;
    private Context context;
    private LayoutInflater inflater;

    public SwipeAdapter(Context context,ArrayList<ReposDetails> arrayList){
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public int getCount() {
       // return arrayList.size();
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.swipe_layout,container,false);
        TextView namet=view.findViewById(R.id.git_name);
        TextView htmlurl=view.findViewById(R.id.git_htmlUrl);
        TextView desct=view.findViewById(R.id.git_descp);
        namet.setText(context.getResources().getString(R.string.rep_name)+" "+arrayList.get(position).getName());
        htmlurl.setText(context.getResources().getString(R.string.html_url)+" "+arrayList.get(position).getHtml_url());
        desct.setText(context.getResources().getString(R.string.descp)+" "+arrayList.get(position).getDescription());
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
