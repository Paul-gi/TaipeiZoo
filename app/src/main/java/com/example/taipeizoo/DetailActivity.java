package com.example.taipeizoo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.taipeizoo.Util.UtilCommonStr;
import com.example.taipeizoo.Util.UtilTools;
import com.example.taipeizoo.Util.WebViewActivity;
import com.example.taipeizoo.databinding.MainDetailActivityBinding;
import com.example.taipeizoo.listdata.ListData;
import com.example.taipeizoo.listdata.LocationPositionData;
import com.example.taipeizoo.zooListAdapter.GoogleMapGeoAdapter;

import java.util.ArrayList;

public class DetailActivity extends Activity {
    MainDetailActivityBinding mDataBinding;
    Bundle mBundle;
    String mTitle;
    ListData mListData = new ListData();
    LocationPositionData mLocationPositionData = new LocationPositionData();
    GoogleMapGeoAdapter mGoogleMapGeoAdapter;
    ArrayList<LocationPositionData> mLocationPositionListData = new ArrayList<>();
    ArrayList<LocationPositionData> mGeoListData = new ArrayList<>();
    UtilTools mUtilTools = new UtilTools();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBundleData();
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.main_detail_activity);

        if (mTitle.equals(UtilCommonStr.getInstance().mAnimal)) {
            initAnimalView();
        } else if (mBundle.getString("TitleName").equals(UtilCommonStr.getInstance().mPlant)) {
            initPlantView();
        } else {
            initDepartment();
        }

        mDataBinding.mTitleBar.mToolbar.setTitle(mTitle);
        mDataBinding.mTitleBar.mChange.setVisibility(View.GONE);
        initBelowView();
    }

    private void getBundleData() {
        mBundle = getIntent().getExtras();
        mTitle = mBundle.getString("TitleName");
        mListData.setRawJson(mTitle, mBundle.getString("ListData"));
    }


    private void initDepartment() {
        mDataBinding.mDepartmentDetail.getRoot().setVisibility(View.VISIBLE);
        mDataBinding.mAnimalDetail.getRoot().setVisibility(View.GONE);
        mDataBinding.mPlantDetail.getRoot().setVisibility(View.GONE);
        mDataBinding.mBelowDetail.mGeoRecycleViewGeo.setVisibility(View.GONE);

        mUtilTools.setData(mListData.getKeyEName(), mDataBinding.mDepartmentDetail.mEName);
        mUtilTools.setData(mListData.getKeyKeyInfo(), mDataBinding.mDepartmentDetail.mEInfo);
        mUtilTools.setData(mListData.getKeyMemo(), mDataBinding.mDepartmentDetail.mEMemo);
        mUtilTools.setData(mListData.getKeyDistribution(), mDataBinding.mDepartmentDetail.mADistribution);

        mDataBinding.mDepartmentDetail.mDistributionView.setVisibility(View.GONE);
        mDataBinding.mDepartmentDetail.mADistribution.setVisibility(View.GONE);
    }

    private void initAnimalView() {
        mDataBinding.mDepartmentDetail.getRoot().setVisibility(View.GONE);
        mDataBinding.mAnimalDetail.getRoot().setVisibility(View.VISIBLE);
        mDataBinding.mPlantDetail.getRoot().setVisibility(View.GONE);

        mUtilTools.setData(mListData.getChineseName(), mDataBinding.mAnimalDetail.mANameCh);
        mUtilTools.setData(mListData.getEnglishName(), mDataBinding.mAnimalDetail.mANameEn);
        mUtilTools.setData(mListData.getKeyBehavior(), mDataBinding.mAnimalDetail.mABehavior);
        mUtilTools.setData(mListData.getKeyDistribution(), mDataBinding.mAnimalDetail.mADistribution);
        mUtilTools.setData(mListData.getKeyClass(), mDataBinding.mAnimalDetail.mAClass);
        mUtilTools.setData(mListData.getKeyFamily(), mDataBinding.mAnimalDetail.mAFamily);
        mUtilTools.setTextScrollView(mDataBinding.mAnimalDetail.mABehavior);
    }

    private void initPlantView() {
        mDataBinding.mDepartmentDetail.getRoot().setVisibility(View.GONE);
        mDataBinding.mAnimalDetail.getRoot().setVisibility(View.GONE);
        mDataBinding.mPlantDetail.getRoot().setVisibility(View.VISIBLE);

        mUtilTools.setData(mListData.getChineseName(), mDataBinding.mPlantDetail.mANameCh);
        mUtilTools.setData(mListData.getEnglishName(), mDataBinding.mPlantDetail.mANameEn);
        mUtilTools.setData(mListData.getKeyAlsoKnown(), mDataBinding.mPlantDetail.mAAlsoKnown);
        mUtilTools.setData(mListData.getKeyBrief(), mDataBinding.mPlantDetail.ABrief);
        mUtilTools.setData(mListData.getKeyGenus(), mDataBinding.mPlantDetail.mAGenus);
        mUtilTools.setData(mListData.getKeyFeature(), mDataBinding.mPlantDetail.mAFeature);
        mUtilTools.setData(mListData.getKeyFamily(), mDataBinding.mPlantDetail.mAFamily);
        mUtilTools.setData(mListData.getKeyFunctionApplication(), mDataBinding.mPlantDetail.mAFunctionApplication);
    }

    private void initBelowView() {

        mUtilTools.setPictureGone(this, mListData.keyUrl01(), mDataBinding.mBelowDetail.mAPic01URL, mDataBinding.mBelowDetail.mAPic01ALT, mDataBinding.mBelowDetail.mImageTitle);
        mUtilTools.setPictureGone(this, mListData.keyUrl02(), mDataBinding.mBelowDetail.mAPic02URL, mDataBinding.mBelowDetail.mAPic02ALT);
        mUtilTools.setPictureGone(this, mListData.keyUrl03(), mDataBinding.mBelowDetail.mAPic03URL, mDataBinding.mBelowDetail.mAPic03ALT);
        mUtilTools.setPictureGone(this, mListData.keyUrl04(), mDataBinding.mBelowDetail.mAPic04URL, mDataBinding.mBelowDetail.mAPic04ALT);
        mUtilTools.setData(mListData.getKeyAlt01(), mDataBinding.mBelowDetail.mAPic01ALT);
        mUtilTools.setData(mListData.getKeyAlt02(), mDataBinding.mBelowDetail.mAPic02ALT);
        mUtilTools.setData(mListData.getKeyAlt03(), mDataBinding.mBelowDetail.mAPic03ALT);
        mUtilTools.setData(mListData.getKeyAlt04(), mDataBinding.mBelowDetail.mAPic04ALT);
        mUtilTools.setGeo(mListData, mLocationPositionData, mGeoListData);
        mUtilTools.setLocation(mListData, mLocationPositionData, mLocationPositionListData);
        mDataBinding.mBelowDetail.mLocation.setOnClickListener(v -> {
            Intent iIntent = new Intent();
            Bundle iBundle = new Bundle();
            iBundle.putSerializable("mLocationPositionListData", mLocationPositionListData);
            iIntent.setClass(v.getContext(), GoogleMapActivity.class);
            iIntent.putExtras(iBundle);
            startActivity(iIntent);
        });
        mGoogleMapGeoAdapter = new GoogleMapGeoAdapter(mGeoListData);
        mDataBinding.mBelowDetail.mGeoRecycleViewGeo.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.mBelowDetail.mGeoRecycleViewGeo.setAdapter(mGoogleMapGeoAdapter);
        mDataBinding.mTitleBar.mBackBtn.setOnClickListener(v -> {
            this.finish();
        });
        if (mListData.getKeyVedio().equals("")) {
            mDataBinding.mBelowDetail.mAVedioURL.setVisibility(View.GONE);
            mDataBinding.mBelowDetail.mVdoView.setVisibility(View.GONE);
        } else {
            //jumpVedioUrl
            mDataBinding.mBelowDetail.mAVedioURL.setOnClickListener(v -> {
                //點擊外開影片連結
//                Intent pIntent = new Intent(Intent.ACTION_VIEW, Uri.parse((mListData.getKeyVedio())));
//                startActivity(pIntent);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("getUrl", mListData.getKeyVedio());
                intent.setClass(DetailActivity.this, WebViewActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            });
        }
    }
}
