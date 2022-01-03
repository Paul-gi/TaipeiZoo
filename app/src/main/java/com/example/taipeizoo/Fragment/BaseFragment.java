package com.example.taipeizoo.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.example.taipeizoo.R;
import com.example.taipeizoo.Util.ProgressDialogCustom;
import com.example.taipeizoo.Util.UtilCommonStr;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    public T mDataBing;
    public UtilCommonStr mUtilCommonStr = UtilCommonStr.getInstance();
    public String mTitleStr = "Title";
    //    public ProgressDialog mProgress;
    public ProgressDialogCustom mProgressDialogCustom;

    protected void initView() {
        mProgressDialogCustom = new ProgressDialogCustom(requireContext());
        checkBundle();
    }

    protected String getTitleName() {
        return mTitleStr;
    }


    public void checkBundle() {
        Bundle iBundle = getArguments();
        if (iBundle != null) {
            mTitleStr = iBundle.getString(mUtilCommonStr.mKeyTitle, "");
        }
    }

    public void goToNextPage(Fragment pFragment, String pTypeStr) {
        assert this.getFragmentManager() != null;
        Bundle iBundle = new Bundle();
        iBundle.putString(mUtilCommonStr.mKeyTitle, pTypeStr);
        this.getFragmentManager().beginTransaction()
                .add(R.id.mFragment, pFragment.getClass(), iBundle, pFragment.getClass().getSimpleName())
                .hide(this)
                .addToBackStack(null)
                .commit();
    }

    public void fragmentBackPressed() {
        assert getFragmentManager() != null;
        getFragmentManager().popBackStack();
    }

//    /**
//     * @param progress 進度條
//     */
//    public void Loading(ProgressDialog progress) {
//        progress.setMessage("載入中");
//        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progress.setCanceledOnTouchOutside(false);
//        progress.show();
//    }

//    protected abstract void initView();

}
