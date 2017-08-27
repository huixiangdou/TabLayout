package com.tablayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tablayout.R;
import com.tablayout.moel.Recipe;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/23.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> implements View.OnClickListener {
    private ArrayList<Recipe> recipeArrayList;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;
    public RecipeAdapter(ArrayList arrayList) {
        this.recipeArrayList = arrayList;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_item,parent,false);
        RecipeHolder recipeHolder = new RecipeHolder(view);
        view.setOnClickListener(this);
        return recipeHolder;
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        Recipe recipe = recipeArrayList.get(position);
        if (recipe.getPic().isEmpty()){
            recipe.setPic("http://api.jisuapi.com/recipes/upload/201708/23170003_38771.jpg");
        }
        ImageLoader.getInstance().displayImage(recipe.getPic(),holder.pic);
        holder.cookingtime.setText(recipe.getCookingtime());
        holder.name.setText(recipe.getName());
        holder.peoplenum.setText(recipe.getPeoplenum());

        holder.itemView.setTag(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClickListener != null){
            onRecyclerViewItemClickListener.onItemClick(view,(Recipe)view.getTag());
        }
    }

    public static class RecipeHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView peoplenum;
        private TextView cookingtime;
        private ImageView pic;
        public RecipeHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipe_name_tv);
            peoplenum = itemView.findViewById(R.id.recipe_peoplenum_tv);
            cookingtime = itemView.findViewById(R.id.recipe_cookingtime_tv);
            pic = itemView.findViewById(R.id.recipe_pic_image);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }
}
