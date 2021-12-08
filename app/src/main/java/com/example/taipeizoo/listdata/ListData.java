package com.example.taipeizoo.listdata;

import com.example.taipeizoo.Util.UtilCommonStr;

import org.json.JSONException;
import org.json.JSONObject;

public class ListData {

    private JSONObject mJsonObject;
    private ZooDataDetail mZooDataDetail;
    private String keyUrl01 = "";
    private String keyChineseName = "";
    private String keyEnglishName = "";

    public void setData(JSONObject asJsonObject) {
        mJsonObject = asJsonObject;
    }

    public void setRawJson(String pRawJson, String pValueType) {
        try {
            mJsonObject = new JSONObject(pRawJson);
            switch (pValueType) {
                case "Department":
                    setTypeDepartment(true);
                    break;
                case "Animal":
                    setTypeAnimal(true);
                    break;
                case "Plant":
                    setTypePlant(true);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void selectType(String pTitleName, Boolean pDetail) {
        if( pTitleName.equals( UtilCommonStr.getInstance().mAnimal)) {
            setTypeAnimal(pDetail);
        } else if( pTitleName.equals( UtilCommonStr.getInstance().mPlant)) {
            setTypePlant(pDetail);
        } else {
            setTypeDepartment(pDetail);
        }
    }

    public void setTypeAnimal(Boolean pDetail) {
        keyUrl01 = "A_Pic01_URL";
        keyChineseName = "\uFEFFA_Name_Ch";
        keyEnglishName = "A_Name_En";
        if (pDetail) {
            mZooDataDetail = new ZooDataDetail("A");
        }
    }

    public void setTypePlant(Boolean pDetail) {
        keyUrl01 = "F_Pic01_URL";
        keyEnglishName = "F_Name_En";
        keyChineseName = "\uFEFFF_Name_Ch";
        if (pDetail) {
            mZooDataDetail = new ZooDataDetail("F");
        }
    }

    public void setTypeDepartment(Boolean pDetail) {
        keyUrl01 = "E_Pic_URL";
        keyChineseName = "E_Name";
        if (pDetail) {
            mZooDataDetail = new ZooDataDetail("E");
        }
    }

    public String getRawData() {
        return mJsonObject.toString();
    }

    public String keyUrl01() {
        return getData(keyUrl01);
    }

    public String getEnglishName() {
        return getData(keyEnglishName);
    }

    public String getChineseName() {
        return getData(keyChineseName);
    }


    public String keyUrl02() {
        return getData(mZooDataDetail.keyUrl02);
    }

    public String keyUrl03() {
        return getData(mZooDataDetail.keyUrl03);
    }

    public String keyUrl04() {
        return getData(mZooDataDetail.keyUrl04);
    }

    public String getKeyAlt01() {
        return getData(mZooDataDetail.keyAlt01);
    }

    public String getKeyAlt02() {
        return getData(mZooDataDetail.keyAlt02);
    }

    public String getKeyAlt03() {
        return getData(mZooDataDetail.keyAlt03);
    }

    public String getKeyAlt04() {
        return getData(mZooDataDetail.keyAlt04);
    }

    public String getKeyClass() {
        return getData(mZooDataDetail.keyClass);
    }

    public String getKeyDistribution() {
        return getData(mZooDataDetail.keyDistribution);
    }

    public String getKeyFamily() {
        return getData(mZooDataDetail.keyFamily);
    }

    public String getKeyGeo() {
        return getData(mZooDataDetail.keyGeo);
    }

    public String getKeyLocation() {
        return getData(mZooDataDetail.keyLocation);
    }

    public String getKeyVedio() {
        return getData(mZooDataDetail.keyVedio);
    }

    public String getKeyFunctionApplication() {
        return getData(mZooDataDetail.KeyFunctionApplication);
    }

    public String getKeyGenus() {
        return getData(mZooDataDetail.KeyGenus);
    }

    public String getKeyFeature() {
        return getData(mZooDataDetail.KeyFeature);
    }

    public String getKeyBrief() {
        return getData(mZooDataDetail.KeyBrief);
    }

    public String getKeyAlsoKnown() {
        return getData(mZooDataDetail.KeymAlsoKnown);
    }

    public String getKeyKeyInfo() {
        return getData(mZooDataDetail.KeyInfo);
    }

    public String getKeyE_Pic_URL() {
        return getData(mZooDataDetail.KeyE_Pic_URL);
    }

    public String getKeyMemo() {
        return getData(mZooDataDetail.KeyMemo);
    }

    public String getKeyUrl() {
        return getData(mZooDataDetail.KeyUrl);
    }

    public String getKeyEName() {
        return getData(mZooDataDetail.KeyEName);
    }

    public String getKeyBehavior() {
        return getData(mZooDataDetail.keyBehavior);
    }

    private String getData(String pKey) {
        if (pKey == null || pKey.equals("")) {
            pKey = "";
        }
        try {
            return mJsonObject.getString(pKey);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}

@SuppressWarnings("SpellCheckingInspection")
class ZooDataDetail {
    public String keyUrl02;
    public String keyUrl03;
    public String keyUrl04;
    public String keyBehavior;
    public String keyDistribution;
    public String keyAlt01;
    public String keyAlt02;
    public String keyAlt03;
    public String keyAlt04;
    public String keyClass;
    public String keyFamily;
    public String keyVedio;
    public String keyLocation;
    public String keyGeo;
    public String KeymAlsoKnown;
    public String KeyBrief;
    public String KeyFeature;
    public String KeyGenus;
    public String KeyFunctionApplication;
    public String KeyInfo;
    public String KeyMemo;
    public String KeyUrl;
    public String KeyE_Pic_URL;
    public String KeyEName;

    /**
     * @param pType + _Pic02_URL
     *              因管區 最前面為 “X+固定字串”
     */
    ZooDataDetail(String pType) {
        keyUrl02 = pType + "_Pic02_URL";
        keyUrl03 = pType + "_Pic03_URL";
        keyUrl04 = pType + "_Pic04_URL";
        keyAlt01 = pType + "_Pic01_ALT";
        keyAlt02 = pType + "_Pic02_ALT";
        keyAlt03 = pType + "_Pic03_ALT";
        keyAlt04 = pType + "_Pic04_ALT";
        keyBehavior = pType + "_Behavior";
        keyDistribution = pType + "_Distribution";
        keyClass = pType + "_Class";
        keyFamily = pType + "_Family";
        keyVedio = pType + "_Vedio_URL";
        keyLocation = pType + "_Location";
        keyGeo = pType + "_Geo";
        KeymAlsoKnown = pType + "_AlsoKnown";
        KeyBrief = pType + "_Brief";
        KeyFeature = pType + "_Feature";
        KeyGenus = pType + "_Genus";
        KeyFunctionApplication = pType + "_Function＆Application";

        KeyE_Pic_URL = pType + "_Pic_URL";
        KeyInfo = pType + "_Info";
        KeyMemo = pType + "_Memo";
        KeyUrl = pType + "_URL";
        KeyEName = pType + "_Name";


    }
}
