package com.example.taipeizoo.Util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taipeizoo.R;
import com.example.taipeizoo.listdata.ListData;
import com.example.taipeizoo.listdata.LocationPositionData;

import java.util.ArrayList;

public class UtilTools {


    /**
     * @param pString 圖片url位址
     * @param pImage  image位址
     */
    public void controlPicture(Context pContext, String pString, ImageView pImage) {
        String iStringSplit;
        iStringSplit = pString.replace("?", "");
        Glide.with(pContext)
                .asBitmap()
                .load(iStringSplit)
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(pImage);
    }

    /**
     * @param pContext        context
     * @param pURL            取得URL 判斷有無資料
     * @param pImageView      圖片欄位
     * @param pTextView       圖片名稱欄位
     * @param mImageTitleView 第一張圖片都沒有則把Title也mark掉
     */
    public void setPictureGone(Context pContext, String pURL, ImageView pImageView, TextView pTextView, TextView mImageTitleView) {
        if (pURL.equals("")) {
            pImageView.setVisibility(View.GONE);
            mImageTitleView.setVisibility(View.GONE);
            if (pTextView != null) {
                pTextView.setVisibility(View.GONE);
            }
        } else {
            controlPicture(pContext, pURL, pImageView);
            if (pTextView != null) {
                pTextView.setText(pURL);
            }
        }
    }

    /**
     * 二三四張圖片用這個tool
     *
     * @param pContext
     * @param pURL
     * @param pImageView
     * @param pTextView
     */
    public void setPictureGone(Context pContext, String pURL, ImageView pImageView, TextView pTextView) {
        if (pURL.equals("")) {
            pImageView.setVisibility(View.GONE);
            if (pTextView != null) {
                pTextView.setVisibility(View.GONE);
            }
        } else {
            controlPicture(pContext, pURL, pImageView);
            if (pTextView != null) {
                pTextView.setText(pURL);
            }
        }
    }


    /**
     * 設定地理位置名稱
     */
    public void setGeo(ListData pListData, LocationPositionData pLocationPositionData, ArrayList<LocationPositionData> pGeoListData) {
        //"熱帶雨林室內館(穿山甲館)；兩棲爬蟲動物館",
        //"沙漠動物區；兒童動物區"
        String iGeo = pListData.getKeyLocation();
        String[] iGeoSplit = iGeo.split("；");
        String iGeoStore;
        for (int i = 0; i < iGeoSplit.length; i++) {
            iGeoStore = (iGeoSplit[i].replaceAll("\"", ""));
            pLocationPositionData.setKeyLocationLogo(iGeoStore);
            pGeoListData.add(pLocationPositionData);
            pLocationPositionData = new LocationPositionData();
        }
    }

    /**
     * 設定位置的Map
     */
    public void setLocation(ListData pListData, LocationPositionData pLocationPositionData, ArrayList<LocationPositionData> pLocationPositionDataArrayList) {
        //"MULTIPOINT ((121.5804577 24.9979216), (121.5805328 24.9959671), (121.5836763 24.9957094), (121.5894029 24.9951126), (121.5899205 24.9945669))",
        String iLocation, iSplit[];
        int iCount = 0;
        iLocation = pListData.getKeyGeo()
                .replaceAll("MULTIPOINT +\\(+\\(", "")
                .replaceAll("\\)+\\)", "")
                .replaceAll("\\)+,+ + + +\\(", "-")
                .replaceAll("\\)+,+ + +\\(", "-")
                .replaceAll("\\)+,+ +\\(", "-");
        //((121.5833766 24.9960938),(121.5898494 24.9940697))


        String[] iLocationStore = iLocation.split("-");
        for (int i = 0; i < iLocationStore.length; i++) {
            iSplit = iLocationStore[i].split(" |,");
            pLocationPositionData.setKeyX_position(iSplit[iCount]);
            pLocationPositionData.setKeyY_position(iSplit[iCount + 1]);
            pLocationPositionDataArrayList.add(pLocationPositionData);
            pLocationPositionData = new LocationPositionData();
            iCount = 0;
        }
    }

    /**
     * 沒有資料就把他GONE起來
     */
    public void setData(String pShowContext, TextView pTextView) {
        if (pShowContext == null) {
            pTextView.setVisibility(View.GONE);
        } else {
            pTextView.setText(pShowContext);
        }
    }


    /**
     * 在ScrollView中滑動TextView方法
     */
    @SuppressLint("ClickableViewAccessibility")
    public void setTextScrollView(TextView pTextView) {
        pTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
        pTextView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                v.getParent().requestDisallowInterceptTouchEvent(false);
            }
            return false;
        });
    }
}
