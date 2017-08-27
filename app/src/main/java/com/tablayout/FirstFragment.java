package com.tablayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tablayout.adapter.OnRecyclerViewItemClickListener;
import com.tablayout.adapter.RecipeAdapter;
import com.tablayout.async.AsyncClientInterface;
import com.tablayout.async.ClientUtils;
import com.tablayout.async.RecipeAsyncResponseHandler;
import com.tablayout.moel.Recipe;
import com.tablayout.utils.ImageUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/25.
 */

public class FirstFragment extends Fragment implements AsyncClientInterface {
    TextView textView;
    private ArrayList<Recipe> recipeArrayList;
    private RecipeAdapter recipeAdapter;
    private RecyclerView recipeRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private View view;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        context = container.getContext();
        ImageUtils.initUniversalImageLoader(container.getContext());
        view = inflater.inflate(R.layout.fragment_first,null);
        String name = "result";
        String url = "http://way.jd.com/jisuapi/byclass?classid=2&start=0&num=20&appkey=5b200400becd4eca53c4242203ab251e";
        ClientUtils.getClientUtils().getNetDate(url,new RecipeAsyncResponseHandler(container.getContext(),this,name));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSuccess(int statusCode, ArrayList arrayList) {
        mLayoutManager = new LinearLayoutManager(context);
        recipeRecyclerView = view.findViewById(R.id.recipe_recycler);
        recipeRecyclerView.setLayoutManager(mLayoutManager);
        recipeAdapter = new RecipeAdapter(arrayList);
        recipeRecyclerView.setAdapter(recipeAdapter);
        recipeAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object object) {
                Recipe recipe = (Recipe) object;
                Log.d("123123123",recipe.getProcessArrayList().get(0).getPcontent()+"");
                Intent intent = new Intent(context,ContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("RECIPE",recipe);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(ArrayList arrayList) {

    }

    @Override
    public void onSuccess(String string) {

    }

    @Override
    public void onFailure(int statusCode) {

    }
}
