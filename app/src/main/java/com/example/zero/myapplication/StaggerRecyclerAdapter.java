package com.example.zero.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class StaggerRecyclerAdapter extends SimpleRecyclerAdapater{

    public StaggerRecyclerAdapter(Context context,List<String> mDatas) {
        super(context,mDatas);

    }



    //绑定ViewHoler
    @Override
    public void onBindViewHolder(MyViewHoder myViewHoder, int position) {
//        ViewGroup.LayoutParams layoutParams=myViewHoder.tv.getLayoutParams();//获取控件的参数对象
//        layoutParams.height=mHeight.get(position);//设置控件的参数
//        Log.i("tag",layoutParams.height+"hhh");
//        myViewHoder.tv.setLayoutParams(layoutParams);//将新设置的参数，实现在控件上

        myViewHoder.tv.setText(mDatas.get(position));
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, 100+(int) (Math.random() * 300));
        myViewHoder.tv.setLayoutParams(lp);
        setUpOnItemClick(myViewHoder);

    }

}

