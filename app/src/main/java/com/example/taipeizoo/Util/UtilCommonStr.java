package com.example.taipeizoo.Util;

public class UtilCommonStr {

    private static UtilCommonStr mUtilCommonStr = null;

    public static UtilCommonStr getInstance() {
        if( mUtilCommonStr == null) {
            mUtilCommonStr = new UtilCommonStr();
        }
        return mUtilCommonStr;
    }

//    public String mDepartment = "Department";
    public String mAnimal = "動物簡介";
    public String mPlant = "植物簡介";
    public String mKeyRawJson = "KeyRawJson";

    public String mInSideArea;
    public String mOutSideArea;

    public String mKeyTitle = "mKeyTitle";

}
