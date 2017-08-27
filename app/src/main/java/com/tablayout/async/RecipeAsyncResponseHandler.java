package com.tablayout.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.tablayout.Application;
import com.tablayout.moel.Material;
import com.tablayout.moel.Recipe;
import com.tablayout.moel.Process;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class RecipeAsyncResponseHandler extends JsonHttpResponseHandler {

    private AsyncClientInterface asyncClientInterface;
    private ArrayList<Recipe> recipes_ArrayList;
    private ArrayList<Process> processArrayList;
    private ArrayList<Material> materialArrayList;
    private Context context;
    private ProgressDialog progressDialog;
    private String name;

    /**
     * 初始类
     * @param context
     * @param asyncClientInterface
     */

    public RecipeAsyncResponseHandler(Context context, AsyncClientInterface asyncClientInterface, String name){
        this.context = context;
        this.asyncClientInterface = asyncClientInterface;
        this.name = name;

        //定义diglog
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("努力加载中......");
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        super.onSuccess(statusCode, headers, response);

        if (statusCode == 200){
            Application.statusCode = true;
        }

        Log.d("statusCode", String.valueOf(statusCode));

        try {
            if (response.opt(name).equals(null)) {
                Toast.makeText(context,"搜索结果为空,请缩短关键词再试！", Toast.LENGTH_SHORT).show();
                return;
            }else {
                JSONObject recipe_jsonObject = response.getJSONObject(name).getJSONObject(name);
                JSONArray recipe_jsonArray =  recipe_jsonObject.getJSONArray("list");
                recipes_ArrayList = new ArrayList<Recipe>();

                for (int i = 0; i < recipe_jsonArray.length(); i++){
                    JSONObject jsonObject = (JSONObject) recipe_jsonArray.get(i);
                    JSONArray processJsonArray = jsonObject.optJSONArray("process");
                    JSONArray materialJsonArray = jsonObject.optJSONArray("material");
                    processArrayList = new ArrayList<Process>();
                    materialArrayList = new ArrayList<Material>();

                    for (int p =0; p < processJsonArray.length(); p++){
                        JSONObject process_jsonObject = (JSONObject) processJsonArray.get(p);
                        String pcontent = process_jsonObject.optString("pcontent");
                        String pic = process_jsonObject.optString("pic");
                        processArrayList.add(new Process(pcontent,pic));
                    }

                    for (int m =0; m < materialJsonArray.length(); m++){
                        JSONObject material_jsonObject = (JSONObject) materialJsonArray.get(m);
                        String amount = material_jsonObject.optString("amount");
                        String mname = material_jsonObject.optString("mname");
                        materialArrayList.add(new Material(amount,mname));
                    }

                    String preparetime = jsonObject.optString("preparetime");
                    String name = jsonObject.optString("name");
                    String id = jsonObject.optString("id");
                    String pic = jsonObject.optString("pic");
                    String tag = jsonObject.optString("tag");
                    String peoplenum = jsonObject.optString("peoplenum");
                    String content = jsonObject.optString("content");
                    String cookingtime = jsonObject.optString("cookingtime");
                    recipes_ArrayList.add(new Recipe(processArrayList,materialArrayList,name,id,pic,tag,peoplenum,content,cookingtime));
                    Log.d("titletitle",processArrayList.get(0).toString() + "");
                }
                asyncClientInterface.onSuccess(statusCode, recipes_ArrayList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        super.onFailure(statusCode, headers, throwable, errorResponse);
        asyncClientInterface.onFailure(statusCode);
    }


    @Override
    public void onStart() {
        super.onStart();
        progressDialog.show();
    }

    @Override
    public void onFinish() {
        super.onFinish();
        progressDialog.dismiss();
    }
}
