package com.example.taipeizoo.ViewModel;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taipeizoo.Service.RetrofitManager;
import com.example.taipeizoo.Service.ZooApiService;
import com.example.taipeizoo.Util.UtilCommonStr;
import com.example.taipeizoo.listdata.ListData;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<ListData>> mDataList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> mFinish = new MutableLiveData<>();
    private int mIndex = 0;
    private boolean mIsNotFinish = false;
    private boolean mGetData = false;
    private Call<JsonObject> mCall;

    public MutableLiveData<ArrayList<ListData>> getDataListObserver() {
        return mDataList;
    }

    public MutableLiveData<Boolean> getDataFinishState() {
        return mFinish;
    }

    public void mCallApi(String pTitleName) {
        if (mIsNotFinish) {
            return;
        }
        if (mGetData) {
            return;
        }
        synchronized (this) {
            mGetData = true;
        }

        ZooApiService mZooApiService = RetrofitManager.getInstance().createService(ZooApiService.class);

        if( pTitleName.equals( UtilCommonStr.getInstance().mAnimal)) {
            mCall = mZooApiService.getAnimalData(50, mIndex);
        } else if( pTitleName.equals( UtilCommonStr.getInstance().mPlant)) {
            mCall = mZooApiService.getPlantData(50, mIndex);
        } else {
            mCall = mZooApiService.getPavilionData(pTitleName, 50, mIndex);
        }

        mCall.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NotNull Call<JsonObject> call, @NotNull Response<JsonObject> response) {
                try {
                    ArrayList<ListData> iListData = new ArrayList<>();
                    assert response.body() != null;
                    JSONObject ix = new JSONObject(response.body().toString());
                    JSONArray iz = ix.getJSONObject("result").getJSONArray("results");

                    for (int i = 0; i < iz.length(); i++) {
                        ListData iData = new ListData();
                        iData.setData(iz.getJSONObject(i));

                        iData.selectType(pTitleName,false);
                        iListData.add(iData);
                    }
                    mDataList.postValue(iListData);

                    if (iListData.size() == 50) {
                        mIndex += 50;
                    } else {
                        mIsNotFinish = true;
                        mFinish.postValue(true);
                    }
                    mGetData = false;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call<JsonObject> call, @NotNull Throwable t) {
                mDataList.postValue(null);
            }
        });
    }

}
