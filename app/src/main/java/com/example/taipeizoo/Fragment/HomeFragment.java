package com.example.taipeizoo.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.example.taipeizoo.R;
import com.example.taipeizoo.databinding.HomeFragmentBinding;

public class HomeFragment extends BaseFragment<HomeFragmentBinding> {
    /**
     * <isOpenFragment>
     * home頁是1
     * 打開管區是2
     * 其他的fragment一律給99
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBing = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        initView();
        return mDataBing.getRoot();
    }


    @Override
    protected void initView() {
        super.initView();
        mDataBing.mAllAreaNavigation.mDepartmentButton.setOnClickListener(view -> {
            openDepartmentPage();
            mDataBing.mDepartmentNavigation.mInDoorAreaBtn.setOnClickListener(view1 ->
                    goToNextPage(new NavigationFragment(), mUtilCommonStr.mInSideArea));
            mDataBing.mDepartmentNavigation.mOutDoorAreaBtn.setOnClickListener(view12 ->
                    goToNextPage(new NavigationFragment(), mUtilCommonStr.mOutSideArea));

        });

        mDataBing.mAllAreaNavigation.mAnimalButton.setOnClickListener(view -> {
            goToNextPage(new NavigationFragment(), mUtilCommonStr.mAnimal);
        });

        mDataBing.mAllAreaNavigation.mPlantButton.setOnClickListener(view -> {
            goToNextPage(new NavigationFragment(), mUtilCommonStr.mPlant);
        });


        mDataBing.mToolbarLayout.mBackBtn.setOnClickListener(view -> {
            if (mDataBing.mToolbarLayout.getRoot().getVisibility() == View.VISIBLE) {
                openHomePage();
            } else {
                fragmentBackPressed();
            }
        });
    }

    private void openHomePage() {
        mDataBing.mToolbarLayout.getRoot().setVisibility(View.GONE);
        mDataBing.mAllAreaNavigation.getRoot().setVisibility(View.VISIBLE);
        mDataBing.mZooLogoImage.setVisibility(View.VISIBLE);
        mDataBing.mDepartmentNavigation.getRoot().setVisibility(View.GONE);
    }

    private void openDepartmentPage() {
        mDataBing.mToolbarLayout.mToolbar.setTitle("館區簡介");
        mDataBing.mToolbarLayout.getRoot().setVisibility(View.VISIBLE);
        mDataBing.mToolbarLayout.mChange.setVisibility(View.GONE);
        mDataBing.mAllAreaNavigation.getRoot().setVisibility(View.GONE);
        mDataBing.mZooLogoImage.setVisibility(View.GONE);
        mDataBing.mDepartmentNavigation.getRoot().setVisibility(View.VISIBLE);
    }
}
