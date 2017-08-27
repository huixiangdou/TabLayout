package com.tablayout.async;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/23.
 */

public interface AsyncClientInterface {
    void onSuccess(int statusCode, ArrayList arrayList);
    void onSuccess(ArrayList arrayList);
    void onSuccess(String string);
    void onFailure(int statusCode);
}
