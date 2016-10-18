package com.example.zero.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private List<String> mDatas;
    private SimpleRecyclerAdapater mSimpleRecyclerAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();
    }

    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.rcyView);
        mSimpleRecyclerAdapater = new SimpleRecyclerAdapater(this, mDatas);
        mRecycleView.setAdapter(mSimpleRecyclerAdapater);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);

        mRecycleView.setItemAnimator(new DefaultItemAnimator());//item的动画效果

        mSimpleRecyclerAdapater.setOnItemOnclickLister(new SimpleRecyclerAdapater.OnItemOnclickLister() {
            @Override
            public void OnItemClick(View view, int pos) {
                Toast.makeText(MainActivity.this,"onclik:"+pos,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void OnLongItemClick(View view, int pos) {
                Toast.makeText(MainActivity.this,"onlongclik:"+pos,Toast.LENGTH_SHORT).show();
            }
        });

        //每个item添加分隔线
//        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_add:
                //添加
                mSimpleRecyclerAdapater.addData(1);
                break;
            case R.id.action_delete:
                //删除
                mSimpleRecyclerAdapater.deleteData(1);
                break;
            case R.id.action_listview:
                mRecycleView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.action_gridview:
                mRecycleView.setLayoutManager(new GridLayoutManager(this, 3));//RecycleView由ListView变成GridView只需这一行代码
                break;
            case R.id.action_horizongridview:
                mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.action_stagger:
                Intent intent = new Intent(this, StaggerActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
