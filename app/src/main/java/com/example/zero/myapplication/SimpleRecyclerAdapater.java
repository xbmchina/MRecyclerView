package com.example.zero.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public class SimpleRecyclerAdapater extends RecyclerView.Adapter<MyViewHoder> {
    private Context mContext;
    protected List<String> mDatas;
    private LayoutInflater mInflater;

    public SimpleRecyclerAdapater(Context context, List<String> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    public interface OnItemOnclickLister {
        void OnItemClick(View view, int pos);

        void OnLongItemClick(View view, int pos);
    }

    private OnItemOnclickLister mOnItemOnclickLister;

    public void setOnItemOnclickLister(OnItemOnclickLister onItemOnclickLister) {
        this.mOnItemOnclickLister = onItemOnclickLister;
    }

    //创建ViewHolder
    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.items_single, viewGroup, false);
        MyViewHoder viewHoder = new MyViewHoder(view);
        return viewHoder;
    }

    //绑定ViewHoler
    @Override
    public void onBindViewHolder(final MyViewHoder myViewHoder, final int position) {
        myViewHoder.tv.setText(mDatas.get(position));

        setUpOnItemClick(myViewHoder);

    }

    protected void setUpOnItemClick(final MyViewHoder myViewHoder) {
        if (mOnItemOnclickLister != null) {

            myViewHoder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPostion = myViewHoder.getLayoutPosition();//防止position没更新。
                    mOnItemOnclickLister.OnItemClick(myViewHoder.itemView, layoutPostion);

                }
            });

            myViewHoder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPostion = myViewHoder.getLayoutPosition();
                    mOnItemOnclickLister.OnLongItemClick(myViewHoder.itemView, layoutPostion);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    public void addData(int pos) {
        mDatas.add(pos, "Insert one");

        notifyItemInserted(pos);
    }

    public void deleteData(int pos) {
        mDatas.remove(pos);

        notifyItemRemoved(pos);
    }


}

class MyViewHoder extends RecyclerView.ViewHolder {
    TextView tv;

    public MyViewHoder(View view) {
        super(view);
        tv = (TextView) view.findViewById(R.id.tv);
    }
}