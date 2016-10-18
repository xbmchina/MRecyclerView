package com.example.zero.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class StaggerActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;
    private List<String> mDatas;
    private StaggerRecyclerAdapter mStaggerRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();
    }

    private void initView() {
        mRecycleView = (RecyclerView) findViewById(R.id.rcyView);
        mStaggerRecyclerAdapter = new StaggerRecyclerAdapter(this, mDatas);
        mRecycleView.setAdapter(mStaggerRecyclerAdapter);

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        mStaggerRecyclerAdapter.setOnItemOnclickLister(new SimpleRecyclerAdapater.OnItemOnclickLister() {
            @Override
            public void OnItemClick(View view, int pos) {

            }

            @Override
            public void OnLongItemClick(View view, int pos) {
                mStaggerRecyclerAdapter.deleteData(pos);
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


        return super.onOptionsItemSelected(item);
    }
}
