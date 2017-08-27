package com.tablayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.widget.TextView;

import com.headerfooter.songhang.library.SmartRecyclerAdapter;
import com.tablayout.adapter.MaterialAdapter;
import com.tablayout.adapter.ProcessAdapter;
import com.tablayout.moel.Material;
import com.tablayout.moel.Recipe;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {
    private Recipe recipe;
    private ProcessAdapter processAdapter;
    private MaterialAdapter materialAdapter;
    private StaggeredGridLayoutManager materialLinearLayout;
    private LinearLayoutManager processLinearLayout;
    private RecyclerView materialRecycler;
    private RecyclerView processRecycler;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("RECIPE");

        TextView name_tv = (TextView) findViewById(R.id.name_tv);
        TextView tag_tv = (TextView) findViewById(R.id.tag_tv);
        TextView cookingtime_tv = (TextView) findViewById(R.id.cookingtime_tv);
        TextView content_tv = (TextView) findViewById(R.id.content_tv);

        name_tv.setText(recipe.getName());
        cookingtime_tv.setText("烹饪时长: " + recipe.getCookingtime());
        tag_tv.setText("标签: " + recipe.getTag());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            content_tv.setText(Html.fromHtml(recipe.getContent(),Html.FROM_HTML_MODE_COMPACT));
        }else {
            content_tv.setText(Html.fromHtml(recipe.getContent()));
        }

        ArrayList<Material> materialArrayList = recipe.getMaterialArrayList();
        materialLinearLayout = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        materialAdapter = new MaterialAdapter(materialArrayList);
        SmartRecyclerAdapter smartRecyclerAdapter = new SmartRecyclerAdapter(materialAdapter);
        materialRecycler = (RecyclerView) findViewById(R.id.maerial_recycler);
        materialRecycler.setLayoutManager(materialLinearLayout);
        materialRecycler.setAdapter(smartRecyclerAdapter);

//        ArrayList<Process> processArrayList = recipe.getProcessArrayList();
//        processAdapter = new ProcessAdapter(processArrayList);
//        processLinearLayout = new LinearLayoutManager(this);
//        processRecycler = (RecyclerView) findViewById(R.id.process_recycler);
//        processRecycler.setLayoutManager(processLinearLayout);
//        processRecycler.setAdapter(processAdapter);
    }
}
